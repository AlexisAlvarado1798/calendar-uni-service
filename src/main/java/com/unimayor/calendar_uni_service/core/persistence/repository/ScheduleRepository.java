package com.unimayor.calendar_uni_service.core.persistence.repository;

import com.unimayor.calendar_uni_service.core.persistence.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository
        extends CrudRepository<ScheduleEntity, String>, JpaSpecificationExecutor<ScheduleEntity> {
}
