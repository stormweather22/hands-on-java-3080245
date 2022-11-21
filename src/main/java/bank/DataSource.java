package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {
    String dbfile = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(dbfile);
      // System.out.println("We're connected!");

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "Select * from customers where username = ?";
    Customer customer = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, username);
      try (ResultSet resultset = statement.executeQuery()) {
        customer = new Customer(
            resultset.getInt("id"),
            resultset.getString("name"),
            resultset.getString("username"),
            resultset.getString("password"),
            resultset.getInt("account_id"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return customer;
  }

  public static Account getAccount(int accountid) {
    String sql = "Select * from accounts where id = ?";
    Account account = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setInt(1, accountid);

      try (ResultSet resultset = statement.executeQuery()) {
        account = new Account(resultset.getInt("id"),
            resultset.getString("type"),
            resultset.getDouble("balance"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return account;
  }

  public static void main(String[] args) {

    Account account = getAccount(10385);
    System.out.println(account.getBalance());

  }

}
