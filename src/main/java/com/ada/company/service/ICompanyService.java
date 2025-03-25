package com.ada.company.service;

import com.ada.company.dto.CompanyDto;
/**
 * Interfaz que define las operaciones básicas de gestión de tarjetas bancarias.
 * Contrato para implementaciones de servicios que manejen lógica de negocio relacionada con tarjetas.
 */
public interface ICompanyService {

    /**
     * Crea una nueva tarjeta asociada a un cliente existente.
     * @param codigoCompany Número móvil único del cliente (validado por formato)
     * @throws IllegalArgumentException Si el formato del número móvil es inválido
     * @example Ejemplo: createCard("1234567890") crea tarjeta para móvil 1234567890
     */
    void createCompany(String codigoCompany);

    /**
     * Recupera la información completa de una tarjeta existente.
     * @param codigoCompany Número móvil registrado asociado a la tarjeta
     * @return DTO con los detalles de la tarjeta del cliente
     * @apiNote El número móvil debe cumplir con el formato \d{10}
     */
    CompanyDto fetchCompany(String codigoCompany);

    /**
     * Actualiza la información de una tarjeta existente.
     * @param companyDto Objeto con los nuevos datos de la tarjeta
     * @return true si la actualización fue exitosa, false si falló
     * @implNote Actualiza límites de crédito y configuración de la tarjeta
     */
    boolean updateCompany(CompanyDto companyDto);

    /**
     * Elimina permanentemente una tarjeta del sistema.
     * @param codigoCompany Número móvil asociado a la tarjeta
     * @return true si la eliminación fue exitosa, false si no se encontró
     * @sample deleteCard("0987654321") elimina tarjeta del móvil 0987654321
     */
    boolean deleteCompany(String codigoCompany);
}