package com.ada.company.service.impl;

import com.ada.company.constants.CompanyConstants;
import com.ada.company.dto.CompanyDto;
import com.ada.company.dto.CompanyDtoGet;
import com.ada.company.entity.Company;
import com.ada.company.exception.CompanyAlreadyExistsException;
import com.ada.company.exception.ResourceNotFoundException;
import com.ada.company.mapper.CompanyMapper;
import com.ada.company.repository.CompanyRepository;
import com.ada.company.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * Implementación concreta del servicio de gestión de tarjetas bancarias.
 * Proporciona operaciones CRUD para tarjetas y manejo de reglas de negocio.
 */
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private CompanyRepository companyRepository;

    /**
     * Crea una nueva compañia asociada a un codigo.
     * @param codigoCompany codigo del cliente (validado previamente)
     * @throws CompanyAlreadyExistsException Si ya existe una compañia con el mismo codigo
     * @implNote Genera número de tarjeta único y establece límites iniciales
     */
    @Override
    public void createCompany(String codigoCompany) {
        Optional<Company> optionalCompany = companyRepository.findByCodigoCompany(codigoCompany);
        if(optionalCompany.isPresent()) {
            throw new CompanyAlreadyExistsException("Company already registered with given code " + codigoCompany);
        }
        companyRepository.save(createNewCompany(codigoCompany));
    }

    /**
     * Genera una nueva entidad Cards con valores iniciales.
     * @param codigoCompany codigo asociado a la compañia
     * @return Entidad Cards configurada con valores por defecto
     * @implSpec Utiliza Random para generación de números (mejorable para producción)
     */
    private Company createNewCompany(String codigoCompany) {
        return Company.builder()
                .codigoCompany(codigoCompany)
                .nameCompany(CompanyConstants.ADA)
                .descriptionCompany(CompanyConstants.DESCRIPTION)
                .build();
    }

    /**
     * Recupera los detalles de una tarjeta existente.
     * @param codigoCompany codigo asociado a la compañia
     * @return DTO con información completa de la tarjeta
     * @throws ResourceNotFoundException Si no se encuentra la compañia
     */
    @Override
    public CompanyDtoGet fetchCompany(String codigoCompany) {
        Company company = companyRepository.findByCodigoCompany(codigoCompany)
                .orElseThrow(() -> new ResourceNotFoundException("Compañia", "codigo", codigoCompany));

        return CompanyMapper.mapToCompanyDtoGet(company);
    }

    /**
     * Actualiza los datos de una compañia existente.
     * @param companyDto DTO con nuevos datos de la compañia
     * @return true si la actualización fue exitosa
     * @throws ResourceNotFoundException Si la compañia no existe
     */
    @Override
    public boolean updateCompany(CompanyDto companyDto) {
        Company company = companyRepository.findByCodigoCompany(companyDto.codigoCompany())
                .orElseThrow(() -> new ResourceNotFoundException("Compañia", "Codigo", companyDto.codigoCompany()));
        CompanyMapper.mapToCompany(companyDto, company);
        companyRepository.save(company);
        return true;
    }

    /**
     * Elimina una compañia del sistema.
     * @param codigoCompany codigo asociado a la tarjeta
     * @return true si la eliminación fue exitosa
     * @throws ResourceNotFoundException Si la compañia no existe
     */
    @Override
    public boolean deleteCompany(String codigoCompany) {
        Company company = companyRepository.findByCodigoCompany(codigoCompany)
                .orElseThrow(() -> new ResourceNotFoundException("Compañia", "codigo", codigoCompany));
        companyRepository.deleteById(company.getIdCompany());
        return true;
    }
}