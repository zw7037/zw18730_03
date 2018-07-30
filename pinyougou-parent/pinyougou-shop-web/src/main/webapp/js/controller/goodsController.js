  //控制层 
app.controller('goodsController' ,function($scope,$controller,typeTemplateService,itemCatService  ,goodsService,uploadService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
//	itemImages:[{"color":"白色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVnGZfWAaX2hAAjlKdWCzvg173.jpg"}
//	{"color":"白色","url":"http://192.168.25.133/group1/M00/00/00/wKgZhVnGZfWAaX2hAAjlKdWCzvg173.jpg"}]
	
	$scope.entity={tbGoods:{},tbGoodsDesc:{itemImages:[],specificationItems:[]},itemList:[]};
//	查询一级分类数据
	$scope.findCategory1List=function(){
		itemCatService.findByParentId(0).success(function(response){
			$scope.category1List=response;
			
		})
	}
//	监测一级分类数据，如果一级分类数据变化，会触发方法
	$scope.$watch("entity.tbGoods.category1Id",function(newValue){
		itemCatService.findByParentId(newValue).success(function(response){
			$scope.category2List=response;
		})
	})
	
//	监测二级分类数据，如果二级分类数据变化，会触发方法
	$scope.$watch("entity.tbGoods.category2Id",function(newValue){
		itemCatService.findByParentId(newValue).success(function(response){
			$scope.category3List=response;
		})
	})
//	监测三级分类数据，如果三级分类数据变化，会触发方法 ：查询模板id
	$scope.$watch("entity.tbGoods.category3Id",function(newValue){
		itemCatService.findOne(newValue).success(function(response){
			$scope.entity.tbGoods.typeTemplateId= response.typeId;
			
//			使用模板id查询模板对象
			typeTemplateService.findOne(response.typeId).success(function(response){
//				response:模板对象
//				{"brandIds":"[{"id":1,"text":"联想"},{"id":3,"text":"三星"},{"id":2,"text":"华为"},{"id":5,"text":"OPPO"},{"id":4,"text":"小米"},{"id":9,"text":"苹果"},{"id":8,"text":"魅族"},{"id":6,"text":"360"},{"id":10,"text":"VIVO"},{"id":11,"text":"诺基亚"},{"id":12,"text":"锤子"}]",
//				"customAttributeItems":" [{"text":"配送方式"},{"text":"是否支持验机"}]",
//				"id":35,"name":"手机",
//				"specIds":"[{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]"}
//				页面需要的品牌数据
				$scope.brandList = JSON.parse(response.brandIds);
//				页面需要的扩展属性数据
				$scope.entity.tbGoodsDesc.customAttributeItems = JSON.parse(response.customAttributeItems);
//				"specIds":"[{"id":27,"text":"网络",options:[{},{},{}]},{"id":32,"text":"机身内存",options:[{},{},{}]}]"
			});
			typeTemplateService.findSpecList(response.typeId).success(function(response){
//				response:[{"id":27,"text":"网络",options:[{id:1,optionName:"联通3G"},{id:2,optionName:"联通4G"},{id:3,optionName:"联通2G"}]},
//				{"id":32,"text":"机身内存",options:[{id:4,optionName:"16G"},{id:5,optionName:"32G"},{id:6,optionName:"64G"}]}]
				$scope.specList=response;
				
			})
			
			
			
		})
	})
	
	
//	勾选或取消规格小项触发的方法  举例： key="网络" value="移动3G"
	$scope.updateSpecificationItems=function($event,key,value){
		var specItemList = $scope.entity.tbGoodsDesc.specificationItems;
//		specItems数据机构:[{"attributeName":"网络","attributeValue":["移动3G","联通3G"]},
//			{"attributeName":"机身内存","attributeValue":["32G","64G"]}]
		
		if($event.target.checked){
			
			var specItem = searchObjectBykey(specItemList,key);
			if(specItem==null){
				specItemList.push({attributeName:key,attributeValue:[value]});
			}else{
				specItem.attributeValue.push(value);
			}
			
		}else{
//			取消
			var specItem = searchObjectBykey(specItemList,key);
			var index = specItem.attributeValue.indexOf(value);
			specItem.attributeValue.splice(index,1);
			
			if(specItem.attributeValue.length==0){
				var index1 = specItemList.indexOf(specItem);
				specItemList.splice(index1,1);
			}
		}
	}
	
	
	
	function searchObjectBykey(specItemList,key){
//		specItemList格式：[{"attributeName":"网络","attributeValue":["移动3G"]},{"attributeName":"机身内存","attributeValue":["移动3G"]}]
		for (var i = 0; i < specItemList.length; i++) {
			if(specItemList[i].attributeName==key){
				return specItemList[i];
			}
		}
		return null;
	}
	
	
	
	
	$scope.image={url:""};
	$scope.uploadFile=function(){
		uploadService.uploadFile().success(function(response){
//			response:{success:true|false,message:"图片路径"|"上传失败"}
			if(response.success){
				$scope.image.url=response.message;
			}else{
				alert("上传失败");
			}
			
		})
		
	}
	
//	向图片列表中放数据
	$scope.addItemImages=function(){
		$scope.entity.tbGoodsDesc.itemImages.push($scope.image);
	}
//从图片列表中移除数据
	$scope.delItemImages=function(index){
		$scope.entity.tbGoodsDesc.itemImages.splice(index,1);
	}
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){		
		
		alert( $scope.entity.tbGoodsDesc.customAttributeItems);
		return;
		
		$scope.entity.tbGoodsDesc.introduction=editor.html();
		var serviceObject;//服务层对象  				
		if($scope.entity.tbGoods.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
//		        	$scope.reloadList();//重新加载
					alert("新增成功，请等待商品审核");
					$scope.entity={tbGoods:{},tbGoodsDesc:{},itemList:[]};
					editor.html("");
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	// 重新加载列表 数据
	$scope.reloadList = function() {
		// 切换页码
		$scope.search($scope.paginationConf.currentPage,
				$scope.paginationConf.itemsPerPage);
	}
	// 分页控件配置
	$scope.paginationConf = {
		currentPage : 1,
		totalItems : 10,
		itemsPerPage : 10,
		perPageOptions : [ 10, 20, 30, 40, 50 ],
		onChange : function() {
			$scope.reloadList();// 重新加载
		}
	};
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	//创建sku列表
	$scope.createItemList=function(){	
		$scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' } ];//初始
		var items=  $scope.entity.tbGoodsDesc.specificationItems;	
		for(var i=0;i< items.length;i++){
			$scope.entity.itemList = addColumn( $scope.entity.itemList,items[i].attributeName,items[i].attributeValue );    
		}	
	}
	//添加列值 
	addColumn=function(list,columnName,conlumnValues){
		var newList=[];//新的集合
		for(var i=0;i<list.length;i++){
			var oldRow= list[i];
			for(var j=0;j<conlumnValues.length;j++){
				var newRow= JSON.parse( JSON.stringify( oldRow )  );//深克隆
				newRow.spec[columnName]=conlumnValues[j];
				newList.push(newRow);
			}    		 
		} 		
		return newList;
	}
	
	
	
	
	
	
	
	
});	
