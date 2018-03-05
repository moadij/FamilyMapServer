package JSON;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import Service.Request.RegisterRequest;

/**
 * Created by Parker on 3/4/18.
 */


//should every handler class share the same encoder?? We only want one instance of gson...
    //so how can we do that??
public class Encoder {

    Gson gson = new Gson();

    public Encoder(){}

    public String encode(Object objectToEncode)
    {
        return gson.toJson(objectToEncode);
    }

    public Object decode(String json, Class toJsonClass)
    {
        return gson.fromJson(json, toJsonClass);
    }

    public void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    public String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}