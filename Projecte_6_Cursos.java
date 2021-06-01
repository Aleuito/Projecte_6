
package projecte_6_cursos;

//realizando los imports necesarios
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.sql.Statement;
import java.sql.SQLException;
import static projecte_6_cursos.CursosEsqui.p;

public class Projecte_6_Cursos extends Application {
    
    /*CONNEXIO BASE DE DADES*/
    Connection conexio = ConnexioMYSQL.getConnection();
    
    //declaracio de les calsses utilitzades
    CursosEsqui curs;
    Persona p;
    
    //parts principals del programa
    Scene miEscena;
    BorderPane contenedor;
    Pane addPaneTitol, PaneClients, PaneCursos, PaneLlogats, PaneLlogar, addPaneTitolLlogar;
    VBox VBoxLeft;
    
    //Botons i textos
    Button btnAlquilar, btnCancelar, btnSalir;
    Label lblIdCurs, lblDni, lblCheckBox;
    TextField txtIdCurs, txtDni;
    CheckBox una, dos, tres, dia;
    
    //creem el hashmap on guardarem tots els cursos llogats del dia
    HashMap <CursosEsqui, Integer> llistat = new HashMap <CursosEsqui, Integer> ();
    
    //declarem les dos observable list que farem servir
    private ObservableList<Persona> perPersona;
    private ObservableList<CursosEsqui> cursos;
    private ObservableList<CursosEsqui> llogats;
    
    //declarem les dos table views que utilitzarem
    private TableView<Persona> taulaClients;
    private TableView<CursosEsqui> taulaCursos;
    private TableView<CursosEsqui> taulaCursosLlogats;

    //metodo main obrir finestra
    public static void main(String[] args) {
         Application.launch(args);
    }
    
    @Override
    public void start(Stage miEscenario){
        //declarem el contenedor principal on afegirem totes els altres contenedors
        contenedor = new BorderPane();
        
        //afegim el pane del titol i el coloquem al top
        addPaneTitol = new Pane();
        addPaneTitol = addPane();
        contenedor.setTop(addPaneTitol);
        
        //cridem el pane per llogar cursos i el coloquem al centre
        PaneLlogar = new Pane();
        PaneLlogar = addPaneCursosLlogar();
        contenedor.setCenter(PaneLlogar);
        
        //afegim el pane de la taula dels cursos llogats i la coloquem a la dreta
        PaneLlogats = new Pane();
        PaneLlogats = addPaneCursosLlogats();
        contenedor.setRight(PaneLlogats);
        
        //cridem els panes on tenim els cursos disponibles i els clients registrats i els coloquem a la dreta
        VBoxLeft = new VBox();
        VBoxLeft = panelEsquerra();
        contenedor.setLeft(VBoxLeft);
        
        //creem una escena amb el contenedor que conte tota la informació
        miEscena = new Scene(contenedor);
        
        //posem els atributs que volem al nostre escenari, li afegim l'escena, i el show per mostrar el que hem creat
        miEscenario.setTitle("Aplicació Java Empresa Esquis");
        miEscenario.setMinWidth(1300);
        miEscenario.setHeight(700);
        
        miEscenario.setScene(miEscena);
        
        miEscenario.show();
    };

    //metode on creem el Pane del titol
    public Pane addPane(){
        
        addPaneTitol = new Pane();
        
        Text titulo = new Text("Empresa Cursos Esqui");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        
        titulo.setTranslateX(450);
        titulo.setTranslateY(50);
        
        btnSalir= new Button("SORTIR APLICACIÓ");
        btnSalir.setMinWidth(100);
        btnSalir.setMinHeight(20);
        btnSalir.setTranslateX(1160);
        
        btnSalir.setOnAction(e-> tancar());
        
        addPaneTitol.setMinHeight(90);
        
        addPaneTitol.getChildren().addAll(titulo, btnSalir);
         
        return addPaneTitol;        
    }
    
    //metode on creem el Pane de la tableView on mostrem es clients
    public Pane addPaneClients(){
        
        PaneClients = new Pane();
        
        perPersona = FXCollections.observableArrayList();
        
        Text titulo = new Text("Clients");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTranslateX(150);
        
        //creem taula i creem les columnes de la taula
        taulaClients = new TableView();
        TableColumn<Persona, String> colDni = new TableColumn<>("DNI");
        TableColumn<Persona, String> colNom = new TableColumn<>("NOM");
        TableColumn<Persona, String> colCognom = new TableColumn<>("COGNOM");
        TableColumn<Persona, String> colCorreu = new TableColumn<>("E-MAIL");
        TableColumn<Persona, String> colTelef = new TableColumn<>("TELEFON");
        
        //agreguem les columnes a la taula clients
        taulaClients.getColumns().addAll(colDni, colNom, colCognom, colCorreu, colTelef);
        
        //li atribuim latribut de la classe a cada columna
        colDni.setCellValueFactory(new PropertyValueFactory("dni"));
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colCognom.setCellValueFactory(new PropertyValueFactory("cognom"));
        colCorreu.setCellValueFactory(new PropertyValueFactory("correu"));
        colTelef.setCellValueFactory(new PropertyValueFactory("telefon"));
        
        //creem un objecte persona
        Persona p = new Persona();
        
        //obtenim els clients en un observable list del metodo getPersonas
        perPersona = p.getPersonas(conexio);
        
        //posem que la taula mostri els clients guardats en l'observable
        taulaClients.setItems(perPersona);
        
        //establim una alçada i llargada a la taula clients
        taulaClients.setMaxHeight(220);
        taulaClients.setMinWidth(400);
        
        //establir espai entre titol itaula clients
        taulaClients.setTranslateY(12);
        
        //posem que la taula automaticament ocupi els espais
        taulaClients.autosize();
        
        //agreguem al pane el titol i la taula dels clients
        PaneClients.getChildren().addAll(titulo, taulaClients);
         
        return PaneClients;        
    }
    
    //metode on creem el Pane dels cursos disponibles i els mostrem
    public Pane addPaneCursos(){
        
        PaneCursos = new Pane();
        
        cursos = FXCollections.observableArrayList();
        
        Text titulo = new Text("Cursos");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTranslateX(150);
        
        taulaCursos= new TableView();
        TableColumn<CursosEsqui, String> colIdCurs = new TableColumn<>("ID CURS");
        TableColumn<CursosEsqui, String> colNomCurs = new TableColumn<>("NOM CURS");
        TableColumn<CursosEsqui, Double> colPreuHota = new TableColumn<>("PREU/HORA");
        TableColumn<CursosEsqui, String> colCarnet = new TableColumn<>("CARNET");
        
        taulaCursos.getColumns().addAll(colIdCurs, colNomCurs, colPreuHota, colCarnet);
        
        colIdCurs.setCellValueFactory(new PropertyValueFactory("idCurs"));
        colNomCurs.setCellValueFactory(new PropertyValueFactory("NomCurs"));
        colPreuHota.setCellValueFactory(new PropertyValueFactory("preu_hora"));
        colCarnet.setCellValueFactory(new PropertyValueFactory("carnet"));
        
        CursosEsqui c = new CursFamiliar();
        
        cursos = c.mostrar_cursos(conexio);
        
        taulaCursos.setItems(cursos);
        
        //establim una alçada i llargada a la taula cursos disponibles
        taulaCursos.setMaxHeight(150);
        taulaCursos.setMinWidth(400);
        
        //establir espai entre titol itaula clients
        taulaCursos.setTranslateY(12);
        
        //posem que la taula automaticament ocupi els espais
        taulaCursos.autosize();
        
        PaneCursos.getChildren().addAll(titulo, taulaCursos);
         
        return PaneCursos;        
    }
    
    //metode on creem el Pane dels cursos llogats i els mostrem    
    public Pane addPaneCursosLlogats(){
        
        PaneLlogats = new Pane();
        
        llogats = FXCollections.observableArrayList();
        
        Text titulo = new Text("Cursos Llogats");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTranslateX(150);
        
        taulaCursosLlogats= new TableView();
        TableColumn<CursosEsqui, String> colIdCurs = new TableColumn<>("ID CURS");
        TableColumn<CursosEsqui, String> colDni = new TableColumn<>("DNI");
        TableColumn<CursosEsqui, Date> colData = new TableColumn<>("DATA COMPRA");
        TableColumn<CursosEsqui, Integer> colHora = new TableColumn<>("HORES");
        TableColumn<CursosEsqui, Double> colDescompte = new TableColumn<>("DESCOMPTE");
        TableColumn<CursosEsqui, Double> colPreu_final = new TableColumn<>("PREU FINAL");
        
        taulaCursosLlogats.getColumns().addAll(colDni, colIdCurs, colData, colHora, colDescompte, colPreu_final);
        
        colDni.setCellValueFactory(new PropertyValueFactory("dni"));
        colIdCurs.setCellValueFactory(new PropertyValueFactory("idCurs"));
        colData.setCellValueFactory(new PropertyValueFactory("datacompra"));
        colHora.setCellValueFactory(new PropertyValueFactory("hores"));
        colDescompte.setCellValueFactory(new PropertyValueFactory("descompte"));
        colPreu_final.setCellValueFactory(new PropertyValueFactory("preu_final"));
        
        CursosEsqui c = new CursFamiliar();
        
        llogats = c.cursos_comprats(conexio);
        
        taulaCursosLlogats.setItems(llogats);
        
        //establim una alçada i llargada a la taula cursos disponibles
        taulaCursosLlogats.setMaxHeight(400);
        taulaCursosLlogats.setMinWidth(400);
        
        //establir espai entre titol itaula clients
        taulaCursosLlogats.setTranslateY(12);
        
        PaneLlogats.setPadding(new Insets(10,10,10,10));
        
        //posem que la taula automaticament ocupi els espais
        taulaCursosLlogats.autosize();
        
        PaneLlogats.getChildren().addAll(titulo, taulaCursosLlogats);
         
        return PaneLlogats;        
    }
    
    //metode on creem el Pane amb les opcions per llogar els cursos
    public Pane addPaneCursosLlogar(){
        
        PaneLlogar = new Pane();
        
        Text titulo = new Text("Lloga el teu curs!");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTranslateY(10);
        titulo.setTranslateX(120);
        
        lblIdCurs = new Label("ID Curs:");
        lblIdCurs.setTranslateX(80);
        lblIdCurs.setTranslateY(40);
        
        txtIdCurs = new TextField();
        txtIdCurs.setTranslateX(150);
        txtIdCurs.setTranslateY(40);
        
        lblDni = new Label("DNI:");
        lblDni.setTranslateX(80);
        lblDni.setTranslateY(80);
        
        txtDni = new TextField();
        txtDni.setTranslateX(150);
        txtDni.setTranslateY(80);
        
        lblCheckBox = new Label("Selecciona les HORES del curset:");
        lblCheckBox.setTranslateX(80);
        lblCheckBox.setTranslateY(120);
       
        una = new CheckBox("1 Hora");
        una.setTranslateX(80);
        una.setTranslateY(160);
        
        dos = new CheckBox("2 Hores");
        dos.setTranslateX(80);
        dos.setTranslateY(200);
        
        tres = new CheckBox("3 Hores");
        tres.setTranslateX(80);
        tres.setTranslateY(240);
        
        dia = new CheckBox("7 Hores");
        dia.setTranslateX(80);
        dia.setTranslateY(280);
        
        btnAlquilar = new Button("LLOGAR");
        btnAlquilar.setMinWidth(100);
        btnAlquilar.setTranslateX(80);
        btnAlquilar.setTranslateY(320);
        
        btnCancelar = new Button("CANCELAR");
        btnCancelar.setMinWidth(100);
        btnCancelar.setTranslateX(190);
        btnCancelar.setTranslateY(320);
        
        PaneLlogar.setPadding(new Insets(10,10,10,10));
        
        btnAlquilar.setOnAction(e-> Alquilar());
        btnCancelar.setOnAction(e-> Cancelar());
        
        PaneLlogar.getChildren().addAll(titulo, lblIdCurs, txtIdCurs, lblDni, txtDni, lblCheckBox, una, dos, tres, dia, btnAlquilar, btnCancelar); 
        
        return PaneLlogar;        
    }
    
    //metode vertical box costat esquerra per posar clients i cursos disponibles
    public VBox panelEsquerra(){
        
        VBoxLeft = new VBox();
        
        PaneClients = addPaneClients();
        PaneCursos = addPaneCursos();
        
        VBoxLeft.setPadding(new Insets(10,10,10,10));
        VBoxLeft.setSpacing(20);
        
        VBoxLeft.getChildren().addAll(PaneClients, PaneCursos);
        
        return VBoxLeft;
    }
    
    //accio al clicar cancelar
    public void Cancelar(){
            //borra les dades seleccionades en l'apartat llogar curs
            this.txtDni.clear();
            this.txtIdCurs.clear();
            this.una.setSelected(false);
            this.dos.setSelected(false);
            this.tres.setSelected(false);
            this.dia.setSelected(false);
            //missatge conforme heu cancelat el proces que portaveu fent
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("INFORMACIO");
            alert.setContentText("Heu cancelat la vostra seleccio");
            alert.showAndWait();
    }
    
    //Accio al clicar Alquilar 
    public void Alquilar(){
        
        int hora = 0;
        double preu = 0;
        double descompte = 0;
        
        curs = new CursFamiliar();
        p = new Persona();
        //agafem per variable els valors seleccionats en l'apartat de llogar curs
        String idCurs = this.txtIdCurs.getText();
        String dni = this.txtDni.getText();
        
        if (una.isSelected()){
            hora = 1;
            descompte = 20;
        }
        if (dos.isSelected()){
            hora = 2;
            descompte = 20;
        }
        if (tres.isSelected()){
            hora = 3;
            descompte = 30;
        }
        if (dia.isSelected()){
            hora = 7;
            descompte = 50;
        }
        //comprovem si l'id del curs existeix a la base de dades
        if(curs.comprovarIdCurso(conexio, idCurs)){
            //comprovem si el dni existeix a la base de de dades
            if(p.comprovarDni(conexio, dni)){
                //calculem el preu del curs a partit de les seleccions realitzades
                preu = curs.getPreu(conexio, idCurs, hora, dni);
                //si la funcio getPreu no es 0, realitzi les accions següents
                if (preu!=0){
                    //mostrem missatge de curs llogat correctament
                    Alert alert = new Alert (Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("INFORMACIO");
                    alert.setContentText("Moltes gracies. Acaba de llogar el Curs "+idCurs+" amb un cost de "+preu);
                    alert.showAndWait();
                    //Mirem si te el familiar per aplicar el 40% adicional
                    String curset = idCurs.substring(0, 4);
                    if(curset.equals("CFAM")){
                        String carnet_familiar = p.carnetFamiliar(conexio, dni);
                        if(carnet_familiar==null){
                            descompte = descompte;
                        }else if(carnet_familiar.equals("CFAM2021")){
                            descompte= descompte+40;
                            }
                    }
                    
                    //Creem l'objecte curs on es guarda linformacio del curs llogat
                    curs = new CursFamiliar (idCurs, dni, String.valueOf(LocalDate.now()), hora, descompte, preu);
                    //Afegim el curs al hashmap per després poder recorrer-lo i obtenir les dades dels cursos
                    llistat.put(curs, llistat.containsKey(curs) ? llistat.get(curs)+1 :1);
                    //afegim el curs llogats al observablelist que mostrem a la taula cursos llogats i el mostrem a la taula
                    llogats.add(curs);
                    taulaCursosLlogats.setItems(llogats);
                    //netegem les seleccions realitzades
                    this.txtDni.clear();
                    this.txtIdCurs.clear();
                    this.una.setSelected(false);
                    this.dos.setSelected(false);
                    this.tres.setSelected(false);
                    this.dia.setSelected(false);
                }

            }else{
                //missatge d'error ja que el dni no coincideix
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("El DNI introduit no esta registrat a la base de dades");
                alert.showAndWait();
                //borrem la selecció que hem realitzat
                this.txtDni.clear();
                this.txtIdCurs.clear();
                this.una.setSelected(false);
                this.dos.setSelected(false);
                this.tres.setSelected(false);
                this.dia.setSelected(false);
            }

        }else{
            //missatge d'error ja que l'id del curs no coincideix
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("L'Id del curs introduit no es troba a la base de dades");
            alert.showAndWait();
            //borrem la selecció que hem realitzat
            this.txtDni.clear();
            this.txtIdCurs.clear();
            this.una.setSelected(false);
            this.dos.setSelected(false);
            this.tres.setSelected(false);
            this.dia.setSelected(false);
        }
    }
    
    public void tancar(){
  
        //declaracio variables necessaries
        int contador;
        int valor = 0;
        
        CursosEsqui definitiu = new CursFamiliar();
        
        //creem un iterador per poder recorrer el hashmap mitjançant la clau
        Iterator<CursosEsqui> cursos = llistat.keySet().iterator();
        
        //while per recorrer el hash map
        while(cursos.hasNext()){
            
            //agafem el curs en CURS i el numero de cops que esta llogat en contador
            curs = cursos.next();
            contador = llistat.get(curs);
            
            //bucle per agafar el curs més llogat del dia
            if ( contador > valor){
                valor = contador;
                definitiu = curs;
            }
            
            //agafem substring per comprovar quin curs es
            String principal = curs.getIdCurs().substring(0, 4);
            
            //setencies per guardar el curs a la base de dades relacional
            if (principal.equals("CFAM")){
                
                CursFamiliar noucurs = (CursFamiliar)curs;
                noucurs.llogarCurs(conexio, noucurs.getIdCurs(), noucurs.getDni(), noucurs.getDatacompra(), noucurs.getHores(), noucurs.getDescompte(), noucurs.getPreu_final());
            
            }else if(principal.equals("CFED")){
                
                CursFamiliar noucurs = (CursFamiliar)curs;
                noucurs.llogarCurs(conexio, noucurs.getIdCurs(), noucurs.getDni(), noucurs.getDatacompra(), noucurs.getHores(), 0, noucurs.getPreu_final());
            }
        }

        try{
            Statement conn = SQLOracle.conn();

            //cridem metode que ens retorna el curs
            curs = curs.cursmesllogat(definitiu.getIdCurs());
            
            System.out.println(curs.getIdCurs());
            System.out.println(curs.getNomCurs());
            
            String consulta = "INSERT INTO cursos_llogats VALUES ('"+String.valueOf(LocalDate.now())+"', Cursos('"+curs.getIdCurs()+"', '"+curs.getNomCurs()+"'))";

            conn.execute(consulta);
            
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
       
        //tanquem l'aplicació 
        Platform.exit();
        System.exit(0);
        
    }       
}