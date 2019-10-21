<?php

	$con = mysqli_connect("localhost","root","","weather_data");
	//$con = mysqli_connect("sql12.freesqldatabase.com","sql12309103","XvdGjf921I","sql12309103");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
?>