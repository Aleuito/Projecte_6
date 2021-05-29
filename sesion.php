<?php 
//condicion de redireccionamiento de pagina (si se inicia sesion, que nos lleve a la pagina de inicio de los socios)
session_start();
if(isset($_SESSION["loggedin"]) && $_SESSION ["loggedin"]== true){
    header("location: inicio.php");
    exit;
}
//solcitando la conexion
require_once "conexion.php";
//inicializando variables
$DNI = $password = "";
$DNI_err = $password_err = "";
//si el formulario se envia:
if($_SERVER["REQUEST_METHOD"] === "POST"){
    //si el campo "DNI" esta vacio, que nos muestre el siguiente error,sino que se ejecute:
    if(empty(trim($_POST["DNI"]))){
        $DNI_err = "Por favor, ingrese su DNI";
    }else{
        $DNI = trim($_POST["DNI"]);
    }
    //si el campo "password" esta vacio, que nos muestre el siguiente error, sino que se ejecute:
    if(empty(trim($_POST["password"]))){
        $password_err = "Por favor, ingrese su constraseña";
    }else{
        $password = trim($_POST["password"]);
    }



    //validar credenciales
    if(empty($DNI_err) && empty($password_err)){
        
        $sql = "SELECT DNI,CONTRASENYA FROM CLIENTS WHERE DNI = ?";
        
        if($stmt = mysqli_prepare($link, $sql)){
            
            mysqli_stmt_bind_param($stmt, "s", $param_DNI);
            
            $param_DNI = $DNI;
            
            if(mysqli_stmt_execute($stmt)){
                mysqli_stmt_store_result($stmt);
            }
            
            if(mysqli_stmt_num_rows($stmt) == 1){
                mysqli_stmt_bind_result($stmt,$DNI,$password);
                if(mysqli_stmt_fetch($stmt)){
                    
                    if($password == $_POST["password"]){ //Aqui el verifica si la contraseña es correcta
                        session_start();
                        
                        // ALMACEAR DATOS EN VARABLES DE SESION
                        $_SESSION["loggedin"] = true;
                        
                        $_SESSION["DNI"] = $DNI;
                        
                        header("location: inicio.php");
                    }else{
                        $password_err = "La contraseña que has introducido no es valida";
                    }
                    
                } 
            }else{
                    $DNI_err = "No se ha encontrado ninguna cuenta con ese DNI.";
                }
            
        }else{
                    echo "UPS! algo salio mal, intentalo mas tarde";
                }
        
    }
    
    mysqli_close($link);
    
}


?>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>INICIA SESION</title>
    
        <link rel="stylesheet" href="IMAGENES/">
        <link rel="stylesheet" href="iconos/css/fontello.css">
        <link rel="stylesheet" href="iconos2/css/fontello.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">  
        <link rel="stylesheet" href="CSS/formulario.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, user-scalable=no,initial-sclale=1.0,maximum-scale=1.0,minimum-scale=1.0">


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
                 <option value="riba.html">Alta Ribagorça,</option>
                 <option value="pallars.html">Pallars Sobirà</option>
                 <option value="urgell.html">Alt Urgell,</option>
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




    <div class="container-all">  <!-- Aquí estará contenido el formulario y el texto de acompañamiento (todo)-->

        <div class="container-form"> <!-- Aquí se guardara la parte del formulario-->
          
          <h1 class="title">Iniciar sesión</h1>

          <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">  <!-- Asi el formulario estará esperando la llamada cuando se le de clic al boton de registrarse haga una función-->
              <label for="">DNI:</label>
              <input type="text" name="DNI">
              <span class="message-error"><?php echo $DNI_err; ?></span>
              <label for="">Contraseña:</label>
              <input type="password" name="password">
              <span class="message-error"><?php echo $password_err; ?></span>

              <input type="submit" value="Entrar">
          </form>
            
            <span class="text-footer"> Es necesario iniciar sesión para poder realizar tu compra. ¿Aún no tienes una cuenta? <a href="registro.php">Registrate.</a>
            </span>
        </div>


    </div>

    </body> 
        
    </html>