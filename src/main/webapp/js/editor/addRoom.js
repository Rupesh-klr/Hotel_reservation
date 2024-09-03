$(document).ready(function() {
    $("#RoomType").blur(function(){
        if($("#RoomType").val().trim()===""){
            $("#errRMtype").html("<b>&nbsp;*</b>Required .");
        }else if($("#RoomType").val().trim().length<3 ||$("#RoomType").val().trim().length<8 ){
            $("#errRMtype").html("<b>&nbsp;*</b>valide Types");
        }else{
            $("#errRMtype").html("<b>&nbsp;*</b><a> <a>");
            // logData.userMain = $("#regname").val();
        }
    });
    $("#rmCode").blur(function(){
        if($("#rmCode").val().trim()===""){
            $("#errRmC").html("<b>&nbsp;*</b>Required .");
        }else{
            $("#errRmC").html("<b>&nbsp;*</b>");
            // logData.userMain = $("#regname").val();
        }
    });
    $("#price").blur(function(){
        if($("#price").val() === ""){
            $("#errPrice").html("<b>&nbsp;*</b>Requied");
            flage =false;
        }else if(parseInt($("#price").val()) < 250 || parseInt($("#price").val())>10000){
            $("#errPrice").html("<b>&nbsp;*</b>Not a phone Number");
        } else{
            $("#errPrice").html("<b>&nbsp;*</b>");
        }
    });
    $("#rmBed").blur(function(){
        if($("#rmBed").val() === ""){
            $("#errRmBed").html("<b>&nbsp;*</b>Requied");
        }else if(parseInt($("#rmBed").val()) < 1 || parseInt($("#rmBed").val())>10){
            $("#errRmBed").html("<b>&nbsp;*</b>max 10");
        } else{
            $("#errRmBed").html("<b>&nbsp;*</b>");
        }
    });
    $("#CanStay").blur(function(){
        if($("#CanStay").val() === ""){
            $("#errCanStay").html("<b>&nbsp;*</b>Requied");
        }else if(parseInt($("#CanStay").val()) < 1 || parseInt($("#CanStay").val())>400){
            $("#errCanStay").html("<b>&nbsp;*</b>Not a phone Number");
        } else{
            $("#errCanStay").html("<b>&nbsp;*</b>");
        }
    });

    $("#RoomBtn").click(function(){
        if(valideRoomFrom()){
          
            var RoomFrom={
                "rmbed":$("#rmBed").val(),
              "price":$("#price").val(),
                "rmcode":$("#rmCode").val(),
                "rmtype":$("#RoomType").val(),
                "canstay":$("#CanStay").val(),
                "HTId":$("#hotellsit").val() ,
                "id":sessionStorage.getItem("userId")?sessionStorage.getItem("userId"):"1006"
            }
            ajaxobj={
                url:"http://localhost:8080/HotelReservation/addRoomBYHTid",
                type:"POST",
                data:RoomFrom,
                datatype:"json"
              }
              console.log(ajaxobj);
              commonAjaxcall(ajaxobj,function responseCallback(data,status){
                if(data===null){
                    alert("Soory somthing went while handling data!")
                  return;
                }
                if(typeof(data)==="string"){
                    data=JSON.parse(data)
                }
                if(data.status===1){alert("One room is added to hotel successfully."); location.reload();
                }else{alert("failed to add Room")}
              });
        }
    });
    $("#RoomDiscard").click(function(){
        console.log("Discarding all changes and going to home page.");
    });
    // selecthoteloption();
    selecthoteloption()
    
});
function valideRoomFrom(){
    var flage=true;
        if($("#RoomType").val().trim()===""){
            $("#errRMtype").html("<b>&nbsp;*</b>Required ."); flage=false;
        }else if($("#RoomType").val().trim().length<3 ||$("#RoomType").val().trim().length<8 ){
            $("#errRMtype").html("<b>&nbsp;*</b>valide Types");flage=false;
        }
        if($("#rmCode").val().trim()===""){
            $("#errRmC").html("<b>&nbsp;*</b>Required .");flage=false;
        }
        if($("#price").val() === ""){
            $("#errPrice").html("<b>&nbsp;*</b>Requied");flage=false;
        }else if(parseInt($("#price").val()) < 250 || parseInt($("#price").val())>10000){
            $("#errPrice").html("<b>&nbsp;*</b>Not a phone Number");flage=false;
        }
        if($("#CanStay").val() === ""){
            $("#errCanStay").html("<b>&nbsp;*</b>Requied");flage=false;
        }else if(parseInt($("#CanStay").val()) < 1 || parseInt($("#CanStay").val())>400){
            $("#errCanStay").html("<b>&nbsp;*</b>Not a phone Number");flage=false;
        } 
        if($("#rmBed").val() === ""){
            $("#errRmBed").html("<b>&nbsp;*</b>Requied");flage=false;
        }else if(parseInt($("#rmBed").val()) < 1 || parseInt($("#rmBed").val())>10){
            $("#errRmBed").html("<b>&nbsp;*</b>max10");flage=false;
        } 
    return flage;
}
function selecthoteloption(){
    console.log("hotel listed");
    ajaxobj={
        url:"http://localhost:8080/HotelReservation/selectHotelRequest",
        type:"POST",
        data:{"opration":"statustrue","id":sessionStorage.getItem("userId")?sessionStorage.getItem("userId"):"1006",},
        datatype:'json'
      }
      commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry somthing went wrong")
          return;
        }
        if(typeof(data)==="string"){
            data=JSON.parse(data)
        }
        console.log(data);
        if(data.status===1){
            // $("#hotellsit").val() gives the room hotel id
            var htmltx=`<select class="form-control text-center text-capitalize form-select"  aria-label="Default select example" id="hotellsit">`;
            // console.log(data.datapack);
            // console.log(data.datapack.length);
            // console.log(data.datapack[0].length);
            for(let i=0;i<data.datapack.length;i++){
                htmltx+=`<option value="${data.datapack[i].Hotelid}" >${data.datapack[i].HostelName}</option>`
            }
            htmltx+=`</select>`;
            // console.log(htmltx);
            $("#yourhotelselectlist").html(htmltx)
        }
      });
};