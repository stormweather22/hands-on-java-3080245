package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {

  public static customerLogin (String username, String password) throws LoginException{

    Customer customer = DataSource.getCustomer(username);

    if (customer == null){
      throw new LoginException("Username not found.");
    }

    if (password.equals(customer.getPassword())){
      customer.setAuthenticated(true);

      return customer;
    }else{
      throw new LoginException("Incorrect username or password.");
    }
  }

  public static void logOut(Customer customer) {
    customer.setAuthenticated(false);
  }

}
