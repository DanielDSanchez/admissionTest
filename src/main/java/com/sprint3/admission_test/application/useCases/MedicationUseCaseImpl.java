package com.sprint3.admission_test.application.useCases;

import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.application.ports.out.ICategoryRepository;
import com.sprint3.admission_test.application.ports.out.IMedicationRepository;
import com.sprint3.admission_test.domain.exceptions.NotFoundException;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.domain.ports.in.MedicationDTO;
import com.sprint3.admission_test.domain.ports.mapper.IMedicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class MedicationUseCaseImpl implements IMedicationUseCase {

    private final IMedicationRepository medicationRepository;
    private final ICategoryRepository categoryRepository;
    private final IMedicationMapper medicationMapper;

    @Override
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find medication with ID: " + id
                ));
    }

    @Override
    public Medication save(@Validated MedicationDTO medicationDTO) {
        var category = categoryRepository.findCategoryByName(medicationDTO.getCategoryName())
                .orElseThrow(() -> new NotFoundException(
                        "Could not find category with name: " + medicationDTO.getCategoryName()
                ));
        var medication = medicationMapper.toEntity(medicationDTO);
        medication.setCategory(category);
        return medicationRepository.save(medication);
    }
}
