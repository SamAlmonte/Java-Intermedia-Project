package com.amazon.ata.deliveringonourpromise.orderfulfillmentservice;

import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.deliverypromiseservice.service.DeliveryPromise;
import com.amazon.ata.orderfulfillmentservice.OrderFulfillmentService;
import com.amazon.ata.orderfulfillmentservice.OrderPromise;
import com.amazon.ata.deliveringonourpromise.Client.Client;

//implements Client client has Order getDeliveryPromiseByOrderItemId method
public class OrderFulfillmentServiceClient implements Client {
    //private OrderManipulationAuthority omaService;
    private OrderFulfillmentService ofaService;


    /**
     * Create new client that calls OFS with the given service object.
     *
     * @param service The OrderFulfillmentService  that this client will call
     */
    public OrderFulfillmentServiceClient(OrderFulfillmentService ofaService) {
        this.ofaService = ofaService;
    }


    /**
     * Fetches the Order promise for the given order ID.
     *
     * @param orderId String representing the order ID to fetch the order for.
     * @return the Order Promise for the given order ID if found; or null, otherwise
     */
    public Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId) {
        if (customerOrderItemId == null)
            return null;
        OrderPromise deliveryPromise = ofaService.getOrderPromise(customerOrderItemId);

        if (null == deliveryPromise) {
            return null;
        }

        return Promise.builder()
                .withPromiseLatestArrivalDate(deliveryPromise.getPromiseLatestArrivalDate())
                .withCustomerOrderItemId(deliveryPromise.getCustomerOrderItemId())
                .withPromiseLatestShipDate(deliveryPromise.getPromiseLatestShipDate())
                .withPromiseEffectiveDate(deliveryPromise.getPromiseEffectiveDate())
                .withIsActive(deliveryPromise.isActive())
                .withPromiseProvidedBy(deliveryPromise.getPromiseProvidedBy())
                .withAsin(deliveryPromise.getAsin())
                .build();
    }
}