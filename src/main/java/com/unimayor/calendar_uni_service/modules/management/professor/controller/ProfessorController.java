package com.unimayor.calendar_uni_service.modules.management.professor.controller;

import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.persistence.repository.ProfessorRepository;
import com.unimayor.calendar_uni_service.core.util.StringUtils;
import com.unimayor.calendar_uni_service.modules.management.professor.dataprovider.ProfessorDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@Log4j2
public class ProfessorController {
    @Autowired
    private ProfessorDataProvider professorDataProvider;
    private final StringUtils stringUtils = new StringUtils();

    public void createProfessor(ProfessorDomain professorDomain) {
        log.info("Validacion de dominio de creacion de profesor");
        validateProfessor(professorDomain);
        noExist(professorDomain);
        save(professorDomain);
    }

    private void validateProfessor(ProfessorDomain professorDomain) {
        log.info("validate parameters professor");
        try {
            stringUtils.isAllBlank(professorDomain.getEmail());
            stringUtils.isAllBlank(professorDomain.getName());
            stringUtils.isAllBlank(professorDomain.getLastname());
        } catch (BusinessException e) {
            log.error("el usuario o contraseña no puede ser nulo o vacio");
            throw new BusinessException("el usuario o contraseña no puede ser nulo o vacio");
        }
    }

    private void noExist(ProfessorDomain professorDomain) {
        log.info("Validate use is exist");

        if (professorDomain.getId() != null) {
            log.info("El usuario exite existe");
            throw new BusinessException("El usuario existe en la BD: " + professorDomain.getId());
        }
    }

    private void save(ProfessorDomain professorDomain) {
        professorDataProvider.save(professorDomain);
    }

    public List<ProfessorDomain> findAll() {
        return professorDataProvider.findAll();
    }

    public void update(ProfessorDomain professorDomain) {
        log.info("Validacion de actualizacion de profesor");
        validateProfessor(professorDomain);
        isExistById(professorDomain);
    }

    private void isExistById(ProfessorDomain professorDomain) {
        ProfessorDomain professorDomain1 = professorDataProvider.findProfessor(professorDomain.getId());

        if (Objects.nonNull(professorDomain1)) {
            professorDataProvider.update(professorDomain);
        } else {
            throw new BusinessException("No existe el usuario");
        }
    }

    public void delete(ProfessorDomain professorDomain) {
        ProfessorDomain professorDomain1 = professorDataProvider.findProfessor(professorDomain.getId());
        professorDataProvider.delete(professorDomain);
    }
}
