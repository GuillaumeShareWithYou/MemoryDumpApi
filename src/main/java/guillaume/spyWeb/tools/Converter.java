package guillaume.spyWeb.tools;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Mon magnifique Converter :D
 */
public class Converter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <A, B> B map(A a, Class<B> clazz) {
        return modelMapper.map(a, clazz);
    }

    public static <A, B> Page<B> map(Page<A> entities, Class<B> clazz) {
        var list = map(entities.getContent(), clazz);
        return new PageImpl<>(list);
    }

    public static <A, B> Set<B> map(Set<A> set, Class<B> clazz) {
        var res = new HashSet<B>();
        for (var e : set) res.add(map(e, clazz));
        return res;
    }

    public static <A, B> List<B> map(Iterable<A> iterable, Class<B> clazz) {
        var list = new ArrayList<B>();
        for (var e : iterable) list.add(map(e, clazz));
        return list;
    }

}
