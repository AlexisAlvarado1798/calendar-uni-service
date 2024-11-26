package com.unimayor.calendar_uni_service.core.persistence.entity;

import jakarta.persistence.*;
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
@Table(name = "clases", schema = "calendar")
public class ScheduleEntity {
    @Id
    @Column(unique = true, nullable = false, length = 100)
    private String id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime starDate;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesores_id", nullable = false)
    private ProfessorEntity professorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_id", nullable = false)
    private UserEntity userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salones_id", nullable = false)
    private RoomEntity roomId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materias_id", nullable = false)
    private UniversitySubjectEntity universitySubjectId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime creationDate;

}
