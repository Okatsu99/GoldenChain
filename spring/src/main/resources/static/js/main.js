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
 * Function to request a modal fragment and to display the data.
 * Params:
 * modalContainer = Parent div of the moda aka contains the modal
 * modalName = The modal fragment to be used. ID=?
 * queryUrl = The link to the controller that will return a fragment
 * id = Primary Key of what to search
 */
function openEditModal(modalContainer,modalName,queryUrl,id){
	modalContainer = '#' + modalContainer;
	modalName = '#' + modalName;
	$.ajax({
			url: queryUrl + id,
			success: function(data){
				console.log(data);
				$(modalContainer).html(data);
				$(modalName).modal("show");
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
    		console.log(data);
    		newdiv.innerHTML = data;
    		document.getElementById('additionalProcedures').append(newdiv);
    	  }
    });
}

function updateProcedureInputPriceField(event){
	 alert(event.target.id);
}
/*
 * 
 */
function addMedicine(){
    var newdiv = document.createElement('div');
    var content = `<div class="col"  style=" width: 70%;padding: 1%;">
                <label for="GenericMedicineName" class="text-dark" style="float: left;">Generic Medicine name</label>
                <input type="text" class="form-control" id="GenericMedicineName" placeholder="Enter Generic Medicine Name" style="float: right; width: 80%;">
              </div>
              <br>
              <br>
              <div class="col"  style=" width: 70%;padding: 1%;">
                <label for="BrandedMedicineName" class="text-dark" style="float: left;">Branded Medicine name</label>
                <input type="text" class="form-control" id="BrandedMedicineName" placeholder="Enter Branded Medicine name" style="float: right; width: 80%;">
              </div>
              <br>
              <br>
              <div class="col"  style=" width: 70%;padding: 1%;">
                <label for="DosageMedicine" class="text-dark" style="float: left;">Recommend Dosage</label>
                <input type="text" class="form-control" id="DosageMedicine" placeholder="Enter How many intake per usee" style="float: right; width: 80%;">
              </div>
              <br>
              <br>`
    newdiv.innerHTML = content;
    document.getElementById('addMedicine').append(newdiv);
}