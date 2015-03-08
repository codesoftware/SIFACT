/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.entity;

/**
 * Clase encargada de almacenar los datos y los calculos que se realizan para la
 * facturacion de un producto
 *
 * @author Nicolas
 */
public class CalculoProdEntity{
    
    private String dska_dska;
    private String dska_codigo;
    private String nombre;
    private String precioUnidad;
    private String ivaUnidad;
    private String valortotal;
    private String ivaTotal;
    private String totalPagar;
    private String cantidad;

    public String getDska_dska() {
        return dska_dska;
    }

    public void setDska_dska(String dska_dska) {
        this.dska_dska = dska_dska;
    }

    public String getDska_codigo() {
        return dska_codigo;
    }

    public void setDska_codigo(String dska_codigo) {
        this.dska_codigo = dska_codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(String precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public String getIvaUnidad() {
        return ivaUnidad;
    }

    public void setIvaUnidad(String ivaUnidad) {
        this.ivaUnidad = ivaUnidad;
    }

    public String getValortotal() {
        return valortotal;
    }

    public void setValortotal(String valortotal) {
        this.valortotal = valortotal;
    }

    public String getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(String ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

    public String getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(String totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}