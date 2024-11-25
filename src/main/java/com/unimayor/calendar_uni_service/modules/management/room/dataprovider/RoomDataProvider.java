package com.unimayor.calendar_uni_service.modules.management.room.dataprovider;

import com.unimayor.calendar_uni_service.core.domain.RoomDomain;

import java.util.List;

public interface RoomDataProvider {
    List<RoomDomain> findAll();

    void save(RoomDomain roomDomain);
}
