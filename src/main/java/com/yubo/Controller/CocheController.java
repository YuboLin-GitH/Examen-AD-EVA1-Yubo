package com.yubo.Controller;

import com.yubo.DAO.MarcasDAO;
import com.yubo.Model.Marcas;
import com.yubo.util.AlertUtils;
import com.yubo.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class CocheController {

    @FXML
    public ComboBox<Marcas> cbMarca;
    @FXML
    public TextField tfMatricula, tfKm;
    @FXML
    public Button btModificar;



    @FXML
    public void initialize(){
        tfMatricula.setDisable(true);
        cargarEspecialidades();
    }


    private void cargarEspecialidades() {
        MarcasDAO especialidadDAO = new MarcasDAO();
        try {

            List<Marcas> especialidades = especialidadDAO.obtenerEspecialidad();
            if (especialidades.isEmpty()) {
                AlertUtils.mostrarError("Error al obtener las especialidades");
                return;
            }



            for (Marcas esp : especialidades) {
                cbMarca.setValue(esp);
                break;
            }

            cbMarca.getItems().addAll(especialidades);
        } catch (Exception e) {

            AlertUtils.mostrarError("Error：" + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    public void modificarCoche(){


    }

}
