package com.ar.sgi.model.dto;

public class ClienteFiltroDTO {

    String razonSocial,cuit;

    public ClienteFiltroDTO(){
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial){this.razonSocial = razonSocial;}

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

}
