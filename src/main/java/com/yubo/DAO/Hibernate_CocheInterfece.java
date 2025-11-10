package com.yubo.DAO;

import com.yubo.Model.Coches;
import org.hibernate.Session;

public interface Hibernate_CocheInterfece {
    void modificarCita(Session session, Coches c);
}
