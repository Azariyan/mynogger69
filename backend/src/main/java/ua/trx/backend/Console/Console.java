package ua.trx.backend.Console;
// рОМА АУТИСТ И СІН ШЛЮХИ
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.trx.backend.Service.UserGlobalDB;

import java.util.Scanner;
// рОМА АУТИСТ И СІН ШЛЮХИ
@Component

public class Console {

    @Autowired
    private UserGlobalDB user;

    private Scanner scanner = new Scanner(System.in);

    private String currentUsername;

    public Console() {
    }

    public void all(){

        boolean isSigning = false;

        while (!isSigning){
            isSigning = choose();
        }
    }

        private boolean choose(){
            System.out.println("Sign up or sign in:");
            System.out.println("1");
            System.out.println("2");

            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    return signUP();
                case "2":
                    return signIN();
                default:
                    return false;
            }
        }

        private boolean signIN() {
            System.out.println("Sign in:");

            System.out.println("enter username:");
            String username = scanner.nextLine();
            System.out.println("enter password:");
            String password = scanner.nextLine();

            boolean authenticated = user.authenticateUser(username, password);

            if (authenticated) {
                System.out.println(username);
                return true;
            }else{
                System.out.println("\ntry again\n");
                return false;
            }
        }

        private boolean signUP() {
            System.out.println("Enter new username:");
            String username = scanner.nextLine();
            System.out.println("Enter new password:");

            String password = scanner.nextLine();

            boolean userCreated = user.register(username, password);

            if (userCreated) {
                System.out.println("\nsuccessful");
                currentUsername = username;
                return true;
            } else {
                System.out.println("\ntry again\n");
                return false;
            }
        }
}