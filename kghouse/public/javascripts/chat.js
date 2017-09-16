var app=angular.module('chatApp',['ngMaterial']);
app.controller('chatController',function($scope){

$scope.messages = [
    {
       sender:"BOT",
       text: "WHAT CAN I DO FOR U??",
       time:"1:15"
    },
    {
       sender:"user",
       text: "what is 1+1",
       time:"1:15"
    },
    {
       sender:"BOT",
       text: "Hi 2",
       time:"1:15"
    }];
var  exampleSocket =  new  WebSocket("ws://localhost:9000/chatSocket");
exampleSocket.onmessage  =   function  (event) {
       var jsonData = JSON.parse(event.data);
       console.log(jsonData);
   };
});