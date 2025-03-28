package com.ada.company.repository;

import com.ada.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCodigoCompany(String codigoCompany);
}
