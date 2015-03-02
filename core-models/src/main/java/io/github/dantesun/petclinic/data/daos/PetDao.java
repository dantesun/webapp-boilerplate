package io.github.dantesun.petclinic.data.daos;

import io.github.dantesun.petclinic.data.entities.Pet;
import io.github.dantesun.petclinic.data.entities.PetType;

import java.util.List;

/**
 * Created by dsun on 15/3/1.
 */
public interface PetDao extends Dao<Pet> {
    List<Pet> findByType(PetType type);
}
