<?php
	$servername = "localhost";
    $username   = "root";
  	$password   = "";
  	$dbname     = "equipament";

	$conn = new mysqli($servername, $username, $password, $dbname);
      if($conn->connect_error){
        die("Conexión fallida: ".$conn->connect_error);
      }

    $salida = "";
	$salida2 = "";
	$salida3 = "";

    $query = "SELECT * FROM botes ORDER By id_botes";
	$query2 = "SELECT * FROM pals ORDER By id_pals";
	$query3 = "SELECT * FROM esquis ORDER By id_esquis";

    if (isset($_POST['consulta'])) {
    	$q = $conn->real_escape_string($_POST['consulta']);
    	$query = "SELECT * FROM botes WHERE id_botes LIKE '%$q%' OR  nom LIKE '%$q%' OR talla LIKE '%$q%' OR preu LIKE '%$q%' ";
    }

    $resultado = $conn->query($query);

    if ($resultado->num_rows>0) {
    	$salida.="<table class='table table-hover table-dark' id='tablaBotas' >
    			<thead>
    				<tr>
    					<td>ID BOTAS</td>
    					<td>NOMBRE</td>
    					<td>TALLA</td>
    					<td>PRECIO</td>
    				</tr>

    			</thead>
    			

    	<tbody>";

    	while ($fila = $resultado->fetch_assoc()) {
    		$salida.=" <tr>
    					<td>".$fila['id_botes']."</td>
    					<td>".$fila['nom']."</td>
    					<td>".$fila['talla']."</td>
    					<td>".$fila['preu']."</td>
    				</tr>";

    	}
    	$salida.="</tbody></table>";
    }

	echo $salida;



	if (isset($_POST['consulta'])) {
    	$a = $conn->real_escape_string($_POST['consulta']);
    	$query2 = "SELECT * FROM pals WHERE id_pals LIKE '%$a%' OR  nom LIKE '%$a%' OR talla LIKE '%$a%' OR preu LIKE '%$a%' ";
    }

    $resultado2 = $conn->query($query2);

    if ($resultado2->num_rows>0) {
    	$salida2.="<table class='table table-hover table-dark' id='tablaBotas' >
    			<thead>
    				<tr>
    					<td>ID PALOS</td>
    					<td>NOMBRE</td>
    					<td>TALLA</td>
    					<td>PRECIO</td>
    				</tr>

    			</thead>
    			

    	<tbody>";

    	while ($fila2 = $resultado2->fetch_assoc()) {
    		$salida2.=" <tr>
    					<td>".$fila2['id_pals']."</td>
    					<td>".$fila2['nom']."</td>
    					<td>".$fila2['talla']."</td>
    					<td>".$fila2['preu']."</td>
    				</tr>";

    	}
    	$salida2.="</tbody></table>";
    }
	echo $salida2;




	if (isset($_POST['consulta'])) {
    	$b = $conn->real_escape_string($_POST['consulta']);
    	$query3 = "SELECT * FROM esquis WHERE id_esquis LIKE '%$b%' OR  nom LIKE '%$b%' OR talla LIKE '%$b%' OR preu LIKE '%$b%' ";
    }

    $resultado3 = $conn->query($query3);

    if ($resultado3->num_rows>0) {
    	$salida3.="<table class='table table-hover table-dark' id='tablaBotas' >
    			<thead>
    				<tr>
    					<td>ID ESQUIS</td>
    					<td>NOMBRE</td>
    					<td>TALLA</td>
    					<td>PRECIO</td>
    				</tr>

    			</thead>
    			

    	<tbody>";

    	while ($fila3 = $resultado3->fetch_assoc()) {
    		$salida3.=" <tr>
    					<td>".$fila3['id_esquis']."</td>
    					<td>".$fila3['nom']."</td>
    					<td>".$fila3['talla']."</td>
    					<td>".$fila3['preu']."</td>
    				</tr>";

    	}
    	$salida3.="</tbody></table>";
    }



    echo $salida3;

    $conn->close();



?>