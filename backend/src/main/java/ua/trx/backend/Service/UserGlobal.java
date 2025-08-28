package ua.trx.backend.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import jakarta.persistence.*;
import ua.trx.backend.pidor.User;

@Service
@Transactional

public class UserGlobal implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean register(String username, String password){

        if(userExists(username)){
            System.out.println("Username already exists.");
            return false;
        }

        User user = new User(username, password);

        entityManager.persist(user);
        return true;
    }

    public boolean userExists(String username){
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    public boolean authenticateUser(String username, String password){
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username AND u.password = :password", Long.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();

        return count > 0;
    }
}
