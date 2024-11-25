package com.unimayor.calendar_uni_service.modules.management.schedule.controller;

import com.unimayor.calendar_uni_service.core.domain.ResponseScheduleDomain;
import com.unimayor.calendar_uni_service.core.domain.ScheduleDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.util.StringUtils;
import com.unimayor.calendar_uni_service.modules.management.schedule.dataprovider.ScheduleDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2
public class ControllerSchedule {
    @Autowired
    private ScheduleDataProvider scheduleDataProvider;

    private final StringUtils stringUtils = new StringUtils();

    public void createSchedule(ScheduleDomain scheduleDomain) {
        log.info("Validacion de dominio de creacion de profesor");
        validateSchedule(scheduleDomain);
        //noExist(scheduleDomain);
        scheduleDataProvider.save(scheduleDomain);
    }

    private void validateSchedule(ScheduleDomain scheduleDomain) {
        log.info("validate parameters professor");
        try {
            stringUtils.isNull(scheduleDomain.getStarDate());
            stringUtils.isNull(scheduleDomain.getEndDate());
            stringUtils.isAllBlank(scheduleDomain.getUserId());
            stringUtils.isAllBlank(scheduleDomain.getProfessorId());
        } catch (BusinessException e) {
            log.error("el usuario o contraseña no puede ser nulo o vacio");
            throw new BusinessException("el usuario o contraseña no puede ser nulo o vacio");
        }
    }

    public List<ResponseScheduleDomain> findAll() {
        return scheduleDataProvider.findAll();
    }
}
