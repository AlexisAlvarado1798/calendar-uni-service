package com.unimayor.calendar_uni_service.modules.management.university_subject.dataprovider;

import com.unimayor.calendar_uni_service.core.domain.UniversitySubjectDomain;

import java.util.List;

public interface UniversitySubjectDataProvider {
    List<UniversitySubjectDomain> findAll();

    void save(UniversitySubjectDomain universitySubjectDomain);
}
