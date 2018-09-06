package com.locrock.sell.logTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {


    @Test
    public void test_1(){
        String name = "locrock";

        log.debug("debug......");
        log.info("INFO: name =　{}....",name);
        log.error("error ........");
    }
}
