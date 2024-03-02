package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import com.rotikhao.storemanagement.models.Store;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@AllArgsConstructor
public class StoreDAO {

    private JdbcClient jdbcClient;

    public List<Store> getAllStores(UUID ownerId) {
        var sql = "SELECT * FROM STORES WHERE ownerid = :id";

        return jdbcClient.sql(sql)
                .param("id", ownerId)
                .query(Store.class)
                .list();
    }

    public UUID create(CreateStoreRequest createStoreRequest, UUID ownerId) {
        var storeId = UUID.randomUUID();

        jdbcClient.sql("""
                        INSERT INTO STORES(
                            ID,
                            STORENAME,
                            DESCRIPTION,
                            OWNERID
                        ) VALUES (:id, :storeName, :description, :ownerId);
                        """)
                .param("id", storeId)
                .param("storeName", createStoreRequest.getName())
                .param("description", createStoreRequest.getDescription())
            .param("ownerId", ownerId)
                .update();
        return storeId;
    }

    public Optional<Store> getStoreById(UUID storeID,UUID ownerId) {
        return jdbcClient.sql("SELECT * FROM STORES WHERE id = :id AND ownerId = :ownerId")
                .param("id", storeID)
                .param("ownerId", ownerId)
                .query(Store.class)
                .optional();
    }

    public void updateStore(Store store) {
        jdbcClient.sql("""
                UPDATE STORES SET STORENAME = :storeName, DESCRIPTION = :description, LOCATION = :location 
                WHERE ID = :id
                """).paramSource(store).update();
    }
}
