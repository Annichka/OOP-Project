

function requestFunc() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("content").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "FriendRequests", true)
	xhttp.send()
	}

function searchFunc() {
    var name = document.getElementById("mySearch").value;
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("content").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "Search?searching=" + name, true)
	xhttp.send(name)  
}

function friendFunc() {
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("content").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "FriendList", true)
	xhttp.send()  
}

function noteFunc() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("content").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "Notes", true)
	xhttp.send() 
}

function sendNote() {
    var person = window.prompt("Sending to..");
    var note = window.prompt("Note");
    if (person != null && note != null) {
    	var xhttp = new XMLHttpRequest();
    	xhttp.onreadystatechange = function() {
    		if (xhttp.readyState == 4 && xhttp.status == 200) {
    			document.getElementById("content").innerHTML = xhttp.responseText;
    		}
    	};
    	xhttp.open("POST", "CreateNote?user="+person + "&note=" + note, true)
    	xhttp.send() 
    }
}

