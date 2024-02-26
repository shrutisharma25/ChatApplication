let stompClient = null;

function connect() {
    const socket = new SockJS("http://localhost:8080/server1");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log("Connected:- " + frame);
        
        $("#name-form").addClass('d-none');
        $("#chat-room").removeClass('d-none');    
        
        stompClient.subscribe("/topic/return-to", function(response){
            showMessage(JSON.parse(response.body));
        });    
        
    }, function(error) {
        console.error("Error during connection:", error);
        // Retry connection or handle error as needed
    });
}

function showMessage(message) {
    $("#message-container-table").prepend(`<tr><td><b>${message.name}:</b> ${message.content}</td></tr>`);
}

function sendMessage() {
    const messageContent = $("#message-value").val().trim();
    if (messageContent) {
        const jsonObject = {
            name: localStorage.getItem("name"),
            content: messageContent
        };
        stompClient.send("/app/message", {}, JSON.stringify(jsonObject));
        $("#message-value").val(""); // Clear the input field after sending
    }
}

$(document).ready(function() {
    $("#login").click(function() {
        const name = $("#name-value").val().trim();
        if (name) {
            localStorage.setItem("name", name);
            connect();
        }
    });
    $("#send").click(function() {
        sendMessage();
    });
});
