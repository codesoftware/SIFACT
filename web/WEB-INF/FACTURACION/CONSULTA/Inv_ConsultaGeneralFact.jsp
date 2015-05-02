<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/TEMPLATES/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATES/cabecera.jsp" />
        <script type="text/javascript">
            $(function () {
                $('.input-group.date').datepicker({
                    format: 'dd/mm/yyyy'
                });
            });
            var ventana = window;
            function buscaFactura(id) {
                var url = "fact_GeneraFactura.action?factura.fact_fact=" + id;
                try{
                    ventana.close();
                }catch(e){}                
                ventana = window;
                ventana.open(url, "_blank", "directories=no, status=no,width=400, height=300,top=0,left=0");
            }
        </script>
    </head>
    <body>
        <div class="row" style="position: fixed;z-index: 500;top:0px;width: 100%;">
            <div class="col-md-12 col-xs-12 col-sm-12" style="text-transform: uppercase;">
                <h6 class="alert alert-danger text-center">RECUERDE QUE SE ENCUENTRA EN LA SEDE <s:text name="parametros.sedeNombre" /> POR LO TANTO CONSULTARA LOS DATOS DE ESTA SEDE</h6>
            </div>
        </div>
        <s:form theme="simple" action="Fact_EjeConsFacturas" id="Fact_EjeConsFacturas">
            <s:textfield name="accion" value="ejecutaConsulta" cssStyle="display:none;"/>
            <div class="row" style="margin-top: 60px">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="4"><h4 class="alert alert-info">CONSULTA DE FACTURAS</h4></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td >Cedula Cliente</td>
                                <td >Fecha Ini</td>
                                <td >Fecha Fin</td>
                            </tr>
                            <tr>
                                <td style="width: 30;">
                                    <s:textfield cssClass="form-control" />
                                </td>
                                <td style="width: 35;">
                                    <div class="input-group date" style="width: 200px;" >
                                        <s:textfield  cssClass="form-control" readonly="true"/>
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </td>
                                <td style="width: 35;">
                                    <div class="input-group date" style="width: 200px;" >
                                        <s:textfield name="remision.rmce_fcve" cssClass="form-control" readonly="true"/>
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td style="text-align: right;" colspan="3">
                                    <button class="btn btn-primary" >
                                        Consultar
                                    </button>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0">
                </div>
            </div>
        </s:form>
        <s:if test="%{facturas != null}">
            <div class="row">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td>Id Fact</td>
                                <td>Cedula Cliente</td>
                                <td>Nombre Cliente</td>
                                <td>Valor Total</td>
                                <td>Valor Iva</td>
                                <td>Total Pagado</td>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="facturas">
                                <tr>
                                    <td><s:property value="fact_fact" /></td>
                                    <td><a onclick="buscaFactura('<s:property value="fact_fact" />')"><s:property value="clien_cedula" /></a></td>
                                    <td><s:property value="clien_nombres" /></td>
                                    <td>$<s:property value="fact_vlr_total" /></td>
                                    <td>$<s:property value="fact_vlr_iva" /></td>
                                    <td>$<s:property value="pagoTotal" /></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
            </div>
        </s:if>
    </body>
</html>
