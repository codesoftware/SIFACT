var codigoDeBarras = '';
$(document).ready(function () {
    $('#codigoBarras').keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            codigoDeBarras = $('#codigoBarras').val();
            var datos = new Object();
            datos.codigoBarras = $('#codigoBarras').val();
            datos.descuento = $('#descuento').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: RutaSitio + '/traeProducto.action',
                async: false,
                success: function (response) {
                    var datos = JSON.parse(response);
                    if (datos.respuesta == 'OK') {
                        $('#filaConsultaProd').remove();
                        $('#filaConsultaRem').remove();
                        var tbody = '';
                        var codigo = codigoDeBarras;
                        /*Logica cuando es un producto */
                        if (codigo.charAt(0) == '1') {
                            var descuento = mascaraMonedaConValor(datos.objeto.descuento);
                            tbody = '<tr id=\"filaConsultaProd\" >' +
                                    '<td>Referencia:</td>' +
                                    '<td>' + datos.objeto.dska_desc + '</td>' +
                                    '<td>Descuento:</td>' +
                                    '<td>$' + descuento + '</td>' +
//                                    '<td>Nombre:</td>' +
//                                    '<td>' + datos.objeto.dska_nom_prod + '</td>' +
                                    '<td>Precio:</td>' +
                                    '<td>$' + datos.objeto.precio + '</td>' +
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
                        $('#msnInfo').html('Producto Inexistente o ya no se encuentra en esta sede por favor intente de nuevo');
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
    $(document).on('click', '.elimnarFilaProd', function () {
        $(this).closest('.filaProdFact').remove();
        var sumaIva = sumasValorIva();
        $('#vlrIvaText').html(sumaIva);
        var totalProd = sumasValorTotalProd();
        $('#vlrTotalProdText').html(totalProd);
        var totalPagar = sumasValorTotalPagar();
        $('#vlrTotalPagarText').html(totalPagar);
    });
    $('#enviaFacturar').click(function () {
        //alert('Envio formulario de Facturacion');
        document.getElementById("Fac_Facturar").submit();
    });
    $('#buscaImei').click(function () {
        if ($(this).is(':checked')) {
            $('#IdImei').show('slow');
            $('#IdImei').focus();
        } else {
            $('#IdImei').hide('fast');
        }
    });
    $('.rPago').click(function () {
        var pago = $('.rPago:checked').val();
        if (pago == 'T') {
            $('#IdVoucher').show();
            $('#valorTarjeta').hide();
            $('#labelVlrTarjeta').hide();
            $('#spanVlrTarjeta').hide();
        }
        if (pago == 'E') {
            $('#IdVoucher').hide();
            $('#valorTarjeta').hide();
            $('#labelVlrTarjeta').hide();
            $('#spanVlrTarjeta').hide();
        }
        if (pago == 'M') {
            $('#IdVoucher').show();
            $('#valorTarjeta').show();
            $('#labelVlrTarjeta').show();
            $('#spanVlrTarjeta').show();
        }
    });
    $('#IdImei').keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            var datos = new Object();
            datos.rmce_imei = $('#IdImei').val();
            $.ajax({
                type: 'POST',
                url: RutaSitio + '/buscaRemcXImei.action',
                data: datos,
                async: false,
                success: function (data, textStatus, jqXHR) {
                    var objeto = JSON.parse(data);
                    if (objeto.respuesta = "OK") {
                        $('#filaConsultaProd').remove();
                        $('#filaConsultaRem').remove();
                        $('#codigoBarras').val(objeto.objeto.rmce_codigo);
                        tbody = '<tr id=\"filaConsultaRem\" >' +
                                '<td>Imei:</td>' +
                                '<td>' + objeto.objeto.rmce_imei + '</td>' +
                                '<td>Valor:</td>' +
                                '<td>' + objeto.objeto.rmce_valor + '</td>' +
                                '<td>Tipo de plan:</td>';
                        var plan = '';
                        if (objeto.objeto.rmce_tppl == 'pr') {
                            plan = 'Prepago';
                        } else {
                            plan = 'Postpago';
                        }
                        tbody += '<td>' + plan + '</td>' +
                                '<td>Fecha de vencimiento:</td>' +
                                '<td>' + objeto.objeto.rmce_fcve + '</td>' +
                                '</tr>';
                        $('#bodyConsulta').append(tbody);
                        $('#cantidad').val('1');
                        $('#btnAgregarProdRem').focus();
                        $('#IdImei').hide('fast');
                        $('#IdImei').val('');
                        var imei = document.getElementById('buscaImei');
                        imei.checked = false;
                    }
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
        datos.descuento = $('#descuento').val();
        $.ajax({
            type: 'GET',
            data: datos,
            url: RutaSitio + '/traeProducto.action',
            async: false,
            dataType: 'json',
            success: function (datos) {
                if (datos.respuesta == 'OK') {
                    codigoDeBarras = $('#codigoBarras').val();
                    if (codigoDeBarras.charAt(0) == '1') {
                        agregaProductos(datos.objeto.dska_dska, datos.objeto.descuento);
                    } else {
                        agregaRemisiones(datos.objeto.rmce_rmce);
                    }
                }
                else {
                    $('#filaConsultaProd').remove();
                    $('#msnInfo').html('Producto Inexistente o ya no se encuentra en esta sede por favor intente de nuevo');
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
    if (duplicados == false) {
        $('#msnInfo').html('El producto que desea ingresar ya se encuentra en la lista de productos');
        $('#informacionPopUp').modal('show');
        return false;
    }
    var duplicadosRem = validaRemExistente(codigo);
    if (duplicadosRem == false) {
        $('#msnInfo').html('El equipo ceular que desea ingresar ya se encuentra en la lista.');
        $('#informacionPopUp').modal('show');
        return false;
    }
    var descuento = $('#descuento').val();
    if (descuento.trim() == '') {
        $('#descuento').val('0');
    }
    return true;
}

function validaProdExistente(cod) {
    var codigos = document.getElementsByClassName('codigoProd');
    if (codigos.length = 0) {
        return true;
    } else {
        for (var i = 0; i < codigos.length; i++) {
            var codigo = codigos[i].value;
            if (codigo == cod) {
                return false;
            }
        }
    }
    return true;
}

function validaRemExistente(cod) {
    var codigos = document.getElementsByClassName('codigoRem');
    if (codigos.length = 0) {
        return true;
    } else {
        for (var i = 0; i < codigos.length; i++) {
            var codigo = codigos[i].value;
            if (codigo == cod) {
                return false;
            }
        }
    }
    return true;
}


function agregaProductos(dska_dska, descuento) {
    var datos = new Object();
    datos.dska_dska = dska_dska;
    datos.cantidad = $('#cantidad').val();
    datos.descuento = descuento;
    $.ajax({
        url: RutaSitio + "/adicionaFactura.action",
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
    $('#cantidad').val('1');
    $.ajax({
        url: RutaSitio + "/adicionaFacturaRem.action",
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
            '<td>' + objeto.cantidad + '<input type=\"hidden\" class=\"id_prod_fact\" data-desc=\"' + objeto.descuentoTotal + '\" data-cant=\"' + objeto.cantidad + '\" value=\"' + objeto.dska_dska + '\" /></td>' +
            '<td>' + objeto.dska_codigo + '<input type=\"hidden\" class=\"codigoProd\" value=\"' + objeto.dska_codigo + '\" /></td>' +
            '<td>' + objeto.nombre + '</td>' +
            '<td>$' + mascaraMonedaConValor(objeto.precioSinDto) + '</td>' +
            '<td>$' + mascaraMonedaConValor(objeto.descuentoTotal) + '</td>' +
            '<td>$' + mascaraMonedaConValor(objeto.precioUnidad) + '</td>' +
            '<td>$' + mascaraMonedaConValor(objeto.ivaUnidad) + '</td>' +
            '<td>$' + mascaraMonedaConValor(objeto.valortotal) + '<input type=\"hidden\" class=\"valor\" value=\"' + objeto.totalProdSf + '\" /></td>' +
            '<td>$' + mascaraMonedaConValor(objeto.ivaTotal) + '<input type=\"hidden\" class=\"iva\" value=\"' + objeto.totalIvaSf + '\" /> <input type=\"hidden\" name=\"prodFact\" value=\"' + objeto.dska_dska + '&' + objeto.cantidad + '&' + objeto.descuentoTotal + '\" /></td>' +
            '<td>$' + mascaraMonedaConValor(objeto.totalPagar) + '<input type=\"hidden\" class=\"total\" value=\"' + objeto.totalPagarSf + '\" /></td>' +
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
            '<td>' + objeto.rmce_fcve + '<input type=\"hidden\" name=\"remisionFact\" value=\"' + objeto.rmce_rmce + '\" /></td>' +
            '<td>' + objeto.rmce_comision + '<input type=\"hidden\" class=\"codigoRem\" value=\"' + objeto.rmce_codigo + '\" /></td>' +
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
        $.each(valor, function (key, value) {
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
        $.each(valor, function (key, value) {
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
        $.each(valor, function (key, value) {
            var aux = parseInt(value.value);
            sumatoria = sumatoria + aux;
        });
        return sumatoria;
    }
}

function facturar() {
    var valida = validaDatosBeforeFac();
    if (valida) {
        $('#usuarioFacturador').modal('show');
    }
}

function validaDatosBeforeFac() {
    var pago = $('.rPago:checked').val();
    if (pago == 'T') {
        var idVoucher = $('#IdVoucher').val();
        if (idVoucher == '') {
            $('#msnInfo').html('Si el pago es con tajeta no puede ser nulo el Id del Vaucher');
            $('#informacionPopUp').modal('show');
            $('#IdVoucher').focus();
            return false;
        }
    }
    if (pago == 'M') {
        var idVoucher = $('#IdVoucher').val();
        var valorTarjeta = $('#valorTarjeta').val();
        $('#IdVoucher').val(idVoucher.trim());
        if (idVoucher.trim() == '') {
            $('#msnInfo').html('Si el pago es Mixto (tarjeta y efectino) no puede ser nulo el Id del Vaucher');
            $('#informacionPopUp').modal('show');
            $('#IdVoucher').focus();
            return false;
        }
        $('#valorTarjeta').val(valorTarjeta.trim());
        if (valorTarjeta.trim() == '') {
            $('#msnInfo').html('Si el pago es Mixto (tarjeta y efectivo) no puede ser nulo el valor del pago en TARJETA');
            $('#informacionPopUp').modal('show');
            $('#IdVoucher').focus();
            return false;
        }

    }
    return true;
}

function buscaCodigoXIdProducto(id) {
    var codigo = "";
    var datos = new Object();
    datos.dska_dska = id;
    $.ajax({
        data: datos,
        async: false,
        url: RutaSitio + "/buscaProdXId.action",
        success: function (data, textStatus, jqXHR) {
            var datosRta = JSON.parse(data);
            codigo = datosRta.objeto.dska_cod;
        }
    });
    return codigo;
}


function simulaMovimientoscontables() {
    var productos = $('.id_prod_fact');
    var productosArray = "[";
    var bandera = false;
    $.each(productos, function (key, value) {
        if (key != '0') {
            productosArray += ",";
        }
        productosArray += "{";
        productosArray += '\"dska_dska\" : \"' + value.value + '\",';
        productosArray += '\"dska_cant\" : \"' + $(value).data('cant') + '\",';
        productosArray += '\"descuento\" : \"' + $(value).data('desc') + '\"';
        bandera = true;
        productosArray += "}";
    });
    productosArray += "]";
    alert(productosArray);
    if (!bandera) {
        $('#msnInfo').html('Por Favor agregar al menos un producto a la lista de productos para poder facturar');
        $('#informacionPopUp').modal('show');
    } else {
        var datos = new Object();
        datos.productosArray = productosArray;
        $.ajax({
            url: RutaSitio + "/SimulaMoviContables.action",
            cache: false,
            dataType: 'json',
            data: datos,
            success: function (data, textStatus, jqXHR) {
                alert('llego aqui');
            }
        });

    }

}
//Se debe llamar la funcion facturar en el momento que acepte el asiento a realizar

