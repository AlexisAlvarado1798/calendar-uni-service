package com.unimayor.calendar_uni_service.modules.management.user.dataprovider;

import com.unimayor.calendar_uni_service.core.domain.UserDomain;

import java.util.List;

public interface UserDataProvider {
    /**
     * Metodo para guardar un usuario
     *
     * @param userDomain
     */
    void save(UserDomain userDomain);

    /**
     * Metodo para retornar todos los usaurios
     *
     */
    List<UserDomain> getFindAll();

    UserDomain findUser(String username);

    void delete(UserDomain userDomain);

    void update(UserDomain user);

    UserDomain isExistById(UserDomain userDomain);
}
