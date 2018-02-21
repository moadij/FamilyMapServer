package Service;
import Service.Request.Request;
import Service.Result.LoadResult;

/**
 * Represents a Load Service object. Implements Service interface.
 * Instance created by LoadHandler. Brains of the api call.
 * Verifies Request. Clears all data from the database (just like
 * the /clear API), and then loads the posted user,
 * person, and event data into the database.
 *
 * Connection between LoadHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class LoadService implements Service  {

    /**
     * Brains of the load service. Verifies Request.
     * Clears all data from the database (just like
     * the /clear API), and then loads the posted user,
     * person, and event data into the database.
     * @param request of type Request Interface
     * @return Returns an LoadResult with success or error message.
     */
    public LoadResult service(Request request ){

        return null;
    }
}
