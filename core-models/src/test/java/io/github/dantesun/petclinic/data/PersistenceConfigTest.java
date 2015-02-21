package io.github.dantesun.petclinic.data;

import io.github.dantesun.petclinic.data.daos.PersonDao;
import io.github.dantesun.petclinic.data.entities.Person;
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
    private PersonDao personDao;

    @Test
    public void testDS() throws SQLException {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        assertEquals(1, personDao.insertOne(person));
        assertNotNull(person.getId());
        assertNotNull(personDao.findOne(person.getId()));

        //Insert the second record
        person.setId(null);
        assertEquals(1, personDao.insertOne(person));
        assertNotNull(person.getId());
        assertNotNull(personDao.findOne(person.getId()));

        List<Person> persons = personDao.findAll();
        assertEquals(2, persons.size());
    }
}