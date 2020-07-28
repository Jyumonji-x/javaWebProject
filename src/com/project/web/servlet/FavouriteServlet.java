package com.project.web.servlet;

import com.project.dao.impl.UserDaoImpl;
import com.project.pojo.TravelImage;
import com.project.service.FavouriteService;
import com.project.service.impl.FavouriteServiceImpl;
import com.project.util.Consts;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "FavouriteServlet",urlPatterns ={"*.favor"})
public class FavouriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FavouriteService favouriteService = new FavouriteServiceImpl();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    private void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nothingFound",null);
        request.setAttribute("presentPage",1);
        long pageAll = 0;
        int uid = (int) request.getSession().getAttribute("uid");

        if(request.getParameter("friendsUid")!=null){
            int friendsUid =Integer.parseInt(request.getParameter("friendsUid"));
            if(favouriteService.showFavors(friendsUid)!=null){
                pageAll = (long)Math.ceil((double)favouriteService.showFavors(friendsUid).size()/(double) Consts.IMAGE_EVERY_PAGE);
                request.setAttribute("nothingFound",null);
            }else{
                request.setAttribute("nothingFound",true);
            }
            request.getRequestDispatcher("pageShow.favor?page="+request.getAttribute("presentPage")+"&pageAll="+pageAll+"&friendsUid="+friendsUid).forward(request,response);
        }else{
            if(favouriteService.showFavors(uid)!=null){
                pageAll = (long)Math.ceil((double)favouriteService.showFavors(uid).size()/(double) Consts.IMAGE_EVERY_PAGE);
                request.setAttribute("nothingFound",null);
            }else{
                request.setAttribute("nothingFound",true);
            }
            request.getRequestDispatcher("pageShow.favor?page="+request.getAttribute("presentPage")+"&pageAll="+pageAll).forward(request,response);
        }
    }
    private void pageShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TravelImage> travelImages;
        int uid = (int) request.getSession().getAttribute("uid");
        int page = Integer.parseInt(request.getParameter("page"));
        if(request.getParameter("friendsUid")!=null){
            request.setAttribute("friendsUid",request.getParameter("friendsUid"));
            request.setAttribute("friendsName", new UserDaoImpl().getByUid(Integer.parseInt(request.getParameter("friendsUid"))).getUserName());
            travelImages = favouriteService.showFavorsByPage(Integer.parseInt(request.getParameter("friendsUid")),page);
        }else{
            travelImages = favouriteService.showFavorsByPage(uid,page);
        }
        if(travelImages==null||travelImages.size()==0){
            request.setAttribute("nothingFound",true);
        }
        request.setAttribute("thisPageResult",travelImages);
        List<TravelImage> footPrints = favouriteService.showFootprints(uid);
        request.setAttribute("footprints",footPrints);
        request.getRequestDispatcher("/Favourite.jsp").forward(request,response);
    }
    private void dislike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageId = Integer.parseInt( request.getParameter("imageId"));
        int uid = (int) request.getSession().getAttribute("uid");
        favouriteService.delete(uid,imageId);
        request.getRequestDispatcher("init.favor").forward(request,response);
    }
    private void setPermission(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        favouriteService.setPermission((int) request.getSession().getAttribute("uid"),"permission".equals(request.getParameter("permission")));
        request.getRequestDispatcher("init.favor").forward(request,response);
    }
}
