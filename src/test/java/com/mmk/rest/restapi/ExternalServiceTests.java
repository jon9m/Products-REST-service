package com.mmk.rest.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@Import(MyTestContainersConfig.class)
@SpringBootTest
class ExternalServiceTests {

    @Value("${external.service.port:-1}")
    int nginxPort;

    @Test
    void printRandomPortProperty(){
        System.out.println(nginxPort);
    }


}
