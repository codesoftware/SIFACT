<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/TEMPLATES/cabecera.jsp"%> 
    </head>
    <body>
        <s:form action="logeo" autocomplete="off" theme="simple">
            <div class="row" style="margin-top: 10%">
                <div class="col-sm-1 col-md-4"></div>
                <div class="col-sm-10 col-md-4">
                    <div class="row" >
                        <div class="thumbnail col-md-12 col-sm-12">
                            <div class="alert alert-success text-center" role="alert"><h4><label>Sistema de Facturación</label></h4></div>
                            <div class="form-group col-md-12 col-sm-12">   
                                <h3><label for="nick" class="text-left">Usuario</label></h3>
                                <s:textfield cssClass="form-control" name="user"  required="true"  /><br>
                            </div>
                            <div class="form-group col-md-12 col-sm-12">
                                <h3><label for="nick">Contraseña</label></h3>
                                <s:password cssClass="form-control" name="pass"  required="true" /><br><br>
                            </div>
                            <div class="form-group col-md-12 col-sm-12 text-center">
                                <div class="Mensajes" style="display: none;">
                                    <s:if test="hasActionErrors()">
                                        <div class="errors">
                                            <div class="alert alert-danger" id="info" role="alert" ><h4><s:actionerror /></h4></div>
                                            <script>
                                                mostrarMsn();
                                            </script>
                                        </div>
                                    </s:if>
                                </div>
                                <s:submit label="Ingresar"  cssClass="btn btn-primary"/>
                                <s:a action="recuperarContra">Recuperar Contraseña</s:a>     
                            </div>
                            </div>
                        </div>
                    </div>
                <div class="col-sm-1 col-md-4"></div>
            </div>
        </s:form>
        <script type="text/javascript">
            $(function () {
                $('.btn-primary').val('Enviar');
            });
        </script>
    </body>
</html>
