package com.myccnice.dubbo.protocol.http;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.myccnice.dubbo.framework.Invocation;

public class HttpClient {

    public static String post(String host, int port, Invocation invocation) {
        try {
            URL url = new URL("http", host, port, "/");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStream out = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream in = connection.getInputStream();
            return IOUtils.toString(in, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
