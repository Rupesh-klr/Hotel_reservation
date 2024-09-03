$(document).ready(function() {
    $("#option").change(function (e) {
        $("#regkey").val("")
        $("#oldpass").val("")
        $("#erroldpass").html("<b>&nbsp;*</b><a> <a>");
        $("#err_regkey").html("<b>&nbsp;*</b><a> <a>");
        if($("#option").val()==="key"){
            $("#regkey").removeAttr("disabled");
            $("#oldpass").attr('disabled', 'disabled');
        $("#erroldpass").html("");
        }else{
            $("#err_regkey").html("");
            $("#oldpass").removeAttr("disabled");
            $("#regkey").attr('disabled', 'disabled');
        }
    });
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
    $("#oldpass").blur(function(){
        if($("#oldpass").val().trim() === ""){
            $("#erroldpass").html("<b>&nbsp;*</b>Password is requied");
        }else if($("#oldpass").val().trim().length<6){            
            $("#erroldpass").html("<b>&nbsp;*</b>Password length must be atleast 6.")
        }else{
            $("#erroldpass").html("<b>&nbsp;*</b>")
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
    $("#resetpass").blur(function(){
        if($("#resetpass").val().trim() === ""){
            $("#err_Restpass").html("<b>&nbsp;*</b>Password is requied");
        }else if($("#resetpass").val().trim().length<6){            
            $("#err_Restpass").html("<b>&nbsp;*</b>Password length must be atleast 6.")
        }else{
            $("#err_Restpass").html("<b>&nbsp;*</b>")
        }        
    });// resetcp  err_Resetcp
    $("#resetcp").blur(function(){
        if($("#resetcp").val().trim() === ""){
            $("#err_Resetcp").html("<b>&nbsp;*</b>Conform Password is requied");
        }else if($("#resetcp").val().trim()!=$("#resetpass").val().trim()){            
            $("#err_Resetcp").html("<b>&nbsp;*</b>password not matched.")
        }else{
            $("#err_Resetcp").html("<b>&nbsp;*</b>")
        }        
    });
    $("#resetBtn").click(function(){
        if(validerestfrom()){
            requestresestpassword()
        }
    });
    //updateUserRequest
});
function validerestfrom(){
    var flage=true;
    // flage=false;
    if($("#option").val()==="key"){// validate key
        if($("#regkey").val().trim() === ""){
            $("#err_regkey").html("<b>&nbsp;*</b>secertkey is requied");flage=false;
        }else if($("#regkey").val().trim().length>6 && $("#regkey").val().trim().length<15){            
            $("#err_regkey").html("<b>&nbsp;*</b>")        
        }else{
            $("#err_regkey").html("<b>&nbsp;*</b>secertkey length should be in between 6-15");flage=false;
        }    
    $("#erroldpass").html("");
    }else{// validate password
        $("#err_regkey").html("");
        if($("#oldpass").val().trim() === ""){
            $("#erroldpass").html("<b>&nbsp;*</b>Password is requied");flage=false;
        }else if($("#oldpass").val().trim().length<6){            
            $("#erroldpass").html("<b>&nbsp;*</b>Password length must be atleast 6.");flage=false;
        }else{
            $("#erroldpass").html("<b>&nbsp;*</b>")
        }   
    }
    if($("#LoginUserName").val() === ""){
        $("#errloginname").html("<b>&nbsp;*</b>Email is requied");flage=false;
    }else{
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if($("#LoginUserName").val().match(mailformat)){
            $("#errloginname").html("<b>&nbsp;*</b><a> <a>");
            //true part
        }else{
        $("#errloginname").html("<b>&nbsp;*</b>Invalid email id is requied");flage=false;
        }
    }
    if($("#resetpass").val().trim() === ""){
        $("#err_Restpass").html("<b>&nbsp;*</b>Password is requied");flage=false;
    }else if($("#resetpass").val().trim().length<6){            
        $("#err_Restpass").html("<b>&nbsp;*</b>Password length must be atleast 6.");flage=false;
    }else{
        $("#err_Restpass").html("<b>&nbsp;*</b>")
    }   
    if($("#resetcp").val().trim() === ""){
        $("#err_Resetcp").html("<b>&nbsp;*</b>Conform Password is requied");flage=false;
    }else if($("#resetcp").val().trim()!=$("#resetpass").val().trim()){            
        $("#err_Resetcp").html("<b>&nbsp;*</b>password not matched.");flage=false;
    }else{
        $("#err_Resetcp").html("<b>&nbsp;*</b>")
    }  
    return flage;
}
function requestresestpassword(){
    var temp="";
    if($("#option").val()==="key"){
      temp=$("#regkey").val().trim() 
    }else{
        temp=$("#oldpass").val().trim()
    }
    var obj={
        "opertion":"forgotPass",
        "newpass":$("#resetpass").val(),
        "email": $("#LoginUserName").val(),
        "value1":temp
    }
    ajaxobj={
        url:"http://localhost:8080/HotelReservation/updateUserRequest",
        type:"POST",
        data:obj,
        datatype:'json'
      }
      commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("unable to reset password!")          
          return;
        }
        if(typeof(data)==="string"){
            data=JSON.parse(data)
        }//serachrequest
      });
}