//关闭对话框
function close(){
	//隐藏背景色
	$(".opacity_bg").hide();
	//清空对话框
	$("#can").empty();
}
//弹出对话框
function alertWindowNoteBook(){
	//弹出对话框,并加载到id=can的div中
	$('#can').load('/Cloud_Note/html/alert/alert_notebook.html');
	//弹出背景色
	$(".opacity_bg").show();
}
function alertWindowNote(){
	//检测是否选中笔记本,必须选中笔记本才可以创建笔记
	var $a=$("#contacts-list li a.checked");//获取选中的li
	if($a.length==1){
		//弹出对话框,并加载到id=can的div中
		$('#can').load('/Cloud_Note/html/alert/alert_note.html');
		//弹出背景色
		$(".opacity_bg").show();
	}else{
		alert("未选中笔记本");
	}
}
//弹出笔记本重命名对话框
function alertWindowNoteBookRename(){
	//弹出对话框,并加载到id=can的div中
	$('#can').load('/Cloud_Note/html/alert/alert_rename.html');
	//弹出背景色
	$(".opacity_bg").show();
}
function alertMove(){
	//获取笔记本列表
	var books=$("#contacts-list li");
	//弹出对话框,并加载到id=can的div中
	$('#can').load('/Cloud_Note/html/alert/alert_move.html',function(){
		for(var i=0;i<books.length;i++){
			//获取笔记本名字添加到select中
			var book=$(books[i]).find("a").text().trim();
			var bookid=$(books[i]).data("bookid");
			var options="<option value="+bookid+">"+book+"</option>";
			$("#moveSelect").append(options);
		}
	});
	//弹出背景色
	$(".opacity_bg").show();
}
function alertDelRollbackWindow(){
	//弹出对话框,并加载到id=can的div中
	$('#can').load('/Cloud_Note/html/alert/alert_delete_rollback.html');
	//弹出背景色
	$(".opacity_bg").show();
}
function alertReplayWindow(){
	//获取笔记本列表
	var books=$("#contacts-list li");
	//弹出对话框,并加载到id=can的div中
	$('#can').load('/Cloud_Note/html/alert/alert_replay.html',function(){
		for(var i=0;i<books.length;i++){
			//获取笔记本名字添加到select中
			var book=$(books[i]).find("a").text().trim();
			var bookid=$(books[i]).data("bookid");
			var options="<option value="+bookid+">"+book+"</option>";
			$("#replaySelect").append(options);
		}
	});
	//弹出背景色
	$(".opacity_bg").show();
}
function alertDelNoteBookWindow(){
	//弹出对话框,并加载到id=can的div中
	$('#can').load('/Cloud_Note/html/alert/alert_delete_notebook.html');
	//弹出背景色
	$(".opacity_bg").show();
}