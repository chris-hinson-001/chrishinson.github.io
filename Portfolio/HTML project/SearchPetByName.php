<!DOCTYPE html>
<html>
<head>
	<title>NPC Pet Name Search</title>
	<link href="Style.css" rel="stylesheet" type="text/css">
	<nav>
		<div id="logo">
			<a href="Index.html">
				<img src="logo_cropped2.png" alt="Logo">
			</a></div>
			<label for="drop" class="toggle">Menu</label>
			<input type="checkbox" id="drop" />
			<ul class="menu">
				<li> <a href="Index.html">Home</a></li>
				<li> <a href="Gallery.html">Gallery</a></li>
				<li>
					<label for="drop-1" class="toggle">Database +</label>
					<a href="">Database</a>
					<input type="checkbox" id="drop-1"/>
					<ul>
						<li> <a href="Appointment.php">Appointment DB</a></li>
						<li> <a href="Doctors.php">Doctors DB</a></li>
						<li> <a href="Pets.php">Pets DB</a></li>
						<li> <a href="SearchPetByName.php">Search Pets</a></li>
						<li> <a href="AddPet.php">Add Pets</a></li>
					</ul> 

				</li>
				<li><a href="AboutUs.html">About Us</a></li>
				<li><a href="ContactUs.php">Contact Us</a></li>
			</ul>
		</nav>
	</head>
	<body>	


		<h1>Search for Pet by Name</h1>

		<div class="container">
			<form name="Contact" id="usrform" action="SearchPetByName.php" method="POST">
				<div class="row">
					<div class="col-25">
						<label for="petname">Pet Name</label>
					</div>
					<div class="col-75">
						<input type="text" id="petname" name="petname" placeholder="Your pet&#39s name..">
					</div>
				</div>
				<div class="row">
					<input type="submit" value="Submit">
				</div>				
			</form>
		</div>

		<?php 
		include 'dbconnection.php';
		if(isset($_POST['petname'])){ 
			$petname=$_POST['petname'];
		//$petname= preg_filter("#[^0-9a-z]#i", "", $petname);

			$conn = OpenCon();
			$sql = "SELECT PETID,PETNAME, OWNERNAME, PETSPECIES,PETAGE FROM PETS WHERE  PETNAME LIKE '%" . $petname . "%'";
			$result = $conn->query($sql);
			if ($result->num_rows > 0) {
				echo"<table>
				<tr>
				<th>Pet ID</th>
				<th>Pet Name</th>
				<th>Owner Name</th>
				<th>Pet Species</th>
				<th>Pet Age</th>          
				</tr>";    
				while($row = $result->fetch_assoc()) {
					echo "<tr><td>" . $row["PETID"]. "</td><td>" . $row["PETNAME"] . "</td><td>"
					. $row["OWNERNAME"]. "</td><td>" .  
					$row["PETSPECIES"]. "</td><td>" . $row["PETAGE"] . "</td></tr>";
				}
				echo "</table>";
			} else { echo "0 results"; }
			CloseCon($conn);
			
		} 
		?>

	</body>
	<footer>
		<p>Call us on 0161 123 4567</p>
	</footer>
	</html>