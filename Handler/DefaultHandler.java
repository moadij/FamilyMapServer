package Handler;

import com.sun.net.httpserver.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;


/**
 * Default handler that returns the webpage for the client
 * to interact with the server/web api calls
 */
public class DefaultHandler implements HttpHandler{

    public DefaultHandler(){}

    /**
     * Reads in the http exchange from the server. Returns the web pages
     * for the client.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        //System.out.print("Default Handler!\n");

        String path = exchange.getRequestURI().getPath();
        if (path.equals("/") || path.length()==0)
        {
            path = "/index.html";
        }
        File file = new File("web" + path);

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream output = exchange.getResponseBody();
        FileInputStream fs = new FileInputStream(file);


        final byte[] buffer = new byte[0x10000];
        int count = 0;
        while((count = fs.read(buffer)) >=0)
        {
            output.write(buffer, 0, count);
        }

        output.flush();
        output.close();
        fs.close();
    }



}
