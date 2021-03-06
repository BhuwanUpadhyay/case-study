package io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.ModifyOrderCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.commands.PrepareShippingCommand;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.aggregates.Shipping;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories.Shippings;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.valueobjects.OrderId;
import io.github.bhuwanupadhyay.core.CommandService;

import java.util.Optional;

public class ModifyShippingCommandService implements CommandService<ModifyOrderCommand> {

	private final Shippings shippings;

	private final PrepareShippingCommandService prepareShippingCommandService;

	public ModifyShippingCommandService(Shippings shippings,
			PrepareShippingCommandService prepareShippingCommandService) {
		this.shippings = shippings;
		this.prepareShippingCommandService = prepareShippingCommandService;
	}

	@Override
	public void execute(ModifyOrderCommand command) {
		Optional<Shipping> byOrderId = shippings.findByOrderIdAndStatusNotShipped(new OrderId(command.orderId()));
		if (byOrderId.isPresent()) {
			Shipping shipping = byOrderId.get();
			shipping.on(command);
			shippings.save(shipping);
		}
		else {
			this.prepareShippingCommandService
					.execute(new PrepareShippingCommand(command.orderId(), command.orderItems()));
		}
	}

}
