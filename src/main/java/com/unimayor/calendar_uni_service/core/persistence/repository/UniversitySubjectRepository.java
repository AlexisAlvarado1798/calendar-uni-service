package com.unimayor.calendar_uni_service.core.persistence.repository;

import com.unimayor.calendar_uni_service.core.persistence.entity.UniversitySubjectEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UniversitySubjectRepository
        extends CrudRepository<UniversitySubjectEntity, String>, JpaSpecificationExecutor<UniversitySubjectEntity> {

}
