package com.dongduong.controller.web;


import com.dongduong.command.UserCommand;
import com.dongduong.core.web.utils.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home.html")
public class HomeController extends HttpServlet {
    //meth doget
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        UserCommand command = FormUtil.populate(UserCommand.class,request);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request,response);
    }

    //meth dopost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

    }
}
