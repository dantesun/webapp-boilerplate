package io.github.dantesun.petclinic.data;

import io.github.dantesun.petclinic.data.daos.OwnerDao;
import io.github.dantesun.petclinic.data.entities.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelTestApp.class)
public class PersistenceConfigTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private OwnerDao ownerDao;

    @Test
    public void testDS() throws SQLException {
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        assertEquals(1, ownerDao.insertOne(owner));
        assertNotNull(owner.getId());
        assertNotNull(ownerDao.findOne(owner.getId()));
        owner.setFirstName("Michael");
        owner.setLastName(null);
        assertEquals(1, ownerDao.update(owner));
        owner = ownerDao.findOne(owner.getId());
        assertNotNull(owner);
        assertEquals("Michael", owner.getFirstName());
        assertEquals("Doe", owner.getLastName());

        //Insert the second record
        owner.setId(null);
        assertEquals(1, ownerDao.insertOne(owner));
        assertNotNull(owner.getId());
        assertNotNull(ownerDao.findOne(owner.getId()));

        List<Owner> persons = ownerDao.findAll();
        assertEquals(12, persons.size());


    }
}