/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.usuario.dao;

/**
 *
 * @author Nicolas
 */
public class UsuarioDao {

    private String tius_tius;
    private String tius_pers;
    private String tius_perf;
    private String tius_tipo_usuario;
    private String tius_usuario;
    private String tius_fecha_registro;
    private String tius_ultimo_ingreso;
    private String tius_contra_act;
    private String tius_contra_futura;
    private String tius_cambio_contra;
    private String tius_estado;
    private String tius_sede;

    public String getTius_tius() {
        return tius_tius;
    }

    public void setTius_tius(String tius_tius) {
        this.tius_tius = tius_tius;
    }

    public String getTius_pers() {
        return tius_pers;
    }

    public void setTius_pers(String tius_pers) {
        this.tius_pers = tius_pers;
    }

    public String getTius_perf() {
        return tius_perf;
    }

    public void setTius_perf(String tius_perf) {
        this.tius_perf = tius_perf;
    }

    public String getTius_tipo_usuario() {
        return tius_tipo_usuario;
    }

    public void setTius_tipo_usuario(String tius_tipo_usuario) {
        this.tius_tipo_usuario = tius_tipo_usuario;
    }

    public String getTius_usuario() {
        return tius_usuario;
    }

    public void setTius_usuario(String tius_usuario) {
        this.tius_usuario = tius_usuario;
    }

    public String getTius_fecha_registro() {
        return tius_fecha_registro;
    }

    public void setTius_fecha_registro(String tius_fecha_registro) {
        this.tius_fecha_registro = tius_fecha_registro;
    }

    public String getTius_ultimo_ingreso() {
        return tius_ultimo_ingreso;
    }

    public void setTius_ultimo_ingreso(String tius_ultimo_ingreso) {
        this.tius_ultimo_ingreso = tius_ultimo_ingreso;
    }

    public String getTius_contra_act() {
        return tius_contra_act;
    }

    public void setTius_contra_act(String tius_contra_act) {
        this.tius_contra_act = tius_contra_act;
    }

    public String getTius_contra_futura() {
        return tius_contra_futura;
    }

    public void setTius_contra_futura(String tius_contra_futura) {
        this.tius_contra_futura = tius_contra_futura;
    }

    public String getTius_cambio_contra() {
        return tius_cambio_contra;
    }

    public void setTius_cambio_contra(String tius_cambio_contra) {
        this.tius_cambio_contra = tius_cambio_contra;
    }

    public String getTius_estado() {
        return tius_estado;
    }

    public void setTius_estado(String tius_estado) {
        this.tius_estado = tius_estado;
    }

    public String getTius_sede() {
        return tius_sede;
    }

    public void setTius_sede(String tius_sede) {
        this.tius_sede = tius_sede;
    }

    /**
     * Funcion encargada de realizar la validacion de la sede del usuario y una
     * sede en especifico
     *
     * @return
     */
    public String validaSedeYFacturacion() {
        String sql = "";
        sql += "SELECT CASE                                                \n";
        sql += "        WHEN count(*) = 1 THEN 'EXISTE'                    \n";
        sql += "        ELSE 'INEXISTENTE'                                 \n";
        sql += "        END valUsua,                                       \n";
        sql += "      CASE                                                 \n";
        sql += "        WHEN tius_sede = " + this.getTius_sede() + " THEN 'CONCIDE'\n";
        sql += "        ELSE 'NOCOINCIDE'                                  \n";
        sql += "        END sede                                           \n";
        sql += "  FROM us_ttius                                            \n";
        sql += " WHERE upper(trim(tius_usuario)) = upper(trim('" + this.getTius_usuario() + "'))\n";
        sql += "GROUP BY tius_sede                                         \n";
        sql += "  HAVING count(*) < 2                                      \n";
        return sql;
    }

    /**
     * Funcion encargada realizar el Query para buscar un usuario teniendo como
     * referencia el usuario
     *
     * @return
     */
    public String buscaUsuarioXUsuario() {
        String sql = "";
        sql += "SELECT tius_tius, tius_pers, tius_perf, tius_tipo_usuario, tius_usuario,               \n";
        sql += "       tius_fecha_registro, tius_ultimo_ingreso, tius_contra_act, tius_contra_futura,  \n";
        sql += "       tius_cambio_contra, tius_estado, tius_sede                                      \n";
        sql += "  FROM us_ttius                                                                        \n";
        sql += " WHERE upper(trim(tius_usuario)) =  upper(trim('" + this.getTius_usuario() + "'))      \n";
        return sql;
    }
}
