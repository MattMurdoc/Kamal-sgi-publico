package com.ar.sgi.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class SeccionValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSeccion() {
        SeccionDTO seccionDTO = new SeccionDTO();
        seccionDTO.setId(1L);
        seccionDTO.setDescripcion("Test 1");
        seccionDTO.setEstado(true);
        seccionDTO.setDepartamentoId(1L);
        Set<ConstraintViolation<SeccionDTO>> violations = validator.validate(seccionDTO);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testDescripcionNullShouldFailValidation() {
        SeccionDTO seccionDTO = new SeccionDTO();
        seccionDTO.setId(1L);
        seccionDTO.setEstado(true);
        seccionDTO.setDepartamentoId(1L);
        Set<ConstraintViolation<SeccionDTO>> violations = validator.validate(seccionDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testDescripcionMaxOverflowShouldFailValidation() {
        SeccionDTO seccionDTO = new SeccionDTO();
        seccionDTO.setId(1L);
        seccionDTO.setEstado(true);
        seccionDTO.setDepartamentoId(1L);
        seccionDTO.setDescripcion("A".repeat(256));
        Set<ConstraintViolation<SeccionDTO>> violations = validator.validate(seccionDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testNullSeccionIdShouldFailValidation() {
        SeccionDTO seccionDTO = new SeccionDTO();
        seccionDTO.setId(1L);
        seccionDTO.setEstado(true);
        seccionDTO.setDescripcion("Familia Test 1");
        Set<ConstraintViolation<SeccionDTO>> violations = validator.validate(seccionDTO);
        Assertions.assertFalse(violations.isEmpty());
    }
}
