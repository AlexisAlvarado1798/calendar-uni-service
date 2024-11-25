package com.unimayor.calendar_uni_service.modules.management.room.usecase;

import com.unimayor.calendar_uni_service.core.domain.RoomDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.util.StringUtils;
import com.unimayor.calendar_uni_service.modules.management.room.dataprovider.RoomDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Log4j2
public class RoomController {
    @Autowired RoomDataProvider roomDataProvider;
    private final StringUtils stringUtils = new StringUtils();

    public List<RoomDomain> findAll() {
        return roomDataProvider.findAll();
    }

    public void createRoom(RoomDomain roomDomain) {
        log.info("Validacion de dominio de creacion de usuario");
        validateRoom(roomDomain);
        roomDataProvider.save(roomDomain);
    }

    private void validateRoom(RoomDomain roomDomain) {
        log.info("validate parameters authentication");
        try {
            stringUtils.isAllBlank(roomDomain.getDescription());
            stringUtils.isAllBlank(roomDomain.getCapacity().toString());
        } catch (BusinessException e) {
            log.error("el usuario o contraseña no puede ser nulo o vacio");
            throw new BusinessException("el usuario o contraseña no puede ser nulo o vacio");
        }
    }
}
