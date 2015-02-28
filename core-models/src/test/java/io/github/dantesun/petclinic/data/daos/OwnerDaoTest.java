package io.github.dantesun.petclinic.data.daos;

import io.github.dantesun.petclinic.data.ModelTestApp;
import io.github.dantesun.petclinic.data.entities.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelTestApp.class)
public class OwnerDaoTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private OwnerDao ownerDao;

    @Test
    public void testFindOne() throws Exception {
        Owner owner = ownerDao.findOne(1);
        assertNotNull(owner);
        assertEquals("George", owner.getFirstName());
        assertEquals("Franklin", owner.getLastName());
        assertEquals("110 W. Liberty St.", owner.getAddress());
        assertEquals("Madison", owner.getCity());
        assertEquals("6085551023", owner.getTelephone());
    }


    @Test
    public void testFindAll() throws Exception {

        List<Owner> owners = ownerDao.findAll();
        assertEquals(10, owners.size());
    }

    @Test(expected = CleanupException.class)
    @Transactional(rollbackFor = CleanupException.class)
    public void testInsertOne() throws Exception, CleanupException {
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        assertEquals(1, ownerDao.insertOne(owner));
        assertNotNull(owner.getId());
        assertNotNull(ownerDao.findOne(owner.getId()));
        throw new CleanupException();
    }

    @Test
    public void testUpdate() throws Exception {
        Owner owner = ownerDao.findOne(1);
        assertNotEquals("John", owner.getFirstName());
        owner.setFirstName("John");
        ownerDao.update(owner);
        owner = ownerDao.findOne(owner.getId());
        assertEquals("John", owner.getFirstName());

    }

    @Test
    public void testFindByLastName() throws Exception {
        List<Owner> owners = ownerDao.findByLastName("Franklin");
        assertEquals(1, owners.size());
        Owner owner = owners.get(0);
        assertEquals("George", owner.getFirstName());
        assertEquals("Franklin", owner.getLastName());
        assertEquals("110 W. Liberty St.", owner.getAddress());
        assertEquals("Madison", owner.getCity());
        assertEquals("6085551023", owner.getTelephone());
    }

    private class CleanupException extends Throwable {
    }
}