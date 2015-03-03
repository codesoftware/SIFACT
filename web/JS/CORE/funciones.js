var RutaSitio = "/SIFACT";
/**
 * Funcion la cual se encarga de abrir un dialogo mediante unos parametros * 
 * 
 */
function abrirDialogoAcCa(idDialog, usuario) {
    document.getElementById('datoUsuario').innerHTML = usuario;
    idDialog = "#" + idDialog;
    var postDialog = document.getElementById('numDialogo').value;
    $(idDialog).dialog({
        modal: true,
        width: 500,
        show: {
            effect: "clip",
            duration: 500
        },
        hide: {
            effect: "Clip",
            duration: 1000
        },
        buttons: {
            Aceptar: function() {
                postDialogAc(postDialog, this);
            },
            Cancelar: function() {
                postDialogCa(postDialog, this);
            }
        }
    });

}

function abrirDialogoInfo(msn) {
    var idDialog = "#informacion";
    document.getElementById('mensaje').innerHTML = msn;
    $(idDialog).dialog({
        modal: true,
        width: 500,
        show: {
            effect: "clip",
            duration: 500
        },
        hide: {
            effect: "fold",
            duration: 1000
        },
        buttons: {
            Aceptar: function() {
                postDialogAc('informacion', this);
            }
        }
    });

}

function cerrarDialog(dialogo) {
    $(dialogo).dialog('close');
}

function eliminarEnter(cadena) {
    var auxiliar = "";
    var tam = cadena.length;
    for (var i = 0; i < tam; i++) {
        if (cadena[i] != '\n') {
            auxiliar += cadena[i];
        }
    }
    return auxiliar;
}

function mostrarMsn() {
    $('.Mensajes').show("slow");
}
function mostrarMsnOk() {
    $('.MensajesOk').show("slow");
}

function replaceAll(text, busca, reemplaza) {
    while (text.toString().indexOf(busca) != - 1)
        text = text.toString().replace(busca, reemplaza);
    return text;
}
//Funcion para validar si una fecha si tiene el formato correcto
function validaFechaDDMMAAAA(fecha) {
    var dtCh = "/";
    var minYear = 1900;
    var maxYear = 2100;
    function isInteger(s) {
        var i;
        for (i = 0; i < s.length; i++) {
            var c = s.charAt(i);
            if (((c < "0") || (c > "9")))
                return false;
        }
        return true;
    }
    function stripCharsInBag(s, bag) {
        var i;
        var returnString = "";
        for (i = 0; i < s.length; i++) {
            var c = s.charAt(i);
            if (bag.indexOf(c) == -1)
                returnString += c;
        }
        return returnString;
    }
    function daysInFebruary(year) {
        return (((year % 4 == 0) && ((!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28);
    }
    function DaysArray(n) {
        for (var i = 1; i <= n; i++) {
            this[i] = 31
            if (i == 4 || i == 6 || i == 9 || i == 11) {
                this[i] = 30
            }
            if (i == 2) {
                this[i] = 29
            }
        }
        return this
    }
    function isDate(dtStr) {
        var daysInMonth = DaysArray(12)
        var pos1 = dtStr.indexOf(dtCh)
        var pos2 = dtStr.indexOf(dtCh, pos1 + 1)
        var strDay = dtStr.substring(0, pos1)
        var strMonth = dtStr.substring(pos1 + 1, pos2)
        var strYear = dtStr.substring(pos2 + 1)
        strYr = strYear
        if (strDay.charAt(0) == "0" && strDay.length > 1)
            strDay = strDay.substring(1)
        if (strMonth.charAt(0) == "0" && strMonth.length > 1)
            strMonth = strMonth.substring(1)
        for (var i = 1; i <= 3; i++) {
            if (strYr.charAt(0) == "0" && strYr.length > 1)
                strYr = strYr.substring(1)
        }
        month = parseInt(strMonth)
        day = parseInt(strDay)
        year = parseInt(strYr)
        if (pos1 == -1 || pos2 == -1) {
            return false
        }
        if (strMonth.length < 1 || month < 1 || month > 12) {
            return false
        }
        if (strDay.length < 1 || day < 1 || day > 31 || (month == 2 && day > daysInFebruary(year)) || day > daysInMonth[month]) {
            return false
        }
        if (strYear.length != 4 || year == 0 || year < minYear || year > maxYear) {
            return false
        }
        if (dtStr.indexOf(dtCh, pos2 + 1) != -1 || isInteger(stripCharsInBag(dtStr, dtCh)) == false) {
            return false
        }
        return true
    }
    if (isDate(fecha)) {
        return true;
    } else {
        return false;
    }
}

function remplazaGuion(valor, id) {
    var nuevoVal = valor.replace('\'', '-');
    document.getElementById(id).value = nuevoVal;
}

function validarEnter(e,valor) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        despuesEnter(valor);
    }
}

/**
 * Funcion la cual remplaza todas las coincidencias de un texto
 */
function replaceAll(text, busca, reemplaza) {
    while (text.toString().indexOf(busca) != - 1)
        text = text.toString().replace(busca, reemplaza);
    return text;
}


function eliminarPuntos(cadena) {
    var auxiliar = "";
    var tam = cadena.length;
    for (var i = 0; i < tam; i++) {
        if (cadena[i] != '\.') {
            auxiliar += cadena[i];
        }
    }
    return auxiliar;
}