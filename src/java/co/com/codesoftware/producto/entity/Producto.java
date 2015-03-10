/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.producto.entity;

/**
 *
 * @author Personal
 */
public class Producto {

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
    private String filtros; //Indica S como se le debe mostrar al cliente y N como se le debe enviar a la base de datos
    private String cantExis; //Numero de cantidades existentes de cada producto
    private String precio;
    private String catidad;
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

    public String getFiltros() {
        return filtros;
    }

    public void setFiltros(String filtros) {
        this.filtros = filtros;
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

    public String getCatidad() {
        return catidad;
    }

    public void setCatidad(String catidad) {
        this.catidad = catidad;
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
}
