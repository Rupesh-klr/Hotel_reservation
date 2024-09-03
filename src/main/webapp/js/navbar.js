const userNavbar = `
<div class="">
  <nav class="navbar navbar-expand-md navbar-dark bg-dark px-sm-0 px-md-5 rounded justify-content-md-between">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#userNavbar" aria-controls="userNavbar" aria-expanded="true" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse " id="userNavbar">
      <ul class="navbar-nav ">
        <li class="nav-item ">
          <a class="nav-link" href="./home.html">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./profile.html">Profile</a>
        </li>
          <li class="nav-item">  <!--disabled -->
          <a class="nav-link " href="./Mybooking.html">Bookings</a>
        </li>            
      </ul>
    </div>
    <div class="form-row" id="valueFiled" style="display: none;">
						<div class="form-group col-md-6 m-0">	
							<lable id="min">MiN</lable>				
							<input class="form-control m-0" placeholder="0" type="number" id="valuemin">
						</div>
						<div class="form-group text-right col-md-6 m-0">
							<lable id="max">MiN</lable>						
							<input class="form-control m-0" type="number" id="valuemax">
						</div>
				</div>
				<input type="text" id="searchtext" class="form-control" placeholder="What search?" style="display: none;">
				<select class="form-control form-control-lg rounded-0" id="optionsearch" style="display: none;">					<option value="location" >Location</option>
					<option value="name">Hotel name</option>
					<option value="address">Address</option>
					<option value="rating">rating</option>
					<option value="price" selected="">price</option>
				</select>
				<div class="input-group-append ">
					<button class="btn btn-outline-secondary m-0" id="searchBtn" type="button"  style="display: none;"><i class="fa-solid fa-magnifying-glass"></i></button>
				</div>
      <div class=" collapse  justify-content-end mr-3 " id="loginnav" >
        <button  type="button" class="btn  btn-outline-danger rounded-0  ms-md-2"  id="logedoutBtnfocused">Logout</button>
        </div> 
        <!-- navbar-collapse -->
      <div class=" collapse  justify-content-end mr-3" id="logoutnav"  >
        <button  type="button" class="btn btn-outline-success rounded-0 ms-5 mr-md-2" id="logBtnfocused">log in/SigUp</button>
        </div> 
        </nav>
        </div><!-- user navbar -->  `;
// <button  type="button" class="btn btn-info btn-outline-success  rounded-0  ms-md-2">Sig up</button>
const coOrdinatorNavbar = `
<div class="">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-sm-0 px-md-5 rounded">
    <!-- <a class="navbar-brand" href="https://getbootstrap.com/docs/4.0/examples/navbars/#">Expand at sm</a> -->
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#ownerNavbar" aria-controls="ownerNavbar" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
          <!-- collapse show -->
    <div class="collapse navbar-collapse justify-content-md-start" id="ownerNavbar">
      <ul class="navbar-nav ">
        <li class="nav-item ">
          <a class="nav-link" href="./home.html">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./profile.html">Profile</a>
        </li>
          <li class="nav-item">  <!--disabled -->
          <a class="nav-link " href="./Mybooking.html">Bookings</a>
        </li>
        </li>
          <li class="nav-item">  <!--disabled -->
          <a class="nav-link " href="./hotelRequests.html">Hotels Requests</a>
        </li>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle"  id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Update Hotels/Rooms</a>
          <div class="dropdown-menu " aria-labelledby="dropdown03">
            <a class="dropdown-item" href="./showallHotel.html">Hotels</a>
            <a class="dropdown-item" href="./showallRoomByHotel.html">Rooms</a>
            <a class="dropdown-item" href="./addhotel.html">add Hotel </a>
            <a class="dropdown-item" href="./addRoom.html">add Rooms </a>
          </div>
        </li>
      </ul>
    </div>
    
    <div class=" collapse  justify-content-end mr-3 " id="loginnav" >
      <button  type="button" class="btn  btn-outline-danger rounded-0  ms-md-2" id="logedoutBtnfocused">Logout</button>
      </div> 
      <!-- navbar-collapse -->
    <div class=" collapse  justify-content-end mr-3" id="logoutnav"  >
      <button  type="button" class="btn btn-outline-success rounded-0 ms-5 mr-md-2" id="logBtnfocused">log in</button>
      </div>      
  </nav>
</div><!-- owner navbar --> `;
const adminNavbar = `
<div class=" ">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-sm-0 px-md-5 rounded">
    <!-- <a class="navbar-brand" href="https://getbootstrap.com/docs/4.0/examples/navbars/#">Expand at sm</a> -->
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#adminNavbar" aria-controls="adminNavbar" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
          <!-- collapse show -->
    <div class="collapse navbar-collapse justify-content-md-start" id="adminNavbar">
      <ul class="navbar-nav ">
        <li class="nav-item ">
          <a class="nav-link" href="./home.html">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./showallcordinates.html">Co Ordinators</a>
        </li>
          <li class="nav-item">  <!--disabled -->
          <a class="nav-link " href="./approvalrequest.html">Approvals</a>
        </li>
        </li>
          <li class="nav-item">  <!--disabled -->
          <a class="nav-link " href="./hotelList.html">Hotels lists</a>
        </li>
        </li>
          
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle"  id="adminNavbar" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">ADD Hotels/Rooms</a>
          <div class="dropdown-menu" aria-labelledby="dropdown03">
            <a class="dropdown-item" href="./showallHotel.html">Hotels</a>
            <a class="dropdown-item" href="./showallRoomByHotel.html">Rooms</a>
            <a class="dropdown-item" href="./addhotel.html">add Hotel </a>
            <a class="dropdown-item" href="./addRoom.html">add Rooms </a>
            <a class="dropdown-item" href="./Mybooking.html">My Booking</a>
            
          </div>
        </li>
        <li class="nav-item">  <!--disabled -->
          <a class="nav-link " href="profile.html">Profile</a>
        </li>
      </ul>
    </div>
   
    <div class=" collapse  justify-content-end mr-3 " id="loginnav" >
      <button  type="button" class="btn  btn-outline-danger rounded-0  ms-md-2"  id="logedoutBtnfocused">Logout</button>
      </div> 
      <!-- navbar-collapse -->
    <div class=" collapse  justify-content-end mr-3" id="logoutnav"  >
      <button  type="button" class="btn btn-outline-success rounded-0 ms-5 mr-md-2" id="logBtnfocused">log in</button>
      </div>       
  </nav>
</div><!-- admin navbar --> `;
var isUserLogedin = "";
var isUserType = "";
$(document).ready(function () {
	$("#navTab").on("click", "a", function () {
		console.log($(this)[0].attributes[1].baseURI);
		console.log(
			$(this)[0].attributes[1].textContent,
			$(this)[0].attributes[1].value
		);
	});
});
function commonValidingFunction() {
	isUserType = "";
	if (
		sessionStorage.getItem("logedIn") == null &&
		sessionStorage.getItem("logedIn") !== "YES"
	) {
		console.log("not loged into network");
		isUserLogedin = "No";
    console.log("log in checking");
    redirectnoneuser();
		// if(confirm())
		// location.href="home.html"
		// }else if(localStorage.getItem("logedIn")==null && localStorage.getItem("logedIn")!=="YES"){
		//   sessionStorage.setItem("user",JSON.stringify(msg.datapack));
		//   sessionStorage.setItem("userId",msg.datapack.id);
	} else {
		isUserLogedin = "YES";
	}
	if (sessionStorage.getItem("userRole") !== null) {
		console.log("not loged into network");
		isUserType = sessionStorage.getItem("userRole");
	} else {
    // redirectnoneuser();
		isUserType = "user";
	}
	return [isUserType, isUserLogedin];
}
function redirectnoneuser(){
  try {
    console.log(activepage);console.log("in log Or home page.");
  } catch (error) {
    if(confirm("You need to login to access.\nCan you login")){
      location.href="login.html"
    }else{
      location.href="home.html"
    }
  }
}
function navbarBegin(kp) {
	var kp = commonValidingFunction();
	const userType = kp[0];
	const userlogedin = kp[1];
	if (userlogedin === "YES") {
		if (userType === "admin") {
			$("#navTab").html(adminNavbar);
			console.log("admin login");
		} else if (userType === "coOrdinator" || userType === "owner") {
			console.log("owner loged in");
			$("#navTab").html(coOrdinatorNavbar);
		} else {
			console.log("User");
			$("#navTab").html(userNavbar);
		}
		$("#loginnav").show();
		$("#logoutnav").hide();
	} else {
		
		//location.href="home.html";}
		// location.href="home.html";
		console.log("need login for any thing user");
		$("#navTab").html(userNavbar);
		$("#logoutnav").show();
		$("#loginnav").hide();
	}
}
$(document).ready(function () {
	navbarBegin();
	$("body").on("click", "#logBtnfocused", function (e) {
		window.location = "login.html#";
	});
	$("body").on("click", "#logedoutBtnfocused", function (e) {
		console.log("clicked logeout");
		sessionStorage.removeItem("user");
		sessionStorage.removeItem("logedIn");
		sessionStorage.removeItem("userRole");
		sessionStorage.removeItem("userId");
		window.location = "home.html";
	});
});
/*
ajaxobj={
  url:"",
  type:"POST",
  data:"",
  datatype:'json'
}
commonAjaxcall(ajaxobj,function responseCallback(data,status){
  if(data===null){
    
    return;
  }

});
*/
function commonAjaxcall(obj, callback) {
	console.log(obj);
	try {
		$.ajax({
			type: obj.type,
			url: obj.url,
			data: obj.data,
			dataType: obj.datatpe,
			success: function (msg) {
				// (response, textStatus, jqXHR) {
				console.log(msg);
				callback(msg, { sss: "--" });
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.table(jqXHR);
				console.table(textStatus);
				console.table(errorThrown);
				callback(null, "error from server");
			},
		});
	} catch (error) {
		console.table(error);
		callback(null, "error in ajaxcall calling");
	}
}
