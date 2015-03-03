/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.logica;

import co.com.codesoftware.facturacion.dao.ClienteDao;
import co.com.codesoftware.facturacion.entity.ClienteEntity;
import co.com.codesoftware.general.persistencia.EnvioFuncion;
import java.sql.ResultSet;

/**
 * Clase encargada de realizar la logica necesaria para el cliente
 *
 * @author Personal
 */
public class ClienteLogica {

    /**
     * Funcion encargada de realizar la logica para obtener los datos de un
     * cliente basandose en el numero de la cedula
     *
     * @param objDto
     * @return
     */
    public ClienteEntity obtenerclienteXCeduala(ClienteEntity objDto) {
        ClienteEntity rta = null;
        ClienteDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = poblarDao(objDto);
            ResultSet rs = function.enviarSelect(objDao.consultarClienteXCedula());
            if (rs.next()) {
                rta = new ClienteEntity();
                rta.setClien_clien(rs.getString("clien_clien"));
                rta.setClien_cedula(rs.getString("clien_cedula"));
                rta.setClien_nombres(rs.getString("clien_nombres"));
                rta.setClien_apellidos(rs.getString("clien_apellidos"));
                rta.setClien_telefono(rs.getString("clien_telefono"));
                rta.setClien_correo(rs.getString("clien_correo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica necesaria para ingresar un
     * cliente a la base de datos
     *
     * @param cliente
     * @return
     */
    public String insertaCliente(ClienteEntity cliente) {
        try (EnvioFuncion function = new EnvioFuncion();) {
            function.adicionarNombre("US_FINSERT_CLIENTE");
            String param = "";
            param = function.adicionarParametro(cliente.getClien_nombres());
            param = function.adicionarParametro(cliente.getClien_apellidos());
            param = function.adicionarNumeric(cliente.getClien_cedula());
            if (cliente.getClien_correo() == null) {
                cliente.setClien_correo("SIN INFO");
            }
            param = function.adicionarParametro(cliente.getClien_correo());
            if (cliente.getClien_telefono() == null) {
                cliente.setClien_telefono("SIN INFO");
            }
            param = function.adicionarParametro(cliente.getClien_telefono());
            String rtaPg = function.llamarFunction(function.getSql());
            function.recuperarString();
            function.cerrarConexion();
            String[] rtaVector = rtaPg.split("-");
            // Este mensaje lo envia la funcion que envia la funcion de java que
            // confirma que el llamado de a la funcion fue exitiso
            if (rtaVector[1].equalsIgnoreCase("Ok")) {
                //Esta es la respuesta que retorna postgres
                String rtaFunc = function.getRespuesta();
                String[] auxRta = rtaFunc.split("-");
                if ((auxRta[0].trim()).equalsIgnoreCase("OK")) {
                    return auxRta[0];
                } else {
                    return "Error";
                }
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    /**
     * Funcion encargada de poblar un objeto Entity a un objeto Dao
     *
     * @param objDto
     * @return
     */
    public ClienteDao poblarDao(ClienteEntity objDto) {
        ClienteDao objDao = new ClienteDao();
        try {
            objDao.setClien_clien(objDto.getClien_clien());
            objDao.setClien_cedula(objDto.getClien_cedula());
            objDao.setClien_nombres(objDto.getClien_nombres());
            objDao.setClien_apellidos(objDto.getClien_apellidos());
            objDao.setClien_telefono(objDto.getClien_telefono());
            objDao.setClien_correo(objDto.getClien_correo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDao;
    }

}
