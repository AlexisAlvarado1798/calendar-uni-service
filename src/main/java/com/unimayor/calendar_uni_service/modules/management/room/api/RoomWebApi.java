package com.unimayor.calendar_uni_service.modules.management.room.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.RoomDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/room", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RoomWebApi {
    @GetMapping
    public ResponseEntity<Map<String, Object>> getFindAll() {
        log.info("init get all professor");

        Map<String, Object> response = new HashMap<>();

        try {
            List<RoomDomain> professorDomains = professorController.findAll();
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
}
