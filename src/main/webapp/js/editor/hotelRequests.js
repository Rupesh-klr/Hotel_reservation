$(document).ready(function() {
    HotelListRequestFun();
});
function HotelListRequestFun(){
    var temp=1006;
    ajaxobj={
    url:'http://localhost:8080/HotelReservation/hoetelListview',
    type:"POST",
    data:{"userId":sessionStorage.getItem("userId"),"id":sessionStorage.getItem("userId"),"opertion":"allmyhotellist"},
    datatype:"json"  }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
    if(data===null){
        alert("sorry for issuses?Please try again",status)
        console.log("error occured during connection"+status);
    return;}
    if(typeof(data)==='string'){
        data=JSON.parse(data)
    }if(data.status===1){
          dataTableShow($('#HotelrequestsStatus'),data.datapack)
    }console.log("point common call ended");
    });
  console.log("point ended");
  }
  function dataTableShow(tableselector,dataPacket){
      tableselector.DataTable({
        "processing": true,
          data: dataPacket, 
           buttons: [
            {extend:    'excelHtml5',
                text:      '<i class="fa fa-file-excel-o"></i>',
                titleAttr: 'Excel'
            }],
            "bPaginate": false,
            'paging': true,
            "bLengthChange": true,
            "bFilter": true,
          columns: [
            { data: 'Hotelid' },
            { data: 'HostelName' },
            { data: 'HotelNumber' },
            { data: 'HotelLocation' },
            { data: 'HotelAddress' },
            { data: 'statumsg' },                  
            { data: 'HTstatus',
              defaultContent: `k1`,
              render: function(data, type,  full, meta) {
                var textvalue="";
                if(data ===0 ) {                        
                  textvalue=`<button type="button" class="btn btn-outline-warning disabled">Pending</button>`                       
                }else if(data===1){textvalue= `<button type="menu" class="btn btn-outline-success  disabled" >Approved</button>`}
                else if(data===-1){textvalue=`<button type="button" class="btn btn-outline-danger  disabled" >Rejected</button>`}   
                else if(data===2){textvalue=`<button type="button" class="btn btn-outline-danger  disabled" >closed hotel</button>`}   

                return  textvalue;  
              },
             }, { data: 'HTstatus'},      
            { data: 'Htisdeleted',
              className: 'dt-center is-active-hotel',
            //   defaultContent: `k1`,
              "scrollX": true,
              render: function(data, type,  full, meta) {
                  var textvalue="";
                  if(data ===0 ) {                           
                    textvalue=`<button type="button" class="btn btn-outline-info  text-dark">Active hotel</button>`                       
                  }else{textvalue=`<button type="menu" class="btn btn-outline-secondary text-danger   ">closed hotel</button>`}            
                  return  textvalue;  
                },         
             },       
          ],
          responsive: true,
         dom: 'Bfrtip'
        });
  }