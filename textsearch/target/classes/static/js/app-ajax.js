
		function ajax() {
		$.ajax({
			url: 'ajax',
			type: 'POST',
			data: {
				userName: $('#userName').val()
			},
			success: function (responseText) {
				$('#ajaxGetUserServletResponse').text(responseText);
			}
		});
		}

