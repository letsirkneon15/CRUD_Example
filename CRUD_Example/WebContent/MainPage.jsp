<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>HS Code Information</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css" />
<link href="css/appstyle.css" rel="stylesheet" />
</head>
<body>

<!-- Include Header.jsp -->
    <div id="header">
		<%@ include file="Header.jsp" %>
	</div>

<!-- START - Create Form for Record selection	 -->
<div class ="container">
<div class="well">
 	<div class="panel-group">
     <form action="${pageContext.request.contextPath}/main?action=formPost" method="post">
     <div class="panel-default">
     <div class="panel-body">
  		
  		<!-- Firm Code Drop Down -->
  		<div class ="col-sm-3">
  			<label for="firm">OSA Code:</label>
  			<select class="form-control" id="firmCode" name="firmCode">
			  <c:forEach var="firmCode" items="${firmCodeList}">
  			     <option value="${firmCode[0]},${firmCode[1]}">${firmCode[1]}</option>
	   		  </c:forEach>
	      	</select>
  		</div>	
  		
  		<!-- Customer Code textbox -->
  		<div class ="col-sm-3">
  			<label for="cust">Customer Code:</label>
  		 	<input class="form-control" type="text" id="custCode" name="custCode" value="${custCode}">
  		</div> 	
  		
  		<!-- Part Number textbox -->
  		<div class ="col-sm-3">
  		  	<label for="part">Part Number:</label>
  		  	<input class="form-control" type="text" id="partNum" name="partNum" value="${partNum}">
  		</div>
  		
  		<!-- Article Number textbox -->
  		<div class ="col-sm-3">
  			<label for="art">Article Number:</label>
  		  	<input class="form-control" type="text" id="article" name="article" value="${article}">
  		</div>
  	</div>
  	</div>
  	<div class="panel-default">
  	<div class="panel-body">
  		<!-- PPT Code textbox -->
  		<div class ="col-sm-3">
  			<label for="ppt">PPT Code:</label>
  			<input class="form-control" type="text" id="pptCode" name="pptCode" value="${pptCode}">
  		</div>
  		
  		<!-- HS Code textbox-->
  		<div class ="col-sm-3">
  			<label for="hsCode">HS Code:</label>
  		  	<input class="form-control" type="text" id="hsCode" name="hsCode" value="${hsCode}">
  		</div>
  		
  		<!-- Tariff Description (E) textbox -->
  		<div class ="col-sm-3">
  			<label for="tariffE">Tariff Description (E):</label>
  			<input class="form-control" type="text" id="tariffDescE" name="tariffDescE" value="${tariffDescE}">
  		</div>
  	</div>	
  	</div>
  	<div class="panel-default">
  	<div class="panel-body">	
  		<!-- Submit Button -->
  		<div class ="col-sm-5">
  			<button type="submit" class="btn btn-primary" name="button" value="display"><span class="glyphicon glyphicon-search"></span> Display</button>			
		    <!-- Trigger the modal with a button -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createNew"><span class="glyphicon glyphicon-plus"></span> Create New</button>	    
			<button type="submit" class="btn btn-primary" name="button" value="download" data-toggle="modal"><span class="glyphicon glyphicon-download"></span> Download to Excel </button>	
		</div>
  	</div>
  	</div>
    </form> 
</div>
</div>
<!-- END - Create Form for Record selection	 -->

<!-- <c:if test = "${button=='display'}"> -->
<!--  START - Table creation for HS Code Information -->
<div id="wrap">
  <div class = " container table-responsive">
  <table id="datatable" class="datatable table-striped table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%">
  <!-- <h3>HS Code Information</h3> -->
  <thead>
  <tr>  
           <th>Customer</th>
           <th>Part Number</th>
           <th>Article Number</th>
           <th>PPT</th>
           <th>H40 Code</th>
           <th>HS Code</th>
           <th>Duty</th>
           <th>Tariff Description (English)</th>
           <th>Tariff Description (Local)</th>
           <th>Custom Judgement</th> 
           <th class="sorting_asc_disabled sorting_desc_disabled"></th>
           <th class="sorting_asc_disabled sorting_desc_disabled"></th>   
  </tr>
  </thead>
  
   <tbody>
        <c:forEach var="hs" items="${hsArr}" varStatus="hsIndex">        
        <tr>
           <td>${hs.hsCust }</td>
           <td>${hs.hsPart }</td>
           <td>${hs.hsMura}</td>
           <td>${hs.hsPptc }</td>
           <td>${hs.hsH40c }</td>
           <td>${hs.hsCode }</td>
           <td>${hs.hsDuty }</td>
	       <td>${hs.hsEProdName }</td>
	       <td>${hs.hsLProdName }</td>
	       <td>${hs.hsCusJudge }</td>  	       
	       <td><a href="#editRecord" data-toggle="modal" data-id="edtLink${hsIndex.index}"
	       			data-firm="${hs.hsFirm}" data-cust="${hs.hsCust}" data-part="${hs.hsPart}"
	       			data-art="${hs.hsMura}" data-amur="${hs.hsAmur}" data-pptc="${hs.hsPptc}"
	       			data-h40c="${hs.hsH40c}" data-hcode="${hs.hsCode}" data-duty="${hs.hsDuty}"
	       			data-eprodname="${hs.hsEProdName}" data-lprodname="${hs.hsLProdName}" 
	       			data-cusjudge="${hs.hsCusJudge}"><img src='images/edit.jpg' height=15 width=15></a>
	       </td> 	
	       <%-- <td><a href="" id="dltLink${hsIndex.index}"><img src='images/delete.jpg' height=15 width=15></a> --%>
	       <td><a href="#deleteRecord" data-toggle="modal" data-id="dltLink${hsIndex.index}"
	       			data-firm="${hs.hsFirm}" data-cust="${hs.hsCust}" data-part="${hs.hsPart}"
	       			data-art="${hs.hsMura}" data-pptc="${hs.hsPptc}" data-h40c="${hs.hsH40c}" 
	       			data-hcode="${hs.hsCode}"><img src='images/delete.jpg' height=15 width=15></a>
	       </td>           
        </tr> 
        </c:forEach>
   </tbody>
  </table>
  </div>
 </div>   
<!--  END - Table creation for HS Code Information -->
<!-- </c:if> -->

<c:if test = "${button=='download'}">
<!--START- Download to Excel (MODAL)-->
<div class="modal fade show in" id="downloadToExcel" role="dialog">
  <div class="modal-dialog" style="width: 50%">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><b>HS Code - Download to Excel File</b></h4>
        </div>
	
		<!-- Create a Form to download record -->
		<form action="${pageContext.request.contextPath}/main?action=downloadRec" method="post">
         <div class="modal-body bgColorWhite">
         	<p> The excel file will be created in C:/Users/Public with filename = HS_Code_Download.xlsx </p>
         </div>
         <input class="form-control" name="dldFirm" id="dldFirm" type="hidden" value="${firmCode}">
         <input class="form-control" name="dldCust" id="dldCust" type="hidden" value="${custCode}">
         <input class="form-control" name="dldPartNum" id="dldPartNum" type="hidden" value="${partNum}">
         <input class="form-control" name="dldArt" id="dldArt" type="hidden" value="${article}">
         <input class="form-control" name="dldPptCode" id="dldPptCode" type="hidden" value="${pptCode}">
         <input class="form-control" name="dldHsCode" id="dldHsCode" type="hidden" value="${hsCode}">
         <input class="form-control" name="dldTariffE" id="dldTariffE" type="hidden" value="${tariffDescE}">
         <div class="modal-footer">
  		 <!-- Submit Button -->
     	 <button type="submit" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-ok"></span> OK </button>
        </div>
        </form>
	  </div>		     
  </div>
</div>	 
<!--END- Download to Excel (MODAL)-->
</c:if>

<!--START- Create New Record (MODAL)-->
  <div class="modal fade" id="createNew" role="dialog">
    <div class="modal-dialog" style="width: 80%">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><b>HS Code - Create a New Record</b></h4>
        </div>
         <form action="${pageContext.request.contextPath}/main?action=createRec" method="post">
         <div class="modal-body">
         <!-- <div class="alert alert-success">
         	<strong>Success!</strong> This alert box could indicate a successful or positive action.
  		 </div> -->
         <div class="table-responsive" style="width:100%">
         <div class="well">
         		<div class="panel-default">
  				<div class="panel-body">	
     				<!-- Firm Code Drop Down -->
  					<div class ="col-sm-4">
  						<label for="firm">OSA Code:</label>
  						<select class="form-control" id="crtFirmCode" name="crtFirmCode">
			  			<c:forEach var="crtFirmCode" items="${firmCodeList}">
  			     			<option value="${crtFirmCode[0]},${crtFirmCode[1]}">${crtFirmCode[1]}</option>
	   		  			</c:forEach>
	      				</select>
			  		</div>	
 					<!-- Customer Code  -->
 					<div class ="col-sm-4">
 						<label for="cust">Customer Code:</label>
     					<input class="form-control" id="crtCust" name="crtCust" type="text" value="${crtCust}">
     				</div>	
     				<!-- Part Number -->
     				<div class ="col-sm-4">
     					<label for="partNum">Part Number:</label>
     					<input class="form-control" id="crtPartNum" name="crtPartNum" type="text" value="${crtPartNum}">   
     				</div> 
     			</div>
     			</div>	
     			<div class="panel-default">
  				<div class="panel-body">	 
     				<!-- Article Number  -->
     				<div class ="col-sm-4">
     					<label for="art">Article Number:</label>
     					<input class="form-control" id="crtArt" name="crtArt" type="text" value="${crtArt}">
     				</div>
     				<!-- Article Number without plus  -->
 					<div class ="col-sm-4">
 						<label for="art2">Article Number without Plus:</label>
     					<input class="form-control" id="crtArtWithoutPlus" name="crtArtWithoutPlus" type="text" value="${crtArtWithoutPlus}">
     				</div>
     				<!-- PPT Code -->
     				<div class ="col-sm-4">
     					<label for="pptc">PPT Code:</label>
     					<input class="form-control" id="crtPptCode" name="crtPptCode" type="text" value="${crtPptCode}">   
     				</div>  
     			</div>
     			</div>	
 				<div class="panel-default">
  				<div class="panel-body">
 					<!-- H40 Code  -->
     				<div class ="col-sm-4">
     					<label for="h40c">H40 Code:</label>
     					<input class="form-control" id="crtH40Code" name="crtH40Code" type="text" value="${crtH40Code}">
     				</div>	
 					<!-- HS Code  -->
 					<div class ="col-sm-4">
 						<label for="hsCode">HS Code:</label>
     					<input class="form-control" id="crtHsCode" name="crtHsCode" type="text" value="${crtHsCode}">
     				</div>
     				<!-- HS Duty -->
     				<div class ="col-sm-4">
     					<label for="duty">Duty:</label>
     					<input class="form-control" id="crtDuty" name="crtDuty" type="text" value="${crtDuty}">   
     				</div>  			
     			</div>
     			</div>
     			<div class="panel-default">
  				<div class="panel-body">
  					<!-- Tariff Description (English) -->
		     		<div class ="col-sm-4">
     					<label for="tariffDescEnglish">Tariff Description (E):</label>
     					<input class="form-control" id="crtTariffEnglish" name="crtTariffEnglish" type="text" value="${crtTariffEnglish}">
     				</div>	
 					<!-- Tariff Description (Local) -->
 					<div class ="col-sm-4">
 						<label for="tariffDescLocal">Tariff Description (L):</label>
     					<input class="form-control" lang="th" id="crtTariffLocal" name="crtTariffLocal" type="text" value="${crtTariffLocal}">
     				</div>
     				<!-- Custom Judgement -->
		     		<div class ="col-sm-4">
     					<label for="cusJudge">Custom Judgement:</label>
     					<input class="form-control" name = "crtCusJudge" id="crtCusJudge" type="text" value="${crtCusJudge}">   
     				</div>  
  				</div>
  				</div>
  		</div>
     	</div>
		</div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-save"></span> Save </button>			
          <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-eye-close"></span> Close</button>
        </div>
        </form>
      </div>
    </div>
  </div>    
</div>
<!--END- Create New Record (MODAL)-->

<!--START- Edit Record (MODAL)-->
<div class="modal fade" id="editRecord" role="dialog">
  <div class="modal-dialog" style="width: 80%">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><b>HS Code - Update Record</b></h4>
        </div>
	
		<!-- Create a Form to edit record -->
		<form action="${pageContext.request.contextPath}/main?action=editRec" method="post">
         <div class="modal-body bgColorWhite">
         	<div class="table-responsive" style="width:100%">
         	 <div class="well">
         		<div class="panel-default">
     			<div class="panel-body">
     				<!-- Firm Code  -->
     				<div class ="col-sm-4">
     					<label for="firm">Firm Code:</label>
     					<input class="form-control" name = "edtFirm" id="edtFirm" type="text" value="" readonly>
     				</div>	
 					<!-- Customer Code  -->
 					<div class ="col-sm-4">
 						<label for="cust">Customer Code:</label>
     					<input class="form-control" name = "edtCust" id="edtCust" type="text" value="" readonly>
     				</div>
     				<!-- Part Number -->
     				<div class ="col-sm-4">
     					<label for="partNum">Part Number:</label>
     					<input class="form-control" name="edtPartNum" id="edtPartNum" type="text" value="" readonly>   
     				</div>  			
     			</div>
  	 			</div>
  	 			<div class="panel-default">
     			<div class="panel-body">
     				<!-- Article Number  -->
     				<div class ="col-sm-4">
     					<label for="art">Article Number:</label>
     					<input class="form-control" name="edtArt" id="edtArt" type="text" value="" readonly>
     				</div>	
 					<!-- Article Number without plus  -->
 					<div class ="col-sm-4">
 						<label for="art2">Article Number without Plus:</label>
     					<input class="form-control" name="edtArtWithoutPlus" id="edtArtWithoutPlus" type="text" value="" readonly>
     				</div>
     				<!-- PPT Code -->
     				<div class ="col-sm-4">
     					<label for="pptc">PPT Code:</label>
     					<input class="form-control" name="edtPptCode" id="edtPptCode" type="text" value="" readonly>   
     				</div>  			
     			</div>
  	 			</div>
  	 			<div class="panel-default">
     			<div class="panel-body">
     				<!-- H40 Code  -->
     				<div class ="col-sm-4">
     					<label for="h40c">H40 Code:</label>
     					<input class="form-control" name="edtH40Code" id="edtH40Code" type="text" value="" readonly>
     				</div>	
		 			<!-- HS Code  -->
 					<div class ="col-sm-4">
 						<label for="hsCode">HS Code:</label>
     					<input class="form-control" name="edtHsCode" id="edtHsCode" type="text" value="">
     				</div>
     				<!-- HS Duty -->
     				<div class ="col-sm-4">
     					<label for="duty">Duty:</label>
     					<input class="form-control" name = "edtDuty" id="edtDuty" type="text" value="">   
     				</div>  			
     			</div>
  	 			</div>
  	 			<div class="panel-default">
     			<div class="panel-body">
     				<!-- Tariff Description (English) -->
     				<div class ="col-sm-4">
     					<label for="tariffDescEnglish">Tariff Description (E):</label>
     					<input class="form-control" id="edtTariffDescEnglish" name="edtTariffDescEnglish" type="text" value="">
     				</div>	
 					<!-- Tariff Description (Local) -->
 					<div class ="col-sm-4">
 						<label for="tariffDescLocal">Tariff Description (L):</label>
     					<input class="form-control" lang="th" id="edtTariffDescLocal" name="edtTariffDescLocal" type="text" value="">
     				</div>
     				<!-- Custom Judgement -->
     				<div class ="col-sm-4">
     					<label for="cusJudge">Custom Judgement:</label>
     					<input class="form-control" name = "edtCusJudge" id="edtCusJudge" type="text" value="">   
     				</div>  			
     			</div>
  	 			</div> 		
  	 			</div>		
        	</div>
        	</div>
        	<div class="modal-footer">
  					<!-- Submit Button -->
         			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Update</button>
					<button type="button" class="btn btn-primary" name="cancel" onclick="display()"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-eye-close"></span> Close</button>
        	</div>
        </form>
	  </div>		     
  </div>
</div>	 
<!--END- Edit Record (MODAL)-->

<!--START- Delete (MODAL)-->
<div class="modal fade" id="deleteRecord" role="dialog">
  <div class="modal-dialog" style="width: 50%">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><b>HS Code - Delete Record</b></h4>
        </div>
	
		<!-- Create a Form to edit record -->
		<form action="${pageContext.request.contextPath}/main?action=dltRec" method="post">
         <div class="modal-body bgColorWhite">
         	<p> Are you sure you wish to delete one record? </p>
         </div>
         <input class="form-control" name="dltFirm" id="dltFirm" type="hidden" value="">
         <input class="form-control" name="dltCust" id="dltCust" type="hidden" value="">
         <input class="form-control" name="dltPartNum" id="dltPartNum" type="hidden" value="">
         <input class="form-control" name="dltArt" id="dltArt" type="hidden" value="">
         <input class="form-control" name="dltPptCode" id="dltPptCode" type="hidden" value="">
         <input class="form-control" name="dltH40Code" id="dltH40Code" type="hidden" value="">
         <input class="form-control" name="dltHsCode" id="dltHsCode" type="hidden" value="">
         <div class="modal-footer">
  					<!-- Submit Button -->
         			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Delete</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-eye-close"></span> Close</button>
        </div>
        </form>
	  </div>		     
  </div>
</div>	 
<!--END- Delete Record (MODAL)-->

<!-- Java Script function for Pagination -->
<script type="text/javascript">

$(document).ready(function() {
	$('.datatable').dataTable({
		"sPaginationType": "bs_full"
	});	

	$('.datatable').each(function(){
		var datatable = $(this);
		// SEARCH - Add the placeholder for Search and Turn this into in-line form control
		var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
		search_input.attr('placeholder', 'Search');
		search_input.addClass('form-control input-sm');
		// LENGTH - Inline-Form control
		var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
		length_sel.addClass('form-control input-sm');

		var info = datatable.page.info();
		alert(info.page+1);
	});
});

$('#editRecord').on('show.bs.modal', function(e) {
    var hsId = $(e.relatedTarget).data('id');
    var hsFirm = $(e.relatedTarget).data('firm');
    var hsCust = $(e.relatedTarget).data('cust');
    var hsPart = $(e.relatedTarget).data('part');
    var hsMura = $(e.relatedTarget).data('art');
    var hsAmur = $(e.relatedTarget).data('amur');
    var hsPptc = $(e.relatedTarget).data('pptc');
    var hsH40c = $(e.relatedTarget).data('h40c');
    var hsCode = $(e.relatedTarget).data('hcode');
    var hsDuty = $(e.relatedTarget).data('duty');
    var hsEProdName = $(e.relatedTarget).data('eprodname');
    var hsLProdName = $(e.relatedTarget).data('lprodname');
    var hsCusJudge=$(e.relatedTarget).data('cusjudge'); 

    $(e.currentTarget).find('input[name="edtFirm"]').val(hsFirm);
    $(e.currentTarget).find('input[name="edtCust"]').val(hsCust);
    $(e.currentTarget).find('input[name="edtPartNum"]').val(hsPart);
    $(e.currentTarget).find('input[name="edtArt"]').val(hsMura);
    $(e.currentTarget).find('input[name="edtArtWithoutPlus"]').val(hsAmur);
    $(e.currentTarget).find('input[name="edtPptCode"]').val(hsPptc);
    $(e.currentTarget).find('input[name="edtH40Code"]').val(hsH40c);
    $(e.currentTarget).find('input[name="edtHsCode"]').val(hsCode);
    $(e.currentTarget).find('input[name="edtDuty"]').val(hsDuty);
    $(e.currentTarget).find('input[name="edtTariffDescEnglish"]').val(hsEProdName);
    $(e.currentTarget).find('input[name="edtTariffDescLocal"]').val(hsLProdName);
    $(e.currentTarget).find('input[name="edtCusJudge"]').val(hsCusJudge);
});

$('#deleteRecord').on('show.bs.modal', function(e) {
    var hsId = $(e.relatedTarget).data('id');
    var hsFirm = $(e.relatedTarget).data('firm');
    var hsCust = $(e.relatedTarget).data('cust');
    var hsPart = $(e.relatedTarget).data('part');
    var hsMura = $(e.relatedTarget).data('art');
    var hsPptc = $(e.relatedTarget).data('pptc');
    var hsH40c = $(e.relatedTarget).data('h40c');
    var hsCode = $(e.relatedTarget).data('hcode');

    $(e.currentTarget).find('input[name="dltFirm"]').val(hsFirm);
    $(e.currentTarget).find('input[name="dltCust"]').val(hsCust);
    $(e.currentTarget).find('input[name="dltPartNum"]').val(hsPart);
    $(e.currentTarget).find('input[name="dltArt"]').val(hsMura);
    $(e.currentTarget).find('input[name="dltPptCode"]').val(hsPptc);
    $(e.currentTarget).find('input[name="dltH40Code"]').val(hsH40c);
    $(e.currentTarget).find('input[name="dltHsCode"]').val(hsCode);
});

$(function(){
	  $("#crtArt").keyup(function(){
	    $("#crtArtWithoutPlus").val($(this).val().replace(/\+.*/,''));
	  });

	  $("#crtTariffEnglish").keyup(function(){
		  $("#crtTariffLocal").val($(this).val());
		  $("#crtTariffLocal").toLocaleString("th");
	  });
});
 
</script>

</body>
</html>