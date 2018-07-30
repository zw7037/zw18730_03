     app.controller("typeTemplateController",function($scope,$controller,brandService,specificationService,typeTemplateService){
    	 
//    	 作用：就是项目typeTemplateController和baseController共用同一个$scope
    	 $controller('baseController',{$scope:$scope});//继承 
    	 
//    	 $scope.brandList={data:[{id:1,text:"aaaa"},{id:2,text:"bbbb"},{id:3,text:"cccc"}]};
    	 
    	 $scope.findBrandList=function(){
    		 brandService.findBrandList().success(function(response){
    			 $scope.brandList=response;
    		 })
    	 }
    	 
    	 $scope.entity={customAttributeItems:[]};
    	 
//    	 动态添加行
    	 $scope.addTables = function(){
    		 $scope.entity.customAttributeItems.push({});
    	 }
    	 
//    	 动态删除行
    	 $scope.delTables = function(index){
    		 $scope.entity.customAttributeItems.splice(index,1);
    	 }
    	 
    	 
    	 $scope.findSpecList=function(){
    		 specificationService.findSpecList().success(function(response){
    			 $scope.specList=response;
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
    		 typeTemplateService.findByPage().success(function(response){
     			 $scope.list = response.rows;
     			 $scope.paginationConf.totalItems = response.total;
     		 })
    	 }
    	 
//     	 查询所有品牌数据
    	 $scope.findAll = function(){
    		 typeTemplateService.findAll().success(function(response){
    			 $scope.list = response;
    		 })
    	 }
    	 
//     	 保存方法  新增   修改
 		$scope.save = function(){
			var resultObject;
			if($scope.entity.id!=null){
				resultObject = typeTemplateService.update($scope.entity);
			}else{
				resultObject = typeTemplateService.add($scope.entity);
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
			typeTemplateService.findOne(id).success(function(response){
//				response.specIds从数据库中获取的是字符串，需要把字符串转成 对象
//				把字符串转成数组对象
				response.specIds = JSON.parse(response.specIds);
				response.customAttributeItems = JSON.parse(response.customAttributeItems);
				response.brandIds = JSON.parse(response.brandIds);
				$scope.entity=response;
			})
			
		}
//		[{"id":26,"text":"上衣尺码"},{"id":34,"text":"腰围"}]

//		[{"text":"安装服务"},{"text":"退换货服务"}]
		$scope.jsonTostring=function(jsonArray){
			jsonArray = JSON.parse(jsonArray);
			var str="";
			for (var i = 0; i < jsonArray.length; i++) {
				if(i==jsonArray.length-1){
					str+=jsonArray[i].text;
				}else{
					str+=jsonArray[i].text+",";
				}
			}
			return str;
		}
    	 
    	
//     	 删除
		$scope.del = function(){
			if($scope.selectIds.length==0){
				return;
			}	
	           
			var isDel = window.confirm("是否要删除您选择的数据");
			if(isDel){
				typeTemplateService.del($scope.selectIds).success(function(response){
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
			typeTemplateService.search(page,pageSize,searchEntity).success(function(response){
     			 $scope.list = response.rows;
     			 $scope.paginationConf.totalItems = response.total;
     		 })
			
		}
    	 
     })