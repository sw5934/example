<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>    

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js">
</script>

<script id="reply-list-template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-rno={{rno}}>
<i class="fa fa-comments bg-blue"></i>
 <div class="timeline-item" >
  <span class="time">
    <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
	 <a class="btn btn-primary btn-xs" id="modifyReplyBtn"
	    data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">Modify</a>
  </span>
  <h3 class="timeline-header"><strong style="display:none;">{{rno}}</strong>{{replyer}}</h3>
  <div class="timeline-body">{{replyText}} </div>
</li>
{{/each}}	
</script>

<script>
	Handlebars.registerHelper("prettifyDate",function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth()+1;
		var date = dateObj.getDate();
		return year+"/"+month+"/"+date;
	});

	var printDATA=function(replyArr, target, templateObject){
		var template = Handlebars.compile(templateObject.html());
		var html = template(replyArr);
		$('.replyLi').remove();
		target.after(html);
	};

	var replyPage = 1;

	/* ajax를 메소드형식으로 바꿈 */
	function getPage(pageInfo){
		$.getJSON(pageInfo,function(data){
			printDATA(data.replyList, $('#repliesDiv'), $('#reply-list-template'));	
		    printPaging(data.pageMaker,$('.pagination'));
		});				
	}

	getPage("<%=request.getContextPath() %>/replies/${board.bno}/"+replyPage);
	
	var printPaging=function(pageMaker, target){
		var str="";
		if(pageMaker.prev){
			str +="<li class='page-item'><a class = 'page-link' href='"+(pageMaker.startPage-1)+"'><< </a></li>";
		}
		for(var i=pageMaker.startPage, len=pageMaker.endPage; i<=len; i++){
			var strClass = pageMaker.cri.page==i ?'active':'';
			str +="<li  class='page-item" + strClass+"'><a class = 'page-link' href='"+i+"'>"+i+"</a></li>";			
		}
		if(pageMaker.next){
			str +="<li class='page-item'><a class = 'page-link' href='"+(pageMaker.endPage+1)+"'>>> </a></li>";
		}
		target.html(str);
	}
	
	$('.pagination').on('click','li a',function(event){
		event.preventDefault();
		replyPage=$(this).attr("href");
		getPage("<%=request.getContextPath()%>/replies/${board.bno}/"+replyPage);
	});	
	
	$('#replyAddBtn').on('click',function(e){
		var replyer=$('#newReplyWriter').val();
		var replyText=$('#newReplyText').val();
		
		if(replyText==""){
			alert('댓글의 내용은 필수입니다.');
			$('#newReplyText').focus().css("border-color","red").attr("placeholder","내용은 필수입니다!");
			return;
		}
		
		var data={
				"bno":"${board.bno}",
				"replyer":replyer,
				"replyText":replyText
		}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/replies",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json",
			dataType:"text",
			success:function(data){
				if(data=="SUCCESS"){
					alert('댓글이 등록되었습니다.');
					getPage("<%=request.getContextPath()%>/replies/${board.bno}/"+replyPage);
					$('#newReplyText').val("");
				}else{
					alert('댓글 등록이 취소되었습니다.');
				}		
			},
			error:function(error){
				alert('서버 내부 오류로 인하여 댓글 등록에 실패하였습니다.');
			}
		});
	});
	
	$('ul.timeline').on('click','#modifyReplyBtn',function(event){
		var replyer = $(event.target).attr("data-replyer");
		if(replyer!="${loginUser.id}"){
			alert("타인의 댓글은 수정할 수 없습니다.");
			$(this).attr("data-toggle","");
		}
	});
	
	$('.timeline').on('click','.replyLi',function(event){
		var reply=$(this);
		$('#replyText').val(reply.find('.timeline-body').text());
		$('.modal-title').html(reply.attr('data-rno'));
	});
	
	$('#replyModBtn').on('click',function(event){
		var rno=$('.modal-title').html();
		var replyText=$('#replyText').val();
		
		var sendData={
				rno:rno,
				replyText:replyText
		}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/replies/"+rno,
			type:"put",
			data:JSON.stringify(sendData),
			headers:{
				"Content-type":"application/json",
				"X-HTTP-Method-Override":"put"				
			},
			success:function(result){
				if(result=="SUCCESS"){
					alert("수정되었습니다.");
					getPage("<%=request.getContextPath()%>/replies/${board.bno}/"+replyPage);
				}
			},
			error:function(error){
				alert("댓글 수정에 실패했습니다.");
			},
			complete:function(){
				$('#modifyModal').modal('hide');
			}
		});
	});
	
	$('#replyDelBtn').on('click',function(event){
		var rno=$('.modal-title').html();
		
		var sendData={
				rno:rno,
		}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/replies/"+rno,
			type:"delete",
			data:JSON.stringify(sendData),
			headers:{
				"Content-type":"application/json",
				"X-HTTP-Method-Override":"delete"				
			},
			success:function(result){
				if(result=="SUCCESS"){
					alert("삭제되었습니다.");
					getPage("<%=request.getContextPath()%>/replies/${board.bno}/"+replyPage);
				}
			},
			error:function(error){
				alert("댓글 삭제에 실패했습니다.");
			},
			complete:function(){
				$('#modifyModal').modal('hide');
			}
		});
	});
</script>