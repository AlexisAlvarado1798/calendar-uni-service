package com.unimayor.calendar_uni_service.modules.management.professor.dataprovider.jpa;

import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.persistence.entity.ProfessorEntity;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import com.unimayor.calendar_uni_service.core.persistence.repository.ProfessorRepository;
import com.unimayor.calendar_uni_service.core.persistence.repository.UserRepository;
import com.unimayor.calendar_uni_service.modules.management.professor.dataprovider.ProfessorDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class ProfessorDataProviderImpl implements ProfessorDataProvider {
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public ProfessorDomain findProfessor(String id) {
        Optional<ProfessorEntity> professorEntity
                = professorRepository.findById(id);

        return professorEntity.map(entity -> ProfessorDomain.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .lastname(entity.getLastName())
                .email(entity.getEmail())
                .active(entity.isActive())
                .build()).orElse(null);
    }

    @Override
    public void save(ProfessorDomain professorDomain) {
        ProfessorEntity professorEntity = ProfessorEntity.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .code(professorDomain.getCode())
                .name(professorDomain.getName())
                .lastName(professorDomain.getLastname())
                .email(professorDomain.getEmail())
                .active(professorDomain.isActive())
                .creationDate(LocalDateTime.now())
                .build();

        professorRepository.save(professorEntity);
    }

    @Override
    public List<ProfessorDomain> findAll() {
        Iterable<ProfessorEntity> professorEntities = professorRepository.findAll();
        List<ProfessorDomain> professorDomains = new ArrayList<>();

        for (ProfessorEntity professorEntity: professorEntities) {
            ProfessorDomain professorDomain = ProfessorDomain.builder()
                    .id(professorEntity.getId())
                    .code(professorEntity.getCode())
                    .name(professorEntity.getName())
                    .lastname(professorEntity.getLastName())
                    .email(professorEntity.getEmail())
                    .active(professorEntity.isActive())
                    .creationDate(professorEntity.getCreationDate())
                    .build();
            professorDomains.add(professorDomain);
        }

        return professorDomains;

    }

    @Override
    public void update(ProfessorDomain professorDomain) {
        Optional<ProfessorEntity> professorEntityOptional
                = professorRepository.findById(professorDomain.getId());

        if (professorEntityOptional.isPresent()) {
            ProfessorEntity professorEntity = professorEntityOptional.get();

            professorEntity.setName(professorDomain.getName());
            professorEntity.setLastName(professorDomain.getLastname());
            professorEntity.setLastName(professorDomain.getLastname());
            professorEntity.setActive(professorDomain.isActive());

            professorRepository.save(professorEntity);
        }

    }

    @Override
    public void delete(ProfessorDomain professorDomain) {
        ProfessorEntity professorEntity = ProfessorEntity.builder()
                .id(professorDomain.getId())
                .code(professorDomain.getCode())
                .name(professorDomain.getName())
                .lastName(professorDomain.getLastname())
                .email(professorDomain.getEmail())
                .active(professorDomain.isActive())
                .creationDate(LocalDateTime.now())
                .build();
        professorRepository.delete(professorEntity);
    }
}
