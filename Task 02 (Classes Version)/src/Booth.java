import java.io.*;
import java.util.ArrayList;

public class Booth {
private  String  CustomerName;
private  int BoothID ;

    public String getCustomerName() {
        return CustomerName;
    }

    public int getBoothID() {
        return BoothID;
    }

    public void setBoothID(int boothID) {
        BoothID = boothID;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
}
