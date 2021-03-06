<!DOCTYPE html>
<html>
    <head>
    
    <title>SPEED WINTER</title>
    <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="iconos/css/fontello.css">
        <link rel="stylesheet" href="CSS/inicio2.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>      
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        
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

	<div id="datos"></div>
    

<div class="social-bar">
        <a href="https://www.facebook.com" class="icon icon-facebook" target="_blank"></a>
        <a href="https://twitter.com" class="icon icon-twitter" target="_blank"></a>
        <a href="https://www.youtube.com" class="icon icon-youtube-play" target="_blank"></a>
        <a href="https://www.instagram.com" class="icon icon-instagram" target="_blank"></a>
        </div>
</body>
</html>