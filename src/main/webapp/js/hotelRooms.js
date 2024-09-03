$(document).ready(function() {
    var kp=commonValidingFunction();
    const userType=kp[0];   
    const userlogedin=kp[1];
    console.log(kp,userlogedin,userType)
    var Htid=sessionStorage.getItem("HotelSelectIdIs");
    console.log("HTid -->>",Htid)
    function dataRoomList(dataList,st){
        // console.log("funtion1");
        if(dataList===null){
            st.append("<h1>no data to show</h1>");
        }
        if(dataList===[]|| dataList.length===0){
            st.append("<h3 class='text-info text-center'>No rooms available cooming soon.</h3>");
            return 
        }
    	var datat=`<section id="team" class="pb-5"><div class="container"><div class="row">`;
        i=0,value="";
        dataList.forEach(item => {
            // console.log(item);
            value=JSON.stringify(item)
            console.log(item.Roomid);
        var roomimg=item.Rmimage==='NTH'?"../assets/img/rooms/room1whiteice.webp":item.Rmimage;
            datat+=`
                <div class="col-6 col-sm-5 col-md-4 col-lg-3">
                    <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                        <div class="mainflip">
                                <div class="card">
                                    <div class="card-body text-center">
                                        <p><img class=" img-fluid" src="${roomimg}" alt="${roomimg}"></p>
                                        <h4 class="card-title">Price :${item.price}</h4>
                                        <p class="card-text"> 
                                            RoomType :${item.RoomType.toUpperCase()}<br>                                            
                                            BedCount:${item.BedCount}:&nbsp; CayStay :${item.CanStaycount}                                           
                                        </p>
                                        <ul class="list-inline">
                                            <li class="list-inline-item"><a class="social-icon text-xs-center" target="_blank" href="$"><i class="fa fa-facebook"></i></a>
                                            </li>
                                            <li class="list-inline-item"><a class="social-icon text-xs-center" target="_blank" href="#"><i class="fa fa-twitter"></i></a>
                                            </li>
                                            <li class="list-inline-item"><a class="social-icon text-xs-center" target="_blank" href="#"><i class="fa fa-skype"></i>  </a>
                                            </li>
                                            <li class="list-inline-item"><a class="social-icon text-xs-center" target="_blank" href="#"><i class="fa fa-google"></i></a>
                                            </li>
                                        </ul>
                                        <button id="${item.Roomid}" type="button" class="btn  border-0 btn-link mx-5 bookingbythisroom" data-field='${value}'>Check rooms</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`;
        });
        datat+=`</div></div></section>`;
        st.append(datat);
    }
    // Htid=7;
    
    if(sessionStorage.getItem("HotelSelectIdIs")!==null){
        var HotelDet=JSON.parse( sessionStorage.getItem("HotelDetails"))
        // console.log(HotelDet);
        console.log(HotelDet.HostelName.toUpperCase);
        console.log(HotelDet.HostelName.toUpperCase());
        var innerHtml=`<div class="col-12">
        <h1 class="text-center">Welcome to our hotel</h1>
                    <h1 class="text-center" >${HotelDet.HostelName.toUpperCase()}</h1>
            <h3 class="text-center">location :${HotelDet.HotelLocation.toUpperCase()},Contact us:${HotelDet.HotelNumber}</h3>
            <h4>Addrees us here:<br>${HotelDet.HotelAddress}</h4>
            </div> `;
        $("#hotelDetails").html(innerHtml);
    }
    if(Htid!=null){
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/HotelReservation/selectRoomByHotelHTid",
            data: {"request":"data wanted to display","HTId":Htid},
            dataType: 'json',
            success: function (msg) {
                console.log(msg);
                if(msg===null || msg === undefined || msg=== NaN ){
                    msg={"datapack":{"roomList":null}};
                }
                dataRoomList(msg.datapack,$("#roomlistselector"));           
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.table(jqXHR)
            }, complete: function(){
            }        
        });
        // */
    }
    $("div").on("click", ".bookingbythisroom", function(e) {
        console.log("---->>>//");
        console.log($(this).attr('id'));
        sessionStorage.setItem("SelectedRoomsIs",$(this).attr('id'));
        sessionStorage.setItem("RoomsIsDeatils",$(this).attr('data-field'));
        location.href="booking.html";
      });
});