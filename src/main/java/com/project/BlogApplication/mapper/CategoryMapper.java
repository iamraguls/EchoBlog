package com.project.BlogApplication.mapper;

import com.project.BlogApplication.dto.CategoryRequestDTO;
import com.project.BlogApplication.dto.CategoryResponseDTO;
import com.project.BlogApplication.entity.Category;
import com.project.BlogApplication.entity.PostStatus;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDTO toDTO(Category category){
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        Long publishedPostCount = category
                .getPosts()
                .stream()
                .filter(post -> post.getStatus() == PostStatus.PUBLISHED)
                .count();

        dto.setPostCount(publishedPostCount);
        return dto;
    }

    public Category toEntity(CategoryRequestDTO categoryRequestDTO){
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        return category;
    }

}
