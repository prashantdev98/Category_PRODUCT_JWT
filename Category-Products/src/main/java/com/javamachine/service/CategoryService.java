package com.javamachine.service;


import com.javamachine.dto.CustomResponse;
import com.javamachine.dto.OrderResponse;
import com.javamachine.entity.Category;
import com.javamachine.exception.CategoryNotFoundException;
import com.javamachine.repository.CategoryRepository;
import com.javamachine.util.Convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mapping.PropertyReferenceException;
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
    
    public List<CustomResponse> findCategorywithSorting(String field) throws CategoryNotFoundException{
    	try {
    		return dto.entityToDtoList( categoryRepository.findAll(Sort.by(Direction.ASC,field)));
    	}catch(PropertyReferenceException ex) {
    		throw new CategoryNotFoundException("field does not exist");
    	}
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
    
    
    public Category getCategory(int id) throws CategoryNotFoundException{
        if(categoryRepository.existsById(id)){
        	return categoryRepository.findById(id).get();
        }else {
        	throw new CategoryNotFoundException("category not present");
        }
//    	Optional<Category> category = categoryRepository.findById(id);
//        
//        return category.get();
        
    }
    
    public List<OrderResponse> getCategoryByCustomResponse() throws CategoryNotFoundException{
    	if(categoryRepository.getJoinInformation().isEmpty()) {
    		throw new CategoryNotFoundException("category not present");
    	}
    	return categoryRepository.getJoinInformation();
    }
    
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
    
    
    public Category updateCategory(int id,Category category)throws CategoryNotFoundException{
    	if(categoryRepository.existsById(id)){
    		category.setCategoryId(id);
    		return categoryRepository.save(category);
    	}else {
    		throw new CategoryNotFoundException("category not present");
    	}
    }
    public void deleteCategory(int id)throws CategoryNotFoundException{
    	if(categoryRepository.existsCategoryByCategoryId(id)){
    		categoryRepository.deleteById(id);
    	}else {
    		throw new CategoryNotFoundException("category not present");
    	}
    }
    
    public List<Category> findByPartialBetweenName(String name) throws CategoryNotFoundException{
    	name = "%"+name+"%";
    	if(categoryRepository.existsCategoryByCategoryNameLike(name)) {
    		return categoryRepository.findByCategoryNameLike(name);
    	}else {
    		throw new CategoryNotFoundException("category not present");
    	}
    }
    
    public List<Category> findByPartialStartWithName(String name) throws CategoryNotFoundException{
    	if(categoryRepository.existsCategoryByCategoryNameStartsWith(name)) {
    		return categoryRepository.findByCategoryNameStartsWith(name);
    	}else {
    		throw new CategoryNotFoundException("category not present");
    	}
    	
    }
    
    
    public List<Category> findByPartialEndWithName(String name) throws CategoryNotFoundException{
    	if(categoryRepository.existsCategoryByCategoryNameEndsWith(name)) {
    		return categoryRepository.findByCategoryNameEndsWith(name);
    	}else {
    		throw new CategoryNotFoundException("category not present");
    	}
    	
    }
}