package com.unimayor.calendar_uni_service.modules.management.schedule.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.ResponseScheduleDomain;
import com.unimayor.calendar_uni_service.core.domain.ScheduleDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.modules.management.schedule.controller.ControllerSchedule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class para web api de schedule
 *
 * @author Brayan Alexis Alvarado
 * @version 1.0
 * @since 2024-11-21
 */
@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/schedule", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ScheduleWebApi {
    @Autowired
    private ControllerSchedule controllerSchedule;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSchedule(@Valid @RequestBody final ScheduleDomain scheduleDomain) {
        log.info("Initializing create Schedule method with scheduleDomain: {}", scheduleDomain);
        Map<String, Object> response = new HashMap<>();
        try {
            controllerSchedule.createSchedule(scheduleDomain);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BusinessException e) {
            log.error("Error creating schedule: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error al momento de crear el horario" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error creating schedule: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFindAll() {
        log.info("init get all Schedule");

        Map<String, Object> response = new HashMap<>();

        try {
            List<ResponseScheduleDomain> professorDomains = controllerSchedule.findAll();
            response.put(MessageConstant.RESPONSE_MESSAGE, professorDomains);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de retonar todos los Schedule");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en Schedule: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
