package com.ar.sgi.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class SubFamiliaValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSeccion() {
        SubFamiliaDTO subFamiliaDTO = new SubFamiliaDTO();
        subFamiliaDTO.setId(1L);
        subFamiliaDTO.setDescripcion("Test 1");
        subFamiliaDTO.setEstado(true);
        subFamiliaDTO.setFamiliaId(1L);
        Set<ConstraintViolation<SubFamiliaDTO>> violations = validator.validate(subFamiliaDTO);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testDescripcionNullShouldFailValidation() {
        SubFamiliaDTO subFamiliaDTO = new SubFamiliaDTO();
        subFamiliaDTO.setId(1L);
        subFamiliaDTO.setEstado(true);
        subFamiliaDTO.setFamiliaId(1L);
        Set<ConstraintViolation<SubFamiliaDTO>> violations = validator.validate(subFamiliaDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testDescripcionMaxOverflowShouldFailValidation() {
        SubFamiliaDTO subFamiliaDTO = new SubFamiliaDTO();
        subFamiliaDTO.setId(1L);
        subFamiliaDTO.setEstado(true);
        subFamiliaDTO.setDescripcion("A".repeat(256));
        subFamiliaDTO.setFamiliaId(1L);
        Set<ConstraintViolation<SubFamiliaDTO>> violations = validator.validate(subFamiliaDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testNullSeccionIdShouldFailValidation() {
        SubFamiliaDTO subFamiliaDTO = new SubFamiliaDTO();
        subFamiliaDTO.setId(1L);
        subFamiliaDTO.setEstado(true);
        subFamiliaDTO.setDescripcion("A");
        Set<ConstraintViolation<SubFamiliaDTO>> violations = validator.validate(subFamiliaDTO);
        Assertions.assertFalse(violations.isEmpty());
    }
}
