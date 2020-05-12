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