/**
 * 
 */

function makeDeviceControlForm(id, schUse, schControl, schTime){
	
	var schWeek = ['SUN','MON','TUE','WED','THU','FRI','SAT'];
	var deviceControl;
	if(id === ''){
		schUse = ['Y','Y','Y','Y','Y','Y','Y'];
		schControl = ['D','D','D','D','D','D','D'];
		schTime = ['2300','2300','2300','2300','2300','2300','2300'];
		deviceControl = document.getElementById('deviceControl1');
	}else{
		deviceControl = document.getElementById('deviceControl2');
	}
	
	var html = '';
	html += '<table class="mb-0 table table-bordered">';
	html += 	'<thead>';
	html += 		'<tr style="text-align:center;">';
	html += 			'<th></th>';
	html += 			'<th>사용여부</th>';
	html += 			'<th>제어명령</th>';
	html += 			'<th>적용시간</th>';
	html += 	'</thead>';
	html += 	'<tbody>';
	html +=			'<tr>';
	html +=				'<th scope="row" style="text-align:center;">All</th>';
	html +=				'<td style="text-align:center;">';
	html +=					'<div role="group" class="btn-group-sm btn-group btn-group-toggle" data-toggle="buttons" style="width:90%">';
	html +=						'<label class="btn btn-pill btn-outline-info active"> ';
	html +=							'<input type="radio" name="options1" id="option111" autocomplete="off" checked=""> 사용';
	html +=						'</label>';
	html +=						'<label class="btn btn-pill btn-outline-primary">';
	html +=							'<input type="radio" name="options1" id="option133" autocomplete="off" > 미사용';
	html +=						'</label>';
	html +=					'</div>';
	html +=				'</td>';
	html +=				'<td style="text-align:center;">';
	html +=					'<div role="group" class="btn-group-sm btn-group btn-group-toggle" data-toggle="buttons" style="width:90%">';
	html +=						'<label class="btn btn-pill btn-outline-secondary active">';
	html +=							'<input type="radio" name="options" id="option177" autocomplete="off" checked=""> 종료';
	html +=						'</label>';
	html +=						'<label class="btn btn-pill btn-outline-warning"> ';
	html +=							'<input type="radio" name="options" id="option188" autocomplete="off" > 재부팅';
	html +=						'</label>';
	html +=					'</div>';
	html +=				'</td>';
	html +=				'<td style="text-align:center;width:25%;">';
	html +=					'<div class="input-group bootstrap-timepicker">';
	html +=						'<input type="text" id="id_sch_time" value="23:00" class="form-control timepicker-default" readonly /> ';
	html +=						'<span class="input-group-btn">';
	html +=							'<button class="btn btn-focus" type="button" style="font-size: 1rem;">';
	html +=								'<i class="pe-7s-clock"></i>';
	html +=							'</button>';
	html +=						'</span>';
	html +=					'</div>';
	html +=				'</td>';
	html +=			'</tr>';
	for(var i=0; i<schWeek.length; i++){
		var week = schWeek[i];
		var use = schUse[i];
		var control = schControl[i];
		var time = schTime[i];
		
		console.log(week);
		html +=			'<tr>';
		html +=				'<th scope="row" style="text-align:center;">' + week + '</th>';
		html +=				'<td style="text-align:center;">';
		html +=					'<div role="group" class="btn-group-sm btn-group btn-group-toggle" data-toggle="buttons" style="width:90%">';
		html +=						'<label class="btn btn-pill btn-outline-info  ' + checkUseY(use) + '"> ';
		html +=							'<input type="radio" name="schUse" id="schUseYes" valeu="Y" autocomplete="off" > 사용';
		html +=						'</label>';
		html +=						'<label class="btn btn-pill btn-outline-primary  ' + checkUseN(use) + '">';
		html +=							'<input type="radio" name="schUse" id="schUseNo" valeu="N" autocomplete="off" > 미사용';
		html +=						'</label>';
		html +=					'</div>';
		html +=				'</td>';
		html +=				'<td style="text-align:center;">';
		html +=					'<div role="group" class="btn-group-sm btn-group btn-group-toggle" data-toggle="buttons" style="width:90%">';
		html +=						'<label class="btn btn-pill btn-outline-secondary ' + checkControlD(control) + '">';
		html +=							'<input type="radio" name="options" id="option177" autocomplete="off" checked=""> 종료';
		html +=						'</label>';
		html +=						'<label class="btn btn-pill btn-outline-warning  ' + checkControlR(control) + '"> ';
		html +=							'<input type="radio" name="options" id="option188" autocomplete="off" > 재부팅';
		html +=						'</label>';
		html +=					'</div>';
		html +=				'</td>';
		html +=				'<td style="text-align:center;width:25%;">';
		html +=					'<div class="input-group bootstrap-timepicker">';
		html +=						'<input type="text" id="id_sch_time" value="' + makeFormatTime(time) + '" class="form-control timepicker-default" readonly /> ';
		html +=						'<span class="input-group-btn">';
		html +=							'<button class="btn btn-focus" type="button" style="font-size: 1rem;">';
		html +=								'<i class="pe-7s-clock"></i>';
		html +=							'</button>';
		html +=						'</span>';
		html +=					'</div>';
		html +=				'</td>';
		html +=			'</tr>';
	}
	html +=		'</tbody>';
	html += '</table>';
		
	if(id === ''){
	}else{
		
	}
	
	deviceControl.innerHTML = html;
}
	
////////////////////////////
// Device Control Schedule Util
////////////////////////////
function checkUseY(use){
	if(use === 'Y'){
	return 'active';
}else{
	return '';
	}
}

function checkUseN(use){
	if(use === 'N'){
	return 'active';
}else{
	return '';
	}
}

function checkControlD(control){
	if(control === 'D'){
	return 'active';
}else{
	return '';
	}
}

function checkControlR(control){
	if(control === 'R'){
	return 'active';
}else{
	return '';
	}
}

function makeFormatTime(time){
	var h = time.substring(0,2);
	var m = time.substring(2);
	time = h + ':' + m;
	return time;
}