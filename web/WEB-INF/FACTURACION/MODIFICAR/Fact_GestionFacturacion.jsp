<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/TEMPLATES/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATES/cabecera.jsp"></s:include>


        <script type="text/javascript">

            $(document).ready(function () {
                $('#codigoBarras').keypress(function (event) {
                    var keycode = (event.keyCode ? event.keyCode : event.which);
                    if (keycode == '13') {
                        var datos= new Object();
                        datos.codigoBarras = $('#codigoBarras').val();
                        $.ajax({
                            type: 'GET',
                            data: datos,
                            url: '${pageContext.request.contextPath}/traeProducto.html',
                            success: function (response) {
                                $('.result').html(response);
                            }
                        });
                    }
                });
            });
        </script>
    </head>
    <body>
        <s:div cssClass="header">
            <s:include value="/WEB-INF/NEWTEMPLATE/FrameTop.jsp" > 
                <s:param name="nombre"><s:text name="usuario.apellido"/> <s:text name="usuario.nombre"/></s:param>
                <s:param name="perfil"><s:text name="usuario.NomPerfil"/></s:param>
                <s:param name="ultimoIngreso"><s:text name="usuario.ultimoIngreso"/></s:param>
            </s:include>
        </s:div>
        <s:div cssClass="navigator">
            <s:include value="/WEB-INF/NEWTEMPLATE/menu.jsp">
                <s:param name="title"><s:property value="usuario.usuario" /></s:param>
            </s:include> 
        </s:div>
        <div class="row">
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
            <div class="col-md-8 col-xs-12 col-sm-12">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <td colspan="4" class="alert alert-info text-center"><h3>Datos del Cliente</h3></td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="width: 20%"><b>CEDULA:</b></td>
                            <td style="width: 30%"><s:text name="cliente.clien_cedula" /></td>
                            <td style="width: 20%"><b>CORREO:</b></td>
                            <td style="width: 30%"><s:text name="cliente.clien_correo" /></td>
                        </tr>
                        <tr>
                            <td><b>NOMBRE:</b></td>
                            <td colspan="3"><s:text name="cliente.clien_nombres" /> <s:text name="cliente.clien_apellidos" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <div class="row">
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
            <div class="col-md-8 col-xs-12 col-sm-12">
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
                            <td style="width: 15%;"><b>Valor Iva:</b></td>
                            <td style="width: 18%;">0</td>
                            <td style="width: 15%;"><b>Valores Totales:</b></td>
                            <td style="width: 18%;">0</td>
                            <td style="width: 15%;"><b>Total a Pagar:</b> </td>
                            <td style="width: 17%;">0</td>
                        </s:else>
                    </tr>
                    <tr>
                        <td colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td>
                            <button type="button" class="btn btn-default" onclick="agregar()"><span class="glyphicon glyphicon-plus"></span>&nbsp;Agregar</button>
                        </td>                        
                    </tr>
                </table>
                <form>
                <table class="table table-bordered"  >
                    <tr>
                        <td class="alert alert-info text-center" colspan="6" ><h3>Consultar Producto</h3></td>
                    </tr>
                    <tr>
                        <td>Codigo de barras:<s:textfield cssClass="form-control" name="codigoBarras" required="true" id="codigoBarras" theme="simple" /></td>   

                    </tr>
                </table>
                <div class="result"></div>
            </div>
            <div class="col-md-2 col-xs-0 col-sm-0"></div>
        </div>
        <!-- Inicio popups de la pagina-->
        <!-- Dialogo en el cual se elige si se va ha adicionar un servicio o un producto-->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="dialogoAddServProd">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Elección Producto o Servicio</h4>
                    </div>
                    <div class="modal-body">
                        ¿Desea adicionar un producto o un servicio?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CERRAR
                        </button>
                        <button type="button" class="btn btn-primary" id="sevicioAdd">
                            SERVICIO
                        </button>
                        <button type="button" class="btn btn-primary" id="productoAdd">
                            PRODUCTO
                        </button>                        
                    </div>
                </div>
            </div>
        </div>
        <!-- Muestra un formulario con las opciones de Filtro con las cuales el usuario podra reservar una habitacion -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="dialogoFiltroReservServ">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Filtros Reserva Habitaciones</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-danger" style="display: none; text-align: center;padding: 5px; font-size: 13px;" id="divError" >
                            <span id="msgError" style="font-weight: 700;"></span>
                        </div>
                        <div style="width: 100%;text-align: center;font-size: 14px;"><b>POSIBLES FILTROS PARA RESERVA DE HABITACION</b></div>                        
                        <form>
                            <div class="form-group">
                                <label for="fecha" class="control-label">Fecha:</label>
                                <div class="input-group date">
                                    <input type="text" class="form-control" id="fechaReserv" readonly="true" >
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numPersonas" class="control-label">Número de Personas:</label>
                                <input type="text" class="form-control" id="numPersonasReserv" onkeypress="return validaNumeros(event);">
                            </div>
                            <div class="form-group">
                                <label for="numDias" class="control-label">Numero de Días:</label>
                                <input type="text" class="form-control" id="numDiasReserv" onkeypress="return validaNumeros(event);">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CERRAR
                        </button>
                        <button type="button" class="btn btn-primary" id="buscarPosibleReservHab">
                            BUSCAR
                        </button>                        
                    </div>
                </div>                
            </div>
        </div>
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
        <!-- Pop up en el cual se visualizara los resultados de la consulta de servicios -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="consultaBusquedaServ">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Resultados Busqueda Habitaciones</h4>
                    </div>
                    <div class="modal-body">
                        <table id="tablaAddServ" class="table table-hover table-striped">

                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CANCELAR
                        </button>   
                        <button type="button" class="btn btn-primary" id="confirmarReserva">
                            RESERVAR
                        </button> 
                    </div>
                </div>
            </div>
        </div>
        <!-- Muestra un formulario con las opciones de Filtro con las cuales el usuario podra encontrar un podructo -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="dialogoFiltroAdicionProd">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Filtros Cosulta Productos</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-danger" style="display: none; text-align: center;padding: 5px; font-size: 13px;" id="divErrorProd" >
                            <span id="msgErrorProd" style="font-weight: 700;"></span>
                        </div>
                        <div style="width: 100%;text-align: center;font-size: 14px;"><b>POSIBLES FILTROS PARA ENCONTRAR UN PRODUCTO</b></div>                        
                        <form>
                            <div class="form-group">
                                <label for="codigoProd" class="control-label">Codigo:</label>
                                <input type="text" class="form-control" id="codigoProd" />
                            </div>
                            <div class="form-group">
                                <label for="referenciaProd" class="control-label">Referencia:</label>
                                <input type="text" class="form-control" id="referenciaProd" />
                            </div>
                            <div class="form-group">
                                <label for="nombreProducto" class="control-label">Nombre Producto:</label>
                                <input type="text" class="form-control" id="nombreProducto" />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CERRAR
                        </button>
                        <button type="button" class="btn btn-primary" id="buscaPosiblesProd">
                            BUSCAR
                        </button>                        
                    </div>
                </div>                
            </div>
        </div>
        <!--- Popup el cual visualizara los resultados de la consulta de productos -->
        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="consultaBusquedaProd">
            <div class="modal-dialog">                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Resultado busqueda de Productos</h4>
                    </div>
                    <div class="modal-body">
                        <table id="tablaAddProd" class="table table-hover table-striped">

                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            CANCELAR
                        </button>   
                        <button type="button" class="btn btn-primary" id="adicionaProd" >
                            ADICIONAR
                        </button> 
                    </div>
                </div>
            </div>
        </div>

        <!-- Fin popups de la pagina-->
    </body>
</html>