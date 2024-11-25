package com.unimayor.calendar_uni_service.modules.management.schedule.dataprovider.jpa;

import com.unimayor.calendar_uni_service.core.domain.ProfessorDomain;
import com.unimayor.calendar_uni_service.core.domain.ResponseScheduleDomain;
import com.unimayor.calendar_uni_service.core.domain.ScheduleDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.persistence.entity.ProfessorEntity;
import com.unimayor.calendar_uni_service.core.persistence.entity.ScheduleEntity;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import com.unimayor.calendar_uni_service.core.persistence.repository.ProfessorRepository;
import com.unimayor.calendar_uni_service.core.persistence.repository.ScheduleRepository;
import com.unimayor.calendar_uni_service.modules.management.schedule.dataprovider.ScheduleDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleImp implements ScheduleDataProvider {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(ScheduleDomain scheduleDomain) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setId(scheduleDomain.getProfessorId());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(scheduleDomain.getUserId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

        LocalDateTime startDate = LocalDateTime.parse(scheduleDomain.getStarDate(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(scheduleDomain.getEndDate(), formatter);

        ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .starDate(startDate)
                .endDate(endDate)
                .professorEntity(professorEntity)
                .userEntity(UserEntity.builder()
                        .id(scheduleDomain.getUserId())
                        .build())
                .creationDate(LocalDateTime.now())
                .build();

        scheduleRepository.save(scheduleEntity);
    }

    @Override
    public List<ResponseScheduleDomain> findAll() {
        Iterable<ScheduleEntity> scheduleEntities = scheduleRepository.findAll();
        List<ResponseScheduleDomain> professorDomains = new ArrayList<>();

        for (ScheduleEntity scheduleEntity: scheduleEntities) {
            ProfessorDomain professorDomain = ProfessorDomain.builder()
                    .id(scheduleEntity.getProfessorEntity().getId())
                    .code(scheduleEntity.getProfessorEntity().getCode())
                    .name(scheduleEntity.getProfessorEntity().getName())
                    .lastname(scheduleEntity.getProfessorEntity().getLastName())
                    .email(scheduleEntity.getProfessorEntity().getEmail())
                    .active(scheduleEntity.getProfessorEntity().isActive())
                    .creationDate(scheduleEntity.getProfessorEntity().getCreationDate())
                    .build();

            UserDomain userDomain = UserDomain.builder()
                    .id(scheduleEntity.getUserEntity().getId())
                    .username(scheduleEntity.getUserEntity().getUsername())
                    .password(scheduleEntity.getUserEntity().getPassword())
                    .active(scheduleEntity.getUserEntity().isActive())
                    .creationDate(scheduleEntity.getUserEntity().getCreationDate())
                    .build();

            ResponseScheduleDomain scheduleDomain = ResponseScheduleDomain.builder()
                    .id(scheduleEntity.getId())
                    .starDate(scheduleEntity.getStarDate())
                    .endDate(scheduleEntity.getEndDate())
                    .professorId(professorDomain)
                    .userId(userDomain)
                    .creationDate(scheduleEntity.getCreationDate())
                    .build();

            professorDomains.add(scheduleDomain);
        }
        return professorDomains;
    }
}
