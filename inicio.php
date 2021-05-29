<?php
require_once "conexion.php";
session_start();

if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: index.html");
    exit;
}
   // echo ($_SESSION["emaill"]);
?>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>REGISTRATE</title>
        <link rel="stylesheet" href="iconos/css/fontello.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/inicio2.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </head>
    <body>

        <header> 

            <nav class="navbar navbar-dark bg-dark">
                      
                      <a class="navbar-brand" href="#"></a>
                      <a class="nav-link" href="index.html">Home</a>
                      <a class="nav-link" href="productos.php">Stock</a>
                      <select class="form-select form-select-sm" id="desplegar" aria-label=".form-select-sm example">
                      <option selected  value="">Weather</option>
             <option value="aran.html">Val d’Aran</option>
             <option value="riba.html">Alta Ribagorça</option>
             <option value="pallars.html">Pallars Sobirà</option>
             <option value="urgell.html">Alt Urgell</option>
             <option value="cerdanya.html">Cerdanya</option>
                       </select>
                      <a class="nav-link" href="sesion.php">Log in</a>
           
                   <form class="form-inline my-2 my-lg-0">
                   <input class="form-control mr-sm-2" id="caja_busqueda" type="search" placeholder="Search" aria-label="Search">
                   <button class="btn btn-outline-light" type="submit">Search</button>
                   </form>
           
             </nav>

        </header>
      
        <script>
$('select#desplegar').change(function(){
    window.location = $(this).val();
});
</script>  






       
    </body> 
           <?php
            $D=$_SESSION["DNI"];
            $sql="SELECT CLIENTS.DNI,CLIENTS.NOM, CLIENTS.COGNOM,CLIENTS.TELEFON,CLIENTS.CORREU,CLIENTS.CONTRASENYA,CLIENTS.CARNET_FEDERAT,CLIENTS.CARNET_FAMILIAR FROM CLIENTS WHERE CLIENTS.DNI =  '".$D."'";
            
            $result= mysqli_query($link,$sql);
            
            while($mostrar=mysqli_fetch_array($result)) {
                
            ?>



<div class="d-flex justify-content-center" id="divCuerpo"> 
  <div class='item'>
  <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post"> 
    <img src='https://static2.abc.es/media/summum/2019/11/06/KV_W-2019-20_BergeWinterSchneePisteSonneFahrer(C)ThreePieceMedia.jpg(14)-kkF--1200x630@abc.jpg' id="img1">
        <div class='itemInfo'>
            <br>
            <div class="row">
                <div class="col">
                    <h1>DNI:&nbsp<span class="info"><?php echo $mostrar['DNI']?></span></h1>
                    <br>
                    <h1>NOMBRE:&nbsp<span class="info"><?php echo $mostrar['NOM']?></span></h1>
                </div>

                <div class="col">
                    <h2>APELLIDOS:&nbsp<span class="info"><?php echo $mostrar['COGNOM']?></span></h2>
                    <br>
                    <h2>TELEFONO:&nbsp<span class="info"><?php echo $mostrar['TELEFON']?></span></h2>
                </div>
            </div>


            <br>
            <div class="row">
                <div class="col">
                    <h3>EMAIL:&nbsp<span class="info"><?php echo $mostrar['CORREU']?></span></h3>
                    <br>
                    <h3>CONTRASEÑA:&nbsp<span class="info"><?php echo $mostrar['CONTRASENYA']?></span></h3>
                </div>

                <div class="col">
                    <h4>EXTRA:&nbsp<span class="info"><?php echo $mostrar['CONTRASENYA']?></span></h4>
                    <br>
                    <h4>EXTRA:&nbsp<span class="info"><?php echo $mostrar['CONTRASENYA']?></span></h4>
                </div>
                
            </div>
            <span  style="margin-left: 170px;" class="text-footer">Grcias por tus compras.<a href="cerrar-sesion.php" class="close-sesion">Cerrar Sesión</a></span>
        </div>
  </div>
</div>



            <?php
            }
           ?>
      
        



            
    </html>