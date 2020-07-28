package com.project.web.servlet;

import com.project.dao.impl.ImageDaoImpl;
import com.project.pojo.TravelImage;
import com.project.service.MyPhotoService;
import com.project.service.impl.MyPhotoServiceImpl;
import com.project.util.Consts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "MyPhotoServlet",urlPatterns ={"*.myPhoto"})
public class MyPhotoServlet extends HttpServlet {
    private MyPhotoService myPhotoService = new MyPhotoServiceImpl();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 8);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 8);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nothingFound",null);
        request.setAttribute("presentPage",1);
        long pageAll = 0;
        int uid = (int) request.getSession().getAttribute("uid");
        if(myPhotoService.showAll(uid)!=null){
            pageAll = (long)Math.ceil((double)myPhotoService.showAll(uid).size()/(double)Consts.IMAGE_EVERY_PAGE);
            request.setAttribute("nothingFound",null);
        }else{
            request.setAttribute("nothingFound",true);
        }
        request.getRequestDispatcher("pageShow.myPhoto?page="+request.getAttribute("presentPage")+"&pageAll="+pageAll).forward(request,response);
    }

    private void pageShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TravelImage> travelImages;
        int uid = (int) request.getSession().getAttribute("uid");
        int page = Integer.parseInt(request.getParameter("page"));
        travelImages = myPhotoService.showAllByPage(uid,page);
        if(travelImages==null||travelImages.size()==0){
            request.setAttribute("nothingFound",true);
        }
        request.setAttribute("thisPageResult",travelImages);
        request.getRequestDispatcher("/MyPhoto.jsp").forward(request,response);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageId = Integer.parseInt(request.getParameter("imageId"));
        File fileTemp = new File(getServletContext().getRealPath("/img/images")+"\\"+new ImageDaoImpl().getImageById(imageId).getPath());
        if(fileTemp.exists()){
            fileTemp.delete();
        }
        myPhotoService.delete(imageId);

        request.getRequestDispatcher("init.myPhoto").forward(request,response);
    }
}

