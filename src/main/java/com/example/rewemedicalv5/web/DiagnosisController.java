package com.example.rewemedicalv5.web;

import com.example.rewemedicalv5.data.dtos.diagnosis.EditDiagnosisDto;
import com.example.rewemedicalv5.data.dtos.diagnosis.NewDiagnosisDto;
import com.example.rewemedicalv5.data.dtos.diagnosis.ViewDiagnosisDto;
import com.example.rewemedicalv5.exceptions.validations.UniqueDiagnosisCode;
import com.example.rewemedicalv5.services.DiagnosisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.example.rewemedicalv5.exceptions.InvalidValidationMessage.INVALID_DIAGNOSIS_CODE;

@RestController
@RequestMapping("/api/diagnoses")
@AllArgsConstructor
@Validated
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @GetMapping("/all")
    public ResponseEntity<List<ViewDiagnosisDto>> getAll() {
        var diagnoses = diagnosisService.getAll();

        if (diagnoses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(diagnoses);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ViewDiagnosisDto> findById(
//            @PathVariable
//            @Positive(message = "Invalid ID")
//            Long id
//    ) {
//        return ResponseEntity.ok(diagnosisService.findById(id));
//    }

    @GetMapping("/{code}")
    public ResponseEntity<ViewDiagnosisDto> findViewByCode(
            @PathVariable
            @NotBlank(message = INVALID_DIAGNOSIS_CODE)
            String code
    ) {
        return ResponseEntity.ok(diagnosisService.findViewByCode(code));
    }

    @PostMapping("/add")
    public ResponseEntity<ViewDiagnosisDto> create(
            @RequestBody
            @Valid NewDiagnosisDto dto,

            UriComponentsBuilder uriComponentsBuilder
    ) {

        diagnosisService.create(dto);

        return ResponseEntity
                .created(uriComponentsBuilder
                                .path("/api/diagnoses")
                                .path("/{code}")
                        .build(dto.code())
                )
                .build();
    }

    @PutMapping("/{code}/edit")
    public ResponseEntity<ViewDiagnosisDto> update(
            @PathVariable
            @NotBlank(message = INVALID_DIAGNOSIS_CODE)
            String code,

            @RequestBody
            @Valid
            EditDiagnosisDto dto
    ) {
        return ResponseEntity.ok(diagnosisService.update(code, dto));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ViewDiagnosisDto> delete(
//            @PathVariable
//            @Positive(message = "Invalid ID")
//            Long id
//    ) {
//        diagnosisService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping
    public ResponseEntity<ViewDiagnosisDto> deleteByCode(
            @PathVariable
            @NotBlank(message = INVALID_DIAGNOSIS_CODE)
            String code
    ) {
        diagnosisService.delete(code);
        return ResponseEntity.noContent().build();
    }
}
