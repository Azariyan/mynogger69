package ua.trx.backend.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.trx.backend.Service.UserGlobal;
import ua.trx.backend.pidor.User;

import java.util.Scanner;

@Component

public class Console {

    @Autowired
    private UserGlobal user;

    private Scanner scanner = new Scanner(System.in);

    public Console() {
    }

    public void all(){

        boolean isSigning = false;

        while (!isSigning){
            isSigning = choose();
        }
    }

        private boolean choose(){
            System.out.println("Sign up or sign up:");
            System.out.println("1");
            System.out.println("2");

            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    return signIN();
                case "2":
                    return sighUP();
                default:
                    return false;
            }
        }

        private boolean signIN() {
            System.out.println("Sign in:");

            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            boolean authenticated = user.authenticateUser(username, password);

            if (authenticated) {
                System.out.println("Welcome " + username);
                return true;
            }else{
                System.out.println("\nTry again.\n");
                return false;
            }
        }

        private boolean sighUP() {
            System.out.println("Enter new username:");
            String username = scanner.nextLine();
            System.out.println("Enter new password:");

            String password = scanner.nextLine();
            System.out.println("Sign up:");
            return true;
        }
}