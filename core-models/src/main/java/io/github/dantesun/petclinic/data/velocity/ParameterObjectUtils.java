package io.github.dantesun.petclinic.data.velocity;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * Created by dsun on 15/2/26.
 */
public class ParameterObjectUtils {
    private final Object root;
    private final StringUtils strings;
    private static final Logger logger = LoggerFactory.getLogger(ParameterObjectUtils.class);

    public ParameterObjectUtils(Object root, StringUtils strings) {
        this.root = root;
        this.strings = strings;
    }

    public Object[] mapNotNull(String sql_columns) {
        String[] strings = this.strings.deleteWhitespace(sql_columns).split(",");
        Map<String, String> mappings = new HashMap<>();
        IntFunction<String> canonicalize = i -> {
            String column = strings[i];
            String property = this.strings.removeUnderscoreAndCamelize(column);
            mappings.put(column, property);
            return property;
        };
        Object[] properties = IntStream.range(0, strings.length)
                                       .mapToObj(canonicalize)
                                       .filter(property -> {
                                           try {
                                               return Ognl.getValue(property, root) != null;
                                           } catch (OgnlException e) {
                                               logger.error("Can't get value {}.{}", root, property);
                                           }
                                           return false;
                                       }).toArray();
        return mappings.entrySet().stream()
                       .filter(entry -> Arrays.stream(properties).anyMatch(entry.getValue()::equals)).toArray();
    }

}
