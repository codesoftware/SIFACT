<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="EleccionSede" id="sede" theme="simple">
            <s:textfield name="accion" value="obtieneSedes" cssStyle="display:none;"/>
        </form>
        <script>
            document.forms[0].submit();
        </script>
    </body>
</html>
