package com.sprint3.admission_test.infrastructure.adapter.out.persistence.repositoryImpl;

import com.sprint3.admission_test.application.ports.out.IMedicationRepository;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.infrastructure.adapter.out.persistence.jpaRepository.MedicationJpaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MedicationRepositoryImpl implements IMedicationRepository {

    private final MedicationJpaRepository medicationJpaRepository;

    @Override
    public Optional<Medication> findById(Long id) {
        return medicationJpaRepository.findById(id);
    }

    @Override
    public Medication save(Medication medication) { return medicationJpaRepository.save(medication); }

    @Override
    public List<Medication> findByCategoriesAndExpirationAfter(
            @NonNull String categoryName,
            @NonNull LocalDate expirationsDate
    ) {
        return medicationJpaRepository.findByCategoryAndExpirationsAfter(
                categoryName,
                expirationsDate,
                Pageable.unpaged(Sort.by("expirationDate").descending())
        );
    }

}
