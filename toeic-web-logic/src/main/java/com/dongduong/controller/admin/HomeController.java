package com.dongduong.controller.admin;


import com.dongduong.command.UserCommand;
import com.dongduong.core.web.utils.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-home.html")
public class HomeController extends HttpServlet {
    //metho doget
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/home.jsp");
        requestDispatcher.forward(request, response);
    }

    //metho gopost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

    }

}