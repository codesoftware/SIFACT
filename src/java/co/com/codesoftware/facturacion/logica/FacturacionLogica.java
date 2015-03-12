/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.logica;

import co.com.codesoftware.facturacion.dao.TempProductoFactDao;
import co.com.codesoftware.facturacion.entity.TempProductoFactEntity;
import co.com.codesoftware.general.persistencia.EnvioFuncion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar la logica para la facturacion de productos
 *
 * @author Nicolas
 */
public class FacturacionLogica {

    /**
     * Obtiene el valor de la secuencia co_temp_tran_factu_sec el cual se
     * utilizara como id de la transaccion de facturacion
     *
     * @return
     */
    public String obtieneValorSecuenciaTemp() {
        String sec = "";
        TempProductoFactDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new TempProductoFactDao();
            ResultSet rs = function.enviarSelect(objDao.obtieneSecuenciaTemFact());
            if (rs.next()) {
                sec = rs.getString("secuencia");
            } else {
                sec = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sec = null;
        }
        return sec;
    }

    /**
     * Funcion la cual se encargara de insertar la lista de productos
     * adicionados por el usuario a la tabla temporal de facturacion
     *
     * @param prodFact
     * @return
     */
    public String insertarTemporalProductos(List prodFact, String idTrans) {
        String rta = "";
        try (EnvioFuncion function = new EnvioFuncion()) {
            List<TempProductoFactEntity> objDto = mapeoListaAObjeto(prodFact);
            for (TempProductoFactEntity objAux : objDto) {
                TempProductoFactDao auxDao = new TempProductoFactDao();
                auxDao.setTem_fact_trans(idTrans);
                auxDao.setTem_fact_dska(objAux.getTem_fact_dska());
                auxDao.setTem_fact_cant(objAux.getTem_fact_cant());
                function.enviarSelect(auxDao.insertTemoral());
            }
            rta = "Ok";
        } catch (Exception e) {
            this.borrarTemporalXidTransaccion(idTrans);
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion la cual se encarga apartir de una lista de strings los cuales
     * estan separados por un & obtiene una lista de objetos
     *
     * @param listaObjetos
     * @return
     */
    public List mapeoListaAObjeto(List<String> listaObjetos) {
        List<TempProductoFactEntity> rta = null;
        try {
            for (String cadena : listaObjetos) {
                if (rta == null) {
                    rta = new ArrayList<TempProductoFactEntity>();
                }
                String aux[] = cadena.split("&");
                String auxDska = aux[0];
                String auxCant = aux[1];
                TempProductoFactEntity objAux = new TempProductoFactEntity();
                objAux.setTem_fact_cant(auxCant);
                objAux.setTem_fact_dska(auxDska);
                rta.add(objAux);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion encargada de borrar los datos de la temporal si por algun motivo
     * entra a un error controlado o es necesario eliminar los datos ya
     * insertados por algun error de validacion
     *
     * @param idTrans
     */
    public void borrarTemporalXidTransaccion(String idTrans) {
        try (EnvioFuncion function = new EnvioFuncion()) {
            TempProductoFactDao objDao = new TempProductoFactDao();
            function.enviarUpdate(objDao.borraDatosXIdTrans(idTrans));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public String creaFacturacion(String idTrans){
        String rta = "";
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;                 
    }

}
