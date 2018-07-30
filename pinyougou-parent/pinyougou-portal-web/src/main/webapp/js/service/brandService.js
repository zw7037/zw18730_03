
app.service("brandService",function($http){
    	 
	    this.findBrandList=function(){
	    	return $http.get("../brand/findBrandList.do");
	    }
	
//     	 分页查询
    	 this.findPage=function(page,pageSize){
    		 return $http.get("../brand/findPage.do?page="+page+"&pageSize="+ pageSize);
    	 }
    	 
//     	 查询所有品牌数据
    	 this.findAll = function(){
    		 
    		return $http.get("../brand/findAll.do");
    	 }
    	 
//     	 保存方法  新增   修改
 		this.add = function(entity){
			return $http.post("../brand/add.do",entity);
		}
 		
 		this.update = function(entity){
 			return $http.post("../brand/update.do",entity);
 		}
 		
// 		根据id查询对象
		this.findOne=function(id){
			return $http.get("../brand/findOne.do?id="+id);
			
		}
    	 
    	
//     	 删除
		this.del = function(selectIds){
			return 	$http.post("../brand/del.do?ids="+selectIds);
		}
		
		this.search=function(page,pageSize,searchEntity){
// 			当前页+每页显示的条数+searchEntity   
			return $http.post("../brand/search.do?page="+page+"&pageSize="+ pageSize,searchEntity);
		}
    	 
     })