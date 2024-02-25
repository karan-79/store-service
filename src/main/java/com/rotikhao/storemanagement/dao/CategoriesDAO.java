package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.api.v1.web.ItemsController;
import com.rotikhao.storemanagement.api.v1.web.models.APIItemCategory;
import com.rotikhao.storemanagement.api.v1.web.models.CreateCategoryRequest;
import com.rotikhao.storemanagement.models.ItemCategory;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
@AllArgsConstructor
public class CategoriesDAO {

    private final JdbcClient jdbcClient;

    public List<ItemCategory> getAllCategories(String storeId) {
        var sql = """
                Select * from MENUCATEGORIES where STOREID = :storeId
                """;
        return jdbcClient.sql(sql)
                .param("storeId", storeId)
                .query(ItemCategory.class)
                .list();
    }

    public int createItemCategory(CreateCategoryRequest request, String storeId) {
        var sql = """
                INSERT INTO MENUCATEGORIES (
                        STOREID,
                        NAME
                ) VALUES (:storeId, :name)
                RETURNING ID;
                """;
        return jdbcClient.sql(sql)
                .param("storeId", storeId)
                .param("name", request.getName())
                .query(Integer.class)
                .single();
    }

    public void updateCategory(APIItemCategory apiItemCategory, String storeId){
        var sql = """
                UPDATE MENUCATEGORIES SET NAME = :name WHERE ID = :id AND storeId = :storeId
                """;
        jdbcClient.sql(sql)
                .param("name", apiItemCategory.getName())
                .param("id", apiItemCategory.getId())
                .param("storeId", storeId)
                .update();
    }

    public void deleteCategory(int categoryId, String storeId) {
        var sql = """
                DELETE FROM MENUCATEGORIES WHERE ID = :id AND STOREID = :storeId
                """;
        jdbcClient.sql(sql)
                .param("id", categoryId)
                .param("storeId", storeId)
                .update();
    }
}
