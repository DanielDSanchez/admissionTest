package com.sprint3.admission_test.application.ports.out;

import com.sprint3.admission_test.domain.model.Category;
import lombok.NonNull;

import java.util.Optional;

public interface ICategoryRepository {

    Optional<Category> findCategoryByName(@NonNull String name);
}
