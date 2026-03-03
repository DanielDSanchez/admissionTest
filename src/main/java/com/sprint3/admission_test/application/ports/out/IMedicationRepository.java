package com.sprint3.admission_test.application.ports.out;

import com.sprint3.admission_test.domain.model.Medication;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IMedicationRepository {

    Optional<Medication> findById(Long id);

    Medication save(Medication medication);

    List<Medication> findByCategoriesAndExpirationAfter(
            @NonNull String categoryName,
            @NonNull LocalDate expirationsDate
    );
}
