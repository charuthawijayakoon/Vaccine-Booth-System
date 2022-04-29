public class Booth {
    private  int BoothID ;
    private BoothStatus BStatus;
    private  Patient PatientDetail;

    public int getBoothID() {
        return BoothID;
    }

    public Patient getPatientDetail() {
        return PatientDetail;
    }

    public BoothStatus getBStatus() {
        return BStatus;
    }

    public void setBoothID(int boothID) {
        BoothID = boothID;
    }

    public void setBStatus(BoothStatus BStatus) {
        this.BStatus = BStatus;
    }

    public void setPatientDetail(Patient patientDetail) {
        PatientDetail = patientDetail;
    }
}
