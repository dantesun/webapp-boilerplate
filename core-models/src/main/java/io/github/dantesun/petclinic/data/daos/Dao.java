package io.github.dantesun.petclinic.data.daos;

import java.util.List;

/**
 * Created by dsun on 15/3/1.
 */
@SuppressWarnings("MybatisMapperInXmlInspection")
public interface Dao<T> {

    T findOne(Integer id);

    List<T> findAll();

    int insertOne(T o);

    int update(T o);

    int deleteById(Integer id);
}
