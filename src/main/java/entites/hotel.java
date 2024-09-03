package entites;
public class hotel {
    private int Hotelid,id,Rating,HTstatus,Htisdeleted;
    private double StaringPr,EndPr;
    private String HostelName,HotelAddress,HotelDesc,Htimage,HotelLocation,statumsg,LastmodifiedHT,LastmodifiedByHT,HotelNumber;
    public hotel() {
		super();
		   Hotelid = 0;
	        this.id = 0;
	        Rating = 0;
	        HTstatus = 0;
	        Htisdeleted = 0;
	        StaringPr = 0;
	        EndPr = 0;
	        HostelName = "";
	        HotelAddress = "";
	        HotelDesc = "";
	        Htimage = "";
	        HotelLocation = "";
	        this.statumsg = "";
	        this.LastmodifiedHT = "";
	        LastmodifiedByHT = "";
	}
    // object getting form table 
    /*
    public hotel(int hotelid, int id, int rating, int hTstatus, int htisdeleted, String hostelName, String hotelAddress,
            String hotelDesc, String htimage, String hotelLocation, String statumsg, String LastmodifiedHT,
            String lastmodifiedByHT, double staringPr, double endPr) {
        Hotelid = hotelid;
        this.id = id;
        Rating = rating;
        HTstatus = hTstatus;
        Htisdeleted = htisdeleted;
        HostelName = hostelName;
        HotelAddress = hotelAddress;
        HotelDesc = hotelDesc;
        Htimage = htimage;
        HotelLocation = hotelLocation;
        this.statumsg = statumsg;
        this.LastmodifiedHT = LastmodifiedHT;
        LastmodifiedByHT = lastmodifiedByHT;
        StaringPr = staringPr;
        EndPr = endPr;
    }
    


	// object creating to store into DB
    public hotel(int id, int rating, String hostelName, String hotelAddress, String hotelDesc, String htimage,
            String hotelLocation, String statumsg) {
        this.id = id;
        Rating = rating;
        HostelName = hostelName;
        HotelAddress = hotelAddress;
        HotelDesc = hotelDesc;
        Htimage = htimage;
        HotelLocation = hotelLocation;
        this.statumsg = statumsg;
    }
    */
    public int getHotelid() {
        return Hotelid;
    }
    public void setHotelid(int hotelid) {
        Hotelid = hotelid;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRating() {
        return Rating;
    }
    public void setRating(int rating) {
        Rating = rating;
    }
    public int getHTstatus() {
        return HTstatus;
    }
    public void setHTstatus(int hTstatus) {
        HTstatus = hTstatus;
    }
    public int getHtisdeleted() {
        return Htisdeleted;
    }
    public void setHtisdeleted(int htisdeleted) {
        Htisdeleted = htisdeleted;
    }
    public String getHostelName() {
        return HostelName;
    }
    public void setHostelName(String hostelName) {
        HostelName = hostelName;
    }
    public String getHotelAddress() {
        return HotelAddress;
    }
    public void setHotelAddress(String hotelAddress) {
        HotelAddress = hotelAddress;
    }
    public String getHotelDesc() {
        return HotelDesc;
    }
    public void setHotelDesc(String hotelDesc) {
        HotelDesc = hotelDesc;
    }
    public String getHtimage() {
        return Htimage;
    }
    public void setHtimage(String htimage) {
    	if(htimage==null) {
    		Htimage="NTH";
    	}else {    		
    		Htimage = htimage;
    	}
    }
    public String getHotelLocation() {
        return HotelLocation;
    }
    public void setHotelLocation(String hotelLocation) {
        HotelLocation = hotelLocation;
    }
    public String getStatumsg() {
        return statumsg;
    }
    public void setStatumsg(String statumsg) {
        this.statumsg = statumsg;
    }
    public String getLastmodifiedHT() {
        return LastmodifiedHT;
    }
    public void setLastmodifiedHT(String LastmodifiedHT) {
        this.LastmodifiedHT = LastmodifiedHT;
    }
    public String getLastmodifiedByHT() {
        return LastmodifiedByHT;
    }
    public void setLastmodifiedByHT(String lastmodifiedByHT) {
        LastmodifiedByHT = lastmodifiedByHT;
    }
    public double getStaringPr() {
        return StaringPr;
    }
    public void setStaringPr(double staringPr) {
        StaringPr = staringPr;
    }
    public double getEndPr() {
        return EndPr;
    }
    public void setEndPr(double endPr) {
        EndPr = endPr;
    }
    public String getHotelNumber() {
		return HotelNumber;
	}
	public void setHotelNumber(String hotelNumber) {
		HotelNumber = hotelNumber;
	}
	@Override
    public String toString() {
        return "hotel [Hotelid=" + Hotelid + ", id=" + id + ", Rating=" + Rating + ", HTstatus=" + HTstatus
                + ", Htisdeleted=" + Htisdeleted + ", HostelName=" + HostelName + ", HotelAddress=" + HotelAddress
                + ", HotelDesc=" + HotelDesc + ", Htimage=" + Htimage + ", HotelLocation=" + HotelLocation
                + ", statumsg=" + statumsg + ", LastmodifiedHT=" + LastmodifiedHT + ", LastmodifiedByHT="
                + LastmodifiedByHT + ", StaringPr=" + StaringPr + ", EndPr=" + EndPr + "]";
    }
	public void setId(String parameter) {
		this.id=Integer.parseInt(parameter);		
	}
	

}
