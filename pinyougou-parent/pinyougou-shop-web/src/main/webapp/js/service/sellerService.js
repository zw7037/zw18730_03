app.service("sellerService",function($http){
	
	this.save = function(seller){
		return $http.post("/seller/register.do",seller);
	}
	
	
})