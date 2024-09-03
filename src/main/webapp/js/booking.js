var roomedateAlreadyBooked=[]
$(document).ready(function() {
  console.log(roomedateAlreadyBooked,"oooooooooooooooooooooooooooooooo");
  roomedateAlreadyBooked=ajaxcallRoomdates();
  $("#roomDetails").html("hellooooo");
  if(sessionStorage.getItem("HotelSelectIdIs")!==null){
    var HotelDet=JSON.parse( sessionStorage.getItem("HotelDetails"))
    // console.log(HotelDet);
    var innerHtml=`<div class="col-12">
    <h1 class="text-center">Welcome to our hotel</h1>
                <h1 class="text-center" >${HotelDet.HostelName.toUpperCase()}</h1>
        <h3 class="text-center">location :${HotelDet.HotelLocation.toUpperCase()},Contact us:${HotelDet.HotelNumber}</h3>
        <h4>Addrees us here:<br>${HotelDet.HotelAddress}</h4>
        </div> `;
    $("#hotelDetails").html(innerHtml);
    var RoomDet=JSON.parse( sessionStorage.getItem("RoomsIsDeatils"))
    console.log(RoomDet);
    var innerHtml1=`<div class="col-12 m-5 fw-bolder">
    <h2 class="text-center"> Room Details</h2>
    <p>Seleted room<br>RoomTypes is ${RoomDet.RoomType.toUpperCase()} Beds is ${RoomDet.BedCount}</p>CanStaycount
    <p1>Prices ${RoomDet.price}</p1>
    </div> `;
    $(".roomDetails").html(innerHtml1);
  }
  
    $("#NoOfGuests").blur(function(){
      if($("#NoOfGuests").val().trim()===""){
          $("#errGuests").html("<b>&nbsp;*</b>Required.");
      }else if(parseInt( $("#NoOfGuests").val().trim())>0 && parseInt( $("#NoOfGuests").val().trim())<11){
          $("#errGuests").html("<b>&nbsp;*</b><a><a>");
      }else{
          $("#errGuests").html("<b>&nbsp;*</b>Perbooking may be 1-10");
      }});
      $("#bookFromdate").blur(function(){
        if($("#bookFromdate").val().trim()===""){
            $("#errdatefrom").html("<b>&nbsp;*</b>Required.");
        }else{
            $("#errdatefrom").html("<b>&nbsp;*</b><a> <a>");
            // logData.userMain = $("#regname").val();
        }});
      $("#bookTodate").blur(function(){
        if($("#bookTodate").val().trim()===""){
            $("#errdateto").html("<b>&nbsp;*</b>Required.");
        }else{
            $("#errdateto").html("<b>&nbsp;*</b><a> <a>");
            // logData.userMain = $("#regname").val();
        }});
      $("#discardBtn").click(function(){
        if(confirm("Need to stop booking.but your almost to booking.")){
        data1.HostelId=sessionStorage.removeItem("HotelSelectIdIs")
        data1.RoomId=sessionStorage.removeItem("SelectedRoomsIs")
        window.location="home.html";
        // location.href="home.html";
        console.log("discarded");
        }
      });
      // var kk={"canStayCount":"1","CheckInTime":"2023-08-02","NoOfDays":10,"advanceMount":"1","HostelId":"4","RoomId":"16","id":"1006"};
      // fun1(kk)
      
      $("#bookNow").click(function(){
        if(validebokingFrom()){
          var date_diff_indays = function(date1, date2) {
              dt1 = new Date(date1);
              dt2 = new Date(date2);
              return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
          }
          var data1={
              'canStayCount': $('#NoOfGuests').val(),
              'CheckInTime' :$('#bookFromdate').val(),
              'NoOfDays': date_diff_indays($('#bookFromdate').val(),$('#bookTodate').val())+1,
              'advanceMount': $('#AdvancesPaid').val(),
              
          }        
          data1.HostelId=sessionStorage.getItem("HotelSelectIdIs")
          data1.RoomId=sessionStorage.getItem("SelectedRoomsIs")
          data1.id=sessionStorage.getItem("userId")
          console.log(data1);
          if(confirm("Are you sure your's booking.")){      
            console.log("conformed");
            sessionStorage.setItem("bookingForm",JSON.stringify( data1))
            try{
                $.ajax({
                type: "POST",
                url: "http://localhost:8080/HotelReservation/appBooking",
                data: data1,
                dataType:'json',
                success: function (msg) {
                    console.table(msg);
                      if (msg.status===1) {
                          // console.log("success fully object recived.");   
                          data1.HostelId=sessionStorage.removeItem("HotelSelectIdIs")
                          data1.RoomId=sessionStorage.removeItem("SelectedRoomsIs")
                          alert("Booking completed.");               
                          location.href="home.html";
                      } else {
                        location.reload();
                        console.log("Cannot able to book !");
                      }
                    }
                ,error:function(data,status,er) {
                    console.log(":details error\n"+data);console.log(data);
                    console.log(" \nstatus: "+status+" er<<\t"+er+"\t>>");
                } ,
                complete: function(){
                    console.log("->");
                } });
          } catch (error) {
              console.log("error "+error);
          } 
          }else{
              console.log("not conformed");
          }          
          }
      });
    console.log(roomedateAlreadyBooked);
});
function validebokingFrom(){
  flage=true;
      if($("#NoOfGuests").val().trim()===""){
        $("#errGuests").html("<b>&nbsp;*</b>Required.");
        flage= false;
      }else  if(parseInt( $("#NoOfGuests").val().trim())>0 && parseInt( $("#NoOfGuests").val().trim())<11){
      }else{
        flage= false;
        $("#errGuests").html("<b>&nbsp;*</b>Perbooking may be 1-10");
      }
      if($("#bookFromdate").val().trim()===""){
        $("#errdatefrom").html("<b>&nbsp;*</b>Required.");
        flage= false;
      }
      if($("#bookTodate").val().trim()===""){
        $("#errdateto").html("<b>&nbsp;*</b>Required.");
        flage= false;
      }
  return flage;
}
function fun1(data1){
  try {
    sessionStorage.setItem("bookingForm",JSON.stringify( data1))
    $.ajax({
    type: "POST",
    url: "http://localhost:8080/HotelReservation/appBooking",
    data: data1,
    dataType:'json',
    success: function (msg) {
        console.table(msg);
          if (msg.status===1) {
              // console.log("success fully object recived.");   
              data1.HostelId=sessionStorage.removeItem("HotelSelectIdIs")
              data1.RoomId=sessionStorage.removeItem("SelectedRoomsIs")
              window.location="home.html";
              location.href="home.html";
              alert("booking conformed");               
          } else {
            location.reload();
            console.log("Cannot able to book !");
          }
        }
    ,error:function(data,status,er) {
        console.log(":details error\n"+data);console.log(data);
        console.log(" \nstatus: "+status+" er<<\t"+er+"\t>>");
    } ,
    complete: function(){
        console.log("->");
    } });
  } catch (error) {
      console.log("error "+error);
  } 
}
function elementesZeroInDate(date){
  var arr=[],te=[]
  for(let i=0;i<date.length;i++){
      te=date[i].split("-")
      te[1]=parseInt( te[1])
      te[2]=parseInt( te[2])
      arr.push (te.join("-"))
  }
  return arr;
}
function ajaxcallRoomdates(){
  var datesbookedIs=[]
  obj={
    url:"http://localhost:8080/HotelReservation/bookingBYDates",
    type:"POST",
    // data:{"RoomId":11},
    data:{"RoomId":JSON.parse(sessionStorage.getItem("SelectedRoomsIs"))},
    datatype:"json"
  }
  commonAjaxcall(obj,function responseCallback(data,status){
    if(data !== null){
      datesbookedIs= data.datapack;
      roomedateAlreadyBooked= data.datapack;
      console.log(datesbookedIs)
      console.log(roomedateAlreadyBooked)
      return data.datapack;
    }else{
      console.log(status);
    }
	}); 
  return datesbookedIs;
}

$(document).ready(function() {
  roomedateAlreadyBooked=ajaxcallRoomdates();
  console.log(roomedateAlreadyBooked);
  function unavailable(date) {
    var bookeddate=elementesZeroInDate(roomedateAlreadyBooked);
    dmy = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
    console.log(dmy);
    if ($.inArray(dmy, bookeddate) == -1) {
      return [true, ""];
    } else {
      return [false,"","Unavailable"];
    }
  }
  console.log(roomedateAlreadyBooked);
  $( "#bookFromdate" ).datepicker({
    dateFormat: 'yy-mm-dd',
    showOn: "button",
    buttonImage: "images/calendar.gif",
    buttonImageOnly: true,
    changeMonth: true,
    minDate: 0, 
    maxDate: "+2M +2D" ,
    numberOfMonths: 1,
    showButtonPanel: true,
    todayHighlight:true,
    autoclose:true,
    clearBtn:true,
    multidate: true,
    beforeShowDay: unavailable
  }).on( "change", function() {
    to.datepicker( "option", "minDate", $( "#bookFromdate" ).val() );
    // to.datepicker( "option", "maxDate", "+10D" );
  }),to = $( "#bookTodate" ).datepicker({
    dateFormat: 'yy-mm-dd',
    showOn: "button",
    minDate: 0, 
    buttonImage: "images/calendar.gif",
    buttonImageOnly: true,
    maxDate: "+2M +2D" ,
    changeMonth: true,
    numberOfMonths: 1,
    showButtonPanel: true,
    todayHighlight:true,
    autoclose:true,
    showButtonPanel: true,
    beforeShowDay: unavailable
  });

  $('#datepicker').datepicker({
    // startDate: new Date(),
    // format: "dd/mm/yyyy",
    // daysOfWeekHighlighted: "5,6",
    // datesDisabled: ['31/08/2017'],
    // language: 'en',
    dateFormat: 'yy-mm-dd',
    // showOn: "button",
    // buttonImage: "images/calendar.gif",
    // buttonImageOnly: true,
    maxDate: "+2M +2D" ,
    changeMonth: true,
    numberOfMonths: 1,
    showButtonPanel: true,
    todayHighlight:true,
    autoclose:true,
    showButtonPanel: true,
    multidate: true,
    beforeShowDay: unavailable
}).on('changeDate', function(e) {
    $(this).find('.input-group-addon .count').text(' ' + e.dates.length);
});
// $( "#bookFromdate" ).val()
});

/*
$.ajax({
    type: "POST",
    url: "http://localhost:8080/HotelReservation/appBooking",
    data: data1,
    dataType:'json',
    success: function (msg) {
        console.table(msg);
          if (msg.status===1) {
              // console.log("success fully object recived.");   
              data1.HostelId=sessionStorage.removeItem("HotelSelectIdIs")
              data1.RoomId=sessionStorage.removeItem("SelectedRoomsIs")
              window.location="home.html";
              location.href="home.html";
              alert("booking conformed");               
          } else {
            location.reload();
            console.log("Cannot able to book !");
          }
        }
    ,error:function(data,status,er) {
        console.log(":details error\n"+data);console.log(data);
        console.log(" \nstatus: "+status+" er<<\t"+er+"\t>>");
    } ,
    complete: function(){
        console.log("->");
    } });
*/