package com.project.web.servlet;

import com.project.dao.UserDao;
import com.project.dao.impl.UserDaoImpl;
import com.project.pojo.TravelUser;
import com.project.service.FriendsService;
import com.project.service.impl.FriendsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "FriendsServlet",urlPatterns ={"*.friend"})
public class FriendsServlet extends HttpServlet {
    FriendsService friendsService = new FriendsServiceImpl();
    UserDao userDao= new UserDaoImpl();
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
    private void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = (int) request.getSession().getAttribute("uid");
        List<TravelUser> friends;
        friends = friendsService.showAllByUid(uid);
        request.setAttribute("thisPageResult",friends);
        request.getRequestDispatcher("Friends.jsp").forward(request,response);
    }
    private void searching(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("searcher");
        List<TravelUser> travelUsers = userDao.searchByUserName(userName);
        request.setAttribute("searchResult",travelUsers);
        request.getRequestDispatcher("init.friend").forward(request,response);
    }
    private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int friendUid = Integer.parseInt(request.getParameter("friendsUid"));
        friendsService.order((int)request.getSession().getAttribute("uid"),friendUid);
        request.getRequestDispatcher("init.friend").forward(request,response);
    }
}
