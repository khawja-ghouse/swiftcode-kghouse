var app = angular.module('chatApp',[])
.controller('chatController', function($scope, $http){

$scope.checkLogin = function (){
   console.log("checkLogin");
   $.ajax({
       url: "/login",
       method: "POST",
       data: {
           "username": $scope.username,
           "password": $scope.password
       },
       success: function(data){
           console.log(data + "asas");
           window.location.href = '/';
       },
       error: function(data){
           console.log(data);
           alert("Wrong username or password");
       }
   });
}
});