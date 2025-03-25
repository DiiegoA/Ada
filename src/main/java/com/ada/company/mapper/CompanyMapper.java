package com.ada.company.mapper;

import com.ada.company.dto.CompanyDto;
import com.ada.company.dto.CompanyDtoGet;
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
                company.getCodigoCompany(),
                company.getNameCompany(),
                company.getDescriptionCompany()

        );
    }

    /**
     * Convierte un objeto de entidad (Accounts) a un DTO (AccountsDto) para exponer datos de forma controlada.
     *
     * @param company Objeto entidad obtenido de la base de datos
     * @return DTO con solo los campos necesarios para la comunicación externa
     */
    public static CompanyDtoGet mapToCompanyDtoGet(Company company) {
        return new CompanyDtoGet(
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
        company.setCodigoCompany(companyDto.codigoCompany());
        company.setNameCompany(companyDto.nameCompany());
        company.setDescriptionCompany(companyDto.descriptionCompany());
        return company;
    }

    /**
     * Actualiza una entidad existente con los valores de un DTO (usado en operaciones de actualización).
     *
     * @param companyDtoGet DTO con nuevos valores a aplicar
     * @param company Entidad existente que será modificada
     * @return Entidad actualizada (misma instancia recibida)
     */
    public static Company mapToCompanyGet(CompanyDtoGet companyDtoGet, Company company) {
        company.setIdCompany(companyDtoGet.idCompany());
        company.setCodigoCompany(companyDtoGet.codigoCompany());
        company.setNameCompany(companyDtoGet.nameCompany());
        company.setDescriptionCompany(companyDtoGet.descriptionCompany());
        return company;
    }
}