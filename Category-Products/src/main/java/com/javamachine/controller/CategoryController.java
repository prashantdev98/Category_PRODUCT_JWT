package com.javamachine.controller;


import com.javamachine.dto.APIResponse;
import com.javamachine.dto.CustomResponse;
import com.javamachine.dto.OrderResponse;
import com.javamachine.entity.Category;
import com.javamachine.repository.CategoryRepository;
import com.javamachine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Category> getCategory(){
        return categoryService.getCategories();
    }
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id){
        return categoryService.getCategory(id);
    }
    
    @GetMapping("/custom")
    public List<OrderResponse> getCustomCategory(){
        return categoryRepository.getJoinInformation();
    }
    
    @GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Category>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Category> productsWithPagination = categoryService.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(productsWithPagination.getSize(),productsWithPagination);
    }
    
    
    @GetMapping("/sort/{field}")
    public List<CustomResponse> getSortedCategory(@ PathVariable String field){
    	List<CustomResponse> sortList = categoryService.findCategorywithSorting(field);
    	return sortList;
    }
    
    
    @GetMapping("/sortAscend")
    public List<CustomResponse> getAscend(){
    	List<CustomResponse> sortList = categoryService.findCategorywithSortingAscending();
    	return sortList;
    }
    
    
    @GetMapping("/sortDescend")
    public List<CustomResponse> getDescend(){
    	List<CustomResponse> sortList = categoryService.findCategorywithSortingDescending();
    	return sortList;
    }
    
    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @PutMapping("/{id}")	
    public Category addCategory(@PathVariable int id,@RequestBody Category category){
        return categoryService.updateCategory(id,category);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id){
    	categoryService.deleteCategory(id);
    	return "DELETED "+id;
    }
}