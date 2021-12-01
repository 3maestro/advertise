package com.eltov.air.core.code;

import org.springframework.http.HttpStatus;

public enum ExceptionCode {
	//0~ 기본
	E000_UNCHECKED_ERROR("E000_UNCHECKED_ERROR", "서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	
	//1~ DB
	E101_DB_SQL_EXCEPTION("E101_DB_SQL_EXCEPTION", "서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E102_UNSATISFIED_TRANSACTION("E102_UNSATISFIED_TRANSACTION", "요청이 정상적으로 처리되지 않았습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E103_LOG_INSERT_FAIL_DB("E103_LOG_INSERT_FAIL_DB", "로그 남기기 실패", HttpStatus.OK),
	E104_NOTHING_SELECTED("E104_NOTHING_SELECTED", "존재하지 않는 페이지입니다.", HttpStatus.NOT_FOUND),
	E105_DB_UPDATE_FAIL("E105_DB_UPDATE_FAIL", "수정에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E106_DB_DELETE_FAIL("E106_DB_DELETE_FAIL", "삭제에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E107_CANNOT_DELETE_BASIC_DATA("E107_CANNOT_DELETE_BASIC_DATA", "% 삭제할 수 없습니다.", HttpStatus.OK),
	E108_DB_ABNORMAL_INSERT("E108_DB_ABNORMAL_INSERT", "등록에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.OK),
	
	//2~ FILE
	E201_INVALID_FILE("E201_INVALID_FILE", "등록할 수 없는 파일 유형입니다. 확장자와 파일 크기를 확인해주십시오. (% / %) 단위:byte", HttpStatus.BAD_REQUEST),
	E202_FILE_SAVE_FAIL("E202_FILE_SAVE_FAIL", "파일 등록에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.OK),
	E203_FILE_SAVE_FAIL_DB("E203_FILE_SAVE_FAIL_DB", "파일 등록에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.OK),
	E204_FILE_NOT_FOUND("E204_FILE_NOT_FOUND", "등록할 파일이 선택되지 않았습니다.", HttpStatus.BAD_REQUEST),
	E205_FILE_DELETE_FAIL("E205_FILE_DELETE_FAIL", "파일 삭제에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E206_FILE_UPDATE_FAIL_DB("E206_FILE_UPDATE_FAIL_DB", "수정에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E207_FILE_CONVERT_ERROR("E207_FILE_CONVERT_ERROR", "서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E208_FILE_UPDATE_FAIL("E208_FILE_UPDATE_FAIL", "파일 변경에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	
	//3~ USER
	E301_USER_ID_DUPLICATION("E301_USER_ID_DUPLICATION", "이미 사용 중인 아이디입니다.", HttpStatus.OK),
	E302_NO_USER("E302_NO_USER", "존재하지 않는 사용자입니다.", HttpStatus.OK),
	E303_WRONG_PASSWORD("E303_WRONG_PASSWORD", "비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주십시오.", HttpStatus.OK),
	
	//4~ PROCESS
	E401_PROCESS_ABNORMAL_TERMINATION("E401_PROCESS_ABNORMAL_TERMINATION", "서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.INTERNAL_SERVER_ERROR),
	E402_FFMPEG_FAIL("E402_FFMPEG_FAIL", "파일이 정상적으로 처리되지 않았습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.OK),
	
	//5~ WEB
	E501_INVALID_PARAMETER("E501_INVALID_PARAMETER", "요청한 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
	
	//6~ NETWORK
	E601_SOCKET_CONNECTION_FAIL("E601_SOCKET_CONNECTION_FAIL", "디바이스와 연결에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.OK),
	E602_SOCKET_CLOSE_FAIL("E602_SOCKET_CLOSE_FAIL", "디바이스와 연결에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.OK),
	E603_SOCKET_FAIL_SEND_PACKET("E603_SOCKET_FAIL_SEND_PACKET", "디바이스와 연결에 실패했습니다. 잠시 후 다시 시도해주십시오.", HttpStatus.OK);
	
	private String code;
	private String message;
	private HttpStatus httpStatus;
	
	ExceptionCode(String code, String message, HttpStatus httpStatus) {
		this.code = code;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
