/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.contabilidad.logica;

import co.com.codesoftware.contabilidad.dao.ContabilidadDao;
import co.com.codesoftware.contabilidad.dto.MoviContableDto;
import co.com.codesoftware.facturacion.entity.ProductosFactEntitiy;
import co.com.codesoftware.general.persistencia.EnvioFuncion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class ContabilidadLogica {

    private String idTrans;

    /**
     * Funcion encargada de realizar la logica para la simulacion de los
     * asientos contables
     *
     * @param productos
     * @param sede
     * @param tipoPago
     * @param vlrTarjeta
     * @return
     */
    public String generaSimulacionAsientoContable(ArrayList<ProductosFactEntitiy> productos, String sede, String tipoPago, String vlrTarjeta) {
        String rta = null;
        try {
            String sec = this.obtieneSecuenciaTemporalSim();
            String valida = "";
            if (sec != null) {
                valida = this.insertaProductosTemporal(productos, sec);
                if ("Ok".equalsIgnoreCase(valida)) {
                    valida = this.generaSimulacionParaMoviContables(sec, sede, tipoPago, vlrTarjeta);
                    String []aux = valida.split("-");
                    if (!"Ok".equalsIgnoreCase(aux[0])) {
                        return valida;
                    } else {
                        this.idTrans = aux[1];
                        return "Ok";
                    }
                } else {
                    rta = valida;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener la secuencia o el id
     * de transaccion para la simulacion de asientos contables
     *
     * @return
     */
    private String obtieneSecuenciaTemporalSim() {
        String sec = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            ContabilidadDao objDao = new ContabilidadDao();
            ResultSet rs = function.enviarSelect(objDao.obtieneDatoSecuenciaTempoSim());
            if (rs.next()) {
                sec = rs.getString("secuencia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sec;
    }

    /**
     * Funcion encargada de realizar la logica para insertar en la tabla
     * temporal para saber que productos esta comprando
     *
     * @param productos
     * @param sec
     * @return
     */
    private String insertaProductosTemporal(ArrayList<ProductosFactEntitiy> productos, String sec) {
        String rta = "Ok";
        try (EnvioFuncion function = new EnvioFuncion()) {
            ContabilidadDao objDao = new ContabilidadDao();
            for (ProductosFactEntitiy producto : productos) {
                boolean valida = function.enviarUpdate(objDao.insertTemoralProductos(producto, sec));
                if (!valida) {
                    return "Error al insertar los productos en la tabla temporal";
                }
            }
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        return rta;
    }

    public String generaSimulacionParaMoviContables(String idTrans, String sede, String tipoPago, String valrTarjeta) {
        String rta = "";
        try (EnvioFuncion function = new EnvioFuncion()) {
            function.adicionarNombre("SIMULACION_MVTO_CONTABLES");
            function.adicionarNumeric(idTrans);
            function.adicionarNumeric(sede);
            function.adicionarParametro(tipoPago);
            function.adicionarNumeric(valrTarjeta);
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
                } else {
                    return rtaVector[1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener la informacion
     * necesaria para un asiento contable
     *
     * @param simc_trans String id de la transaccion
     * @return
     */
    public List<MoviContableDto> recuperaAsientoContableTemporal(String simc_trans) {
        List<MoviContableDto> rta = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            ContabilidadDao objDao = new ContabilidadDao();
            ResultSet rs = function.enviarSelect(objDao.generaAsientoContable(simc_trans));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                MoviContableDto aux = new MoviContableDto();
                aux.setSbcu_nombre(rs.getString("sbcu_nombre"));
                aux.setSbcu_codigo(rs.getString("sbcu_codigo"));
                aux.setDebitos(rs.getString("debitos"));
                aux.setCreditos(rs.getString("creditos"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

}
