/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.producto.logica;

import co.com.codesoftware.general.persistencia.EnvioFuncion;
import co.com.codesoftware.producto.dao.ProductoDao;
import co.com.codesoftware.producto.entity.Producto;
import co.com.codesoftware.utilidades.Utilidades;
import java.sql.ResultSet;

/**
 *
 * @author Personal
 */
public class ProductoLogica {

    public String buscaProductoXCodigoBarras(String dska_cod) {
        String rta = "";
        Producto prd = null;
        ProductoDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new ProductoDao();
            objDao.setDska_cod(dska_cod);
            ResultSet rs = function.enviarSelect(objDao.traeProductoCodigo());
            prd = new Producto();
            if (rs.next()) {
                prd.setDska_cod(rs.getString("dska_cod"));
                prd.setDska_desc(rs.getString("dska_desc"));
                prd.setDska_cate(rs.getString("dska_cate"));
                prd.setDska_dska(rs.getString("dska_dska"));
                prd.setDska_estado(rs.getString("dska_estado"));
                prd.setDska_fec_ingreso(rs.getString("dska_fec_ingreso"));
                prd.setDska_iva(rs.getString("dska_iva"));
                prd.setDska_marca(rs.getString("dska_marca"));
                prd.setDska_nom_prod(rs.getString("dska_nom_prod"));
                prd.setDska_porc_iva(rs.getString("dska_porc_iva"));
                prd.setDska_refe(rs.getString("dska_refe"));
            }
            Utilidades utilidades = new Utilidades();
            rta = utilidades.convertirObjetoJSON(prd);
        } catch (Exception e) {
            e.printStackTrace();
            prd = null;
        }
        return rta;
    }

}
