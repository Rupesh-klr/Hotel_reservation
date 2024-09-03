package entites;

public class room {

	private int HostelId,id,Roomid,BedCount,CanStaycount,RmisDeleted;
    private double price;
    private String RoomType,LastmodifiedRM,LastmodifiedByRM,Rmimage,Rmcode;
    // object form DB
    public room() {
		super();
		 HostelId = 0;
	        this.id = 0;
	        Roomid = 0;
	        BedCount = 0;
	        CanStaycount = 0;
	        RmisDeleted = 0;
	        this.price = 0;
	        RoomType = "";
	        LastmodifiedRM = "";
	        LastmodifiedByRM = "";
		this.Rmimage="";
	}
    public String getRmcode() {
		return Rmcode;
	}
	public void setRmcode(String rmcode) {
		Rmcode = rmcode;
	}
	public room(int hostelId, int id, int roomid, int bedCount, int canStaycount, int rmisDeleted, double price,
            String roomType, String lastmodifiedRM, String lastmodifiedByRM) {
        HostelId = hostelId;
        this.id = id;
        Roomid = roomid;
        BedCount = bedCount;
        CanStaycount = canStaycount;
        RmisDeleted = rmisDeleted;
        this.price = price;
        RoomType = roomType;
        LastmodifiedRM = lastmodifiedRM;
        LastmodifiedByRM = lastmodifiedByRM;
    }
    
	// object for creating to store into DB
    public room(int hostelId, int id, int bedCount, int canStaycount, double price, String roomType) {
        HostelId = hostelId;
        this.id = id;
        BedCount = bedCount;
        CanStaycount = canStaycount;
        this.price = price;
        RoomType = roomType;
    }
    public int getHostelId() {
        return HostelId;
    }
    public void setHostelId(int hostelId) {
        HostelId = hostelId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRoomid() {
        return Roomid;
    }
    public void setRoomid(int roomid) {
        Roomid = roomid;
    }
    public int getBedCount() {
        return BedCount;
    }
    public void setBedCount(int bedCount) {
        BedCount = bedCount;
    }
    public int getCanStaycount() {
        return CanStaycount;
    }
    public void setCanStaycount(int canStaycount) {
        CanStaycount = canStaycount;
    }
    public int getRmisDeleted() {
        return RmisDeleted;
    }
    public void setRmisDeleted(int rmisDeleted) {
        RmisDeleted = rmisDeleted;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getRoomType() {
        return RoomType;
    }
    public void setRoomType(String roomType) {
        RoomType = roomType;
    }
    public String getRmimage() {
		return Rmimage;
	}
	public void setRmimage(String rmimage) {
		Rmimage = rmimage;
	}
	public String getLastmodifiedRM() {
        return LastmodifiedRM;
    }
    public void setLastmodifiedRM(String lastmodifiedRM) {
        LastmodifiedRM = lastmodifiedRM;
    }
    public String getLastmodifiedByRM() {
        return LastmodifiedByRM;
    }
    public void setLastmodifiedByRM(String lastmodifiedByRM) {
        LastmodifiedByRM = lastmodifiedByRM;
    }
    @Override
    public String toString() {
        return "room [HostelId=" + HostelId + ", id=" + id + ", Roomid=" + Roomid + ", BedCount=" + BedCount
                + ", CanStaycount=" + CanStaycount + ", RmisDeleted=" + RmisDeleted + ", price=" + price + ", RoomType="
                + RoomType + ", LastmodifiedRM=" + LastmodifiedRM + ", LastmodifiedByRM=" + LastmodifiedByRM + "]";
    }
    
}
