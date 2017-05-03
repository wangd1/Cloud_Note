//分享笔记 
function shareNote(noteid){
	$.ajax({
		url:"/Cloud_Note/note/shareNote.do",
		data:{"noteid":noteid},
		type:"post",
		dateType:"json",
		success:function(result){
			if(result.status==0){
				alert("分享成功");
			}
		},
		error:function(){
			alert("分享笔记异常,请稍后重试");
		}
	})
}
//验证笔记是否分享，如果没有分享，就插入一条分享，如果分享了，就更新分享
function sureShare(){
	//获取当前笔记ID
	var noteid=$("#note-list li a.checked").parent().data("noteid");
	//发送ajax请求验证笔记是否已经分享
	$.ajax({
		url:"/Cloud_Note/note/updateShare.do",
		data:{"noteid":noteid},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==1){
				//分享过，更新分享
				alert(result.msg);
			}else if(result.status==0){
				//未分享过
				//分享笔记,向分享表中插入一条笔记
				shareNote(noteid);
			}
		},
		error:function(){
			alert("分享笔记异常,请稍后重试");
		}
	});
}
function searchShareNote(event){
	if(event.which==13){
		//隐藏全部笔记和编辑笔记
		$("#pc_part_2").hide();
		$("#pc_part_3").hide();
		$("#pc_part_4").hide();
		$("#pc_part_7").hide();
		//显示搜索结果和预览笔记
		$("#pc_part_5").show();
		$("#pc_part_6").show();
		//并清除笔记标题和内容
		$("#noput_note_title").text("");
		$("#noput_note_body").text("");
		//执行查询操作
		$.ajax({
			url:"/Cloud_Note/note/searchNote.do",
			type:"post",
			data:{"title":$(this).val().trim()},
			dataType:"json",
			success:function(result){
				// 获取笔记对象数组
				var notes=result.data;
				$("#search-list").empty();
				for(var i=0;i<notes.length;i++){
					var li="<li class='online'>"+
						"<a>"+
							"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i> "+notes[i].cn_share_title+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down btn_star'><i class='fa fa-star'></i></button>"+
						"</a>"+
					"</li>";
					var $li=$(li);// 将li转成jQuery对象
					$li.data("noteid",notes[i].cn_note_id);// 将noteid绑定到$li中
					$("#search-list").append($li);
				}
			},
			error:function(){
				alert("查询笔记异常,请稍后重试");
			}
		});
	}
}