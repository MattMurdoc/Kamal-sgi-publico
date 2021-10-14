package com.ar.sgi.model.mappers;

import com.ar.sgi.model.Paginator;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PaginatorMapper {
    private static final Logger LOGGER =Logger.getLogger(PaginatorMapper.class.getName());

    /**
     * @param <T>
     * @param <V>
     * @param fromPaginator list source objects
     * @param toClass target class objects
     * @return list of toClass mapped
     */
    public static <T, V> Paginator<V> mapPaginator(Paginator<T> fromPaginator, Class<V> toClass) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

        return new Paginator<>(fromPaginator.getEntity().stream().map(from -> {
            try {
                return mapper.map(from, toClass);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error Dozer Mapping Objects {}", e.getMessage());
            }
            return null;
        }).collect(Collectors.toList()), fromPaginator.getCantidadRegistros());
    }

}
