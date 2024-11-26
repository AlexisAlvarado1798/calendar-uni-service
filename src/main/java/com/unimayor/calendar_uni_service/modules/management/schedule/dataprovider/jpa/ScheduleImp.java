package com.unimayor.calendar_uni_service.modules.management.schedule.dataprovider.jpa;

import com.unimayor.calendar_uni_service.core.domain.*;
import com.unimayor.calendar_uni_service.core.persistence.entity.*;
import com.unimayor.calendar_uni_service.core.persistence.repository.ProfessorRepository;
import com.unimayor.calendar_uni_service.core.persistence.repository.ScheduleRepository;
import com.unimayor.calendar_uni_service.modules.management.schedule.dataprovider.ScheduleDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(scheduleDomain.getRoomId());

        UniversitySubjectEntity universitySubjectEntity = new UniversitySubjectEntity();
        universitySubjectEntity.setId(scheduleDomain.getUniversitySubjectId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

        LocalDateTime startDate = LocalDateTime.parse(scheduleDomain.getStarDate(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(scheduleDomain.getEndDate(), formatter);

        ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .starDate(startDate)
                .endDate(endDate)
                .professorId(professorEntity)
                .userId(userEntity)
                .roomId(roomEntity)
                .universitySubjectId(universitySubjectEntity)
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
                    .code(scheduleEntity.getProfessorId().getCode())
                    .id(scheduleEntity.getProfessorId().getId())
                    .name(scheduleEntity.getProfessorId().getName())
                    .lastname(scheduleEntity.getProfessorId().getLastName())
                    .email(scheduleEntity.getProfessorId().getEmail())
                    .active(scheduleEntity.getProfessorId().isActive())
                    .creationDate(scheduleEntity.getProfessorId().getCreationDate())
                    .build();

            UserDomain userDomain = UserDomain.builder()
                    .id(scheduleEntity.getUserId().getId())
                    .username(scheduleEntity.getUserId().getUsername())
                    .password(scheduleEntity.getUserId().getPassword())
                    .active(scheduleEntity.getUserId().isActive())
                    .creationDate(scheduleEntity.getUserId().getCreationDate())
                    .build();

            RoomDomain roomDomain = RoomDomain.builder()
                    .id(scheduleEntity.getRoomId().getId())
                    .code(scheduleEntity.getRoomId().getCode())
                    .description(scheduleEntity.getRoomId().getDescription())
                    .capacity(scheduleEntity.getRoomId().getCapacity())
                    .active(scheduleEntity.getRoomId().isActive())
                    .creationDate(LocalDateTime.now())
                    .build();

            UniversitySubjectDomain universitySubjectDomain = UniversitySubjectDomain.builder()
                    .id(scheduleEntity.getUniversitySubjectId().getId())
                    .code(scheduleEntity.getUniversitySubjectId().getCode())
                    .description(scheduleEntity.getUniversitySubjectId().getDescription())
                    .credit(scheduleEntity.getUniversitySubjectId().getCredit())
                    .active(scheduleEntity.getUniversitySubjectId().isActive())
                    .creationDate(LocalDateTime.now())
                    .build();

            ResponseScheduleDomain scheduleDomain = ResponseScheduleDomain.builder()
                    .id(scheduleEntity.getId())
                    .starDate(scheduleEntity.getStarDate())
                    .endDate(scheduleEntity.getEndDate())
                    .professorId(professorDomain)
                    .userId(userDomain)
                    .roomId(roomDomain)
                    .universitySubjectId(universitySubjectDomain)
                    .creationDate(scheduleEntity.getCreationDate())
                    .build();

            professorDomains.add(scheduleDomain);
        }
        return professorDomains;
    }

    @Override
    public void delete(String id) {
        Optional<ScheduleEntity> scheduleEntity = scheduleRepository.findById(id);

        scheduleEntity.ifPresent(entity -> scheduleRepository.delete(entity));
    }

    @Override
    public ScheduleDomain isExistById(ScheduleDomain scheduleDomain) {
        Optional<ScheduleEntity> scheduleEntity = scheduleRepository.findById(scheduleDomain.getId());

        return scheduleEntity.map(entity -> ScheduleDomain.builder()
                .id(entity.getId())
                .starDate(entity.getStarDate().toString())
                .endDate(entity.getEndDate().toString())
                .professorId(entity.getProfessorId().getId())
                .userId(entity.getUserId().getId())
                .roomId(entity.getRoomId().getId())
                .universitySubjectId(entity.getUniversitySubjectId().getId())
                .creationDate(entity.getCreationDate())
                .build()).orElse(null);


    }

    @Override
    public void update(ScheduleDomain scheduleDomain1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime startDate = LocalDateTime.parse(scheduleDomain1.getStarDate(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(scheduleDomain1.getEndDate(), formatter);

        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setId(scheduleDomain1.getProfessorId());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(scheduleDomain1.getUserId());

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(scheduleDomain1.getRoomId());

        UniversitySubjectEntity universitySubjectEntity = new UniversitySubjectEntity();
        universitySubjectEntity.setId(scheduleDomain1.getUniversitySubjectId());

        Optional<ScheduleEntity> userEntityOptional
                = scheduleRepository.findById(scheduleDomain1.getId());
        if (userEntityOptional.isPresent()) {
            ScheduleEntity schedule = userEntityOptional.get();
            schedule.setStarDate(startDate);
            schedule.setEndDate(endDate);
            schedule.setProfessorId(professorEntity);
            schedule.setRoomId(roomEntity);
            schedule.setUniversitySubjectId(universitySubjectEntity);
            scheduleRepository.save(schedule);
        }
    }
}
