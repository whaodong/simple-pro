package pers.hd.simplepro.server.model.support.base;

import org.springframework.lang.Nullable;
import pers.hd.simplepro.core.util.BeanUtils;
import pers.hd.simplepro.core.util.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * @author WangHaoDong
 */
public interface InputConverter<D> {

    /**
     * Convert to domain.(shallow)
     *
     * @return new domain with same value(not null)
     */
    @SuppressWarnings("unchecked")
    default D convertTo() {
        // Get parameterized type
        ParameterizedType currentType = parameterizedType();

        // Assert not equal
        Objects.requireNonNull(currentType,
            "Cannot fetch actual type because parameterized type is null");

        Class<D> domainClass = (Class<D>) currentType.getActualTypeArguments()[0];

        return BeanUtils.transformFrom(this, domainClass);
    }

    /**
     * Update a domain by dto.(shallow)
     *
     * @param domain updated domain
     */
    default void update(D domain) {
        BeanUtils.updateProperties(this, domain);
    }

    /**
     * Get parameterized type.
     *
     * @return parameterized type or null
     */
    @Nullable
    default ParameterizedType parameterizedType() {
        return ReflectionUtils.getParameterizedType(InputConverter.class, this.getClass());
    }
}

