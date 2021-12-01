package com.eltov.air.core.exception;

import org.springframework.http.HttpStatus;

import com.eltov.air.core.code.ExceptionCode;

public class ExceptionWrapperForAjax extends Exception {
	
	private static final long serialVersionUID = -4530234393261460911L;
	
	private Exception e;
	private ExceptionCode errContext;
	private String[] args;
	
	public ExceptionWrapperForAjax() {}
	
	private ExceptionWrapperForAjax(ExceptionWrapper ew) {
		this.e = ew.getE();
		this.errContext = ew.getErrContext();
		this.args = ew.getArgs();
	}
	
	private ExceptionWrapperForAjax(ExceptionCode errContext, Exception e) {
		super();
		this.e = e;
		this.errContext = errContext;
	}
	
	public static ExceptionWrapperForAjax from(Exception e) {
		if(e instanceof ExceptionWrapper) {
			ExceptionWrapper ew = (ExceptionWrapper)e;
			ExceptionWrapperForAjax ewfa = new ExceptionWrapperForAjax(ew);
			ew = null;
			return ewfa;
		}else {
			return new ExceptionWrapperForAjax(ExceptionCode.E000_UNCHECKED_ERROR, e);
		}
	}
	
//	public ExceptionWrapperForAjax(Exception e) {
//		if(e instanceof ExceptionWrapper) {
//			ExceptionWrapper ew = (ExceptionWrapper)e;
//			this.e = ew.getE();
//			this.errContext = ew.getErrContext();
//			this.args = ew.getArgs();
//		}else {
//			ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ExceptionCode.E000_UNCHECKED_ERROR, e);
//			this.e = exceptionWrapper.getE();
//			this.errContext = exceptionWrapper.getErrContext();
//			this.args = exceptionWrapper.getArgs();
//		}
//	}
	
	public Exception getE() {
		return e;
	}

	public String[] getArgs() {
		return args;
	}
	
	@Override // 사용자에게 보여줄 getMessage
	public String getMessage() {
		return parseMessage(errContext.getMessage(), getArgs());
	}
	
	// 디버그용 error 메세지
	public String getErrorCause() {
		String errorCause = "";
		if(e != null) {
			errorCause = new StringBuilder(e.toString()).append(" : ").append(e.getMessage()).toString(); //Exception를 wrapping할 때 발생한 Exception을 넣어줬으면 여기서 가져오고
		}else {
			errorCause = errContext.getMessage(); // 없으면 지정 메세지를 가져온다
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
			return message;

		for (String arg : args) {
			message = message.replaceFirst("%", arg);
		}
		
		message = message.replaceAll("%", "");
		
		return message;
	}
}
