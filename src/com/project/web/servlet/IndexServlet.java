package com.project.web.servlet;
import com.project.pojo.TravelImage;
import com.project.service.IndexService;
import com.project.service.impl.IndexServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "IndexServlet",urlPatterns ={"*.index"})
public class IndexServlet extends HttpServlet {
    private IndexService indexService = new IndexServiceImpl();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 6);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<TravelImage> popTravelImages = indexService.popularPhotos();
        List<TravelImage> newTravelImages = indexService.newPhotos();
        request.setAttribute("popImages",popTravelImages);
        request.setAttribute("newImages",newTravelImages);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
