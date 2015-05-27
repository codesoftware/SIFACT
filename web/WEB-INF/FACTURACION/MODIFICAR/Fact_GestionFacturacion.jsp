<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/TEMPLATES/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATES/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/FACTURACION/CREACION/Fact_GestionFacturacion.js"></script>
    </head>
    <body>
        <div class="row" style="position: fixed;z-index: 500;top:0px;width: 100%;">
            <div class="col-md-12 col-xs-12 col-sm-12" style="text-transform: uppercase;">
                <h6 class="alert alert-danger text-center">RECUERDE QUE SE ENCUENTRA EN LA SEDE <s:text name="parametros.sedeNombre" /> POR LO TANTO FACTURARA Y CONSULTARA LOS DATOS DE ESTA SEDE
                    <a href="Inv_EscojeSede.action?parametros.sede=<s:text name="parametros.sede" />">MENU PRINCIPAL</a>

                </h6>
            </div>
        </div>
        <div class="row" style="margin-top: 80px;" >
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <td colspan="6" class="alert alert-info text-center"><h3>Datos del Cliente</h3></td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="width: 20%"><b>CEDULA:</b></td>
                            <td style="width: 30%"><s:text name="cliente.clien_cedula" /></td>
                            <td style="width: 20%"><b>CORREO:</b></td>
                            <td style="width: 30%"><s:text name="cliente.clien_correo" /></td>
                            <td><b>NOMBRE:</b></td>
                            <td colspan="3"><s:text name="cliente.clien_nombres" /> <s:text name="cliente.clien_apellidos" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
        </div>
        <s:form action="Fac_Facturar" id="Fac_Facturar" theme="simple">
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <div class="Mensajes" style="display: none;">
                        <s:if test="hasActionErrors()">
                            <div class="alert alert-danger" id="info" role="alert" ><h4><s:actionerror /></h4></div>
                            <script>
                                mostrarMsn();
                            </script>
                        </s:if>
                    </div>
                    <div class="MensajesOk" style="display: none;">
                        <s:if test="hasActionMessages()">
                            <div class="alert alert-success" id="info" role="alert" ><h4><s:actionmessage/></h4></div>
                            <script>
                                mostrarMsnOk();
                            </script>
                        </s:if>
                    </div>
                    <table class="table table-bordered">
                        <tr>
                            <td class="alert alert-info text-center" colspan="6" ><h3>Valores de Facturaci&oacute;n</h3></td>
                        </tr>
                        <tr>
                            <s:if test="%{factura != null}">
                            </s:if>
                            <s:else>
                                <td style="width: 15%;"><b>Vlr Iva:</b></td>
                                <td style="width: 18%;">$<span id="vlrIvaText">0</span></td>
                                <td style="width: 15%;"><b>Vlr Totales:</b></td>
                                <td style="width: 18%;">$<span id="vlrTotalProdText">0</span></td>
                                <td style="width: 15%;"><b>Total a Pagar:</b> </td>
                                <td style="width: 17%;">$<span id="vlrTotalPagarText">0</span></td>
                            </s:else>
                        </tr>
                    </table>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td class="alert alert-info text-center" colspan="8" ><h3>Consultar Producto</h3></td>
                            </tr>
                        </thead>
                        <tbody id="bodyConsulta">
                            <tr>
                                <td colspan="4"> 
                                    <b>Buscar por Imei:</b> <input type="checkbox" id="buscaImei" /> <input type="text" id="IdImei" placeholder="IMEI" style="display: none;" class="form-control"/>
                                </td>
                                <td colspan="5">
                                    <b>Pago con:</b>&nbsp;&nbsp;&nbsp;&nbsp;Tarjeta&nbsp;<input type="radio" name="pago.tipoPago" value="T"  class="rPago" />&nbsp;&nbsp;Efectivo&nbsp;<input type="radio" name="pago.tipoPago" value="E" class="rPago" checked/>
                                    <input type="text" id="IdVoucher" placeholder="ID VOUCHER" style="display: none;" name="pago.idVucher" class="form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 10%;">
                                    <b>CODIGO:</b>
                                </td>
                                <td colspan="2">                                    
                                    <s:textfield cssClass="form-control" name="codigoBarras" required="true" id="codigoBarras" theme="simple" />
                                </td>
                                <td style="width: 10%;">
                                    <b>Descuento:</b>
                                </td>
                                <td colspan="2">
                                    <div class="input-group">
                                        <span class="input-group-addon">$</span>
                                        <s:textfield cssClass="form-control" name="descuento" required="true" id="descuento" theme="simple" onkeypress="return validaNumeros(event)" onkeyup="mascaraMoneda(this)" value="0" />
                                    </div>                                    
                                </td>
                                <td style="width: 10%;">
                                    <b>CANTIDAD:</b>
                                </td>
                                <td colspan="3">
                                    <s:textfield cssClass="form-control" name="cantidad" required="true" id="cantidad" theme="simple" onkeypress="return validaNumeros(event)"/>
                                </td>                                
                            </tr>
                        </tbody> 
                        <tfoot>
                            <tr>
                                <td style="width:10%;text-align: right;" colspan="8">
                                    <button type="button" class="btn btn-default" id="btnAgregarProdRem" onclick="adicionaProducto()">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;Agregar
                                    </button>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <div class="result"></div>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
            </div>

            <s:textfield name="accion" cssStyle="display:none" value="facturar"/>
            <s:textfield name="cliente.clien_cedula" cssStyle="display:none"/>
            <s:textfield name="cliente.clien_clien"  cssStyle="display:none"/>
            <s:textfield name="cliente.clien_apellidos"  cssStyle="display:none"/>
            <s:textfield name="cliente.clien_nombres"  cssStyle="display:none"/>
            <s:textfield name="cliente.clien_correo"  cssStyle="display:none"/>
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td class="alert alert-info text-center" colspan="11" ><h3>Productos Factura</h3></td>
                            </tr>
                        </thead>
                        <tbody id="tablaFactProd">
                            <tr class="alert alert-success">
                                <td>Cant.</td>
                                <td>Cod.</td>
                                <td>Referencia</td>
                                <td>Precio Sin Dto.</td>
                                <td>Descto.</td>
                                <td>Prod Unidad</td>
                                <td>Iva Unidad</td>
                                <td>Total Prod</td>
                                <td>Total Iva</td>
                                <td>Total</td>
                                <td>Accion</td>
                            </tr>
                        </tbody>
                    </table>       
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
            </div>
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td class="alert alert-info text-center" colspan="9" ><h3>Remisiones Factura</h3></td>
                            </tr>
                        </thead>
                        <tbody id="tablaFactRem">
                            <tr class="alert alert-success">
                                <td>valor</td>
                                <td>Tipo de plan</td>
                                <td>Fecha de vencimiento</td>
                                <td>Comisión</td>
                                <td>Acción</td>
                            </tr>
                        </tbody>
                    </table> 
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
            </div>
            <div class="row">
                <div class="col-md-5 col-xs-2 col-sm-2"></div>
                <div class="col-md-2 col-xs-8 col-sm-8">
                    <button type="button" class="btn btn-warning" id="btn-factura" onclick="facturar()" style="width: 100%">
                        <span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Facturar
                    </button>                
                </div>
                <div class="col-md-5 col-xs-2 col-sm-2"></div>
            </div>
            <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="usuarioFacturador">
                <div class="modal-dialog">                
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">usuario Facturador</h4>
                        </div>
                        <div class="modal-body">
                            Por Favor digite su Usuario:<br/>
                            <s:textfield name="usuario" cssClass="form-control" id="usuarioFacturador" />
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                CANCELAR
                            </button>
                            <button type="button" class="btn btn-primary" id="enviaFacturar">
                                FACTURAR
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
        <!-- Inicio popups de la pagina-->
        <!-- Popup utilizado para visualizar informacion -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="informacionPopUp">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Informaci&oacute;n</h4>
                    </div>
                    <div class="modal-body">
                        <span id="msnInfo"></span>                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            ACEPTAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fin popups de la pagina-->
    </body>
    <s:iterator value="prodFact">
        <script>
            var valor = '<s:property />';
            var vector = valor.split("&amp;");
            var dska = vector[0];
            var cantidad = vector[1];
            var codigo = buscaCodigoXIdProducto(dska);
            $('#codigoBarras').val(codigo);
            $('#cantidad').val(cantidad);
            adicionaProducto();
            $('#codigoBarras').val('');
            $('#cantidad').val('');
            //alert('Identificador: ' + dska + ' Cantidad: ' + cantidad + ' Codigo: ' + codigo);
        </script>        
    </s:iterator>
</html>