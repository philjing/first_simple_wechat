function init(){
	$("#history_table").empty();
	checkinit();
}

function checkinit(){
	if(historyDetails===null){
		$("#history_table").append("服务器出现异常");
	}else{
		initcontent(historyDetails);
	}
}

function initcontent(obj){	
	var trs = '';
	$.each(obj,function(n,value){
		trs += '<tr><td>'+value+'</td></tr>';		
	});
	$("#history_table").append(trs);
}