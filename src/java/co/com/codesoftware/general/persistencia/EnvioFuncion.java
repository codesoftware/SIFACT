/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.general.persistencia;

import java.sql.ResultSet;

/**
 *
 * @author Personal
 */
public class EnvioFuncion  implements AutoCloseable{
       private String nameFunction;
    private String respuesta;
    // Atributo el cual contiene la respuesta cuando postgres retorna un vector
    private String[] respuestaVector;
    private Conexion con;
    private String msnError;
    private String sql;
    private ResultSet rtaSql;
    private String parametrosIn;

    /**
     * Constructor de la clase
     *
     * @since 1.0.0
     */
    public EnvioFuncion()  {
        this.con = Conexion.getInstance();
        this.nameFunction = "";
        this.respuesta = "";
        this.msnError = "Llamado exitoso - Ok";
        this.sql = "SELECT ";
        this.parametrosIn = "";
    }

    /**
     * Función con la cual adiciono los parametros necesarios para el llamado de
     * la función
     *
     * @param String parametro: Es el dato que se quiere enviar a la base de
     * datos
     */
    public String adicionarNumeric(String numero) {
        this.parametrosIn = parametrosIn + "," + numero;
        return "Parametro adicionado - Ok";
    }

    /**
     * Función con la cual adiciono los parametros necesarios para el llamado de
     * la función
     *
     * @param String parametro: Es el dato que se quiere enviar a la base de
     * datos
     */
    public String adicionarParametro(String parametro) {
        this.parametrosIn = parametrosIn + ",'" + parametro + "'";
        return "Parametro adicionado - Ok";
    }

    /**
     * Función con la cual adiciono parametros nulos a los parametros que se
     * enviaran a la funcion de base de datos
     *
     * @return String confirmacion de la operación
     */
    public String adicionarNull() {
        this.parametrosIn = parametrosIn + ",null";
        return "Parametro adicionado - Ok";
    }

    /**
     * Funcion la cual se encargara de ingresar un parametro de fecha le
     * adicionara el formato to_date() de sql
     *
     * @param String date fecha la cual se enviara a la base de datos
     * @return Respuesta de confirmacion de que la operacion fue exitosa
     */
    public String addicionarParametroDate(String date) {
        this.parametrosIn = parametrosIn + ",to_date('" + date + "','dd/mm/yyyy')";
        return "Parametro adicionado - Ok";

    }

    /**
     * Función la cual adiciona el nombre de la funcion la cual se va a llamar a
     * la base de datos
     *
     * @param String nombre: Nombre de la funcion la cual se va ha llamar
     * @return String msg: Mensaje indicando que la operacion fue exitosa
     */
    public String adicionarNombre(String nombre) {
        this.nameFunction = nombre;
        return "Parametro adicionado - Ok";
    }

    /**
     * Funcion la cual envia la sentencia sql a la clase Conexion en la cual
     * sera enviada a la base de datos.
     *
     * @param String sql: sentencia sql la cual se va ha enviar a la base de
     * datos
     * @return String Mensaje de respuesta de la funcion.
     * @since 1.0.0
     */
    public String llamarFunction(String sql) {
        String valida = con.conexion();
        if (valida.equalsIgnoreCase("OK") && !this.nameFunction.isEmpty() && !this.parametrosIn.isEmpty()) {
            parametrosIn = parametrosIn.replaceFirst(",", "");
            this.sql = this.sql + this.nameFunction + "(" + parametrosIn + ")";
            this.rtaSql = this.con.queryConRetorno(this.sql);
            return "Llamado Exitoso-Ok";
        } else {
            return "Error al llamar función - Err";
        }
    }
    /**
     * Funcion la cual envia la sentencia sql a la clase Conexion en la cual
     * sera enviada a la base de datos y le da un formato de pesos
     * @param sql
     * @return 
     */
    public String llamarFunctionFormatoPesos(String sql) {
        String valida = con.conexion();
        if (valida.equalsIgnoreCase("OK") && !this.nameFunction.isEmpty() && !this.parametrosIn.isEmpty()) {
            parametrosIn = parametrosIn.replaceFirst(",", "");
            this.sql = this.sql + "to_char(" +this.nameFunction + "(" + parametrosIn + "),'LFM9,999,999.99')";
            this.rtaSql = this.con.queryConRetorno(this.sql);
            return "Llamado Exitoso-Ok";
        } else {
            return "Error al llamar función - Err";
        }
    }

    /**
     * Funcion la cual se encarga de cerrar la conexion con la base de datos
     */
    public void cerrarConexion() {
        try {
            con.cerrarConexion();
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexión " + e);
        }

    }

    /**
     * Funcion la cual recupera los datos devueltos por la base de datos y los
     * envia a la funcion leerResultSet() para que lo convierta en un String
     *
     * @since 1.0.0
     */
    public void recuperarString() {
        if (this.rtaSql != null) {
            leerResultSet();
        }
    }

    /**
     * Funcion la cual lee el resulset y convierte el retorno de la sentencia en
     * un solo String
     *
     */
    private void leerResultSet() {
        String res = "";
        try {
            while (this.rtaSql.next()) {
                res = this.rtaSql.getString(1);
            }
            if (res != null) {
                res = res.replace("(", "");
                res = res.replace(")", "");
            }
            this.respuesta = res;
        } catch (Exception e) {
            this.respuesta = null;
        }
    }

  

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String[] getRespuestaVector() {
        return respuestaVector;
    }

    public void setRespuestaVector(String[] respuestaVector) {
        this.respuestaVector = respuestaVector;
    }

    public ResultSet enviarSelect(String select) {
        ResultSet rs = null;
        try {
            String valida = con.conexion();
            if (valida.equalsIgnoreCase("OK") ) {
                rs = this.con.queryConRetorno(select);
            }
        } catch (Exception e) {
            System.out.println("error EnvioFunction.enviarSelect : " + e);
            return null;
        }
        return rs;
    }
    
    public boolean enviarUpdate(String select) {
        boolean rta = false;
        try {
            String valida = con.conexion();
            if (valida.equalsIgnoreCase("OK") ) {
                boolean envio = this.con.querySinRetorno(select);
                if(!envio){
                    rta = false;
                }else{
                    rta = true;
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return rta;
    }
    /*Sobreescribe metodo de la interfaz Autocloseable 
    */

    public void close() throws Exception {
         try {
            con.cerrarConexion();
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexión " + e);
        }
    }
    
}
