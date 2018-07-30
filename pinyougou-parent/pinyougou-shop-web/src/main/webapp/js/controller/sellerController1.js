app.controller("sellerController1",function($scope,sellerService){
	
	$scope.entity = {};
	
	$scope.save=function(){
		
		sellerService.save($scope.entity).success(function(response){
//			response：{success:true,message:'成功'}
			if(response.success){
				alert("申请成功，24小时内会有审核结果");
				location.href="shoplogin.html";
			}else{
				alert("申请失败");
			}
		})
	}
	
	
})