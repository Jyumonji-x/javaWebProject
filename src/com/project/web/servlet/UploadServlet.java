package com.project.web.servlet;

import com.project.pojo.TravelImage;
import com.project.service.UploadModifyService;
import com.project.service.UploadService;
import com.project.service.impl.UploadModifyServiceImpl;
import com.project.service.impl.UploadServiceImpl;
import net.sf.json.JSONArray;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "UploadServlet",urlPatterns ={"*.upload"})
public class UploadServlet extends HttpServlet {
    private UploadService uploadService = new UploadServiceImpl();
    private UploadModifyService uploadModifyService =new UploadModifyServiceImpl();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("gb2312");
        response.setCharacterEncoding("gb2312");
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 7);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("gb2312");
        response.setCharacterEncoding("gb2312");
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 7);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void citySelect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String iso = request.getParameter("iso");
        JSONArray jsonArray = JSONArray.fromObject(uploadService.showCities(iso));
        response.setContentType("application/json");
        response.getWriter().append(jsonArray.toString());
    }
    private void upload(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*500);
        File tempDirectory = new File(".\\img\\temImg");
        TravelImage travelImage = new TravelImage();
        travelImage.setUid((Integer) request.getSession().getAttribute("uid"));
        factory.setRepository(tempDirectory);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024*1024*30);
        List<FileItem> items = upload.parseRequest(request);
        for(FileItem item:items){
            if(item.isFormField()){
                String name = item.getFieldName();
                String value = item.getString();
                value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) ;
                if("title".equals(name)){
                    travelImage.setTitle(value);
                }
                else if("description".equals(name)){
                    travelImage.setDescription(value);
                }
                else if("content".equals(name)){
                    travelImage.setContent(value);
                }
                else if("country".equals(name)){
                    travelImage.setCountry_RegionCodeISO(value);
                }
                else if("city".equals(name)){
                    travelImage.setCityCode(Integer.parseInt(value));
                }
            }else{
                String fieldName = item.getFieldName();
                String fileName= item.getName();
                String fileDot= fileName.substring(fileName.lastIndexOf("."));
                String contentType = item.getContentType();
                long sizeInBytes = item.getSize();

                InputStream in = item.getInputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                fileName = System.currentTimeMillis()+fileDot;
                travelImage.setPath(fileName);
                fileName=getServletContext().getRealPath("/img/images")+"/"+fileName;
                OutputStream out = new FileOutputStream(fileName);
                while((len=in.read(buffer))!=-1){
                    out.write(buffer,0,len);
                }
                out.close();
                in.close();
            }
        }
        uploadService.upload(travelImage);
        request.getRequestDispatcher("init.myPhoto").forward(request,response);
    }
    private void modify(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*500);
        File tempDirectory = new File(".\\img\\temImg");
        TravelImage travelImage = new TravelImage();
        travelImage.setUid((Integer) request.getSession().getAttribute("uid"));
        if(uploadService.getImageById(Integer.parseInt(request.getParameter("imageId"))).getUid()==(Integer) request.getSession().getAttribute("uid")){
            travelImage.setImageID(Integer.parseInt(request.getParameter("imageId")));
            factory.setRepository(tempDirectory);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024*1024*30);
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item:items){
                if(item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) ;
                    if ("title".equals(name)) {
                        travelImage.setTitle(value);
                    } else if ("description".equals(name)) {
                        travelImage.setDescription(value);
                    } else if ("content".equals(name)) {
                        travelImage.setContent(value);
                    } else if ("country".equals(name)) {
                        travelImage.setCountry_RegionCodeISO(value);
                    } else if ("city".equals(name)) {
                        travelImage.setCityCode(Integer.parseInt(value));
                    }
                }
            }
            uploadModifyService.modify(travelImage);
            request.getRequestDispatcher("init.myPhoto").forward(request,response);
        }
    }
}
