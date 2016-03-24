$().ready(function(){
	
	$.validator.addMethod(
		       "regex",
		        function(value, element, regexp) {
		        var check = false;
		        return this.optional(element) || regexp.test(value);
		        },
		        "Please check your input."
		        );
	
    $.validator.addMethod("email", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
    }, "Email Address is invalid: Please enter a valid email address.");

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
				email: true
			}
		},
		messages:{
			name: {
				required: "Please enter an employee name",
				minlength: "Your name must be at least 2 characters long",
				maxlength: "Your name must be less than 20 characters long",
				regex: "Name is invalid: Korean or English Characters Only"
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
				required: "Please enter an employee name",
				minlength: "Your name must be at least 2 characters long",
				maxlength: "Your name must be less than 20 characters long",
				regex: "Name is invalid: Korean or English Characters Only"
			}
		}
	});
});