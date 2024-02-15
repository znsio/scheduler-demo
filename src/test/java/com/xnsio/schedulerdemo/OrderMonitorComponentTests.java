package com.xnsio.schedulerdemo;

import com.xnsio.schedulerdemo.config.TestDatabaseConfig;
import com.xnsio.schedulerdemo.jobs.OrderMonitor;
import in.specmatic.kafka.mock.KafkaMock;
import in.specmatic.kafka.mock.model.VerificationResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import in.specmatic.kafka.mock.model.Expectation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class OrderMonitorComponentTests {

    private static KafkaMock kafkaMock;
    @Autowired
    private OrderMonitor orderMonitor;

    @BeforeAll
    public static void setUp() {
        System.setProperty("spring.profiles.active", "component-tests");

        kafkaMock = KafkaMock.startInMemoryBroker("localhost", 9092, "./Kafka", new ArrayList<>());
        kafkaMock.setExpectations(Arrays.asList(new Expectation("process-order", 1, new HashMap<>(), "")));
    }

    @Test
    public void shouldProcessOrders() {
        orderMonitor.processOrders();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        kafkaMock.awaitMessages(1, 1000);
        VerificationResult verificationResult = kafkaMock.verifyExpectations(true);
        assertThat(verificationResult.getSuccess()).isTrue();
        kafkaMock.close();
        Thread.sleep(5000);
    }

}
