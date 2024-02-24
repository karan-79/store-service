package com.rotikhao.storemanagement.api.v1.web;

import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import com.rotikhao.storemanagement.models.Store;
import com.rotikhao.storemanagement.service.StoreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    private UUID owner = UUID.fromString("640b238f-a85e-40ce-b754-9dd7607469bc");

    @GetMapping
    public List<Store> getAllStores(@RequestBody String ownerId) { //TODO get details from authentication principle
        return storeService.getAllStore(UUID.fromString(ownerId));
    }

    @PostMapping
    public Store createStore(@RequestBody @Valid CreateStoreRequest createStoreRequest) {
        return storeService.createStore(createStoreRequest, owner);
    }

    @PutMapping
    public void update(@RequestBody CreateStoreRequest updateStoreRequest) {

    }



}
