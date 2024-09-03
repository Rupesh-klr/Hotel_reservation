var activepage = "home";

$(document).ready(function () {
    $("#valueFiled").show();
    $("#optionsearch").show();
    $("#searchBtn").show();
	ajaxobj = {
		url: "http://localhost:8080/HotelReservation/selectTopHotels",
		type: "POST",
		datatype: "json",
		data: { request: "data wanted to display" },
	};
	commonAjaxcall(ajaxobj, function responseCallback(data, msg) {
		if (data === null) {
			console.log("error" + msg);
			return;
		}
		if (data.status === 0 || data.status === 1) {
			console.log("hellloi   ");
			datahoteltolist(data.datapack.topRated, $("#toprated"));
			datahoteltolist(data.datapack.topDeal, $("#bestdeals"));
			datahoteltolist(data.datapack.topL, $("#hotelList"));
			datahoteltolist(data.datapack.Topclass, $("#topClass"));
		} 
	});
	$("div").on("click", ".roomForwardingBTn", function (e) {
		console.log("---->>>//");
		console.log($(this).attr("id"));
		sessionStorage.setItem("HotelSelectIdIs", $(this).attr("id"));
		sessionStorage.setItem("HotelDetails", $(this).attr("data-field"));
		location.href = "hotelRooms.html";
	});
});
function datahoteltolist(dataList, st) {
	if (dataList === null) {
		st.append("<h1>no data to show</h1>");
	}
	var datat = `<section id="team" class="pb-5"><div class="container"><div class="row">`;
	i = 0;
	var value = "";
	dataList.forEach((item) => {
		value = JSON.stringify(item);
		//console.log(i++ ,item.HostelName)
		//console.log(value);
		if (item.Htimage === "NTH") {
			item.Htimage = "hotel1Roundcurvl.jpg";
		}
		datat += `
  <div class="col-6 col-sm-5 col-md-4 col-lg-3" data-field="hello">
      <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
          <div class="mainflip">
              <div class="frontside">
                  <div class="card">
                      <div class="card-body text-center">
                          <p><img class=" img-fluid" src="../assets/img/hotels/${
								item.Htimage
							}" alt="${item.Htimage}"></p>
                          <h4 class="card-title">${item.HostelName.toUpperCase()}</h4>
                          <p class="card-text"> HotelLocation${
								item.HotelLocation
							}<br></p><p> conactus me:${item.HotelNumber}</p>
                      </div>
                  </div>
              </div>
              <div class="backside">
                  <div class="card">
                      <div class="card-body text-center mt-4">
                          <h4 class="card-title">${item.HostelName.toUpperCase()}</h4>
                          <p class="card-text"> 
                              Hotel Location :${item.HotelLocation}<br>
                              HotelAddress<br>${item.HotelAddress}<br>
                              Hotel Reivews :${item.HotelDesc}<br>
                              Rating : ${item.Rating}<br>
                              Prices as ${
									item.StaringPr
								} &nbsp;&nbsp;-&nbsp;&nbsp; ${item.EndPr}<br>
                              conactus me:${item.HotelNumber}
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
                          <button id="${
								item.Hotelid
							}" type="button" class="btn  border-0 btn-link mx-5 roomForwardingBTn" data-field='${value}'>Check rooms</button>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  </div>`;
	});
	datat += `</div></div></section>`;
	//console.log(st)
	st.append(datat);
}
$(document).ready(function () {
	$("#optionsearch").change(function () {
		if (
			$("#optionsearch").val() === "location" ||
			$("#optionsearch").val() === "name" ||
			$("#optionsearch").val() === "address"
		) {
			$("#searchtext").show();
			$("#valueFiled").hide();
		} else {
			$("#searchtext").hide();
			$("#valueFiled").show();
		}
	});
	$("#searchBtn").click(function (e) {
        var qry = "";
        var obj={}
		if (
			$("#optionsearch").val() === "location" ||
			$("#optionsearch").val() === "name" ||
			$("#optionsearch").val() === "address"
		) {
			var item = $("#searchtext").val();
			if (item.trim() === "") {
				alert("search field can't empty");
			}
			switch ($("#optionsearch").val()) {
				case "location":
					qry = "HotelLocation LIKE "//'%" + item + "%'";
					break;
				case "name":
					qry = "HostelName LIKE "//'%" + item + "%'";
					break;
				case "address":
					qry = "HotelAddress LIKE "//'%" + item + "%'";
					break;
			}
            obj={
                "typesSearch":"query",
                "SerachElement":qry,
                "value":item
            }
		} else {
			var st = $("#valuemin").val();
			var end = $("#valuemax").val();
			if (st === "" && end === "") {
				alert(
					"search field's can't empty.atleat one field is required."
				);
				return;
			}
			
			switch ($("#optionsearch").val()) {
				case "rating":
					if (st !== "" && end !== "") {
						qry = "Rating>" + st + " and Rating<" + end;
					} else if (st !== "") {
						qry = "Rating>" + st;
					} else {
						qry = " Rating<" + end;
					}
					break;
				case "price":
					if (st !== "" && end !== "") {
						qry = "StaringPr>" + st + " and EndPr<" + end;
					} else if (st !== "") {
						qry = "StaringPr>" + st;
					} else {
						qry = " EndPr<" + end;
					}
					break;
			}
            obj={
                "typesSearch":"query1",
                "SerachElement":qry
            }
		}
        //url=http://localhost:8080/HotelReservation/selectHotelSerach
       Serachajaxcall(obj)
        // console.log(qry,obj);
	});
});
function Serachajaxcall(obj){
    console.log(obj);
    ajaxobj={
        url:"http://localhost:8080/HotelReservation/selectHotelSerach",
        type:"POST",
        data:obj,
        datatype:'json'
      }
      commonAjaxcall(ajaxobj,function responseCallback(data,status){
        if(data===null){
            alert("unable to search data")          
          return;
        }
        if(typeof(data)==="string"){
            data=JSON.parse(data)
        }//serachrequest
        $("#toprated,#bestdeals,#hotelList,#topClass").hide();
        $("#serachrequest").show();
        $("#serachrequest").html(`<h1>Search results <span class="warn text-danger fw-bolder" onclick='$("#toprated,#bestdeals,#hotelList,#topClass").show();$("#serachrequest").hide();'>&times;</span> </h1>`)
        datahoteltolist(data.datapack, $("#serachrequest"))
      });

}