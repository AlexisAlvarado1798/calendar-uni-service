package com.unimayor.calendar_uni_service.modules.management.user.controller;

import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.util.StringUtils;
import com.unimayor.calendar_uni_service.modules.management.user.dataprovider.UserDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@Log4j2
public class UserController {
    @Autowired private UserDataProvider userDataProvider;
    private final StringUtils stringUtils = new StringUtils();

    public void createUser(UserDomain userDomain) {
        log.info("Validacion de dominio de creacion de usuario");
        validateUser(userDomain);
        noExist(userDomain);
        save(userDomain);
    }

    private void validateUser(UserDomain userDomain) {
        log.info("validate parameters authentication");
        try {
            stringUtils.isAllBlank(userDomain.getUsername());
            stringUtils.isAllBlank(userDomain.getPassword());
        } catch (BusinessException e) {
            log.error("el usuario o contraseña no puede ser nulo o vacio");
            throw new BusinessException("el usuario o contraseña no puede ser nulo o vacio");
        }
    }

    private void noExist(UserDomain userDomain) {
        log.info("Validate use is exist");
        UserDomain user = userDataProvider.findUser(userDomain.getUsername());
        if (Objects.nonNull(user)) {
            log.info("el usuario: {}, existe", userDomain.getUsername());
            throw new BusinessException("El usuario existe: " + userDomain.getUsername());
        }
        log.info("El usuario no existe");
    }

    private void save(UserDomain userDomain) {
        userDataProvider.save(userDomain);

    }

    public List<UserDomain> findAll() {
        return userDataProvider.getFindAll();
    }

    public void delete(String username) {
        UserDomain userDomain = isExist(username);
        userDataProvider.delete(userDomain);
    }

    private UserDomain isExist(String username) {
        log.info("Validate user is exist");
        UserDomain userDomain = userDataProvider.findUser(username);
        if (Objects.isNull(userDomain)) {
            throw new BusinessException("El usuario no existe en la BD: " + username);
        }
        log.info("is exist");
        return userDomain;
    }

    public UserDomain findByUsername(String username) {
        return isExist(username);
    }

    public void update(UserDomain userDomain) {
        log.info("Validacion de actualizacion de usuario");
        validateUser(userDomain);
        isExistById(userDomain);
        userDataProvider.update(userDomain);

    }

    private void isExistById(UserDomain userDomain) {
        UserDomain userDomain1 = userDataProvider.isExistById(userDomain);

        if (Objects.nonNull(userDomain1)) {
            userDataProvider.update(userDomain);
        } else {
            throw new BusinessException("No existe el usuario");
        }
    }
}
