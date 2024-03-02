package com.rotikhao.storemanagement;

import com.rotikhao.storemanagement.config.DevDbConfig;
import org.awaitility.classpath.ClassPathResolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
public class BaseIntegrationTests {

    @LocalServerPort
    int port;
    @Value("${server.servlet.context-path}")
    String contextPath;
    protected UUID ownerID = UUID.fromString("640b238f-a85e-40ce-b754-9dd7607469bc");

    protected TestRestTemplate restTemplate = new TestRestTemplate(new RestTemplateBuilder()
            .defaultHeader("Content-Type","application/json")
            .messageConverters(new MappingJackson2HttpMessageConverter()));

    protected String makePath(String uri) {
        return "http://localhost:" + port + contextPath + uri;
    }
}
