package com.sprint3.admission_test.domain.ports.in;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    @JsonAlias("expiration_date")
    private LocalDate expirationDate;

    @NotNull
    @JsonAlias("category_name")
    private String categoryName;

}
