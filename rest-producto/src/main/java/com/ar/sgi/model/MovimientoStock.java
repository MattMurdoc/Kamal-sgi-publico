package com.ar.sgi.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "TB_MOVIMIENTO_STOCK")
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_HORA")
    private Timestamp fechaHora;

    @Column(name = "TIPO_MOVIMIENTO")
    private Long tipoMovimiento;

    @Column(name = "MOTIVO")
    private Long motivo;

    @Length(max = 255, message = "La observacion no puede superar los 255 caracteres")
    @Column(name = "OBSERVACION")
    private String observacion;

    public MovimientoStock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Long tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Long getMotivo() {
        return motivo;
    }

    public void setMotivo(Long motivo) {
        this.motivo = motivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

