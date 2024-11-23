package com.unimayor.calendar_uni_service.modules.management.user.dataprovider.jpa;

import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import com.unimayor.calendar_uni_service.core.persistence.repository.UserRepository;
import com.unimayor.calendar_uni_service.modules.management.user.dataprovider.UserDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@Service
public class UserImpl implements UserDataProvider {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDomain findUser(String username) {
        Optional<UserEntity> userEntity
                = userRepository.findUsername(username);

        return userEntity.map(entity -> UserDomain.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .active(entity.isActive())
                .build()).orElse(null);
    }

    @Override
    public void delete(UserDomain userDomain) {
        userRepository.deleteByUsername(userDomain.getUsername());
    }

    @Override
    public void update(UserDomain user) {
        Optional<UserEntity> userEntityOptional
                = userRepository.findById(user.getId());

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(user.getPassword());
            userEntity.setActive(user.isActive());

            userRepository.save(userEntity);
        }

    }

    @Override
    public UserDomain isExistById(UserDomain userDomain) {
        Optional<UserEntity> userEntity = userRepository.findById(userDomain.getId());
        return userEntity.map(entity -> UserDomain.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .active(entity.isActive())
                .build()).orElse(null);
    }

    @Override
    public void save(UserDomain userDomain) {
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .username(userDomain.getUsername())
                .password(userDomain.getPassword())
                .active(userDomain.isActive())
                .creationDate(LocalDateTime.now())
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDomain> getFindAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDomain> userDomains = new ArrayList<>();

        for (UserEntity userEntity: userEntities) {
            UserDomain userDomain = UserDomain.builder()
                    .id(userEntity.getId())
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .active(userEntity.isActive())
                    .creationDate(userEntity.getCreationDate())
                    .build();
            userDomains.add(userDomain);
        }

        return userDomains;
    }
}
