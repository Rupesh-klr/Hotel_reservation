$(document).ready(function() {
    console.log("-->");
    ajaxMyBookingList()
});
function ajaxMyBookingList(){
    console.log("point1");
        ajaxobj={
        url:'http://localhost:8080/HotelReservation/bookingByuser',
        type:"POST",
        data:{"id":sessionStorage.getItem("userId")},
        datatype:"json"  }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry for issuses?Please try again",status)
            console.log("error occured during connection"+status);
        return;
        }
        if(data.status===1 || data.status===0){
            $('#MybookingDetails').DataTable({                   
                data: data.datapack,
                columns: [
                    { data: 'BookingId' },
                    { data: 'BookedDate' },
                    { data: 'NoOfGuests' },
                    { data: 'BookingFrom' },
                    { data: 'price' },
                    // { data: 'AttachmentMessage' },
                    {
                        data: null,
                        className: 'dt-center updateStatus',
                        defaultContent: '<button class="btn btn-toolbar btn-primary btn-sm ">Cancle</button>',
                        orderable: false
                    },
                    {
                        data: null,
                        className: 'dt-center view-more',
                        defaultContent: '<button class="btn btn-toolbar btn-Secondary btn-sm ">Now more</button>',
                        orderable: false
                    }
                ],
                dom: 'Bfrtip',
            });
        }
    });

    $("#MybookingDetails").on('click','td.dt-center.updateStatus',function(){clanceBooking($(this).closest('tr').find("td:eq(0)").text(),$(this).closest('tr'))});
    $("#MybookingDetails").on('click','td.dt-center.view-more',function(){viewDetailBTn(($(this).closest('tr').find("td:eq(0)").text()))});
    console.log("point ended");
}
function viewDetailBTn(BookingID){
    console.log("view detail of booking.",BookingID);
    ajaxobj={
        url:'http://localhost:8080/HotelReservation/statusBooking',
        type:"POST",
        data:{"id":sessionStorage.getItem("userId"),"Bookingid":BookingID,"ALL":"NO"},
        datatype:"json"  }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry for issuses?Please try again",status)
            console.log("error occured during connection"+status);
        return;
        }
        console.log(data);
        console.log(data.status===0);
        if(data.status===1 || data.status===0){
            let i=0;
            $("#hotelfulldata").show()
        var text=`
        <!-- <b>user id     :  </b>${data.datapack[0][i++]}  -->
        <!-- <br><b>Booking  id   :  </b> ${data.datapack[0][i++]}  -->
        <h6>Booking details</h6>
        <b> Booking  date   :  </b> ${data.datapack[0][i++]} 
        <br><b>  Booking  from   :  </b>${data.datapack[0][i++]} 
        <br><b>Booking  to   :  </b>${data.datapack[0][i++]} 
       <!-- <hr><b>    Room id   :  </b>${data.datapack[0][i++]}  -->
       <hr><h6>Room details</h6>
      <b>Room advances pay amount   :  </b>${data.datapack[0][i++]}  
      <br><b>Guest staying     :  </b> ${data.datapack[0][i++]} 
      <br><b>room Type   :  </b>${data.datapack[0][i++]} 
       <br><b>Room  total price   :  </b> ${data.datapack[0][i++]} 
       <!-- <hr><b>Hotel id   :  </b> ${data.datapack[0][i++]}  -->
       <hr><h6>Hotel details</h6>
       <b>Hotel   Name   :  </b> ${data.datapack[0][i++]} 
       <br><b>Hotel  Location   :  </b> ${data.datapack[0][i++]} 
       <br><b>Hotel  Address   :  </b> ${data.datapack[0][i++]} 
       <br><b>Hotel  Number   :  </b> ${data.datapack[0][i++]} 
       <!-- <hr><b>user id   :  </b>${data.datapack[0][i++]}   -->
       <hr> <h6>User details</h6>
       <b> user email   :  </b> ${data.datapack[0][i++]} 
       <br><b>  user name   :  </b>${data.datapack[0][i++]} 
       <br><b>  user PhoneNo   :  </b>${data.datapack[0][i++]} 
  `;
            $("#hoteldataall").html(text)
            console.log(data.datapack);
           

            // alert("Booking calnced"+data.datapack)
        }
        console.log("point common call ended");
    });

}
function clanceBooking(BookingId,rem){
    console.log("clanceing",BookingId);
    ajaxobj={
        url:'http://localhost:8080/HotelReservation/closeingBooking',
        type:"POST",
        data:{"id":sessionStorage.getItem("userId"),"BookingId":BookingId},
        datatype:"json"  }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry for issuses?Please try again",status)
            console.log("error occured during connection"+status);
        return;
        }
        if(data.status===1){
            alert("Booking calnced"+data.datapack)
            rem.remove()
        }
        console.log("point common call ended");
    });
}