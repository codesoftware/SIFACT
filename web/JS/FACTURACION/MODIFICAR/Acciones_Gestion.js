
function generarFactura(id) {
    var url = "fact_GeneraFactura.html?factura.fact_fact=" + id ;
    window.open(url, "_blank", "directories=no, status=no,width=400, height=300,top=0,left=0");
}

function generaRemision(id){
    alert('Genero Remision');
}