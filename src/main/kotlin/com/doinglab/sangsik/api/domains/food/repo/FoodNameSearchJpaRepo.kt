package com.doinglab.sangsik.api.domains.food.repo

import com.doinglab.sangsik.api.domains.food.entity.FoodNameSearch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FoodNameSearchJpaRepo: JpaRepository<FoodNameSearch, String> {
    @Query("SELECT * " +
            "FROM FoodNameSearch " +
            " WHERE REPLACE(FoodNameSearch.foodName, ' ', '')  LIKE CONCAT('%', :keyword, '%') or FoodNameSearch.manufacturer LIKE CONCAT('%', :keyword, '%')" +
            " ORDER BY CASE WHEN foodName = :keyword THEN 0" +
            " WHEN foodName LIKE CONCAT('%', :keyword) THEN 1" +
            " WHEN foodName LIKE CONCAT(:keyword, '%') THEN 2"+
            " WHEN foodName LIKE CONCAT('%', :keyword, '%') THEN 3"+
            " ELSE 4"+
            " END, foodName ASC limit 1000", nativeQuery = true)
    fun findFoodNameSearch(keyword: String): List<FoodNameSearch>
}
