$(function() {
	$("#all-films-button").click(allFilms);
	$("#add-film-button").click(hideAddFilm);
	$("#delete-film-button").click(hideDeleteFilm);
	$("#update-film-button").click(hideUpdateFilm);
	$("#search-film-button").click(hideSearchFilm);
	$("#add-film-button-submit").click(addFilm);
	$("#delete-film-button-submit").click(deleteFilm);
	$("#update-film-button-submit").click(updateFilm);
	$("#search-film-button-submit").click(searchFilm);
	$("#switch-index-button").click(switchIndex);
	$("#clear-all-films-button").click(clearAllFilm);
});

// function to switch to the Restful Index
function switchIndex() {
	window.location.href = ("./")
}
// clears the result of an allfilms function call
function clearAllFilm() {
	$("#all-film-result").empty();
}
// clears and toggles the add film form
function hideAddFilm() {
	$("#add-film-result").empty();
	$("#add-film-success").empty();
	$("#add-film-boxes").toggle("slow");
}
// clears and toggles the delete film form
function hideDeleteFilm() {
	$("#delete-film-result").empty();
	$("#delete-film-success").empty();
	$("#delete-film-boxes").toggle("slow");
}
// clears and toggles the update film form
function hideUpdateFilm() {
	$("#update-film-result").empty();
	$("#update-film-success").empty();
	$("#update-film-boxes").toggle("slow");
}
// clears and toggles the search film form
function hideSearchFilm() {
	$("#search-film-result").empty();
	$("#search-film-boxes").toggle("slow");
}
// function to return all films from the database
function allFilms() {
	var resultRegion = "all-film-result";
	var format = $("#data-type").val();
	var action = formatHandler(format, resultRegion);
	var dt = datatypeHandler(format);
	$.ajax({
		url : "./AllServlet",
		data : "format=" + format,
		datatype : dt,
		success : action,
		error : function() {
			alert("Error")
		}
	});
}
// function to add a film to the database
function addFilm() {
	var resultRegion = "add-film-result";
	var info = $("#add-film-form").serialize();

	$.ajax({
		url : "./AddServlet",
		data : "format=text&" + info,
		datatype : "text",
		success : function(rawData) {
			htmlInsert(resultRegion, rawData)
		},
		error : function() {
			alert("Error")
		}
	});
}
// function to delete a film from the database
function deleteFilm() {
	var resultRegion = "delete-film-result";
	var info = $("#delete-film-form").serialize();
	$.ajax({
		url : "./DeleteServlet",
		data : "format=text&" + info,
		datatype : "text",
		success : function(rawData) {
			htmlInsert(resultRegion, rawData)
		},
		error : function() {
			alert("Error")
		}
	});
}
// function to search for a film from the database
function searchFilm() {
	var resultRegion = "search-film-result";
	var format = $("#data-type-s").val();
	var info = $("#search-film-form").serialize();
	var action = formatHandler(format, resultRegion);
	var dt = datatypeHandler(format);
	$.ajax({
		url : "./SearchServlet",
		data : "format=" + format + "&" + info,
		datatype : dt,
		success : action,
		error : function() {
			alert("Error")
		}
	});
}
// function to update a film in the database
function updateFilm() {
	var resultRegion = "update-film-result";
	var info = $("#update-film-form").serialize();
	$.ajax({
		url : "./UpdateServlet",
		data : "format=text&" + info,
		datatype : "text",
		success : function(rawData) {
			htmlInsert(resultRegion, rawData)
		},
		error : function() {
			alert("Error")
		}
	});
}
// function to check the format and assign the correct handler
function formatHandler(format, resultRegion) {
	var handler;
	if (format == 'json') {
		handler = function(rawData) {
			$("#" + resultRegion).createTable(rawData);
		}
	} else if (format == 'xml') {
		handler = function(rawData) {
			showXmlFilmInfo(rawData, resultRegion);
		}
	} else {
		handler = function(rawData) {
			showStringFilmInfo(rawData, resultRegion);
		}
	}
	return handler;
}
// function to check the format and assign the correct datatype for the ajax
// call
function datatypeHandler(format) {
	var dt;
	if (format == 'json') {
		dt = 'json';
	} else if (format == 'xml') {
		dt = 'xml';
	} else {
		dt = 'text';
	}
	return dt;
}