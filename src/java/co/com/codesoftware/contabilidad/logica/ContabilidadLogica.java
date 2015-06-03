/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.contabilidad.logica;

import co.com.codesoftware.contabilidad.dao.ContabilidadDao;
import co.com.codesoftware.facturacion.entity.ProductosFactEntitiy;
import co.com.codesoftware.general.persistencia.EnvioFuncion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class ContabilidadLogica {

    private List moviContable;

    public String generaSimulacionAsientoContable(ArrayList<ProductosFactEntitiy> productos) {
        String rta = null;
        try {
            String sec = this.obtieneSecuenciaTemporalSim();
            String valida = "";
            if (sec != null) {
                valida = this.insertaProductosTemporal(productos, sec);
                if ("Ok".equalsIgnoreCase(valida)) {

                } else {
                    rta = valida;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    private String obtieneSecuenciaTemporalSim() {
        String sec = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            ContabilidadDao objDao = new ContabilidadDao();
            ResultSet rs = function.enviarSelect(objDao.obtieneDatoSecuenciaTempoSim());
            if (rs.next()) {
                sec = rs.getString("secuencia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sec;
    }

    private String insertaProductosTemporal(ArrayList<ProductosFactEntitiy> productos, String sec) {
        String rta = "Ok";
        try (EnvioFuncion function = new EnvioFuncion()) {
            ContabilidadDao objDao = new ContabilidadDao();
            for (ProductosFactEntitiy producto : productos) {
                boolean valida = function.enviarUpdate(objDao.insertTemoralProductos(producto, sec));
                if (!valida) {
                    return "Error al insertar los productos en la tabla temporal";
                }
            }
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        return rta;
    }

    public List getMoviContable() {
        return moviContable;
    }

    public void setMoviContable(List moviContable) {
        this.moviContable = moviContable;
    }

}
