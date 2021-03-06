package com.amazon.ata.deliveringonourpromise.activity;

//import com.amazon.ata.deliveringonourpromise.comparators.PromiseAsinComparator;
import com.amazon.ata.deliveringonourpromise.comparators.PromiseAsinComparator;
import com.amazon.ata.deliveringonourpromise.dao.ReadOnlyDao;
import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.deliveringonourpromise.types.OrderItem;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.deliveringonourpromise.types.PromiseHistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Activity class, handling the GetPromiseHistoryByOrderId API.
 */
public class GetPromiseHistoryByOrderIdActivity {
    private ReadOnlyDao<String, Order> orderDao;
    private ReadOnlyDao<String, List<Promise>> promiseDao;

    /**
     * Instantiates an activity for handling the API, accepting the relevant DAOs to
     * perform its work.
     *
     * @param orderDao data access object fo retrieving Orders by order ID
     * @param promiseDao data access object for retrieving Promises by order item ID
     */
    public GetPromiseHistoryByOrderIdActivity(ReadOnlyDao<String, Order> orderDao,
                                              ReadOnlyDao<String, List<Promise>> promiseDao) {
        this.orderDao = orderDao;
        this.promiseDao = promiseDao;
    }

    /**
     * Returns the PromiseHistory for the given order ID, if the order exists. If the order does
     * not exist a PromiseHistory with a null order and no promises will be returned.
     * @param orderId The order ID to fetch PromiseHistory for
     * @return PromiseHistory containing the order and promise history for that order
     */
    public PromiseHistory getPromiseHistoryByOrderId(String orderId) {
        if (null == orderId) {
            throw new IllegalArgumentException("order ID cannot be null");
        }

        Order order = orderDao.get(orderId);
        if(order == null)
            return new PromiseHistory(null); //return a promise history with null value
        List<OrderItem> customerOrderItems = order.getCustomerOrderItemList();
        //OrderItem customerOrderItem = null;
        if (customerOrderItems != null && !customerOrderItems.isEmpty()) {
            //customerOrderItem = customerOrderItems.get(0);
        }
        else
            return null;
        //loop through all orderItems
        //get all the promises
        //all promises to promise history, return promise history

        PromiseHistory history = new PromiseHistory(order);
        List<Promise> promises = promiseDao.get(customerOrderItems.get(0).getCustomerOrderItemId());
        List<Promise> sortedList = new ArrayList<>();
        for(int i = 0; i < customerOrderItems.size(); i++){
            promises = promiseDao.get(customerOrderItems.get(i).getCustomerOrderItemId());
            for (Promise promise : promises) {
                promise.setConfidence(customerOrderItems.get(i).isConfidenceTracked(), customerOrderItems.get(i).getConfidence());
                //history.addPromise(promise);
                sortedList.add(promise);
            }
        }
        sortedList.sort(new PromiseAsinComparator());
        for (Promise promise : sortedList) {
            history.addPromise(promise);
        }
        //Comparator<Promise> promiseAsinComparator = new PromiseAsinComparator();
        //List<Promise> sortPromises = history.getPromises();
        //
        // sortedList.sort(new PromiseAsinComparator());
        //
        return history;
    }
}
