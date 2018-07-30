
app.service("specificationService",function($http){
    	 
//     	 分页查询
    	 this.findPage=function(page,pageSize){
    		 return $http.get("../specification/findPage.do?page="+page+"&pageSize="+ pageSize);
    	 }
//     	 分页查询
    	 this.findSpecList=function(){
    		 return $http.get("../specification/findSpecList.do");
    	 }
    	 
//     	 查询所有品牌数据
    	 this.findAll = function(){
    		return $http.get("../specification/findAll.do");
    	 }
    	 
//     	 保存方法  新增   修改
 		this.add = function(entity){
			return $http.post("../specification/add.do",entity);
		}
 		
 		this.update = function(entity){
 			return $http.post("../specification/update.do",entity);
 		}
 		
// 		根据id查询对象
		this.findOne=function(id){
			return $http.get("../specification/findOne.do?id="+id);
			
		}
    	 
    	
//     	 删除
		this.del = function(selectIds){
			return 	$http.post("../specification/del.do?ids="+selectIds);
		}
		
		this.search=function(page,pageSize,searchEntity){
// 			当前页+每页显示的条数+searchEntity   
			return $http.post("../specification/search.do?page="+page+"&pageSize="+ pageSize,searchEntity);
		}
    	 
     })