package utils;

public class Form {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final int daysQuantity;
    private final boolean blackScooter;
    private final boolean grayScooter;
    private final String commentary;

    public Form(String firstname, String lastname, String address, String station, String phone, String date, int daysQuantity, boolean blackScooter, boolean grayScooter, String commentary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.daysQuantity = daysQuantity;
        this.blackScooter = blackScooter;
        this.grayScooter = grayScooter;
        this.commentary = commentary;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getStation() {
        return station;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }

    public int getDaysQuantity() {
        return daysQuantity;
    }

    public boolean isBlackScooter() {
        return blackScooter;
    }

    public boolean isGrayScooter() {
        return grayScooter;
    }

    public String getCommentary() {
        return commentary;
    }

    @Override
    public String toString() {
        return "Form{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", station='" + station + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", daysQuantity=" + daysQuantity +
                ", blackScooter=" + blackScooter +
                ", grayScooter=" + grayScooter +
                ", commentary='" + commentary + '\'' +
                '}';
    }
}
