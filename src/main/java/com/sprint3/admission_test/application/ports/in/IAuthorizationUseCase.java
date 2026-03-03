package com.sprint3.admission_test.application.ports.in;

import com.sprint3.admission_test.domain.ports.in.MedicationDTO;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;

public interface IAuthorizationUseCase {

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);
}
