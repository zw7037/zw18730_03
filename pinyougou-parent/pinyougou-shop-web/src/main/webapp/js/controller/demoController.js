app.controller("demoController",function($scope){
	
	$scope.save=function(){
//		var 字符串 = JSON.stringify(对象);
//		var 对象 = JSON.parse(字符串);
		alert(JSON.stringify($scope.entity));
//		$scope.entity2=$scope.entity; //浅克隆
		
		$scope.entity2=JSON.parse(JSON.stringify($scope.entity));  //深克隆
		
		$scope.entity2.username="AAAAAAAAAAAA";
	}
	
	
})