package entites;

public class booking {
	// this entities is used for storing booking type( data table object Handling) object.
    private int BookingId,id,HostelId,Roomid,NoOfGuests,TotalStay,StayDetails,BookingisClosed,days;
    private double price;
    private String BookedDate,BookingFrom,BookingTo,Lastmodified,LastmodifiedBy;
    public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	// object to get from DB
    public booking(int bookingId, int id, int hostelId, int roomid, int noOfGuests, int totalStay, int stayDetails,
            int bookingisClosed, double price, String bookedDate, String bookingFrom, String bookingTo,
            String lastmodified, String lastmodifiedBy) {
        BookingId = bookingId;
        this.id = id;
        HostelId = hostelId;
        Roomid = roomid;
        NoOfGuests = noOfGuests;
        TotalStay = totalStay;
        StayDetails = stayDetails;
        BookingisClosed = bookingisClosed;
        this.price = price;
        BookedDate = bookedDate;
        BookingFrom = bookingFrom;
        BookingTo = bookingTo;
        Lastmodified = lastmodified;
        LastmodifiedBy = lastmodifiedBy;
    }
    public booking() {
		super();
        BookingId = 0;
        this.id = 0;
        HostelId = 0;
        Roomid = 0;
        NoOfGuests = 0;
        TotalStay = 0;
        StayDetails = 0;
        BookingisClosed = 0;
        this.price = 0;
        BookedDate = "";
        BookingFrom = "";
        BookingTo = "";
        Lastmodified = "";
        LastmodifiedBy = "";
	}
	// object to store to DB
    public booking(int id, int hostelId, int roomid, int noOfGuests, int totalStay, String bookedDate,
            String bookingFrom, String bookingTo) {
        this.id = id;
        HostelId = hostelId;
        Roomid = roomid;
        NoOfGuests = noOfGuests;
        TotalStay = totalStay;
        BookedDate = bookedDate;
        BookingFrom = bookingFrom;
        BookingTo = bookingTo;
    }
    public int getBookingId() {
        return BookingId;
    }
    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getHostelId() {
        return HostelId;
    }
    public void setHostelId(int hostelId) {
        HostelId = hostelId;
    }
    public int getRoomid() {
        return Roomid;
    }
    public void setRoomid(int roomid) {
        Roomid = roomid;
    }
    public int getNoOfGuests() {
        return NoOfGuests;
    }
    public void setNoOfGuests(int noOfGuests) {
        NoOfGuests = noOfGuests;
    }
    public int getTotalStay() {
        return TotalStay;
    }
    public void setTotalStay(int totalStay) {
        TotalStay = totalStay;
    }
    public int getStayDetails() {
        return StayDetails;
    }
    public void setStayDetails(int stayDetails) {
        StayDetails = stayDetails;
    }
    public int getBookingisClosed() {
        return BookingisClosed;
    }
    public void setBookingisClosed(int bookingisClosed) {
        BookingisClosed = bookingisClosed;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getBookedDate() {
        return BookedDate;
    }
    public void setBookedDate(String bookedDate) {
        BookedDate = bookedDate;
    }
    public String getBookingFrom() {
        return BookingFrom;
    }
    public void setBookingFrom(String bookingFrom) {
        BookingFrom = bookingFrom;
    }
    public String getBookingTo() {
        return BookingTo;
    }
    public void setBookingTo(String bookingTo) {
        BookingTo = bookingTo;
    }
    public String getLastmodified() {
        return Lastmodified;
    }
    public void setLastmodified(String lastmodified) {
        Lastmodified = lastmodified;
    }
    public String getLastmodifiedBy() {
        return LastmodifiedBy;
    }
    public void setLastmodifiedBy(String lastmodifiedBy) {
        LastmodifiedBy = lastmodifiedBy;
    }
    @Override
    public String toString() {
        return "booking [BookingId=" + BookingId + ","+days+ " id=" + id + ", HostelId=" + HostelId + ", Roomid=" + Roomid
                + ", NoOfGuests=" + NoOfGuests + ", TotalStay=" + TotalStay + ", StayDetails=" + StayDetails
                + ", BookingisClosed=" + BookingisClosed + ", price=" + price + ", BookedDate=" + BookedDate
                + ", BookingFrom=" + BookingFrom + ", BookingTo=" + BookingTo + ", Lastmodified=" + Lastmodified
                + ", LastmodifiedBy=" + LastmodifiedBy +"]";
    }
    
}
