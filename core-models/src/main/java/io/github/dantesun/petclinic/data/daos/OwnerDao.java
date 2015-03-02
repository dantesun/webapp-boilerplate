package io.github.dantesun.petclinic.data.daos;

import io.github.dantesun.petclinic.data.entities.Owner;

import java.util.List;


/**
 * Created by dsun on 15/2/21.
 */

public interface OwnerDao extends Dao<Owner> {

    public List<Owner> findByLastName(String lastName);

}
