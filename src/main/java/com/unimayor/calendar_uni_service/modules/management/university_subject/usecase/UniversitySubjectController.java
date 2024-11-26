package com.unimayor.calendar_uni_service.modules.management.university_subject.usecase;

import com.unimayor.calendar_uni_service.core.domain.UniversitySubjectDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.util.StringUtils;
import com.unimayor.calendar_uni_service.modules.management.university_subject.dataprovider.UniversitySubjectDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Log4j2
@Controller
public class UniversitySubjectController {
    @Autowired
    UniversitySubjectDataProvider universitySubjectDataProvider;
    private final StringUtils stringUtils = new StringUtils();

    public List<UniversitySubjectDomain> findAll() {
        return universitySubjectDataProvider.findAll();
    }

    public void createSubject(UniversitySubjectDomain universitySubjectDomain) {
        log.info("Validacion de dominio de creacion de materias");
        validateSubject(universitySubjectDomain);
        universitySubjectDataProvider.save(universitySubjectDomain);
    }

    private void validateSubject(UniversitySubjectDomain universitySubjectDomain) {
        log.info("validate parameters de materias");
        try {
            stringUtils.isAllBlank(universitySubjectDomain.getDescription());
            stringUtils.isAllBlank(universitySubjectDomain.getCredit().toString());
            stringUtils.isAllBlank(universitySubjectDomain.getCode());
        } catch (BusinessException e) {
            log.error("el usuario o contraseña no puede ser nulo o vacio");
            throw new BusinessException("el usuario o contraseña no puede ser nulo o vacio");
        }
    }
}
