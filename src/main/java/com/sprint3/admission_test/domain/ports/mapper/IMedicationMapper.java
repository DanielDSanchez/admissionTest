package com.sprint3.admission_test.domain.ports.mapper;

import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.domain.ports.in.CategoryDTO;
import com.sprint3.admission_test.domain.ports.in.MedicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMedicationMapper {

    @Mapping(source = "category.name",target = "categoryName" )
    MedicationDTO toDTO(Medication Medication);

    Medication toEntity(MedicationDTO MedicationDTO);

    List<MedicationDTO> toDTOList(List<Medication> medications);
}
