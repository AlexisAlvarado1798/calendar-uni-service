package com.unimayor.calendar_uni_service.modules.management.schedule.dataprovider;

import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.ResponseScheduleDomain;
import com.unimayor.calendar_uni_service.core.domain.ScheduleDomain;

import java.util.List;

public interface ScheduleDataProvider {
    void save(ScheduleDomain scheduleDomain);

    List<ResponseScheduleDomain> findAll();

    void delete(String id);

    ScheduleDomain isExistById(ScheduleDomain scheduleDomain);

    void update(ScheduleDomain scheduleDomain1);
}
