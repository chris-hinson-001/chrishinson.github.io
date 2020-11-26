<!DOCTYPE html>
<html>
<head>
	<title>NPC Add Pet</title>
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
    <?php
    include 'dbconnection.php';
    if(isset($_POST['petname'])){ 
      $petname=$_POST['petname'];
      $petage=$_POST['petage'];
      $petspecies=$_POST['petspecies'];
      $petbreed=$_POST['petbreed'];
      $petgender=$_POST['petgender'];
      $petcolour=$_POST['petcolour'];
      $petweight=$_POST['petweight'];
      $ownername=$_POST['ownername'];
      $owneraddress=$_POST['owneraddress'];

      echo"<table>
      <tr>
      <th>Pet Name</th>
      <th>Pet Age</th>
      <th>Pet Species</th>
      <th>Pet Breed</th>
      <th>Pet Gender</th>
      <th>Pet Colour</th>
      <th>Pet Weight</th>
      <th>Owner Name</th>
      <th>Owner Address</th>          
      </tr>";
      $conn = OpenCon();
      $sql ="INSERT INTO PETS (PETID, PETNAME, OWNERNAME, OWNERADDRESS, PETSPECIES, PETAGE, PETBREED, PETGENDER, PETWEIGHT, PETCOLOUR)  VALUES(null,'".$petname."','".$ownername."','".$owneraddress."','".$petspecies."','".$petage."','".$petbreed."','".$petgender."','".$petweight."','".$petcolour."')";
      $result =$conn->query($sql);
      echo "<tr><td>" . $petname. "</td><td>" . $petage . "</td><td>"
      . $petspecies. "</td><td>" .$petbreed. "</td><td>" . 
      $petgender. "</td><td>" . $petcolour . "</td><td>"
      .$petweight. "</td><td>".$ownername. "</td><td>"
      .$owneraddress. "</td></tr>";
      echo "</table>";
      echo "<h3>Record Added</h3>";
      CloseCon($conn);
      
    } 
    ?>

    <h1>Add Pet</h1>
    <div class="container">
      <form name="Addpet" id="usrform" action="AddPet.php" method="POST">
        <div class="row">
          <div class="col-25">
            <label for="petname">Pet Name</label>
          </div>
          <div class="col-75">
            <input type="text" id="petname" name="petname" placeholder="Your pet&#39s name..">
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="petage">Pet Age</label>
          </div>
          <div class="col-75">
            <input type="number" id="petage" name="petage" placeholder="Your pet&#39s age..">
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="petspecies">Pet Species</label>
          </div>
          <div class="col-75">
            <input type="text"  id="petspecies" name="petspecies" placeholder="Your pet&#39s species..">
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="petbreed">Pet Breed</label>
          </div>
          <div class="col-75">
            <input type="text"  id="petbreed" name="petbreed" placeholder="Your pet&#39s breed..">
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="petgender">Pet Gender</label>
          </div>
          <div class="button">
            <input type="radio" class="radio" name="petgender" value="m" id="male" checked />
            <label for="male">Male</label>
          </div>
          <div class="button">
            <input type="radio" class="radio" name="petgender" value="f" id="female" />
            <label for="female">Female</label>
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="petcolour">Pet Colour</label>
          </div>
          <div class="col-75">
            <input type="text"  id="petcolour" name="petcolour" placeholder="Your pet&#39s colour..">
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="petweight">Pet Weight(kg)</label>
          </div>
          <div class="col-75">
            <input type="number" step="0.1" id="petweight" name="petweight" placeholder="Your pet&#39s weight..">
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="ownername">Owner</label>
          </div>
          <div class="col-75">
            <input type="text" id="ownername" name="ownername" placeholder="Your name..">
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="owneraddress">Address</label>
          </div>
          <div class="col-75">
            <input type="text" id="ownername" name="owneraddress" placeholder="Your address..">
          </div>
        </div>
        <div class="row">
          <input type="submit" value="Add pet">
        </div>
      </form>
    </div> 
  </table>
</body>
<footer>
  <p>Call us on 0161 123 4567</p>
</footer>
</html>