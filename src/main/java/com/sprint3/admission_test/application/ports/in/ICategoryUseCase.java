package com.sprint3.admission_test.application.ports.in;

import com.sprint3.admission_test.domain.model.Category;
import lombok.NonNull;

public interface ICategoryUseCase {

    Category getCategoryByName(@NonNull String name);
}
