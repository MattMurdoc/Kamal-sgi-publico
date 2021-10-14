package com.ar.sgi.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Proveedor {

    private Long id;

    private String nombreFantasia;

    public Proveedor() {
    }

    public Proveedor(Long id, String nombreFantasia) {
        this.id = id;
        this.nombreFantasia = nombreFantasia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }
}
