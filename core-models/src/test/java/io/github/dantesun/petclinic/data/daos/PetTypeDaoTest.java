package io.github.dantesun.petclinic.data.daos;


import io.github.dantesun.petclinic.data.ModelTestApp;
import io.github.dantesun.petclinic.data.entities.PetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelTestApp.class)
public class PetTypeDaoTest {
    @Autowired
    private PetTypeDao dao;

    @Test
    public void testFindOne() {
        PetType type = dao.findOne(1);
        assertNotNull(type);
        assertEquals("cat", type.getName());

    }
}