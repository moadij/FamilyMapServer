package Service;
import Service.Result.EventResult;
import Service.Request.Request;

/**
 * Represents a Event Service object. Implements Service interface.
 * Instance created by EventHandler.
 */

public class EventService implements Service {

    /**
     * Brains of the event service. Verifies Request.
     * Returns ALL events for ALL family members of
     * the current user. The current user is determined
     * from the provided auth token.
     * @param request of type Request Interface
     * @return Returns an EventResult with success or error message.
     */
    public EventResult service( Request request ){

        return null;
    }
}