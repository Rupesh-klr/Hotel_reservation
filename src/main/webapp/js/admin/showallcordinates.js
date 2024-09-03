$(document).ready(function() {
    
    console.log("-->");
    ajaxCordinatorlist()
    $("#UserUpdateForm").on("click","#updateUserAdminBtn",function(){editoruserFromsubmit()});
    console.log("---------------------->>>>>");
});

function ajaxCordinatorlist(){
    ajaxobj={
    url:'http://localhost:8080/HotelReservation/getAllOwner',
    type:"POST",
    data:{"userId":sessionStorage.getItem("userId")},
    datatype:"json"  }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
    if(data===null){
        alert("sorry for issuses?Please try again",status)
        console.log("error occured during connection"+status);
    return;
    }
    if(data.status===1){
        $('#coOrdinators').DataTable({
            data: data.datapack,columns: [
                { data: 'id' },
                { data: 'userName' },
                { data: 'userEmail' },
                { data: 'userNumber' },
                { data: 'photoRef' },
                { data: 'role',orderable: true,"width": "5%"},
                { data: 'role',
                className: 'dt-center editor-roles-selection',
                    "scrollX": true,
                render: function(data, type,  full, meta) {
                    var e1="",e2="",e3="";
                    if(data == 'admin' ) {                           
                          e3="selected";                       
                    }else if (data == 'owner' ) {         
                        e2="selected";                        
                    }else{e1="selected";}            
                    return  `<select class="form-select-lg text-center" 
                    aria-label="Default select example" class='dt-center editor-roles'>
                        <option value="" disabled >Select role</option>
                        <option value="owner" ${e2}>Co-ordinator</option>
                        <option value="customer" ${e1}>Custmoer</option>
                        <option value="admin" ${e3}>Admin</option>
                    </select>`;                    
                }}, {
                    data: null,
                    className: '',
                    defaultContent: '<button class="btn btn-primary btn- btn-sm text-center dt-center editor-delete ">delete</button><button class="btn mt-3 btn-secondary  btn-sm text-center">edit</button>',
                    orderable: false
                },{
                    data: 'role',
                    className: 'dt-center editor-view-hotel',
                    render: function(data, type,  full, meta) {
                        var e1="",e2="",e3="";
                        if(data == 'admin' || data == 'owner' ) {                           
                          return  `<button class="btn btn-toolbar btn-Secondary btn-sm text-center">View hotels</button>`                      
                        }else{e1="selected";}            
                        return  `customer no hotels`;                    
                    }
                }],dom: 'Bfrtip'
        });// #role
        // $('#coOrdinators').on('click', 'updateUserAdmin', function (e) { });
        $("#coOrdinators").on('change','td.dt-center.editor-roles-selection select',function(){ 
            userid=$(this).closest('tr').find("td:eq(0)").text();
            memberType=$(this).val();
            if(confirm("you need to update user")){
                updateuserfun(memberType,userid)
            }
            });
        $("#coOrdinators").on('click','button.btn.mt-3.btn-secondary.btn-sm.text-center',function(){ $("#UserUpdateForm").show();sessionStorage.setItem("editingUserId",$(this).closest('tr').find("td:eq(0)").text());fillupdateform($(this).closest('tr'))});
        $("#coOrdinators").on('click','td button.dt-center.editor-delete',function(){deleteuser($(this).closest('tr').find("td:eq(0)").text(),$(this).closest('tr'))});
        $("#coOrdinators").on('click','td.dt-center.editor-view-hotel',function(){viewuserHotels(($(this).closest('tr').find("td:eq(0)").text()))});
    }
});
}
function fillupdateform(tr){
    console.log(tr);
    $("#updusername").val(tr.find("td:eq(1)").text())
    $("#upduseremail").val(tr.find("td:eq(2)").text())
    $("#updusernumber").val(tr.find("td:eq(3)").text())
    $("#Imageref").val(tr.find("td:eq(4)").text())
    $("#role").val(tr.find("td:eq(5)").text())
    console.log(tr.find("td:eq(1)").text());
    console.log(tr.find("td:eq(2)").text());
    console.log(tr.find("td:eq(3)").text());
    console.log(tr.find("td:eq(4)").text());
    console.log(tr.find("td:eq(5)").text());
}
//$("#UserUpdateForm").on("click","#updateUserAdminBtn",function(){editoruserFromsubmit()});
function editoruserFromsubmit(){
    if(!confirm("you need to update")){
        return;
    }
    var userUpdatedData={
        "AdmId":sessionStorage.getItem("userId")?sessionStorage.getItem("userId"):"1001",
        "id":sessionStorage.getItem("editingUserId"),
        "userName":$("#updusername").val(),
        "userNumber":$("#updusernumber").val(),
        "useremail":$("#upduseremail").val(),
        "role":$("#role").val(),
        "photoref":$("#Imageref").val(),
        "opertion":"modifyUser"
    }
    console.table("edit option",userUpdatedData);
    ajaxobj={
        url:'http://localhost:8080/HotelReservation/deletemember',
        type:"POST",
        data:userUpdatedData,
        datatype:"json"}
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry for issuses?Please try again",status)
            console.log("error occured during connection"+status);
        return;
        }
        if(data.status===1){
            $("#UserUpdateForm").hide();
            alert("User updated",data.datapack)
            ajaxCordinatorlist() // location.reload()
        }else if(data.status===0){
            $("#err_upduseremail").html(data.datapack);
        }
    });
}
function deleteuser(id,rem){
    if(!confirm("you need to update")){
        return;
    }
    console.log("delete option");
    ajaxobj={
        url:'http://localhost:8080/HotelReservation/deletemember',
        type:"POST",
        // data:{"AdmId":sessionStorage.getItem("userId"),"id":id,"delete":"YEs"},
        data:{"AdmId":"1003","id":id,"delete":"YEs"},
        datatype:"json"  }
        // console.log(ajaxobj);
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry for issuses?Please try again",status)
            console.log("error occured during connection"+status);
        return;
        }
        if(typeof(data)==='string'){
            data=JSON.parse(data)
        }
        if(data.status===1){
            alert("sdelete memeber",data.datapack)
            rem.remove()
        }
    });
}
function viewuserHotels(id){
    console.log("view hotels option");
    ajaxobj={
        url:'http://localhost:8080/HotelReservation/deletemember',
        type:"POST",
        data:{"userId":sessionStorage.getItem("userId"),"id":id,"viewHotel":"YES"},
        datatype:"json"  }
        commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry for issuses?Please try again",status)
            console.log("error occured during connection"+status);
        return;
        }
        if(data.status===1){
            $("#memberhotels").show()
            var htmlhotels="";
            if(data.datapack.length===0){
                htmlhotels="<b>NO hotels</b>"
            }else{
                for(let i=0;i<data.datapack.length;i++){
                    HotelList=data.datapack[i];
                    temp=`<div class="card my-2" id="${HotelList.Hotelid}">
                    <div class="card-body text-center">
                        <p class="card-text"><b> ${HotelList.HostelName}</b><br></p><p> ${HotelList.HotelAddress} <b>${HotelList.HotelNumber} ${HotelList.HotelLocation}</b></p>
                    </div>
                </div>`
                htmlhotels+=temp;
                }}
            $("#hotelsData").html(htmlhotels)
        }
    });
}
function  updateuserfun(memberType,id){
    console.log("update membership option",memberType,id);
    var adminId=sessionStorage.getItem("userId")?sessionStorage.getItem("userId"):"1001";
    ajaxobj={
        url:'http://localhost:8080/HotelReservation/deletemember',
        type:"POST",
        data:{"AdmId":adminId,"id":id,"role":memberType,"update":"admin"},
        datatype:"json"  }
    commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("sorry for issuses?Please try again",status)
            console.log("error occured during connection"+status);
        return;
        }
        if(typeof(data)==='string'){
            data=JSON.parse(data)
        }
        if(data.status===1){
            alert("update memeber",data.datapack)
        }
    });
}
$(document).ready(function() {
    
    $("#upduseremail").blur(function(){
        if($("#upduseremail").val().trim() === ""){
            $("#err_upduseremail").html("<b>&nbsp;*</b>Email is requied");
        }else{
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if($("#upduseremail").val().match(mailformat)){
                $("#err_upduseremail").html("<b>&nbsp;*</b><a> <a>");
            }else{
            $("#err_upduseremail").html("<b>&nbsp;*</b>Invalid email id is requied")
            }
        }
    });
    $("#updusername").blur(function(){
        if($("#updusername").val().trim()===""){
            $("#err_updusername").html("<b>&nbsp;*</b>User name is required .");
        }else if($("#updusername").val().trim().length<6){
            $("#err_updusername").html("<b>&nbsp;*</b>password lenght must be 6");
        }else{
            $("#err_updusername").html("<b>&nbsp;*</b><a> <a>");
        }
    });$("#updusernumber").blur(function(){
        if($("#updusernumber").val().trim() === ""){
            $("#err_updusernumber").html("<b>&nbsp;*</b>number is requied");
        }else if($("#updusernumber").val().trim().length<10){            
            $("#err_updusernumber").html("<b>&nbsp;*</b>number is less than 10 digits")        
        }else if($("#updusernumber").val().trim().length>10){            
            $("#err_updusernumber").html("<b>&nbsp;*</b>number is more than 10 digits")
        }else{
            $("#err_updusernumber").html("<b>&nbsp;*</b>")
        }        
    });

});
function updateformvalided(){
    var flage= true;
        if($("#upduseremail").val().trim() === ""){
            $("#err_upduseremail").html("<b>&nbsp;*</b>Email is requied");
            flage= false;
        }else{
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if($("#upduseremail").val().match(mailformat)){
            }else{flage= false;
            $("#err_upduseremail").html("<b>&nbsp;*</b>Invalid email id is requied")
            }
        }
        if($("#updusername").val().trim()===""){
            $("#err_updusername").html("<b>&nbsp;*</b>User name is required .");flage= false;
        }else if($("#updusername").val().trim().length<6){
            $("#err_updusername").html("<b>&nbsp;*</b>password lenght must be 6");flage= false;
        }
        if($("#updusernumber").val().trim() === ""){
            $("#err_updusernumber").html("<b>&nbsp;*</b>number is requied");flage= false;
        }else if($("#updusernumber").val().trim().length<10){            
            $("#err_updusernumber").html("<b>&nbsp;*</b>number is less than 10 digits")   ;flage= false;    
        }else if($("#updusernumber").val().trim().length>10){            
            $("#err_updusernumber").html("<b>&nbsp;*</b>number is more than 10 digits");flage= false;
        }  
        return flage;
}