package io.github.bhuwanupadhyay.casestudy.fulfillment;

import io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.brokers.EventSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableBinding(EventSource.class)
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
public class FulfillmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfillmentApplication.class, args);
	}

}
