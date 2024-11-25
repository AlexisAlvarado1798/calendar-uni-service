package com.unimayor.calendar_uni_service.modules.management.professor.dataprovider;

import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;

import java.util.List;

public interface ProfessorDataProvider {
    ProfessorDomain findProfessor(String id);

    void save(ProfessorDomain professorDomain);

    List<ProfessorDomain>  findAll();

    void update(ProfessorDomain professorDomain);

    void delete(ProfessorDomain professorDomain);
}
