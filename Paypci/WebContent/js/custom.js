$(".buy_now_dcs").on('click',function() {
	$('html,body').animate({ scrollTop: $("#compareProducts").offset().top}, 1000);
	return false;
	e.preventDefault();				
});


//cart
$('.collapse').on('shown.bs.collapse', function(){
	$(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");
	}).on('hidden.bs.collapse', function(){
	$(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
});


//AJAX

function loadXMLDoc(dataStr, targetDiv) {
	var xmlhttp;
	//loader();
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			 var responseText = xmlhttp.responseText;
			 
			 //alert(responseText);
			 var index1       = responseText.indexOf("_",0);// y_(cartcount)
			 var removestatus = responseText.substring(0, index1);
			 var cartcount    = responseText.substring(index1+1, responseText.length);
			 
			 var abc = cartcount.substring(cartcount.indexOf("(",0) +1 , cartcount.indexOf(")",0));
			 
			 //alert("cartcount :"+abc);
			  if(abc=='0') {
				//  alert("removing");
				  $('h3.cart-empty-message').show().html('Cart is empty');
				  $("#checkout").remove();
				  $('.payment-gateway-accordion').remove();
				  $('.enter-details-accordion').remove();
				  $('#enter-details').remove();
			}
			 //if(removestatus.localeCompare("y") == 0){
				 document.getElementById(targetDiv).innerHTML = '';
				 document.getElementById('totalItems').innerHTML = cartcount;

			// }
		}
	};
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}

function loginCheck(dataStr, calledFrom) {
	var xmlhttp;
	//loader();
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			 var responseText = xmlhttp.responseText;
			 
			 if(responseText.trim() == 'm'){
				 window.location.replace("mc?service=myAccount");
			 } else if(responseText.trim() == 'y' && calledFrom == 'cart') {
					$('.user-login-menu').empty();
					$('.enter-details-accordion').remove();
					$('.user-login-menu').html('<a href="javascript:myaccount()">  My Account </a>');
					$('.detail-checkout-button').empty().html('<button class="btn btn-danger pull-right"  id="checkout" onclick="goToPaymentGateway();">Checkout</button>');
				     goToPaymentGateway();
				   
				   //replace login with myaccount in header	
					
			  } else if(responseText.trim() == 'y' && calledFrom == 'header') {
				    $('.user-login-menu').empty();
					$('.user-login-menu').html('<a href="javascript:myaccount()">  My Account </a>');
					$('.enter-details-accordion').remove();
					$("#collapseOne").addClass('in');
					$('.detail-checkout-button').empty().html('<button class="btn btn-danger pull-right"  id="checkout" onclick="goToPaymentGateway();">Checkout</button>');
					
					var pageName =$('#pageName').val();// document.loginform.pageName.value;//
					
					if(pageName!=null && pageName!='' && pageName=='cart.jsp'){
						
						var cartcount = $('#cartcount').val();
						if(cartcount!=null && cartcount!='' && cartcount>0){
							
							goToPaymentGateway();
						}
					}
				  // 1. replace login with myaccount in both header
				  // 2. if called from cart.jsp then hide the (ENTER DETAILS TO PROCEED)
				  // 3. proceed to payment gateway
				  
			  } else if(responseText.trim() != 'y' && calledFrom == 'header'){
				  $('.loginErrorMsg').html(responseText);
			  } else {
				  $('.responseMessage').html(responseText);
			  }
		}
	};
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}

//
//location.reload(); 


function generateOrderDetails(dataStr, targetDiv) {
	var xmlhttp;
	//loader();
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			 var responseText = xmlhttp.responseText;
			// alert("responseText :"+responseText);
			 var respArr =  responseText.split("_");
			 
			/* alert("orderId :"+respArr[0]);
			   alert("status  :"+respArr[1]);
			   alert("gateway :"+respArr[2]);
			   alert("subId   :"+respArr[3]);
			   alert("productIdstr   :"+respArr[4]);
			 */
			 
			 callSuccessPaymentPage(respArr[0], respArr[1],respArr[2], respArr[3], respArr[4]);
			 
		}
	};
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}


function goToPaymentGateway(){
	 $("#collapseThree").collapse('show');
	 $(".payment-gateway-accordion").show();
	 $("#collapseTwo").collapse('hide');
	 generateOrderDetails('appsdaily?action=generateOrderDetails', '');
	 ga('send','event', 'Conversion', 'Checkout', '','0');
	 
}


function callSuccessPaymentPage(orderId, orderType, gateway, subId,appIds) {
	var urlNew = "payment?action=paymentgateway&orderId=" + orderId
			+ "&subscriberId=" + subId + "&orderType=" + orderType+ "&appIds=" + appIds
			+ "&gateway=" + gateway;
	if(gateway=='techprocess'){
		window.location.href = urlNew;
	}else{
		$('.enter-details-section').slideUp(300);
		$('span.enter-details ').removeClass('active');
		$('span.payment-gateway').addClass('active');
		$('#paymentGatewayForm').slideDown(300);
		$(this).next('.acc-trigger').addClass('active');
		$("#paymentFrame").attr("src", urlNew);
	}	
}

function setGradeBeanArrayInSession() {
	getDataFromURL("payment?action=setdefaultvalues", "setdefaultvalues");
	return true;

}

function getDataFromURL(dataStr, type) {
	var xmlhttp;

	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				//alert("setGradeBeanArrayInSession200");
			} else if (xmlhttp.status == 400) {
				//alert("setGradeBeanArrayInSession400");
			} else {
				//alert("setGradeBeanArrayInSessionelse");
			}
		}
	};
	
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}

function validateModal() {
	var day1, day2;
	var month1, month2;
	var year1, year2;
	
	value1 = document.getElementById('purchase-date').value;
	value2 = document.getElementById('current_date').value;
	
	day1 = value1.substring (0, value1.indexOf ("/"));
	month1 = value1.substring (value1.indexOf ("/")+1, value1.lastIndexOf ("/"));
	year1 = value1.substring (value1.lastIndexOf ("/")+1, value1.length);
	
	day2 = value2.substring (0, value2.indexOf ("/"));
	month2 = value2.substring (value2.indexOf ("/")+1, value2.lastIndexOf ("/"));
	year2 = value2.substring (value2.lastIndexOf ("/")+1, value2.length);
	
	date1 = year1+"/"+month1+"/"+day1;
	date2 = year2+"/"+month2+"/"+day2;
	
	firstDate = Date.parse(date1);
	secondDate= Date.parse(date2);
	
	msPerDay = 24 * 60 * 60 * 1000;
	diffDays = Math.round((secondDate.valueOf()-firstDate.valueOf())/ msPerDay) + 1;

	if (document.getElementById('purchase-date').value=='') {
		
		$('.error-message').text('Please select purchase date.');
		return false;
	}else if (document.getElementById('value_options').value=='') {
		//alert("Days Difference : " +diffDays);
		$('.error-message').text('Please select handset value.');
		return false;
	} else if (document.getElementById('platformSelected').value=='0') {
		//alert("Days Difference : " +diffDays);
		$('.error-message').text('Please select OS.');
		return false;
	}else if (diffDays > 15) {
		
	//	alert("Days Difference : " +diffDays);
		//alert("Insurance is not applicable !");
		//$('.error-message').text('Insurance is  applicable only for purchases within last 15 days ');
		$('#validation-data').show();
		$('.error-message').empty();
		$('#modal-form-fields,.modal-footer').hide();
		var productName = document.getElementById('productName').value;
		var productID = document.getElementById('productID').value;
		ga('send','event', 'Conversion', '15-Day_limit', productName+'-'+productID, diffDays);
		return false;
		
	}else {
		//alert("Days Difference : " +diffDays);
		$('.error-message').empty();
		//alert("Successfull !")
		addtocart();
		
		return true;
		
	}
}

function changepassword(dataStr, targetDiv) {
	var xmlhttp;
	//loader();
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			 var responseText = xmlhttp.responseText;
			  var message = '';
			  if(responseText=='f'){
				  message = 'Password update failed';
			  }else{
				  if(responseText == 'y'){
					  message = 'Your password has been updated successfully. Please proceed to login with your new password.';
				  }else {
					  message = 'Please enter correct password.';
				  }  
			  }
			  
			  $('.responseMessage').html(message);
		}
	};
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}

function backToFields() {
	$('#validation-data').hide();
	$('#modal-form-fields,.modal-footer').show();
}


$("#new_user_link, .enter_details_link").on('click',function(e) {
	//alert("Hi");
	e.preventDefault();
	$('#login_block').hide('slow');
	$('#new_user_block').load('new_user.jsp');
});
$('#forgot_link').on('click',function(e) {
	e.preventDefault();
	$('#login_block').hide('slow');
	$('#forgot_user_block').load('forgot.jsp');
});

function backToLoginBlock(){
      $('#new_user_block').empty();
      $('#forgot_user_block').empty();
      $('#login_block').fadeIn('slow').css('display','block');
}


function validateEmailId(email) {
	   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	   if(email != null && reg.test(email) == false) {
	      return false;
	   }
	   return true;
	}

function validateSpecialChars(myStringID)
{
	   var reg = /^[a-zA-Z]+$/;
	   if(myStringID != null && reg.test(myStringID) == false) {
	      return false;
	   }
	   return true;
}

function numbersonly(e, decimal) {
	 var key;
	 var keychar;
	 
	 if (window.event) {
	    key = window.event.keyCode;
	 }else if (e) {
	    key = e.which;
	 }else {
	   return true;
	 }

	 keychar = String.fromCharCode(key);
	 
	 if ((key==null) || (key==0) || (key==8) ||  (key==9) || (key==13) || (key==27) ) {
	    return true;
	 }else if ((("0123456789").indexOf(keychar) > -1)) {
	    return true;
	 }else if (decimal && (keychar == ".")) {
	    return true;
	 }else {
	   return false;
	 }
	}

function cartcount(dataStr, targetDiv) {
	var xmlhttp;
	//loader();
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			 var responseText = xmlhttp.responseText;
			 
			// alert("cartcount :"+responseText);
			document.getElementById('totalItems').innerHTML = '(' + responseText + ')';
			 document.getElementById('totalItems').value = responseText;
			 

		}
	};
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}

function sendmail() {
	
	var email_user =  $("#email_user").val();
	var flag = true;
	if(email_user == null || email_user == '') {
		 flag = false;
		 $('.responseMessage').html('Enter email Id');
	  	 return document.forgetPassForm.email_user.focus();
	}
	//Validate Email
	flag=validateEmailId(email_user);
    if(flag){
         var url = 'appsdaily?action=login&methodName=forgotPassword&email_user='+email_user;
         sendMailTo(url,'');
     }else{
    	 $('.responseMessage').html('Enter Valid Email Id');
	  	 return document.forgetPassForm.email_user.focus();
     } 
    
}


function sendMailTo(dataStr, targetDiv) {
	var xmlhttp;
	//loader();
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		//alert(xmlhttp.readyState);
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			 var responseText = xmlhttp.responseText;
			// alert("responseText :"+responseText);
			  if(responseText == 'y'){
				  goToPaymentGateway();
			  } else {
				  $('.responseMessage').html(responseText);
			  }
		}
	};
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}

function getResponseFromURL(action, methodName, mobile, scratchCode) {
	loader();
	$.ajax({
		 url: 'appsdaily',
		 type: 'GET',
		 data: {action:action,methodName:methodName,mobile:mobile,scratchCode:scratchCode},
		 dataType: "json",
		 success: function(jsonObj) {
			 $("#overlay").remove();
			 var resText = jsonObj.message;
			 var resTextStatus = jsonObj.status;
			 if(methodName =='getScratchCode'){
				 if(resTextStatus=='Y'){
					 $('.responseMessage').html("");
					 $('.successMessage').html(resText); 
				 }else{
					 $('.responseMessage').html(resText);
					 $('.successMessage').html("");
				 }				 
			 }
		  },
		  error: function(jqXHR, exception) {
			  console.log(jqXHR.responseText +" and "+exception);			  
		  }
	
	});
	
	/*var xmlhttp;
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			 var responseText = xmlhttp.responseText;
			 $("#overlay").remove();
			 if(targetDiv =='getScrachCode'){
				 if(responseText.contains('Use Scratch Code')){
					 $('.responseMessage').html("");
					 $('.successMessage').html(responseText); 
				 }else{
					 $('.responseMessage').html(responseText);
					 $('.successMessage').html("");
				 }				 
			 }
		}
	};
	var url = dataStr;
	xmlhttp.open("POST", url, true);
	xmlhttp.send();*/
}

function loader() {
	var docHeight = $(document).height();
	$("body")
			.append(
					"<div id='overlay'><span id='centerloading'><img src='assets/js/images/ajax-loader.gif' /><h3>Please wait...</h3></span> </div>");
	$("#overlay").height(docHeight).css({
		'opacity' : 0.4,
		'position' : 'absolute',
		'top' : 0,
		'left' : 0,
		'background-color' : 'black',
		'width' : '100%',
		'z-index' : 5000
	});
	$("#centerloading").css({
		 'width': '300px',
	   'height': '300px',
	   'left': '50%',
	   'top': '50%',
	   'margin-left': '-50px',
	   'margin-top': '-100px',
	   'position' : 'absolute'
	});
	
}

$(document).ready(function(){
	  $(".faq_section_title").click(function(event){
	    event.preventDefault();
	  });
	});

$(document).ready(function() {
	
    $(".buy-now-button, .buy-this-product").bind('contextmenu',function(e){
    	e.preventDefault();
    });
	
	//cartcount('appsdaily?action=utils&methodName=getCartCount','');
});

function validateform(formname,errormessagefield) {
	var errorflag = false;
    var elem = document.getElementById(formname).elements;
    for (var i = 0; i < elem.length; i++) {
    	//var name = elem[i].name;
    	if(elem[i].value==null || elem[i].value=="" && (elem[i].type=='text' || elem[i].type=='password' || elem[i].type=='select-one' || elem[i].type=='textarea')){
    		errorflag = true;
    		var placeholder = elem[i].placeholder;
    		var titleElement = elem[i].title;
    		if(placeholder == null && titleElement!=null)placeholder = titleElement;
    		if(placeholder==null || placeholder=='undefined')placeholder = 'Field';
			$(errormessagefield).html(placeholder + ' cannot be blank');
			elem[i].focus();
			break;
		}
    }
	return errorflag;		    
}

//Menu Dropdown on Support Page
$(document).ready(function(){
	$("#menu-support").click(function (e) {
		//alert("Is Working ");
		e.preventDefault();
	    $header = $(this);
	    $content = $header.next("#menu-support-ul");
	    $content.slideToggle(300, function () {
	    });
	});
});


$(document)
.ready(
		function() {
			$('.faq_section_body h3')
					.each(
							function() {
								var tis = $(this), state = false, answer = tis
										.next('div')
										.slideUp();
								tis
										.click(function() {
											$('.faq_section_body div').not($(this).next('div')).not('.clearfix').hide(500);
											state = !state;
											answer
													.slideToggle(state);
											tis
													.toggleClass(
															'active',
															state);
										});
							});
		});

function scrollToFaq(divID) {
	$('html,body').animate({
		scrollTop : $(divID).offset().top - 100
	}, 1000);
	return false;
	e.preventDefault();
}

function sendGAEvent(action, label, value) {
	ga('send','event', 'Conversion', action, label, value);
}

$(document).ready(function() {
	$(window).scroll(function() {
		if ($(this).scrollTop() > 400) {
			$('.back_top').fadeIn();
		} else {
			$('.back_top').fadeOut();
		}
	});
});

function registerUserOnline(){
	var fullname      = $("#fullname").val();
	var dob           = $("#dob").val();
	var parentsname   = $("#parentsname").val();
	var mobilenumber  = $("#mobilenumber").val();
	var emailid       = $("#emailid").val();
	var password      = $("#password").val();
	var cpassword     = $("#cpassword").val();
	var invoicenumber = $("#invoicenumber").val();
	var invoicedate   = $("#invoicedate").val();
	var invoiceamount = $("#invoiceamount").val();
	var retailersname = $("#retailersname").val();
	var mobilemodel   = $("#mobilemodel").val();
	var mobilemake    = $("#mobilemake").val();
	var imeino        = $("#imeino").val();
	var action        = $("#action").val();
	var methodName    = $("#methodName").val();
	var scarchcard    = $("#scarchcard").val();
	var platformid    = $("#platformid").val();
	var productid     = $("#productid").val();
	var orderid       = $("#orderid").val();
	var gender       = $("#gender").val();
	
	var jsonObj = {
			"fullname" : fullname,
			"dob" : dob,
			"parentsname" : parentsname,
			"mobilenumber" : mobilenumber,
			"emailid" : emailid,
			"password" : password,
			"cpassword" : cpassword,
			"invoicenumber" : invoicenumber,
			"invoicedate" : invoicedate,
			"invoiceamount" : invoiceamount,
			"retailersname" : retailersname,
			"mobilemodel" : mobilemodel,
			"mobilemake" : mobilemake,
			"imeino" : imeino,
			"action" : action,
			"methodName" : methodName,
			"scarchcard" : scarchcard,
			"platformid" : platformid,
			"productid" : productid,
			"gender":gender,
			"orderid" : orderid
	};
	var JsonString = JSON.stringify(jsonObj);
	ajaxJQuery(JsonString, "register_user_online", action, methodName);
	
}

function ajaxJQuery(dataStr, reqType, action, methodName){
	$.ajax({
		  url: 'appsdaily',
		  type: 'GET',
		  data: {message:dataStr,type:reqType,action:action,methodName:methodName},
		  dataType: "json",
		  success: function(jsonObj) {
			 if (jsonObj.flag == "success") {
				 if(reqType == 'register_user_online'){
					 window.location.href = "onlineRegistrationSuccess.jsp?message=success&dataStr="+dataStr;
				 }
			 } else {
				 bootbox.alert('<h4>'+jsonObj.message+'</h4>');			
			 }
		  },
		  error: function(jqXHR, exception) {
			  if (jqXHR.status == 400) {
				  bootbox.alert('<h4>Sorry, Server error.</h4>');
			  } else {
	        	if (jqXHR.responseText == null || jqXHR.responseText == '') {
	        		bootbox.alert('<h4>Sorry, Server error.</h4>');
				} else {										
					bootbox.alert('<h4>Sorry, Server error.</h4>');
				}
	  
			  }
		  }
	
		});
}


/*****Password copy paste*///////////
/*$(function () {
    $("input[type='password']").bind("paste cut paste", function (e) {
        e.preventDefault();
        return false;
    });
});*/

jQuery(document).ready(function() {
	   // Toggle Left Menu
	   jQuery('.leftpanel .nav-parent > a').click(function() {
	      
	      var parent = jQuery(this).parent();
	      var sub = parent.find('> ul');
	      
	     
	});
	   
	   
	   // Menu Toggle
	   jQuery('.menutoggle').click(function(){
	      
	      var body = jQuery('body');
	      var bodypos = body.css('position');
	      
	      if(bodypos != 'relative') {
	         
	         if(!body.hasClass('leftpanel-collapsed')) {
	            body.addClass('leftpanel-collapsed');
	            jQuery('.nav-bracket ul').attr('style','');
	            
	            jQuery(this).addClass('menu-collapsed');
	            
	         } else {
	            body.removeClass('leftpanel-collapsed chat-view');
	            jQuery('.nav-bracket li.active ul').css({display: 'block'});
	            
	            jQuery(this).removeClass('menu-collapsed');
	            
	         }
	      } else {
	         
	         if(body.hasClass('leftpanel-show'))
	            body.removeClass('leftpanel-show');
	         else
	            body.addClass('leftpanel-show');
	         
	             
	      }

	   });
	   
	   

	});



	//AJAX
	function killsession() {
		var xmlhttp;
		//loader();
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var responseText='fail';
				responseText = xmlhttp.responseText;
				 if(responseText=='success'){
				 }else{
				 }
			}
		};
		var url = '/payment/ajax?action=kill';
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		
	}

