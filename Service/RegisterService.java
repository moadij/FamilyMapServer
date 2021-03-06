package Service;

import java.sql.SQLException;

import Model.AuthToken;
import Service.Result.*;
import Service.Request.*;
import Model.User;
import DAO.*;

/**
 * Represents a Register Service object. Implements Service interface.
 * Instance created by RegisterHandler. The Brains of the api call.
 * Creates a new user account, generates 4 generations
 * of ancestor data for the new user, logs the user in,
 * and returns an auth token.
 *
 * Connection between RegisterHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class RegisterService{

    DAO dao = new DAO();
    //gotta build user object out of the request...
    User user = null;
    AuthToken authToken = null;

    /**
     * Brains of the register service. Verifies Request.
     * Creates a new user account, generates 4 generations
     * of ancestor data for the new user, logs the user in,
     * and returns an auth token.
     * @param request of type Request Interface
     * @return Returns a RegisterResult with success or error message.
     */
    public RegisterResult service( RegisterRequest request )
    {
        //System.out.print("Entered service function!" );

        RegisterResult rResult = new RegisterResult();

        //probably need to edit this DAO stuff to check for stuff first butttt....
        try
        {
            dao.initialize();
            if(request.checkValues()) {
                if (!checkUsername(request.getUserName())) {
                    user = new User(request.getUserName(), request.getPassWord(),request.getEmail(),request.getFirstName(),request.getLastName(),request.getGender());

                    if (dao.getUserDAO().createUser(user)) {
                        //create the auth token??
                        authToken = new AuthToken();
                        authToken.setUserName(user.getUserName());
                        authToken.setAuthTokenID(authToken.generateID());

                        //create the auth token in the database!!
                        dao.getAuthTokenDAO().createAuthToken(authToken);

                        rResult.setUserName(user.getUserName());
                        rResult.setPersonID(user.getPersonID());

                        rResult.setAuthToken(authToken.getAuthTokenID());

                        //now call the fill...
                        FillRequest frequest = new FillRequest();
                        frequest.setUserName(request.getUserName());
                        frequest.setGenerations(4);
                        FillService fservice = new FillService();
                        fservice.service(frequest);
                    }
                } else {
                    rResult.setMessage("Username already taken by another user");
                }
            } else
            {
                rResult.setMessage(("Request property missing or has invalid value"));
            }
        }
        catch(SQLException e)
        {
            rResult.setMessage("Internal Server Error: " + e.getMessage());
            //System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            rResult.setMessage("Internal Server Error: " + e.getMessage());
            //System.out.print(e.getMessage());
        }
        return rResult;
    }

    /**
     * checks username for authenticity to see if it exists
     * @param username
     * @return
     */
    public Boolean checkUsername(String username)
    {
        try
        {
            if(dao.getUserDAO().readUser(username) == null)
            {
                return false;
            }
        }
        catch(SQLException e)
        {
            //System.out.print(e.getMessage());
        }

        return true;
    }

}
