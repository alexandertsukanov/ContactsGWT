package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.entity.Contacts;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;



public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {

    private SessionFactory sessionFactory;
    private Session session;
    private List usersList = new ArrayList<>();
    public MySampleApplicationServiceImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public List<Contacts> getAllUsers() {
        Query query =  session.createQuery("from Contacts");
        usersList = query.list();
        return usersList;
    }

    @Override
    public Contacts deleteUser(Contacts user) {
        Transaction transaction = session.beginTransaction();
        Contacts contact = (Contacts) session.get(Contacts.class, user.getId());
        if(contact == null){
            return null;
        }
        else {
            session.delete(contact);
            transaction.commit();
            return null;
        }
    }

    @Override
    public Contacts updateUser(List<Contacts> user) {
        Transaction transaction = session.beginTransaction();
        for(Contacts i : user){
            Contacts contact = (Contacts) session.get(Contacts.class, i.getId());
            if(session.contains(contact)) {
                session.merge(i);
            }
            else{
                session.save(i);
            }
        }
        transaction.commit();
        return null;
    }
}
