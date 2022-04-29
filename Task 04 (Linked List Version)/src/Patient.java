import java.util.PrimitiveIterator;    //

public class Patient {
    private String FirstName;
    private String Surname;
    private int  Age;
    private String City;
    private String Nic ;
    private VaxinType VaxReqType;

    public String getFirstName() {
        return FirstName;
    }
    public String getSurname() {
        return Surname;
    }
    public int getAge() {
        return Age;
    }
    public String getCity(){
        return City;
    }
    public String getNic(){
        return  Nic;
    }
    public VaxinType getVaxReqType()
    {
        return VaxReqType;
    }

    public void  setFirstName(String firstName){
        FirstName=firstName;
    }
    public void  setSurname(String surname){
        Surname =surname;
    }
    public  void setAge(int age){
        Age= age;
    }
    public void setCity(String city) {
        City = city;
    }
    public void setNic(String nic) {
        Nic = nic;
    }
    public void setVaxReqType(VaxinType vaxReqType) {
        VaxReqType = vaxReqType;
    }
}
