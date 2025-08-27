package ua.trx.backend.Service;

public interface UserService {
    boolean register(String username, String password);
    public boolean userExists(String username);
    public boolean authenticateUser(String username, String password);
}
