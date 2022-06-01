package com.javamachine.repository;

import com.javamachine.dto.OrderResponse;
import com.javamachine.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

 @Query("SELECT new com.javamachine.dto.OrderResponse(c.categoryName,p.productName) FROM Category c JOIN c.products p")
  public List<OrderResponse> getJoinInformation();
}