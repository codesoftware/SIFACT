/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.general.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase la cual se conecta con la base de datos y la cual lanza los Scripts y
 * retorna las respuestas que devuelve la base de datos
 *
 * @author john
 * @version 1.0.0
 * @since 1.0.0
 */
public class Conexion {

    private static Conexion conexion;
    private static Connection con;
    private static String host;
    private static String port;
    private static String user;
    private static String pass;
    private static String db;

    private Conexion() {
        ResourceBundle rb = ResourceBundle.getBundle("co.com.codesoftware.filesconfig.BASECONFIG");
        this.host = rb.getString("HOST").trim();
        this.user = rb.getString("USER").trim();
        this.pass = rb.getString("PASSWORD").trim();
        this.db = rb.getString("DATABASE").trim();
        this.port = rb.getString("PUERTO").trim();
    }

    public static Conexion getInstance() {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }

    /**
     * Funcion encargada de cerrar la session de la base de datos
     */
    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println("Error Conexion.cerrarConexion al cerrar la conexion" + e);
        }
    }

    /**
     * Función la cual se conecta a la base de datos
     *
     * @return String mensaje el cual nos indica si fue exitosa la conexion con
     * la base de datos
     */
    public String conexion() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.db;
            con = DriverManager.getConnection(url, this.user, this.pass);
            return "OK";
        } catch (Exception e) {
            System.out.println("Error al realizar la conexion...");
            e.printStackTrace();
            return "ERR";
        }
    }


    /**
     * Función la cual ejecuta la sentencia sql enviada y retorna un result set
     * con la informacion solicitada
     *
     * @param sql
     * @return
     */
    public ResultSet queryConRetorno(String sql) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error conexion.queryConRetorno " + ex);
            }
        }
        return rs;
    }

    public boolean querySinRetorno(String sql) {
        boolean rta = false;
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            rta = true;
        } catch (SQLException e) {
            e.printStackTrace();
            rta = false;
        }
        return rta;
    }
    
}
