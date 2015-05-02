<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@include file="/WEB-INF/TEMPLATES/Parametros.jsp" %>
<!DOCTYPE html>
<html>
    <head>        
        <s:include value="/WEB-INF/TEMPLATES/cabecera.jsp"></s:include>
        <script type="text/javascript" src="<%=RutaSitio%>/JS/FACTURACION/MODIFICAR/Acciones_Gestion.js"></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-1 col-xs-1 col-sm-1"></div>
            <div class="col-md-10 col-xs-10 col-sm-10" style="text-transform: uppercase;">
                <h6 class="alert alert-danger text-center">RECUERDE QUE SE ENCUENTRA EN LA SEDE <s:text name="parametro.sedeNombre" /> POR LO TANTO FACTURARA Y CONSULTARA LOS DATOS DE ESTA SEDE</h6>
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"></div>
        </div>
    </div>
    <br/>
    <br/>
    <br/>
<center>
    <a class="btn btn-primary" href="<%=RutaSitio%>/homeFacturacion.jsp">FACTURA NUEVA</a>
    <a class="btn btn-primary" onclick="enviaConsultaGeneral()">CONSULTA PRODUCTOS</a>
    <s:url action="Fact_RedirConsulta.action" var="aURL" />
    <s:a href="%{aURL}?accion=reedireccion" cssClass="btn btn-primary">
        CONSULTA FACTURACION
    </s:a>
</center>
    <s:form action="Inv_consProductos" id="Inv_consProductos" theme="simple">
        <s:textfield name="accion" value="consultaGeneralProductos" cssStyle="display:none;"/>
    </s:form>
<s:if test="%{fact_fact != null}">
    <script>
        generarFactura('<s:text name="fact_fact"/>');
    </script>
</s:if>
<s:else>
</s:else>
<s:if test="%{idRemision != null}" >
    <script>
        generaRemision('<s:text name="idRemision" />');
    </script>
</s:if>
<s:else>
</s:else>
</body>
</html>
