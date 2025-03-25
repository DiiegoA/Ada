package com.ada.company.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad JPA que representa una tarjeta bancaria en el sistema.
 * Hereda campos de auditoría de BaseEntity.
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    /**
     * Identificador único de la compañia (clave primaria).
     * @apiNote Generado automáticamente por la base de datos
     * @example 12345
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long idCompany;

    @Column(name = "codigo_company", unique = true)
    private String codigoCompany;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "description_company")
    private String descriptionCompany;
}
