$(document).ready(function() {
//HotelListRequest
   
  HotelListRequestFun()
});
function HotelListRequestFun(){
  ajaxobj={
  url:'http://localhost:8080/HotelReservation/updateHotelRequestAdmin?type=get',
  type:"POST",
  data:{"userId":sessionStorage.getItem("userId")},
  datatype:"json"  }
  commonAjaxcall(ajaxobj,function responseCallback(data,status){
  if(data===null){
      alert("sorry for issuses?Please try again",status)
      console.log("error occured during connection"+status);
  return;}
  if(typeof(data)==='string'){
      data=JSON.parse(data)
  }if(data.status===1){
        dataTableShow($('#HotelListRequest'),data.datapack)
  }console.log("point common call ended");
  });
console.log("point ended");
}
function dataTableShow(tableselector,dataPacket){
    tableselector.DataTable({
      "processing": true,
     // "serverSide": true,
        data: dataPacket,
        columns: [
          { data: 'Hotelid' },
          { data: 'id' },
          { data: 'HostelName' },
          { data: 'HotelAddress' },
          { data: 'HotelLocation' },
          { data: 'HotelNumber' },
          { data: 'statumsg' },
          { data: null,
            className: 'dt-center editor-edit',
            defaultContent: '<i class="fa fa-pencil"/>',
            defaultContent: `<button type="button" class="btn btn-success btn-sm mb-1 mx-auto">Accept</button><br><button type="button" class="btn btn-primary btn-sm mt-1 mx-auto">reject</button>`,
            orderable: false         
           },       
        ],
        responsive: true,
       dom: 'Bfrtip'
      });
      
      $("#HotelListRequest").on("click", "button.btn.btn-success.btn-sm.mb-1", function(e) {
        //approval 
        if(confirm("you need to accepthis this hotel "+($(this).closest('tr').find("td:eq(0)").text()))){
            console.log("approvaliong hotel");
            HotelListupdate(($(this).closest('tr').find("td:eq(0)").text()),1)
        }
      });
      $("#HotelListRequest").on("click", "button.btn.btn-primary.btn-sm.mt-1.mx-auto", function(e) {
        if(confirm("you need to reject this hotel "+($(this).closest('tr').find("td:eq(0)").text()))){
            console.log("rejecting hotel");
            HotelListupdate(($(this).closest('tr').find("td:eq(0)").text()),-1)
            $(this).closest('tr').remove()
        }
        console.log("reject hotel");
      });
     
}
  function HotelListupdate(val,sta){
    ajaxobj={
    url:'http://localhost:8080/HotelReservation/updateHotelRequestAdmin?type=update',
    type:"POST",
    data:{"id":sessionStorage.getItem("userId"),"HTId":val,"ModifiedChange":sta},
    datatype:"json" }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
    if(data===null){
        alert("sorry for issuses?Please try again",status)
        console.log("error occured during connection"+status);
    return;}
    if(typeof(data)==='string'){
        data=JSON.parse(data)
    }if(data.status===1){
          alert("update record!")
          sta.remove()
    }
    });
  }
