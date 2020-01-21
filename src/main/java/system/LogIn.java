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
    public boolean tryToLogIn(String username, String password) {
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

    public boolean createNewAccount(Scanner scanner) {
        String username;
        String password;

        try {
            username = scanner.nextLine();
        } catch (InputMismatchException e) {
            return false;
        }        try {
            password = scanner.nextLine();
        } catch (InputMismatchException e) {
            return false;
        }
        if(!isUsernameOrPasswordValid(username) || !isUsernameOrPasswordValid(password)) {
            return false;
        }

        addAccount(new User(username, password));
        return true;
    }

    private boolean isUsernameOrPasswordValid(String input) {
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
        System.out.println("1 - View all commands\n" +
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
