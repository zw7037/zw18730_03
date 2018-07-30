 //控制层 
app.controller('itemCatController' ,function($scope,$controller,itemCatService,typeTemplateService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
//	1、需要一个变量记录当前展示的是第几级的数据
	  $scope.grade=1;
//	2、需要三个对象用来存储面包屑上的名称
	 $scope.entity1={name:"顶级分类列表",id:0};
	 $scope.entity2=null;
	 $scope.entity3=null;
	 
	 $scope.setGrade=function(grade,pojo){
		 $scope.grade=grade;
		 if($scope.grade==1){
			 $scope.entity1=pojo;
			 $scope.entity2=null;
			 $scope.entity3=null;
		 }
		 
		 if($scope.grade==2){
			 $scope.entity2=pojo;
			 $scope.entity3=null;
		 }
		 if($scope.grade==3){
			 $scope.entity3=pojo;
		 }
		 
	 }
	
	
	$scope.findTypeTemplateList=function(){
		typeTemplateService.findAll().success(function(response){
			$scope.typeTemplateList=response;
		})
	}
	
	
	$scope.findByParentId=function(parentId){
		itemCatService.findByParentId(parentId).success(function(response){
			$scope.list=response;
		})
	}
	
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){	
//		保存之前需要给当前entity设置parentId
		if($scope.grade==1){
			$scope.entity.parentId=$scope.entity1.id;
		}
		if($scope.grade==2){
			$scope.entity.parentId=$scope.entity2.id;
		}
		if($scope.grade==3){
			$scope.entity.parentId=$scope.entity3.id;
		}
		
		
		
		$scope.serviceObject = {};//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.findByParentId($scope.entity.parentId);//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){	
		
		if($scope.selectIds.length==0){
			return;
		}
		
		//删除之前需要给当前entity设置parentId
		if($scope.grade==1){
			$scope.entity.parentId=$scope.entity1.id;
		}
		if($scope.grade==2){
			$scope.entity.parentId=$scope.entity2.id;
		}
		if($scope.grade==3){
			$scope.entity.parentId=$scope.entity3.id;
		}
           
		var isDel = window.confirm("是否要删除您选择的数据");
		if(isDel){
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.findByParentId($scope.entity.parentId);//重新加载
				}else{
					alert(response.message);
				}						
			}		
		);				
	}
}
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});	
