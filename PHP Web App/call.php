<?php

	include "db_connect.php";
	
    mysqli_select_db($con,'weather_data') or die("Impossibile to select the database.");
	//mysqli_select_db($con,'sql12309103') or die("Impossibile to select the database.");
  $query = "SELECT * FROM `w_data`";
  $result = mysqli_query($con,$query) or die("Couldn't execute query");
  $num = mysqli_num_rows($result);
  mysqli_close($con); 

    $i = $num - 1;
 $timest = mysqli_result($result,$i,"time"); 
 $hum = mysqli_result($result,$i,"hum");
 $temp = mysqli_result($result,$i,"temp");

	function mysqli_result($res, $row, $field=0) { 
		$res->data_seek($row);
		$datarow = $res->fetch_array();
		return $datarow[$field];
	}
 
	echo json_encode(array($timest,$temp,$hum));

?>