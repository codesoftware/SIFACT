/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.datos;

/**
 *
 * @author Personal
 */
/**
 * Clase utilizada para enviar la respuesta con dos atributos a la clase que la
 * llame
 *
 * @author nicolas
 * @version 1.0.0
 * @since 1.0.0
 */
public class RetornoLoginDto {

    private String update;
    private boolean acceso;

    public RetornoLoginDto() {
        this.update = "No";
        this.acceso = false;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }
}
