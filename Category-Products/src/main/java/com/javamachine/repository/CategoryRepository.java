package com.javamachine.repository;

import com.javamachine.dto.OrderResponse;
import com.javamachine.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

 @Query("SELECT new com.javamachine.dto.OrderResponse(c.categoryName,p.productName) FROM Category c JOIN c.products p")
  public List<OrderResponse> getJoinInformation();
 
 List<Category> findByCategoryNameLike(String categoryName);
 List<Category> findByCategoryNameStartsWith(String categoryName);
 List<Category> findByCategoryNameEndsWith(String categoryName);
 boolean existsCategoryByCategoryId(int categoryId); 
 boolean existsCategoryByCategoryNameLike(String categoryName);
 boolean existsCategoryByCategoryNameStartsWith(String categoryName);
 boolean existsCategoryByCategoryNameEndsWith(String categoryName);
}