package com.yubo.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yubo.Model.Marcas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MarcasDAO {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static List<Marcas> obtenerEspecialidad() throws IOException {

        ArrayList<Marcas> especialidad =
                JSON_MAPPER.readValue(new File("src/main/resources/BaseDatos/marcas.json"),
                        JSON_MAPPER.getTypeFactory().constructCollectionType
                                (ArrayList.class, Marcas.class));

        return especialidad;
    }
}
