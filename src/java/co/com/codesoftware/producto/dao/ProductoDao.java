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
    private String descuento;
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

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
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
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT to_char(((((select cast(PARA_VALOR as INT) from em_tpara where para_clave = 'IVAPR') * prpr_precio)/100)+ prpr_precio),'9,999,999,999.00') ");
        sql.append(" as prpr_precio ");
        sql.append("FROM in_tprpr ");
        sql.append("WHERE prpr_estado = 'A' ");
        sql.append("AND prpr_dska = ");
        sql.append(this.getDska_dska());
        sql.append(" AND prpr_sede = ");
        sql.append(this.getSede());
        return sql.toString();
    }

    /**
     * Funcion encargada de realizar el Query para obtener el precio del
     * producto
     *
     * @return
     */
    public String obtienePrecioProductoXDescuento(String descuento) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT to_char((((((select cast(PARA_VALOR as INT) from em_tpara where para_clave = 'IVAPR') * prpr_precio)/100)+ prpr_precio)-" + descuento + " ),'9,999,999,999.00') ");
        sql.append(" as prpr_precio ");
        sql.append("FROM in_tprpr ");
        sql.append("WHERE prpr_estado = 'A' ");
        sql.append("AND prpr_dska = ");
        sql.append(this.getDska_dska());
        sql.append(" AND prpr_sede = ");
        sql.append(this.getSede());
        return sql.toString();
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
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT dska_dska,                                                             ");
        sql.append("        cantidad,                                                              ");
        sql.append("        codigo,                                                                ");
        sql.append("        nombre,                                                                ");
        sql.append("       cast(precio as numeric(50,0))                            precio,          ");
        sql.append("       cast(ivauni as numeric(50,0))                            ivaUni,          ");
        sql.append("       cast(vlrtotal as numeric(50,0)) 			    vlrTotal,        ");
        sql.append("       cast(ivatotal as numeric(50,0))                          ivaTotal,        ");
        sql.append("       cast(( vlrtotal + ivatotal ) as numeric(50,0))           totalPagar,      ");
        sql.append("       Cast(( vlrtotal + ivatotal ) AS INT)                     totalPagarSinFil,");
        sql.append("       Cast(ivatotal AS INT) 			            totalIvaSinFil,  ");
        sql.append("       Cast(vlrtotal AS INT)                                    vlrPagarSinFil,  ");
        sql.append("       cast(preciosindto as numeric(50,0))                      preciosindto,    ");
        sql.append("       descuentoTotal                                           descuentoTotal,   ");
        sql.append("       descuento                                                descuento       ");
        sql.append(" FROM   (SELECT dska_dska,                                                     ");
        sql.append("                cantidad,                                                      ");
        sql.append("                codigo,                                                        ");
        sql.append("                nombre,                                                        ");
        sql.append("                descuento				      AS descuento,        ");
        sql.append("                descuentoTotal                            AS descuentoTotal,   ");
        sql.append("                precio                                    AS precioSinDto,     ");
        sql.append("                precio - descuento                        AS precio,           ");
        sql.append("                ( ( precio * iva ) / 100 )                ivaUni,              ");
        sql.append("                ( ( precio - descuento ) * cantidad )     vlrTotal,            ");
        sql.append("                ( ( ( precio * iva ) / 100 ) * cantidad ) ivaTotal             ");
        sql.append("         FROM   (SELECT dska_dska,                                             ");
        sql.append("                        dska_cod                                  codigo,      ");
        sql.append("                        dska_nom_prod                             nombre,      ");
        sql.append("                        prpr_precio                               precio,      ");
        sql.append(this.getCantidad());
        sql.append("                        cantidad,    (");
        sql.append(this.getDescuento());
        sql.append("*");
        sql.append(this.getCantidad());
        sql.append(")                        descuentoTotal,   ");
        sql.append(this.getDescuento());
        sql.append("                        descuento, ");
        sql.append("                        Cast((SELECT para_valor                                ");
        sql.append("                              FROM   em_tpara                                  ");
        sql.append("                              WHERE  para_clave = 'IVAPR')AS INT) iva          ");
        sql.append("                 FROM   in_tdska,                                              ");
        sql.append("                        in_tprpr                                               ");
        sql.append("                 WHERE  dska_dska =                                            ");
        sql.append(this.getDska_dska());
        sql.append("                        AND prpr_dska = dska_dska                              ");
        sql.append("                        AND prpr_estado = 'A'                                  ");
        sql.append("                        AND prpr_sede =                   ");
        sql.append(this.getSede());
        sql.append(") param) tablaFinal");
        System.out.println("Este es el sql para el calculo de precio\n\n\n" + sql.toString());
        return sql.toString();
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
        sql.append(armaWhere());
        sql.append("   ORDER BY dska_dska ");
        return sql.toString();
    }

    public String armaWhere() {
        StringBuilder where = new StringBuilder();
        if (!"-1".equalsIgnoreCase(this.getDska_cate())) {
            where.append(" AND DSKA_CATE = ");
            where.append(this.getDska_cate());
        }
        if (!"-1".equalsIgnoreCase(this.getDska_refe())) {
            where.append(" AND dska_refe = ");
            where.append(this.getDska_refe());
        }
        if (!"".equalsIgnoreCase(this.getDska_cod().trim())) {
            where.append(" AND dska_cod = '");
            where.append(this.getDska_cod().trim());
            where.append("'");
        }
        return where.toString();
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
