package org.eventuate.saga.orderservice.command;

import io.eventuate.tram.commands.common.Command;
import lombok.NoArgsConstructor;
import org.eventuate.saga.orderservice.saga.OrderSagaData;

@NoArgsConstructor
public class RejectOrderSagaCommand implements Command {

    private OrderSagaData orderSagaData;

    public RejectOrderSagaCommand(OrderSagaData orderSagaData) {
        this.orderSagaData = orderSagaData;
    }

    public OrderSagaData getOrderSagaData() {
        return orderSagaData;
    }
}
