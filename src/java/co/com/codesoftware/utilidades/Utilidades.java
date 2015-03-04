/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.utilidades;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Personal
 */
public class Utilidades {

    public String convertirObjetoJSON(Object objeto) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        String stringJSON = "";
        try {
            Gson objJSON = new Gson();
            if (objeto == null) {
                respuesta.put("respuesta", "ERROR OBJETO NULO");
            }else{
                respuesta.put("respuesta", "OK");
                respuesta.put("objeto", objeto);
            }
            stringJSON = objJSON.toJson(respuesta);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringJSON;
    }
}
