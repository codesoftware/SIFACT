/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.datos;

import co.com.codesoftware.acciones.EjecutarFunciones;

/**
 *
 * @author Personal
 */
public class ConfirmaDatos {

    public RetornoLoginDto confimaUsuario(String usuario, String contra) {
        RetornoLoginDto retorno = new RetornoLoginDto();
        try (EjecutarFunciones function = new EjecutarFunciones()) {
            function.adicionarNombre("US_FAUTENTICAR_USUA");
            function.adicionarParametro(usuario);
            function.adicionarParametro(contra);
            String rta = "";
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            function.cerrarConexion();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitoso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // aqui verifico si es true or false
                    String[] rtaPg = function.getRespuesta().split(",");
                    if (rtaPg[1].equalsIgnoreCase("Acceso_aprobado")) {
                        retorno.setAcceso(true);
                        if (rtaPg[0].equalsIgnoreCase("UPD")) {
                            //indica que si debe redireccionar al usuario a la pagina para hacer el cambio de contraseña
                            retorno.setUpdate("SI");
                        } else {
                            retorno.setUpdate("NO");
                        }   
                    } 

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

}
