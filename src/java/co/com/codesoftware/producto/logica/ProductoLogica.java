/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.producto.logica;

import co.com.codesoftware.facturacion.entity.CalculoProdEntity;
import co.com.codesoftware.general.persistencia.EnvioFuncion;
import co.com.codesoftware.producto.dao.ProductoDao;
import co.com.codesoftware.producto.entity.Producto;
import co.com.codesoftware.utilidades.Utilidades;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de realizar la logica de los productos
 *
 * @author Personal
 */
public class ProductoLogica {

    /**
     * Funcion encargada de realizar la busqueda de productos por su
     * Identificador
     *
     * @param dska_dska
     * @return
     */
    public String buscaProdXIdProducto(String dska_dska, String sede_sede) {
        String rta = "";
        Producto prd = null;
        ProductoDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new ProductoDao();
            objDao.setDska_dska(dska_dska);
            ResultSet rs = function.enviarSelect(objDao.traeProductoIdentifi());
            if (rs.next()) {
                prd = new Producto();
                prd.setDska_cod(rs.getString("dska_cod"));
                prd.setDska_desc(rs.getString("dska_desc"));
                prd.setDska_cate(rs.getString("dska_cate"));
                prd.setDska_dska(rs.getString("dska_dska"));
                prd.setDska_estado(rs.getString("dska_estado"));
                prd.setDska_fec_ingreso(rs.getString("dska_fec_ingreso"));
                prd.setDska_iva(rs.getString("dska_iva"));
                prd.setDska_marca(rs.getString("dska_marca"));
                prd.setDska_nom_prod(rs.getString("dska_nom_prod"));
                prd.setDska_porc_iva(rs.getString("dska_porc_iva"));
                prd.setDska_refe(rs.getString("dska_refe"));
                prd.setPrecio(obtienePrecioProductoXId(prd.getDska_dska(), sede_sede));
                //Obtengo la cantidad existente de productos en la sede
                prd.setCantExis(obtenerExistenciasPorSede(prd.getDska_dska(), sede_sede));
            }
            Utilidades utilidades = new Utilidades();
            rta = utilidades.convertirObjetoJSON(prd);
        } catch (Exception e) {
            e.printStackTrace();
            prd = null;
        }
        return rta;
    }

    /**
     * Funcion encargada de obtener los datos del producto por medio del codigo
     * de barras
     *
     * @param dska_cod
     * @return
     */
    public String buscaProductoXCodigoBarras(String dska_cod, String sede_sede) {
        String rta = "";
        Producto prd = null;
        ProductoDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new ProductoDao();
            objDao.setDska_cod(dska_cod);
            ResultSet rs = function.enviarSelect(objDao.traeProductoCodigo());
            if (rs.next()) {
                prd = new Producto();
                prd.setDska_cod(rs.getString("dska_cod"));
                prd.setDska_desc(rs.getString("dska_desc"));
                prd.setDska_cate(rs.getString("dska_cate"));
                prd.setDska_dska(rs.getString("dska_dska"));
                prd.setDska_estado(rs.getString("dska_estado"));
                prd.setDska_fec_ingreso(rs.getString("dska_fec_ingreso"));
                prd.setDska_iva(rs.getString("dska_iva"));
                prd.setDska_marca(rs.getString("dska_marca"));
                prd.setDska_nom_prod(rs.getString("dska_nom_prod"));
                prd.setDska_porc_iva(rs.getString("dska_porc_iva"));
                prd.setDska_refe(rs.getString("dska_refe"));
                prd.setPrecio(obtienePrecioProductoXId(prd.getDska_dska(), sede_sede));
                //Obtengo la cantidad existente de productos en la sede
                prd.setCantExis(obtenerExistenciasPorSede(prd.getDska_dska(), sede_sede));
            }
            Utilidades utilidades = new Utilidades();
            rta = utilidades.convertirObjetoJSON(prd);
        } catch (Exception e) {
            e.printStackTrace();
            prd = null;
        }
        return rta;
    }

    /**
     * Funcion la cual se encarga de realizar la logica para obtener el precio
     * de un producto basandose en el id del producto
     *
     * @param dska_dska
     * @return
     */
    public String obtienePrecioProductoXId(String dska_dska, String sede_sede) {
        String precio = "";
        try (EnvioFuncion function = new EnvioFuncion()) {
            ProductoDao objDao = new ProductoDao();
            objDao.setDska_dska(dska_dska);
            objDao.setSede(sede_sede);
            ResultSet rs = function.enviarSelect(objDao.obtienePrecioProducto());
            if (rs.next()) {
                precio = rs.getString("prpr_precio");
            } else {
                precio = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            precio = "0";
        }
        return precio;
    }

    /**
     * Fucion encargada de realizar la logica para adicionar un producto a una
     * factura
     *
     * @param dska_dska
     * @param cantidad
     * @return
     */
    public String adicionProdFactura(String dska_dska, String cantidad, String sede_sede) {
        String rta = "";
        Map<String, Object> respuesta = null;
        respuesta = new HashMap<String, Object>();
        Gson gson = new Gson();
        ProductoDao objDao = new ProductoDao();
        try (EnvioFuncion function = new EnvioFuncion()) {
            String cantiExisSede = obtenerExistenciasPorSede(dska_dska, sede_sede);
            int cantExisSede = Integer.parseInt(cantiExisSede);
            if (cantExisSede == 0) {
                respuesta.put("respuesta", "Error");
                respuesta.put("mensaje", "En esta sede no existe este producto");
                rta = gson.toJson(respuesta);
                return rta;
            }
            int cantCliente = Integer.parseInt(cantidad);
            if (cantCliente > cantExisSede) {
                respuesta.put("respuesta", "Error");
                respuesta.put("mensaje", "No existen el numero de productos para satisfacer la cantidad de productos solicitados por el cliente");
                rta = gson.toJson(respuesta);
                return rta;
            }
            objDao.setDska_dska(dska_dska);
            objDao.setSede(sede_sede);
            objDao.setCantidad("" + cantCliente);
            ResultSet rs = function.enviarSelect(objDao.calculosFactura());
            while (rs.next()) {
                CalculoProdEntity prod = new CalculoProdEntity();
                prod.setCantidad(rs.getString("cantidad"));
                prod.setDska_codigo(rs.getString("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecioUnidad(rs.getString("cantidad"));
                prod.setIvaUnidad(rs.getString("ivauni"));
                prod.setValortotal(rs.getString("vlrtotal"));
                prod.setIvaTotal(rs.getString("ivatotal"));
                prod.setPrecioUnidad(rs.getString("precio"));
                prod.setTotalPagar(rs.getString("totalpagar"));
                prod.setTotalIvaSf(rs.getString("totalIvaSinFil"));
                prod.setTotalProdSf(rs.getString("vlrPagarSinFil"));
                prod.setTotalPagarSf(rs.getString("totalPagarSinFil"));
                prod.setDska_dska(rs.getString("dska_dska"));
                respuesta.put("objeto", prod);
            }
            rta = gson.toJson(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.put("respuesta", "Error");
            respuesta.put("mensaje", "Error ProductoLogica.adicionProdFactura " + e);
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener el numero de
     * entradas o ingrersos de un producto en especifico por sede
     *
     * @param dska_dska String Identificador primario de la tabla de productos
     * @param sede_sede
     * @return
     */
    public int obtieneIngresosProdXSede(String dska_dska, String sede_sede) {
        int rta = 0;
        ProductoDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new ProductoDao();
            objDao.setDska_dska(dska_dska);
            ResultSet rs = function.enviarSelect(objDao.ingresoProdSede(sede_sede));
            if (rs.next()) {
                rta = rs.getInt("ingresos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = 0;
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener el numero de salidas
     * o egresos de un producto en especifico por sede
     *
     * @param objDto
     * @param sede_sede
     * @return
     */
    public int obtieneEgresosProdXSede(String dska_dska, String sede_sede) {
        int rta = 0;
        ProductoDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = new ProductoDao();
            objDao.setDska_dska(dska_dska);
            ResultSet rs = function.enviarSelect(objDao.egresosProdSede(sede_sede));
            if (rs.next()) {
                rta = rs.getInt("egresos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = 0;
        }
        return rta;
    }

    /**
     * Funcion encarda de realizar la logica para obtener el valor de las
     * existencias de un producto por sede
     *
     * @param producto Objeto con la informacion necesaria para encontrar el
     * producto
     * @param sede_sede Sede de la cual desea saber las existencias
     * @return
     */
    public String obtenerExistenciasPorSede(String dska_dska, String sede_sede) {
        String rta = null;
        try {
            int ingresos = 0;
            int egresos = 0;
            int total = 0;
            ingresos = obtieneIngresosProdXSede(dska_dska, sede_sede);
            egresos = obtieneEgresosProdXSede(dska_dska, sede_sede);
            total = ingresos - egresos;
            rta = "" + total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para obtener los productos por
     * medio de filtros
     *
     * @param objEntity
     * @return
     */
    public List obtieneProductosXFiltros(Producto objEntity, String sede) {
        List<Producto> rta = null;
        ProductoDao objDao = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            objDao = poblarDao(objEntity);
            objDao.setSede(sede);
            ResultSet rs = function.enviarSelect(objDao.obtieneProductoXFiltros());
            if (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<Producto>();
                }
                Producto aux = new Producto();
                aux.setDska_dska(rs.getString("dska_dska"));
                aux.setDska_refe(rs.getString("dska_refe"));
                aux.setDska_cod(rs.getString("dska_cod"));
                aux.setDska_nom_prod(rs.getString("dska_nom_prod"));
                aux.setDska_desc(rs.getString("dska_desc"));
                aux.setDska_iva(rs.getString("dska_iva"));
                aux.setDska_porc_iva(rs.getString("dska_porc_iva"));
                aux.setDska_marca(rs.getString("dska_marca"));
                aux.setDska_estado(rs.getString("dska_estado"));
                aux.setDska_fec_ingreso(rs.getString("dska_fec_ingreso"));
                aux.setDska_cate(rs.getString("dska_cate"));
                aux.setDska_sbcu(rs.getString("dska_sbcu"));
                aux.setCateNombre(rs.getString("cate_desc"));
                aux.setRefeNombre(rs.getString("refe_desc"));
                aux.setCantExis(this.obtenerExistenciasPorSede(aux.getDska_dska(), sede));
                aux.setPrecio(this.obtienePrecioProductoXId(aux.getDska_dska(), sede));
                Producto prodAux = this.obtieneCostoProdctoXSede(aux.getDska_dska(), sede);
                aux.setTotalPagarSf(prodAux.getTotalPagarSf());
                prodAux = null;
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica el precio con y sin iva de un
     * producto
     *
     * @return
     */
    public Producto obtieneCostoProdctoXSede(String dska_dska, String sede_sede) {
        Producto producto = null;
        try (EnvioFuncion function = new EnvioFuncion()) {
            ProductoDao objDao = new ProductoDao();
            objDao.setSede(sede_sede);
            objDao.setDska_dska(dska_dska);
            ResultSet rs =  function.enviarSelect(objDao.obtieneCostosXProducto());
            while(rs.next()){
                if(producto == null){
                    producto = new Producto();
                }
                producto.setTotalProdSf(rs.getString("prpr_precio"));
                producto.setTotalPagarSf(rs.getString("precioConIva"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    public ProductoDao poblarDao(Producto objEntity) {
        ProductoDao objDao = new ProductoDao();
        try {
            objDao.setDska_cod(objEntity.getDska_cod());
            objDao.setDska_cate(objEntity.getDska_cate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDao;
    }

}
