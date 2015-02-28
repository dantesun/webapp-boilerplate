package io.github.dantesun.petclinic.data;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelTestApp.class)
public class PersistenceConfigTest {

    @Autowired
    private SqlSessionFactory sessionFactory;

    @Test
    @Transactional
    public void testConfiguration() throws Exception {
        assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.getConnection();
        sqlSession.close();
    }

}