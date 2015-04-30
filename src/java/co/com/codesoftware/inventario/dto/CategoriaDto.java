/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.inventario.dto;

/**
 * Clase encargada de transferir el objeto categoria
 * @author nicolas
 */
public class CategoriaDto {

    private String cate_cate;
    private String cate_desc;
    private String cate_estado;
    private String cate_runic;
    private String cate_feven;

    public String getCate_cate() {
        return cate_cate;
    }

    public void setCate_cate(String cate_cate) {
        this.cate_cate = cate_cate;
    }

    public String getCate_desc() {
        return cate_desc;
    }

    public void setCate_desc(String cate_desc) {
        this.cate_desc = cate_desc;
    }

    public String getCate_estado() {
        return cate_estado;
    }

    public void setCate_estado(String cate_estado) {
        this.cate_estado = cate_estado;
    }

    public String getCate_runic(){
        return cate_runic;
    }

    public void setCate_runic(String cate_runic) {
        this.cate_runic = cate_runic;
    }

    public String getCate_feven() {
        return cate_feven;
    }

    public void setCate_feven(String cate_feven) {
        this.cate_feven = cate_feven;
    }
    
    

}
