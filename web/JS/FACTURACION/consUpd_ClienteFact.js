$(function() {
});

function postDialogAc(id, dialog) {
    cerrarDialog(dialog);
}

function consultaCliente() {
    var datosOk = validaDatos();
    if (datosOk == true) {
        document.getElementById("Fac_consClienteID").submit();
    }
}

function insertar() {
    document.getElementById("Fac_nuevoCliente").submit();
}

function validaDatos() {
    var cedula = $('#cedulaCliente');
    if (!$.isNumeric(cedula.val())) {
        $('#textoMsn').html('El campo Cedula debe ser numerico o no puede ser nulo');
        $('#mensaje').modal('show');
        return false;
    }
    return true;
}


function enviaClienteGenerico(){
    $('#cedulaCliente').val('0');
    consultaCliente();
}