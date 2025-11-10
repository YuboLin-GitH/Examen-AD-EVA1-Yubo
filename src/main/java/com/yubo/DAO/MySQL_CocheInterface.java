package com.yubo.DAO;

import com.yubo.Model.Coches;
import com.yubo.Model.Reparaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MySQL_CocheInterface {
    Coches resultadoCoche(ResultSet resultado) throws SQLException;

    Coches buscarPorMatricula(String matricula) throws SQLException;

    List<Reparaciones> obtenerReparacionPorCocheId(int cocheId) throws SQLException;
}
