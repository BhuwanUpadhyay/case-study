package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.CancelShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.PrepareShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ShipOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.events.OrderShipped;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.*;
import io.github.bhuwanupadhyay.core.Problem;
import io.github.bhuwanupadhyay.ddd.AggregateRoot;

import java.util.Objects;

public class Shipping extends AggregateRoot<ShippingId> {

	private final OrderId orderId;

	private final ShippingAddress shippingAddress;

	private ShippingStatus status;

	public Shipping(ShippingId shippingId, OrderId orderId, ShippingAddress shippingAddress) {
		super(shippingId);
		this.orderId = orderId;
		this.shippingAddress = shippingAddress;
	}

	public Shipping(ShippingId shippingId, PrepareShippingCommand command, Addresses addresses) {
		super(shippingId);
		this.shippingAddress = addresses.getByCustomerId(new CustomerId(command.orderId()));
		this.orderId = new OrderId(command.orderId());
		this.status = ShippingStatus.PREPARED;
	}

	public void on(ShipOrderCommand command) {
		this.status = ShippingStatus.SHIPPED;
		this.registerEvent(new OrderShipped(this.getId(), this.orderId, this.shippingAddress));
	}

	public void on(ModifyOrderCommand command) {
		this.status = ShippingStatus.PREPARED;
	}

	public void on(CancelShippingCommand command) {
		this.status = ShippingStatus.CANCELLED;
	}

	public OrderId getOrderId() {
		return orderId;
	}

	public ShippingStatus getStatus() {
		return status;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public boolean isNotShipped() {
		return !Objects.equals(this.status, ShippingStatus.SHIPPED);
	}

	public Shipping withStatus(ShippingStatus status) {
		Problem.notNull(status);
		this.status = status;
		return this;
	}

}
