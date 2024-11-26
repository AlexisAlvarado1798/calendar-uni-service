package com.unimayor.calendar_uni_service.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class UniversitySubjectDomain {
    public String id;
    public String code;
    public Integer credit;
    public String description;
    public boolean active;
    public LocalDateTime creationDate;
}
