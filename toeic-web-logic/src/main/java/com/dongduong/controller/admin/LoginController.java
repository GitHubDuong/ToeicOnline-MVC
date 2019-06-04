package com.dongduong.controller.admin;


import com.dongduong.command.UserCommand;
import com.dongduong.core.dto.UserDTO;
import com.dongduong.core.service.UserService;
import com.dongduong.core.service.impl.UserServiceImpl;
import com.dongduong.core.web.common.WebConstant;
import com.dongduong.core.web.utils.FormUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login.html")
public class LoginController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/login.jsp");
        requestDispatcher.forward(request, response);
    }

    //metho gopost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand userCommand = FormUtil.populate(UserCommand.class, request);
        UserDTO userDTO = userCommand.getPojo();
        UserService userService = new UserServiceImpl();
      try{
          if (userService.userIsExist(userDTO) != null){
            if(userService.findRoleId(userDTO).getRoleDTO().getName().equals(WebConstant.ROLE_ADMIN)){
                response.sendRedirect("/admin-home.html");
            }else if(userService.findRoleId(userDTO).getRoleDTO().getName().equals(WebConstant.ROLE_USER)){
               response.sendRedirect("/home.htm");
            }
          }
      }catch (NullPointerException e){
          log.error(e);
          request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
          request.setAttribute(WebConstant.MASSAGE_RESPONSE,"Lổi...");
          RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/login.jsp");
          requestDispatcher.forward(request, response);
      }

    }
}
