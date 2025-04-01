package com.project.BlogApplication.contoller;

import com.project.BlogApplication.category.CategoryService;
import com.project.BlogApplication.dto.CategoryRequestDTO;
import com.project.BlogApplication.dto.CategoryResponseDTO;
import com.project.BlogApplication.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(CategoryRequestDTO category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

}
