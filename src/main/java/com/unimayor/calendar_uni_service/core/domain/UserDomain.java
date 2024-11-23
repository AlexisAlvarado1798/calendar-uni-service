package com.unimayor.calendar_uni_service.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDomain {
    public String id;
    public String username;
    public String password;
    public boolean active;
    public LocalDateTime creationDate;
}
