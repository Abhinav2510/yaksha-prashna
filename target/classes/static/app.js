const SERVER_URL ="http://localhost:8080"
let stompClient;
function connectToYP(){
   console.log("connecting to YP over websocket...");
   let groupId= "1234";
   let socket= new SockJS(SERVER_URL+"/yakshaprashna/");
   stompClient = Stomp.over(socket);
   stompClient.connect({},function(frame){
        console.log("Connected to YP over websocket successfully! "+frame);
   });
}

function sendMessage(groupId){
    stompClient.send("/app/yakshaprashna/"+groupId,{},JSON.stringify({
        groupId: groupId,
        groupName:"Abhinav",
        fromUser:"Test1",
        eventType:"JOINED"
    }));
}

function subscribeGroup(groupId){
    stompClient.subscribe("/topic/group/"+groupId,function(response){
                console.log("Message from /topic/group/"+groupId)
                console.log(response.body)
            });
}

$(function(){
    console.log("connecting YP")
    connectToYP();
    $("#joinGroup").click(function(){
        console.log("I am joining group");
        sendMessage();
    })

    $("#createGroup").click(function(){
    console.log("Create group");

    });

});