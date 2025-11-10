package com.yubo.DAO;

import com.yubo.Connection.MySQL_ConnectionDB;
import com.yubo.Model.Coches;
import com.yubo.Model.Reparaciones;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQL_CocheDAO implements MySQL_CocheInterface{

    @Override
    public Coches resultadoCoche(ResultSet resultado) throws SQLException {
        Coches coches = new Coches();
        coches.setIdcoche(resultado.getInt("idcoche"));
        coches.setMatricula(resultado.getString("matricula"));
        coches.setMarca(resultado.getString("marca"));
        coches.setKm(resultado.getInt("km"));
        //coches.setReparaciones(resultado.get("direccion"));
        return coches;
    }

    @Override
    public Coches buscarPorMatricula(String matricula) throws SQLException {
        String sql = "SELECT * FROM coches WHERE matricula = ?";
        try (Connection conn = MySQL_ConnectionDB.conectar();
             PreparedStatement sentencia = conn.prepareStatement(sql)) {
            sentencia.setString(1, matricula);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                return resultadoCoche(resultado);
            }
        }
        return null;
    }


    @Override
    public List<Reparaciones> obtenerReparacionPorCocheId(int cocheId) throws SQLException {
        List<Reparaciones> reparaciones = new ArrayList<>();
        String sql = "SELECT r.idreparacion, c.idcoche, r.fechareparacion, r.descripcion, r.precio, r.pagado " +
                "FROM coches c JOIN reparaciones r ON c.idcoche = r.idcoche WHERE r.idcoche = ?";

        try (Connection conn = MySQL_ConnectionDB.conectar();
             PreparedStatement sentencia = conn.prepareStatement(sql)) {

            sentencia.setInt(1, cocheId);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Reparaciones reparacione = new Reparaciones();
                reparacione.setIdreparacion(rs.getInt("idreparacion"));
                reparacione.setFechareparacion(rs.getDate("fechareparacion").toLocalDate());
                reparacione.setDescripcion(rs.getString("descripcion"));
                reparacione.setPrecio(rs.getInt("precio"));
                reparacione.setPagado(Boolean.valueOf("Pagado"));

                Coches coches = new Coches();
                coches.setIdcoche(rs.getInt("idcoche"));
                coches.setReparaciones(reparaciones);


                reparaciones.add(reparacione);
            }
        }
        return reparaciones;
    }



}
