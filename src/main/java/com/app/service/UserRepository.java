package com.app.service;

import com.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@AllArgsConstructor
@Slf4j
public class UserRepository {

    SessionFactory sessionFactory;

    public void saveOrUpdateUser(User user) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            if(user.getId() == null) {
                session.persist(user);
            } else {
                session.merge(user);
            }

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            log.error("Error saving user", e);
        }
    }

    public User getUserById(int id) {
        User user = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            user = session.get(User.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error getting user", e);
        }
        return user;
    }

    public void deleteUserById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.remove(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error deleting user", e);
        }
    }

}
