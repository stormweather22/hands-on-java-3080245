package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import Exceptions.AmountException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Globe bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountid());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();

  }

  private Customer authenticateUser() {
    System.out.println("Please enter your username: ");
    String username = scanner.next();

    System.out.println("Please enter your password: ");
    String password = scanner.next();

    Customer customer = null;

    try {
      customer = Authenticator.customerLogin(username, password);

    } catch (LoginException ex) {
      ex.printStackTrace();
    }

    return customer;

  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;

    while (selection != 4 && customer.isAuthenticated()) {
      System.out.println("-------------------------------------------------------");
      System.out.println("Please select one of the following options: ");
      System.out.println("1. Deposit");
      System.out.println("2. Withdraw");
      System.out.println("3. Check balance");
      System.out.println("4. Exit");
      System.out.println("-------------------------------------------------------");

      selection = scanner.nextInt();
      double amount;

      switch (selection) {
        case 1:
          System.out.println("How much you want to deposit? ");
          amount = scanner.nextDouble();
          try {
            account.deposit(amount);
          } catch (AmountException e) {
            e.getMessage();
            System.out.println("Please try again.");
          }

          break;
        case 2:
          System.out.println("How much you want to withdraw? ");
          amount = scanner.nextDouble();
          try {
            account.withdraw(amount);
          } catch (AmountException e) {
            e.getMessage();
            System.out.println("Please try again.");
          }

          break;
        case 3:
          System.out.println("Current balance " + account.getBalance());
          break;
        case 4:
          Authenticator.logOut(customer);
          break;
        default:
          System.out.println("Invalid option, please try again. ");
          break;
      }

    }
  }

}
