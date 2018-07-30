app.controller("indexController",function($scope,indexService){
	
	$scope.showUsername = function(){
		indexService.showUsername().success(function(response){
			$scope.username = response.username;
		})
	}
	
})