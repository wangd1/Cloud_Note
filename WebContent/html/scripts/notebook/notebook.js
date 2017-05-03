//加载笔记本列表
function loadNoteBook(){
	// 发送AJAX请求,加载笔记本内容
	$.ajax({
		url:"/Cloud_Note/notebook/loadNoteBook.do",
		type:"post",
		dataType:"json",
		data:{"cn_user_id":user_id},
		success:function(result){
			if(result.status==0){
				// 获取笔记本json对象数组
				var books=result.data;
				// 遍历books,生成笔记本列表
				// 遍历之前,清除原有笔记本列表
				$("#contacts-list").empty();
				for(var i=0;i<books.length;i++){
					// 获得笔记本ID
					var bookid=books[i].cn_notebook_id;
					// 生成li
					var li="<li class='online'>"+
								"<a>"+
									"<i class='fa fa-book' title='online' rel='tooltip-bottom'>"+
									"</i> "+books[i].cn_notebook_name+"<button type='button' class='btn btn-default btn-xs btn_position btn_delete'><i class='fa fa-times'></i></button>"+
								"</a>"+
							"</li>";
					var $li=$(li);// 将li转成jQuery对象
					$li.data("bookid",bookid);// 将bookid绑定到$li中
					$("#contacts-list").append($li);
				}
			}
		},
		error:function(){
			alert("查询笔记本异常");
		}
	})
}
//加载笔记列表
function loadNoteList(){
	//显示全部笔记和编辑笔记
	$("#pc_part_4").hide();
	$("#pc_part_5").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	//隐藏搜索结果和预览笔记
	$("#pc_part_2").show();
	$("#pc_part_3").show();
	//加载笔记本前,将之前的笔记内容和标题清除
	um.setContent("");
	$("#input_note_title").val("");
	// 将当前选中的笔记本设置为选中状态
	$(this).siblings().find("a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 获取请求参数bookid,即当前选中的笔记本的ID
	var bookid=$(this).data("bookid");
	/*alert(bookid);*/
	$.ajax({
		url:"/Cloud_Note/note/loadNotes.do",
		data:{"bookid":bookid},
		dataType:"json",
		type:"post",
		success:function(result){
			$("#note-list").empty();
			if(result.status==0){
				// 获取笔记对象数组
				var notes=result.data;
				for(var i=0;i<notes.length;i++){
					var li="<li class='online'>"+
						"<a>"+
							"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> "+notes[i].cn_note_title+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>"+
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
					$li.data("noteid",notes[i].cn_note_id);// 将noteid绑定到$li中
					$("#note-list").append($li);
				}
			}
		},
		error:function(){
			alert("查询笔记异常");
		}
	});
}
//添加一条笔记本
function sureAddNoteBook(){
	//获取请求参数
	var bookname=$("#input_notebook").val();
	$.ajax({
		url:"/Cloud_Note/notebook/add.do",
		type:"post",
		dataType:"json",
		data:{"bookname":bookname,"userid":user_id},
		success:function(result){
			if(result.status==0){
				var bookid=result.data;
				// 生成li
				var li="<li class='online'>"+
						"<a>"+
							"<i class='fa fa-book' title='online' rel='tooltip-bottom'>"+
							"</i> "+bookname+"<button type='button' class='btn btn-default btn-xs btn_position btn_delete'><i class='fa fa-times'></i></button>"+
						"</a>"+
					"</li>";
				var $li=$(li);// 将li转成jQuery对象
				$li.data("bookid",bookid);// 将bookid绑定到$li中
				$("#contacts-list").append($li);
			}
			//关闭对话框
			close();
			alert("添加成功");
			$("#contacts-list li a").removeClass("checked");
			$("#contacts-list li:last a").addClass("checked");
		},
		error:function(){
			alert("添加笔记本异常");
		}
	});
}
//笔记本重命名
function sureRename(){
	var bookid=$("#contacts-list li a.checked").parent().data("bookid");
	var newName=$("#input_notebook_rename").val();
	$.ajax({
		url:"/Cloud_Note/notebook/rename.do",
		data:{"notebookName":newName,"notebookId":bookid},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//更新成功!修改原有笔记本标题
				var $i="<i class='fa fa-book' title='online' rel='tooltip-bottom'></i> "+newName;
				$("#contacts-list li a.checked").html($i);
			}
			//关闭窗口
			close();
			//弹出提示信息
			alert(result.msg);
		},
		error:function(){
			alert("笔记本重命名异常");
		}
	});
}
function delNoteBook(bookid){
	var $li=$("#contacts-list li a.checked").parent();
	$.ajax({
		url:"/Cloud_Note/notebook/delNoteBook.do",
		type:"post",
		data:{"bookid":bookid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$li.remove();
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除笔记本异常,请稍后重试");
		}
	})
}
function sureDelNoteBook(){
	//如果笔记本中有笔记,则不能删除
	//获取笔记本ID
	var $li=$("#contacts-list li a.checked").parent();
	var bookid=$li.data("bookid");
	$.ajax({
		url:"/Cloud_Note/note/loadNotes.do",
		type:"post",
		data:{"bookid":bookid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//查询到笔记,不能删除笔记本
				alert("请先删除笔记,再删除笔记本");
			}else if(result.status==1){
				delNoteBook(bookid);
			}
		},
		error:function(){
			alert("查询笔记异常,请稍后重试");
		}
	});
}