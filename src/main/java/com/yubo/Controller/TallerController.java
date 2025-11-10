package com.yubo.Controller;

import com.yubo.DAO.*;
import com.yubo.Model.Coches;
import com.yubo.Model.Marcas;
import com.yubo.Model.Reparaciones;
import com.yubo.util.AlertUtils;
import com.yubo.util.HibernateUtil;
import com.yubo.util.R;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TallerController {

    private final MySQL_CocheInterface mySQLCocheInterface = new MySQL_CocheDAO();
    private final MongoDB_ReparacionesInterface mongoDBReparacionesInterface = new MongoDB_ReparacionesDAO();
    private final Hibernate_ReparacionesInterface hibernateReparacionesInterface = new Hibernate_ReparacionesDAO();

    @FXML
    public TextField tfMatricula, tfMarca, tfKm, tfNumeroReparacion, tfPrecio;

    @FXML
    public TextArea taDescripcion;

    @FXML
    public Button btModificarDatos, btVerReparaciones, btLimbiar, btNuevaReparacion, btBorrarReparacion, btPagarReparacion;


    @FXML
    public DatePicker dpFechaReparacion;


    @FXML
    public TableView<Reparaciones> tvReparacionVehiculo;
    @FXML
    private TableColumn<Reparaciones, Integer> colIdReparacion;
    @FXML
    private TableColumn<Reparaciones, Date> colFecha;
    @FXML
    private TableColumn<Reparaciones, String> colDescripcion;
    @FXML
    private TableColumn<Reparaciones, Integer> colPrecio;
    @FXML
    private TableColumn<Reparaciones, String> colPagado;

    @FXML
    private RadioButton noPagado, siPagado;

    @FXML
    private ToggleGroup tgPagado;


    private Coches coches;


    public TallerController(){

    }


    @FXML
    public void initialize() {

        colIdReparacion.setCellValueFactory(new PropertyValueFactory<>("idreparacion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechareparacion"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));


        tfMatricula.setOnKeyPressed(this::manejarEnterParaVerCita);
    }



    private void manejarEnterParaVerCita(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            verCoche();
        }
    }



    /*
    * (1 Punto) Lo primero será buscar los datos del coche por su matrícula para
    * lo cual introduciremos la matrícula y al pulsar la tecla ENTER nos buscara
    * el coche en la BD MysqL utilizando JDBC, si la matricula existe nos cargará
    * sus datos en los TextBox correspondientes y los deshabilitaremos para que
    * no se puedan modificar (Excepto el número de matrícula),
    * en caso de NO existir me mostrará un mensaje de ERROR.
     * */

    @FXML
    public void verCoche() {
        try {
            String matriculaIngresado = tfMatricula.getText().trim();
            if (matriculaIngresado.isEmpty()) {
                AlertUtils.mostrarError("Introduce un Matricula válido");
                return;
            }


            Coches coche = mySQLCocheInterface.buscarPorMatricula(matriculaIngresado);


            if (coche == null) {
                AlertUtils.mostrarError("No se encontró coche con ese Matricula");
                return;
            }


            this.coches = coche;
            mostrarDatosCoche();

            tfKm.setDisable(true);
            tfMarca.setDisable(true);


        } catch (Exception e) {
            AlertUtils.mostrarError("Error al buscar Matricula: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void mostrarDatosCoche() {
        tfMatricula.setText(coches.getMatricula());
        tfMarca.setText(coches.getMarca());
        tfKm.setText(String.valueOf(coches.getKm()));
    }


    @FXML
    public void verReparaciones() {


        try {
            String matriculaIngresado = tfMatricula.getText().trim();
            if (matriculaIngresado.isEmpty()) {
                AlertUtils.mostrarError("Introduce una Matricula válido");
                return;
            }


            Coches coche = mySQLCocheInterface.buscarPorMatricula(matriculaIngresado);


            if (coche == null) {
                AlertUtils.mostrarError("No se encontró paciente con ese Matricula");
                return;
            }


            List<Reparaciones> reparaciones = mySQLCocheInterface.obtenerReparacionPorCocheId(coche.getIdcoche());


            if (reparaciones.isEmpty()) {
                AlertUtils.mostrarInformacion("El paciente no tiene reparaciones registradas.");
                tvReparacionVehiculo.getItems().clear();
                return;
            }





            tvReparacionVehiculo.setItems(FXCollections.observableArrayList(reparaciones));

            tfNumeroReparacion.setDisable(true);


        } catch (Exception e) {
            AlertUtils.mostrarError("Error al buscar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    void onClickTabla(MouseEvent event) {
        Reparaciones cocheSeleccionado = tvReparacionVehiculo.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL EQUIPO SELECCIONADO
        if (cocheSeleccionado != null) {
            tfNumeroReparacion.setText(String.valueOf(cocheSeleccionado.getIdreparacion()));

            dpFechaReparacion.setValue(cocheSeleccionado.getFechareparacion());
            taDescripcion.setText(cocheSeleccionado.getDescripcion());
            tfPrecio.setText(String.valueOf(cocheSeleccionado.getPrecio()));


             if (cocheSeleccionado.getPagado()) {
                noPagado.setSelected(false);
                siPagado.setSelected(true);
            } else {
             noPagado.setSelected(true);
         siPagado.setSelected(false);
            }




        }
    }



    @FXML
    public void limpiarCajas() {
        tfNumeroReparacion.clear();
        dpFechaReparacion.setValue(null);
        taDescripcion.clear();
        tfPrecio.clear();
    }




    @FXML
    private  void nuevaReparacion() {
        try {

            LocalDate fechaSeleccionada = dpFechaReparacion.getValue();
            int precioNuevo = Integer.parseInt(tfPrecio.getText());
            String descrpcionNuevo = taDescripcion.getText();


            if (fechaSeleccionada == null ) {
                AlertUtils.mostrarError("Elegir fecha de Reparaciones");
                return;
            }
            Reparaciones r = new Reparaciones();
            r.setFechareparacion(fechaSeleccionada);
            r.setPrecio(precioNuevo);
            r.setDescripcion(descrpcionNuevo);
            r.setCoches(coches);

            try(Session session = HibernateUtil.getSession()) {

                hibernateReparacionesInterface.insertarReparaciones(session, r);
                cargarDatos();
                limpiarCajas();

            }catch (Exception e){
                System.out.println("Error de Insertar Reparaciones en MYSQL");
            }


            boolean mongoOK = mongoDBReparacionesInterface.insertReparaciones(r);
            if (mongoOK) {
                AlertUtils.mostrarInformacion("Cita registrada correctamente en MySQL y MongoDB.");
            } else {
                AlertUtils.mostrarInformacion("Cita registrada en MySQL, pero falló al guardarla en MongoDB.");
            }


        }catch (Exception e) {
            AlertUtils.mostrarError("Error al crear cita: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void cargarDatos() {
        //modoEdicion(false);
        tvReparacionVehiculo.getItems().clear();

        try (Session session = HibernateUtil.getSession()){
            List<Reparaciones> reparaciones= hibernateReparacionesInterface.listaReparaciones(session);
            List<Reparaciones> esteCoche = new ArrayList<>();

            for (Reparaciones r : reparaciones) {
                if (r.getCoches() != null && r.getCoches().getIdcoche() == coches.getIdcoche()) {
                    esteCoche.add(r);
                }
            }

            tvReparacionVehiculo.setItems(FXCollections.observableList(esteCoche));
        }
    }


    @FXML
    private void borrarReparaciones(){
        Reparaciones reparacionesSeleccionada = tvReparacionVehiculo.getSelectionModel().getSelectedItem();
        if (reparacionesSeleccionada == null) {
            AlertUtils.mostrarError("el seleccionado no existe");
            return;
        }
        try (Session session = HibernateUtil.getSession()){


            //SOLO se podrá borrar una reparación sino está pagada.

            if (reparacionesSeleccionada.getPagado() == false){
                hibernateReparacionesInterface.borrarReparaciones(session, reparacionesSeleccionada);
                AlertUtils.mostrarInformacion("Reparacion eliminada");
            }



            cargarDatos();
            limpiarCajas();
            reparacionesSeleccionada = null;
        } catch (Exception e) {
            AlertUtils.mostrarError("Error：" + e.getMessage());
        }

    }




    @FXML
    private void modifcarVehiculo() {


        try {
            // CARGAR EL ARCHIVO FXML
            FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("coche.fxml"));
            Parent root = fxmlLoader.load();


            // OBTENER EL STAGE ACTUAL A PARTIR DEL BOTON QUE SE HA CLICADO
            Stage nuevoStage = new Stage();
            nuevoStage.setTitle("Modificar choche");
            nuevoStage.setScene(new Scene(root));
            nuevoStage.initModality(Modality.APPLICATION_MODAL);
            nuevoStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace(); // SI HAY ERROR EN LA CARGA DEL FXML, SE LANZA LA EXCEPCION
        }
    }


}
