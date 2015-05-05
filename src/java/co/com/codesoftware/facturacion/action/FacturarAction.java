/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.ClienteEntity;
import co.com.codesoftware.facturacion.entity.FacturaEntity;
import co.com.codesoftware.facturacion.entity.PagoEntity;
import co.com.codesoftware.facturacion.logica.FacturacionLogica;
import co.com.codesoftware.facturacion.logica.RemisionLogica;
import co.com.codesoftware.parametros.Parametro;
import co.com.codesoftware.usuario.entity.UsuarioEntity;
import co.com.codesoftware.usuario.logica.UsuarioLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ACER
 */
public class FacturarAction extends ActionSupport implements SessionAware {

    private List remisionFact;
    private List prodFact;
    private ClienteEntity cliente;
    private String accion;
    private String usuario;
    private String fact_fact;
    private String idRemision;
    private Map session;
    private PagoEntity pago;
    private Parametro parametros;
    private String fechaIni;
    private String fechaFin;
    private List facturas;
    private FacturaEntity factura;

    public String facturar() {
        FacturacionLogica logica = null;
        String tius_tius = "";
        try {
            logica = new FacturacionLogica();
            //Obtentgo el identificador primario del usuario que facturo
            UsuarioLogica logicaUsu = new UsuarioLogica();
            UsuarioEntity objUsu = logicaUsu.buscaUsuarioXusuario(usuario.trim());
            logicaUsu = null;
            if (prodFact != null) {
                String idTrans = logica.obtieneValorSecuenciaTemp();
                if (idTrans != null) {
                    String rtaTemp = logica.insertarTemporalProductos(prodFact, idTrans);
                    if ("Ok".equalsIgnoreCase(rtaTemp)) {
                        //Aqui hago la facturacion
                        String valida = logica.creaFacturacion(idTrans, objUsu.getTius_tius(), cliente.getClien_clien(), pago, parametros.getSede());
                        String[] facturo = valida.split("-");
                        if (!"Ok".equalsIgnoreCase(facturo[0])) {
                            addActionError("Error al generar la Facturacion: " + valida);
                            return ERROR;
                        } else {
                            fact_fact = facturo[1];
                        }
                    } else {
                        addActionError("Error al generar la factura");
                        return ERROR;
                    }
                    logica.borrarTemporalXidTransaccion(idTrans);
                }else{
                    addActionError("Error al obtner la secuencia para los movimientos de inventario");
                    return ERROR;
                }
            } else {
                addActionError("Error al obtener el usuario facturador");
                return ERROR;
            }
            if (remisionFact != null) {
                String idRemision = logica.obtieneValorSecuenciaTempRemison();
                if (idRemision != null) {
                    RemisionLogica logicaRemision = new RemisionLogica();
                    String valida = logicaRemision.generaRemision(remisionFact, objUsu.getTius_tius(), cliente.getClien_clien(), idRemision);
                    if (!valida.equalsIgnoreCase("Ok")) {
                        addActionError(valida);
                    } else {
                        this.idRemision = idRemision;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de reedireccionar a la pagina de consulta de facturas
     *
     * @return
     */
    public String reedireccionConsulta() {
        return SUCCESS;
    }

    public String ejecutaConsulta() {
        FacturacionLogica logica = null;
        try {
            logica = new FacturacionLogica();
            facturas = logica.consultaFacturasXFiltros(factura);
            if (facturas == null) {
                addActionError("La consulta no arrojo ningun resultado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void validate() {
        obtieneObjParametros();
        if ("facturar".equalsIgnoreCase(accion)) {
            UsuarioLogica usuarioLogica = new UsuarioLogica();
            String valida = usuarioLogica.validaSedeUsuaSedeFactura(usuario, parametros.getSede());
            if (!"Ok".equalsIgnoreCase(valida)) {
                addActionError("Error al validar Usuario: " + valida);
            }
            if (parametros.getSede() == null || "".equalsIgnoreCase(parametros.getSede())) {
                addActionError("El sistema no puede obtener la sede en la cual se encuetra porfavor vaya al inicio y seleccione su sede e intente de nuevo");
            }
            if (prodFact == null & remisionFact == null) {
                addActionError("La lista de Productos y equipos celulares se encuentra vacia");
            }
            usuarioLogica = null;
        }
        if ("reedireccion".equalsIgnoreCase(accion)) {
        }
        if ("ejecutaConsulta".equalsIgnoreCase(accion)) {

        }
    }

    public List getRemisionFact() {
        return remisionFact;
    }

    public void setRemisionFact(List remisionFact) {
        this.remisionFact = remisionFact;
    }

    public List getProdFact() {
        return prodFact;
    }

    public void setProdFact(List prodFact) {
        this.prodFact = prodFact;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFact_fact() {
        return fact_fact;
    }

    public void setFact_fact(String fact_fact) {
        this.fact_fact = fact_fact;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(String idRemision) {
        this.idRemision = idRemision;
    }

    public PagoEntity getPago() {
        return pago;
    }

    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    public Parametro getParametros() {
        return parametros;
    }

    public void setParametros(Parametro parametros) {
        this.parametros = parametros;
    }

    public void obtieneObjParametros() {
        try {
            parametros = (Parametro) session.get("parametros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List getFacturas() {
        return facturas;
    }

    public void setFacturas(List facturas) {
        this.facturas = facturas;
    }
}
