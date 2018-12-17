package com.myccnice.dubbo.protocol.http;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.myccnice.dubbo.framework.Invocation;

public class HttpClient {

    public static String post(String host, int port, Invocation invocation) {
        try {
            URL url = new URL("http", host, port, "/");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream input = connection.getInputStream();
            return IOUtils.toString(input, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
