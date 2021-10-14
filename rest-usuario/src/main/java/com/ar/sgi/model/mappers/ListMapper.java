package com.ar.sgi.model.mappers;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ListMapper {
    private static final Logger LOGGER =Logger.getLogger(ListMapper.class.getName());

    /**
     * @param <T>
     * @param <V>
     * @param fromList list source objects
     * @param toClass target class objects
     * @return list of toClass mapped
     */
    public static <T, V> List<V> mapList(List<T> fromList, Class<V> toClass) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

        return fromList.stream().map(from -> {
            try {
                return mapper.map(from, toClass);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error Dozer Mapping Objects {}", e.getMessage());
            }
            return null;
        }).collect(Collectors.toList());
    }

}
