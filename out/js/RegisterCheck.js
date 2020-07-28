var key = CryptoJS.enc.Utf8.parse('o7H8uIM2O5qv65l2');//秘钥
var show_num = [];
draw(show_num);
document.getElementById("canvas").onclick= function(){
    draw(show_num);
};
function encrypt(srcs){

    var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});

    return encrypted.toString();

}
/**
 * @return {boolean}
 */
function RegisterCheck() {
    let name = document.getElementsByName("userName")[0].value;
    let email = document.getElementsByName("email")[0].value;
    let pass = document.getElementsByName("password")[0].value;
    let passConfirm = document.getElementsByName("passwordConfirm")[0].value;
    let val = document.getElementById("code").value;
    let num = show_num.join("");
    if(name.match("^[a-zA-Z0-9_]{4,15}$")==null||name===""){
        alert("Your name is illegal!(4-15,A-Z or a-z or 0-9 or _");
        return false;
    }
    if(pass.match("^[a-zA-Z0-9_]{6,12}$")==null||pass===""){
        alert("Your password is illegal!(6-12,A-Z or a-z or 0-9 or _");
        return false;
    }
    if(email.match("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")==null||email==="") {
        alert("Your email is illegal!");
        return false;
    }
    if(pass!==passConfirm){
        alert("Two input password must be consistent.");
        return false;
    }
    if(val===''){
        alert('Please put in the code!');
        return false;
    }
    if(val !== num){
        alert('Wrong Code!');
        document.getElementById("code").value="";
        draw(show_num);
        return false;
    }
    let password = document.getElementsByName("password")[0];
    password.value = encrypt(password.value);
    return true;


}

function passStrength() {
    let pass = document.getElementsByName("password")[0].value;
    let mark = 0;
    if(pass.match("^.*[0-9]+.*$")!=null){
        mark++;
    }
    if(pass.match("^.*[A-Z]+.*$")!=null){
        mark++;
    }
    if(pass.match("^.*[a-z]+.*$")!=null){
        mark++;
    }
    if(pass.match("^.*[_]+.*$")!=null){
        mark++;
    }
    let outPut = document.getElementById("passStrength");
    if(mark===1){
        outPut.style.color="red";
        outPut.innerText="weak";
    }
    if(mark===2){
        outPut.style.color="orange";
        outPut.innerText="medium";
    }
    if(mark===3){
        outPut.style.color="green";
        outPut.innerText="strong";
    }
    if(mark===4){
        outPut.style.color="green";
        outPut.innerText="very strong";
    }
}

//生成并渲染出验证码图形
function draw(show_num) {
    let canvas = document.getElementById("canvas");
    let canvas_width=$("#canvas").width();
    let canvas_height=$("#canvas").height();
    let context = canvas.getContext("2d");//获取到canvas画图的环境
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    let codes = ['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
    for (let i = 0; i < 4; i++) {
        let j = Math.floor(Math.random() * codes.length);

        let txt = codes[j];
        show_num[i] = txt.toLowerCase();
        let x = 10 + i * 20;//文字在canvas上的x坐标
        let y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";
        let deg = Math.random() - 0.5; //产生一个随机弧度
        context.translate(x, y);
        context.rotate(deg);
        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);
        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (let i = 0; i <= 7; i++) { //线
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (let i = 0; i <= 50; i++) { //点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}

//得到随机的颜色值
function randomColor() {
    var r = Math.floor(Math.random() * 206)+50;
    var g = Math.floor(Math.random() * 206)+50;
    var b = Math.floor(Math.random() * 206)+50;
    return "rgb(" + r + "," + g + "," + b + ")";
}