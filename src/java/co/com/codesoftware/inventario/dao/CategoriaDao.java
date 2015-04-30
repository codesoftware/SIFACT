/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.inventario.dao;

/**
 * Clase encargada de realizar los query's de la tabla in_tcate
 *
 * @author nicolas
 */
public class CategoriaDao {

    private String cate_cate;
    private String cate_desc;
    private String cate_estado;
    private String cate_runic;
    private String cate_feven;

    public String getCate_cate() {
        return cate_cate;
    }

    public void setCate_cate(String cate_cate) {
        this.cate_cate = cate_cate;
    }

    public String getCate_desc() {
        return cate_desc;
    }

    public void setCate_desc(String cate_desc) {
        this.cate_desc = cate_desc;
    }

    public String getCate_estado() {
        return cate_estado;
    }

    public void setCate_estado(String cate_estado) {
        this.cate_estado = cate_estado;
    }

    public String getCate_runic() {
        return cate_runic;
    }

    public void setCate_runic(String cate_runic) {
        this.cate_runic = cate_runic;
    }

    public String getCate_feven() {
        return cate_feven;
    }

    public void setCate_feven(String cate_feven) {
        this.cate_feven = cate_feven;
    }

    /**
     * Funcion la cual devuelve el query para obtener todas las categorias
     * parametrizadas en el sitema
     *
     * @return
     */
    public String consultaGeneralActivos() {
        String select = "";
        select += "SELECT cate_cate, cate_desc, cate_estado, cate_runic, cate_feven\n";
        select += "  FROM in_tcate                                                 \n";
        select += " WHERE cate_estado = 'A' \n";
        return select;
    }

    public String consultaFiltros(String filtros) {
        String select = "";
        select += "SELECT cate_cate, cate_desc, cate_estado, cate_runic, cate_feven\n";
        select += "  FROM in_tcate   where   " + filtros;

        return select;
    }

    /**
     * Funcion la cual genera el query para obtener una categoria en especifico
     * dado el id de la categoria
     *
     * @return
     */
    public String consultaEspecificaXId() {
        String select = "";
        select += "SELECT cate_cate, cate_desc, cate_estado, cate_runic, cate_feven\n";
        select += "  FROM in_tcate                                                 \n";
        select += " WHERE cate_cate = " + this.cate_cate + " \n";
        return select;
    }

    public String actualizaCategoria() {
        String select = "UPDATE in_tcate set cate_desc = '" + this.cate_desc + "',cate_estado = '" + this.cate_estado + "',cate_runic = '" + this.cate_runic + "',cate_feven = '" + this.cate_feven + "' WHERE cate_cate = " + this.cate_cate + "";
        System.out.println("Update " + select);
        return select;
    }

    public String insertaCategoria() {
        String select = "";
        select = "INSERT into in_tcate (cate_desc,  cate_estado, cate_runic,cate_feven)"
                .concat("values ('" + this.cate_desc + "','" + this.cate_estado + "','" + this.cate_runic + "','" + this.cate_feven + "')");
        System.out.println("select" + select);
        return select;

    }

    public String consultaCategoriasActivas() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cate_cate, cate_desc\n");
        sql.append("  FROM in_tcate            \n");
        sql.append(" WHERE cate_estado = 'A'   \n");
        return sql.toString();
    }

}
