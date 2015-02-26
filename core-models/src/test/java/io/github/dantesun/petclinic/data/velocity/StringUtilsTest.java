package io.github.dantesun.petclinic.data.velocity;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    private Configuration config;
    private StringUtils stringUtils;

    @Before
    public void setUp() throws IOException {
        XMLConfigBuilder builder = new XMLConfigBuilder(ResourceUtils.getURL("classpath:/mybatis-config.xml").openStream());
        this.config = builder.parse();
        this.stringUtils = new StringUtils(this.config);
    }

    @Test
    public void testSql2parameter() throws Exception {
        final String columns = "      \n first_name, \n last_name, \ncity";
        final String expected = "@{firstName},@{lastName},@{city}";
        String parameter = stringUtils.columns2parameters(columns);
        assertEquals(parameter, expected);

    }

    @Test
    public void testDeleteWhitespace() throws Exception {
        final String columns = "      \n first_name, \n last_name, \ncity";
        assertEquals("first_name,last_name,city", stringUtils.deleteWhitespace(columns));

    }

    @Test
    public void testRemoveUnderscoreAndCamelize() throws Exception {
        final String columns = stringUtils.deleteWhitespace("      \n first_name, \n last_name, \ncity");
        assertEquals("firstName,lastName,city", stringUtils.removeUnderscoreAndCamelize(columns));
    }
}