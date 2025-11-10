package com.yubo.DAO;

import com.yubo.Model.Coches;
import org.hibernate.Session;

public class Hibernate_CocheDAO implements Hibernate_CocheInterfece{

    @Override
    public void modificarCita(Session session, Coches c)
    {
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
    }

}
