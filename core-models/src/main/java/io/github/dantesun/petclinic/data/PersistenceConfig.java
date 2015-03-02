package io.github.dantesun.petclinic.data;

import io.github.dantesun.petclinic.data.daos.DaoMarker;
import io.github.dantesun.petclinic.data.entities.BaseEntity;
import io.github.dantesun.petclinic.data.velocity.VelocityDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by dsun on 15/2/21.
 */
@Configuration
@MapperScan(basePackageClasses = DaoMarker.class)
@EnableTransactionManagement
public class PersistenceConfig {

    DataSourceTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);
        String typeAliasesPackage = BaseEntity.class.getPackage().getName();
        factoryBean.setTypeAliasesPackage(typeAliasesPackage);
        factoryBean.setTypeHandlersPackage(typeAliasesPackage);
        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        //Use velocity as the scripting language for mapper
        factoryBean.setTypeAliases(new Class<?>[]{VelocityDriver.class});
        return factoryBean.getObject();
    }
}
