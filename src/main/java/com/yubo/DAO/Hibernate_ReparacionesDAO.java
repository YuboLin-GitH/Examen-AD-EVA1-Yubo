package com.yubo.DAO;

import com.yubo.Model.Reparaciones;
import org.hibernate.Session;

import java.util.List;

public class Hibernate_ReparacionesDAO implements Hibernate_ReparacionesInterface{
    @Override
    public void insertarReparaciones(Session session, Reparaciones r)
    {
        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();
    }


    @Override
    public List<Reparaciones> listaReparaciones(Session session)
    {
        List<Reparaciones> lista = session.createQuery("from Reparaciones", Reparaciones.class).list();
        return lista;

        //list.forEach(System.out::println);//version 1.8 de java
    }

    @Override
    public void borrarReparaciones(Session session, Reparaciones r)
    {

        session.beginTransaction();
        session.delete(r);
        session.getTransaction().commit();
    }



}
