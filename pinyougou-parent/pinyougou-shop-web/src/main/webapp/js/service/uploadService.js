app.service("uploadService",function($http){

	this.uploadFile=function(){
		//	angularJs文件上传
		var formData=new FormData();
		
	//	file.files[0]获取document中的第一个file类型的数据
	    formData.append("file",file.files[0]); 
	
		return $http({
			method:"post",
			data:formData,
			url:"../upload.do",
			headers: {'Content-Type':undefined},
	        transformRequest: angular.identity
		})
	}
})