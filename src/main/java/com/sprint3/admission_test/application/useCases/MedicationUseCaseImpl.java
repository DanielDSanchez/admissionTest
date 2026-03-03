package com.sprint3.admission_test.application.useCases;

import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.application.ports.out.ICategoryRepository;
import com.sprint3.admission_test.application.ports.out.IMedicationRepository;
import com.sprint3.admission_test.domain.exceptions.NotFoundException;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.domain.ports.in.MedicationDTO;
import com.sprint3.admission_test.domain.ports.mapper.IMedicationMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationUseCaseImpl implements IMedicationUseCase {

    private final IMedicationRepository medicationRepository;
    private final ICategoryRepository categoryRepository;
    private final IMedicationMapper medicationMapper;

    @Override
    public MedicationDTO getMedicationById(Long id) {
        return medicationRepository.findById(id)
                .map(medicationMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find medication with ID: " + id
                ));
    }

    @Override
    public MedicationDTO save(@Validated MedicationDTO medicationDTO) {
        var category = categoryRepository.findCategoryByName(medicationDTO.getCategoryName())
                .orElseThrow(() -> new NotFoundException(
                        "Could not find category with name: " + medicationDTO.getCategoryName()
                ));
        var medication = medicationMapper.toEntity(medicationDTO);
        medication.setCategory(category);
        return medicationMapper.toDTO(
                medicationRepository.save(medication)
        );
    }

    @Override
    public List<MedicationDTO> getMedicationByCategory(@NonNull String categoryName, @NonNull LocalDate expirationsDate) {
        return medicationMapper.toDTOList(
                medicationRepository.findByCategoriesAndExpirationAfter(
                        categoryName,
                        expirationsDate
                )
        );
    }

}
