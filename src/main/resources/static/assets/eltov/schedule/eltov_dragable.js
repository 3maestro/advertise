/**
 * 
 */
function dragUseInSch(){
//	    const ulFrameList = document.querySelector('#frameList');
//	var dragged;
	const ulFrameList = document.getElementById('frameList');
	const divFolderList = document.getElementById('folderList');
//	const dropZoneList = document.getElementsByClassName("frameDropZone card-body");
    addEventInit(ulFrameList, divFolderList);
}

function addEventInit(frameListElement, folderListElement){
	const dragFrames = frameListElement.getElementsByClassName('frames');
	const dropZones = folderListElement.getElementsByClassName('dropZone');
	
    var dragFramesLength = dragFrames.length;
    var dropZonesLength = dropZones.length;
  
	if (dragFramesLength > 0) {
		for (i = 0; i < dragFramesLength; i++) {
			var frame = dragFrames[i];
//			console.log(frame);
			frame.draggable = true;
			// add drag event : 광고 컨텐츠를 drag 할 수 있다.
			frame.addEventListener("drag", function(e) {frameDragEvent(e, this);}, false);
			// add drag start : 광고 컨텐츠를 drag 하기 시작하면 해당 frame의 데이터를 담고 투명도를 설정 후 타겟 객체를 리턴.
			frame.addEventListener("dragstart", function(e) {frameDragStartEvent(e, this);}, false);
			// add drag end : 광고 컨텐츠의 drag가 끝날때 투명도를 원위치 시키고 drop zone에 데이터를 넘겨준다.
			frame.addEventListener("dragend", function(e) {frameDragEndEvent(e, this);}, false);
		}
	}
  
	if (dropZonesLength > 0) {
		for (i = 0; i < dropZonesLength; i++) {
			var dropZone = dropZones[i];
			console.log(dropZone); 
			// add drag over : 광고 컨텐츠가 drop 대상에서 이벤트 발생 허용하도록 prevetDefault() 호출
			dropZone.addEventListener("dragover", function(e) {folderDragOverEvent(e, this);}, false);
			// add drag enter : 광고 컨텐츠가 drop 하려는 대상 위로 드래그했을 때 
			dropZone.addEventListener("dragenter", function(e) {folderDragEnterEvent(e, this);}, false);
			// add drag leave : 광고 컨텐츠가 drop 하려던 대상으로부터 벗어났을 때
			dropZone.addEventListener("dragleave", function(e) {folderDragLeaveEvent(e, this);}, false);
			// add drop event : 광고 컨텐츠가 drop 할 수 있다.
			dropZone.addEventListener("drop", function(e) {folderDropEvent(e, this);}, false);
		}
	}
	
	test();
}

function frameDragEvent(e, frame){}

function frameDragStartEvent(e, frame){
	var target = e.target;
	target.style.opacity = 0.5;
	
	// target의 데이터를 setting
	var data = {
		frameId: target.id,
		frameName: target.dataset.name,
		framePeriod: target.dataset.period,
		frameTime: target.dataset.time
	}
	e.dataTransfer.setData("text/plain", JSON.stringify(data));
}

function frameDragEndEvent(e, frame){
	var target = e.target;
	target.style.opacity = 1;
	// target의 데이터를 getting
}

function folderDragOverEvent(e, dropZone){
	e.preventDefault();
}

function folderDragEnterEvent(e, dropZone){
	var target = e.target;
	target.style.background = '#ffedff';
}

function folderDragLeaveEvent(e, dropZone){
	var target = e.target;
	target.style.background = '';
}

function folderDropEvent(e, dropZone){
	e.preventDefault();
//	console.log(e.currentTarget);
//	if(e.target == dropZone){
		// drop 된 데이터 추출
		const jsonObj = e.dataTransfer.getData("text/plain");
		const data = JSON.parse(jsonObj);
		
		// drop 된 frame 생성
		var element = makingElement(data);
		dropZone.appendChild(element);
		
		// 서버로 전송 할 데이터 취합
//	}
	
}

function makingElement(data){
	
	var div = document.createElement('div');
	div.className = 'col-md-3 folderContext ui-sortable-handle ui-droppable ondrag="contextDrag(e);" ondragstart="contextDragStart(e);" draggable="true"';
	div.id = data.frameId;
	div.name = data.frameName;
	div.period = data.framePeriod;
	div.time = data.frameTime;
	var div1 = document.createElement('div');
	var numberSpan = document.createElement('span');
	var contextSpan = document.createElement('span');
	var contextNameSpan = document.createElement('span');
	var contextTimeSpan = document.createElement('span');
	var removeBtnSpan = document.createElement('span');
	var removeIconSpan = document.createElement('span');
	var br = document.createElement('br');
	numberSpan.className = 'number number_sort';
	numberSpan.innerText = '1';
	contextSpan.className = 'folderContextName';
	contextSpan.style = 'width:75%;';
	contextNameSpan.innerText = data.frameName;
	contextTimeSpan.innerText = '재생시간 : ' + data.frameTime + 'sec';
	removeBtnSpan.className = 'remove';
	removeBtnSpan.onclick = 'javascript:onClickListDelete(this);';
	removeIconSpan.className = 'ion-android-close';
	
	contextSpan.appendChild(contextNameSpan);
	contextSpan.appendChild(br);
	contextSpan.appendChild(contextTimeSpan);
	removeBtnSpan.appendChild(removeIconSpan);
	
	div1.appendChild(numberSpan);
	div1.appendChild(contextSpan);
	div1.appendChild(removeBtnSpan);
	div.appendChild(div1);
	
	return div;
//	<div class="col-md-3 folderContext ui-sortable-handle ui-droppable" id="id_info_1_1" data-type="NO" add-type="NO" frame_id="94" sch_id="1" con_name="강남구청 4편_1920x1080 30초">
//		<div>
//			<span class="number number_sort">1</span>
//			<span class="folderContextName" style="width: 75%;">
//				<span>강남구청 4편_1920x1080 30초ffffffffffffffffffffffffffffffffffffff</span>
//				<br/>
//				<span>재생시간 : 30sec</span>
//			</span>
//			<span class="remove" onclick="javascript:onClickListDelete(this);">
//				<span class="ion-android-close"></span>
//			</span>
//		</div>
//	</div>
}
// 폴더 내에서 컨텐츠를 드래그 시작 할때 -> 컨텐츠의 데이터 및 인덱스 값 setting
function contextDragStart(e){
//	const jsonObj = e.dataTransfer.getData("text/plain");
//	const data = JSON.parse(jsonObj);
	var target = e.target;
	console.log(target);
	var data = {
		index: 1,
		frameId: target.id,
		frameName: target.dataset.name,
		framePeriod: target.dataset.period,
		frameTime: target.dataset.time
	}
	
	console.log(data);
	e.dataTransfer.setData("text/plain", JSON.stringify(data));
}

// 폴더 내에서 컨텐츠를 드래그 할때 -> 순서 변경
function contextDrag(e){
	e.preventDefault();
	var target = e.target;
//	console.log(target);
}

///////////////////////////////
//drag & drop  Util
///////////////////////////////
//drag & drop 유효성 검사
//function isValid(target, standard){
//	
//	console.log(target);
//	console.log(standard);
//	
//	// drag or drop 할 수 있는 영역인가? 
//	if(target == standard){
//		return target;
//	}else{
//		return false;
//	}
//}

function test(){
	const dropZones = document.getElementsByClassName('dropZone');
	var dropLength = dropZones.lenght;
	for(var i=0; i<dropLength; i++){
		var dropZone = dropZones[i];
		$dropZone.sortable({
			zIndex: 9999,
			cursor: "grab",
			connectWith: "#accordion",
			receive: function(event, ui){
				var order = $(this).sortable();
				console.log(order);
			},
//			items: "> li",
			opacity: 0.5,
			scroll: false
		}).disableSelection();
//		var connectWith = $( "#folderList" ).sortable( "option", "connectWith" );
//		console.log(connectWith);
//		$("#folderList").disableSelection();
	}
}
//$("#folderList").sortable({
//	zIndex: 9999,
//	cursor: "grab",
//	connectWith: "#accordion",
//	receive: function(event, ui){
//		var order = $(this).sortable();
//		console.log(order);
//	},
//	items: "> li",
//	opacity: 0.5,
//	scroll: false
//}).disableSelection();
//var connectWith = $( "#folderList" ).sortable( "option", "connectWith" );
//console.log(connectWith);
//$("#folderList").disableSelection();





// 드래그 가능한 대상에서 이벤트 발생 
//document.addEventListener("drag", function(e) {
//	
//}, false);

/*
// 드래그 시작
document.addEventListener("dragstart", function(e) {
	// 드래그한 요소에 대한 참조 변수
	dragged = event.target;
	// 요소를 반투명하게 함
	event.target.style.opacity = 0.5;
}, false);

// 드래그 종료
document.addEventListener("dragend", function(e) {
	// 투명도를 리셋
	event.target.style.opacity = 1;
}, false);


// 드롭 대상에서 이벤트 발생 
document.addEventListener("dragover", function(event) {
	// 드롭을 허용하도록 prevetDefault() 호출
	event.preventDefault();
}, false);

// 요소를 드롭하려는 대상 위로 드래그했을 때
document.addEventListener("dragenter", function(event) {

	// 대상의 배경색 변경
	if (event.target.className == "frameDropZone card-body") {
		event.target.style.background = "#ffedff";
	}
	
}, false);

// 요소를 드래그하여 드롭하려던 대상으로부터 벗어났을 때
document.addEventListener("dragleave", function(event) {
	// 배경색 리셋
//		if (event.target.className.indexOf('frameDropZone') > 0 ) {
	if (event.target.className == "frameDropZone card-body") {
		event.target.style.background = "";
	}
	
}, false);

document.addEventListener("drop", function(event) {
	// 기본 액션을 막음 (링크 열기같은 것들)
	event.preventDefault();
	// 드래그한 요소를 드롭 대상으로 이동
	if (event.target.className == "frameDropZone card-body") {
		event.target.style.background = "";
//		dragged.parentNode.removeChild(dragged);
		console.log(dragged);
		console.log(dragged.parentNode);
		console.log(event.target);
		console.log(event.currentTarget);
		console.log(event.target.className);
		event.target.appendChild(dragged);
		// dragged 의 데이터를 가져와서 
		// drop영역에 프레임을 그린다.
	}
}, false);
*/

