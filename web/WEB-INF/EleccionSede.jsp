<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/TEMPLATES/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATES/cabecera.jsp"></s:include>
        </head>
        <body>
            <br/>
            <br/>
            <br/>
        <s:form action="Inv_EscojeSede" theme="simple">
            <s:textfield name="accion" value="escojeSede" cssStyle="display:none;" />
            <div class="row">
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
                </div>
                <div class="col-md-3 col-sm-0 col-xs-0"></div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-0 col-xs-0"></div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th colspan="2"><h3 class="alert alert-info">Seleccione a la cual pertenece para Iniciar a Facturar</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><b>SEDE:</b></td>
                                <td>
                                    <s:select name="sede" list="sedes" cssClass="form-control" headerKey="-1" headerValue="Seleccione una Sede"/>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" class="text-right">
                                    <button class="btn btn-primary">
                                        Iniciar
                                    </button>                                    
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="col-md-3 col-sm-0 col-xs-0"></div>
            </div>
        </s:form>

    </body>
</html>
