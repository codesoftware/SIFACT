/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.inventario.dao;

/**
 *
 * @author Personal
 */
public class ReferenciaDao {

    private String refe_refe;
    private String refe_desc;
    private String refe_estado;
    private String refe_came;
    private String refe_memori;
    private String refe_pantalla;

    public String getRefe_pantalla() {
        return refe_pantalla;
    }

    public void setRefe_pantalla(String refe_pantalla) {
        this.refe_pantalla = refe_pantalla;
    }

    public String getRefe_refe() {
        return refe_refe;
    }

    public void setRefe_refe(String refe_refe) {
        this.refe_refe = refe_refe;
    }

    public String getRefe_desc() {
        return refe_desc;
    }

    public void setRefe_desc(String refe_desc) {
        this.refe_desc = refe_desc;
    }

    public String getRefe_estado() {
        return refe_estado;
    }

    public void setRefe_estado(String refe_estado) {
        this.refe_estado = refe_estado;
    }

    public String getRefe_came() {
        return refe_came;
    }

    public void setRefe_came(String refe_came) {
        this.refe_came = refe_came;
    }

    public String getRefe_memori() {
        return refe_memori;
    }

    public void setRefe_memori(String refe_memori) {
        this.refe_memori = refe_memori;
    }

    /**
     * Funcion la cual devuelve el query para obtener todas las categorias
     * parametrizadas en el sitema
     *
     * @return
     */
    public String consultaGeneralActivos() {
        String select = "";
        select += "SELECT refe_refe, refe_desc, refe_estado, refe_came, refe_memori,refe_pantalla\n";
        select += "  FROM in_trefe                                                 \n";
        select += " WHERE refe_estado = 'A' \n";
        return select;
    }
    /**
     * Funcion la cual devuelve el query para obtener todas las categorias
     * parametrizadas en el sitema
     *
     * @param filtros
     * @return
     */
    public String consultaFilros(String filtros) {
        String select = "SELECT refe_refe, refe_desc, refe_estado, refe_came, refe_memori,refe_pantalla  from in_trefe  WHERE "+filtros;
        System.out.println("Filtros"+select);
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
        select += "SELECT refe_refe, refe_desc, refe_estado, refe_came, refe_memori,refe_pantalla \n";
        select += "  FROM in_trefe                                                 \n";
        select += " WHERE refe_refe = " + this.refe_refe + " \n";
        return select;
    }

    /*PFuncion que inserta la referencia para la categoría del celular
     *
     * @return
     
     **/
    public String insertaReferencia() {
       String select="";
       select = "INSERT into in_trefe (refe_desc,  refe_came, refe_memori,refe_pantalla)"
                     .concat( "values ('"+this.refe_desc+"',"+this.refe_came+","+this.refe_memori+","+this.refe_pantalla+")");
       return select;

    }
    
    public String actualizaReferencia(){
        String select = "UPDATE in_trefe set refe_desc = '"+this.refe_desc+"',refe_estado = '"+this.refe_estado+"',refe_came = '"+this.refe_came+"',refe_memori = '"+this.refe_memori+"',refe_pantalla = '"+this.refe_pantalla+"' WHERE refe_refe = "+this.refe_refe+"";
        System.out.println("Update "+select);
        return select;
    }

}
