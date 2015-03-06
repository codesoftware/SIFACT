$(document).ready(function(){
    $('#codigoBarras').keypress(function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            var datos = new Object();
            datos.codigoBarras = $('#codigoBarras').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: RutaSitio +'/traeProducto.html',
                success: function(response) {
                    var datos = JSON.parse(response);
                    if(datos.respuesta == 'OK' ){
                        $('#filaConsultaProd').remove();
                        var tbody = '<tr id=\"filaConsultaProd\" >'+
                                        '<td>Descripcion</td>' + 
                                        '<td>'+datos.objeto.dska_desc +'</td>'+
                                        '<td>Nombre</td>'+
                                        '<td>'+datos.objeto.dska_nom_prod + '</td>'+
                                    '</tr>';
                        $('#bodyConsulta').append(tbody);
                    }else{
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

function adicionaProducto(){
    var valida = validaDatos();
    if(valida){
        alert('Agrego el producto a la lista de facturacion');
    }
}

function validaDatos(){
    var codigo = $('#codigoBarras').val();
    var cantidad = $('#cantidad').val();
    if(codigo == ''){    
        $('#msnInfo').html('El campo codigo no puede ser nulo');
        $('#informacionPopUp').modal('show');        
        return false;
    }
    if(cantidad == ''){
        $('#msnInfo').html('El campo cantidad no puede ser nulo');
        $('#informacionPopUp').modal('show');        
        return false;
    }
    var cast = parseInt(cantidad);
    if(cast == 0){
        $('#msnInfo').html('El valor de cantidad no puede ser cero');
        $('#informacionPopUp').modal('show');        
        return false;
        
    }
    return true;
}