package com.project.web.servlet;

import com.project.service.DetailsService;
import com.project.service.impl.DetailsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "DetailsServlet",urlPatterns ={"*.detail"})
public class DetailsServlet extends HttpServlet {
    private DetailsService detailsService= new DetailsServiceImpl();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
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
        request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
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
    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageId = Integer.parseInt( request.getParameter("imageId"));
        int uid = 0;
        if(request.getSession().getAttribute("uid")!=null){
            uid = (int) request.getSession().getAttribute("uid");
            detailsService.addFootprint(imageId,uid);
        }
        request.setAttribute("imageID",imageId);
        request.setAttribute("imageInfo",detailsService.showImage(imageId));
        request.setAttribute("country",detailsService.countryName(detailsService.showImage(imageId).getCountry_RegionCodeISO()));
        request.setAttribute("city",detailsService.cityName(detailsService.showImage(imageId).getCityCode()));
        request.setAttribute("userName",detailsService.userName(detailsService.showImage(imageId).getUid()));
        request.setAttribute("likePeople",detailsService.likePeople(imageId));
        request.setAttribute("likeOrNot",detailsService.likeOrNot(imageId,uid));
        request.getRequestDispatcher("/Details.jsp?imageId="+imageId).forward(request,response);
    }
    private void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageId = Integer.parseInt(request.getParameter("imageId"));
        int uid = (int) request.getSession().getAttribute("uid");
        detailsService.like(imageId,uid);
        request.getRequestDispatcher("/show.detail?imageId="+imageId).forward(request,response);
    }
    private void dislike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageId = Integer.parseInt( request.getParameter("imageId"));
        int uid = (int) request.getSession().getAttribute("uid");
        detailsService.dislike(imageId,uid);
        request.getRequestDispatcher("/show.detail?imageId="+imageId).forward(request,response);
    }
}
