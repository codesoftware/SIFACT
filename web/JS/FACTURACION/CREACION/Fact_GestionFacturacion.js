$(document).ready(function() {
    $('#codigoBarras').keypress(function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            var datos = new Object();
            datos.codigoBarras = $('#codigoBarras').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: RutaSitio + '/traeProducto.html',
                success: function(response) {
                    var datos = JSON.parse(response);
                    if (datos.respuesta == 'OK') {
                        $('#filaConsultaProd').remove();
                        var tbody = '<tr id=\"filaConsultaProd\" >' +
                                '<td>Descripcion:</td>' +
                                '<td>' + datos.objeto.dska_desc + '</td>' +
                                '<td>Nombre:</td>' +
                                '<td>' + datos.objeto.dska_nom_prod + '</td>' +
                                '<td>Precio:</td>' +
                                '<td>' + datos.objeto.precio + '</td>' +
                                '<td>Existencias:</td>' +
                                '<td>' + datos.objeto.cantExis + '</td>' +
                                '</tr>';
                        $('#bodyConsulta').append(tbody);
                        $('#cantidad').focus();
                    } else {
                        $('#filaConsultaProd').remove();
                        $('#msnInfo').html('Producto Inexistente por favor intente de nuevo');
                        $('#informacionPopUp').modal('show');
                    }
                    //$('.result').html(response);
                }
            });
        }
    });
});

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
                    agregaProductos(datos.objeto.dska_dska);
                } else {
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
        success: function(data, textStatus, jqXHR) {
            var obj = JSON.parse(data);
            if (obj.respuesta == 'Error') {
                $('#msnInfo').html(obj.mensaje);
                $('#informacionPopUp').modal('show');
            }else{
                adicionaProductoFactura(obj.objeto);
            }
        }
    });
}

function adicionaProductoFactura(objeto){
    var tabla = $('#tablaFactProd');
    var fila = '<tr>'+
                    '<td>' + objeto.cantidad + '</td>'+
                    '<td>' + objeto.dska_codigo + '</td>'+
                    '<td>' + objeto.nombre + '</td>'+
                    '<td>' + objeto.precioUnidad + '</td>'+
                    '<td>' + objeto.ivaUnidad + '</td>'+
                    '<td>' + objeto.valortotal + '</td>'+
                    '<td>' + objeto.ivaTotal + '</td>'+
                    '<td>' + objeto.totalPagar + '</td>'+
                    '<td>'+
                        '<button type=\"button\" class=\"btn btn-danger elimnarFila\">'+
                        '<span class=\"glyphicon glyphicon-remove\" ></span> </button>'+
                    '</td>'+ 
                '</tr>';
    tabla.append(fila);
}
