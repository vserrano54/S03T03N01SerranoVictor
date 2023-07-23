$(function() {

	$('.dates #fecha').datepicker({
	'format': 'yyyy-mm-dd',
	'autoclose': true
	});
});
		 
    

const btnNuevo = document.querySelector('#btnNuevo');
const btnAgregar = document.querySelector('#btnAgregar');
const btnguardar = document.querySelector('#btnguardar');
var lista = document.querySelector('#lista');
const txtgrantotal = document.querySelector('#grantotal').value;
let data=[];
let linea=0;
let id_row;
let grantotal=0;


let listaArbolesJson = document.getElementById('jsonArbol').value
let listaFloresJson = document.getElementById('jsonFlor').value
let listaDecoracionJson = document.getElementById('jsonDecoracion').value

let listaArboles = JSON.parse(listaArbolesJson);
let listaFlores = JSON.parse(listaFloresJson);
let listaDecoraciones = JSON.parse(listaDecoracionJson);

let tipoproducto = document.querySelector('#tipoproducto')
let producto = document.querySelector('#producto');
let total =0;


 
 const Recorrer = (lista,lugar) => {
	 let elementos = '<option selected > --Seleccion --</option>'
	for (var i = 0; i < lista.length; i++) {
    var p = lista[i];
    elementos += 
		'<option value="' + p.codigo+'">' + p.nombre + '</option>'
		
	}
	lugar.innerHTML=elementos;

 }
 

        
  tipoproducto.addEventListener('change',() =>{  
	  if (tipoproducto.value==1){
		  Recorrer(listaArboles,producto);  
	  } else if (tipoproducto.value==2){
		    Recorrer(listaFlores,producto);
	  }else{
		   Recorrer(listaDecoraciones,producto);
	  }
  });
  
   btnAgregar.addEventListener('click',() =>{
   desabilitarcampos();
   agregar();

 });
 
 /********************************************************************************* */
 
 function agregar(){
	 
	 
	 const tipoproducto =parseInt(document.querySelector('#tipoproducto').value);	 
	 const idproducto = parseInt(document.querySelector('#producto').value);
	
	
	 //const idCompra=1;
	 
	 switch (tipoproducto) {
    case 1:
        agregarProducto(idproducto, tipoproducto);
        break;
    case 2:
        agregarProducto(idproducto, tipoproducto);
        break;
    case 3:
        agregarProducto(idproducto, tipoproducto);
        break;
    default:
        console.error("Tipo de producto no válido");
}

	 
	 
	
}

 
 function agregarProducto(producto, tipoProducto) {
   
    const cantidad = parseInt(document.querySelector('#cantidad').value);
	const precio = parseFloat(document.querySelector('#precio').value);
    const total=precio*cantidad;
    
    var dataProducto;
    switch (tipoProducto) {
        case 1:
            dataProducto = listaArboles.find(a => a.codigo == producto);
            break;
        case 2:
            dataProducto = listaFlores.find(f => f.codigo == producto);
            break;
        case 3:
            dataProducto = listaDecoraciones.find(d => d.codigo == producto);
            break;
        default:
            return;
    }

    if (!dataProducto) {
        console.error("Producto no encontrado");
        return;
    }

    data.push({
        "linea": linea,
        "tipo": tipoProducto,
        "cod": dataProducto.codigo,
        "nombre": dataProducto.nombre,
        "cantidad": cantidad,
        "precio": precio,
        "total": total,
    });

    var id_row = 'row' + linea;
    var fila = '<tr id="' + id_row + '">' +
        '<td class="ocultar">' + linea + '</td>' +
        '<td>' + tipoProducto + '</td>' +
        '<td>' + dataProducto.codigo + '</td>' +
        '<td>' + dataProducto.nombre + '</td>' +
        '<td>' + cantidad + '</td>' +
        '<td>' + precio + '</td>' +
        '<td>' + total + '</td>' +
        '<td>' +
        '<a href="#" class="btn btn-danger" onclick="eliminar(' + linea + ');"><i class="fa-solid fa-trash-can"></i></a>' +
        '</td>' +
        '</tr>';

    $("#lista").append(fila);

    grantotal += total;
    limpiarCampos();
    document.getElementById('grantotal').value = grantotal;

    linea += 1;
    sumar();
    console.log(linea);
}

/***************************************************************************************** */

 
 
 
 const limpiar = () => {
  for (let i = $select.options.length; i >= 0; i--) {
    $select.remove(i);
  }
};

function limpiarCampos() {
   document.getElementById('tipoproducto').selectedIndex=0;
   
   producto.selectedIndex =0;
   document.getElementById('precio').value='';
   document.getElementById('cantidad').value='';
    
   
  }

function sumar(){
	let tot =0;
	for (x of data){
		tot = tot+x.total;
	}
	document.getElementById('grantotal').value=tot;
}

function eliminar(linea){
	console.log("entro a la funcion eliminar");
	console.log("linea " +linea);
	$("#row"+linea).remove();
	let i=0;
	let pos=0;
	
	for (x of data){
		if (x.linea==linea){
			pos=i;
	
		}
		i++;
	}
	data.splice(linea,1);
	sumar();
	
}


 function enviarDatos() {
	 		console.log("entra a enviar datos");
	 	  // desabilitarOtrosCampos();
           		
			const factura = document.querySelector('#factura').value;
			var fecha = $("#fecha").val();
			
            var datosFactura = {
                "factura": factura,
                "fecha": fecha,
            };

          
            var datosCompletos = {
                "tabla": data,
                "datosFactura": datosFactura
            };

            
            var datosJson = JSON.stringify(datosCompletos);

           
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "VentaController", true);
            xhttp.setRequestHeader("Content-type", "application/json");
            
            
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
          
            var respuestaJson = xhttp.responseText;

           
            var respuestaObjeto = JSON.parse(respuestaJson);

           
            console.log(respuestaObjeto.mensaje); 
        }
    };
	   
    xhttp.send(datosJson);
        }


  btnguardar.addEventListener('click',() =>{
	  
	  
   desabilitarOtrosCampos();
	  
   enviarDatos();


 });
 
function desabilitarcampos(){
	factura.disabled = true;
	fecha.disabled = true;

}

function desabilitarOtrosCampos(){
	
	btnAgregar.disabled = true;
	btnguardar.disable = true;
	
	
	 document.querySelector('#tipoproducto').disabled = true;	 
	 document.querySelector('#producto').disabled = true;
	 document.querySelector('#cantidad').disabled = true;
	 document.querySelector('#precio').disabled = true;
	
}
