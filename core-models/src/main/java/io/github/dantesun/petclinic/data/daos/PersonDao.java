package io.github.dantesun.petclinic.data.daos;

import io.github.dantesun.petclinic.data.entities.Person;

import java.util.List;


/**
 * Created by dsun on 15/2/21.
 */
@SuppressWarnings("MybatisMapperMethodInspection")
public interface PersonDao {
    public Person findOne(Integer id);

    public List<Person> findAll();

    public int insertOne(Person person);
}
