$(document).ready(function() {
    $("#HotelName").blur(function(){
        if($("#HotelName").val().trim()===""){
            $("#errHtName").html("<b>&nbsp;*</b>Required .");
        }else if($("#HotelName").val().trim().length<6){
            $("#errHtName").html("<b>&nbsp;*</b>password lenght must be 6");
        }else{
            $("#errHtName").html("<b>&nbsp;*</b><a> <a>");
            // logData.userMain = $("#regname").val();
        }
    });
    $("#HotelLocation").blur(function(){
        if($("#HotelName").val().trim()===""){
            $("#errHtLocation").html("<b>&nbsp;*</b>Required .");
        }else{
            $("#errHtLocation").html("<b>&nbsp;*</b>");
            // logData.userMain = $("#regname").val();
        }
    });
    $("#HotelNum").blur(function(){
        if($("#HotelNum").val() === ""){
            $("#errHtNumber").html("<b>&nbsp;*</b>Requied");
            flage =false;
        }else if(parseInt($("#HotelNum").val()) < 1000000000 || parseInt($("#HotelNum").val())>9999999999){
            $("#errHtNumber").html("<b>&nbsp;*</b>Not a phone Number");
        } else{
            $("#errHtNumber").html("<b>&nbsp;*</b>");
        }
    });
    $("#HotelDesc").blur(function(){
        if($("#HotelDesc").val().trim() === "" && $("#HotelDesc").val().trim().length>10){
            $("#erradd").html("<b>&nbsp;*</b>Requied");
        }else if($("#HotelDesc").val().trim().length<10){
            $("#errHtdes").html("<b>&nbsp;*</b>Valide Description");
        }else{
            $("#errHtdes").html("");
        }
    });
    $("#HotelAddress").blur(function(){
        if($("#HotelAddress").val().trim() === "" && $("#HotelAddress").val().trim().length>10){
            $("#errHtAdd").html("<b>&nbsp;*</b>Requied");
        }else if($("#HotelAddress").val().trim().length<10){
            $("#errHtAdd").html("<b>&nbsp;*</b>Valide Address.");
        }else{
            $("#errHtAdd").html("");
        }
    });
    $("#stsMsg").blur(function(){
        if($("#stsMsg").val().trim() === "" && $("#stsMsg").val().trim().length>10){
            $("#errHtstsMsg").html("<b>&nbsp;*</b>Requied");
        }else if($("#stsMsg").val().trim().length<10){
            $("#errHtstsMsg").html("<b>&nbsp;*</b>Valide Message.");
        }else{
            $("#errHtstsMsg").html("");
        }
    });
    $("#HTRt").blur(function(){
        if($("#HTRt").val() === ""){
            $("#errHTRt").html("<b>&nbsp;*</b>Requied");
        }else if(parseInt($("#HTRt").val()) <0 || parseInt($("#HTRt").val())>5){
            $("#errHTRt").html("<b>&nbsp;*</b>give rating with in 5");
        } else{
            $("#errHTRt").html("<b>&nbsp;*</b>");
        }
    });
    $("#HotelBtn").click(function(){
        if(valideHotelFrom()){
            var displayMsg="Hotel is add!"
            var isEditing=sessionStorage.getItem("isEditing");
            var HotelFrom={
                "HotelName":$("#HotelName").val(),
                "HotelNumber":$("#HotelNum").val(),
                "HotelLocation":$("#HotelLocation").val(),
                "HotelAddress":$("#HotelAddress").val(),
                "Desc":$("#HotelAddress").val(),
                "statusMsg":$("#HotelAddress").val(),
                "HTimage":$("#HTImage").val(),
                "id":sessionStorage.getItem("userId")
            }
            console.log(HotelFrom)
            ajaxobj={
                url:"http://localhost:8080/HotelReservation/addHotel",
                type:"POST",
                data: HotelFrom,
                datatype:"json"
              }
              commonAjaxcall(ajaxobj,function responseCallback(data,status){
                data=JSON.parse(data)
                console.log(data,data.successMsg);
                if(data===null){
                    alert("Soory somthing went while handling data!")
                  return;
                }
                if(data.status===1){alert(displayMsg); location.reload();
                }else{alert("Nothing stored try again with correct process.")}
              });
        }
    });
    $("#HotelDiscard").click(function(){
        console.log("Discarding all changes and going to home page.");
    });
});
function valideHotelFrom(){
    var flage=true;
        if($("#HotelName").val().trim()===""){
            $("#errHtName").html("<b>&nbsp;*</b>Required .");flage=false;
        }else if($("#HotelName").val().trim().length<6){
            $("#errHtName").html("<b>&nbsp;*</b>password lenght must be 6");flage=false;
        }else{
            $("#errHtName").html("<b>&nbsp;*</b><a> <a>");
            // logData.userMain = $("#regname").val();
        }
        if($("#HotelName").val().trim()===""){
            $("#errHtLocation").html("<b>&nbsp;*</b>Required .");flage=false;
        }else{
            $("#errHtLocation").html("<b>&nbsp;*</b>");
            // logData.userMain = $("#regname").val();
        }
        if($("#HotelNum").val() === ""){
            $("#errHtNumber").html("<b>&nbsp;*</b>Requied");flage=false;
            flage =false;
        }else if(parseInt($("#HotelNum").val()) < 1000000000 || parseInt($("#HotelNum").val())>9999999999){
            $("#errHtNumber").html("<b>&nbsp;*</b>Not a phone Number");flage=false;
        } else{
            $("#errHtNumber").html("<b>&nbsp;*</b>");
        }
        if($("#HotelDesc").val().trim() === "" && $("#HotelDesc").val().trim().length>10){
            $("#erradd").html("<b>&nbsp;*</b>Requied");flage=false;
        }else if($("#HotelDesc").val().trim().length<10){
            $("#errHtdes").html("<b>&nbsp;*</b>Valide Description");flage=false;
        }else{
            $("#errHtdes").html("");
        }
        if($("#HotelAddress").val().trim() === "" && $("#HotelAddress").val().trim().length>10){
            $("#errHtAdd").html("<b>&nbsp;*</b>Requied");flage=false;
        }else if($("#HotelAddress").val().trim().length<10){
            $("#errHtAdd").html("<b>&nbsp;*</b>Valide Address.");flage=false;
        }else{
            $("#errHtAdd").html("");
        }
        if($("#stsMsg").val().trim() === "" && $("#stsMsg").val().trim().length>10){
            $("#errHtstsMsg").html("<b>&nbsp;*</b>Requied");flage=false;
        }else if($("#stsMsg").val().trim().length<10){
            $("#errHtstsMsg").html("<b>&nbsp;*</b>Valide Message.");flage=false;
        }else{
            $("#errHtstsMsg").html("");
        }
        return flage;
}