package com.project.BlogApplication.mapper;

import com.project.BlogApplication.dto.CategoryDTO;
import com.project.BlogApplication.entity.Category;
import com.project.BlogApplication.entity.PostStatus;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category){
        CategoryDTO dto = new CategoryDTO();
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

}
