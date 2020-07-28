// 局部放大功能
$(document).ready(function () {
    $("#magnifier").css("transform","scale(2)");
});
$("#photo").mousemove(function (e) {
    $("#magnifier").css("top",(e.offsetY+100)+"px");
    $("#magnifier").css("left",(e.offsetX+300)+"px");
    let width =  $("#magnifier").width();
    let height =  $("#magnifier").height();
    let bigImgWidth = document.getElementById("photo").offsetWidth;
    $("#photoBig").css("width",bigImgWidth);
    let bigImgHeight = document.getElementById("photo").offsetHeight;
    $("#photoBig").css("height",bigImgHeight);
    let marginLeft=e.offsetX-width/2;
    if(marginLeft<0) marginLeft=0;
    if(marginLeft>bigImgWidth-width) marginLeft=bigImgWidth-width;
    let marginTop=e.offsetY-height/2;
    if(marginTop<0) marginTop=0;
    if(marginTop>bigImgHeight-height) marginTop=bigImgHeight-height;
    $("#photoBig").css("margin-left",-marginLeft);
    $("#photoBig").css("margin-top",-marginTop);
});
$("#photo").mouseleave(function () {
    $("#magnifier").css("display","none");
});
$("#photo").mouseover(function () {
    $("#magnifier").css("display","inline");
});