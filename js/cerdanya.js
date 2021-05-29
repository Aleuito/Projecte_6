window.addEventListener("load", inicio, false);

function inicio() {
    xml();
    xml1();
    xml2();

}
//////////////////////////////////////////////////////////////////////
function xml() {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            lecturaXML(this);
        }
    };
    xhttp.open("GET", "https://static-m.meteo.cat/content/opendata/ctermini_comarcal.xml", true);
    xhttp.send();
}
//////////////////////////////////////////////////////////////////////
function xml1(){
    
        var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            PREDICCION(this);
        }
    };
    xhttp.open("GET", "https://static-m.meteo.cat/content/opendata/ctermini_comarcal.xml", true);
    xhttp.send();
}
//////////////////////////////////////////////////////////////////////
function xml2(){
    
        var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            PREDICCION2(this);
        }
    };
    xhttp.open("GET", "https://static-m.meteo.cat/content/opendata/ctermini_comarcal.xml", true);
    xhttp.send();
}
//////////////////////////////////////////////////////////////////////

		function lecturaXML(xml) {
		var xmlDoc = xml.responseXML;
		var seccio = document.getElementsByTagName("section");
		var x = xmlDoc.getElementsByTagName("comarca");
		

			var id = x[14].getAttribute("id");

			var nombre = x[14].getAttribute("nomCOMARCA"); 
            
		  
			var nombreCa = x[14].getAttribute("nomCAPITALCO");
       
		seccio[0].innerHTML += 

							"<table class='table table-hover table-dark' id='tabla1'>"+
						
							"<tr class='titulotabla'></tr>"+
							"<th>comarca</th>"+
							"<th>capital de comarca</th>"+
							"</tr>"+

							"<tr class='dentrotabla'></tr>"+
							"<td>"+ nombre +"</td>"+
							"<td>"+ nombreCa +"</td>"+
							"</tr>"+

							"</table>";

		}

		function PREDICCION(xml) {
		var xmlDoc = xml.responseXML;
		var seccio = document.getElementsByTagName("section");
		var variable = xmlDoc.getElementsByTagName("prediccio")[14].getElementsByTagName("variable")[0];
		var variablee = xmlDoc.getElementsByTagName("simbol");
		var idsimbolmati = variable.getAttribute("simbolmati").slice(0,-4);
		var idsimboltarda = variable.getAttribute("simboltarda").slice(0,-4);
		
		        var data = variable.getAttribute("data");
	
				var dia = variable.getAttribute("dia");
				
				var tempmin = variable.getAttribute("tempmin");

				var tempmax = variable.getAttribute("tempmax");
			  
				var simbolmati = variable.getAttribute("simbolmati");

				var simboltarda = variable.getAttribute("simboltarda");

				 

				console.log(idsimbolmati);
				console.log(idsimboltarda);
				
				for (var i = 0; i < xmlDoc.getElementsByTagName("simbol").length; i++) {
					var hola = xmlDoc.getElementsByTagName("simbol")[i];
					console.log(hola.id);
				    console.log(hola);
					console.log(hola.getAttribute("nomsimbol"));
					if(idsimbolmati==hola.id){
					var	hola2 = (hola.getAttribute("nomsimbol"));
						console.log(hola2);

					}if(idsimboltarda==hola.id){
						var	hola3 = (hola.getAttribute("nomsimbol"));
						console.log(hola3);
					}
										
				}
				

			
				// console.log(xmlDoc.getElementsByTagName("simbol")[i].id);			
				// var id = variablee.getAttribute("id");
				// console.log(id);			
		   
		        seccio[1].innerHTML += 

				"<table class='table table-hover table-dark' id='tabla2'>"+
						
						"<tr class='titulotabla2'></tr>"+
						"<th>fecha</th>"+
						"<th>temperatura minima</th>"+
						"<th>temperatura maxima</th>"+
						"<th>nombre de simbolo:</th>"+
						"<th>mañana</th>"+
						"<th>nombre de simbolo:</th>"+
						"<th>tarde</th>"+
						"</tr>"+

						"<tr class='titulotabla2'></tr>"+
						"<td>"+ data +"</td>"+
						"<td>"+ tempmin +"º </td>"+
						"<td>"+ tempmax +"º </td>"+
						"<td>"+ hola2 +"º </td>"+
						"<td><img src='https://static-m.meteo.cat/assets-w3/images/meteors/estatcel/" + simbolmati + "' ><td>"+
						"<td>"+ hola3 +"º </td>"+
						"<td><img src='https://static-m.meteo.cat/assets-w3/images/meteors/estatcel/" + simboltarda + "' ><td>"+
						"</tr>"+

						"</table>";
		}

		    function PREDICCION2(xml) {
			var xmlDoc = xml.responseXML;
			var seccio = document.getElementsByTagName("section");

			var variable = xmlDoc.getElementsByTagName("prediccio")[14].getElementsByTagName("variable")[1];
			var idsimbolmati = variable.getAttribute("simbolmati").slice(0,-4);
			var idsimboltarda = variable.getAttribute("simboltarda").slice(0,-4);

			var data = variable.getAttribute("data");

			var dia = variable.getAttribute("dia");
			
			var tempmin = variable.getAttribute("tempmin");

			var tempmax = variable.getAttribute("tempmax");
			
			var simbolmati = variable.getAttribute("simbolmati");

			var simboltarda = variable.getAttribute("simboltarda");
					

			for (var i = 0; i < xmlDoc.getElementsByTagName("simbol").length; i++) {
				var hola = xmlDoc.getElementsByTagName("simbol")[i];
				console.log(hola.id);
				console.log(hola);
				console.log(hola.getAttribute("nomsimbol"));
				if(idsimbolmati==hola.id){
				var	hola2 = (hola.getAttribute("nomsimbol"));
					console.log(hola2);

				}if(idsimboltarda==hola.id){
					var	hola3 = (hola.getAttribute("nomsimbol"));
					console.log(hola3);
				}
									
			}
				   
				seccio[2].innerHTML += 
		

				"<table class='table table-hover table-dark' id='tabla3'>"+
						
						"<tr class='titulotabla2'></tr>"+
						"<th>fecha</th>"+
						"<th>temperatura minima</th>"+
						"<th>temperatura maxima</th>"+
						"<th>nombre de simbolo:</th>"+
						"<th>mañana</th>"+
						"<th>nombre de simbolo:</th>"+
						"<th>tarde</th>"+
						"</tr>"+

						"<tr class='titulotabla2'></tr>"+
						"<td>"+ data +"</td>"+
						"<td>"+ tempmin +"º </td>"+
						"<td>"+ tempmax +"º </td>"+
						"<td>"+ hola2 +"º </td>"+
						"<td><img src='https://static-m.meteo.cat/assets-w3/images/meteors/estatcel/" + simbolmati + "' ><td>"+
						"<td>"+ hola3 +"º </td>"+
						"<td><img src='https://static-m.meteo.cat/assets-w3/images/meteors/estatcel/" + simboltarda + "' ><td>"+
						"</tr>"+

						"</table>";
		
	}
