

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

function questionTypes() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		document.getElementById("content").innerHTML = xhttp.responseText;
   		}
   	};
   	xhttp.open("GET", "QuestionTypes", true)
   	xhttp.send() 
}

function addQuestion(btn) {
	var wansw = "0";
	var ordered = "0";
	if(btn.name === "MC") {
		wansw = window.prompt("Wrong answer count");
	} else if(btn.name === "MCA") {
		wansw = window.prompt("Correct answer count");
	}
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		var qst = document.getElementById("content")
    		qst.innerHTML = xhttp.responseText;
   		}
    };
   	xhttp.open("GET", "QuestionForm?type=" + btn.name + "&ansc=" + wansw + "&ord=" + ordered, true)
   	xhttp.send()
}

function displayQuestions() {
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		var qst = document.getElementById("questions")
    		qst.innerHTML = xhttp.responseText;
   		}
    };
   	xhttp.open("GET", "DisplayQuestions", true)
   	xhttp.send()
}

function editQuestion(obj) {
	var questid = obj.id
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		var qst = document.getElementById("questions")
    		qst.innerHTML = xhttp.responseText;
   		}
    };
    
   	xhttp.open("GET", "DisplayQuestions?questid=" +questid, true)
   	// unda gadaikvanos im gverdze sadac question ketdeba, + show whole quiz button
   	xhttp.send()
}
