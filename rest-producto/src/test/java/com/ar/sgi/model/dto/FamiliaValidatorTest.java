package com.ar.sgi.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class FamiliaValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidFamilia() {
        FamiliaDTO familiaDTO = new FamiliaDTO();
        familiaDTO.setId(1L);
        familiaDTO.setDescripcion("Test 1");
        familiaDTO.setEstado(true);
        familiaDTO.setSeccionId(1L);
        Set<ConstraintViolation<FamiliaDTO>> violations = validator.validate(familiaDTO);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testDescripcionNullShouldFailValidation() {
        FamiliaDTO familiaDTO = new FamiliaDTO();
        familiaDTO.setId(1L);
        familiaDTO.setEstado(true);
        familiaDTO.setSeccionId(1L);
        Set<ConstraintViolation<FamiliaDTO>> violations = validator.validate(familiaDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testDescripcionMaxOverflowShouldFailValidation() {
        FamiliaDTO familiaDTO = new FamiliaDTO();
        familiaDTO.setId(1L);
        familiaDTO.setEstado(true);
        familiaDTO.setSeccionId(1L);
        familiaDTO.setDescripcion("A".repeat(256));
        Set<ConstraintViolation<FamiliaDTO>> violations = validator.validate(familiaDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testNullSeccionIdShouldFailValidation() {
        FamiliaDTO familiaDTO = new FamiliaDTO();
        familiaDTO.setId(1L);
        familiaDTO.setDescripcion("Familia Test");
        familiaDTO.setEstado(true);
        Set<ConstraintViolation<FamiliaDTO>> violations = validator.validate(familiaDTO);
        Assertions.assertFalse(violations.isEmpty());
    }
}
