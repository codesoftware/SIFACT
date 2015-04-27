
function generarFactura(id) {
    var url = "fact_GeneraFactura.action?factura.fact_fact=" + id ;
    window.open(url, "_blank", "directories=no, status=no,width=400, height=300,top=0,left=0");
}

function generaRemision(id){
    var url = "fact_GeneraRemision.action?remision.rmce_trans=" + 10 ;
    window.open(url, "_blank", "directories=no, status=no,width=400, height=300,top=0,left=0");
}