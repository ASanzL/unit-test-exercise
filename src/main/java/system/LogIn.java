package system;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LogIn {
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private Account loggedInAccount = null;

    /**
     * Check all accounts if their username and password match, and if so set the logged in user
     * to that account
     * @param username
     * @param password
     * @return If an account has been logged in our not
     */
    public boolean tryToLogIn(String username, String password, boolean printOutput) {
        for(Account account : accounts) {
            if(account.getUsername().equals(username) &&
                    account.getPassword().equals(password)) {
                loggedInAccount = account;
                if (printOutput) {
                    System.out.println("Welcome, " + loggedInAccount.getUsername());
                    printAllCommands(loggedInAccount instanceof Admin);
                }
                return true;
            }
        }
        if (printOutput) {
            System.out.println("Wrong username or password.");
        }
        return false;
    }

    public boolean tryToLogIn(String username, String password) {
        return tryToLogIn(username, password, true);
    }

    /**
     * Adds a new account
     * @param newUser
     * @return If a new account has been created
     */
    public boolean createNewAccount(User newUser) {
        if (isUsernameOrPasswordValid(newUser.getUsername()) && isUsernameOrPasswordValid(newUser.getPassword())) {
            addAccount(newUser);
            return true;
        }
        return false;
    }

    public boolean removeAccount(User userToRemove) {
        for (Account user : accounts) {
            if (user instanceof User) {
                if (user.getUsername().equals(userToRemove.getUsername())
                && user.getPassword().equals(userToRemove.getPassword())) {
                    accounts.remove(user);
                    System.out.println("Account has been removed");
                    return true;
                }
            }
        }
        System.out.println("No user matched the username and password combination");
        return false;
    }

    public boolean removeLoggedInAccount(User userToRemove) {
        if (loggedInAccount instanceof User) {
            if (userToRemove.getUsername().equals(loggedInAccount.getUsername())
            && userToRemove.getPassword().equals(loggedInAccount.getPassword())) {
                accounts.remove(loggedInAccount);
                loggedInAccount = null;
                System.out.println("Account has been removed");
                return true;
            }
        }
        System.out.println("Wrong username or password.");
        return false;
    }

    public String enterUsername(Scanner scanner) {
        System.out.print("Enter username: ");
        try {
            return scanner.next();
        } catch (InputMismatchException e) {
            return "error";
        }
    }
    public String enterPassword(Scanner scanner) {
        System.out.print("Enter password: ");
        try {
            return scanner.next();
        } catch (InputMismatchException e) {
            return "error";
        }
    }

    public static boolean isUsernameOrPasswordValid(String input) {
        boolean haveLetter = false;
        boolean haveNumber = false;

        for(char c : input.toCharArray()) {
            if(Character.isLetter(c)) {
                haveLetter = true;
            }
            if(Character.isDigit(c)) {
                haveNumber = true;
            }
        }
        return (haveLetter && haveNumber);
    }

    /**
     * Prints all commands an account can use
     * @param isAdmin Output change depending on if account is admin or user
     */
    public void printAllCommands(boolean isAdmin) {
        System.out.println("0 - Log out\n" +
                "1 - View all commands\n" +
                "2 - View bank balance\n" +
                "3 - View salary\n" +
                "4 - View role");
        System.out.println(isAdmin ?
                "5 - View all accounts\n" +
                "6 - \n" +
                "7 - \n" +
                "8 - Add new account\n" +
                "9 - Remove account"
                :
                "5 - Request change of role or salary\n" +
                "6 - Delete your account");
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }
}
