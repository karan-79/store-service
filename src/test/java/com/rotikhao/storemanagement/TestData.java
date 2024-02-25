package com.rotikhao.storemanagement;

import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import com.rotikhao.storemanagement.models.Store;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TestData {
    public CreateStoreRequest getCreateStoreRequest(){
        return new CreateStoreRequest("gian dhaba", "egg curry is best!!", "jha complex de samne");
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
