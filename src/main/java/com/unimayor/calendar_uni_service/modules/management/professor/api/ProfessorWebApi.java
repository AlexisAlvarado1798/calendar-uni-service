package com.unimayor.calendar_uni_service.modules.management.professor.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.modules.management.professor.controller.ProfessorController;
import com.unimayor.calendar_uni_service.modules.management.user.controller.UserController;
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
@RequestMapping(value = "/professor", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfessorWebApi {
    @Autowired
    private ProfessorController professorController;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody final ProfessorDomain professorDomain) {
        log.info("init save user");

        Map<String, Object> response = new HashMap<>();

        try {
            professorController.createProfessor(professorDomain);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de crear el profesor");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en profesor: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFindAll() {
        log.info("init get all professor");

        Map<String, Object> response = new HashMap<>();

        try {
            List<ProfessorDomain> professorDomains = professorController.findAll();
            response.put(MessageConstant.RESPONSE_MESSAGE, professorDomains);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de retonar todos los professor");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en professor: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody final ProfessorDomain professorDomain) {
        log.info("init update Professor");

        Map<String, Object> response = new HashMap<>();

        try {
            professorController.update(professorDomain);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de crear el Professor");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en Professor: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> delete(@RequestBody final ProfessorDomain professorDomain) {
        log.info("init delete Professor");

        Map<String, Object> response = new HashMap<>();

        try {
            professorController.delete(professorDomain);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de eliminar el Professor");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en Professor: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
