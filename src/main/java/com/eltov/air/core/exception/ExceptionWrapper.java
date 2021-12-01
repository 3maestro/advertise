package com.eltov.air.core.exception;

import org.springframework.http.HttpStatus;

import com.eltov.air.core.code.ExceptionCode;

public class ExceptionWrapper extends Exception {

	private static final long serialVersionUID = -6692182605764222916L;
	private static final String DEFAULT_ERROR_MESSAGE = "서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해주십시오.";
	
	private Exception e;
	private ExceptionCode errContext;
	private String[] args;
	
	public ExceptionWrapper() {}
	
	public static ExceptionWrapper from(Exception e) {
		if(e instanceof ExceptionWrapper) {
			return (ExceptionWrapper)e;
		}else {
			return new ExceptionWrapper(ExceptionCode.E000_UNCHECKED_ERROR, e);
		}
	}
	
//	public ExceptionWrapper(Exception e) {
//		if(e instanceof ExceptionWrapper) {
//			ExceptionWrapper ew = (ExceptionWrapper)e;
//			this.e = ew.getE();
//			this.errContext = ew.getErrContext();
//			this.args = ew.getArgs();
//		}else {
//			this.e = e;
//			this.errContext = ExceptionCode.E000_UNCHECKED_ERROR;
//		}
//	}
	
	private ExceptionWrapper(ExceptionCode errContext) {
		super();
		this.errContext = errContext;
	}
	
	public ExceptionWrapper(ExceptionCode errContext, Exception e) {
		super();
		this.e = e;
		this.errContext = errContext;
	}
	
//	public ExceptionWrapper(ExceptionCode errContext, String...args) {
//		super();
//		this.errContext = errContext;
//		this.args = args;
//	}
	
	public ExceptionWrapper(ExceptionCode errContext, Exception e, String...args) {
		super();
		this.errContext = errContext;
		this.e = e;
		this.args = args;
	}

	public Exception getE() {
		return e;
	}
	
	public String[] getArgs() {
		return args;
	}

	public ExceptionCode getErrContext() {
		return errContext;
	}

	@Override
	public String getMessage() {
		return parseMessage(errContext.getMessage(), getArgs());
	}
	
	@Override
	public String toString() {
		if(e != null) {
			return this.e.toString(); //Exception를 wrapping할 때 발생한 Exception을 넣어줬으면 여기서 가져오고
		}else {
			return errContext.getCode(); // 없으면 지정 메세지를 가져온다
		}
	}
	
	public String getErrorCause() {
		String errorCause = "";
		if(e != null) {
			errorCause = this.e.toString(); //Exception를 wrapping할 때 발생한 Exception을 넣어줬으면 여기서 가져오고
		}else {
			errorCause = errContext.getCode(); // 없으면 지정 메세지를 가져온다
		}
		return errorCause;
	}
	
	public HttpStatus getHttpStatus() {
		return errContext.getHttpStatus();
	}
	
	public String getErrorCode() {
		return errContext.getCode();
	}
	
	public String getErrorPoint() {
		StackTraceElement errorPoint = e.getStackTrace()[0];
		return errorPoint.getClassName() + "."+ errorPoint.getMethodName();
	}
	
	private String parseMessage(String message, String...args) {
		if (message == null || args == null || args.length < 1)
			return DEFAULT_ERROR_MESSAGE;

		for (String arg : args) {
			message = message.replaceFirst("%", arg);
		}
		
		message = message.replaceAll("%", "");
		
		return message;
	}
	
}
