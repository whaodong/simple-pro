package pers.hd.simplepro.server.pojo.dto.base;

import org.springframework.lang.NonNull;
import pers.hd.simplepro.core.util.BeanUtils;

/**
 * @author WangHaoDong
 */
public interface OutputConverter <DtoT extends OutputConverter<DtoT, D>, D>{
    /**
     * Convert from domain.(shallow)
     *
     * @param domain domain data
     * @return converted dto data
     */
    @SuppressWarnings("unchecked")
    @NonNull
    default <T extends DtoT> T convertFrom(@NonNull D domain) {

        BeanUtils.updateProperties(domain, this);

        return (T) this;
    }
}
