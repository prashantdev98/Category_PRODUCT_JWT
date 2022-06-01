package com.javamachine.service;


import com.javamachine.dto.CustomResponse;
import com.javamachine.entity.Category;
import com.javamachine.repository.CategoryRepository;
import com.javamachine.util.Convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Convertor dto = new Convertor();

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
    
    public Page<Category> findProductsWithPagination(int offset,int pageSize){
        Page<Category> categories = categoryRepository.findAll(PageRequest.of(offset, pageSize));
        return  categories;
    }
    
    public List<CustomResponse> findCategorywithSorting(String field){
    	return dto.entityToDtoList( categoryRepository.findAll(Sort.by(Direction.ASC,field)));
    }
    
    public List<CustomResponse> findCategorywithSortingAscending(){
    	List<CustomResponse> unSort = dto.entityToDtoList(categoryRepository.findAll());   
    	unSort = unSort.stream().sorted(Comparator.comparing(CustomResponse::getCategorytName)).collect(Collectors.toList());
    	return unSort;
    }
    
    
    public List<CustomResponse> findCategorywithSortingDescending(){
    	List<CustomResponse> unSort = dto.entityToDtoList(categoryRepository.findAll());   
    	unSort = unSort.stream().sorted(Comparator.comparing(CustomResponse::getCategorytName).reversed()).collect(Collectors.toList());
    	return unSort;
    }
    
    
    public Category getCategory(int id){
        Optional<Category> category = categoryRepository.findById(id);

        return category.get();
    }
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category updateCategory(int id,Category category){
    	category.setCategoryId(id);
        return categoryRepository.save(category);
    }
    public void deleteCategory(int id){
    	categoryRepository.deleteById(id);
    }
}