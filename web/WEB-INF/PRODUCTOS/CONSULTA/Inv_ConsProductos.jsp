<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/TEMPLATES/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATES/cabecera.jsp" />
    </head>
    <body>
        <div class="row" style="position: fixed;z-index: 500;top:0px;width: 100%;">
            <div class="col-md-12 col-xs-12 col-sm-12" style="text-transform: uppercase;">
                <h6 class="alert alert-danger text-center">RECUERDE QUE SE ENCUENTRA EN LA SEDE <s:text name="parametros.sedeNombre" /> POR LO TANTO CONSULTARA LOS DATOS DE ESTA SEDE</h6>
            </div>
        </div>
        <s:form theme="simple">
            <div class="row" style="margin-top: 60px">
                <div class="col-md-1 col-xs-0 col-sm-0"></div>
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="4"><h4 class="alert alert-info">CONSULTA DE PRODUCTOS</h4></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Categoria</td>
                                <td>Referencias</td>
                            </tr>
                            <tr>
                                <td><s:select cssClass="form-control" headerKey="-1" headerValue="Seleccione Categoria" list="categorias"></s:select></td>
                                <td><s:select cssClass="form-control" headerKey="-1" headerValue="Seleccione Referencia" list="referecias"></s:select></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td style="text-align: right;" colspan="2">
                                        <button class="btn btn-primary" >
                                            Consultar
                                        </button>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <div class="col-md-1 col-xs-0 col-sm-0"></div>
                </div>
        </s:form>
    </body>
</html>
