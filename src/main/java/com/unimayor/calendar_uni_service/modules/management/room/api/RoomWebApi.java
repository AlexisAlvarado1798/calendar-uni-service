package com.unimayor.calendar_uni_service.modules.management.room.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.RoomDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.modules.management.room.usecase.RoomController;
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
@RequestMapping(value = "/room", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RoomWebApi {
    @Autowired
    RoomController roomController;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFindAll() {
        log.info("init get all salones");

        Map<String, Object> response = new HashMap<>();

        try {
            List<RoomDomain> professorDomains = roomController.findAll();
            response.put(MessageConstant.RESPONSE_MESSAGE, professorDomains);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de retonar todos los salones");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en salones: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody final RoomDomain roomDomain) {
        log.info("init save user");

        Map<String, Object> response = new HashMap<>();

        try {
            roomController.createRoom(roomDomain);
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
}
