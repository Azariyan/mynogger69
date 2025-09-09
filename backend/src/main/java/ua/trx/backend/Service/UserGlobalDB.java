package ua.trx.backend.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.trx.backend.pidor.User;

@Service
@Transactional
public class UserGlobalDB implements UserServiceDB {

    @PersistenceContext
    private EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    public UserGlobalDB(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(String username, String password) {
        if (userExists(username)) {
            return false;
        }

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);

        entityManager.persist(user);
        return true;
    }

    public boolean userExists(String username) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    public boolean authenticateUser(String username, String rawPassword) {
        User user = entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (user == null) return false;

        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
