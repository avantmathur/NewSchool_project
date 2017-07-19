/**
 * 
 */
window.onload = function(){
	getStudentInfo();
}

function getStudentInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = JSON.parse(xhr.responseText);
			document.getElementById('username').innerHTML = data.userUsername;
			document.getElementById('password').innerHTML = data.userPassword;
			document.getElementById('name').innerHTML = data.userFirstName + " " + data.userLastName;
			document.getElementById('email').innerHTML = data.userEmail;
		}
	}
	xhr.open("GET", "student", true);
	xhr.send();
}

