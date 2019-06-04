package com.dongduong.core.common.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UploadUtil{
    private final int maxMemorySize = 1024*1024*3;
    private final int maxRequestSize = 1024*1024*50;
    public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValues,String path) throws FileUploadException,Exception{
        ServletContext context = request.getServletContext();
        String address = context.getRealPath("image");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        boolean check = true;
        String fileLocal = "";
        String name ="";
        Map<String,String> mapReturnMapVlues =  new HashMap<String, String>();
        if(!isMultipart){
            System.out.println("have not encrypt multupart/form-data");
            check = false;
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(maxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(maxRequestSize);

        // Parse the request

            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item : items){
                if(!item.isFormField()){
                    name = item.getName();
                    if(StringUtils.isNotBlank(name)){
                       /* File uploadFile = new File("/home/hirokiakasi/Documents/Project/ToeicOnline/toeic-web/src/main/webapp/image/listenguideline/"+fileName) ;*/
                        File uploadFile = new File(address+File.separator+path+File.separator+name);
                        fileLocal = uploadFile.toString();
                        boolean isExist = uploadFile.exists();
                        if(isExist){
                            if(uploadFile.delete()){
                                item.write(uploadFile);
                            }else {
                                check=false;
                            }
                        }else{
                            item.write(uploadFile);
                        }
                    }
                }else {
                    String titleFiled =item.getFieldName();
                    String valuesFiled = item.getString();
                    if(titleValues.contains(titleFiled)){
                        mapReturnMapVlues.put(titleFiled,valuesFiled);
                    }
                }
            }
        return new Object[]{check,fileLocal,name,mapReturnMapVlues};
    }
}
