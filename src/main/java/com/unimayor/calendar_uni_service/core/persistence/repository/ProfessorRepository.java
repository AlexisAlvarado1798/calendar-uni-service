package com.unimayor.calendar_uni_service.core.persistence.repository;

import com.unimayor.calendar_uni_service.core.persistence.entity.ProfessorEntity;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository
        extends CrudRepository<ProfessorEntity, String>, JpaSpecificationExecutor<ProfessorEntity>
{
}
