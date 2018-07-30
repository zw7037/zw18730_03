
app.service("sellerService",function($http){
    	 
		this.updateStatus=function(sellerId,status){
			return $http.get("../seller/updateStatus.do?sellerId="+sellerId+"&status="+status);
		}
	
	
	    this.findSellerList=function(){
	    	return $http.get("../seller/findSellerList.do");
	    }
	
//     	 分页查询
    	 this.findPage=function(page,pageSize){
    		 return $http.get("../seller/findPage.do?page="+page+"&pageSize="+ pageSize);
    	 }
    	 
//     	 查询所有品牌数据
    	 this.findAll = function(){
    		 
    		return $http.get("../seller/findAll.do");
    	 }
    	 
//     	 保存方法  新增   修改
 		this.add = function(entity){
			return $http.post("../seller/add.do",entity);
		}
 		
 		this.update = function(entity){
 			return $http.post("../seller/update.do",entity);
 		}
 		
// 		根据id查询对象
		this.findOne=function(id){
			return $http.get("../seller/findOne.do?id="+id);
			
		}
    	 
    	
//     	 删除
		this.del = function(selectIds){
			return 	$http.post("../seller/del.do?ids="+selectIds);
		}
		
		this.search=function(page,pageSize,searchEntity){
// 			当前页+每页显示的条数+searchEntity   
			return $http.post("../seller/search.do?page="+page+"&pageSize="+ pageSize,searchEntity);
		}
    	 
     })