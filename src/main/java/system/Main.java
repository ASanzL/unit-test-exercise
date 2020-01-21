package system;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import systemtest.AdminTest;
import systemtest.LogInTest;
import systemtest.UserTest;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static boolean testDone = false;

    public static void runTests() {
        Result result = JUnitCore.runClasses(
                AdminTest.class,
                LogInTest.class
        );

        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
        testDone = true;
    }

    public static void main(String[] args) {
        runTests();

        while (!testDone) {
            System.out.println("running");
        }
        LogIn login = new LogIn();
        login.addAccount(new Admin("admin1", "admin1234"));

        while (true) {
            if(login.getLoggedInAccount() != null) {
                System.out.print("Enter a command: ");
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        login.printAllCommands(login.getLoggedInAccount() instanceof Admin);
                        break;
                    case 2:
                        System.out.println("Your balance is: " + login.getLoggedInAccount().getBalance());
                        break;
                    case 3:
                        System.out.println("Your salary is: " + login.getLoggedInAccount().getSalary());
                        break;
                    case 4:
                        System.out.println("You role is : " + login.getLoggedInAccount().getRole());
                        break;
                    case 5:
                        if(login.getLoggedInAccount() instanceof Admin) {
                            System.out.println("Username\tPassword");
                            for (Account account : login.getAccounts()) {
                                System.out.println(account.getUsername() + "\t\t" + account.getPassword());
                            }
                        }
                        break;
                    case 8:
                        if(login.getLoggedInAccount() instanceof Admin) {

                        }
                        break;
                }
                System.out.println("");
            } else {
                System.out.println("Please log in.");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.println("");

                login.tryToLogIn(username, password);
            }
        }
    }
}
