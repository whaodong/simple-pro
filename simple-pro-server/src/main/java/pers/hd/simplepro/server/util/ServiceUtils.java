package pers.hd.simplepro.server.util;

import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wanghaodong
 */
public class ServiceUtils {
    private ServiceUtils() {
    }

    /**
     * 获取要设置的id。
     *
     * @param datas 数据集
     * @param mappingFunction 集合中的ID
     * @param <I> ID类型
     * @param <T> 数据类型
     * @return 返回ID集合
     */
    @NonNull
    public static <I, T> Set<I> fetchProperty(final Collection<T> datas,
                                              Function<T, I> mappingFunction) {
        return CollectionUtils.isEmpty(datas)
                ? Collections.emptySet() :
                datas.stream().map(mappingFunction).collect(Collectors.toSet());
    }
}
