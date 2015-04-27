/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.logica;

import co.com.codesoftware.facturacion.dao.TempProductoFactDao;
import co.com.codesoftware.facturacion.entity.PagoEntity;
import co.com.codesoftware.facturacion.entity.TempProductoFactEntity;
import co.com.codesoftware.general.persistencia.EnvioFuncion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

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

    /**
     * Funcion encargada de realizar la facturacion
     *
     * @param idTrans
     * @return
     */
    public String creaFacturacion(String idTrans, String usuario, String cliente, PagoEntity pago, String sede_sede) {
        String rta = "";
        try (EnvioFuncion function = new EnvioFuncion()) {
            function.adicionarNombre("FA_CREA_FACTURA_COMPLETO");
            function.adicionarNumeric(usuario);
            function.adicionarNumeric(cliente);
            function.adicionarNumeric(idTrans);
            function.adicionarNumeric(sede_sede);
            function.adicionarParametro(pago.getTipoPago());
            if(pago.getIdVucher() == null || "".equalsIgnoreCase(pago.getIdVucher())){
                function.adicionarNull();
            }else{
                function.adicionarNumeric(pago.getIdVucher());
            }
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    String rtaPg = function.getRespuesta();
                    return rtaPg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public String generarFactura(String fact_fact, String ruta, String rutaDestino) {
        String rta = "Ok";

        Connection conn = null;
        try{
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("fact_fact", fact_fact);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            JasperExportManager.exportReportToPdfFile(print, rutaDestino);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rta;
    }
    
    public String generarRemision(String rmce_trans, String ruta, String rutaDestino) {
        String rta = "Ok";

        Connection conn = null;
        try{
            conn = this.generarConexion();
            String ubicacionReporte = ruta;
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("rmce_trans", rmce_trans);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ubicacionReporte);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, properties, conn);
            JasperExportManager.exportReportToPdfFile(print, rutaDestino);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rta;
    }

    public Connection generarConexion() {
        Connection con = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("co.com.codesoftware.archivos.BASECONFIG");
            String host = rb.getString("HOST").trim();
            String user = rb.getString("USER").trim();
            String pass = rb.getString("PASSWORD").trim();
            String db = rb.getString("DATABASE").trim();
            String port = rb.getString("PUERTO").trim();
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error al realizar la conexion...");
            e.printStackTrace();
        }
        return con;
    }
    
    /**
     * Obtiene el valor de la secuencia in_tsec_trans_rmce el cual se
     * utilizara como id de la transaccion de facturacion
     *
     * @return
     */
    public String obtieneValorSecuenciaTempRemison() {
        String sec = "";
        TempProductoFactDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new TempProductoFactDao();
            ResultSet rs = function.enviarSelect(objDao.obtieneSecuenciaTemRemision());
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

}
