package com.sprint3.admission_test.domain.ports.mapper;

import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.domain.ports.in.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {


    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toList(List<Category> categories);
}
