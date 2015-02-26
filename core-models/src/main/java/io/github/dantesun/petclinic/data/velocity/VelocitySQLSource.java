package io.github.dantesun.petclinic.data.velocity;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.mybatis.scripting.velocity.ParameterMappingCollector;
import org.mybatis.scripting.velocity.ParameterMappingSourceParser;
import org.mybatis.scripting.velocity.VelocityFacade;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This is a re-implementation for {@link org.mybatis.scripting.velocity.SQLScriptSource}. Originally,
 *
 * @{link org.mybatis.scripting.velocity.SQLScriptSource} handling the parameter mappings first, then renders the
 * velocity script. It greatly limits the flexibility of generating parameter mappings using velocity script.
 * This class tries to reverse the process. It firstly renders the velocity script, then handling the parameter mappings.
 * </p>
 */
public class VelocitySqlSource implements SqlSource {
    private static final String PARAMETER_OBJECT_KEY = "_parameter";
    private static final String DATABASE_ID_KEY = "_databaseId";
    private static final String MAPPING_COLLECTOR_KEY = "_pmc";
    private static final String VARIABLES_KEY = "_vars";

    private final Configuration configuration;
    private final String script;
    private final Class<?> parameterType;

    private static int templateIndex = 0;
    private final StringUtils stringUtils;


    public VelocitySqlSource(Configuration configuration, String script, Class<?> parameterType) {
        this.configuration = configuration;
        this.script = script;
        this.parameterType = parameterType;
        stringUtils = new StringUtils(configuration);

    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        Map<String, Object> context = new HashMap<>();
        context.put(DATABASE_ID_KEY, configuration.getDatabaseId());
        context.put(PARAMETER_OBJECT_KEY, parameterObject);
        context.put(VARIABLES_KEY, configuration.getVariables());
        context.put("strings", stringUtils);
        context.put("utils", new ParameterObjectUtils(parameterObject, stringUtils));
        //The first round of velocity script rendering
        Object compiledScript = VelocityFacade.compile(script, "velocity-template-" + (++templateIndex));
        String appliedScript = VelocityFacade.apply(compiledScript, context);

        //Handing the parameter mappings
        ParameterMappingSourceParser mappingParser = new ParameterMappingSourceParser(configuration, appliedScript, parameterType);
        ParameterMapping[] parameterMappingSources = mappingParser.getParameterMappingSources();
        final ParameterMappingCollector pmc = new ParameterMappingCollector(parameterMappingSources, context, configuration);
        context.put(MAPPING_COLLECTOR_KEY, pmc);

        //Have to render the sql as velocity script again because "@_pmc.g" expressions are inserted by ParameterMappingSourceParser
        String sql = mappingParser.getSql();
        Object compiledSql = VelocityFacade.compile(sql, "velocity-template-" + (++templateIndex));
        sql = VelocityFacade.apply(compiledSql, context);
        BoundSql boundSql = new BoundSql(configuration, sql, pmc.getParameterMappings(), parameterObject);
        for (Map.Entry<String, Object> entry : context.entrySet()) {
            boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
        }

        return boundSql;
    }

}
