package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import com.rotikhao.storemanagement.models.Store;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class StoreDAO {

    private JdbcClient jdbcClient;

    public List<Store> getAllStores(String ownerId) {
        return jdbcClient.sql("SELECT * FROM STORES WHERE ownerid = ?")
                .param(ownerId)
                .query(Store.class)
                .list();
    }

    public UUID create(CreateStoreRequest createStoreRequest, String ownerId) {
        var storeId = UUID.randomUUID();
        jdbcClient.sql("""
                        INSERT INTO STORES(
                            ID
                            STORENAME,
                            DESCRIPTION,
                            OWNERID
                        ) VALUES (:storeName, :description, :ownerId);
                        """)
                .param("id", storeId)
                .param("storeName", createStoreRequest.getName())
                .param("description", createStoreRequest.getDescription())
                .param("ownerId", ownerId)
                .query();
        return storeId;
    }

    public Store getStoreById(String storeID) {
        return jdbcClient.sql("SELECT * STORE WHERE id = ?")
                .param(storeID)
                .query(Store.class)
                .single();
    }

    public void updateStore(Store store) {
        jdbcClient.sql("""
                UPDATE STORES SET STORENAME = :storeName, DESCRIPTION = :description, LOCATION = :location 
                WHERE ID = :id
                """).paramSource(store).query();
    }
}
