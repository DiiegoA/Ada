package com.ada.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando se intenta crear un cliente que ya existe en el sistema.
 * Anotación que define el código de estado HTTP a devolver (400 BAD_REQUEST)
 * cuando esta excepción es lanzada y no se captura explícitamente.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CompanyAlreadyExistsException extends RuntimeException {

    /**
     * Constructor que crea la excepción con un mensaje específico.
     * @param message Descripción detallada del error (ej: "Cliente con email 'x@y.com' ya existe")
     * El mensaje será incluido en la respuesta HTTP automáticamente.
     */
    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}