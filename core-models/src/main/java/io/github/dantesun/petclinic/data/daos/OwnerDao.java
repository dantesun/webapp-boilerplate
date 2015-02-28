package io.github.dantesun.petclinic.data.daos;

import io.github.dantesun.petclinic.data.entities.Owner;

import java.util.List;


/**
 * Created by dsun on 15/2/21.
 */

public interface OwnerDao {
    public Owner findOne(Integer id);

    public List<Owner> findAll();

    public int insertOne(Owner owner);

    public int update(Owner owner);

    public List<Owner> findByLastName(String lastName);

    public int deleteById(Integer id);

}
