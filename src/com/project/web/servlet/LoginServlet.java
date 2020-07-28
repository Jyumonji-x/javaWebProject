package com.project.web.servlet;

import com.project.service.IndexService;
import com.project.service.LoginService;
import com.project.service.impl.LoginServiceImpl;
import com.project.util.EncryptUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

@WebServlet(name = "LoginServlet",urlPatterns ={"*.login"})
public class LoginServlet extends HttpServlet {
    private LoginService loginService= new LoginServiceImpl();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
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
    private void login(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String userNameOrEmail = request.getParameter("userNameOrEmail");
        String pass = request.getParameter("password");
        String key = "o7H8uIM2O5qv65l2";
        pass = EncryptUtil.aesDecrypt(pass,key);
        if (loginService.isUser(userNameOrEmail, pass)) {
            request.setAttribute("hasWrongNameOrPass", null);
            request.getSession().setAttribute("uid", loginService.getUserUid(userNameOrEmail, pass));
            request.getRequestDispatcher("/loginSuccess.jsp").forward(request,response);
        } else {
            request.setAttribute("hasWrongNameOrPass", true);
            request.setAttribute("userNameOrEmail",request.getParameter("userNameOrEmail"));
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }
    private void logOut(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("uid");
        request.getRequestDispatcher("init.login").forward(request, response);
    }
    private void init(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hasWrongNameOrPass", null);
        request.setAttribute("userNameOrEmail",null);
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }
}
