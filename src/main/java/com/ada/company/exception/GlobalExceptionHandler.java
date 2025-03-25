package com.ada.company.exception;

import com.ada.company.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manejador global de excepciones para la aplicación de gestión de tarjetas.
 * Proporciona respuestas estandarizadas para diferentes tipos de errores.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja errores de validación de argumentos en solicitudes HTTP.
     * @param ex Excepción generada por validación fallida
     * @param headers Encabezados HTTP de la respuesta
     * @param status Código de estado HTTP
     * @param request Solicitud web actual
     * @return ResponseEntity con mapa de errores de validación
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, String> validationErrors = ex.getBindingResult().getAllErrors()
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ?
                                error.getDefaultMessage() : "Validation error",
                        (existing, replacement) -> existing));

        return ResponseEntity.badRequest().body(validationErrors);
    }

    /**
     * Maneja excepciones genéricas no capturadas específicamente.
     * @param exception Excepción lanzada
     * @param webRequest Contexto de la solicitud web
     * @return ResponseEntity con detalles del error interno
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception exception, WebRequest webRequest) {

        return ResponseEntity.internalServerError()
                .body(new ErrorResponseDto(
                        webRequest.getDescription(false),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }

    /**
     * Maneja casos donde no se encuentra un recurso solicitado.
     * @param exception Excepción de recurso no encontrado
     * @param webRequest Contexto de la solicitud web
     * @return ResponseEntity con detalles del error 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(
                        webRequest.getDescription(false),
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }

    /**
     * Maneja intentos de crear tarjetas duplicadas.
     * @param exception Excepción de tarjeta existente
     * @param webRequest Contexto de la solicitud web
     * @return ResponseEntity con detalles del error 400
     */
    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException(
            CompanyAlreadyExistsException exception, WebRequest webRequest) {

        return ResponseEntity.badRequest()
                .body(new ErrorResponseDto(
                        webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }
}

