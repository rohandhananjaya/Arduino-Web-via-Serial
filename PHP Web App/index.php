<!DOCTYPE html>
<html>
<head>
<title>Real Time Weather Station</title>
<!-- For-Mobile-Apps -->
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Real Time Weather Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //For-Mobile-Apps -->
<!-- Style -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!-- Style -->
<!-- JavaScript -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- Javascript -->

<script>
	function getData(){
		$.ajax({
		   url: 'call.php',
		   dataType: 'json'
		}).done(
		   function(data){
			 var time_st = data[0];
			 var temp_val = data[1];
			 var hume_val = data[2];
			 document.getElementById('time_stamp').innerHTML =time_st ;
			 document.getElementById('temp_data').innerHTML =temp_val ;
			 document.getElementById('hume_data').innerHTML =hume_val ;
		   }
	);
	}
	function real_time () {           
		setTimeout(function () { 
		getData();            
		real_time();
		}, 500)
	}
</script>

</head>
<body onload="real_time()">

<h1>REAL TIME WEATHER STATION</h1>

<div class="container">

    <div class="city">
        Rathnapura, Sri Lanka.
    </div>

	 <div class="temp">
		<p id="temp_data">0.00</p>
	 </div>
	  
	 <div class="hume">
		<p id="hume_data">0.00</p>
	  </div>
	  
    <div class="clear">
	</div>
	
	<div class="city">
		<div id="time_stamp">-----------</div>
    </div>
	
    </div>
             
</div>

<div class="footer">
     <p>Copyright &copy; 2019 Real Time Weather Station. All Rights Reserved | Design by <a href="http://techsayura.com">techsayura.com</a></p>
</div>

</body>
</html>