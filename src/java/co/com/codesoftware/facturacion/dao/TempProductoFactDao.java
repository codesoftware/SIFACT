/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.dao;

/**
 *
 * @author Nicolas
 */
public class TempProductoFactDao {

    private String tem_fact_trans;
    private String tem_fact_dska;
    private String tem_fact_cant;
    private String tem_fact_dcto;

    public String getTem_fact_trans() {
        return tem_fact_trans;
    }

    public void setTem_fact_trans(String tem_fact_trans) {
        this.tem_fact_trans = tem_fact_trans;
    }

    public String getTem_fact_dska() {
        return tem_fact_dska;
    }

    public void setTem_fact_dska(String tem_fact_dska) {
        this.tem_fact_dska = tem_fact_dska;
    }

    public String getTem_fact_cant() {
        return tem_fact_cant;
    }

    public void setTem_fact_cant(String tem_fact_cant) {
        this.tem_fact_cant = tem_fact_cant;
    }

    public String getTem_fact_dcto() {
        return tem_fact_dcto;
    }

    public void setTem_fact_dcto(String tem_fact_dcto) {
        this.tem_fact_dcto = tem_fact_dcto;
    }

    /**
     * Funcion encargada de realizar el Query necesario para insertar los datos
     * en la tabla temporal de facturacion
     *
     * @return
     */
    public String insertTemoral() {
        String sql = "";
        sql += "INSERT INTO co_ttem_fact(                                  \n";
        sql += "            tem_fact,tem_fact_trans, tem_fact_dska, tem_fact_cant, tem_fact_dcto )  \n";
        sql += "    VALUES ((select coalesce( max(tem_fact), 0 ) +1 from co_ttem_fact)," + this.getTem_fact_trans() + ", " + this.getTem_fact_dska() + "," + this.getTem_fact_cant() + "," + this.getTem_fact_dcto() + ")\n";
        return sql;
    }

    /**
     * Funcion encargada de realiar el Query necesaria para obtener el valor de
     * la secuencia de la tabla temporal
     *
     * @return
     */
    public String obtieneSecuenciaTemFact() {
        String sql = "";
        sql = "SELECT nextval('co_temp_tran_factu_sec') secuencia ";
        return sql;
    }

    /**
     * Funcion encargada de realizar el Query para eliminar los datos temporales
     * de facturacion
     *
     * @param idTrans
     * @return
     */
    public String borraDatosXIdTrans(String idTrans) {
        String sql = "";
        sql = "DELETE FROM co_ttem_fact WHERE tem_fact_trans = " + idTrans;
        return sql;
    }

    /**
     * Funcion encargada de realizar el Query para obtener la secuencia de
     * transaccion para una
     *
     * @return
     */
    public String obtieneSecuenciaTemRemision() {
        String sql = "";
        sql = "SELECT nextval('in_tsec_trans_rmce') secuencia ";
        return sql;
    }

}
