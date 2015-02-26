package io.github.dantesun.petclinic.data.velocity;

import org.apache.ibatis.session.Configuration;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * String functions used in velocity script of MyBatis mapper
 */
@SuppressWarnings("UnusedDeclaration")
public class StringUtils {
    private final Configuration configuration;

    public StringUtils(Configuration configuration) {
        this.configuration = configuration;
    }

    public String deleteWhitespace(String str) {
        return org.apache.commons.lang.StringUtils.deleteWhitespace(str);
    }

    private String camelize(String word, Function<Character, Character> camelizer) {
        return new StringBuilder().append(camelizer.apply(word.charAt(0))).append(word.substring(1)).toString();
    }

    public String removeUnderscoreAndCamelize(String str) {
        if (!configuration.isMapUnderscoreToCamelCase()) {
            return str;
        }
        String[] strings = str.split("_");
        return IntStream.range(0, strings.length)
                        .mapToObj(i -> camelize(strings[i], i == 0 ? Character::toLowerCase : Character::toUpperCase))
                        .collect(Collectors.joining());
    }

    public String columns2parameters(String str) {
        String[] strings = deleteWhitespace(str).split(",");
        IntFunction<String> canonicalize = i ->
                removeUnderscoreAndCamelize(strings[i]);
        return IntStream.range(0, strings.length)
                        .mapToObj(canonicalize)
                        .map(s -> String.format("%s%s%s", "@{", s, "}"))
                        .collect(Collectors.joining(","));
    }
}
