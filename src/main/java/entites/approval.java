package entites;

public class approval {
	// this entities is used for storing approval type( data table object Handling) object.
    private int ApprovalId,ProofType,id,closebyId,isClosed;
    private String Proof="",AttachmentMessage="",closedReplay="",closedOn="";
    // for getting object from table 
    
    public approval(int approvalId, int proofType, int id, int closebyId, int isClosed, String proof,
            String attachmentMessage, String closedReplay, String closedOn) {
        ApprovalId = approvalId;
        ProofType = proofType;
        this.id = id;
        this.closebyId = closebyId;
        this.isClosed = isClosed;
        Proof = proof;
        AttachmentMessage = attachmentMessage;
        this.closedReplay = closedReplay;
        this.closedOn = closedOn;
    }
    public approval() {
		super();
		ApprovalId = 0;
        ProofType = 0;
        this.id = 0;
        this.closebyId = 0;
        this.isClosed = 0;
        Proof = "";
        AttachmentMessage = "";
        this.closedReplay = "";
        this.closedOn = "";
	}
	// requested maked by user
    public approval(int proofType, String proof, String attachmentMessage,int id) {
        ProofType = proofType;
        Proof = proof;
        AttachmentMessage = attachmentMessage;
        this.id = id;
    }
    public int getApprovalId() {
        return ApprovalId;
    }
    public void setApprovalId(int approvalId) {
        ApprovalId = approvalId;
    }
    public int getProofType() {
        return ProofType;
    }
    public void setProofType(int proofType) {
        ProofType = proofType;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClosebyId() {
        return closebyId;
    }
    public void setClosebyId(int closebyId) {
        this.closebyId = closebyId;
    }
    public int getIsClosed() {
        return isClosed;
    }
    public void setIsClosed(int isClosed) {
        this.isClosed = isClosed;
    }
    public String getProof() {
        return Proof;
    }
    public void setProof(String proof) {
        Proof = proof;
    }
    public String getAttachmentMessage() {
        return AttachmentMessage;
    }
    public void setAttachmentMessage(String attachmentMessage) {
        AttachmentMessage = attachmentMessage;
    }
    public String getClosedReplay() {
        return closedReplay;
    }
    public void setClosedReplay(String closedReplay) {
        this.closedReplay = closedReplay;
    }
    public String getClosedOn() {
        return closedOn;
    }
    public void setClosedOn(String closedOn) {
        this.closedOn = closedOn;
    }
    @Override
    public String toString() {
        return "approval [ApprovalId=" + ApprovalId + ", ProofType=" + ProofType + ", id=" + id + ", closebyId="
                + closebyId + ", isClosed=" + isClosed + ", Proof=" + Proof + ", AttachmentMessage=" + AttachmentMessage
                + ", closedReplay=" + closedReplay + ", closedOn=" + closedOn + "]";
    };
}
