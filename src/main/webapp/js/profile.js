var userDetail={}
$(document).ready(function() {
  updateProfiledata();
      $("#fromBlock").on("click", "#requestBtn", function(e) {
        console.log("clicked re");
        if(true){
          var reqFrom={
            "ProofDocumentNumber":$("#floatingInputValue").val().trim()||"sampleNumber",
            "proofType":$("#proofType").val()||"1",
            "AttachmentTA":$("#AttachmentTA").val().trim()||"attachment message here.",
            "id":sessionStorage.getItem("userId"),
          }
          ajaxobj={
            url:"http://localhost:8080/HotelReservation/addApproval",
          type:"POST",
          data:reqFrom,
          datatype:"json"
          }
          commonAjaxcall(ajaxobj,function responseCallback(data,status){
            if(data===null){
              console.log("try again later");
              alert("try again later.server was busy!"+status);
              return;
            }
            else if(data){
            // console.log(data);
            // alert(data.datapack);
            if(confirm(data.datapack+"\nyou need to continue!")){
              location.href="home.html";
            }
            }
          });
          // console.log(reqFrom);
        }
      })
      $("body").on("click", "#closeBtn", function(e) {
        $("#fromBlock").hide();
      })
      $("#fromBlock").on("click", "#dicardBtn", function(e) {
        console.log("clicked discard");
        $("#fromBlock").hide();
      })
      $("h5").on("click", "#approvalRequest", function(e) {
        $("#fromBlock").show();
      });
});
function updateProfiledata(){
  var userDetail= updateRequested(JSON.parse(sessionStorage.getItem("user")));//ldfklfj
      function getRequeststatus(value){
        // return `0<button type="button" name="requestForm" id="approvalRequest" class="btn btn-primary mx-2 "btn-lg btn-block">request</button>`;
        //return `you request is rejected.<br>Missing some reqirement try study poily onces again try again.<button type="button" name="requestForm" id="approvalRequest" class="btn btn-primary" btn-lg btn-block">request</button>`;
        var rs="";
        console.log(value==="-1",value===-1);
        if(value===1 || sessionStorage.getItem("userRole")==="owner" ||sessionStorage.getItem("userRole")==="coOrdinator" || sessionStorage.getItem("userRole")==="admin" ){
          return "YOUR a member. Conragulations."
        }else if(value===0){
          return `0<button type="button" name="requestForm" id="approvalRequest" class="btn btn-primary mx-2 "btn-lg btn-block">request</button>`;
        }else if(value==="2"|| value===2){
          return "we have recived your requested wait still response."
      }
        return `you request is rejected.<br>Missing some reqirement try study poily onces again try again.<button type="button" name="requestForm" id="approvalRequest" class="btn btn-primary" btn-lg btn-block">request</button>`;
      }
      requestsession=getRequeststatus(userDetail.Requested)
      imgprofile=userDetail.photoRef==='NTH'?'profile0.jpg':userDetail.photoRef;
      datamsg=userDetail.apporavalmsg==='NTH'?'Nothing to dispaly':userDetail.apporavalmsg
      htmlcontainProfilebody=`
        <img class="card-sm-img mx-auto" class=" mx-auto" src='../assets/img/profiles/${imgprofile}' alt="profile0.jpg" width="350px" >
        <div class="row">        
            <div class="col-sm-8 mx-auto">
                <div class="card ">
                  <div class="card-body">
                    <h5 class="card-title text-end">Name</h5>
                    <h5 class="card-title">${userDetail.userName}</h5>
                    <h5 class="card-title">Email</h5>
                    <h5 class="card-title">${userDetail.userEmail}</h5>
                    <h5 class="card-title">Number</h5>
                    <h5 class="card-title">${userDetail.userNumber}</h5>
                    <h5 class="card-title">Role</h5>
                    <h5 class="card-title">${userDetail.role}</h5>
                    <h5 class="card-title">Requested</h5>
                    <h5 class="card-title">${requestsession}</h5>
                  </div>
                </div>
              </div>
              <h6 class="col-12 mx-4 px-3 pb-0 d-inline">Message </h6>
              <p class="mx-4 px-3 pb-0 d-inline">${datamsg}</p>
      </div>`;
      $("#profileBody").html(htmlcontainProfilebody);

}
function updateRequested(userdata){
  ajaxobj={
    url:"http://localhost:8080/HotelReservation/selectUserById",
  type:"POST",
  data:{"operation":"requests","id":sessionStorage.getItem("userId")},
  datatype:"json"
  }
  commonAjaxcall(ajaxobj,function responseCallback(data,status){
    if(data===null){
      console.log("try again later");
      alert("try again later.server was busy!"+status);
      return;
    }
    if(typeof(data)==="string"){
      data=JSON.parse(data)
    }
    else if(data.status===1){
      temp=data.datapack
      userdata.apporavalmsg=temp.apporavalmsg
      userdata.Requested=temp.Requested
      userdata.role=temp.role
      sessionStorage.setItem("userRole",temp.role)    
      console.log(userdata);
      sessionStorage.setItem("user",JSON.stringify( userdata));
    }
  });
  console.log(userdata);
  return userdata;
}