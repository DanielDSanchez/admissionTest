package com.sprint3.admission_test.application.ports.in;

import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.domain.ports.in.MedicationDTO;

import java.util.Optional;

public interface IMedicationUseCase {

    Medication getMedicationById(Long id);

    Medication save(MedicationDTO medicationDTO);

}
