

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
    		document.getElementById("questions").innerHTML = "";
   		}
   	};
   	xhttp.open("GET", "QuestionTypes", true)
   	xhttp.send() 
}

function addQuestion(btn) {
	var cansw = "0";
	var wansw = "0";
	var ordered = "0";
	if(btn.name === "MC") {
		while(true) {
			wansw = window.prompt("Wrong answer count");
			if(isNaN(wansw) === false)
				break;
		}
	} else if(btn.name === "MCA") {
		while(true) {
			cansw = window.prompt("Correct answer count");
			if(isNaN(cansw) === false)
				break;
		}
		while (true) {
			wansw = window.prompt("Wrong answer count");
			if(isNaN(wansw) === false)
				break;
		}
	} else if(btn.name === "MA") {
		while(true) {
			cansw = window.prompt("Correct answer count");
			if(isNaN(cansw) === false)
				break;
		}
		while(true) {
			ordered = window.prompt("If answers are ORDERED - write 1 otherwise - 0");
			if(ordered === "1" || ordered === "0")
				break;
		}
	}
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		var qst = document.getElementById("content")
    		qst.innerHTML = xhttp.responseText;
   		}
    };
   	xhttp.open("GET", "QuestionForm?type=" + btn.name + "&cansc=" + cansw + "&wansc=" + wansw + "&ord=" + ordered, true)
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

function categoryQuizes(obj) {
	var cat = obj.name
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		var qst = document.getElementById("content")
    		qst.innerHTML = xhttp.responseText;
   		}
    };
    
   	xhttp.open("GET", "GetByCategory?cat=" +cat, true)
   	xhttp.send()
}

function friendList(obj) {
	var user = obj.name
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		var qst = document.getElementById("content")
    		qst.innerHTML = xhttp.responseText;
   		}
    };
    
   	xhttp.open("GET", "UsersFriends?profile=" + user, true)
   	xhttp.send()
}


function showQuiz(obj) {
	var quizid = obj.name
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    		var qst = document.getElementById("content")
    		qst.innerHTML = xhttp.responseText;
   		}
    };
    
   	xhttp.open("GET", "StartQuiz?quizid=" + quizid, true)
   	xhttp.send()
}


function challengeUser() {
    var person = window.prompt("Challenge..");
    var quizid = obj.name;
    if (person != null && note != null) {
    	var xhttp = new XMLHttpRequest();
    	xhttp.onreadystatechange = function() {
    		if (xhttp.readyState == 4 && xhttp.status == 200) {
    			document.getElementById("content").innerHTML = xhttp.responseText;
    		}
    	};
    	xhttp.open("POST", "Challenge?user="+person + "&quizid=" + quizid, true)
    	xhttp.send() 
    }
}