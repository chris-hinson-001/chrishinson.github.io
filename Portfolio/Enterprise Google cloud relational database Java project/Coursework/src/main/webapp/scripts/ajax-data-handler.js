//This function handles the xml format tables

function showXmlFilmInfo(xmlDocument, resultRegion) {
	var films = xmlDocument.getElementsByTagName("film");
	var rows = new Array(films.length);
	var subElementNames = [ "id", "title", "year", "director", "stars",
			"review" ];
	for (var i = 0; i < films.length; i++) {
		rows[i] = getElementValues(films[i], subElementNames);
	}
	var table = getFilmTable(rows);
	htmlInsert(resultRegion, table);
}

// This function handles the plain text format tables

function showStringFilmInfo(rawData, resultRegion) {
	var str = rawData.substring(1);
	str = str.substring(0, str.length - 1);
	// this splits the data on rows
	var rows = str.split("#,");
	var filmRows = new Array(rows.length - 1)
	for (var i = 0; i < rows.length; i++) {
		// this splits the data into the cells
		var cells = rows[i].split("|");
		filmRows[i] = cells;
	}
	var table = getFilmTable(filmRows);
	htmlInsert(resultRegion, table);
}
