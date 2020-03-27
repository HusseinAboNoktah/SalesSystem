package com.example.salesmanagment.Services;

import com.example.salesmanagment.Entity.Category;
import com.example.salesmanagment.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository ;
    public List<Category> getAll(){
        return  categoryRepository.findAll();
    }

    public void save(Category category){

      categoryRepository.save(category);
    }
    public Category get(long id){
        return categoryRepository.findById(id).get();
    }
    public Category update(Category category){
        Category existCategory  = categoryRepository.findById(category.getId()).orElse(null);

        existCategory.setName(category.getName());
        existCategory.setDescription(category.getDescription());
        return categoryRepository.save(existCategory);
    }

    public void delete(long id){
        categoryRepository.deleteById(id);
    }
}
