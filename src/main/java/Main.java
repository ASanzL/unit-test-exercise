import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    public static Account loggedInAccount = null;

    public static void main(String[] args) {
        accounts.add(new Admin("admin1", "admin1234"));

        while (true) {
            if(loggedInAccount != null) {
                System.out.print("Enter a command: ");
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        printAllCommands(loggedInAccount instanceof Admin);
                        break;
                    case 2:
                        System.out.println("Your balance is: " + loggedInAccount.getBalance());
                        break;
                    case 3:
                        System.out.println("Your salary is: " + loggedInAccount.getSalary());
                        break;
                    case 4:
                        System.out.println("You role is : " + loggedInAccount.getRole());
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

                tryToLogIn(username, password);
            }
        }
    }

    /**
     * Check all accounts if their username and password match, and if so set the logged in user
     * to that account
     * @param username
     * @param password
     * @return If an account has been logged in our not
     */
    public static boolean tryToLogIn(String username, String password) {
        for(Account account : accounts) {
            if(account.getUsername().equals(username) &&
                    account.getPassword().equals(password)) {
                loggedInAccount = account;
                System.out.println("Welcome, " + loggedInAccount.getUsername());
                printAllCommands(loggedInAccount instanceof Admin);
                return true;
            }
        }
        System.out.println("Wrong username or password.");
        return false;
    }

    /**
     * Prints all commands an account can use
     * @param isAdmin Output change depending on if account is admin or user
     */
    public static void printAllCommands(boolean isAdmin) {
        System.out.println("1 - View all commands\n" +
                "2 - View bank balance\n" +
                "3 - View salary\n" +
                "4 - View role");
        System.out.println(isAdmin ?
                "MORE ADMIN COMMANDS"
                :
                "5 - Request change of role or salary\n" +
                "6 - Delete your account");
    }
}
