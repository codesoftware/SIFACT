<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/TEMPLATES/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATES/cabecera.jsp" />
        <script type="text/javascript" src="<%=RutaSitio%>/JS/PRODUCTOS/CONSULTA/Inv_ConsProductos.js"></script>
    </head>
    <body>
        <div class="row" style="position: fixed;z-index: 500;top:0px;width: 100%;">
            <div class="col-md-12 col-xs-12 col-sm-12" style="text-transform: uppercase;">
                <h6 class="alert alert-danger text-center">RECUERDE QUE SE ENCUENTRA EN LA SEDE <s:text name="parametros.sedeNombre" /> POR LO TANTO CONSULTARA LOS DATOS DE ESTA SEDE
                <a href="Inv_EscojeSede.action?parametros.sede=<s:text name="parametros.sede" />">MENU PRINCIPAL</a>
                </h6>
            </div>
        </div>
        <s:form theme="simple" action="Inv_EjeConsProductos" id="Inv_EjeConsProductos">
            <s:textfield name="accion" value="ejecutaConsulta" cssStyle="display:none;"/>
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
                                <td>Codigo</td>
                            </tr>
                            <tr>
                                <td><s:select name="producto.dska_cate" cssClass="form-control" headerKey="-1" headerValue="Seleccione Categoria" list="categorias"></s:select></td>
                                <td><s:select name="producto.dska_refe" cssClass="form-control" headerKey="-1" headerValue="Seleccione Referencia" list="referecias"></s:select></td>
                                <td><s:textfield cssClass="form-control" name="producto.dska_cod"></s:textfield></td>
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
                    <div class="col-md-1 col-xs-0 col-sm-0"></div>
                </div>
        </s:form>
        <div class="row">
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
            <div class="col-md-10 col-xs-12 col-sm-12">
                <s:if test="%{productos!=null}">
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr class="alert alert-info">
                                <td>Codigo</td>
                                <td>Nombre</td>
                                <td>Descripcion</td>
                                <td>Referencia</td>
                                <td>Categoria</td>
                                <td>Cant. Exist.</td>
                                <td>Precio</td>
                                <td>Precio + Iva</td>
                            </tr>                            
                        </thead>
                        <tbody>
                            <s:iterator value="productos">
                                <tr>
                                    <td><s:property value="dska_cod"/></td>
                                    <td><s:property value="dska_nom_prod"/></td>
                                    <td><s:property value="dska_desc"/></td>
                                    <td><s:property value="refeNombre"/></td>
                                    <td><s:property value="cateNombre"/></td>
                                    <td><s:property value="cantExis"/></td>
                                    <td><s:property value="precio"/></td>
                                    <td>$ <s:property value="totalPagarSf"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
            </div>
            <div class="col-md-1 col-xs-0 col-sm-0"></div>
        </div>
    </body>
</html>
