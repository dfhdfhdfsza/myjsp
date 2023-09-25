//댓글 등록시 bno,writer,content
//추가

//댓글등록
document.getElementById('cmtAddBtn').addEventListener('click',()=>{
	const cmtText = document.getElementById('cmtText').value; //입력한 댓글을 가져온다.
	console.log(cmtText);
	if(cmtText == null || cmtText== "")//댓글 내용이 없으면 메서드 종료
	{
		alert("댓글을 입력해주세요.");
		return false;
	}
	else	//댓글 내용이 있으면 json형태로 값 저장
	{
		let cmtDate={
			bno:bnoVal,
			writer:document.getElementById('cmtWriter').value,
			content:cmtText
		};
		console.log(cmtDate);
		//전송 function 호출,값을 저장한 json 전달
		postCommentToServer(cmtDate).then(result=>{
			if(result>0)
			{
				alert("댓글 등록 성공");
			}
			else
			{
				alert("댓글 등록 실패");
			}
			printCommentList(cmtDate.bno);
		})
	}
})

async function postCommentToServer(cmtDate) //댓글 추가 function
{
	try{
		const url="/cmt/post";
		const config={	//서버로 데이터를 보낼때 config 써야됨
			method:'post',
			headers:{
				'Content-Type':'application/json; charset=utf-8'
			},
			body:JSON.stringify(cmtDate) //객체의 값을 문자열로 변환
		}
		const resp= await fetch(url,config); //fetch는 비동기 요청 요청할 url과 json데이터를 보냄,await은 비동기 작업이 완료될때까지 다른 작업을 중지한다. 
		const result=await resp.text(); //0 or 1 (isOk와 같은 역할),text는 resp 요소의 내용을 변수 result에 저장
		//resp의 값을 직접 사용 안하고 따로 저장한 후 사용하는 이유는? fetch는 객체와 주소,isOk를 반환해주므로 따로 변수를 만들어 isOk만 저장해준다.
		return result;
	}catch(error){
		consol.log(error);
	}
}

//리스트

function printCommentList(bno)
{
	getCommentListFromServer(bno).then(result=>{
		console.log(result);
		if(result.length>0)
		{
			spreadCommentList(result);
		}
		else
		{
			let div= document.getElementById('accordionExample');
			div.innerHTML=`comment가 없습니다.`;
		}
	})
}

//서버에 댓글 리스트를 달라고 요청하는 function printCommentList에서 사용
async function getCommentListFromServer(bno)
{
	try{
		const resp= await fetch('/cmt/list/'+bno);//cmt/list/151,요청을 보내고(object response)를 받는다
		const result=await resp.json(); //응답객체 json으로 변환
		return result;
	}catch(error){
		console.log(error);
	}
}

//받아온 list로 html태그를 만들어서 jsp의 댓글태그에 추가
async function spreadCommentList(result)
{
	let div=document.getElementById('accordionExample'); //댓글 최상단div
	div.innerHTML="";
	for(let i=0;i<result.length;i++)
	{
		let str=`<div class="accordion-item">`;
		str += `<h2 class="accordion-header" id="heading${i}">`;
      str += `<button class="accordion-button" type="button"`;
      str += `data-bs-toggle="collapse" data-bs-target="#collapse${i}"`;
      str += `aria-expanded="true" aria-controls="collapse${i}">`;
      str += `댓글번호 : ${result[i].cno}, 작성자 : ${result[i].writer}, 작성일 : ${result[i].regdate}`;
      str += `</button> </h2>`;
      str += `<div id="collapse${i}" class="accordion-collapse collapse show"`;
      str += `data-bs-parent="#accordionExample">`;
      str += `<div class="accordion-body">`;
      str += `<input type="text" class="form-control" id="cmtText" value="${result[i].content}">`
      str += `<button type="button" class="btn btn-outline-warning cmtModBtn" data-cno="${result[i].cno}">수정</button>`;
      str += `<button type="button" class="btn btn-outline-danger cmtDelBtn" data-cno="${result[i].cno}">삭제</button>`;
      str += `</div> </div> </div>`;
      div.innerHTML += str; //누적해서 담기
	}
	
}

document.addEventListener('click',(e)=>{
	if(e.target.classList.contains('cmtModBtn'))//detail.js
	{
		let cno=e.target.dataset.cno;//js는 DOm 생성 시점에 "data-"로 시작하는 속성들을 하나로 모아 "dataset"맵(map)으로 따로 모아서 관리한다.접근방법:dataset.속성이름
		
		//수정구현 (수정할 데이터를 객체로 생성 ->컨트롤러에서 수정 요청)
		//closest를 통해 id를 별도로 주지않고 사용
		let div=e.target.closest('div');//타겟을 기준으로 가장 가까운 div 찾기
		let cmtText=div.querySelector('#cmtText').value;
		let writer=e.target.dataset.writer;
	
		//비동기통신 함수 호출 -> 처리
		updateCommentFromServer(cno,writer,cmtText).then(result=>{
			if(result>0)
			{
				alert('댓글 수정 성공!');
				printCommentList(bnoVal);
			}
			else
			{
				alert('댓글 수정 실패');
			}
		})
	}
	if(e.target.classList.contains('cmtDelBtn'))
	{
		//삭제 구현
		try{
			let cno=e.target.dataset.cno;
			removeCommentFromServer(cno).then(result=>{
				if(result>0)
				{
					alert('댓글 삭제 성공!');
					printCommentList(bnoVal);
				}
				else
				{
					alert('댓글 삭제 실패!');
				}
			})
		}catch(error){
			console.log(error);
		}
	}
})

async function updateCommentFromServer(cnoVal,cmtwriter,cmtText)
{
	try{
		const url='/cmt/modify';
		const config={
			method:'post',
			headers:{
				'Content-Type':'application/json; charset=utf-8'
			},
			body:JSON.stringify({cno:cnoVal,writer:cmtwriter,content:cmtText})
		}
		const resp=await fetch(url,config);
		const result=await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
}

async function removeCommentFromServer(cno)
{
	try{
		const url='/cmt/remove/'+cno;
		const resp=await fetch(url,{method:'post'});//method는 안해도됨
		const result=await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
}

