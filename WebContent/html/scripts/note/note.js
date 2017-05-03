//去除HTML中的标签
function delHtmlTag(str){
	return str.replace(/<[^>]+>/g,"");
}
//加载笔记内容
function loadNoteBody(){
	$(this).find("a").addClass("checked");
	$(this).siblings().find("a").removeClass("checked");
	var noteid=$(this).data("noteid");
	//清空笔记内容和标题
	$("#noput_note_title").text("");
	$("#noput_note_body").text("");
	um.setContent("");
	$("#input_note_title").val("");
	$.ajax({
		url:"/Cloud_Note/note/loadBody.do",
		type:"post",
		data:{"noteid":noteid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#input_note_title").val(result.data.cn_note_title);
//				$("#myEditor").setContent(result.data.cn_note_body);
				um.setContent(result.data.cn_note_body);
				$("#noput_note_title").text(result.data.cn_note_title);
				$("#noput_note_body").html(result.data.cn_note_body);
			}
		},
		error:function(){
			alert("加载笔记异常");
		}
	});
}
/*//加载搜索之后笔记内容
function loadSearchNoteBody(){
	$(this).find("a").addClass("checked");
	$(this).siblings().find("a").removeClass("checked");
	var noteid=$(this).data("noteid");
	//清空笔记内容和标题
	$("#noput_note_title").text("");
	$("#noput_note_body").text("");
	$.ajax({
		url:"/Cloud_Note/note/loadBody.do",
		type:"post",
		data:{"noteid":noteid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#noput_note_title").text(result.data.cn_note_title);
				$("#noput_note_body").text(delHtmlTag(result.data.cn_note_body));
			}
		},
		error:function(){
			alert("加载笔记异常");
		}
	});
}
//加载回收站笔记中的内容
function loadDisableNoteBody(){
	$(this).find("a").addClass("checked");
	$(this).siblings().find("a").removeClass("checked");
	var noteid=$(this).data("noteid");
	//清空笔记内容和标题
	$("#noput_note_title").text("");
	$("#noput_note_body").text("");
	$.ajax({
		url:"/Cloud_Note/note/loadBody.do",
		type:"post",
		data:{"noteid":noteid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#noput_note_title").text(result.data.cn_note_title);
				$("#noput_note_body").text(delHtmlTag(result.data.cn_note_body));
			}
		},
		error:function(){
			alert("加载笔记异常");
		}
	});
}*/
//确认创建笔记
function sureAddNote(){
	//获取请求数据
	var bookid=$("#contacts-list li a.checked").parent().data("bookid");
	var userid=user_id;
	var title=$("#input_note").val();
//	alert(userid);
//	alert(title);
	//发送ajax请求
	$.ajax({
		url:"/Cloud_Note/note/add.do",
		data:{"bookid":bookid,"userid":userid,"title":title},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var li="<li class='online'>"+
					"<a>"+
						"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> "+title+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>"+
					"</a>"+
					"<div class='note_menu' tabindex='-1'>"+
						"<dl>"+
							"<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>"+
							"<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>"+
							"<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>"+
						"</dl>"+
					"</div>"+
					"</li>";
				var $li=$(li);// 将li转成jQuery对象
				$li.data("noteid",result.data);// 将noteid绑定到$li中
				$("#note-list").append($li);
			}
			//关闭对话框
			close();
			alert("添加成功!");
			//将新建的笔记设置为选中状态
			$("#note-list li a").removeClass("checked");
			$("#note-list li:last a").addClass("checked");
			$("#input_note_title").val(title);
		},
		error:function(){
			alert("添加笔记异常,请稍后重试");
		}
	});
}
function saveNote(){
	var noteid=$("#note-list li a.checked").parent().data("noteid");
	var title=$("#input_note_title").val();
	var body=um.getContent();
	if(noteid!=null){
		$.ajax({
			url:"/Cloud_Note/note/update.do",
			type:"post",
			dataType:"json",
			data:{"noteid":noteid,"title":title,"body":body},
			success:function(result){
				if(result.status==0){
					var newtitle="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> "+title+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					$("#note-list li a.checked").html(newtitle);
					//更新成功
					alert("保存成功");
				}
			},
			error:function(){
				alert("保存笔记异常,请稍后重试");
			}
		})
	}else{
		alert("请选择笔记");
	}
}
function showNoteMenu(e){
	/* */
	$(this).parent().addClass("checked");
	$(this).parent().parent().siblings().find("a").removeClass("checked");
	//显示当前div按钮
	$(this).parent().next().toggle(400);
	//停止冒泡
	e.stopPropagation();
	//隐藏其他的div按钮
	$(this).parent().parent().siblings().find("div").hide(); 
	/*
	var $li=$(this).parents("li");
	var $menu=$li.find("div");
	if($menu.is(":hidden")){
		$menu.show();
	}else{
		$menu.hide();
	}*/
}
//更新笔记状态,即回收笔记
function updateStatus(){
	var $li=$(this).parents("li");//获取当前li
	var noteid=$li.data("noteid");//获取当前noteID
	$.ajax({
		url:"/Cloud_Note/note/updateStatus.do",
		data:{"noteid":noteid},
		dataType:"json",
		type:"post",
		async:true,
		success:function(result){
			if(result.status==0){
				$li.remove();
				//删除笔记之后,将笔记内容和标题清除
				um.setContent("");
				$("#input_note_title").val("");
				alert(result.msg);
			}
		},
		error:function(){
			alert("回收笔记异常");
		}
	});
}
//移动笔记
function sureMove(){
	var bookid=$("#moveSelect option:selected").val();
	var $li=$("#note-list li a.checked").parent();
	var noteid=$li.data("noteid");
	$.ajax({
		url:"/Cloud_Note/note/moveNote.do",
		data:{"noteid":noteid,"bookid":bookid},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$li.remove();
				//移动笔记之后,将笔记内容和标题清除
				um.setContent("");
				$("#input_note_title").val("");
				alert(result.msg);
			}
		},
		error:function(){
			alert("移动笔记异常,请稍后重试");
		}
	});
}
//显示回收站笔记
function disableNotes(){
	//隐藏全部笔记和编辑笔记
	$("#pc_part_2").hide();
	$("#pc_part_3").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	//显示回收站
	$("#pc_part_4").show();
	$("#pc_part_5").show();
	$.ajax({
		url:"/Cloud_Note/note/loadDisableNotes.do",
		type:"post",
		data:{"userid":getCookie("cn_user_id")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes=result.data;
				$("#disable-list").empty();
				for(var i=0;i<notes.length;i++){
					var li="<li class='disable'><a ><i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> "+notes[i].cn_note_title+"<button type='button' class='btn btn-default btn-xs btn_position btn_delete'><i class='fa fa-times'></i></button><button type='button' class='btn btn-default btn-xs btn_position_2 btn_replay'><i class='fa fa-reply'></i></button></a></li>";
					var $li=$(li);// 将li转成jQuery对象
					$li.data("noteid",notes[i].cn_note_id);// 将noteid绑定到$li中
					$("#disable-list").append($li);
				}
			}
		},
		error:function(){
			alert("查询回收站笔记异常,请稍后重试");
		}
	});
}
//彻底删除笔记
function sureDeleteRollback(){
	var $li=$("#disable-list li a.checked").parent();
	var noteid=$li.data("noteid");//获取笔记ID
	$.ajax({
		url:"/Cloud_Note/note/deleteRollbackNote.do",
		type:"post",
		data:{"noteid":noteid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//彻底删除成功
				$li.remove();
				//清空笔记内容和标题
				$("#noput_note_title").text("");
				$("#noput_note_body").text("");
				alert("笔记已经彻底删除");
			}
		},
		error:function(){
			alert("删除异常,请稍后重试");
		}
	});
}
//恢复笔记
function sureReplay(){
	var bookid=$("#replaySelect option:selected").val();//获取选中的笔记本ID
	var $li=$("#disable-list li a.checked").parent();
	var noteid=$li.data("noteid");//获取要恢复的笔记ID
	$.ajax({
		url:"/Cloud_Note/note/sureReplay.do",
		type:"post",
		data:{"bookid":bookid,"noteid":noteid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//恢复成功
				$li.remove();
				//清空笔记内容和标题
				$("#noput_note_title").text("");
				$("#noput_note_body").text("");
				alert(result.msg);
			}
		},
		error:function(){
			alert("恢复笔记异常,请稍后重试");
		}
	});
}