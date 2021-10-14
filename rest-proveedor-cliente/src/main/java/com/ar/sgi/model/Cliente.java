package com.ar.sgi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity(name = "TB_CLIENTE")
public class Cliente extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "CUIT")
    private String cuit;
    @Column(name = "CATEGORIA_IVA")
    private Long categoriaIVA;
    @Column(name = "TIPO_DNI")
    private Long tipoDNI;
    @Column(name = "DOMICILIO")
    private String domicilio;

    public Cliente() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Long getCategoriaIVA() {
        return categoriaIVA;
    }

    public void setCategoriaIVA(Long categoriaIVA) {
        this.categoriaIVA = categoriaIVA;
    }

    public String getDomicilio() { return domicilio;}

    public void setDomicilio(String domicilio){ this.domicilio = domicilio;}

    public Long getTipoDNI() {
        return tipoDNI;
    }

    public void setTipoDNI(Long tipoDNI) {
        this.tipoDNI = tipoDNI;
    }
}
