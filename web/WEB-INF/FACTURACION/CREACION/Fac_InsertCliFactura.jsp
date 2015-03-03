<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
    <head>
        <%@include file="/WEB-INF/TEMPLATES/cabecera.jsp"%> 
        <script type="text/javascript" src="<%=RutaSitio%>/JS/FACTURACION/consUpd_ClienteFact.js"></script>     
    </head>
    <body>
        <br/>                
        <div class="row" id="clienteNuevo" style="display: none;">
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
            <div class="col-md-6 col-sm-12 col-xs-12">
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
                <s:form action="Fac_nuevoCliente" name="Fac_nuevoCliente" id="Fac_nuevoCliente" theme="simple">
                    <s:textfield name="accion" cssStyle="display:none" value="insertarCliente" />
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="2" class="alert alert-info text-center" ><h3>NUEVO CLIENTE</h3></td>
                            </tr>                            
                        </thead>
                        <tbody>
                            <tr>
                                <td>Cedula:</td>
                                <td style="text-align: left">
                                    <b><s:text name="cliente.clien_cedula"/></b>
                                    <s:textfield name="cliente.clien_cedula" style="display:none"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Nombres:*</td>
                                <td><s:textfield  name="cliente.clien_nombres" cssClass="form-control" /></td>                                
                            </tr>
                            <tr>
                                <td>Apellidos*:</td>
                                <td><s:textfield name="cliente.clien_apellidos" cssClass="form-control" /></td>
                            </tr>
                            <tr>
                                <td>Correo:</td>
                                <td><s:textfield name="cliente.clien_correo" cssClass="form-control"/></td>
                            </tr>                            
                            <tr>
                                <td>Telefono:</td>
                                <td><s:textfield name="cliente.clien_telefono" cssClass="form-control" /></td>                                
                            </tr>                            
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: right;">
                                    <s:include value="/WEB-INF/TEMPLATES/botones/newClient.jsp" >
                                        <s:param name="function">insertar</s:param>
                                        <s:param name="title">Adicionar Cliente a la base de Datos</s:param>                                    
                                    </s:include>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">Recuerde que los campos obligatorios son (*)</td>
                            </tr>
                        </tfoot>
                    </table>                    
                </s:form>
            </div>
            <div class="col-md-3 col-sm-0 col-xs-0"></div>
        </div>

        <br/>
        <br/>
        <div id="clienteNuevo" style="display:none">

        </div>
        <s:set name="cliente" value="%{existeCliente}" />
        <s:if test="%{#cliente.equalsIgnoreCase('N')}">            
            <script>
                $('#clienteNuevo').show('fold');
                $('#buscaCliente').hide('slow');
            </script>
        </s:if>
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="mensaje">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">INFORMACION</h4>
                    </div>
                    <div class="modal-body">
                        <span id="textoMsn"></span>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            ACEPTAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
