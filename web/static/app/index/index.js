$(function () {
	var Index = function () {
	};
	Index.prototype = {

		init: function () {
			this.bind();
		},
		bind: function () {
			$(document).off('click', '.delete-user')
					.on('click', '.delete-user', function () {
						var _this = this;
						var userId = $(this).data('user-id');
						var d1 = $.ajax({
							type: 'POST',
							url: appPath + '/userDelete',
							data: JSON.stringify([userId]),
							dataType: 'json'
						});

						$.when(d1)
								.done(function (data) {
									if (data.code == 200) {
										$(_this).parents('li').hide(500);
									}
								})
								.fail(function (err) {
									console.log(err);
								});
					});
		}
	};

	var index = new Index();
	index.init();
});