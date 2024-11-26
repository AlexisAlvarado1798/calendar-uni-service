package com.unimayor.calendar_uni_service.modules.management.university_subject.dataprovider.jpa;

import com.unimayor.calendar_uni_service.core.domain.UniversitySubjectDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.persistence.entity.UniversitySubjectEntity;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import com.unimayor.calendar_uni_service.core.persistence.repository.UniversitySubjectRepository;
import com.unimayor.calendar_uni_service.modules.management.university_subject.dataprovider.UniversitySubjectDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UniversitySubjectImpl implements UniversitySubjectDataProvider {
    @Autowired
    UniversitySubjectRepository universitySubjectRepository;
    @Override
    public List<UniversitySubjectDomain> findAll() {
        Iterable<UniversitySubjectEntity> universitySubjectEntities = universitySubjectRepository.findAll();
        List<UniversitySubjectDomain> universitySubjectDomains = new ArrayList<>();

        for (UniversitySubjectEntity universitySubjectEntity: universitySubjectEntities) {
            UniversitySubjectDomain universitySubjectDomain = UniversitySubjectDomain.builder()
                    .id(universitySubjectEntity.getId())
                    .code(universitySubjectEntity.getCode())
                    .description(universitySubjectEntity.getDescription())
                    .credit(universitySubjectEntity.getCredit())
                    .active(universitySubjectEntity.isActive())
                    .creationDate(universitySubjectEntity.getCreationDate())
                    .build();
            universitySubjectDomains.add(universitySubjectDomain);
        }

        return universitySubjectDomains;
    }

    @Override
    public void save(UniversitySubjectDomain universitySubjectDomain) {
        UniversitySubjectEntity universitySubjectEntity = UniversitySubjectEntity.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .code(universitySubjectDomain.getCode())
                .description(universitySubjectDomain.getDescription())
                .credit(universitySubjectDomain.getCredit())
                .active(universitySubjectDomain.isActive())
                .creationDate(LocalDateTime.now())
                .build();

        universitySubjectRepository.save(universitySubjectEntity);

    }
}
