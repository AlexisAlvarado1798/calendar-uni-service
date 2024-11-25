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
@Table(name = "profesores", schema = "calendar")
public class ProfessorEntity {
    @Id
    @Column(unique = true, nullable = false, length = 100)
    private String id;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "correo_electronico", nullable = false, length = 100)
    private String email;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "apellido", nullable = false, length = 100)
    private String lastName;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime
    creationDate;

    @Column(name = "activo", nullable = false)
    private boolean active;
}
