package io.github.dantesun.petclinic.data.daos;

import io.github.dantesun.petclinic.data.ModelTestApp;
import io.github.dantesun.petclinic.data.entities.Pet;
import io.github.dantesun.petclinic.data.entities.PetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelTestApp.class)
public class PetDaoTest {
    @Autowired
    private PetDao petDao;
    @Autowired
    private PetTypeDao typeDao;

    @Test
    public void testFindOne() throws Exception {
        Pet pet = petDao.findOne(8);
        assertNotNull(pet);
        assertEquals("Max", pet.getName());
        assertEquals(2, pet.getVisits().size());
        assertEquals("cat", pet.getType().getName());

    }

    @Test
    public void testFindAll() throws Exception {
        List<Pet> all = petDao.findAll();
        assertEquals(13, all.size());
    }

    @Test
    public void testFindByType() throws Exception {
        PetType type = typeDao.findOne(1);
        assertEquals("cat", type.getName());
        List<Pet> pets = petDao.findByType(type);
        assertEquals(4, pets.size());
        for (Pet pet : pets) {
            assertEquals(pet.getType(), type);
        }
    }

    @Test
    public void testDatetime() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        System.out.println(TimeUnit.NANOSECONDS.toSeconds(Duration.between(LocalDateTime.now(), tomorrowMidnight).toNanos()));
        System.out.println(TimeUnit.NANOSECONDS.toSeconds(Duration.between(LocalDateTime.now(), todayMidnight).toNanos()));

    }
}