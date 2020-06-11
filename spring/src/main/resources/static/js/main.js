(function($) {

	"use strict";

	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();

	$('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
  });

})(jQuery);
/*
 * Function to request a modal fragment and to display the data. Params:
 * modalContainer = Parent div of the moda aka contains the modal modalName =
 * The modal fragment to be used. ID=? queryUrl = The link to the controller
 * that will return a fragment id = Primary Key of what to search
 */
function openEditModal(modalContainer,modalName,queryUrl,id){
	modalContainer = '#' + modalContainer;
	modalName = '#' + modalName;
	$.ajax({
			url: queryUrl+id,
			success: function(data){
				console.log(data);
				$(modalContainer).html(data);
				$(modalName).modal("show");
			}
		});
	}

/*
 * Query server for table body that is filtered (i.e. Asc Desc by lastname OR All items with Category.id = x)
 */

function filterTableView(tableId,tableBodyId,queryUrl,sortId){
	var tableElement = document.getElementById(tableId);
	var tableBody = document.getElementById(tableBodyId);
	console.log(queryUrl);
	$.ajax({
		url: queryUrl,
		data: {
			"filter": sortId
		},
		type: "GET",
		success: function(data){
			var newTable = document.createElement('template');
			newTable.innerHTML = data;
			tableElement.replaceChild(newTable.content,tableBody);
			console.log(newTable.content);
		}
	});
}
/*
 * Adds a new input for procedures in RECEIPT.HTML
 */
function addProcedureInput(){
    var newdiv = document.createElement('div');
    $.ajax({
  	  url:'/app/receipt/addprocedure',
    	  success: function(data){
    		var procedureInput = document.createElement('div');
    		procedureInput.innerHTML = data;
    		document.getElementById('procedureInputs').appendChild(procedureInput);
    		console.log(procedureInput);
    	  }
    });
}
/*
 * Allow edit of the inputs
 */
function enableEditItem(event,modalContainerName){
	var modalContainer = document.getElementById(modalContainerName);
	var modalTitle = modalContainer.querySelector("#editItemModalTitle");
	var submitBtn = modalContainer.querySelector("#saveEditBtn");
	//List of inputs and select
	var inputList = modalContainer.querySelector("#editItemModalContent")
			.querySelectorAll("input");
	var selectList = modalContainer.querySelector("#editItemModalContent")
			.querySelectorAll("select");
	var textAreaList = modalContainer.querySelector("#editItemModalContent")
	.querySelectorAll("textarea");
	console.log(inputList.length);
	console.log(selectList.length);
	console.log(textAreaList);
	//Skip textAreaList since it was just used for address
	if(textAreaList  != null){
		for (var i = 0; i < textAreaList.length; i++) {
			if (textAreaList [i].hasAttribute("disabled")) {
				textAreaList [i].removeAttribute("disabled");
			}
		}
	}
	//Allow edit on all inputs
	for (var i = 0; i < inputList.length; i++) {
		if (inputList[i].hasAttribute("disabled")) {
			inputList[i].removeAttribute("disabled");
		}
	}
	//Allow edit on select tags
	for (var i = 0; i < selectList.length; i++) {
		if (selectList[i].hasAttribute("disabled")) {
			selectList[i].removeAttribute("disabled");
		}
	}
	//Change texts
	modalTitle.innerText = "Edit Item";
	submitBtn.innerText = "Save Changes";
	//Change submit button color
	submitBtn.classList.remove("btn-warning");
	submitBtn.classList.add("btn-success");
	//Change button to submit
	if (submitBtn.type == "button") {
		submitBtn.type = "submit";
		event.preventDefault();
	}
	console.log(inputList.length);
	console.log(selectList.length);

}
/*
 * Query the price of AVAILABLE procedures to Database
 */
function queryProcedurePrice(event){
	var selectNode = event.currentTarget;
	var priceNode = selectNode.parentNode.querySelectorAll('input')[0];
	$.ajax({
		url:'/app/receipt/addprocedure/price',
		data: {
			procedureId: selectNode.options[selectNode.selectedIndex].value
		},
		type: "GET",
		success: function(data){
			priceNode.value = 'Php ' + parseFloat(data).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
			updateReceiptPrice();
		}
	});
}
/*
 * Use an Accounting library for this. Never EVER use floating points as you will lose precision which is bad for money.
 */
function updateReceiptPrice(){
	var procedureList = document.getElementById('procedureInputs').querySelectorAll('input');
	var totalCostText = document.getElementById('totalCost');
	var price = 0;
	for (var i = 0; i < procedureList.length; i++) {
		
		if(procedureList[i].value.replace('Php ', '').replace(',','') != ''){
			console.log('is not numeric : ' + !isNaN(procedureList[i].value.replace('Php ', '').replace(',','')))
			console.log('price: ' + procedureList[i].value.replace('Php ', '').replace(',',''));
			price += parseFloat(procedureList[i].value.replace('Php ', '').replace(',',''));
		}
		console.log(price);
	}
	totalCostText.value = 'Php ' + price.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
}
function addMedicine(){
    var newdiv = document.createElement('div');
    var content = `<div  style=" width: 70%;padding: 1%;">
                <label for="GenericMedicineName" class="text-dark" style="float: left;">Generic Medicine name</label>
                <input type="text" class="form-control" id="GenericMedicineName" name="genericMedicineName" placeholder="Enter Generic Medicine Name" style="float: right; width: 80%;">
              </div>
              <br>
              <br>
              <div class="col"  style=" width: 70%;padding: 1%;">
                <label for="BrandedMedicineName" class="text-dark" style="float: left;">Branded Medicine name</label>
                <input type="text" class="form-control" id="BrandedMedicineName" name="brandedMedicineName" placeholder="Enter Branded Medicine name" style="float: right; width: 80%;">
              </div>
              <br>
              <br>
              <div class="col"  style=" width: 70%;padding: 1%;">
                <label for="DosageMedicine" class="text-dark" style="float: left;">Recommend Dosage</label>
                <input type="text" class="form-control" id="DosageMedicine" name="recommendedDosage" placeholder="Enter How many intake per usee" style="float: right; width: 80%;">
              </div>
              <br>
              <br>
              <div class="col" style="width: 70%; padding: 1%;">
							<label for="Notes" class="text-dark" style="float: left;">Other
								Notes</label>
							<textarea class="form-control" id="Notes" name="medicineNotes"
								aria-label="With textarea" rows="4"
								style="width: 80%; resize: none; float: right;"></textarea>

						</div>

						<br>`
    newdiv.innerHTML = content;
    document.getElementById('addMedicine').append(newdiv);
}