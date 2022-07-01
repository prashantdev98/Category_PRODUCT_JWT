package com.javamachine.controller;


import com.javamachine.dto.APIResponse;
import com.javamachine.dto.CustomResponse;
import com.javamachine.dto.OrderResponse;
import com.javamachine.entity.Category;
import com.javamachine.exception.CategoryNotFoundException;
import com.javamachine.repository.CategoryRepository;
import com.javamachine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CUSTOMER')")
    public List<Category> getCategory(){
        return categoryService.getCategories();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?>  getCategoryById(@PathVariable int id)throws CategoryNotFoundException{
    	
		return new ResponseEntity<> (categoryService.getCategory(id),HttpStatus.OK);
    	
    }
    
    @GetMapping("/custom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?>  getCustomCategory()throws CategoryNotFoundException{
//        return categoryRepository.getJoinInformation();
    	
		return new ResponseEntity<>(categoryService.getCategoryByCustomResponse(),HttpStatus.OK);
    	
    }
//    APIResponse<Page<Category>>
    @GetMapping("/pagination/{offset}/{pageSize}")
    @PreAuthorize("hasAuthority('ADMIN')")
    private  ResponseEntity<?> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Category> productsWithPagination = categoryService.findProductsWithPagination(offset, pageSize);
//        return new APIResponse<>(productsWithPagination.getSize(),productsWithPagination);
        return new ResponseEntity<>(new APIResponse<>(productsWithPagination.getSize(),productsWithPagination),HttpStatus.OK);
    }
    
    
    @GetMapping("/sort/{field}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomResponse> getSortedCategory(@ PathVariable String field)throws CategoryNotFoundException{
    	List<CustomResponse> sortList = categoryService.findCategorywithSorting(field);
    	return sortList;
    }
    
    
    @GetMapping("/sortAscend")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomResponse> getAscend(){
    	List<CustomResponse> sortList = categoryService.findCategorywithSortingAscending();
    	return sortList;
    }
    
    
    @GetMapping("/sortDescend")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomResponse> getDescend(){
    	List<CustomResponse> sortList = categoryService.findCategorywithSortingDescending();
    	return sortList;
    }
    
    
    @GetMapping("/partial/betweenName/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getByPartialBetweenName(@PathVariable String name)throws CategoryNotFoundException{
    	List<Category> categoryList = categoryService.findByPartialBetweenName(name);
    	return new ResponseEntity <>(categoryList,HttpStatus.OK);
    }
    
    @GetMapping("/partial/startName/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getByPartialStartWithName(@PathVariable String name)throws CategoryNotFoundException{
    	List<Category> categoryList = categoryService.findByPartialStartWithName(name);
    	return new ResponseEntity <>(categoryList,HttpStatus.OK);
    }
    
    
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    
    @PutMapping("/{id}")	
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category updateCategory(@PathVariable int id,@RequestBody Category category)throws CategoryNotFoundException{
        return categoryService.updateCategory(id,category);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCategory(@PathVariable int id)throws CategoryNotFoundException{
    	categoryService.deleteCategory(id);
    	return "DELETED "+id;
    }
}