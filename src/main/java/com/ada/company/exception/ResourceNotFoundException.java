package com.ada.company.exception;

// Importa clases necesarias de Spring Framework
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando un recurso requerido no existe en el sistema.
 * Anotación que define el código de estado HTTP 404 (NOT_FOUND) que se devolverá
 * automáticamente cuando esta excepción no sea capturada explícitamente.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
     * Constructor que genera un mensaje descriptivo basado en los parámetros del recurso.
     * @param resourceName Nombre del recurso buscado (ej: "Cuenta", "Cliente")
     * @param fieldName    Campo usado para la búsqueda (ej: "número de cuenta", "ID")
     * @param fieldValue   Valor usado en la búsqueda (ej: "AC123456", "789")
     * El mensaje se construye automáticamente en formato:
     * "[Recurso] not found with [campo] : '[valor]'"
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("No se encontró el %s con los datos de entrada proporcionados '%s' : '%s'",
                resourceName, fieldName, fieldValue));
    }
}
