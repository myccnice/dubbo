package com.myccnice.dubbo.protocol.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDispatcher extends HttpServlet {

    private static final long serialVersionUID = 5692744506520980532L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new ServletHandler().handle(req, resp);
    }
}
