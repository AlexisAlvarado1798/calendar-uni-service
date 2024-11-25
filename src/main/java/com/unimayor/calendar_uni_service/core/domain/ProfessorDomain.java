package com.unimayor.calendar_uni_service.core.domain;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
public class ProfessorDomain {
    public String id;
    public String code;
    public String name;
    public String lastname;
    public String email;
    public boolean active;
    public LocalDateTime creationDate;
}
