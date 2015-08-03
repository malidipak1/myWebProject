<%@page
	import="com.appsdaily.payment.scm.constants.Constants.PaymentConfig"%>
<%@page import="com.appsdaily.payment.util.PaymentProperties"%>
<%@page import="com.appsdaily.payment.scm.constants.Constants"%>
<%

String banking_type=(String)request.getAttribute("banking_type");
if(banking_type==null || (!"C".equals(banking_type)&&!"D".equals(banking_type)&&!"N".equals(banking_type))){banking_type="C";}
%>
<html>
<head>
<title>Payment Gateway</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<link href="images/favicon.png" rel="shortcut icon" type="image/png" />
<jsp:include page="include/HeadSection.jsp" flush="true" />
</head>
<body onload="onpageload()">
	<form method="post" id="customerData" name="customerData"
		autocomplete="off" action="paymentAction">
		<input type="hidden" name="elementNo" id="elementNo" value="1" /> <input
			type="hidden" name="action" id="action"
			value="<%=PaymentConfig.PAYGATEWAY_ACTION %>" />
		<div class="center-container custom-container-wrap"
			style="margin-top: 5px; height: auto;">
			<div class="clearfix"></div>
			<div class="col-sm-12 pad0 main-wrapper">

				<div class="parent-div tabbable tabs-left card-content-wrapper">
					<div class="col-md-12 col-sm-12 col-xs-12 ">
						<%
						    if (request.getParameter("error_msg") != null) {
						%>
						<div class="clearfix"></div>
						<div class="alert alert-danger"
							style="margin: 0; margin-top: 10px">
							Oops! It appears there was an issue with your transaction {<%=request.getParameter("error_msg")%>}.
							Please try again.
						</div>
						<%
						    }
						%>

						<div id="order-details">

							<div class="detail-description">
								<span class="product-title"><%=((String) request.getAttribute("product_name")!=null?(String) request.getAttribute("product_name"):"")%></span>
								<span class="short-description"><%=((String) request.getAttribute("product_details")!=null?(String) request.getAttribute("product_details"):"")%></span>
							</div>
							<div class="total-amount">
								<h3><%=(String) request.getAttribute("amount")%></h3>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="order-id-details">
							<div class="order-id">
								Order ID :
								<%=(String) request.getAttribute("order_id")%>
							</div>
							<div class="order-date"><%=((String) request.getAttribute("order_time")!=null?(String) request.getAttribute("order_time"):"")%></div>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="alert show-menu-link" id="menu-support"
						style="display: none; margin-bottom: -19px !important;">
						<a class="btn btn-default btn-lg btn-block" href="#">Menu <span
							class="caret"></span></a>
					</div>
					<ul id="menu-tabs-ul"
						class="nav nav-tabs nav nav-tabs col-xs-12 pull-left payment-tabs child-one"
						data-tabs="tabs">
						<li class="<%="C".equals(banking_type)?"active":""%>"><a
							href="#CreditCard" class="menu-about-link payOption"
							data-toggle="tab" id="idforfirsttimeclickeventtab1"
							data-value1="OPTCRDC" data-value2="1"><img
								src="images/nav/cards.png" class="payment-type-icon">CreditCard</a></li>
						<li class="<%="D".equals(banking_type)?"active":""%>"><a
							href="#DebitCard" class="menu-about-link payOption"
							data-toggle="tab" data-value1="OPTDBCRD" data-value2="2"
							id="idforfirsttimeclickeventtab2"><img
								src="images/nav/cards.png" class="payment-type-icon">DebitCard</a></li>
						<li class="<%="N".equals(banking_type)?"active":""%>"><a
							href="#NetBanking" class="menu-about-link payOption"
							data-toggle="tab" data-value1="OPTNBK" data-value2="4"
							id="idforfirsttimeclickeventtab3"><img
								src="images/nav/net-banking.png" class="payment-type-icon">NetBanking
						</a></li>
					</ul>
					<div class="tab-content  col-sm-12 col-xs-12">


						<div
							class="tab-pane fade <%="C".equals(banking_type)?"active in":""%>"
							id="CreditCard">
							<div class="clearfix"></div>
							<div class="row">
								<div class="col-md-12 card-box">
									<div class="row form-group hidden">
										<div class="col-md-12">
											<select name="card_name1" id="card_name1"
												class="form-control card_name_class">
												<option value="0">Select Card Name</option>
											</select>
										</div>
									</div>


									<div class="row form-group notezeclick card-no-input">
										<div class="col-md-12">
											<input type="text" name="card_number1" id="card_number1"
												maxlength="19" class="form-control inspectletIgnore"
												placeholder="Enter card number">
										</div>
									</div>
									<div class="row form-group notezeclick card-exp-cvv-detail">
										<div class="col-md-3 col-xs-4">
											<select class="form-control inspectletIgnore"
												name="expiry_month1" id="expiry_month1">
												<option value="0">MM</option>
												<option value="01">01</option>
												<option value="02">02</option>
												<option value="03">03</option>
												<option value="04">04</option>
												<option value="05">05</option>
												<option value="06">06</option>
												<option value="07">07</option>
												<option value="08">08</option>
												<option value="09">09</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
											</select>


										</div>
										<div class="col-md-3 col-xs-4">
											<select class="form-control inspectletIgnore"
												id="expiry_year1" name="expiry_year1">
												<option value="0">YYYY</option>
											</select>


										</div>
										<div class="col-md-3 col-xs-4 pull-right">
											<input type="password" name="cvv_number1" id="cvv_number1"
												class="form-control text-center inspectletIgnore"
												maxlength="4" placeholder="CVV" value="">
										</div>
									</div>

									<div class="row form-group notezeclick">
										<div class="col-md-12">
											<input type="hidden" name="issuing_bank1" id="issuing_bank1"
												value="maestro" class="form-control inspectletIgnore"
												placeholder="Enter bank name">
											<!-- 													<input type="checkbox" name="save_this_card1" id="save_this_card1" class="" value="1">  Save This Card -->
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<button id="pay_button1"
												class="btn btn-danger btn-block creditpaybutton"
												type="button"
												style="font-size: 1.2em !important; padding: 9px; font-weight: 600;"
												onclick="proceedPayment('1')" id="creditpaybutton">PAY</button>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div
							class="tab-pane fade <%="D".equals(banking_type)?"active in":""%>"
							id="DebitCard">
							<div class="clearfix"></div>
							<div class="row">
								<div class="col-md-12 card-box">
									<div class="row form-group hidden">
										<div class="col-md-12">
											<select name="card_name2" id="card_name2"
												class="form-control card_name_class">
												<option value="0">Select Card Name</option>
											</select>
										</div>
									</div>


									<div class="row form-group notezeclick card-no-input">
										<div class="col-md-12">
											<input type="text" name="card_number2" id="card_number2"
												maxlength="19" class="form-control inspectletIgnore"
												placeholder="Enter card number">
										</div>
									</div>
									<div class="row form-group notezeclick card-exp-cvv-detail">
										<div class="col-md-3 col-xs-4">
											<select class="form-control inspectletIgnore"
												name="expiry_month2" id="expiry_month2">
												<option value="0">MM</option>
												<option value="01">01</option>
												<option value="02">02</option>
												<option value="03">03</option>
												<option value="04">04</option>
												<option value="05">05</option>
												<option value="06">06</option>
												<option value="07">07</option>
												<option value="08">08</option>
												<option value="09">09</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
											</select>


										</div>
										<div class="col-md-3 col-xs-4">
											<select class="form-control inspectletIgnore"
												id="expiry_year2" name="expiry_year2">
												<option value="0">YYYY</option>
											</select>


										</div>
										<div class="col-md-3 col-xs-4 pull-right">
											<input type="password" name="cvv_number2" id="cvv_number2"
												class="form-control text-center inspectletIgnore"
												maxlength="4" placeholder="CVV" value="">
										</div>
									</div>

									<div class="row form-group notezeclick">
										<div class="col-md-12">
											<input type="hidden" name="issuing_bank2" id="issuing_bank2"
												value="maestro" class="form-control"
												placeholder="Enter bank name">
											<!-- 													<input type="checkbox" name="save_this_card2" id="save_this_card2" class="" value="1" > Save This Card -->
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<button id="pay_button2"
												class="btn btn-danger btn-block creditpaybutton"
												type="button"
												style="font-size: 1.2em !important; padding: 9px; font-weight: 600;"
												onclick="proceedPayment('2')">PAY</button>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div
							class="tab-pane fade <%="N".equals(banking_type)?"active in":""%>"
							id="NetBanking">
							<div class="clearfix"></div>
							<div class="row">
								<div class="col-md-12 card-box">
									<div class="row form-group bank-logos">
										<h5 style="margin: 0 15px 20px;">Popular Banks</h5>
										<div class="col-md-2 col-xs-3">
											<label class="pop-banks"> <input type="radio"
												name="fb" value="530" class="netbankingclass" /> <img
												src="images/logo/sbi.png" class="img-responsive">
											</label>
										</div>
										<div class="col-md-2 col-xs-3">
											<label class="pop-banks"> <input type="radio"
												name="fb" value="10" class="netbankingclass" /> <img
												src="images/logo/icici.png" class="img-responsive">
											</label>
										</div>
										<div class="col-md-2 col-xs-3">
											<label class="pop-banks"> <input type="radio"
												name="fb" value="300" class="netbankingclass" /> <img
												src="images/logo/hdfc.png" class="img-responsive">
											</label>
										</div>
										<div class="col-md-2 col-xs-3">
											<label class="pop-banks"> <input type="radio"
												name="fb" value="50" class="netbankingclass" /> <img
												src="images/logo/axis.png" class="img-responsive">
											</label>
										</div>
										<div class="col-md-2 col-xs-3">
											<label class="pop-banks"> <input type="radio"
												name="fb" value="910" class="netbankingclass" /> <img
												src="images/logo/kotak.png" class="img-responsive">
											</label>
										</div>
										<div class="col-md-2 col-xs-3">
											<label class="pop-banks"> <input type="radio"
												name="fb" value="1220" class="netbankingclass" /> <img
												src="images/logo/pnb.png" class="img-responsive">
											</label>
										</div>
									</div>
									<div class="row form-group">
										<div class="col-md-12">
											<select name="card_name4" id="card_name4"
												class="form-control card_name_class">
												<option value="0">Select Card Name</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<button id="pay_button4" class="btn btn-danger btn-block"
												type="button"
												style="font-size: 1.2em !important; padding: 9px; font-weight: 600;"
												onclick="proceedPayment('4')">PAY</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<input type="hidden" id="hashcode" name="hashcode"
			value="<%=((String) session.getAttribute("hashcode")!=null?(String) session.getAttribute("hashcode"):"")%>" />
		<input type="hidden" id="payment_option" name="payment_option"
			value="OPTDBCRD"> <input type="hidden" id="card_type"
			name="card_type" /> <input type="hidden" value="Y" id="data_accept"
			name="data_accept" /> <input type="hidden" id="product_details"
			name="product_details"
			value="<%=((String) request.getAttribute("product_details")!=null?(String) request.getAttribute("product_details"):"")%>">
		<input type="hidden" id="product_name" name="product_name"
			value="<%=((String) request.getAttribute("product_name")!=null?(String) request.getAttribute("product_name"):"")%>" />
		<input type="hidden" id="banking_type" name="banking_type"
			value="<%=((String) request.getAttribute("banking_type")!=null?(String) request.getAttribute("banking_type"):"")%>" />
	</form>
</body>

<script src="js/json.js"></script>
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery.creditCardValidator.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

//Sort funtion
var sortSelect = function (select, attr, order) {
    if(attr === 'text'){
        if(order === 'asc'){
            $(select).html($(select).children('option').sort(function (x, y) {
                return $(x).text().toUpperCase() < $(y).text().toUpperCase() ? -1 : 1;
            }));
            $(select).get(0).selectedIndex = 0;
           // e.preventDefault();
        }// end asc
        if(order === 'desc'){
            $(select).html($(select).children('option').sort(function (y, x) {
                return $(x).text().toUpperCase() < $(y).text().toUpperCase() ? -1 : 1;
            }));
            $(select).get(0).selectedIndex = 0;
          //  e.preventDefault();
        }// end desc
    }
};	
	var $cardinput1 = $('#card_number1');
	$('#card_number1').validateCreditCard(function(result)
	{		
		//console.log(result);
		if (result.card_type != null)
		{				
			switch (result.card_type.name)
			{
				case "visa":
					$cardinput1.css('background-position', '3px -46px');
					$cardinput1.addClass('card_visa');
					$("#issuing_bank1").val("Visa");
					$('#card_name1 option[value="1060"').prop("selected", true);
					$('#card_name1').val("1060");
					setDataAcceptForCC();
					break;

				case "visa_electron":
					$cardinput1.css('background-position', '3px -96px');
					$cardinput1.addClass('card_visa_electron');
					$("#issuing_bank1").val("Visa");
					$('#card_name1 option[value="1060"').prop("selected", true);
					$('#card_name1').val("1060");
			    	setDataAcceptForCC();
					break;

				case "mastercard":
					$cardinput1.css('background-position', '3px -145px');
					$cardinput1.addClass('card_mastercard');
					$("#issuing_bank1").val("MasterCard");
					$('#card_name1 option[value="1060"').prop("selected", true);
					$('#card_name1').val("1060");
			    	setDataAcceptForCC();
					break;

				case "maestro":
					$cardinput1.css('background-position', '3px -195px');
					$cardinput1.addClass('card_maestro');
					$("#issuing_bank1").val("Maestro");
					$('#card_name1 option[value="1170"').prop("selected", true);
					$('#card_name1').val("1170");
			    	setDataAcceptForCC();
					break;

				/* case "discover":
					$cardinput1.css('background-position', '3px -245px');
					$cardinput1.addClass('card_discover'); 
					break;*/

				 case "amex":
					$cardinput1.css('background-position', '3px -294px');
					$cardinput1.addClass('card_amex');
					//$("#card_name1").val("Amex");
					$("#issuing_bank1").val("Amex");
					$('#card_name1 option[value="1070"').prop("selected", true);
					$('#card_name1').val("1070");
			    	setDataAcceptForCC();
					break; 
				 case "rupay":
					$cardinput1.css('background-position', '3px -394px');
					$cardinput1.addClass('card_amex');
					$("#issuing_bank1").val("Rupay");
					$('#card_name1 option[value="1370"').prop("selected", true);
					$('#card_name1').val("1370");
			    	setDataAcceptForCC();
					break; 
				case "invalidcard":
					$cardinput1.css('background-position', '3px -344px');
					$cardinput1.addClass('card_amex');
					break;

				default:
					$cardinput1.css('background-position', '3px 3px');
					break;					
			}
		} else {
			$cardinput1.css('background-position', '3px 3px');
		}

		

		// Check for valid card numbere - only show validation checks for invalid Luhn when length is correct so as not to confuse user as they type.
		if (result.length_valid || $cardinput1.val().length > 16)
		{
			if (result.luhn_valid) {
				$cardinput1.parent().removeClass('has-error').addClass('has-success');
				$("#card-err-msg").remove();
			} else {
				$cardinput1.parent().removeClass('has-success').addClass('has-error');
				$("#card-err-msg").remove();
				$cardinput1.parent().append('<span id="card-err-msg" class="has-error valid-card-err">Enter valid card number.</span>');
			}
		} else {
			$cardinput1.parent().removeClass('has-success').removeClass('has-error');
		}
});

	var $cardinput = $('#card_number2');
	$('#card_number2').validateCreditCard(function(result)
	{		
		//console.log(result);
		if (result.card_type != null)
		{				
			switch (result.card_type.name)
			{
				case "visa":
					$cardinput.css('background-position', '3px -46px');
					$cardinput.addClass('card_visa');
					//$("#card_name1").val("Visa");
					$("#issuing_bank2").val("Visa");
					$('#card_name2 option[value="1170"').prop("selected", true);
					$('#card_name2').val("1170");
					setDataAcceptForDC();
					break;

				case "visa_electron":
					$cardinput.css('background-position', '3px -96px');
					$cardinput.addClass('card_visa_electron');
					//$("#card_name1").val("Visa");
					$("#issuing_bank2").val("Visa");
					$('#card_name2 option[value="1170"').prop("selected", true);
					$('#card_name2').val("1170");
					setDataAcceptForDC();
					break;

				case "mastercard":
					$cardinput.css('background-position', '3px -145px');
					$cardinput.addClass('card_mastercard');
					//$("#card_name1").val("MasterCard");
					$("#issuing_bank2").val("MasterCard");
					$('#card_name2 option[value="1170"').prop("selected", true);
					$('#card_name2').val("1170");
					setDataAcceptForDC();
					break;

				case "maestro":
					$cardinput.css('background-position', '3px -195px');
					$cardinput.addClass('card_maestro');
					$("#issuing_bank2").val("Maestro");
					$('#card_name2 option[value="1170"').prop("selected", true);
					$('#card_name2').val("1170");
					setDataAcceptForDC();
					break;

				/* case "discover":
					$cardinput.css('background-position', '3px -245px');
					$cardinput.addClass('card_discover'); 
					break;*/

				 case "amex":
					$cardinput.css('background-position', '3px -294px');
					$cardinput.addClass('card_amex');
					//$("#card_name1").val("Amex");
					$("#issuing_bank2").val("Amex");
					$('#card_name2 option[value="1530"').prop("selected", true);
					$('#card_name2').val("1530");
					setDataAcceptForDC();
					break; 
				 case "rupay":
					$cardinput.css('background-position', '3px -394px');
					$cardinput.addClass('card_amex');
					$("#issuing_bank2").val("Rupay");
					$('#card_name2 option[value="1370"').prop("selected", true);
					$('#card_name2').val("1370");
					setDataAcceptForDC();
					break; 
				case "invalidcard":
					$cardinput.css('background-position', '3px -344px');
					$cardinput.addClass('card_amex');
					break;

				default:
					$cardinput.css('background-position', '3px 3px');
					break;					
			}
		} else {
			$cardinput.css('background-position', '3px 3px');
		}

		

		// Check for valid card numbere - only show validation checks for invalid Luhn when length is correct so as not to confuse user as they type.
		if (result.length_valid || $cardinput.val().length > 16)
		{
			if (result.luhn_valid) {
				$cardinput.parent().removeClass('has-error').addClass('has-success');
				$("#card-err-msg").remove();
			} else {
				$cardinput.parent().removeClass('has-success').addClass('has-error');
				$("#card-err-msg").remove();
				$cardinput.parent().append('<span id="card-err-msg" class="has-error valid-card-err">Enter valid card number.</span>');
			}
		} else {
			$cardinput.parent().removeClass('has-success').removeClass('has-error');
		}
});
	

	$('.netbankingclass').click(function(){
		//alert("Hi");
	    var radioVal = $(this).val();
		if ($(this).attr('checked')) {
			$('.bank-logos').find("label").removeAttr('style');
			$(this).parent().css({"color": "red", "border-bottom": "2px solid #d71921"});
			$('#card_name4 option[value="'+radioVal+'"').prop("selected", true);
			$('#card_name4').val(radioVal);

		}else{
	    	
	    }
	});
	$(document).ready(function() {
		  if ($(window).width() < 960) {
		     $("#card_number1").prop('type','number');
		     $("#card_number2").prop('type','number');
		     $("#card_number1").focus();
		  }
		 else {
			 $("#card_number1").prop('type','text');
			 $("#card_number2").prop('type','text');
			 $("#card_number1").focus();
		 }
		});

		$(function(){
			  
			 var jsonData;
		  	  var amount = '<%=(String) request.getAttribute("amount")%>';
			  $
						.ajax({
							url : 'paymentAction?action=getdatajsontp',
							dataType : 'jsonp',
							jsonp : false,
							jsonpCallback : 'processData',
							success : function(data) {
								jsonData = data;
								// processData method for reference
								processData(data);
								var objToFiretab3 = $("#idforfirsttimeclickeventtab3");
								getDefaultValuesCardNames(objToFiretab3);
								var objToFiretab2 = $("#idforfirsttimeclickeventtab2");
								getDefaultValuesCardNames(objToFiretab2);
								var objToFiretab1 = $("#idforfirsttimeclickeventtab1");
								getDefaultValuesCardNames(objToFiretab1);								
								sortSelect('#card_name4', 'text', 'asc');
								$("select[name=card_name4]").find('option[value="0"]').remove();
								$('#card_name4').prepend("<option selected value='0'>Select</option>");	
							},
							error : function(xhr, textStatus, errorThrown) {
								//window.location.href = "paymentErrorHandler.jsp?order_id=<%=(String) request.getAttribute("order_id")%>&order_status=failure&status_code=120E&status_message=SocketException: Connection reset&cancel_url=<%=(String) request.getAttribute("cancel_url")%>";
							}
						});

				$(".payOption").click(function() {
					getDefaultValuesCardNames($(this));
					sortSelect('#card_name4', 'text', 'asc');
					$("select[name=card_name4]").find('option[value="0"]').remove();							
					$('#card_name4').prepend("<option selected value='0'>Select</option>");
				});

				$(".card_name_class").change(
					function() {
						var id = this.id;
						if(id == 'card_name4'){
							var cardNameNetBanking = $(this).val();
							if(cardNameNetBanking == '300' || cardNameNetBanking == '50' || cardNameNetBanking == '910' || cardNameNetBanking == '10' || cardNameNetBanking == '530' || cardNameNetBanking == '1220'){
								$('.bank-logos').find("label").removeAttr('style');
								$('input:radio[name=fb][value="'+cardNameNetBanking+'"]').click();
								$('input:radio[name=fb][value="'+cardNameNetBanking+'"]').parent().css({"color": "red", "border-bottom": "2px solid #d71921"});
							}else{
								$('.netbankingclass').prop('checked', false);
								$('.bank-logos').find("label").removeAttr('style');
							}
						}
						if ($(this).find(":selected").hasClass("CCAvenue")) {
							$("#data_accept").val("Y");
							var selectedValue = $(this).find(":selected").val();
						} else {
							$("#data_accept").val("Y");							
						}
					});

				// below code for reference 

				function processData(data) {
					var paymentOptions = [];
					var creditCards = [];
					var debitCards = [];
					var netBanks = [];
					var cashCards = [];
					var mobilePayments = [];
					$.each(data, function() {
						// this.error shows if any error   	
						console.log(this.error);
						paymentOptions.push(this.payOpt);
						switch (this.payOpt) {
						case 'OPTCRDC':
							var jsonData = this.OPTCRDC;
							var obj = $.parseJSON(jsonData);
							$.each(obj, function() {
								creditCards.push(this['cardSelectionId']);
							});
							break;
						case 'OPTDBCRD':
							var jsonData = this.OPTDBCRD;
							var obj = $.parseJSON(jsonData);
							$.each(obj, function() {
								debitCards.push(this['cardSelectionId']);
							});
							break;
						case 'OPTNBK':
							var jsonData = this.OPTNBK;
							var obj = $.parseJSON(jsonData);
							$.each(obj, function() {
								netBanks.push(this['cardSelectionId']);
							});
							break;

						case 'OPTCASHC':
							var jsonData = this.OPTCASHC;
							var obj = $.parseJSON(jsonData);
							$.each(obj, function() {
								cashCards.push(this['cardSelectionId']);
							});
							break;

						case 'OPTMOBP':
							var jsonData = this.OPTMOBP;
							var obj = $.parseJSON(jsonData);
							$.each(obj, function() {
								mobilePayments.push(this['cardSelectionId']);
							});
						}

					});
				}
				function getDefaultValuesCardNames(obj) {
					$('.bank-logos').find("label").removeAttr('style');
					var cardid = obj.attr("data-value2");
					var cardNameObject;
					if (cardid == '4') {
						cardNameObject = $("#card_name4");
					} else if(cardid == '2') {
						cardNameObject = $("#card_name2");
					}else {
						cardNameObject = $("#card_name1");
					}
					cardNameObject.children().remove(); // remove old card names from old one
					cardNameObject.append("<option value='0'>Select</option>");
					if (cardid == '1') {						
						cardNameObject.append("<option value='1170'>MAESTRO Debit Card</option>");
						cardNameObject.append("<option value='1370'>Rupay Debit</option>");
					}
					//var paymentOption = $(this).val();
					var paymentOption = obj.attr("data-value1");
					$("#payment_option").val(paymentOption);
					$("#card_type").val(paymentOption.substr(3));
					//$("#card_type").val(paymentOption.replace("OPT", ""));
					$
							.each(
									jsonData,
									function(index, value) {
										//console.log(value.error);
										//console.log(value);
										if (value.payOpt == paymentOption) {
											//console.log(value[paymentOption]);
											var payOptJSONArray = $
													.parseJSON(value[paymentOption]);
											$
													.each(
															payOptJSONArray,
															function() {
																cardNameObject
																		.find(
																				"option:last")
																		.after(
																				"<option class='"+this['dataAcceptedAt']+" "+this['status']+"'  value='"+this['cardSelectionId']+"'>"
																						+ this['cardName']
																						+ "</option>");
															});
										}
									});

				}
				
			});
			



	//Numbers only
$(document).ready(function () {
//$('.debitcardelement').hide();
	  //called when key is pressed in textbox
	  $('#card_number1, #cvv_number1, card_number2, #cvv_number2, #mobile_number').keypress(function (e) {
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        return false;
	    }
	   });
	});
	
	function proceedPayment(elementNo) {

		if (elementNo == '1') {

			$("#elementNo").val("1");
			creditCardPayValidation();

		}else if (elementNo == '4') {
		
			$("#elementNo").val("4");
			netBankPayValidation();
		
		} else {
		
			$("#elementNo").val("2");
			debitCardPayValidation();
		
		}
		
		return false;

	}
	
	
	function creditCardPayValidation() {
		
		var creditCard = $("#card_number1").val();
		var cvv_number = $("#cvv_number1").val();
		var expiry_month = $("#expiry_month1").val();
		var expiry_year = $("#expiry_year1").val();
		
		var issuing_bank = $("#issuing_bank1").val();
		var creditCardName=$("#card_name1").val();
		var creditCardNameText=$("#card_name1 option:selected").text();
		var valid_card_err = $('.valid-card-err').text().trim().length;

		var isMestro = false;
		if(creditCardName == '1170'){
			isMestro = true;
			if(expiry_month=='0'){
				$("#cvv_number1").val("");
				$("#expiry_month1").val("0");
				$("#expiry_year1").val("0");
				$("#card_name1").val("1060");
			}
		}
		
		if(creditCardName == '1370' && $(window).width() < 960){
			$('.card-err-msg').remove();
			$("#card_number1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Select a different card. Rupay is not avaible on mobile devices.</span>');
			return false;
		}else{
		
			if (creditCard=='0'){
				$('#card-err-msg').remove();
				$('.card-err-msg').remove();
				$("#card_number1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter credit card number.</span>');
				return false;
			}else if (valid_card_err > 0) {
	
		        return false;
		        
		    }else if (creditCard.length < 13){
				$('#card-err-msg').remove();
				$('.card-err-msg').remove();
				$("#card_number1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter valid card number.</span>');
				return false;
			}else if (expiry_month=='0' && !isMestro){
				$('.card-err-msg').remove();
				$("#expiry_month1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Select month.</span>');
				return false;
				
			}else if(expiry_year=='0' && !isMestro){
				$('.card-err-msg').remove();
				$("#expiry_year1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Select year.</span>');
				
			}else if(cvv_number=='' && !isMestro){
				$('.card-err-msg').remove();
				$("#cvv_number1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter CVV.</span>');
				return false;
			}else if((cvv_number.length < 3 || isNaN(cvv_number)) && !isMestro){
				$('.card-err-msg').remove();
				$("#cvv_number1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter valid CVV.</span>');
				return false;
			}else if(issuing_bank==''){
				$('.card-err-msg').remove();
				$("#issuing_bank1").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter bank name.</span>');
				return false;
			}else{
				 document.getElementById('pay_button1').disabled=true;
				 $('<input />').attr('type', 'hidden')
		          .attr('name', "card_nameTextField")
		          .attr('value', creditCardNameText)
		          .appendTo('#customerData');
				 var d = new Date();
				 var n = d.getTime(); 
				 $('<input />').attr('type', 'hidden')
		          .attr('name', "cl_time")
		          .attr('value', n)
		          .appendTo('#customerData');
				 $("#customerData").submit();
			}
		}
		
	}

	function debitCardPayValidation() {
		
		var creditCard = $("#card_number2").val();
		var cvv_number = $("#cvv_number2").val();
		var expiry_month = $("#expiry_month2").val();
		var expiry_year = $("#expiry_year2").val();
		
		var issuing_bank = $("#issuing_bank2").val();
		var creditCardName=$("#card_name2").val();
		var valid_card_err = $('.valid-card-err').text().trim().length;
		var card_nameText=$("#card_name2 option:selected").text();
		var isMestro = false;
		if(creditCardName == '1170'){
			isMestro = true;
			if(expiry_month=='0'){
				$("#cvv_number2").val("");
				$("#expiry_month2").val("0");
				$("#expiry_year2").val("0");
			}
		}
		
		if(creditCardName == '1370' && $(window).width() < 960){
			$('.card-err-msg').remove();
			$("#card_number2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Select a different card. Rupay is not avaible on mobile devices.</span>');
			return false;
		}else{
		
			if (creditCard=='0'){
				$('#card-err-msg').remove();
				$('.card-err-msg').remove();
				$("#card_number2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter debit card number.</span>');
				return false;
			}else if (valid_card_err > 0) {
	
		        return false;
		        
		    }else if (creditCard.length < 13){
				$('#card-err-msg').remove();
				$('.card-err-msg').remove();
				$("#card_number2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter valid card number.</span>');
				return false;
			}else if (expiry_month=='0' && !isMestro){
				$('.card-err-msg').remove();
				$("#expiry_month2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Select month.</span>');
				return false;
				
			}else if(expiry_year=='0' && !isMestro){
				$('.card-err-msg').remove();
				$("#expiry_year2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Select year.</span>');
				
			}else if(cvv_number=='' && !isMestro){
				$('.card-err-msg').remove();
				$("#cvv_number2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter CVV.</span>');
				return false;
			}else if((cvv_number.length < 3 || isNaN(cvv_number)) && !isMestro){
				$('.card-err-msg').remove();
				$("#cvv_number2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter valid CVV.</span>');
				return false;
			}else if(issuing_bank==''){
				$('.card-err-msg').remove();
				$("#issuing_bank2").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Enter bank name.</span>');
				return false;
			}else{
				 document.getElementById('pay_button2').disabled=true;
				 $('<input />').attr('type', 'hidden')
		          .attr('name', "card_nameTextField")
		          .attr('value', card_nameText)
		          .appendTo('#customerData');
				 var d = new Date();
				 var n = d.getTime(); 
				 $('<input />').attr('type', 'hidden')
		          .attr('name', "cl_time")
		          .attr('value', n)
		          .appendTo('#customerData');
				 $("#customerData").submit();
			}
		}
		
	}
	
	function  netBankPayValidation(){
		var isChecked = $("input[name=fb]:checked").val();
		var card_name4 = $("#card_name4 option:selected").val();
		
		var issuing_bankText=$("#card_name4 option:selected").text();
		if (card_name4 !='0' ) {
			 document.getElementById('pay_button4').disabled=true;
			 $('<input />').attr('type', 'hidden')
	          .attr('name', "bank_nameText")
	          .attr('value', issuing_bankText)
	          .appendTo('#customerData');
			 var d = new Date();
			 var n = d.getTime(); 
			 $('<input />').attr('type', 'hidden')
	          .attr('name', "cl_time")
	          .attr('value', n)
	          .appendTo('#customerData');
			 $("#customerData").submit();
		}else{
			$('.card-err-msg').remove();
			$("#card_name4").parent().append('<span id="card-err-msg" class="has-error card-err-msg">Select bank name.</span>');
			return false;
			
		};
		
	}	
	
	function setDataAcceptForCC(){
		if ($("#card_name1").find(":selected").hasClass("DOWN")) {
			alert("Selected option is currently unavailable. Select another payment option or try again later.");
		}			
		if ($("#card_name1").find(":selected").hasClass("CCAvenue")) {
			$("#data_accept").val("Y");
		} else {
			$("#data_accept").val("Y");
		}
	};

	$(document).ready(function () {
	    var year = (new Date).getFullYear();
	    for(var i = 0; i < 35; i++){        
	    $("#expiry_year1").get(0).options[$("#expiry_year1").get(0).options.length] = new Option(year, year);
	        year=year+1;
	    }
	});
	
	function setDataAcceptForDC(){
		if ($("#card_name2").find(":selected").hasClass("DOWN")) {
			alert("Selected option is currently unavailable. Select another payment option or try again later.");
		}			
		if ($("#card_name2").find(":selected").hasClass("CCAvenue")) {
			$("#data_accept").val("Y");
		} else {
			$("#data_accept").val("Y");
		}
	};

	$(document).ready(function () {
	    var year = (new Date).getFullYear();
	    for(var i = 0; i < 35; i++){        
	    $("#expiry_year2").get(0).options[$("#expiry_year2").get(0).options.length] = new Option(year, year);
	        year=year+1;
	    }
	});	
	
	function analytics(url,data) {
		$.ajax
	    ({  
	        type: "POST",
	        url: url,
	        crossDomain:true,
	        dataType: 'json',
	        async: true,
	        data: data,
	        success: function() {
	        },
	    	error: function() {
			},
			complete: function() {
			}

	    });
		
	}

	function onpageload() {
		var d = new Date();
		 var n = d.getTime(); 
		 var url='<%=PaymentProperties.getAnalyticsUrl(  )%>';
		 var order_id='<%=(String) request.getAttribute("order_id")%>';
		 var page='<%=request.getRequestURI()%>';
		 analytics(url,'{"analytics":[{"timestamp":"'+n+'","order_id":"'+order_id+'","action_id":"1354","page":"'+page+'"}],"subinfo":{},"validation_info":{}}');

	};
</script>
</html>
