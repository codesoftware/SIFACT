/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.dao;

/**
 *
 * @author Personal
 */
public class ClienteDao {

    private String clien_clien;
    private String clien_cedula;
    private String clien_nombres;
    private String clien_apellidos;
    private String clien_telefono;
    private String clien_correo;

    public String getClien_clien() {
        return clien_clien;
    }

    public void setClien_clien(String clien_clien) {
        this.clien_clien = clien_clien;
    }

    public String getClien_cedula() {
        return clien_cedula;
    }

    public void setClien_cedula(String clien_cedula) {
        this.clien_cedula = clien_cedula;
    }

    public String getClien_nombres() {
        return clien_nombres;
    }

    public void setClien_nombres(String clien_nombres) {
        this.clien_nombres = clien_nombres;
    }

    public String getClien_apellidos() {
        return clien_apellidos;
    }

    public void setClien_apellidos(String clien_apellidos) {
        this.clien_apellidos = clien_apellidos;
    }

    public String getClien_telefono() {
        return clien_telefono;
    }

    public void setClien_telefono(String clien_telefono) {
        this.clien_telefono = clien_telefono;
    }

    public String getClien_correo() {
        return clien_correo;
    }

    public void setClien_correo(String clien_correo) {
        this.clien_correo = clien_correo;
    }

    public String consultarClienteXCedula() {
        String sql = "";
        sql += "SELECT clien_clien, clien_cedula, clien_nombres, clien_apellidos, clien_telefono,\n";
        sql += "       clien_correo                                                              \n";
        sql += "  FROM us_tclien                                                                 \n";
        sql += " WHERE clien_cedula =  " + this.getClien_cedula() + "                        \n";
        return sql;
    }

}
