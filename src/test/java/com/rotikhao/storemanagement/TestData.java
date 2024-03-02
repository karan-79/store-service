package com.rotikhao.storemanagement;

import com.rotikhao.storemanagement.api.v1.web.models.CreateItemRequest;
import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import com.rotikhao.storemanagement.models.Item;
import com.rotikhao.storemanagement.models.Store;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TestData {
    public CreateStoreRequest getCreateStoreRequest() {
        return new CreateStoreRequest("gian dhaba", "egg curry is best!!", "jha complex de samne");
    }

    public CreateItemRequest getCreateItemRequest() {
        return new CreateItemRequest("paneer bhurji", BigDecimal.valueOf(220), 2, "kraari hundi ae bhurji");
    }

    public Item getTestItem(UUID storeId) {
        return new Item(
                1,
                storeId,
                1,
                "aloo prantha",
                "1 khaan nal tid bhar je ga",
                BigDecimal.valueOf(30),
                true);
    }

    public Store getStoreData() {
        return new Store(UUID.randomUUID(),
                "some store",
                "description",
                UUID.randomUUID(),
                "flaani jgaah",
                LocalDate.now(),
                LocalDateTime.now());
    }

}
