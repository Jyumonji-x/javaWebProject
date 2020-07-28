<%@ page import="com.project.pojo.GeoCountries" %>
<%@ page import="com.project.service.UploadService" %>
<%@ page import="com.project.service.impl.UploadServiceImpl" %>
<%@ page import="com.project.dao.impl.UserDaoImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: 许同樵
  Date: 2020/7/13
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload</title>
    <!-- Required meta tags -->
    <script src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="./css/main.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/uploadMain.css">
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
                        <a class="dropdown-item active" href="Upload.jsp">
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
<main>
    <div class="col-12 bg-light text-dark p-4 mb-4">
        <h1>Upload</h1>
    </div>
    <form class="col-12  bg-light text-dark p-4 mb-4" enctype="multipart/form-data" action="upload.upload" method="post" id="form" onsubmit="return UploadConfirm()">
        <div class="col-12  bg-light text-dark p-4 mb-4" id="photoUploadFrame">
            <input type="file" value="" name="file" id = "input_file" accept="image/gif,image/jpeg,image/jpg,image/png" onchange="imgPreview(this)" required>
            <img class="photoUpload img-fluid" id="imageUpload">
        </div>
        <input class="inputBox" placeholder="Title" type="text" name="title" id="titleBox" required><br>
        <textarea class="inputBox" rows="5" cols="60" placeholder="Description" name="description" id="descriptionBox" required></textarea><br>
        <input class="select" name="content" type="text" id="contents" placeholder="Content" required>
        <select class="select" name="country" id="countries">
            <option value=null>Filter by Country</option>
<%
    UploadService uploadService = new UploadServiceImpl();
    for(GeoCountries geoCountries:uploadService.showCountries()){
%>
            <option value="<%=geoCountries.getIso()%>"><%=geoCountries.getCountry_regionName()%></option>
<%
    }
%>
        </select>
        <select class="select" name="city" id="cities">
            <option value=null>Filter by City</option>
            <script src="js/browseCitiesSelect.js"></script>
        </select>
        <input type="submit" value="Submit">
    </form>

</main>
<footer class="footer">
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    Tongqiao Xu  No.19302010015
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Optional JavaScript -->
<script src="js/photoUpload.js"></script>
<script src="js/UploadConfirm.js"></script>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>
