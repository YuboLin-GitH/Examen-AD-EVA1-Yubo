package com.yubo.DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yubo.Connection.MongoDB_ConnectionDB;
import com.yubo.Model.Reparaciones;
import com.yubo.util.AlertUtils;
import org.bson.Document;

public class MongoDB_ReparacionesDAO implements MongoDB_ReparacionesInterface{
    MongoClient mongoClient;
    MongoDatabase mongoDatabase; // BASE DE DATOS DE MONGO
    MongoCollection<Document> collection;


    public MongoDB_ReparacionesDAO() {
        mongoClient = MongoDB_ConnectionDB.conectar();
        mongoDatabase = mongoClient.getDatabase("Taller");
        collection = mongoDatabase.getCollection("Reparaciones");
    }

    @Override
    public boolean insertReparaciones(Reparaciones reparaciones) {
        Document doc = new Document(); // DOCUMENTO BSON QUE SE INSERTARA EN LA BD
        try {
            doc.append("idreparacion", reparaciones.getIdreparacion())
                    .append("idcoche", reparaciones.getCoches().getIdcoche())
                    .append("fechareparacion", reparaciones.getFechareparacion())
                    .append("descripción", reparaciones.getIdreparacion())
                    .append("precio", reparaciones.getPrecio())
                    .append("pagado", reparaciones.getPagado());


            collection.insertOne(doc); // INSERTAR EL DOCUMENTO EN LA COLECCION
            return true;
        } catch (Exception e) {
            AlertUtils.mostrarError("Error al conectar a la base de datos: " + e.getMessage());
        }
        return false;
    }

}
