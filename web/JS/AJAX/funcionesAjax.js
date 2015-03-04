
$(document).ready(function () {
    $('#codigoBarras').keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            var datos = new Object();
            datos.codigoBarras = $('#codigoBarras').val();
            $.ajax({
                type: 'GET',
                data: datos,
                url: '${pageContext.request.contextPath}/traeProducto.html',
                success: function (response) {
                    $('.result').html(response);
                }
            });
        }
    });
});