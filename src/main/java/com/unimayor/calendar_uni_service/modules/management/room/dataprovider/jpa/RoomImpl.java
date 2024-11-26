package com.unimayor.calendar_uni_service.modules.management.room.dataprovider.jpa;

import com.unimayor.calendar_uni_service.core.domain.RoomDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.persistence.entity.RoomEntity;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import com.unimayor.calendar_uni_service.core.persistence.repository.RoomRepository;
import com.unimayor.calendar_uni_service.modules.management.room.dataprovider.RoomDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoomImpl implements RoomDataProvider {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<RoomDomain> findAll() {
        Iterable<RoomEntity> roomEntities = roomRepository.findAll();
        List<RoomDomain> roomDomains = new ArrayList<>();

        for (RoomEntity roomEntity: roomEntities) {
            RoomDomain roomDomain = RoomDomain.builder()
                    .id(roomEntity.getId())
                    .code(roomEntity.getCode())
                    .capacity(roomEntity.getCapacity())
                    .description(roomEntity.getDescription())
                    .active(roomEntity.isActive())
                    .creationDate(roomEntity.getCreationDate())
                    .build();
            roomDomains.add(roomDomain);
        }

        return roomDomains;
    }

    @Override
    public void save(RoomDomain roomDomain) {
        RoomEntity roomEntity = RoomEntity.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .code(roomDomain.getCode())
                .description(roomDomain.getDescription())
                .capacity(roomDomain.getCapacity())
                .active(roomDomain.isActive())
                .creationDate(LocalDateTime.now())
                .build();
        roomRepository.save(roomEntity);
    }
}
