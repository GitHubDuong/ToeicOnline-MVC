package com.dongduong.controller.admin;

import com.dongduong.command.ListenguidelineCommand;
import com.dongduong.command.UserCommand;
import com.dongduong.core.common.util.UploadUtil;
import com.dongduong.core.dto.ListenguidelineDTO;
import com.dongduong.core.service.ListenguidelineService;
import com.dongduong.core.service.impl.ListenguidelineServiceImpl;
import com.dongduong.core.utils.ListenguidelineUtil;
import com.dongduong.core.web.common.WebConstant;
import com.dongduong.core.web.utils.FormUtil;
import com.dongduong.core.web.utils.RequestUtil;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import sun.util.resources.CalendarData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/*@WebServlet("/admin-guideline-listen-list.html")*/
@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html","/admin-guideline-listen-edit.html"})
public class ListenGuidelineContreoller extends HttpServlet {
    //meth doget
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Logger log = Logger.getLogger(this.getClass());
        ListenguidelineCommand listenguidelineCommand = FormUtil.populate(ListenguidelineCommand.class,request);
      /*  listenguidelineCommand.setMaxPageItems(2);*/
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourcesBundle");
        HttpSession session = request.getSession();
        //
        /*ListenguidelineService guidelineService = new ListenguidelineServiceImpl();
        //
        RequestUtil.initSearchBean(request,listenguidelineCommand);
        //
        Object[] objects = guidelineService.findListenguidelineByPropertites(null,listenguidelineCommand.getSortExpression(),listenguidelineCommand.getSortExpression(),listenguidelineCommand.getFirstItem(),listenguidelineCommand.getMaxPageItems());
         listenguidelineCommand.setListResult((List<ListenguidelineDTO>) objects[1]);
         listenguidelineCommand.setTotalItems(Integer.parseInt(objects[0].toString()));*/
        /* //
        request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
        request.setAttribute(WebConstant.MASSAGE_RESPONSE,resourceBundle.getString("label.guideline.listen.add.success"));*/
        if(session != null){
            request.setAttribute(WebConstant.ALERT,session.getAttribute(WebConstant.ALERT));
            request.setAttribute(WebConstant.MASSAGE_RESPONSE,session.getAttribute(WebConstant.MASSAGE_RESPONSE));
        }
        request.setAttribute(WebConstant.LIST_ITEM,listenguidelineCommand);
        if(listenguidelineCommand.getUrlType()!= null && listenguidelineCommand.getUrlType().equals(WebConstant.URL_LIST)){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            requestDispatcher.forward(request,response);
        }else if(listenguidelineCommand.getUrlType()!=null && listenguidelineCommand.getUrlType().equals(WebConstant.URL_EDIT)){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            requestDispatcher.forward(request,response);
        }
        session.removeAttribute(WebConstant.ALERT);
        session.removeAttribute(WebConstant.MASSAGE_RESPONSE);

    }

    //meth dopost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        ListenguidelineCommand command = new ListenguidelineCommand();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourcesBundle");
        HttpSession session = request.getSession();
        UploadUtil up = new UploadUtil();
        Set<String> titleValues = buildSetVlueListenguideline();
        try {
            Object[] objects = up.writeOrUpdateFile(request,titleValues,"listenguideline");
            Map<String,String> mapVlue = (Map<String, String>) objects[3];
            String localFilrImg = (String) objects[1];
            command = getListenguidelineCommand(titleValues,mapVlue,command);
            /*request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
            request.setAttribute(WebConstant.MASSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));*/
                 session.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
                 session.setAttribute(WebConstant.MASSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));
        } catch (FileUploadException e) {
            request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            request.setAttribute(WebConstant.MASSAGE_RESPONSE, resourceBundle.getString("label.error"));
        }catch (Exception e){
            request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            request.setAttribute(WebConstant.MASSAGE_RESPONSE, resourceBundle.getString("label.error"));

        }
                response.sendRedirect("/toeic_web_war_exploded/admin-guideline-listen-edit.html?urlType=url_list");
    }


    private ListenguidelineCommand getListenguidelineCommand(Set<String> titleValues, Map<String, String> mapVlue, ListenguidelineCommand command) {
        for(String items : titleValues){
            if(mapVlue.containsKey(items)){
                if(items.equals("pojo.title")){
                    command.getPojo().setTitle(mapVlue.get(items));
                }else  if(items.equals("pojo.content")){
                    command.getPojo().setContent(mapVlue.get(items));
                }
            }
        }
            return command;

    }

    private Set<String> buildSetVlueListenguideline() {
        Set<String> titleVlues = new HashSet<String>();
        titleVlues.add("pojo.title");
        titleVlues.add("pojo.content");
        return titleVlues;
    }

}
