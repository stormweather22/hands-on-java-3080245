package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String dbfile = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      DriverManager.getConnection(dbfile);
      System.out.println("We're connected!");

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return connection;
  }

  public static void main(String[] args) {
    connect();
  }

}
