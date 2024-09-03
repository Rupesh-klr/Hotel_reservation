var removeRowselecter=null
$(document).ready(function() {
    function ajaxCordinatorlist(){
        console.log("point1");
            ajaxobj={
            url:'http://localhost:8080/HotelReservation/newApprovalRequestAdmin',
            type:"POST",
            data:{"userId":sessionStorage.getItem("userId")},
            datatype:"json"  }
        commonAjaxcall(ajaxobj,function responseCallback(data,status){
            if(data===null){
                alert("sorry for issuses?Please try again",status)
                console.log("error occured during connection"+status);
            return;
            }
            console.log(data);
            if(data.status===1 || data.status===0){
                $('#approvalRequest').DataTable({
                     ajax: 'http://localhost:8080/HotelReservation/newApprovalRequestAdmin',
                    // buttons: [
                    //     {
                    //         text: 'Create new record',
                    //         action: function ()  {
                    //             // Create new record
                    //             editor.create({
                    //                 title: 'Create new record',
                    //                 buttons: 'Add'
                    //             });
                    //         }
                    //     }],
                    data: data.datapack,
                    columns: [
                        { data: 'id' },
                        { data: 'ApprovalId' },
                        { data: 'ProofType' },
                        { data: 'Proof' },
                        { data: 'AttachmentMessage' },
                        {
                            data: null,
                            className: 'dt-center updateStatus',
                            defaultContent: '<button class="btn btn-toolbar btn-secondary btn-sm ">Update</button>',
                            orderable: false
                        }
                    ],
                    dom: 'Bfrtip'
                });
            }
            console.log("point common call ended");
        });
        console.log("point ended");
    }
    console.log("-->");
    ajaxCordinatorlist()
    // #approvalRequest > tbody > tr:nth-child(7) > td.dt-center.updateStatus > button
    $("#approvalRequest").on('click','button.btn.btn-toolbar.btn-secondary.btn-sm',function(){
        console.log($(this).closest('tr').find("td:eq(0)").text());
        sessionStorage.setItem("ApprovalId",$(this).closest('tr').find("td:eq(1)").text())
        sessionStorage.setItem("Member",$(this).closest('tr').find("td:eq(0)").text())
        console.log($(this).closest('tr'));
        removeRowselecter=$(this).closest('tr')
        sessionStorage.setItem("rowfromdatatablestr",JSON.stringify($(this).closest('tr')))
        $("#approvalupdate").show()
    });
    $("#approvalupdate").on('click','#responseDiscard, #closeBtnHF',function(){ $("#approvalupdate").hide();});
    $("#selectapprovalMenu").change(function (e) {
        console.log($(this).val()) 
        switch ($(this).val()) {
            case "-1":
                $("#repMsg").val("your message not find related correct prrof!");
                break;
            case "0":
                $("#repMsg").val("try with some more related document is nesscary for approval!");
                break;
            case "2":
                $("#repMsg").val("Congulation from now on word your a member!.");
                break;
            case "3":
                $("#repMsg").val("Congulation from now on word your a member!.");
                break;
            default:
                break;
        }
    });
    $("#approvalupdate").on('click','#responseBtn',function(){ 
        if(!confirm("you need to update")){
            return;
        }
        ajaxobj={
            url:'http://localhost:8080/HotelReservation/changeRequestedApproval',
            type:"POST",
            data:{"id":sessionStorage.getItem("userId"),
                "userId":sessionStorage.getItem("Member"),
                "ApprovalId":sessionStorage.getItem("ApprovalId"),
                "closedMsg": $("#repMsg").val(),
                "requestStatus":$("#selectapprovalMenu").val()},
            datatype:"json" }
            commonAjaxcall(ajaxobj,function responseCallback(data,status){
            if(data===null){
                alert("sorry for issuses?Please try again",status)
                console.log("error occured during connection"+status);
            return;
            }
            console.log(data);
            if(data.status===1 || data.status===0){
               alert("Updated changes succsfully!.");
               removeRowselecter.remove()
               removeRowselecter=null;
            }
            console.log("point common call ended");$("#approvalupdate").hide();
        });
    });
});