
app.service("typeTemplateService",function($http){
    	 
		this.findSpecList=function(typeId){
			return $http.get("../typeTemplate/findSpecList.do?id="+typeId);
		}
	
//     	 分页查询
    	 this.findPage=function(page,pageSize){
    		 return $http.get("../typeTemplate/findPage.do?page="+page+"&pageSize="+ pageSize);
    	 }
    	 
//     	 查询所有品牌数据
    	 this.findAll = function(){
    		return $http.get("../typeTemplate/findAll.do");
    	 }
    	 
//     	 保存方法  新增   修改
 		this.add = function(entity){
			return $http.post("../typeTemplate/add.do",entity);
		}
 		
 		this.update = function(entity){
 			return $http.post("../typeTemplate/update.do",entity);
 		}
 		
// 		根据id查询对象
		this.findOne=function(id){
			return $http.get("../typeTemplate/findOne.do?id="+id);
			
		}
    	 
    	
//     	 删除
		this.del = function(selectIds){
			return 	$http.post("../typeTemplate/del.do?ids="+selectIds);
		}
		
		this.search=function(page,pageSize,searchEntity){
// 			当前页+每页显示的条数+searchEntity   
			return $http.post("../typeTemplate/search.do?page="+page+"&pageSize="+ pageSize,searchEntity);
		}
    	 
     })