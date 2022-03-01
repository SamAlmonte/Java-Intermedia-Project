package com.amazon.ata.deliveringonourpromise.comparators;

import com.amazon.ata.deliveringonourpromise.dao.ReadOnlyDao;
import com.amazon.ata.deliveringonourpromise.types.Promise;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PromiseAsinComparator implements Comparator<Promise >  {
    @Override
    public int compare(Promise promise1,Promise promise2) {
        String asin1 = promise1.getAsin();
        String asin2 = promise2.getAsin();
        //if(asin1.equals(asin2))
        //  return promise1.getDeliveryDate().compareTo(promise2.getDeliveryDate());
        int result = asin1.compareTo(asin2);
      /*  if (result == 0) {
            ZonedDateTime day1 =  promise1.getDeliveryDate();
            ZonedDateTime day2 =  promise1.getDeliveryDate();
            result = day1.compareTo(day2);
            if(result == 0){
                day1 = promise1.getPromiseLatestArrivalDate();
                day2 = promise2.getPromiseLatestArrivalDate();
                return day1.compareTo(day2);
            }
            return day1.compareTo(day2);
        }

       */
        return result;
    }

}