package com.ada.company.dto;

import jakarta.validation.constraints.NotEmpty;

public record CompanyDto(

        @NotEmpty(message = "Company code can not be a null or empty")
        String codigoCompany,

        @NotEmpty(message = "Company name can not be a null or empty")
        String nameCompany,

        @NotEmpty(message = "Company description can not be a null or empty")
        String descriptionCompany
) {


}
