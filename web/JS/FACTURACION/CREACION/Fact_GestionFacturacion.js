var codigoDeBarras = '';
$(document).ready(function() {
    $('#codigoBarras').keypress(function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            codigoDeBarras = $('#codigoBarras').val();
            var datos = new Object();
            datos.codigoBarras = $('#codigoBarras').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: RutaSitio + '/traeProducto.html',
                async: false,
                success: function(response) {
                    var datos = JSON.parse(response);
                    if (datos.respuesta == 'OK') {

                        $('#filaConsultaProd').remove();
                        $('#filaConsultaRem').remove();
                        var tbody = '';
                        var codigo = codigoDeBarras;

                        if (codigo.charAt(0) == '1') {
                            tbody = '<tr id=\"filaConsultaProd\" >' +
                                    '<td>Descripcion:</td>' +
                                    '<td>' + datos.objeto.dska_desc + '</td>' +
                                    '<td>Nombre:</td>' +
                                    '<td>' + datos.objeto.dska_nom_prod + '</td>' +
                                    '<td>Precio:</td>' +
                                    '<td>' + datos.objeto.precio + '</td>' +
                                    '<td>Existencias:</td>' +
                                    '<td>' + datos.objeto.cantExis + '</td>' +
                                    '</tr>';
                        } else {
                            tbody = '<tr id=\"filaConsultaRem\" >' +
                                    '<td>Imei:</td>' +
                                    '<td>' + datos.objeto.rmce_imei + '</td>' +
                                    '<td>Valor:</td>' +
                                    '<td>' + datos.objeto.rmce_valor + '</td>' +
                                    '<td>Tipo de plan:</td>' +
                                    '<td>' + datos.objeto.rmce_tppl + '</td>' +
                                    '<td>Fecha de vencimiento:</td>' +
                                    '<td>' + datos.objeto.rmce_fcve + '</td>' +
                                    '</tr>';
                        }

                        $('#bodyConsulta').append(tbody);
                        $('#cantidad').focus();
                    } else {
                        $('#filaConsultaProd').remove();
                        $('#filaConsultaRem').remove();
                        $('#msnInfo').html('Producto Inexistente por favor intente de nuevo');
                        $('#informacionPopUp').modal('show');
                    }
                }
            });
            if (codigoDeBarras.charAt(0) == '2') {
                $('#cantidad').val('1');
                $('#btnAgregarProdRem').focus();
            }
        }
    });
    $(document).on('click', '.elimnarFilaProd', function() {
        $(this).closest('.filaProdFact').remove();
        var sumaIva = sumasValorIva();
        $('#vlrIvaText').html(sumaIva);
        var totalProd = sumasValorTotalProd();
        $('#vlrTotalProdText').html(totalProd);
        var totalPagar = sumasValorTotalPagar();
        $('#vlrTotalPagarText').html(totalPagar);
    });
    $('#enviaFacturar').click(function(){
        //alert('Envio formulario de Facturacion');
        document.getElementById("Fac_Facturar").submit();
        generarSticker(1);
        
        
    });
});

function generarSticker(id) {
    var url = "fact_GeneraFactura?factura.fact_fact=" + id ;
    window.open(url, "_blank", "directories=no, status=no,width=400, height=300,top=0,left=0");
    $('#dialogoAcciones').modal('hide');
}


function adicionaProducto() {
    var valida = validaDatos();
    if (valida) {
        var datos = new Object();
        datos.codigoBarras = $('#codigoBarras').val();
        $.ajax({
            type: 'GET',
            data: datos,
            url: RutaSitio + '/traeProducto.html',
            async: false,
            success: function(response) {
                var datos = JSON.parse(response);
                if (datos.respuesta == 'OK') {
                    codigoDeBarras = $('#codigoBarras').val();
                    if (codigoDeBarras.charAt(0) == '1') {
                        agregaProductos(datos.objeto.dska_dska);
                    } else {
                        agregaRemisiones(datos.objeto.rmce_rmce);
                    }
                }
                else {
                    $('#filaConsultaProd').remove();
                    $('#msnInfo').html('Producto Inexistente por favor intente de nuevo');
                    $('#informacionPopUp').modal('show');
                }
            }
        });
        $('#codigoBarras').focus();
    }
}

function validaDatos() {
    var codigo = $('#codigoBarras').val();
    var cantidad = $('#cantidad').val();
    if (codigo == '') {
        $('#msnInfo').html('El campo codigo no puede ser nulo');
        $('#informacionPopUp').modal('show');
        return false;
    }
    if (cantidad == '') {
        $('#msnInfo').html('El campo cantidad no puede ser nulo');
        $('#informacionPopUp').modal('show');
        return false;
    }
    var cast = parseInt(cantidad);
    if (cast == 0) {
        $('#msnInfo').html('El valor de cantidad no puede ser cero');
        $('#informacionPopUp').modal('show');
        return false;

    }
    var duplicados = validaProdExistente(codigo);
    if(duplicados== false){
        $('#msnInfo').html('El producto que desea ingresar ya se encuentra en la lista de productos');
        $('#informacionPopUp').modal('show');
        return false;
    }
    return true;
}

function validaProdExistente(cod){
    var codigos =  document.getElementsByClassName('codigoProd');
    if(codigos.length = 0){
        return true;        
    }else{
        for(var i = 0; i < codigos.length; i++){
            var codigo = codigos[i].value;
            if(codigo == cod){
                return false;
            }
        }       
    }
    return true;
}


function agregaProductos(dska_dska) {
    var datos = new Object();
    datos.dska_dska = dska_dska;
    datos.cantidad = $('#cantidad').val();
    $.ajax({
        url: RutaSitio + "/adicionaFactura.html",
        data: datos,
        async: false,
        success: function(data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            if (obj.respuesta == 'Error') {
                $('#msnInfo').html(obj.mensaje);
                $('#informacionPopUp').modal('show');
            } else {
                adicionaProductoFactura(obj.objeto);
            }
        }
    });
}

function agregaRemisiones(rmce_rmce) {
    var datos = new Object();
    datos.rmce_rmce = rmce_rmce;
    $('#cantidad').val('1');
    $.ajax({
        url: RutaSitio + "/adicionaFacturaRem.html",
        data: datos,
        async: false,
        success: function(data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            if (obj.respuesta == 'Error') {
                $('#msnInfo').html(obj.mensaje);
                $('#informacionPopUp').modal('show');
            } else {
                adicionaRemisionFactura(obj.objeto);
            }
        }
    });
}


function adicionaProductoFactura(objeto) {
    var tabla = $('#tablaFactProd');
    var fila = '<tr class=\"filaProdFact\">' +
            '<td>' + objeto.cantidad + '</td>' +
            '<td>' + objeto.dska_codigo + '<input type=\"hidden\" class=\"codigoProd\" value=\"' + objeto.dska_codigo + '\" /></td>' +
            '<td>' + objeto.nombre + '</td>' +
            '<td>' + objeto.precioUnidad + '</td>' +
            '<td>' + objeto.ivaUnidad + '</td>' +
            '<td>' + objeto.valortotal + '<input type=\"hidden\" class=\"valor\" value=\"' + objeto.totalProdSf + '\" /></td>' +
            '<td>' + objeto.ivaTotal + '<input type=\"hidden\" class=\"iva\" value=\"' + objeto.totalIvaSf + '\" /> <input type=\"hidden\" name=\"prodFact\" value=\"' + objeto.dska_dska + '&'+ objeto.cantidad +'\" /></td>' +
            '<td>' + objeto.totalPagar + '<input type=\"hidden\" class=\"total\" value=\"' + objeto.totalPagarSf + '\" /></td>' +
            '<td>' +
            '<button type=\"button\" class=\"btn btn-danger elimnarFilaProd\">' +
            '<span class=\"glyphicon glyphicon-remove\" ></span> </button>' +
            '</td>' +
            '</tr>';
    tabla.append(fila);
    var sumaIva = sumasValorIva();
    $('#vlrIvaText').html(sumaIva);
    var totalProd = sumasValorTotalProd();
    $('#vlrTotalProdText').html(totalProd);
    var totalPagar = sumasValorTotalPagar();
    $('#vlrTotalPagarText').html(totalPagar);
}

function adicionaRemisionFactura(objeto) {
    var tabla = $('#tablaFactRem');
    var fila = '<tr class=\"filaProdFact\">' +
            '<td>' + objeto.rmce_valor + '<input type=\"hidden\" class=\"total\" value=\"' + objeto.valorSinFiltros + '\" /> </td>' +
            '<td>' + objeto.rmce_tppl + '<input type=\"hidden\" class=\"valor\" value=\"' + objeto.valorSinFiltros + '\" /> </td>' +
            '<td>' + objeto.rmce_fcve + '<input type=\"hidden\" name=\"remisionFact\" value=\"' + objeto.rmce_rmce +'\" /></td>' +
            '<td>' + objeto.rmce_comision + '</td>' +
            '<td>' +
            '<button type=\"button\" class=\"btn btn-danger elimnarFilaProd\">' +
            '<span class=\"glyphicon glyphicon-remove\" ></span> </button>' +
            '</td>' +
            '</tr>';
    tabla.append(fila);
    var sumaIva = sumasValorIva();
    $('#vlrIvaText').html(sumaIva);
    var totalProd = sumasValorTotalProd();
    $('#vlrTotalProdText').html(totalProd);
    var totalPagar = sumasValorTotalPagar();
    $('#vlrTotalPagarText').html(totalPagar);
}


function sumasValorIva() {
    var valor = $('.iva');
    if (valor.length == 0) {
        return 0;
    } else {
        var sumatoria = 0;
        $.each(valor, function(key, value) {
            var aux = parseInt(value.value);
            sumatoria = sumatoria + aux;
        });
        return sumatoria;
    }
}



function sumasValorTotalProd() {
    var valor = $('.valor');
    if (valor.length == 0) {
        return 0;
    } else {
        var sumatoria = 0;
        $.each(valor, function(key, value) {
            var aux = parseInt(value.value);
            sumatoria = sumatoria + aux;
        });
        return sumatoria;
    }
}



function sumasValorTotalPagar() {
    var valor = $('.total');
    if (valor.length == 0) {
        return 0;
    } else {
        var sumatoria = 0;
        $.each(valor, function(key, value) {
            var aux = parseInt(value.value);
            sumatoria = sumatoria + aux;
        });
        return sumatoria;
    }
}

function facturar(){
    $('#usuarioFacturador').modal('show');    
}

function buscaCodigoXIdProducto(id){
    var codigo = "";
    var datos = new Object();
    datos.dska_dska = id;
    $.ajax({
        data: datos,
        async: false,
        url: RutaSitio + "/buscaProdXId.html",
        success: function(data, textStatus, jqXHR) {
            var datosRta = JSON.parse(data);
            codigo = datosRta.objeto.dska_cod;
        }
    });
    return codigo;
}