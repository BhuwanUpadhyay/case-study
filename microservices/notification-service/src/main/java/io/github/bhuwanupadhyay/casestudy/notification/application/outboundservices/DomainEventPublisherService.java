package io.github.bhuwanupadhyay.casestudy.notification.application.outboundservices;

import io.github.bhuwanupadhyay.casestudy.notification.infrastructure.brokers.EventSource;
import io.github.bhuwanupadhyay.ddd.DomainEvent;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DomainEventPublisherService implements DomainEventPublisher {

	private final EventSource eventSource;

	public DomainEventPublisherService(EventSource eventSource) {
		this.eventSource = eventSource;
	}

	public void publish(DomainEvent domainEvent) {
		Map<String, Object> h = new HashMap<>();
		h.put("X-Service", "Notification");
		h.put("X-DomainEvent", domainEvent.getClass().getName());
		MessageHeaders headers = new MessageHeaders(h);
	}

}
