<!DOCTYPE html>
<html>
<head>
	<title>NPC Appointment list</title>
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

    </div>
    <h1>All Appointments</h1>

    <table>
        <tr>
            <th>Appointment ID</th>
            <th>Appointment Date</th>
            <th>Pet ID</th>
            <th>Doctor ID</th>
        </tr>
        <?php
        include 'dbconnection.php';
        $conn = OpenCon();
        $sql = "SELECT APPOINTMENTID, APPTDATE, PETID, DOCTORID FROM appointments";
        $result = $conn->query($sql);
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                echo "<tr><td>" . $row["APPOINTMENTID"]. "</td><td>" . $row["APPTDATE"] . "</td><td>"
                . $row["PETID"]. "</td><td>" . $row["DOCTORID"] . "</td></tr>";
            }
            echo "</table>";
        } else { echo "0 results"; }
        CloseCon($conn);
        ?>
    </table>
</body>
<footer>
    <p>Call us on 0161 123 4567</p>
</footer>
</html>