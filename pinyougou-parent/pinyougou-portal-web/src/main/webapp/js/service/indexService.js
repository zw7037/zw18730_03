app.service("indexService",function($http){
	
	this.showUsername=function(){
		return $http.get("../login/showUsername.do");
	}
	
})