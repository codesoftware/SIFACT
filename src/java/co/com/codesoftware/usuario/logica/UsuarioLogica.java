/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.usuario.logica;

import co.com.codesoftware.general.persistencia.EnvioFuncion;
import co.com.codesoftware.parametros.ParametrosEntity;
import co.com.codesoftware.usuario.dao.UsuarioDao;
import co.com.codesoftware.usuario.entity.UsuarioEntity;
import java.sql.ResultSet;

/**
 *
 * @author Nicolas
 */
public class UsuarioLogica {

    /**
     * Funcion encargada de Validar si la sede del software en el cual desea
     * facturar es igual a la sede parametrizada para el usuario
     *
     * @param tius_usuario
     * @return
     */
    public String validaSedeUsuaSedeFactura(String tius_usuario) {
        String rta = "";
        try (EnvioFuncion function = new EnvioFuncion()) {
            UsuarioDao objDao = new UsuarioDao();
            objDao.setTius_usuario(tius_usuario);
            objDao.setTius_sede(ParametrosEntity.SEDE);
            ResultSet rs = function.enviarSelect(objDao.validaSedeYFacturacion());
            if (rs.next()) {
                String validaU = rs.getString("valUsua");
                String validaS = rs.getString("sede");
                if ("EXISTE".equalsIgnoreCase(validaU)) {
                    if ("CONCIDE".equalsIgnoreCase(validaS)) {
                        rta = "Ok";
                    } else {
                        rta = "Error el usuario ingresado como  FACTURADOR no coincide con la sede en la cual desea Facturar";
                    }
                } else {
                    rta = "Error usuario ingresado como FACTURADOR inexistente ";
                }
            } else {
                rta = "Error usuario ingresado como FACTURADOR inexistente ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public UsuarioEntity buscaUsuarioXusuario(String tius_usuario) {
        UsuarioEntity usuario = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            UsuarioDao objDao = new UsuarioDao();
            objDao.setTius_usuario(tius_usuario);
            ResultSet rs = function.enviarSelect(objDao.buscaUsuarioXUsuario());
            while (rs.next()) {
                if (usuario == null) {
                    usuario = new UsuarioEntity();
                }
                usuario.setTius_tius(rs.getString("tius_tius"));
                usuario.setTius_pers(rs.getString("tius_pers"));
                usuario.setTius_perf(rs.getString("tius_perf"));
                usuario.setTius_tipo_usuario(rs.getString("tius_tipo_usuario"));
                usuario.setTius_usuario(rs.getString("tius_usuario"));
                usuario.setTius_fecha_registro(rs.getString("tius_fecha_registro"));
                usuario.setTius_ultimo_ingreso(rs.getString("tius_ultimo_ingreso"));
                usuario.setTius_contra_act(rs.getString("tius_contra_act"));
                usuario.setTius_contra_futura(rs.getString("tius_contra_futura"));
                usuario.setTius_cambio_contra(rs.getString("tius_cambio_contra"));
                usuario.setTius_estado(rs.getString("tius_estado"));
                usuario.setTius_sede(rs.getString("tius_sede"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
