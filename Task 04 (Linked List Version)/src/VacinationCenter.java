import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class VacinationCenter {
    private static int boothNum = 0;
    private static Booth[] VaxBooth = new Booth[7];
    private static String customerName;

    private static  int tempBoothNum =0;
    private static int vaxStock =150;

    private static ArrayList<Patient> PatientList = new ArrayList<Patient>();

    private static String vaxStockFilePath =new File(".").getAbsolutePath()+"//vaxtbl.txt";
    private  static   Patient pDetail =new Patient();
    private static LinkedList<Patient> waitList=new LinkedList<Patient>();

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        getmenuoption();

        Booth boothObj =new Booth();

        initialise(VaxBooth); //better to initialise in a procedure
        while (boothNum < 6)
        {
            System.out.println("Please Enter Menu Code : ");
            String menucode = input.nextLine();

            if(menucode.equals("100") || menucode.toUpperCase().equals("VVB"))
            {
                //View all Vaccination Booths
                getAllBootDetail(VaxBooth);
                //getmenuoption();
            }
            else if(menucode.equals("101") || menucode.toUpperCase().equals("VEB"))
            {
                /// view empty booth
                getEmptyBoot(VaxBooth);
            }
            else if(menucode.equals("102") || menucode.toUpperCase().equals("APB"))
            {
                // add patient to booth

                Booth tempBooth =new Booth();
                Patient tempPatient =new Patient();


                System.out.println("Please Enter First Name : ");
                tempPatient.setFirstName(input.next());

                System.out.println("Please Enter Surname");
                tempPatient.setSurname(input.next());

                System.out.println("Please Enter Age");
                while (!input.hasNextInt()) {
                    System.out.println("Please enter number to age :");
                    input.next(); // this is important!
                }
                tempPatient.setAge(input.nextInt());

                System.out.println("Please Enter City");
                tempPatient.setCity(input.next());

                System.out.println("Please Enter NIC or Passport Number");
                tempPatient.setNic(input.next());

                boolean valid = true;
                do {
                    if (!valid) {
                        System.out.print("Invalid Vaccination Type. ");
                    }
                    System.out.println("Please Enter Required Vaccination\n"+
                            "AstraZeneca  \n"+
                            "Sinopharm  \n"+
                            "Pfizer " );

                    try {
                        VaxinType vaxtype = VaxinType.valueOf(input.next());
                        valid = (vaxtype==VaxinType.AstraZeneca || vaxtype ==VaxinType.Sinopharm || vaxtype == VaxinType.Pfizer );
                        if(valid){
                            tempPatient.setVaxReqType(vaxtype);
                        }
                    } catch (Exception nfex) {
                        valid = false;
                    }
                } while (!valid);


                if(tempPatient.getVaxReqType()==VaxinType.AstraZeneca )
                {
                    if(VaxBooth[0].getBStatus()==BoothStatus.EMPTY  ){
                        tempBooth.setPatientDetail(tempPatient);
                        tempBooth.setBoothID(0);
                        tempBooth.setBStatus(BoothStatus.FULL);
                        VaxBooth[0] = tempBooth;
                        PatientList.add( tempBooth.getPatientDetail());
                        vaxStock = vaxStock - 1;
                    }else if (VaxBooth[1].getBStatus()==BoothStatus.EMPTY){
                        tempBooth.setPatientDetail(tempPatient);
                        tempBooth.setBoothID(1);
                        tempBooth.setBStatus(BoothStatus.FULL);
                        VaxBooth[1] = tempBooth;
                        PatientList.add( tempBooth.getPatientDetail());
                        vaxStock = vaxStock - 1;
                    }
                    else {
                        System.out.println("AstaZeneca boot is not available at the movement ");
                        waitList.add(tempPatient);

                    }
                }else if(tempPatient.getVaxReqType()==VaxinType.Sinopharm){
                    if(VaxBooth[2].getBStatus()==BoothStatus.EMPTY  ){
                        tempBooth.setPatientDetail(tempPatient);
                        tempBooth.setBoothID(2);
                        tempBooth.setBStatus(BoothStatus.FULL);
                        VaxBooth[2] = tempBooth;
                        PatientList.add( tempBooth.getPatientDetail());
                        vaxStock = vaxStock - 1;
                    }else if (VaxBooth[3].getBStatus()==BoothStatus.EMPTY){
                        tempBooth.setPatientDetail(tempPatient);
                        tempBooth.setBoothID(3);
                        tempBooth.setBStatus(BoothStatus.FULL);
                        VaxBooth[3] = tempBooth;
                        PatientList.add( tempBooth.getPatientDetail());
                        vaxStock = vaxStock - 1;
                    }
                    else {
                        System.out.println("Sinopharm boot is not available at the movement ");
                        waitList.add(tempPatient);
                    }
                }else if(tempPatient.getVaxReqType()==VaxinType.Pfizer){
                    if(VaxBooth[4].getBStatus()==BoothStatus.EMPTY  ){
                        tempBooth.setPatientDetail(tempPatient);
                        tempBooth.setBoothID(4);
                        tempBooth.setBStatus(BoothStatus.FULL);
                        VaxBooth[4] = tempBooth;
                        PatientList.add( tempBooth.getPatientDetail());
                        vaxStock = vaxStock - 1;
                    }else if (VaxBooth[5].getBStatus()==BoothStatus.EMPTY){
                        tempBooth.setPatientDetail(tempPatient);
                        tempBooth.setBoothID(5);
                        tempBooth.setBStatus(BoothStatus.FULL);
                        VaxBooth[5] = tempBooth;
                        PatientList.add( tempBooth.getPatientDetail());
                        vaxStock = vaxStock - 1;
                    }
                    else {
                        System.out.println("Pfizer boot is not available at the movement ");
                        waitList.add(tempPatient);
                    }
                }


            }else if(menucode.equals("103") || menucode.toUpperCase().equals("RPB"))
            {
                //remove patient booth
                //remove patient booth
                System.out.println("Please Enter Booth Number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Please enter valid booth number :");
                    input.next(); // this is important!
                }
                tempBoothNum = input.nextInt();
                Booth removBooth =new Booth();
                Patient removePatient =new Patient();
                //removBooth.getPatientDetail().setFirstName("e");
                removBooth.setBStatus(BoothStatus.EMPTY);
                removBooth.setBoothID(tempBoothNum);
                removBooth.setPatientDetail(removePatient);
                if(tempBoothNum<=6){
                    //waitList.add(tempBooth);
                    if(waitList.size()>0)
                    {
                      Patient tempP=  GetPatient(waitList, tempBoothNum);
                      if(tempP!=null)
                      {
                          removBooth.setBStatus(BoothStatus.FULL);
                          removBooth.setBoothID(tempBoothNum);
                          removBooth.setPatientDetail(tempP);
                          PatientList.add(tempP);
                          VaxBooth[tempBoothNum ]=removBooth;
                          vaxStock =vaxStock-1;
                          waitList.remove(tempP);
                      }
                      else{
                          VaxBooth[tempBoothNum]=removBooth;
                      }
                    }
                    else{
                        VaxBooth[tempBoothNum]=removBooth;
                    }

                }
                else {
                    System.out.println("Please enter less than 6");
                }


            }else if(menucode.equals("104") || menucode.toUpperCase().equals("VPS"))
            {
                // view patient alphabetical
                sortPatientList(PatientList);
            }else if(menucode.equals("105") || menucode.toUpperCase().equals("SPD"))
            {
                // store programme  detail file
                saveVaxStock(vaxStock,PatientList,VaxBooth);
                //savePatientDetail(PatientList);

            }else if(menucode.equals("106") || menucode.toUpperCase().equals("LPD"))
            {
                // load program from file
                reloadFromFile();

            }else if(menucode.equals("107") || menucode.toUpperCase().equals("VRV"))
            {
                // view remain vax
                System.out.println("Remaining Vaccinations :" + String.valueOf( vaxStock));

            }else if(menucode.equals("108") || menucode.toUpperCase().equals("AVS"))
            {
                //vaxStockaddVaxStock

                System.out.println("Please Enter Vaccinations quantity : ");
                while (!input.hasNextInt()) {
                    System.out.println("Please enter valid booth number :");
                    input.next();
                }
                int vaxRelodStock = input.nextInt();
                addVaxStock(vaxRelodStock);

            }else if(menucode.equals("999") || menucode.toUpperCase().equals("EXT"))
            {
                System.exit(1);
            }
            else {

            }
        }

    }

    private  static Patient  GetPatient(LinkedList<Patient> tempLsit , int bootID){

        Patient tempP ;
      for(int i =0 ;i < tempLsit.size() ; i++){
          tempP = tempLsit.get(i);
          if((bootID==0 || bootID==1) && tempP.getVaxReqType()==VaxinType.AstraZeneca){
              return tempP;
          }
          else if((bootID==2|| bootID==3)&& tempP.getVaxReqType()==VaxinType.Sinopharm){
              return  tempP;
          }else if((bootID==4 || bootID==5) && tempP.getVaxReqType()==VaxinType.Pfizer){
              return  tempP;
          }
          return  null;
      }

        if(bootID ==0 || bootID==1){

        }
        return null;
       // waitList.add(tempBooth);
    }

    private static void reloadFromFile(){
        try{
            FileInputStream fi = new FileInputStream(new File(vaxStockFilePath));
            ObjectInputStream oi = new ObjectInputStream(fi);
            vaxStock  = (int) oi.readObject();
            ArrayList<Patient> tempPatientList  = (ArrayList<Patient>) oi.readObject();
            PatientList.addAll(tempPatientList);
            Booth tempArray[] = (Booth[])oi.readObject();
            //VaxBooth =tempArray;
            oi.close();
            fi.close();
        }
        catch(Exception ex){

        }


    }

    private static void  saveVaxStock(int stock,ArrayList<Patient> patients, Booth boot[]){
        try {
            FileOutputStream f = new FileOutputStream(new File(vaxStockFilePath));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(stock);
            o.writeObject(patients);
            o.writeObject(boot);
            o.close();
            f.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private  static void addVaxStock( int svalue )
    {
        //add vax to stock

        int tempStock =vaxStock+svalue;
        if(tempStock<=150)
        {
            vaxStock = vaxStock+ svalue;
        }
        else {
            System.out.println("Vaccinations stock should be les than or equal to 150 " );
        }
    }

    private static void sortPatientList(ArrayList<Patient> partiens){
        Patient temp;
        for(int i =0 ; i <partiens.size() ;i++)
        {
            for (int j = i + 1; j < partiens.size(); j++) {
                if (partiens.get(i).getFirstName().compareTo(partiens.get(j).getFirstName()) > 0) {
                    temp = partiens.get(i);
                    partiens.set(i, partiens.get(j));
                    partiens.set(j, temp);
                }
            }
        }

        for(int i=0 ;i<partiens.size();i++){
            System.out.println( "Patient Name :"+ partiens.get(i).getFirstName());
        }
    }
    private static void initialise (Booth hotelRef[] ) {
        Booth temp;
        Patient patientTemp;
        for (int x=0; x<6; x++ ){
            temp =new Booth();
            patientTemp =new Patient();
            temp.setBoothID(x);
            temp.setPatientDetail(patientTemp);
            temp.setBStatus(BoothStatus.EMPTY);
            hotelRef[x] =   temp;
        }
        System.out.println("initialise ");

    }
    private  static void getAllBootDetail(Booth vBoot[]){
        Patient tempPatient ;
        for (int x=0; x<6; x++ ){
            tempPatient = vBoot[x].getPatientDetail();
            System.out.println("Booth " + vBoot[x].getBoothID() +" "+ ((tempPatient.getFirstName()==null)? "Empty": tempPatient.getFirstName()));
        }
    }
    private static void getEmptyBoot(Booth vBoot[] ){
        Boolean isBoothEmpty=false;
        for (int x=0; x<6; x++ ){
            if(vBoot[x].getBStatus()==BoothStatus.EMPTY){
                System.out.println("Booth " + vBoot[x].getBoothID() + " is empty");
                isBoothEmpty=true;
            }
            if (!isBoothEmpty)System.out.println("All Booth full");
        }
    }

    private static void getmenuoption(){
        System.out.println("System Menu");
        System.out.println();
        System.out.println("100 or VVB: View all Vaccination Booths");
        System.out.println("101 or VEB: View all Empty Booths");
        System.out.println("102 or APB: Add Patient to a Booth");
        System.out.println("103 or RPB: Remove Patient from a Booth");
        System.out.println("104 or VPS: View Patients Sorted in alphabetical order (Do not use library sort routine");
        System.out.println("105 or SPD: Store Program Data into file");
        System.out.println("106 or LPD: Load Program Data from file");
        System.out.println("107 or VRV: View Remaining Vaccinations");
        System.out.println("108 or AVS: Add Vaccinations to the");
    }

}
