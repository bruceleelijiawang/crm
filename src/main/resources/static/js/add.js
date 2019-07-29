$(document).ready(function () {
	var updateID;
	$(".tianjia").click(function () {
		var name = $(".name").val();
		var price = $(".price").val();
		alert(name)
		
		$.ajax({
			type : "post",
			url : "http://localhost:8080/crm/add_product",
			dataType : 'json',
			data : {
				"name" : name,
				"price" : parseInt(price)
			},
			success : function(data) {
				window.location.reload(); 

			}
		});
	})
	$(".shanchu").click(function () {
		alert(1)
		var id = $(this).parent().parent().find(".id").text();
		alert(id)
		$.ajax({
			type : "post",
			url : "http://localhost:8080/crm/delete_product",
			dataType : 'json',
			data : {
				"id" : id
			},
			success : function(data) {
				window.location.reload(); 

			}
		});
	})
		$(".xiugai").click(function () {
		
		 updateID = $(this).parent().parent().find(".id").text();
		
		 $(".kuang").css("display","block");

	})
	$(".querenxiugai").click(function () {
		
		var name = $(".xiugainame").val();
		var price = $(".xiugaiprice").val();
		
	
		$.ajax({
			type : "post",
			url : "http://localhost:8080/crm/update_product",
			dataType : 'json',
			data : {
				"id":parseInt(updateID),
				"name" : name,
				"price" : parseInt(price)
			},
			success : function(data) {
				window.location.reload(); 
				
			}
		});
		
		$(".kuang").css("display","none");
	})
})
