(function($) {

	$.fn.createTable = function(data, options) {

		var element = this;
		var settings = $.extend({}, $.fn.createTable.defaults, options);
		var selector;

		if (element[0].className !== undefined) {
			var split = element[0].className.split(' ');
			selector = '.' + split.join('.') + ' ';
		} else if (element[0].id !== undefined) {
			selector = '#' + element[0].id + ' ';
		}

		var table = '<table class="json-to-table">';

		table += '<thead><th class="jsl"></th>';
		table += $.fn.createTable.parseTableData(data, true);
		table += '</thead>';
		table += '<tbody>';
		table += $.fn.createTable.parseTableData(data, false);
		table += '</tbody>';
		table += '</table>';

		element.html(table);

	};

	$.fn.createTable.getHighestColumnCount = function(data) {

		var count = 0, temp = 0, column = {
			max : 0,
			when : 0
		};

		for (var i = 0; i < data.length; i++) {
			count = $.fn.getObjectLength(data[i]);
			if (temp <= count) {
				temp = count;
				column.max = count;
				column.when = i;
			}
		}

		return column;
	};

	$.fn.createTable.parseTableData = function(data, thead) {

		var row = '';

		for (var i = 0; i < data.length; i++) {
			if (thead === false)
				row += '<tr><td class="jsl">' + (i + 1) + '</td>';
			$.each(data[i],
					function(key, value) {
						if (thead === true) {
							if (i === $.fn.createTable
									.getHighestColumnCount(data).when) {
								row += '<th>' + $.fn.humanize(key) + '</th>';
							}
						} else if (thead === false) {
							row += '<td>' + value + '</td>';
						}
					});
			if (thead === false)
				row += '</tr>';
		}

		return row;
	};

	$.fn.getObjectLength = function(object) {

		var length = 0;

		for ( var key in object) {
			if (object.hasOwnProperty(key)) {
				++length;
			}
		}

		return length;
	};

	$.fn.humanize = function(text) {

		var string = text.split('_');

		for (i = 0; i < string.length; i++) {
			string[i] = string[i].charAt(0).toUpperCase() + string[i].slice(1);
		}

		return string.join(' ');
	};

}(jQuery));