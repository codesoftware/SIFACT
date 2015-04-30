/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.inventario.logica;

import co.com.codesoftware.general.persistencia.EnvioFuncion;
import co.com.codesoftware.inventario.dao.CategoriaDao;
import co.com.codesoftware.inventario.dto.CategoriaDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de realizar la logica que implique la tabla in_tcate
 *
 * @author nicolas
 * @version 1.0
 */
public class CategoriaLogica {

    /**
     * Funcion la cual retorna una Array de objetos Categoria
     *
     * @return List de Categorias
     */
    public List<CategoriaDto> obtieneCategoriasActivas() {
        List<CategoriaDto> rta = null;
        CategoriaDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new CategoriaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralActivos());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<CategoriaDto>();
                }
                CategoriaDto aux = new CategoriaDto();
                aux.setCate_cate(rs.getString("cate_cate"));
                aux.setCate_desc(rs.getString("cate_desc"));
                aux.setCate_estado(rs.getString("cate_estado"));
                aux.setCate_runic(rs.getString("cate_runic"));
                aux.setCate_feven(rs.getString("cate_feven"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion la cual retorna una categoria en especifico la cual obtine con el
     * id de la categoria
     *
     * @param cate_cate String llave primaria de la tabla in_tcate
     * @return Objeto con la categoria
     */
    public CategoriaDto obtieneCategoriasXId(String cate_cate) {
        CategoriaDto aux = null;
        CategoriaDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new CategoriaDao();
            objDao.setCate_cate(cate_cate);
            ResultSet rs = function.enviarSelect(objDao.consultaEspecificaXId());
            while (rs.next()) {
                aux = new CategoriaDto();
                aux.setCate_cate(rs.getString("cate_cate"));
                aux.setCate_desc(rs.getString("cate_desc"));
                aux.setCate_estado(rs.getString("cate_estado"));
                aux.setCate_runic(rs.getString("cate_runic"));
                aux.setCate_feven(rs.getString("cate_feven"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    /**
     * Función que me consulta las categorías por los filtros
     *
     * @param objDTO
     * @return
     */
    public ArrayList<CategoriaDto> consultaCategorias(CategoriaDto objDTO) {
        ResultSet rs = null;
        CategoriaDto aux = null;
        ArrayList<CategoriaDto> result = null;
        CategoriaDao objDAO = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {
            objDAO = poblarDAO(objDTO);
            String filtros = traeFiltros(objDTO);
            rs = funcion.enviarSelect(objDAO.consultaFiltros(filtros));
            result = new ArrayList<>();
            while (rs.next()) {
                aux = new CategoriaDto();
                aux.setCate_cate(rs.getString("cate_cate"));
                aux.setCate_desc(rs.getString("cate_desc"));
                aux.setCate_estado(rs.getString("cate_estado"));
                aux.setCate_runic(rs.getString("cate_runic"));
                aux.setCate_feven(rs.getString("cate_feven"));
                result.add(aux);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public String actualizaCategoria(CategoriaDto objDTO) {
        CategoriaDao objDAO = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {
            objDAO = new CategoriaDao();
            objDAO = poblarDAO(objDTO);
            if (funcion.enviarUpdate(objDAO.actualizaCategoria())) {
                return "CATEGORIA ACTUALIZADA CORRECTAMENTE";
            } else {
                return "ERROR AL REALIZAR ACTUALIZACIÓN";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR ACTUALIZANDO CATEGORIA";
        }
    }

    public String insertarCategoria(CategoriaDto objDTO) {
        CategoriaDao objDAO = null;
        try (EnvioFuncion funcion = new EnvioFuncion()) {
            objDAO = poblarDAO(objDTO);
            if (funcion.enviarUpdate(objDAO.insertaCategoria())) {
                return "CATEGORIA INSERTADA CORRECTAMENTE";
            } else {
                return "ERROR AL INSERTAR CATEGORIA";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR INSERTANDO CATEGORIA";
        }

    }

    public CategoriaDao poblarDAO(CategoriaDto objDTO) {
        CategoriaDao objDAO = new CategoriaDao();
        objDAO.setCate_cate(objDTO.getCate_cate());
        objDAO.setCate_desc(objDTO.getCate_desc());
        objDAO.setCate_estado(objDTO.getCate_estado());
        objDAO.setCate_feven(objDTO.getCate_feven());
        objDAO.setCate_runic(objDTO.getCate_runic());
        return objDAO;
    }

    public String traeFiltros(CategoriaDto objDTO) {
        String respuesta = "1=1";
        try {
            if (!objDTO.getCate_estado().equalsIgnoreCase("-1")) {
                respuesta += " AND cate_estado='" + objDTO.getCate_estado() + "'";
            }
            if (!objDTO.getCate_runic().equalsIgnoreCase("-1")) {
                respuesta += " AND cate_runic='" + objDTO.getCate_runic() + "'";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener las categorias
     * parametrizadas y activas en la aplicacion
     *
     * @return
     */
    public Map<String, String> obtieneCategoriasActivasMap() {
        Map<String, String> rta = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            CategoriaDao objDao = new CategoriaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaCategoriasActivas());
            while (rs.next()) {
                if (rta == null) {
                    rta = new HashMap<String, String>();
                }
                rta.put(rs.getString("cate_cate"), rs.getString("cate_desc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
