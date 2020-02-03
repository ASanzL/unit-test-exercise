package system;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import systemtest.AdminTest;
import systemtest.LogInTest;
import systemtest.UserTest;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void runTests() {
        Result result = JUnitCore.runClasses(
                AdminTest.class,
                LogInTest.class,
                UserTest.class
        );

        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful() ? "Test was successful" : "Test failed");
        System.out.println();
    }

    public static void main(String[] args) {
        runTests();

        LogIn login = new LogIn();
        login.addAccount(new Admin("admin1", "admin1234"));

        while (true) {
            if(login.getLoggedInAccount() != null) {
                System.out.print("Enter a command: ");
                int command = -1;
                try {
                    command = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Write a number");
                    scanner.next();
                }
                switch (command) {
                    case 0:
                        login.setLoggedInAccount(null);
                    break;
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
                        } else {
                            System.out.print("Enter requested salary: ");
                            try {
                                int newSalary = scanner.nextInt();
                                ((User) login.getLoggedInAccount()).setRequestedSalary(newSalary);
                            } catch (InputMismatchException e) {
                                System.out.println("Enter a number");
                                scanner.next();
                            }
                        }
                    break;
                    case 6:
                        if(login.getLoggedInAccount() instanceof Admin) {
                            login.reviewSalaryRequests(scanner);
                        } else {
                            login.removeLoggedInAccount(inputUsernameAndPassword(login));
                        }
                    break;
                    case 7:
                        if(login.getLoggedInAccount() instanceof Admin) {
                            login.advanceTime();
                        }
                    break;
                    case 8:
                        if(login.getLoggedInAccount() instanceof Admin) {
                            login.createNewAccount(inputUsernameAndPassword(login));
                        }
                    break;
                    case 9:
                        if(login.getLoggedInAccount() instanceof  Admin) {
                            login.removeAccount(inputUsernameAndPassword(login));
                        }
                    break;
                }
                System.out.println();
            } else {
                System.out.println("Please log in.");
                System.out.print("Enter username: ");
                String username = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();
                System.out.println();

                login.tryToLogIn(username, password);
            }
        }
    }

    public static User inputUsernameAndPassword(LogIn login) {
        String username;
        String password;
        do {
            username = login.enterUsername(scanner);
        } while (!LogIn.isUsernameOrPasswordValid(username));
        do {
            password = login.enterPassword(scanner);
        } while (!LogIn.isUsernameOrPasswordValid(password));
        return new User(username, password);
    }
}
