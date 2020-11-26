<!DOCTYPE html>
<html>
<head>
  <title>NPC Contact Us</title>
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

      </nav>
    </head>
    <body>
      <?php
      include 'dbconnection.php';
      if(isset($_POST['email'])){ 
        $name=$_POST['name'];
        $email=$_POST['email'];
        $comment=$_POST['comment'];
        $conn = OpenCon();
        $sql ="INSERT INTO contacts (NAME, EMAIL, QUERY)  VALUES('".$name."','".$email."','".$comment."')";
        $result =$conn->query($sql);
        echo "<h3>Message sent!</h3>";
      } 
      ?>
      <div id="contact">
        <h1>Contact us</h1>
        <p>We&#39re here to help!</p><br>
        <p>
          We&#39re happy to answer any questions you may have about your pet
          Call us on 0161 123 4567<br>
          or <br>
        fill in the form below and we will email back as soon as we are able!</p>
      </div>
      <div class="container">
        <form name="Contact" id="usrform" action="ContactUs.php" method="POST">
          <div class="row">
            <div class="col-25">
              <label for="name">Name</label>
            </div>
            <div class="col-75">
              <input type="text" id="name" name="name" placeholder="Your name..">
            </div>
          </div>
          <div class="row">
            <div class="col-25">
              <label for="email">Email Address</label>
            </div>
            <div class="col-75">
              <input type="email" id="email"  name="email" placeholder="Your Email@somewhere.com">
            </div>
          </div>
          <div class="row">
            <div class="col-25">
              <label for="subject">Comment</label>
            </div>
            <div class="col-75">
              <textarea id="subject" name="comment" placeholder="Write comments or questions" style="height:200px"></textarea>
            </div>
          </div>
          <div class="row">
            <input type="submit" value="Submit">
          </div>
        </form>
      </div> 
    </body>
    <footer>
      <p>Call us on 0161 123 4567</p>
    </footer>
    </html>