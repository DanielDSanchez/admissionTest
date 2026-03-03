package com.sprint3.admission_test.application.ports.in;

import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.domain.ports.in.MedicationDTO;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMedicationUseCase {

    MedicationDTO getMedicationById(Long id);

    MedicationDTO save(MedicationDTO medicationDTO);

    List<MedicationDTO> getMedicationByCategory(@NonNull String categoryName,@NonNull LocalDate expirationsDate);
}
