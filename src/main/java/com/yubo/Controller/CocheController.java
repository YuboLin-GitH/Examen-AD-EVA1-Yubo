package com.yubo.Controller;

import com.yubo.DAO.Hibernate_CocheDAO;
import com.yubo.DAO.Hibernate_CocheInterfece;
import com.yubo.DAO.MarcasDAO;
import com.yubo.Model.Coches;
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
    Hibernate_CocheInterfece hibernateCocheInterfece = new Hibernate_CocheDAO();

    @FXML
    public ComboBox<Marcas> cbMarca;
    @FXML
    public TextField tfMatricula, tfKm;
    @FXML
    public Button btModificar;

    private Coches coches;

    @FXML
    public void initialize(){
        tfMatricula.setDisable(true);
        cargarEspecialidades();
    }

    public void setCoche(Coches car){
        this.coches = car;
        tfMatricula.setText(car.getMatricula());
        tfKm.setText(String.valueOf(car.getKm()));

        for (Marcas marca : cbMarca.getItems()) {
            if (marca.getNombreEspecilidad().equals(car.getMarca())) {
                cbMarca.setValue(marca);
                break;
            }
        }

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
        Marcas marcas = cbMarca.getValue();

        try (Session session = HibernateUtil.getSession()){


            coches.setKm(Integer.parseInt(tfKm.getText()));
            coches.setMarca(String.valueOf(marcas));


            hibernateCocheInterfece.modificarCita(session, coches);

            AlertUtils.mostrarInformacion("Coche Modificado");

        } catch (Exception e) {
            AlertUtils.mostrarError("Error：" + e.getMessage());
        }

    }

}
