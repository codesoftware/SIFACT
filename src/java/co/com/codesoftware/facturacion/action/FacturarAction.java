/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.ClienteEntity;
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

    public String facturar() {
        FacturacionLogica logica = new FacturacionLogica();
        String tius_tius = "";
        try {
            //Obtentgo el identificador primario del usuario que facturo
            UsuarioLogica logicaUsu = new UsuarioLogica();
            UsuarioEntity objUsu = logicaUsu.buscaUsuarioXusuario(usuario);
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
                            addActionError(valida);
                            return ERROR;
                        } else {
                            fact_fact = facturo[1];
                        }
                    } else {
                        addActionError("Error al generar la factura");
                    }
                    logica.borrarTemporalXidTransaccion(idTrans);
                }
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

    public void validate() {
        obtieneObjParametros();
        if ("facturar".equalsIgnoreCase(accion)) {
            UsuarioLogica usuarioLogica = new UsuarioLogica();
            String valida = usuarioLogica.validaSedeUsuaSedeFactura(usuario, parametros.getSede());
            if (!"Ok".equalsIgnoreCase(valida)) {
                addActionError(valida);
            }
            if (prodFact == null & remisionFact == null) {
                addActionError("La lista de Productos y equipos celulares se encuentra vacia");
            }
            usuarioLogica = null;
        }
        System.out.println("Paso por aqui1");
        if("reedireccion".equalsIgnoreCase(accion)){
            System.out.println("Paso por aqui");
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
}
