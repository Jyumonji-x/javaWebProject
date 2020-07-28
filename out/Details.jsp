<%@ page import="com.project.pojo.TravelImage" %>
<%@ page import="com.project.dao.impl.UserDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: 许同樵
  Date: 2020/7/13
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Details</title>
    <script src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="./css/magnifier.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<header>
</header>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Album</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="index.index">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="init.search">Search <span class="sr-only">(current)</span></a>
            </li>
            <%
                if(session.getAttribute("uid")!=null){
            %>
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <%=new UserDaoImpl().getByUid((int)session.getAttribute("uid")).getUserName()%>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="init.myPhoto">
                            <svg class="bi bi-image-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M.002 3a2 2 0 012-2h12a2 2 0 012 2v10a2 2 0 01-2 2h-12a2 2 0 01-2-2V3zm1 9l2.646-2.354a.5.5 0 01.63-.062l2.66 1.773 3.71-3.71a.5.5 0 01.577-.094L15.002 9.5V13a1 1 0 01-1 1h-12a1 1 0 01-1-1v-1zm5-6.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0z" clip-rule="evenodd"></path>
                            </svg>
                            Photo
                        </a>
                        <a class="dropdown-item" href="Upload.jsp">
                            <svg class="bi bi-capslock-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M7.27 1.047a1 1 0 011.46 0l6.345 6.77c.6.638.146 1.683-.73 1.683H11.5v1a1 1 0 01-1 1h-5a1 1 0 01-1-1v-1H1.654C.78 9.5.326 8.455.924 7.816L7.27 1.047zM4.5 13.5a1 1 0 011-1h5a1 1 0 011 1v1a1 1 0 01-1 1h-5a1 1 0 01-1-1v-1z" clip-rule="evenodd"></path>
                            </svg>
                            Upload
                        </a>
                        <a class="dropdown-item" href="init.favor">
                            <svg class="bi bi-heart-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" clip-rule="evenodd"></path>
                            </svg>
                            Favourite
                        </a>
                        <a class="dropdown-item" href="init.friend">
                            <svg class="bi bi-heart-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" clip-rule="evenodd"></path>
                            </svg>
                            Friends
                        </a>
                        <a class="dropdown-item" href="logOut.login">
                            <svg class="bi bi-people-circle" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 008 15a6.987 6.987 0 005.468-2.63z"></path>
                                <path fill-rule="evenodd" d="M8 9a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd"></path>
                                <path fill-rule="evenodd" d="M8 1a7 7 0 100 14A7 7 0 008 1zM0 8a8 8 0 1116 0A8 8 0 010 8z" clip-rule="evenodd"></path>
                            </svg>
                            Log out
                        </a>
                    </div>
                </li>
            </ul>
        </ul>
        <%
        }else{
            request.getSession().setAttribute("url","show.detail?imageId="+request.getParameter("imageId"));
        %>
        <li class="nav-item">
            <a class="nav-link" href="init.login">Login<span class="sr-only">(current)</span></a>
        </li>
        </ul>
        <%
            }
        %>
    </div>
</nav>
<%
    TravelImage travelImage = (TravelImage) request.getAttribute("imageInfo");
    int likePeople = (int) request.getAttribute("likePeople");
    String country = (String) request.getAttribute("country");
    String city = (String) request.getAttribute("city");
    String userName = (String) request.getAttribute("userName");

%>
<main class="m-5" id="mainBox">
    <h1>
        <%=travelImage.getTitle()%>
    </h1>
    <div class="row align-items-center bg-light">
        <div class="col-8">
            <img src="./img/images/<%=travelImage.getPath()%>" class="img-fluid m-4" id="photo">
        </div>
        <div id="magnifier">
            <img src="./img/images/<%=travelImage.getPath()%>" id="photoBig">
        </div>
        <div class="col-3 p-4">
            <h2>
                like number: <%=likePeople%>
            </h2>
            <%if (session.getAttribute("uid")==null){
            %>
            <button class="btn btn-danger" disabled>
                like
            </button>
            <%
            }else{
                if((boolean) request.getAttribute("likeOrNot")){
            %>
             <button class="btn btn-danger" onclick="location.href='dislike.detail?imageId=<%=travelImage.getImageID()%>'">
                dislike
             </button>
            <%
                    }else{
            %>
            <button class="btn btn-danger" onclick="location.href='like.detail?imageId=<%=travelImage.getImageID()%>'">
                like
            </button>
            <%
                    }
                }
            %>
            <h2>
                Details
            </h2>
            <p>Content: <%=travelImage.getContent()%></p>
            <p>Country: <%=country%></p>
            <p>City: <%=city%></p>
            <p>Photographer: <%=userName%></p>
            <p>Time: <%=travelImage.getLastModifiedTime()%></p>
        </div>
    </div>
    <div class="m-5">
        <p>
            <%=travelImage.getDescription()%>
        </p>
    </div>
</main>
<footer>
    <div>
        <p>Tongqiao Xu   No. 19302010015</p>
    </div>
</footer>
<script src="js/DetailsPosition.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</html>
