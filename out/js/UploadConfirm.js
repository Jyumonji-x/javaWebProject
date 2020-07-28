function UploadConfirm() {
    let imageID=1;
    let url = window.location.href;
    let position = url.indexOf("?imageID=")+9;
    if(position!==8){
        imageID = url.substring(position);
    }
    let title = document.getElementById("titleBox").value;
    let description = document.getElementById("descriptionBox").value;
    let content = document.getElementById("contents").value;
    let country = document.getElementById("countries").value;
    let city = document.getElementById("cities").value;
    if(title===""){
        alert("Please put in the Title!");
        return false;
    }
    else if(description===""){
        alert("Please put in the Description!");
        return false;
    }
    else if(content===""){
        alert("Please put in the Content!");
        return false;
    }
    else if(country==="null"){
        alert("Please choose the Country!");
        return false;
    }
    else if(city==="null"){
        alert("Please choose the City!");
        return false;
    }
    else return confirm('Are you sure?') === true;
}