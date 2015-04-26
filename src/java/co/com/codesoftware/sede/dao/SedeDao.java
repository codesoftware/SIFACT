/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.sede.dao;

/**
 *
 * @author Nicolas
 */
public class SedeDao {

    private String sede_sede;
    private String sede_nombre;
    private String sede_direccion;
    private String sede_telefono;
    private String sede_fecin;
    private String sede_tius;
    private String sede_estado;

    public String getSede_sede() {
        return sede_sede;
    }

    public void setSede_sede(String sede_sede) {
        this.sede_sede = sede_sede;
    }

    public String getSede_nombre() {
        return sede_nombre;
    }

    public void setSede_nombre(String sede_nombre) {
        this.sede_nombre = sede_nombre;
    }

    public String getSede_direccion() {
        return sede_direccion;
    }

    public void setSede_direccion(String sede_direccion) {
        this.sede_direccion = sede_direccion;
    }

    public String getSede_telefono() {
        return sede_telefono;
    }

    public void setSede_telefono(String sede_telefono) {
        this.sede_telefono = sede_telefono;
    }

    public String getSede_fecin() {
        return sede_fecin;
    }

    public void setSede_fecin(String sede_fecin) {
        this.sede_fecin = sede_fecin;
    }

    public String getSede_tius() {
        return sede_tius;
    }

    public void setSede_tius(String sede_tius) {
        this.sede_tius = sede_tius;
    }

    public String getSede_estado() {
        return sede_estado;
    }

    public void setSede_estado(String sede_estado) {
        this.sede_estado = sede_estado;
    }

    /**
     * Funcion encargada de realizar el Query para buscar una sede por el Id
     *
     * @return
     */
    public String buscaSedeXId() {
        String sql = "";
        sql += "SELECT sede_sede, sede_nombre, sede_direccion, sede_telefono, sede_fecin,\n";
        sql += "       sede_tius, sede_estado                                            \n";
        sql += "  FROM em_tsede                                                          \n";
        sql += " WHERE sede_sede = " + this.getSede_sede() + "";
        return sql;
    }
    
    /**
     * Funcion encargada de encontrar las sedes las cuales se encuentran activas
     *
     * @return
     */
    public String buscaSedesActivas() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT sede_sede, sede_nombre, sede_direccion, sede_telefono, sede_fecin, ");
        sql.append("       sede_tius, sede_estado ");
        sql.append("FROM em_tsede ");
        sql.append("WHERE sede_estado = 'A'");
        return sql.toString();
    }

}