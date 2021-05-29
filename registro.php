<?php
    
    // Incluir archivo de conexion a la base de datos
    require_once "conexion.php";
    
    // Definir variable e inicializar con valores vacio
    $DNI = $email = $apellido = $nombre = $cuenta = $telefono = $pass = "";
    $pass_err =    $DNI_err = $email_err = $apellido_err = $nombre_err = $cuenta_err = $telefono_err = "";
    
    if($_SERVER["REQUEST_METHOD"] == "POST"){

         //validando el input de nombre
         if(empty(trim($_POST["nombre"]))){  //mediante el metodo post conectamos las variables (si la variable esta en blanco nos muestre el mensaje mediante la variable error)

            $nombre_err = "Debe ingresar su nombre";
        }else{
            $nombre = trim($_POST["nombre"]);
        }

        //validando el input de apellido
        if(empty(trim($_POST["apellido"]))){  //mediante el metodo post conectamos las variables (si la variable esta en blanco nos muestre el mensaje mediante la variable error)

            $apellido_err = "Debe ingresar su apellido";
        }else{
            $apellido = trim($_POST["apellido"]);
        }
        

        
        // VALIDANDO INPUT DE DNI
        if(empty(trim($_POST["DNI"]))){
            $DNI_err = "Por favor, ingrese su DNI";
        }else{
            //prepara una declaracion de seleccion
            $sql = "SELECT DNI FROM CLIENTS WHERE DNI = ?";
            
            if($stmt = mysqli_prepare($link, $sql)){
                mysqli_stmt_bind_param($stmt, "s", $param_DNI);
                
                $param_DNI = trim($_POST["DNI"]);
                
                if(mysqli_stmt_execute($stmt)){
                    mysqli_stmt_store_result($stmt);
                    
                    if(mysqli_stmt_num_rows($stmt) == 1){
                        $DNI_err = "Este DNI ya está registrado";
                    }else{
                        $DNI = trim($_POST["DNI"]);
                    }
                }else{
                    echo "Ups! Algo salió mal, inténtalo mas tarde";
                }
            }
        }
        
        
        // VALIDANDO INPUT DE EMAIL
        if(empty(trim($_POST["email"]))){
            $email_err = "Por favor, ingrese un correo";
        }else{
            //prepara una declaracion de seleccion
            $sql = "SELECT DNI FROM CLIENTS WHERE CORREU = ?";
            
            if($stmt = mysqli_prepare($link, $sql)){
                mysqli_stmt_bind_param($stmt, "s", $param_email);
                
                $param_email = trim($_POST["email"]);
                
                if(mysqli_stmt_execute($stmt)){
                    mysqli_stmt_store_result($stmt);
                    
                    if(mysqli_stmt_num_rows($stmt) == 1){
                        $email_err = "Este correo ya está en uso";
                    }else{
                        $email = trim($_POST["email"]);
                    }
                }else{
                    echo "Ups! Algo salió mal, inténtalo mas tarde";
                }
            }
        }
        
        // VALIDANDO INPUT DEL TELEFONO
         if(empty(trim($_POST["telefono"]))){
            $telefono_err = "Por favor, ingrese un telefono";
        }else{
            //prepara una declaracion de seleccion
            $sql = "SELECT DNI FROM CLIENTS WHERE TELEFON = ?";
            
            if($stmt = mysqli_prepare($link, $sql)){
                mysqli_stmt_bind_param($stmt, "s", $param_telefono);
                
                $param_telefono = trim($_POST["telefono"]);
                
                if(mysqli_stmt_execute($stmt)){
                    mysqli_stmt_store_result($stmt);
                    
                    if(mysqli_stmt_num_rows($stmt) == 1){
                        $telefono_err = "Este telefono ya está en uso";
                    }else{
                        $telefono = trim($_POST["telefono"]);
                    }
                }else{
                    echo "Ups! Algo salió mal, inténtalo mas tarde";
                }
            }
        }
        
                // VALIDANDO CONTRASEÑA
                if(empty(trim($_POST["pass"]))){
                    $pass_err = "Por favor, ingrese una contraseña";
                }elseif(strlen(trim($_POST["pass"])) < 7){
                    $pass_err = "La contraseña debe de tener al menos 8 caracteres";
                } else{
                    $pass = trim($_POST["pass"]);
                }
       


        // COMPROBANDO LOS ERRORES DE ENTRADA ANTES DE INSERTAR LOS DATOS EN LA BASE DE DATOS
        if(empty($nombre_err) && empty($apellido_err) && empty($DNI_err) && empty($pass_err)  &&  empty($telefono_err) && empty($email_err)){
            
            $sql = "INSERT INTO CLIENTS (DNI,NOM,COGNOM,TELEFON,CORREU,CONTRASENYA) VALUES (?, ?, ?, ?, ?, ?)";
            
            if($stmt = mysqli_prepare($link, $sql)){
                mysqli_stmt_bind_param($stmt, "ssssss",$param_DNI,$param_nombre,$param_apellido,$param_telefono,$param_email,$param_pass);
                
                // ESTABLECIENDO PARAMETRO
                $param_nombre = $nombre;
                $param_apellido = $apellido;
                $param_DNI = $DNI;
                $param_telefono = $telefono;
                $param_email = $email;
                $param_pass = $pass; 
                
                
                if(mysqli_stmt_execute($stmt)){
                    header("location: sesion.php");
                }else{
                    echo "Algo Salio mal, intentalo despues";
                }
            }
        }
        
        
        
    }
    ?>   



<!DOCTYPE html>
<html lang="es">
    <head>
    <meta charset="UTF-8">
    <title>REGISTRATE</title>
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
          
          <h1 class="title">Registrarse</h1>

          <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">  <!-- Asi el formulario estará esperando la llamada cuando se le de clic al boton de registrarse haga una función-->
              <label for="">Nombres:</label>
              <input type="text" name="nombre">
              <span class="message-error"><?php echo $nombre_err; ?></span>
              
              <label for="">Apellidos:</label>
              <input type="text" name="apellido">
              <span class="message-error"><?php echo $apellido_err; ?></span>
              
              <label for="">DNI:</label>
              <input type="text" name="DNI">
              <span class="message-error"><?php echo $DNI_err; ?></span>
              
              <label for="">Telefono:</label>
              <input type="text" name="telefono">
              <span class="message-error"><?php echo $telefono_err; ?></span>
              
              <label for="">Correo electrónico:</label>
              <input type="email" name="email">
              <span class="message-error"><?php echo $email_err; ?></span>
              <label for="">Contraseña:</label>
              <input type="password" name="pass">
              <span class="message-error"><?php echo $pass_err; ?></span>
              
              <input type="submit" value="Registrarse">
          </form>
         
     
          <span class="text-footer">¿Ya tienes una cuenta? <a href="sesion.php">Inicia sesión.</a></span>
            
        </div>

    </body> 
        
    </html>