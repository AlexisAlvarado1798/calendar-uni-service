package com.unimayor.calendar_uni_service.core.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materias", schema = "calendar")
public class UniversitySubjectEntity {
    @Id
    @Column(unique = true, nullable = false, length = 100)
    private String id;

    @Column(name = "codigo", nullable = false, length = 100)
    private String code;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String description;

    @Column(name = "creditos", nullable = false, length = 100)
    private Integer credit;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "activo", nullable = false)
    private boolean active;

}
