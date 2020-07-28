package com.project.web.servlet;

import com.project.pojo.TravelImage;
import com.project.service.SearchService;
import com.project.service.impl.SearchServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet",urlPatterns ={"*.search"})
public class SearchServlet extends HttpServlet {
    private SearchService searchService = new SearchServiceImpl();
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
    private void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nothingFound",null);
        request.setAttribute("presentPage",1);
        request.getRequestDispatcher("/Search.jsp").forward(request,response);
    }
    private void searching(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TravelImage> travelImages = null;
        request.setAttribute("allImages",travelImages);
        long pageAll = 0;
        if(request.getParameter("filter").equals("byTitle")){
            pageAll = searchService.countTitlePage(request.getParameter("searcher"));
        }else{
            pageAll = searchService.countDescriptionPage(request.getParameter("searcher"));
        }
        request.getRequestDispatcher("pageShow.search?searcher="+request.getParameter("searcher")+"&filter"+request.getParameter("filter")+"&order="+request.getParameter("order")+"&pageAll="+pageAll+"&page=1").forward(request,response);
    }
    private void pageShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TravelImage> travelImages;
        String searcher = request.getParameter("searcher");
        int page = Integer.parseInt(request.getParameter("page"));
        if(request.getParameter("filter").equals("byTitle")){
            if (request.getParameter("order").equals("byPop")){
                travelImages = searchService.ImagesTitleSearchByPopSplitPage(searcher,page);
            }else{
                travelImages = searchService.ImagesTitleSearchByTimeSplitPage(searcher,page);
            }
        }else{
            if (request.getParameter("order").equals("byPop")){
                travelImages = searchService.ImagesDescriptionSearchByPopSplitPage(searcher,page);
            }else{
                travelImages = searchService.ImagesDescriptionSearchByTimeSplitPage(searcher,page);
            }
        }
        if(travelImages==null||travelImages.size()==0){
            request.setAttribute("nothingFound",true);
        }
        request.setAttribute("thisPageResult",travelImages);
        request.getRequestDispatcher("/Search.jsp").forward(request,response);
    }
}
