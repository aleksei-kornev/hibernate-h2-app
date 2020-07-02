package com.flamexander.hibernate.h2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.jws.soap.SOAPBinding;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, 1L);
            user.print();

            for (int i = 0; i < 10; i++) {
                User userNew = new User();
                userNew.setName("Bob" + i);
                session.save(userNew);
                userNew.print();
            }

            Item itemBox = session.get(Item.class, 1L);
            itemBox.print();

            for (int i = 0; i < 10; i++) {
                Item item = new Item();
                item.setTitle("Cube" + i);
                item.setPrice(i * 10);
                session.save(item);
                item.print();
            }

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            User userToPrint = session.get(User.class, 1L);
            userToPrint.print();
            System.out.println("Этот пользователь покупал товары: ");
            for (Item itm : userToPrint.getItems()) {
                System.out.println(itm.getTitle());
            }

            Item itemToPrint = session.get(Item.class, 3L);
            itemToPrint.print();
            System.out.println("Этот товар покупали пользователи: ");
            for (User usr : itemToPrint.getUsers()) {
                System.out.println(usr.getName());
            }
            session.getTransaction().commit();

            System.out.println("Удаление пользователя #1");
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(User.class, 1L));
            session.getTransaction().commit();

            System.out.println("Удаление товара #1");
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(Item.class, 1L));
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
