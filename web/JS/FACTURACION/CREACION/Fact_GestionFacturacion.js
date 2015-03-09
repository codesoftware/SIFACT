var codigoDeBarras = '';
$(document).ready(function () {
    $('#codigoBarras').keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            codigoDeBarras = $('#codigoBarras').val();
            var datos = new Object();
            datos.codigoBarras = $('#codigoBarras').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: RutaSitio + '/traeProducto.html',
                success: function (response) {
                    var datos = JSON.parse(response);
                    if (datos.respuesta == 'OK') {

                        $('#filaConsultaProd').remove();
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
                        $('#msnInfo').html('Producto Inexistente por favor intente de nuevo');
                        $('#informacionPopUp').modal('show');
                    }
                }
            });
        }
    });
    $(document).on('click', '.elimnarFilaProd', function () {
        $(this).closest('.filaProdFact').remove();
    });
});

function adicionaProducto() {
    var valida = validaDatos();
    if (valida) {
        var urlAjax = '';
        if (codigoDeBarras.charAt(0) == '1') {
            urlAjax = RutaSitio + '/traeProducto.html'
        } else {
            urlAjax = RutaSitio + '/adicionaFacturaRem.html';
        }
        var datos = new Object();
        datos.codigoBarras = $('#codigoBarras').val();
        $.ajax({
            type: 'GET',
            data: datos,
            url: urlAjax,
            async: false,
            success: function (response) {
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
        success: function (data, textStatus, jqXHR) {
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
    $.ajax({
        url: RutaSitio + "/adicionaFacturaRem.html",
        data: datos,
        async: false,
        success: function (data, textStatus, jqXHR) {
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
            '<td>' + objeto.dska_codigo + '</td>' +
            '<td>' + objeto.nombre + '</td>' +
            '<td>' + objeto.precioUnidad + '</td>' +
            '<td>' + objeto.ivaUnidad + '</td>' +
            '<td>' + objeto.valortotal + '</td>' +
            '<td>' + objeto.ivaTotal + '</td>' +
            '<td>' + objeto.totalPagar + '</td>' +
            '<td>' +
            '<button type=\"button\" class=\"btn btn-danger elimnarFilaProd\">' +
            '<span class=\"glyphicon glyphicon-remove\" ></span> </button>' +
            '</td>' +
            '</tr>';
    tabla.append(fila);
}

function adicionaRemisionFactura(objeto) {
    var tabla = $('#tablaFactRem');
    var fila = '<tr class=\"filaProdFact\">' +
            '<td>' + objeto.rmce_valor + '</td>' +
            '<td>' + objeto.rmce_tppl + '</td>' +
            '<td>' + objeto.rmce_fcve + '</td>' +
            '<td>' + objeto.rmce_comision + '</td>' +
            '<td>' +
            '<button type=\"button\" class=\"btn btn-danger elimnarFilaProd\">' +
            '<span class=\"glyphicon glyphicon-remove\" ></span> </button>' +
            '</td>' +
            '</tr>';
    tabla.append(fila);
}
