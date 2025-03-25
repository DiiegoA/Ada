package com.ada.company.controller;

import com.ada.company.constants.CompanyConstants;
import com.ada.company.dto.CompanyDto;
import com.ada.company.dto.ResponseDto;
import com.ada.company.service.ICompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CompanyController {

    private final ICompanyService companyService;

    /**
     * Crea una nueva tarjeta asociada a un número móvil.
     * @param codigoCompany Número de móvil validado (10 dígitos)
     * @return Respuesta con estado de la operación
     */

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCompany(
            @Valid @RequestParam
            String codigoCompany
    ) {
        companyService.createCompany(codigoCompany);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CompanyConstants.STATUS_201, CompanyConstants.MESSAGE_201));
    }

    /**
     * Obtiene los detalles de una tarjeta existente.
     * @param codigoCompany Número móvil asociado a la tarjeta
     * @return Detalles completos de la tarjeta
     */
    @GetMapping("/fetch")
    public ResponseEntity<CompanyDto> fetchCompanyDetails(
            @RequestParam
            String codigoCompany
    ) {
        return ResponseEntity.ok(companyService.fetchCompany(codigoCompany));
    }

    /**
     * Actualiza la configuración de una tarjeta existente.
     * @param companyDto Objeto con nuevos datos de la tarjeta
     * @return Estado de la operación de actualización
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCompanyDetails(@Valid @RequestBody CompanyDto companyDto) {
        return companyService.updateCompany(companyDto)
                ? ResponseEntity.ok(new ResponseDto(CompanyConstants.STATUS_200, CompanyConstants.MESSAGE_200))
                : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CompanyConstants.STATUS_417, CompanyConstants.MESSAGE_417_UPDATE));
    }

    /**
     * Elimina una tarjeta del sistema.
     * @param codigoCompany Número móvil asociado a la tarjeta
     * @return Estado de la operación de eliminación
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCompanyDetails(
            @RequestParam
            String codigoCompany
    ) {
        return companyService.deleteCompany(codigoCompany)
                ? ResponseEntity.ok(new ResponseDto(CompanyConstants.STATUS_200, CompanyConstants.MESSAGE_200))
                : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CompanyConstants.STATUS_417, CompanyConstants.MESSAGE_417_DELETE));
    }
}