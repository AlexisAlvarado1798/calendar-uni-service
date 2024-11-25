package com.unimayor.calendar_uni_service.core.domain;

import com.unimayor.calendar_uni_service.core.persistence.entity.ProfessorEntity;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Setter
@Getter
public class ScheduleDomain {
    private String id;
    private String starDate;
    private String endDate;
    private String professorId;
    private String userId;
    private LocalDateTime creationDate;
}
