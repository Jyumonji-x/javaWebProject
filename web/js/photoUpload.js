//图片上传后可以显示
function imgPreview(fileDom) {
    //判断是否支持FileReader
    let reader;
    try {
        reader = new FileReader();
    } catch {
        alert("图片无法预览！");
    }
    //获取文件
    const file = fileDom.files[0];
    const imageType = /^image\//;
    //是否是图片
    if(!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //图片路径设置为读取的图片
        document.getElementsByClassName('photoUpload')[0].src = e.target.result;
    };
    reader.readAsDataURL(file);
}