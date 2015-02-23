package io.github.dantesun.petclinic.data.velocity;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.Alias;
import org.mybatis.scripting.velocity.Driver;

/**
 * Created by dsun on 15/2/22.
 */
@Alias("velocity")
public class VelocityDriver implements LanguageDriver {
    private Driver driverImpl = new Driver();

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return driverImpl.createParameterHandler(mappedStatement, parameterObject, boundSql);
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType) {
        return createSqlSource(configuration, script.getNode().getTextContent(), parameterType);
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        if (parameterType == null) {
            parameterType = Object.class;
        }
        return new VelocitySqlSource(configuration, script, parameterType);
    }
}
