/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.usuario.entity;

/**
 *
 * @author Personal
 */
public class Usuario {

    private String idTius;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String tipoUsuario;
    private String usuario;
    private String cambioContra;
    private String permisos;
    private String NomPerfil;
    private String idPerfil;
    private String fechaNacimiento;
    private String estado;
    private String ultimoIngreso;
    private String sede;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCambioContra() {
        return cambioContra;
    }

    public void setCambioContra(String cambioContra) {
        this.cambioContra = cambioContra;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getNomPerfil() {
        return NomPerfil;
    }

    public void setNomPerfil(String NomPerfil) {
        this.NomPerfil = NomPerfil;
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdTius() {
        return idTius;
    }

    public void setIdTius(String idTius) {
        this.idTius = idTius;
    }

    public String getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(String ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

}
