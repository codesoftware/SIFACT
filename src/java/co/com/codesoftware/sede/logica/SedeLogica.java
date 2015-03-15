/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.sede.logica;

import co.com.codesoftware.general.persistencia.EnvioFuncion;
import java.sql.ResultSet;

/**
 *
 * @author Nicolas
 */
public class SedeLogica {

    /**
     * Funcion encargada de realizar la logica para buscar el nombre de una sede
     * teniendo como referencia el Id de la tabla em_tsede
     *
     * @param sede_sede
     * @return
     */
    public String buscaNombreSedeXId(String sede_sede) {
        String rta = "";
        try (EnvioFuncion function = new EnvioFuncion()) {
            SedeDao objDao = new SedeDao();
            objDao.setSede_sede(sede_sede);
            ResultSet rs = function.enviarSelect(objDao.buscaSedeXId());
            if (rs.next()) {
                rta = rs.getString("sede_nombre");
            } else {
                rta = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
