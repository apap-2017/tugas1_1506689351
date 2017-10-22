$(document).ready( function () {
	$('#myTable').DataTable();
	
	$(function() {
	    $("#but1").click(function () {
	        $('#format').hide(); // toggles between show/hide
	        $('#ubah').hide();
	        $('#cari').hide();
	        $('#tentang').show();
	    });
	  });
	
	$(function() {
		document.getElementById("linkubah").onclick = function() {
		    document.getElementById("formubah").submit();
		}
	});
	
	$(function() {
		var x = document.getElementById("statmat").text();
		var z = '1';
		var y = 'Wafat';
		if(x==z){
			$('#wafatsudah').style.display = none;
		    $('#takbisaubah').style.display = none;
		}else if(x==y){
			$('#wafatsudah').style.display = none;
		    $('#takbisaubah').style.display = none;
		}
		
	});
	
	
	
});