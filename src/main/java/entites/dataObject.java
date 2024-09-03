package entites;

public class dataObject {
	// this entities is used for storing output data for front-end(developers) help to read about the request and message type.
	private int status;
	private String successMsg,errorMsg,logData;
	
	private Object datapack;
	
	public dataObject() {
		super();
		this.status = -1;
		this.successMsg = "Nothing";
		this.errorMsg = "somthing went wrong conact to admistrator.";
		this.logData = "nothing log stored";
	}
	public dataObject(int status, String successMsg, String errorMsg, String logData) {
		super();
		this.status = status;
		this.successMsg = successMsg;
		this.errorMsg = errorMsg;
		this.logData = logData;
	}
	public dataObject(int status) {
		if(status==1) { this.status=status;
			this.successMsg = "SuccessFully";
			this.errorMsg = "NTH";
			this.logData = "log";
		}else {this.status=status;
			this.successMsg = "NTH";
			this.errorMsg = "Failed";
			this.logData = "log";
		}
	}
	
	public dataObject(int status,String datareply) {
		 this.status=status;this.datapack=datareply;
		if(status==1) {
		this.successMsg = "SuccessFully";
		this.errorMsg = "NTH";
		this.logData = "log";
		}else {
		this.successMsg = "NTH";
		this.errorMsg = "Failed";
		this.logData = "log";
		}
	}
	
	public String getLogData() {
		return logData;
	}
	public void setLogData(String logData) {
		this.logData = logData;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getDatapack() {
		return datapack;
	}
	public void setDatapack(Object datapack) {
		this.datapack = datapack;
	}
	@Override
	public String toString() {
		return " [status=" + status + "\nsuccessMsg=" + successMsg + ", errorMsg=" + errorMsg + ", logData="
				+ logData + "\ndatapack=\t\t" + datapack.toString() + "]";
	}
}
