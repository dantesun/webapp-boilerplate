package io.github.dantesun.petclinic.data;

import io.github.dantesun.petclinic.data.daos.DaoMarker;
import io.github.dantesun.petclinic.data.entities.BaseEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    DataSourceTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);
        String typeAliasesPackage = BaseEntity.class.getPackage().getName();
        factoryBean.setTypeAliasesPackage(typeAliasesPackage);

        //Use velocity as the scripting language for mapper
        SqlSessionFactory factory = factoryBean.getObject();
//        LanguageDriverRegistry languageRegistry = factory.getConfiguration().getLanguageRegistry();
//        languageRegistry.setDefaultDriverClass(Driver.class);
        return factory;
    }
}
