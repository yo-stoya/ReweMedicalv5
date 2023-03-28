package com.example.rewemedicalv5.data.dtos.specialty;

import com.example.rewemedicalv5.exceptions.validations.UniqueSpecialtyName;
import jakarta.validation.constraints.NotBlank;

import static com.example.rewemedicalv5.exceptions.InvalidValidationMessage.INVALID_SPECIALTY_NAME;

public record NewSpecialtyDto(
        @NotBlank(message = INVALID_SPECIALTY_NAME)
        @UniqueSpecialtyName
        String name
) {
}