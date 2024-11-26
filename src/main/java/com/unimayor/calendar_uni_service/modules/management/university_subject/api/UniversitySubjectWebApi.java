package com.unimayor.calendar_uni_service.modules.management.university_subject.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.ResponseScheduleDomain;
import com.unimayor.calendar_uni_service.core.domain.RoomDomain;
import com.unimayor.calendar_uni_service.core.domain.UniversitySubjectDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.modules.management.schedule.controller.ControllerSchedule;
import com.unimayor.calendar_uni_service.modules.management.university_subject.usecase.UniversitySubjectController;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/university-subject", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UniversitySubjectWebApi {
    @Autowired
    private UniversitySubjectController universitySubjectController;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFindAll() {
        log.info("init get all Schedule");

        Map<String, Object> response = new HashMap<>();

        try {
            List<UniversitySubjectDomain> professorDomains = universitySubjectController.findAll();
            response.put(MessageConstant.RESPONSE_MESSAGE, professorDomains);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de retonar todos las materias");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en materias: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSubject(@RequestBody final UniversitySubjectDomain universitySubjectDomain) {
        log.info("init save room");

        Map<String, Object> response = new HashMap<>();

        try {
            universitySubjectController.createSubject(universitySubjectDomain);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de crear las materias");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado el materias: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
