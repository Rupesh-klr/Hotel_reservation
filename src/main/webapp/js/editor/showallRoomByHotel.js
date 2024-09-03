$(document).ready(function() {
      ajaxroomlist()
      $("#updateroomBtn").click(function(){ roomupdate(); });
});
function dataTableShow(tableselector,dataPacket){
    console.log(dataPacket);
    tableselector.DataTable({
      "processing": true,
    //  "serverSide": true,
        data: dataPacket,
        columns: [
          { data: 'HostelId' },
          { data: 'Roomid' },
          { data: 'Rmcode',className: 'dt-center editor-edit text-center' },
          { data: 'RoomType' },
          { data: 'price' },
          { data: 'BedCount' },
          { data: 'CanStaycount' },
          { data: null,
            className: 'dt-center editor-edit',
            defaultContent: '<i class="fa fa-pencil"/>',
            defaultContent: `<button type="button" class="btn btn-primary btn-sm mb-1 ">Edit</button>
            <button type="button" class="btn btn-secondary btn-sm mt-1">Delet</button>`,
            orderable: false         
           },       
        ],
        responsive: true,
       dom: 'Bfrtip'
      });
      $("#RoomByHotel").on("click", "button.btn.btn-secondary.btn-sm.mt-1", function(e) {
        if(confirm("Do you wanted to deleat Room With id:"+($(this).closest('tr').find("td:eq(1)").text()))){
          console.log("Deleting room");
          ajaxobj={
            url:"http://localhost:8080/HotelReservation/delectRoomByRMid",
            type:"POST",
            data:{"RMId":($(this).closest('tr').find("td:eq(1)").text()),"id":sessionStorage.getItem("userId")},
            datatype:"json"
          }
          commonAjaxcall(ajaxobj,function responseCallback(data,status){
            
            if(data===null){
                alert("Soory somthing went while handling data!")
              return;
            }if(typeof(data)==='string')
                data=JSON.parse(data)
            if(data.status===1){alert("\nROOM deleted"); location.reload();
            }else{alert("Nothing stored try again with correct process.")}
          });
        }
      });
      $("#RoomByHotel").on("click", "button.btn.btn-primary.btn-sm.mb-1", function(e) {
        console.log("room is edit");
        $("#RoomFrom").show();
        sessionStorage.setItem("isEditingRoom",($(this).closest('tr').find("td:eq(1)").text()));
        sessionStorage.setItem("RMId",($(this).closest('tr').find("td:eq(1)").text()));
        $("#rmCode").val($(this).closest('tr').find("td:eq(2)").text());
        $("#RoomType").val($(this).closest('tr').find("td:eq(3)").text());
        $("#price").val($(this).closest('tr').find("td:eq(4)").text());
        $("#rmBed").val($(this).closest('tr').find("td:eq(5)").text());
        $("#CanStay").val($(this).closest('tr').find("td:eq(6)").text());
      });
     
  }

  function ajaxroomlist(){
    ajaxobj={
    url:'http://localhost:8080/HotelReservation/selectRoomByRoomid',
    type:"POST",
    data:{"userId":sessionStorage.getItem("userId"),"id":sessionStorage.getItem("userId")},
    datatype:"json"  }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
    if(data===null){
        alert("sorry for issuses?Please try again",status)
        console.log("error occured during connection"+status);
    return;
    }
    console.log(data);if(data.status===1){
          dataTableShow($('#RoomByHotel'),data.datapack)
    }console.log("point common call ended");
    });
  console.log("point ended");
  }
function roomupdate(){
    console.log(valideRoomFrom());
    if(valideRoomFrom()){
        var isEditing=sessionStorage.getItem("isEditingRoom");
        var RoomFrom={
            "rmbed":$("#rmBed").val(),
          "price":$("#price").val(),
            "rmcode":$("#rmCode").val(),
            "rmtype":$("#RoomType").val(),
            "canstay":$("#CanStay").val(),
            "RMId":sessionStorage.getItem("RMId"),
            "id":sessionStorage.getItem("userId")?sessionStorage.getItem("userId"):"1006"
        }
        ajaxobj={
            url:"http://localhost:8080/HotelReservation/editRoomByOwner",
            type:"POST",
            data:RoomFrom,
            datatype:"json"
          }
          commonAjaxcall(ajaxobj,function responseCallback(data,status){    
            if(data===null){
                alert("Soory somthing went while handling data!")
              return;
            }
            if(typeof(data)==="string"){
                data=JSON.parse(data)
            }
            if(data.status===1){alert("\n Room update"); location.reload();
            }else{alert("Nothing stored try again with correct process.")}
          });
    }
}