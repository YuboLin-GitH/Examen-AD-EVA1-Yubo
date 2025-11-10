package com.yubo.DAO;

import com.yubo.Model.Reparaciones;
import org.hibernate.Session;

import java.util.List;

public interface Hibernate_ReparacionesInterface {
    void insertarReparaciones(Session session, Reparaciones r);

    List<Reparaciones> listaReparaciones(Session session);

    void borrarReparaciones(Session session, Reparaciones r);
}
