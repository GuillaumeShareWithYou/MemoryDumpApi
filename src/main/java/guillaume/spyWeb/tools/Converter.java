package guillaume.spyWeb.tools;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Converter {


    private static final ModelMapper modelMapper = new ModelMapper();

    public static <E, D> E mapToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }


    public static <E, D> Collection<E> mapAllToEntity(Collection<D> dtos, Class<E> entityClass) {
        final List<E> entities = new ArrayList<>();
        for (final D dto : dtos) {
            entities.add(modelMapper.map(dto, entityClass));
        }
        return entities;
    }

    public static <E, D> Collection<E> mapAllToEntity(List<D> dtos, Class<E> entityClass) {
        final List<E> entities = new ArrayList<>();
        for (final D dto : dtos) {
            entities.add(modelMapper.map(dto, entityClass));
        }
        return entities;
    }

    public static <E, D> D mapToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <E, D> Collection<D> mapAllToDto(Collection<E> entities, Class<D> dtoClass) {

        List<D> list = new ArrayList<>();
        for (E entity : entities) {
            list.add(modelMapper.map(entities, dtoClass));
        }
        return list;
    }

    public static <E, D> Collection<D> mapAllToDto(Iterable<E> entities, Class<D> dtoClass) {

        List<D> list = new ArrayList<>();
        for (E entity : entities) {
            list.add(modelMapper.map(entities, dtoClass));
        }
        return list;
    }

}
