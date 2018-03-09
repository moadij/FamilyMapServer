package Server;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

import org.xml.sax.helpers.DefaultHandler;

import Handler.*;


/**
 * Created by Parker on 2/10/18.
 */


public class Server {

    // The maximum number of waiting incoming connections to queue.
    // While this value is necessary, for our purposes it is unimportant.
    // Take CS 460 for a deeper understanding of what it means.
    private static final int MAX_WAITING_CONNECTIONS = 12;

    // Java provides an HttpServer class that can be used to embed
    // an HTTP server in any Java program.
    // Using the HttpServer class, you can easily make a Java
    // program that can receive incoming HTTP requests, and respond
    // with appropriate HTTP responses.
    // HttpServer is the class that actually implements the HTTP network
    // protocol (be glad you don't have to).
    // The "server" field contains the HttpServer instance for this program,
    // which is initialized in the "run" method below.
    private HttpServer server;


    private void run(String portNumber)
    {
        //System.out.print("Initializing HTTP Server\n");

        try
        {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            return;
        }

        // Indicate that we are using the default "executor".
        // This line is necessary, but its function is unimportant for our purposes.
        server.setExecutor(null);

        //System.out.println("Creating contexts");
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load/", new LoadHandler());
        server.createContext("/person/", new PersonIDHandler());
        server.createContext("/person/", new PersonHandler());
        server.createContext("/event/", new EventIDHandler());
        server.createContext("/event", new EventHandler());
        server.createContext("/", new Handler.DefaultHandler());


        // Log message indicating that the HttpServer is about the start accepting
        // incoming client connections.
        //System.out.println("Starting server");

        server.start();

        // Log message indicating that the server has successfully started.
        //System.out.println("Server started");
    }





    // "main" method for the server program
    // "args" should contain one command-line argument, which is the port number
    // on which the server should accept incoming client connections.
    public static void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }
}
