package com.project.web.servlet;


import com.project.service.RegisterService;
import com.project.service.impl.RegisterServiceImpl;
import com.project.util.EncryptUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

@WebServlet(name = "RegisterServlet",urlPatterns ={"*.reg"})
public class RegisterServlet extends HttpServlet {
    private RegisterService registerService = new RegisterServiceImpl();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 4);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 4);
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String key = "o7H8uIM2O5qv65l2";
        pass = EncryptUtil.aesDecrypt(pass,key);
        if(!registerService.hasSameUser(userName,email)){
            registerService.createUser(userName,email,pass);
            pass = EncryptUtil.aesEncrypt(pass,key);
            response.sendRedirect("login.login?userNameOrEmail="+userName+"&password="+pass);
        }
        else{
            request.setAttribute("sameUserName", true);
            request.setAttribute("userName",request.getParameter("userName"));
            request.setAttribute("email",request.getParameter("email"));
            request.getRequestDispatcher("/Register.jsp").forward(request, response);
        }
    }
    private void init(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sameUserName", null);
        request.setAttribute("userName",null);
        request.setAttribute("email",null);
        request.getRequestDispatcher("/Register.jsp").forward(request, response);
    }
}
