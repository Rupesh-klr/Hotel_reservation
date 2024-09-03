var activepage="login"
$(document).ready(function () {
    $('.message a').click(function(){
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
        // $("input").val("");
        console.log("checked -->>");
        $(".warn").html("<b>&nbsp;*</b> </span>");
    });

    $("body").on("click","#homeredirector",function(){
        console.log("cliked");
        location.href=("home.html");
    });
    $("#regname").blur(function(){
        if($("#regname").val().trim()===""){
            $("#err_regname").html("<b>&nbsp;*</b>User name is required .");
        }else if($("#regname").val().trim().length<6){
            $("#err_regname").html("<b>&nbsp;*</b>password lenght must be 6");
        }else{
            $("#err_regname").html("<b>&nbsp;*</b><a> <a>");
            // logData.userMain = $("#regname").val();
        }
    });
    $("#regemail").blur(function(){
        if($("#regemail").val().trim() === ""){
            $("#err_regemail").html("<b>&nbsp;*</b>Email is requied");
        }else{
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if($("#regemail").val().match(mailformat)){
                $("#err_regemail").html("<b>&nbsp;*</b><a> <a>");
                //true part
            }else{
            $("#err_regemail").html("<b>&nbsp;*</b>Invalid email id is requied")
            }
        }
    });
    $("#regpass").blur(function(){
        if($("#regpass").val().trim() === ""){
            $("#err_regpass").html("<b>&nbsp;*</b>Password is requied");
        }else if($("#regpass").val().trim().length<6){            
            $("#err_regpass").html("<b>&nbsp;*</b>Password length must be atleast 6.")
        }else{
            $("#err_regpass").html("<b>&nbsp;*</b>")
        }        
    });
    $("#regcp").blur(function(){
        if($("#regcp").val().trim() === ""){
            $("#err_regcp").html("<b>&nbsp;*</b>Conform Password is requied");
        }else if($("#regcp").val().trim()!=$("#regpass").val().trim()){            
            $("#err_regcp").html("<b>&nbsp;*</b>password not matched.")
        }else{
            $("#err_regcp").html("<b>&nbsp;*</b>")
        }        
    });
    $("#regnumber").blur(function(){
        if($("#regnumber").val().trim() === ""){
            $("#err_regnumber").html("<b>&nbsp;*</b>number is requied");
        }else if($("#regnumber").val().trim().length<10){            
            $("#err_regnumber").html("<b>&nbsp;*</b>number is less than 10 digits")        
        }else if($("#regnumber").val().trim().length>10){            
            $("#err_regnumber").html("<b>&nbsp;*</b>number is more than 10 digits")
        }else{
            $("#err_regnumber").html("<b>&nbsp;*</b>")
        }        
    });
    $("#regkey").blur(function(){
        if($("#regkey").val().trim() === ""){
            $("#err_regkey").html("<b>&nbsp;*</b>secertkey is requied");
        }else if($("#regkey").val().trim().length>6 && $("#regkey").val().trim().length<15){            
            $("#err_regkey").html("<b>&nbsp;*</b>")        
        }else{
            $("#err_regkey").html("<b>&nbsp;*</b>secertkey length should be in between 6-15")
        }        
    });
    $("#agreed").blur(function(){
        if(!$("#agreed").is(":checked")){
            $("#erragreed").html("<b>&nbsp;*</b>Requied");
        }else{
            $("#erragreed").html("<b>&nbsp;*</b>");
        }
    });

    $("#submitBtnSingup").click(function (e) {
       console.log("sig up --->>");
        if(singupformisvalidate()){
            var regData={"userName":$("#regname").val(),
                    "userEmail":$("#regemail").val(),
                    "userPass":$("#regpass").val(),
                    "userNumber":$("#regnumber").val(),
                    "userKey":$("#regkey").val()}
            ajaxobj={
                url:"http://localhost:8080/HotelReservation/addUser",
                type:"POST",datatype:'json',
                data:regData}
            commonAjaxcall(ajaxobj,function responseCallback(msg,status){
                if(msg===null){                    
                    console.log("error is "+status);return;
                }
                msg=JSON.parse(msg)
                if (msg.status === 0) {
                    alert("Email id already exist try with another email.\nLogin to that email.\nOr try to use forgot Password.")                
                    $("#err_regemail").html("<b>&nbsp;*</b>Email is already used.");
                } else if (msg.status === -1){
                    console.log(msg.errorMsg , "\n" , msg.logData  );
                    location.reload();
                }else{alert("you have registred.");
                    location.href="home.html";}      
            });   
        }
    });
 });
$(document).ready(function() {
    $("#LoginUserName").blur(function(){
        if($("#LoginUserName").val() === ""){
            $("#errloginname").html("<b>&nbsp;*</b>Email is requied");
        }else{
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if($("#LoginUserName").val().match(mailformat)){
                $("#errloginname").html("<b>&nbsp;*</b><a> <a>");
                //true part
            }else{
            $("#errloginname").html("<b>&nbsp;*</b>Invalid email id is requied")
            }
        }
    });
   $("#LoginPass").blur(function(){
            if($("#LoginPass").val().trim()===""){
                $("#errloginpass").html("<b>&nbsp;*</b><a>Password is required .<a>");
            }else if($("#LoginPass").val().trim().length<6){
                $("#errloginpass").html("<b>&nbsp;*</b><a>password lenght must be 6<a>");
            }else{
                $("#errloginpass").html("<b>&nbsp;*</b><a> <a>");
                logData.userMain = $("#LoginUserName").val();
            }
        });
        // console.log(flage+"----------->>");
    $("#submitBtnLogin").click(function (e) {  
        if(isvalideLoginfields()){ 
            var logData={
                        "userMain":$("#LoginUserName").val(),
                        "userPass":$("#LoginPass").val(),
                        "role":$("#role").val()
                        ,"isId":"No"};      
            ajaxobj={
                url:"http://localhost:8080/HotelReservation/loginUserCheck",
                type:"POST",datatype:'json',
                data:logData}
                commonAjaxcall(ajaxobj,function responseCallback(msg,status){
                if(msg===null){                    
                    console.log("error is "+status);return;
                }
                console.log(msg);
                if(typeof(msg)==="string")
                    msg=JSON.parse(msg)
                console.log(msg.status);
                if (msg.status === 0) {
                    alert("Email and password not matched.\n Try to use forgot Password. To reset password!.")                
                    $("#errloginrole").html("<b>&nbsp;*</b>Email and password not matched.");
                } else if (msg.status === -1){
                    console.log(msg.errorMsg , "\n" , msg.logData );
                    location.reload();
                }else{   validateduser(msg);}                
                });   
        }  
    });
 });
function singupformisvalidate() {   
    var i=0;         
    if($("#regname").val().trim()===""){
        return false;
    }else if($("#regname").val().trim().length<6){
        return false;
    }
    // console.log(i++ +"hello");
    if($("#regemail").val().trim() === ""){
        return false;
    }else{
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if($("#regemail").val().match(mailformat)){}
        else{return false;}
    }
    // console.log(i++ +"hello");
    if($("#regemail").val().trim() === ""){
        return false;
    }else if($("#regpass").val().trim().length<6){            
        return false;
    }    
    // console.log(i++ +"hello");    
    if($("#regcp").val().trim() === ""){
        return false;
    }else if($("#regcp").val().trim()!=$("#regpass").val().trim()){            
        return false;
    }       
    if($("#regnumber").val().trim() === ""){
        return false;
    }else if($("#regnumber").val().trim().length<10){            
        return false;       
    }else if($("#regnumber").val().trim().length>10){            
        return false;
    }       
    if($("#regkey").val().trim() === ""){
        return false;
    }else if($("#regkey").val().trim().length>6 && $("#regkey").val().trim().length<15){  }
    else{ return false;  }       
    if(!$("#agreed").is(":checked")){
        return false;
    }
    return true;
}
function isvalideLoginfields(){
    if($("#LoginUserName").val() === ""){
        return false;
    }else{
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if($("#LoginUserName").val().match(mailformat)){}
        else{return false;}
    }
    if($("#LoginPass").val().trim()===""){
        return false;
    }else if($("#LoginPass").val().trim().length<6){
        return false;
    }
    return true;
}
function validateduser(msg){
    console.log(msg);
    localStorage.setItem("user",JSON.stringify(msg.datapack))
    // console.log(userId);
    localStorage.setItem("userId",msg.datapack.id)
    localStorage.setItem("logedIn","YES")
    sessionStorage.setItem("user",JSON.stringify(msg.datapack));
    sessionStorage.setItem("userId",msg.datapack.id);
    sessionStorage.setItem("logedIn","YES")
    sessionStorage.setItem("userRole",msg.datapack.role)
    alert("Wait loging user account.");
    window.location="home.html";
    location.href="home.html";
}