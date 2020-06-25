function GolesController(opcion) {
	$("#msg").hide();
	$("#msg").removeClass("alert-success").addClass("alert-danger");
	
	switch(opcion){
	case "list":
		$.ajax({
			type : "post",
			url : "/goles/list",
			success : function(res) {
				result="<table name='golesTable' id='golesTable' class='table-striped'><thead>"
				result+="<tr><th>Jugador RUT</th><th>Cantidad de Goles</th></tr>";
				result+="</thead><tbody>"
				$.each(res, function(k,v) {
					result+="<tr>";
					result+="<td>"+v.jug_rut+"</td>";
					result+="<td>"+v.gol_cantidad+"</td>";
					result+="</tr>";
				});
				result+="</tbody></table>";
				$("#modalTable").html(result);
				$("#golesTable tr").click(function() {
				$("#jug_rut").val($(this).find("td:eq(0)").text());
				$("#gol_cantidad").val($(this).find("td:eq(1)").text());
				$("#myModal .close").click()
	        });
			$("#myModal").modal({show:true});
			
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("Error en busqueda de goles")
			}
		});       			
		break;
	case "get":
		$.ajax({
			type : "post",
			url : "/goles/get",
			data : "jug_rut=" + $("#jug_rut").val(),
			success : function(res) {
				if (res == "") {
					$("#msg").show();
					$("#msg").html("No se encontro el registro");
				} else {
					$("#jug_rut").val(res.jug_rut);
					$("#gol_cantidad").val(res.gol_cantidad);
				}
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("Error en la busqueda de goles");
				}
			});		
		break;
	case "insert":
		var json = 
			{
				'jug_rut': $("#jug_rut").val(),
				'gol_cantidad': $("#gol_cantidad").val()
			}
	
	    var postData = JSON.stringify(json);

	    $.ajax({
			type : "post",
			url : "/goles/insert",
			data : postData,
			contentType : "application/json; charset=utf-8",
	        dataType : "json",
			success : function(res) {
				if (res == 1) {
					$("#msg").removeClass("alert-danger").addClass("alert-success");
					$("#msg").show();
					$("#msg").html("Registro ingresado correctamente.");
				} else {
					$("#msg").show();
					$("#msg").html("No se pudo ingresar el registro.");
				}
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("No se pudo ingresar el registro.");
			}
		});       	
	    break;
	case "update":
		var json = 
			{
				'jug_rut': $("#jug_rut").val(),
				'gol_cantidad': $("#gol_cantidad").val()
			}

	    var postData = JSON.stringify(json);

		$.ajax({
			type : "post",
			url : "/goles/update",
			data : postData,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(res) {
				if (res == 1) {
					$("#msg").removeClass("alert-danger").addClass("alert-success");
					$("#msg").show();
					$("#msg").html("Registro modificado correctamente.");
				} else {
					$("#msg").show();
					$("#msg").html("No se pudo modificar el registro.");
				}
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("No se pudo modificar el registro.");
			}
		});       	
    break;
	case "delete":
		$.ajax({
			type : "post",
			url : "/goles/delete",
			data : "jug_rut="+$("#jug_rut").val(),
			success : function(res) {
				if (res == 1) {
					$("#msg").removeClass("alert-danger").addClass("alert-success");
					$("#msg").show();
					$("#msg").html("Registro eliminado correctamente.");
				} else {
					$("#msg").show();
					$("#msg").html("No se pudo eliminar el registro.");
				}
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("No se pudo eliminar el registro.");
			}
		});
		break;
	default:
		$("#msg").show();
		$("#msg").html("Opción incorrecta.");
	}
}