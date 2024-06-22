package com.example.theatre.Service;

import com.example.theatre.Models.Viewer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ViewerService {

    public void saveViewer(Viewer viewer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(viewer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
