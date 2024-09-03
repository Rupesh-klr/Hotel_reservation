package entites;
public class user {
    
	
    // ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode()
    
	private int id,Requested,isDelete;
    private String userName,userEmail,userNumber,userPass,
    userKey,apporavalmsg,role,lastModifiedDate,lastModifiedBy,photoRef;
    
    
	public user() {
        this.id = 0;
        Requested = 0;
        this.isDelete = 0;
        this.userName = "";
        this.userEmail = "";
        this.userNumber = "";
        this.userPass = "";
        this.userKey = "";
        this.apporavalmsg = "";
        this.role = "";
        this.lastModifiedDate = "";
        this.lastModifiedBy = "";
    }
    /*
	// --wanted forapproval class
    public user(int id, int requested, String userName, String apporavalmsg, String role) {
        this.id = id;
        Requested = requested;
        this.userName = userName;
        this.apporavalmsg = apporavalmsg;
        this.role = role;
    }
    // newly registeing user
    public user(int id, String userName, String userEmail, String userNumber, String userPass, String userKey,
            String role) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userPass = userPass;
        this.userKey = userKey;
        this.role = role;
    } 
    // all field from Db
    public user(int id, int requested, int isDelete, String userName, String userEmail, String userNumber,
            String userPass, String userKey, String apporavalmsg, String role, String lastModifiedDate,
            String lastModifiedBy) {
        this.id = id;
        Requested = requested;
        this.isDelete = isDelete;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userPass = userPass;
        this.userKey = userKey;
        this.apporavalmsg = apporavalmsg;
        this.role = role;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRequested() {
        return Requested;
    }
    public void setRequested(int requested) {
        Requested = requested;
    }
    public int getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserNumber() {
        return userNumber;
    }
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
    public String getUserPass() {
        return userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    public String getUserKey() {
        return userKey;
    }
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
    public String getApporavalmsg() {
        return apporavalmsg;
    }
    public void setApporavalmsg(String apporavalmsg) {
        this.apporavalmsg = apporavalmsg;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    @Override
    public String toString() {
        return "user [id=" + id + ", Requested=" + Requested + ", isDelete=" + isDelete + ", userName=" + userName
                + ", userEmail=" + userEmail + ", userNumber=" + userNumber + ", userPass=" + userPass + ", userKey="
                + userKey + ", apporavalmsg=" + apporavalmsg + ", role=" + role + ", lastModifiedDate="
                + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + "]";
    }
    public String getPhotoRef() {
		return photoRef;
	}
	public void setPhotoRef(String photoRef) {
		this.photoRef = photoRef;
	}
	public void setId(String val) {
		this.id=Integer.parseInt(val);
	}
}