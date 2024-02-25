package com.rotikhao.storemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class BaseIntegrationTests {

    @LocalServerPort
    int port;
    protected UUID ownerID = UUID.fromString("640b238f-a85e-40ce-b754-9dd7607469bc");

    @Autowired
    protected TestRestTemplate restTemplate;

    protected String makePath(String uri) {
        return "http://localhost:" + port + uri;
    }
}
