app.controller("baseController",function($scope){
	
	 $scope.paginationConf = {
			 currentPage: 1,  //当前页 数据由angularjs控制
			 totalItems: 10,  //总数  从后台来
			 itemsPerPage: 10, //每页显示的最大条数  数据由angularjs控制
			 perPageOptions: [10, 20, 30, 40, 50],
			 onChange: function(){    //此方法会在初始化页面时就触发
			   $scope.reloadList();//重新加载   
			 }
	};
	 $scope.reloadList=function(){
		 var page = $scope.paginationConf.currentPage;
		 var pageSize = $scope.paginationConf.itemsPerPage;
// 		 分页查询 需要两个参数 当前页码   每页显示的条数
		 $scope.search(page,pageSize,$scope.searchEntity);
// 		 此方法返回 1、总条数 long  2、当前页的数据list
	 }
	 $scope.searchEntity = {};
	
	 
	$scope.selectIds=[];
	
	$scope.updateSelectIds =function ($event,id){
// 		判断复选框是否为勾选状态
//		$event.target 当前对象：复选框
		if($event.target.checked){
			$scope.selectIds.push(id); //往数组中放数据
// 			alert($scope.selectIds);
		}else{
// 			 代表取消选择 把数据从数组中移除
// 			$scope.selectIds.splice(当前值的索引,数量);
			var index = $scope.selectIds.indexOf(id);
			$scope.selectIds.splice(index,1);
// 			alert($scope.selectIds);
		}
	}
	
})