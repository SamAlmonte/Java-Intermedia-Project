package com.amazon.ata.deliveringonourpromise.Client;

import com.amazon.ata.deliveringonourpromise.types.Promise;

public interface Client {
    public Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId);
}
