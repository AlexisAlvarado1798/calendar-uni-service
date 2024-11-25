package com.unimayor.calendar_uni_service.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RoomDomain {
    public String id;
    public Integer capacity;
    public String description;
    public boolean active;
    public LocalDateTime creationDate;
}
