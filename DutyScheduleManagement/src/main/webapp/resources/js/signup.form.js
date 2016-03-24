$().ready(function(){
	
	$.validator.addMethod(
		"regex",
		function(value, element, regexp) {
			var check = false;
			return this.optional(element) || regexp.test(value);
		},
		"Please check your input."
	);
	
    $.validator.addMethod(
    	"email",
    	function(value, element) {
    		return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z-]+\.[a-zA-Z.]{2,5}$/i.test(value)
    			&& /^(?=.{2,20}@.{5,15}$)(?=.{7,35}$).*$/i.test(value);
    	},
    	"Mail address is invalid"
    );

	$("#signup_frm").validate({
		rules : {
			name: {
				required: true,
				minlength: 2,
				maxlength: 20,
				regex: /^[가-힣a-zA-Z]+$/
			},
			email: {
				required: true,
				email: true,
				remote: {
					url : "/empl/available.html",
					type : "get",
					data : {
						email: function(){
							return $("#email").val();
						}
					}
				}					
			}
		},
		messages:{
			name: {
				required: "This field is required",
				minlength: "At least 2 characters long",
				maxlength: "Less than 20 characters long",
				regex: "Korean or English characters only"
			},
			email: {
				remote: "Such email address already exsits"
			}
		}
	});
	
	$("#m_frm").validate({
		rules : {
			name : {
				required: true,
				minlength: 2,
				maxlength: 20,
				regex: /^[가-힣a-zA-Z]+$/
			},
			email: {
				required: true,
				email: true
				}
		},
		messages : {
			name: {
				required: "This field is required",
				minlength: "At least 2 characters long",
				maxlength: "Less than 20 characters long",
				regex: "Korean or English characters only"
			}
		}
	});
});