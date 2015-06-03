/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.contabilidad.dao;

import co.com.codesoftware.facturacion.entity.ProductosFactEntitiy;

/**
 *
 * @author nicolas
 */
public class ContabilidadDao {
    /**
     * Funcion encargada de obtener el valor que 
     * @return 
     */
    public String obtieneDatoSecuenciaTempoSim(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT nextval('co_temp_sim_movi_contables') secuencia ");
        return sql.toString();
    }
    /**
     * Funcion encargada de crear el insert para la tabla temporal de simulacion de facturacion 
     * @return 
     */
    public String insertTemoralProductos(ProductosFactEntitiy producto, String idTrans) {
        String sql = "";
        sql += "INSERT INTO co_ttem_sifc(                                  \n";
        sql += "            tem_sifc_trans, tem_sifc_dska, tem_sifc_cant, tem_sifc_dcto )  \n";
        sql += "    VALUES (" + idTrans + ", " + producto.getDska_dska()+ "," + producto.getDska_cant() + "," + producto.getDescuento() + ")\n";
        return sql;
    }
    
}
