package com.sprint3.admission_test.infrastructure.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.domain.ports.in.CategoryDTO;
import com.sprint3.admission_test.domain.ports.in.MedicationDTO;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/medications")
@RequiredArgsConstructor
public class MedicationController {

    private final IMedicationUseCase medicationUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<MedicationDTO> getMedicationById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicationUseCase.getMedicationById(id));
    }

    @PostMapping
    public ResponseEntity<MedicationDTO> saveMedication(@Validated @RequestBody MedicationDTO medicationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(medicationUseCase.save(medicationDTO));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MedicationDTO>> getMedicationsByCategory(
            @PathVariable String category,
            @Validated @JsonFormat(pattern = "yyyy-MM-dd") @RequestParam("expiration-after") LocalDate expirationAfter) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicationUseCase.getMedicationByCategory(
                        category,
                        expirationAfter
                ));
    }

}
