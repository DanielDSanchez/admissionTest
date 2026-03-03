package com.sprint3.admission_test.domain.ports.mapper;

import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.domain.ports.in.CategoryDTO;
import com.sprint3.admission_test.domain.ports.in.MedicationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMedicationMapper {

    MedicationDTO toDTO(MedicationDTO medicationDTO);

    Medication toEntity(MedicationDTO MedicationDTO);

    List<MedicationDTO> toDTOList(List<Medication> medications);
}
