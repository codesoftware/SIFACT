/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.logica;

import co.com.codesoftware.facturacion.dao.RemisionDao;
import co.com.codesoftware.facturacion.entity.RemisionEntity;
import co.com.codesoftware.general.persistencia.EnvioFuncion;
import co.com.codesoftware.producto.dao.ProductoDao;
import co.com.codesoftware.producto.entity.Producto;
import co.com.codesoftware.utilidades.Utilidades;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nicolas
 */
public class RemisionLogica {

    public String consultaRemisionXId(String rmce_codigo) {
        String rta = "";
        RemisionEntity rev = null;

        try (EnvioFuncion funcion = new EnvioFuncion()) {
            RemisionDao objDao = new RemisionDao();
            objDao.setRmce_codigo(rmce_codigo);
            ResultSet rs = funcion.enviarSelect(objDao.consultaEspecificaXCodigo());
            if (rs.next()) {
                rev = new RemisionEntity();
                rev.setRmce_rmce(rs.getString("rmce_rmce"));
                rev.setRmce_refe(rs.getString("rmce_refe"));
                rev.setRmce_imei(rs.getString("rmce_imei"));
                rev.setRmce_iccid(rs.getString("rmce_iccid"));
                rev.setRmce_valor(rs.getString("rmce_valor"));
                rev.setRmce_comision(rs.getString("rmce_comision"));
                rev.setRmce_tppl(rs.getString("rmce_tppl"));
                rev.setRmce_fcve(rs.getString("rmce_fcve"));
                rev.setRmce_fcsl(rs.getString("rmce_fcsl"));
                rev.setRmce_fcen(rs.getString("rmce_fcen"));
                rev.setRmce_tius_ent(rs.getString("rmce_tius_ent"));
                rev.setRmce_tius_sal(rs.getString("rmce_tius_sal"));
                rev.setRmce_codigo(rs.getString("rmce_codigo"));
                rev.setRmce_sede(rs.getString("rmce_sede"));
                rev.setRmce_estado(rs.getString("rmce_estado"));
                rev.setRmce_pagado(rs.getString("rmce_pagado"));
            }
            Utilidades utilidades = new Utilidades();
            rta = utilidades.convertirObjetoJSON(rev);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public String insertaRemision(String rmce_rmce) {
        String rta = "";
        Map<String, Object> respuesta = null;
        respuesta = new HashMap<String, Object>();
        Gson gson = new Gson();
        RemisionDao objDAO = new RemisionDao();
        RemisionEntity rev = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {
            objDAO.setRmce_rmce(rmce_rmce);
            ResultSet rs = funcion.enviarSelect(objDAO.consultaRemision());
            while (rs.next()) {
                rev = new RemisionEntity();
                rev.setRmce_codigo(rs.getString("rmce_codigo"));
                rev.setRmce_valor(rs.getString("rmce_valor"));
                rev.setRmce_tppl(rs.getString("plan"));
                rev.setRmce_fcve(rs.getString("rmce_fcve"));
                rev.setRmce_comision(rs.getString("comision"));
                rev.setRmce_rmce(rs.getString("rmce_rmce"));
                rev.setValorSinFiltros(rs.getString("rmce_valorsf"));
            }
            Utilidades utilidades = new Utilidades();
            rta = utilidades.convertirObjetoJSON(rev);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para buscar una remision por
     * medio del codigo
     *
     * @param rmce_codigo parametro de busqueda 
     * @return Objeto entity Remision
     */
    public String consultaRemisionXImei(String rmce_imei) {
        RemisionEntity objDto = null;
        String objJson = "";
        try (EnvioFuncion function = new EnvioFuncion()) {
            RemisionDao objDao = new RemisionDao();
            objDao.setRmce_imei(rmce_imei);
            ResultSet rs = function.enviarSelect(objDao.buscaRemisionXImei());
            if (rs.next()) {
                if (objDto == null) {
                    objDto = new RemisionEntity();
                }
                objDto.setRmce_rmce(rs.getString("rmce_rmce"));
                objDto.setRmce_refe(rs.getString("rmce_refe"));
                objDto.setRmce_imei(rs.getString("rmce_imei"));
                objDto.setRmce_iccid(rs.getString("rmce_iccid"));
                objDto.setRmce_valor(rs.getString("rmce_valor"));
                objDto.setRmce_comision(rs.getString("rmce_comision"));
                objDto.setRmce_tppl(rs.getString("rmce_tppl"));
                objDto.setRmce_fcve(rs.getString("rmce_fcve"));
                objDto.setRmce_fcsl(rs.getString("rmce_fcsl"));
                objDto.setRmce_fcen(rs.getString("rmce_fcen"));
                objDto.setRmce_tius_ent(rs.getString("rmce_tius_ent"));
                objDto.setRmce_tius_sal(rs.getString("rmce_tius_sal"));
                objDto.setRmce_codigo(rs.getString("rmce_codigo"));
                objDto.setRmce_sede(rs.getString("rmce_sede"));
                objDto.setRmce_estado(rs.getString("rmce_estado"));
                objDto.setRmce_pagado(rs.getString("rmce_pagado"));
                objDto.setRmce_comdev(rs.getString("rmce_comdev"));
                objDto.setRmce_trans(rs.getString("rmce_trans"));
            }
            Utilidades utilidades = new Utilidades();
            objJson = utilidades.convertirObjetoJSON(objDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objJson;
    }
    /**
     * 
     * @param remisiones
     * @param tius_tius
     * @param clien_clien
     * @param idTrans
     * @return 
     */
    public String generaRemision(List remisiones, String tius_tius, String clien_clien, String idTrans){
        String rta = "";
        try(EnvioFuncion function = new EnvioFuncion()) {
            RemisionDao objDao = new RemisionDao();
            objDao.setRmce_trans(idTrans);
            objDao.setRmce_clien(clien_clien);
            objDao.setRmce_tius_sal(tius_tius);
            for(Object obj : remisiones){
                String rmce_rmce = (String) obj;
                objDao.setRmce_rmce(rmce_rmce);
                function.enviarUpdate(objDao.generaRemision());                
            }
            rta="Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta= "Error " + e;
        }
        return rta;
    }

}
