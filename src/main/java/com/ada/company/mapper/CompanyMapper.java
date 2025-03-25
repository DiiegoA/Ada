package com.ada.company.mapper;

import com.ada.company.dto.CompanyDto;
import com.ada.company.entity.Company;

public class CompanyMapper {

    /**
     * Convierte un objeto de entidad (Accounts) a un DTO (AccountsDto) para exponer datos de forma controlada.
     *
     * @param company Objeto entidad obtenido de la base de datos
     * @return DTO con solo los campos necesarios para la comunicación externa
     */
    public static CompanyDto mapToCompanyDto(Company company) {
        return new CompanyDto(
                company.getIdCompany(),
                company.getCodigoCompany(),
                company.getNameCompany(),
                company.getDescriptionCompany()

        );
    }

    /**
     * Actualiza una entidad existente con los valores de un DTO (usado en operaciones de actualización).
     *
     * @param companyDto DTO con nuevos valores a aplicar
     * @param company Entidad existente que será modificada
     * @return Entidad actualizada (misma instancia recibida)
     */
    public static Company mapToCompany(CompanyDto companyDto, Company company) {
        company.setIdCompany(companyDto.idCompany());
        company.setCodigoCompany(companyDto.codigoCompany());
        company.setNameCompany(companyDto.nameCompany());
        company.setDescriptionCompany(companyDto.descriptionCompany());
        return company;
    }
}