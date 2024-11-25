package com.unimayor.calendar_uni_service.core.util;

import com.unimayor.calendar_uni_service.core.exeption.BusinessException;

/**
 * Class for validate String
 *
 *
 *
 */
public class StringUtils {

    public void isAllBlank(String value) {
        if (value == null) {
            throw new BusinessException("No puede ser nulo");
        }

        if (value.isBlank()) {
            throw new BusinessException(value + "esta vacio o tiene espacios");
        }
    }

    public void isNull(Object object) {
        if (object == null) {
            throw new BusinessException("No puede ser nulo");
        }

    }


}
