package com.example.musify.hibernate;

import org.hibernate.Session;

public class TestHibernate {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Check database version
        String sql = "select version()";

        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println("Version: "+result);

        session.getTransaction().commit();
        session.close();


        HibernateUtil.shutdown();
    }
}
