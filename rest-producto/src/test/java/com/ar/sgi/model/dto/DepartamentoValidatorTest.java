package com.ar.sgi.model.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class DepartamentoValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidDepartamento() {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(1L);
        departamentoDTO.setEstado(true);
        departamentoDTO.setDescripcion("Departamento Test 1");
        Set<ConstraintViolation<DepartamentoDTO>> violations = validator.validate(departamentoDTO);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testDescripcionNullShouldFailValidation() {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(1L);
        departamentoDTO.setEstado(true);
        Set<ConstraintViolation<DepartamentoDTO>> violations = validator.validate(departamentoDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testDescripcionMaxOverflowShouldFailValidation() {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(1L);
        departamentoDTO.setEstado(true);
        departamentoDTO.setDescripcion("A".repeat(256));
        Set<ConstraintViolation<DepartamentoDTO>> violations = validator.validate(departamentoDTO);
        Assertions.assertFalse(violations.isEmpty());
    }

}
