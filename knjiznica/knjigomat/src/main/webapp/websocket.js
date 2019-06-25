let wsocket; // Websocket connection
let userName; // User's name
let textarea; // Chat area
let wsconsole; // Websocket console area
let userlist; // User list area
/*
 * Connect to the Websocket endpoint Set a callback for incoming messages
 */
function connect() {
	console.log("connecting");
	textarea = document.getElementById("textarea");
	wsconsole = document.getElementById("wsconsole");
	userlist = document.getElementById("userlist");

	const host = document.location.host;
	const pathname = document.location.pathname;

	// wsocket = new WebSocket("ws://" +host + pathname + "websocketbot");

	wsocket = new WebSocket("ws://localhost:8080//webapp/websocketbot");
	wsocket.onmessage = onMessage;
	document.getElementById("name").focus();
	document.getElementById("consolediv").style.visibility = 'hidden';
}
/*
 * Callback function for incoming messages evt.data contains the message Update
 * the chat area, user's area and Websocket console
 */
function onMessage(evt) {
	let line = "";
	/* Parse the message into a JavaScript object */
	const msg = JSON.parse(evt.data);
	if (msg.type === "chat") {
		line = msg.name + ": ";
		if (msg.target.length > 0)
			line += "@" + msg.target + " ";
		line += msg.message + "\n";
		/* Update the chat area */
		textarea.value += "" + line;
	} else if (msg.type === "info") {
		line = "[--" + msg.info + "--]\n";
		/* Update the chat area */
		textarea.value += "" + line;
	} else if (msg.type === "users") {
		line = "Uporabniki:\n";
		for (let i = 0; i < msg.userlist.length; i++)
			line += "-" + msg.userlist[i] + "\n";
		/* Update the user list area */
		userlist.value = line;
	}
	textarea.scrollTop = 999999;
	/* Update the Websocket console area */
	wsconsole.value += "-> " + evt.data + "\n";
	wsconsole.scrollTop = 999999;
}
/* Send a join message to the server */
function sendJoin() {
	const input = document.getElementById("input");
	const name = document.getElementById("name");
	const join = document.getElementById("join");
	let jsonstr;
	if (name.value.length > 0) {
		/* Create a message as a JavaScript object */
		let joinMsg = {};
		joinMsg.type = "join";
		joinMsg.name = name.value;
		/* Convert the message to JSON */
		jsonstr = JSON.stringify(joinMsg);
		/* Send the JSON text */
		wsocket.send(jsonstr);
		/* Disable join controls */
		name.disabled = true;
		join.disabled = true;
		input.disabled = false;
		userName = name.value;
		/* Update the Websocket console area */
		wsconsole.value += "<- " + jsonstr + "\n";
		wsconsole.scrollTop = 999999;
	}
}
/* Send a chat message to the server (press ENTER on the input area) */
function sendMessage(evt) {
	const input = document.getElementById("input");
	let jsonstr;
	let msgstr;
	if (evt.keyCode === 13 && input.value.length > 0) {
		/* Create a chat message as a JavaScript object */
		let chatMsg = {};
		chatMsg.type = "chat";
		chatMsg.name = userName;
		msgstr = input.value;
		chatMsg.target = getTarget(msgstr.replace(/,/g, ""));
		chatMsg.message = cleanTarget(msgstr);
		chatMsg.message = chatMsg.message.replace(/(\r\n|\n|\r)/gm, "");
		/* Convert the object to JSON */
		jsonstr = JSON.stringify(chatMsg);
		/* Send the message as JSON text */
		wsocket.send(jsonstr);
		input.value = "";
		/* Update the Websocket console area */
		wsconsole.value += "<- " + jsonstr + "\n";
		wsconsole.scrollTop = 999999;
	}
}
/* Send a join message if the user presses ENTER in the name area */
function checkJoin(evt) {
	const name = document.getElementById("name");
	const input = document.getElementById("input");
	if (evt.keyCode === 13 && name.value.length > 0) {
		sendJoin();
		input.focus();
	}
}
/* Get the @User (target) for a message */
function getTarget(str) {
	let arr = str.split(" ");
	let target = "";
	for (let i = 0; i < arr.length; i++) {
		if (arr[i].charAt(0) === '@') {
			target = arr[i].substring(1, arr[i].length);
			target = target.replace(/(\r\n|\n|\r)/gm, "");
		}
	}
	return target;
}
/* Remove the @User (target) from a message */
function cleanTarget(str) {
	let arr = str.split(" ");
	let cleanstr = "";
	for (let i = 0; i < arr.length; i++) {
		if (arr[i].charAt(0) !== '@')
			cleanstr += arr[i] + " ";
	}
	return cleanstr.substring(0, cleanstr.length - 1);
}
/* Show or hide the WebSocket console */
function showHideConsole() {
	let chkbox = document.getElementById("showhideconsole");
	let consolediv = document.getElementById("consolediv");
	if (chkbox.checked)
		consolediv.style.visibility = 'visible';
	else
		consolediv.style.visibility = 'hidden';
}
/* Call connect() when the page first loads */
window.addEventListener("load", connect, false);