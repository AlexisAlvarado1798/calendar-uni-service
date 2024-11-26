package com.unimayor.calendar_uni_service.core.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Setter
@Getter
public class ResponseScheduleDomain {
    private String id;
    private LocalDateTime starDate;
    private LocalDateTime endDate;
    private ProfessorDomain professorId;
    private UserDomain userId;
    private RoomDomain roomId;
    private UniversitySubjectDomain universitySubjectId;
    private LocalDateTime creationDate;
}
