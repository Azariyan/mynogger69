package ua.trx.backend.Service;

public interface UserServiceDB {
    boolean register(String username, String password);
    boolean userExists(String username);
    boolean authenticateUser(String username, String password);
}
