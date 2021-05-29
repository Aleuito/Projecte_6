<?php

    //$conexion=mysqli_connect('localhost', 'root', '','gym');
    //mysqli_set_charset($conexion,"utf8");
    //if($conexion === false){
    //    die("no se pudo conetar" . mysqli_connect_error());
    //}else{
    //    echo 'se ha conectado';
    //}


    define('DB_SERVER','localhost');
    define('DB_USERNAME','root');
    define('DB_PASSWORD','');
    define('DB_NAME','EQUIPAMENT');

    $link = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_NAME);
    mysqli_set_charset($link,"utf8");
    if($link === false){
        die("error de conexion" . mysqli_connect_error());
    }

    
?>