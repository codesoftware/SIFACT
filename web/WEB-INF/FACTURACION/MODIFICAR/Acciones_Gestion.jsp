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
        <br/>
        <br/>
        <br/>
    <center>
        <a class="btn btn-primary" href="<%=RutaSitio%>/homeFacturacion.jsp">FACTURA NUEVA</a>
    </center>
    <s:if test="%{fact_fact != null}">
        <script>
                generarSticker('<s:text name="fact_fact"/>');
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
