/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.inventario.logica;

import co.com.codesoftware.general.persistencia.EnvioFuncion;
import co.com.codesoftware.inventario.dao.ReferenciaDao;
import co.com.codesoftware.inventario.dto.ReferenciaDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Personal
 */
public class ReferenciaLogica {

    public Map<String, String> obtieneReferencias() {
        Map<String, String> rta = new HashMap<String, String>();
        return rta;
    }

    /**
     * Funcion la cual se encarga de realizar la logica para consultar las
     * referencias
     *
     * @param objDTO
     * @return
     */
    public ArrayList<ReferenciaDTO> consultaReferencias(ReferenciaDTO objDTO) {
        ResultSet rs = null;
        ReferenciaDTO aux = null;
        ArrayList<ReferenciaDTO> result = null;
        ReferenciaDao objDAO = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {

            objDAO = poblarDAO(objDTO);
            String filtros = traeFiltros(objDTO);
            rs = funcion.enviarSelect(objDAO.consultaFilros(filtros));
            result = new ArrayList<>();
            while (rs.next()) {
                aux = new ReferenciaDTO();
                aux.setRefe_came(rs.getString("refe_came"));
                aux.setRefe_refe(rs.getString("refe_refe"));
                aux.setRefe_desc(rs.getString("refe_desc"));
                aux.setRefe_memori(rs.getString("refe_memori"));
                aux.setRefe_pantalla(rs.getString("refe_pantalla"));
                aux.setRefe_estado(rs.getString("refe_estado"));
                result.add(aux);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * Funcion encargada de actualizar una referecia especifica
     *
     * @param objDTO
     * @return
     */
    public String actualizaReferenciaEspecifica(ReferenciaDTO objDTO) {

        ReferenciaDao objDAO = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {
            objDAO = new ReferenciaDao();
            objDAO = poblarDAO(objDTO);
            if (funcion.enviarUpdate(objDAO.actualizaReferencia())) {
                return "REFERENCIA ACTUALIZADA CORRECTAMENTE";
            } else {
                return "ERROR AL REALIZAR ACTUALIZACIÓN";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR ACTUALIZANDO REFERENCIA";
        }

    }

    /**
     * Funcion encargada de traer una referencia especifica por el id de la
     * referencia
     *
     * @param objDTO
     * @return
     */
    public ReferenciaDTO traeReferenciaEspecifica(ReferenciaDTO objDTO) {
        ReferenciaDTO result = null;
        ReferenciaDao objDAO = null;
        ResultSet rs = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {
            objDAO = new ReferenciaDao();
            result = new ReferenciaDTO();
            objDAO = poblarDAO(objDTO);
            rs = funcion.enviarSelect(objDAO.consultaEspecificaXId());
            while (rs.next()) {
                result.setRefe_came(rs.getString("refe_came"));
                result.setRefe_refe(rs.getString("refe_refe"));
                result.setRefe_desc(rs.getString("refe_desc"));
                result.setRefe_memori(rs.getString("refe_memori"));
                result.setRefe_pantalla(rs.getString("refe_pantalla"));
                result.setRefe_estado(rs.getString("refe_estado"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String insertarReferencia(ReferenciaDTO objDTO) {
        ReferenciaDao objDAO = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {
            objDAO = poblarDAO(objDTO);
            if (funcion.enviarUpdate(objDAO.insertaReferencia())) {
                return "REFERENCIA INSERTADA CORRECTAMENTE";
            } else {
                return "ERROR AL INSERTAR REFERENCIA";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR INSERTANDO REFERENCIA";
        }

    }

    public ReferenciaDao poblarDAO(ReferenciaDTO objDTO) {
        ReferenciaDao objDAO = new ReferenciaDao();
        objDAO.setRefe_desc(objDTO.getRefe_desc());
        objDAO.setRefe_memori(objDTO.getRefe_memori());
        objDAO.setRefe_came(objDTO.getRefe_came());
        objDAO.setRefe_estado(objDTO.getRefe_estado());
        objDAO.setRefe_pantalla(objDTO.getRefe_pantalla());
        objDAO.setRefe_refe(objDTO.getRefe_refe());
        return objDAO;
    }

    public String traeFiltros(ReferenciaDTO objDTO) {
        String respuesta = "1=1";
        try {
            if (!objDTO.getRefe_estado().equalsIgnoreCase("-1")) {
                respuesta += " AND refe_estado='" + objDTO.getRefe_estado() + "'";
            }
            if (!objDTO.getRefe_came().equalsIgnoreCase("-1")) {
                if (objDTO.getRefe_came().equalsIgnoreCase("8")) {
                    respuesta += " AND refe_came <=8 ";
                } else if (objDTO.getRefe_came().equalsIgnoreCase("13")) {
                    respuesta += " AND (refe_came >8 AND refe_came <= 13)  ";
                } else {
                    respuesta += " AND refe_came > 13 ";
                }

            }

            if (!objDTO.getRefe_memori().equalsIgnoreCase("-1")) {
                if (objDTO.getRefe_memori().equalsIgnoreCase("16")) {
                    respuesta += " AND refe_memori <= 16 ";
                } else if (objDTO.getRefe_memori().equalsIgnoreCase("16")) {
                    respuesta += " AND (refe_came > 16 AND refe_came <= 32 )  ";
                } else {
                    respuesta += " AND refe_came > 32 ";
                }

            }

            if (!objDTO.getRefe_memori().equalsIgnoreCase("-1")) {
                if (objDTO.getRefe_memori().equalsIgnoreCase("16")) {
                    respuesta += " AND refe_memori <= 16 ";
                } else if (objDTO.getRefe_memori().equalsIgnoreCase("16")) {
                    respuesta += " AND (refe_memori > 16 AND refe_memori <= 32 )  ";
                } else {
                    respuesta += " AND refe_memori > 32 ";
                }

            }

            if (!objDTO.getRefe_pantalla().equalsIgnoreCase("-1")) {
                if (objDTO.getRefe_pantalla().equalsIgnoreCase("4")) {
                    respuesta += " AND refe_memori <= 4 ";
                } else {
                    respuesta += " AND refe_memori > 4 ";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion la cual contiene la logica necesaria para obtener los id's y las
     * descripciones de las referencias parametrizadas en el sistema
     *
     * @return
     */
    public Map<String, String> obtieneIdDescrReferenciaActivos() {
        Map<String, String> rta = null;
        ReferenciaDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new ReferenciaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralActivos());
            while (rs.next()) {
                if (rta == null) {
                    rta = new LinkedHashMap<>();
                }
                rta.put(rs.getString("refe_refe"), rs.getString("refe_desc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
