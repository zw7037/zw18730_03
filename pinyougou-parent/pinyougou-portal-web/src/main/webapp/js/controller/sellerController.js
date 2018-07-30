     app.controller("sellerController",function($scope,$controller,sellerService){
    	 
//    	 作用：就是项目sellerController和baseController共用同一个$scope
    	 $controller('baseController',{$scope:$scope});//继承 
    	 
    	 //修改商家状态
    	 $scope.updateStatus=function(sellerId,status){
    		 sellerService.updateStatus(sellerId,status).success(function(response){
    			 if(response.success){
    				 $scope.reloadList();
    			 }else{
    				 alert(response.message);
    			 }
    		 })
    		 
    	 }
    	 
    	 $scope.reloadList=function(){
    		 var page = $scope.paginationConf.currentPage;
    		 var pageSize = $scope.paginationConf.itemsPerPage;
//     		 分页查询 需要两个参数 当前页码   每页显示的条数
    		 $scope.search(page,pageSize,$scope.searchEntity);
//     		 此方法返回 1、总条数 long  2、当前页的数据list
    	 }
    	 
//     	 分页查询
    	 $scope.findPage=function(page,pageSize){
    		 sellerService.findByPage().success(function(response){
     			 $scope.list = response.rows;
     			 $scope.paginationConf.totalItems = response.total;
     		 })
    	 }
    	 
//     	 查询所有品牌数据
    	 $scope.findAll = function(){
    		 sellerService.findAll().success(function(response){
    			 $scope.list = response;
    		 })
    	 }
    	 
//     	 保存方法  新增   修改
 		$scope.save = function(){
			var resultObject;
			if($scope.entity.id!=null){
				resultObject = sellerService.update($scope.entity);
			}else{
				resultObject = sellerService.add($scope.entity);
			}
			
			resultObject.success(function(response){
// 				response={success:true|false,message:"保存成功"|"保存失败"}
				if(response.success){
					$scope.reloadList();
				}else{
					alert(response.message);
				}
			})
		}
// 		根据id查询对象
		$scope.findOne=function(id){
			sellerService.findOne(id).success(function(response){
				$scope.entity=response;
			})
			
		}
    	 
    	
//     	 删除
		$scope.del = function(){
			if($scope.selectIds.length==0){
				return;
			}	
	           
			var isDel = window.confirm("是否要删除您选择的数据");
			if(isDel){
				sellerService.del($scope.selectIds).success(function(response){
//	 				response={success:true|false,message:"保存成功"|"保存失败"}
					if(response.success){
						$scope.reloadList();
					}else{
						alert(response.message);
					}
				})
			}
		}
		
		$scope.search=function(page,pageSize,searchEntity){
// 			当前页+每页显示的条数+searchEntity   
			sellerService.search(page,pageSize,searchEntity).success(function(response){
     			 $scope.list = response.rows;
     			 $scope.paginationConf.totalItems = response.total;
     		 })
			
		}
    	 
     })