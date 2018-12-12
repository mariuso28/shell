package org.shell.json;


public class ResultJson {

	private StatusJson status;
	private String message;
	private Object result;

	public ResultJson() {}

	public void fail(String message)
	{
		setStatus(StatusJson.ERROR);
		setMessage(message);
	}
	
	public void warn(String message)
	{
		setStatus(StatusJson.WARN);
		setMessage(message);
	}
	
	public void success(Object result)
	{
		setStatus(StatusJson.OK);
		setResult(result);
	}
	
	public void success()
	{
		setStatus(StatusJson.OK);
	}
	
	public StatusJson getStatus() {
		return status;
	}

	public void setStatus(StatusJson status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
