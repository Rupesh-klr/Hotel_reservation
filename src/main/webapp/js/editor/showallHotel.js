$(document).ready(function () {
	function ajaxHotellist() {
		ajaxobj = {
			url: "http://localhost:8080/HotelReservation/hoetelListview",
			type: "POST",
			data: {
				userId: sessionStorage.getItem("userId"),
				id: sessionStorage.getItem("userId"),
				role: sessionStorage.getItem("userRole"),
			},
			datatype: "json",
		};
		commonAjaxcall(ajaxobj, function responseCallback(data, status) {
			if (data === null) {
				alert("sorry for issuses?Please try again", status);
				console.log("error occured during connection" + status);
				return;
			}
			if (data.status === 1) {
				dataTableShow($("#HotelSearch"), data.datapack);
			}
			console.log("point common call ended");
		});
		console.log("point ended");
	}
	ajaxHotellist();
	$("#HotelUpdateBtn").click(function () {
		console.log(valideHoteleditFrom());
		if (valideHoteleditFrom()) {
			var displayMsg = "Updated hotel";
			var isEditing = sessionStorage.getItem("isEditing");
			var HotelFrom = {
				HotelName: $("#HotelName").val(),
				HotelNumber: $("#HotelNumber").val(),
				HotelLocation: $("#HotelLocation").val(),
				HotelAddress: $("#HotelAddress").val(),
				Rating: $("#HTRt").val(),
				HTimage: $("#HTImage").val(),
				HTId: sessionStorage.getItem("HTId"),
				ADId: sessionStorage.getItem("userId"),
			};
			ajaxobj = {
				url: "http://localhost:8080/HotelReservation/editHotel",
				type: "POST",
				data: HotelFrom,
				datatype: "json",
			};
			commonAjaxcall(ajaxobj, function responseCallback(data, status) {
				data = JSON.parse(data);
				if (data === null) {
					alert("Soory somthing went while handling data!");
					return;
				}
				if (data.status === 1) {
					alert(displayMsg + "\n" + data.dataPacket);
					location.reload();
				} else {
					alert("Nothing stored try again with correct process.");
				}
			});
		}
	});
});
function dataTableShow(tableselector, dataPacket) {
	tableselector.DataTable({
		processing: true,
		// "serverSide": true,
		dom: "Bfrtip",

		buttons: ["excel"],

		data: dataPacket,
		columns: [
			{ data: "id" },
			{ data: "Hotelid" },
			{ data: "HostelName" },
			{ data: "HotelNumber" },
			{ data: "HotelLocation" },
			{ data: "HotelAddress" },
			{ data: "Rating" },
			{ data: "Htimage" },
			{
				data: null,
				className: "dt-center editor-edit",
				defaultContent: '<i class="fa fa-pencil"/>',
				defaultContent: `<button type="button" class="btn btn-primary btn-sm mb-1 ">Edit</button>
          <button type="button" class="btn btn-secondary btn-sm mt-1">Delet</button>`,
				orderable: false,
			},
		],
		responsive: true,
		dom: "Bfrtip",
	});
	$("#HotelSearch").on(
		"click",
		"button.btn.btn-secondary.btn-sm.mt-1",
		function (e) {
			if (
				confirm(
					"Do you wanted to deleat HotelWith id:" +
						$(this).closest("tr").find("td:eq(1)").text()
				)
			) {
				console.log("Deleting hotel");
				ajaxobj = {
					url: "http://localhost:8080/HotelReservation/deleteByHTId",
					type: "POST",
					data: {
						HTId: $(this).closest("tr").find("td:eq(1)").text(),
						ADId: sessionStorage.getItem("userId"),
					},
					datatype: "json",
				};
				commonAjaxcall(
					ajaxobj,
					function responseCallback(data, status) {
						data = JSON.parse(data);
						if (data === null) {
							alert("Soory somthing went while handling data!");
							return;
						}
						if (data.status === 1) {
							alert("\n" + data.datapack);
							location.reload();
						} else {
							alert(
								"Nothing stored try again with correct process."
							);
						}
					}
				);
			}
		}
	);
	$("#HotelSearch").on(
		"click",
		"button.btn.btn-primary.btn-sm.mb-1",
		function (e) {
			console.log("hotel is edit");
			$("#HoteFrom").show();
			sessionStorage.setItem(
				"isEditing",
				$(this).closest("tr").find("td:eq(0)").text()
			);
			sessionStorage.setItem(
				"HTId",
				$(this).closest("tr").find("td:eq(1)").text()
			);
			$("#HotelName").val($(this).closest("tr").find("td:eq(2)").text());
			$("#HotelNumber").val(
				$(this).closest("tr").find("td:eq(3)").text()
			);
			$("#HotelLocation").val(
				$(this).closest("tr").find("td:eq(4)").text()
			);
			$("#HotelAddress").val(
				$(this).closest("tr").find("td:eq(5)").text()
			);
			$("#HTRt").val($(this).closest("tr").find("td:eq(6)").text());
			$("#HTImage").val($(this).closest("tr").find("td:eq(7)").text());
		}
	);
	$("#HoteFrom").on("click", "#HotelDiscard, #closeBtnHF", function (e) {
		$("#HoteFrom").hide();
	});
}
function valideHoteleditFrom() {
	var flage = true;
	if ($("#HotelName").val().trim() === "") {
		$("#errHtName").html("<b>&nbsp;*</b>Required .");
		flage = false;
	} else if ($("#HotelName").val().trim().length < 6) {
		$("#errHtName").html("<b>&nbsp;*</b>password lenght must be 6");
		flage = false;
	}
	if ($("#HotelName").val().trim() === "") {
		$("#errHtLocation").html("<b>&nbsp;*</b>Required .");
		flage = false;
	}
	if ($("#HotelNum").val() === "") {
		$("#errHtNumber").html("<b>&nbsp;*</b>Requied");
		flage = false;
		flage = false;
	} else if (
		parseInt($("#HotelNum").val()) < 1000000000 ||
		parseInt($("#HotelNum").val()) > 9999999999
	) {
		$("#errHtNumber").html("<b>&nbsp;*</b>Not a phone Number");
		flage = false;
	}
	if (
		$("#HotelAddress").val().trim() === "" &&
		$("#HotelAddress").val().trim().length > 10
	) {
		$("#errHtAdd").html("<b>&nbsp;*</b>Requied");
		flage = false;
	} else if ($("#HotelAddress").val().trim().length < 10) {
		$("#errHtAdd").html("<b>&nbsp;*</b>Valide Address.");
		flage = false;
	}
	if ($("#HTRt").val() === "") {
		$("#errHTRt").html("<b>&nbsp;*</b>Requied");
		flage = false;
	} else if (
		parseInt($("#HTRt").val()) < 0 ||
		parseInt($("#HTRt").val()) > 5
	) {
		flage = false;
		$("#errHTRt").html("<b>&nbsp;*</b>give rating with in 5");
	}
	return flage;
}
