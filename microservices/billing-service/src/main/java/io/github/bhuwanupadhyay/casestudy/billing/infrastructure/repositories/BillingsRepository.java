package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.aggregates.Billing;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.model.valueobjects.*;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories.jpa.BillingEntity;
import io.github.bhuwanupadhyay.casestudy.billing.infrastructure.repositories.jpa.BillingEntityRepository;
import io.github.bhuwanupadhyay.ddd.DomainEntityNotFound;
import io.github.bhuwanupadhyay.ddd.DomainEventPublisher;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BillingsRepository extends Billings {

	private final BillingEntityRepository repository;

	protected BillingsRepository(DomainEventPublisher publisher, BillingEntityRepository repository) {
		super(publisher);
		this.repository = repository;
	}

	@Override
	public Optional<Billing> findOne(BillingId billingId) {
		return repository.findByBillingId(billingId.value()).map(this::toBilling);
	}

	@Override
	protected void persist(Billing entity) {
		Optional<BillingEntity> findOne = repository.findByBillingId(entity.getId().value());
		BillingEntity db;
		if (findOne.isPresent()) {
			db = findOne.get();
		}
		else {
			entity.setCreatedAt(LocalDateTime.now());
			db = new BillingEntity();
			db.setOrderId(entity.getOrderId().value());
			db.setBillingId(entity.getId().value());
			db.setCreatedAt(entity.getCreatedAt());
		}
		db.setStatus(entity.getStatus().name());
		db.setBillAmount(entity.getBillAmount().amount());
		db.setRefundReason(Optional.ofNullable(entity.getRefundReason()).map(RefundReason::value).orElse(null));
		repository.save(db);
	}

	@Override
	public BillingId nextId() {
		return new BillingId(UUID.randomUUID().toString());
	}

	@Override
	public Billing findByOrderId(OrderId orderId) {
		return repository.findByOrderId(orderId.value()).map(this::toBilling)
				.orElseThrow(() -> new DomainEntityNotFound("[By OrderId]", orderId));
	}

	private Billing toBilling(BillingEntity e) {
		Billing rs = new Billing(new BillingId(e.getBillingId()), new OrderId(e.getOrderId()));
		rs.setCreatedAt(e.getCreatedAt());
		return rs.withAmount(new BillAmount(e.getBillAmount())).withStatus(BillingStatus.valueOf(e.getStatus()))
				.withRefundReason(new RefundReason(e.getRefundReason()));
	}

}
