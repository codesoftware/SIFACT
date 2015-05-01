/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.producto.dao;

/**
 *
 * @author Personal
 */
public class ProductoDao {

    private String dska_dska;
    private String dska_refe;
    private String dska_cod;
    private String dska_nom_prod;
    private String dska_desc;
    private String dska_iva;
    private String dska_porc_iva;
    private String dska_marca;
    private String dska_estado;
    private String dska_fec_ingreso;
    private String dska_cate;
    private String dska_sbcu;
    private String cantExis;
    //Informacion adicional del producto
    private String precio;
    private String sede;
    private String cantidad;//Cantidad de productos a comprar
    //Valores principales sin filtros
    private String totalPagarSf;
    private String totalIvaSf;
    private String totalProdSf;

    public String getDska_dska() {
        return dska_dska;
    }

    public void setDska_dska(String dska_dska) {
        this.dska_dska = dska_dska;
    }

    public String getDska_refe() {
        return dska_refe;
    }

    public void setDska_refe(String dska_refe) {
        this.dska_refe = dska_refe;
    }

    public String getDska_cod() {
        return dska_cod;
    }

    public void setDska_cod(String dska_cod) {
        this.dska_cod = dska_cod;
    }

    public String getDska_nom_prod() {
        return dska_nom_prod;
    }

    public void setDska_nom_prod(String dska_nom_prod) {
        this.dska_nom_prod = dska_nom_prod;
    }

    public String getDska_desc() {
        return dska_desc;
    }

    public void setDska_desc(String dska_desc) {
        this.dska_desc = dska_desc;
    }

    public String getDska_iva() {
        return dska_iva;
    }

    public void setDska_iva(String dska_iva) {
        this.dska_iva = dska_iva;
    }

    public String getDska_porc_iva() {
        return dska_porc_iva;
    }

    public void setDska_porc_iva(String dska_porc_iva) {
        this.dska_porc_iva = dska_porc_iva;
    }

    public String getDska_marca() {
        return dska_marca;
    }

    public void setDska_marca(String dska_marca) {
        this.dska_marca = dska_marca;
    }

    public String getDska_estado() {
        return dska_estado;
    }

    public void setDska_estado(String dska_estado) {
        this.dska_estado = dska_estado;
    }

    public String getDska_fec_ingreso() {
        return dska_fec_ingreso;
    }

    public void setDska_fec_ingreso(String dska_fec_ingreso) {
        this.dska_fec_ingreso = dska_fec_ingreso;
    }

    public String getDska_cate() {
        return dska_cate;
    }

    public void setDska_cate(String dska_cate) {
        this.dska_cate = dska_cate;
    }

    public String getCantExis() {
        return cantExis;
    }

    public void setCantExis(String cantExis) {
        this.cantExis = cantExis;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotalPagarSf() {
        return totalPagarSf;
    }

    public void setTotalPagarSf(String totalPagarSf) {
        this.totalPagarSf = totalPagarSf;
    }

    public String getTotalIvaSf() {
        return totalIvaSf;
    }

    public void setTotalIvaSf(String totalIvaSf) {
        this.totalIvaSf = totalIvaSf;
    }

    public String getTotalProdSf() {
        return totalProdSf;
    }

    public void setTotalProdSf(String totalProdSf) {
        this.totalProdSf = totalProdSf;
    }

    public String getDska_sbcu() {
        return dska_sbcu;
    }

    public void setDska_sbcu(String dska_sbcu) {
        this.dska_sbcu = dska_sbcu;
    }

    /**
     * Funcion encargada de realizar el query para obtener la informacion de un
     * producto teniendo como referencia el codigo del producto
     *
     * @return
     */
    public String traeProductoCodigo() {
        String select = "";
        select += "SELECT dska_dska, dska_refe, dska_cod, dska_nom_prod, dska_desc, dska_iva, \n";
        select += "       dska_porc_iva, dska_marca, dska_estado, dska_fec_ingreso, dska_cate \n";
        select += "  FROM in_tdska                                                            \n";
        select += " WHERE dska_cod = '" + this.dska_cod + "' \n";
        return select;
    }

    /**
     * Funcion encargada de realizar el query para obtener la informacion de un
     * producto teniendo como referencia el id del producto
     *
     * @return
     */
    public String traeProductoIdentifi() {
        String select = "";
        select += "SELECT dska_dska, dska_refe, dska_cod, dska_nom_prod, dska_desc, dska_iva, \n";
        select += "       dska_porc_iva, dska_marca, dska_estado, dska_fec_ingreso, dska_cate \n";
        select += "  FROM in_tdska                                                            \n";
        select += " WHERE dska_dska = '" + this.dska_dska + "' \n";
        return select;
    }

    /**
     * Funcion encargada de realizar el Query para obtener el precio del
     * producto
     *
     * @return
     */
    public String obtienePrecioProducto() {
        String sql = "";
        sql += "SELECT to_char(prpr_precio,'LFM9,,999,999,999.00') as prpr_precio\n";
        sql += "FROM in_tprpr                                                    \n";
        sql += "WHERE prpr_estado = 'A'                                          \n";
        sql += "AND prpr_dska = " + this.getDska_dska() + " \n";
        sql += " AND prpr_sede = " + this.getSede();
        return sql;
    }

    /**
     * Funcion encargada realizar el query para saber todos los ingresos del
     * producto realizados por sede
     *
     * @return
     */
    public String ingresoProdSede(String sede) {
        String sql = "";
        sql += "SELECT sum(kapr_cant_mvto) ingresos  \n";
        sql += "  FROM in_tmvin, in_tkapr   \n";
        sql += " WHERE mvin_natu = 'I'      \n";
        sql += "   AND mvin_mvin = kapr_mvin\n";
        sql += "   AND kapr_sede = " + sede + " \n";
        sql += "   AND kapr_dska = " + this.getDska_dska();
        return sql;
    }

    /**
     * Funcion encargada realizar el query para saber todos los egresos del
     * producto por sede
     *
     * @param sede
     * @return
     */
    public String egresosProdSede(String sede) {
        String sql = "";
        sql += "SELECT sum(kapr_cant_mvto) egresos  \n";
        sql += "  FROM in_tmvin, in_tkapr   \n";
        sql += " WHERE mvin_natu = 'E'      \n";
        sql += "   AND mvin_mvin = kapr_mvin\n";
        sql += "   AND kapr_sede = " + sede + " \n";
        sql += "   AND kapr_dska = " + this.getDska_dska();
        return sql;
    }

    /**
     * Funcion encargda de realizar el Query de los calculos necesarios para
     * facturar un producto
     *
     * @return
     */
    public String calculosFactura() {
        String sql = "";
        sql += "SELECT dska_dska,cantidad, codigo,nombre, to_char(precio,'LFM9,999,999,999,999.00') precio,               \n";
        sql += "       to_char(ivauni,'LFM9,999,999,999,999.00') ivaUni,                                        \n";
        sql += "       to_char(vlrTotal,'LFM9,999,999,999,999.00') vlrTotal,                                    \n";
        sql += "       to_char(ivaTotal,'LFM9,999,999,999,999.00') ivaTotal,                                    \n";
        sql += "       to_char((vlrTotal+ivaTotal),'LFM9,999,999,999,999.00') totalPagar,                       \n";
        sql += "       cast((vlrTotal+ivaTotal) as int) totalPagarSinFil,                                       \n";
        sql += "       cast( ivaTotal as int ) totalIvaSinFil,                                                \n";
        sql += "       cast( vlrTotal as int) vlrPagarSinFil                                                  \n";
        sql += "  FROM (SELECT  dska_dska,cantidad, codigo, nombre,                                                       \n";
        sql += "                precio, ((precio*iva)/100) ivaUni,                                              \n";
        sql += "                (precio *cantidad) vlrTotal, (((precio*iva)/100)*cantidad) ivaTotal             \n";
        sql += "        FROM (SELECT dska_dska,dska_cod codigo, dska_nom_prod nombre,                                     \n";
        sql += "                     prpr_precio precio, " + this.getCantidad() + " cantidad,                    \n";
        sql += "                     cast((select para_valor from em_tpara where para_clave = 'IVAPR')as int) iva\n";
        sql += "                FROM in_tdska, in_tprpr                                                          \n";
        sql += "               WHERE dska_dska = " + this.getDska_dska() + "                                     \n";
        sql += "                 AND prpr_dska = dska_dska                                                       \n";
        sql += "                 AND prpr_estado = 'A'                                                           \n";
        sql += "                 AND prpr_sede = " + this.getSede() + "                                          \n";
        sql += "              ) param                                                                            \n";
        sql += "       ) tablaFinal                                                                              \n";
        return sql;
    }

    /**
     * Funcion encargada de realizar el Query para obtener los procutos del
     * sistema por medio de una serie de filtros
     *
     * @return
     */
    public String obtieneProductoXFiltros() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dska_dska, dska_refe, dska_cod, dska_nom_prod, dska_desc, dska_iva, dska_porc_iva, dska_marca, dska_estado, dska_fec_ingreso, dska_cate, dska_sbcu, ");
        sql.append(" refe_desc, cate_desc ");
        sql.append("FROM in_tdska, in_trefe, in_tcate ");
        sql.append(" WHERE dska_refe = refe_refe ");
        sql.append("   AND cate_cate = dska_cate ");
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query para consultar el precio de un
     * producto en una sede con impuestos
     *
     * @return
     */
    public String obtieneCostosXProducto() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT prpr_precio, to_char(((prpr_precio * (select cast(para_valor as numeric) from em_tpara where para_clave = 'IVAPR'))/100) + prpr_precio,'9,999,999,999,999.00') as precioConIva ");
        sql.append("FROM in_tprpr ");
        sql.append("WHERE prpr_estado = 'A' ");
        sql.append("  AND prpr_sede = " + this.getSede());
        sql.append("  AND prpr_dska = " + this.getDska_dska());
        return sql.toString();
    }
}
