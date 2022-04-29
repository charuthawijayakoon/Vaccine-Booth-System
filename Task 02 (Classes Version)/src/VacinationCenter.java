import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VacinationCenter {

    private static int boothNum = 0;
    private static Booth[] VaxBooth = new Booth[7];
    private static String customerName;

    private static  int tempBoothNum =0;
    private static int vaxStock =150;

    private static ArrayList<String> PatientList = new ArrayList<String>();

    private static String vaxStockFilePath =new File(".").getAbsolutePath()+"//vaxtbl.txt";



    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        getmenuoption();
        initialise(VaxBooth); //better to initialise in a procedure
        Booth boothObj =new Booth();
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
                System.out.println("Please Enter Booth Number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Please enter valid booth number :");
                    input.next(); // this is important!
                }
                tempBoothNum = input.nextInt();
                if(tempBoothNum<6) {
                    Booth tempBooth =new Booth();
                    Booth bootAvb = VaxBooth[tempBoothNum];
                    if (bootAvb.getCustomerName().equals("e")) {
                        System.out.println("Please Enter Patient Name : ");
                        customerName = input.next();
                        tempBooth.setCustomerName(customerName);
                        tempBooth.setBoothID(tempBoothNum);
                        VaxBooth[tempBoothNum] = tempBooth;
                        PatientList.add(customerName);
                        // boothNum = boothNum+1;
                        vaxStock = vaxStock - 1;
                    } else {
                        System.out.println("Selected boot is not available at the movement , please select available boot number : ");
                    }
                }
                else{
                    System.out.println("Error in boot number , only available 0-5 booth");
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
                removBooth.setCustomerName("e");
                removBooth.setBoothID(tempBoothNum);
                if(tempBoothNum<=6){
                    VaxBooth[tempBoothNum]=removBooth;
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

    private static void reloadFromFile(){
        try{
            FileInputStream fi = new FileInputStream(new File(vaxStockFilePath));
            ObjectInputStream oi = new ObjectInputStream(fi);
            vaxStock  = (int) oi.readObject();
            ArrayList<String> tempPatientList  = (ArrayList<String>) oi.readObject();
            PatientList.addAll(tempPatientList);
            Booth tempArray[] = (Booth[])oi.readObject();
            VaxBooth =tempArray;
            oi.close();
            fi.close();
        }
        catch(Exception ex){

        }


    }

    private static void  saveVaxStock(int stock,ArrayList patients, Booth boot[]){
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

    private static void sortPatientList(ArrayList partiens){
        String temp="";
        for(int i =0 ; i <partiens.size() ;i++)
        {
            for (int j = i + 1; j < partiens.size(); j++) {
                if (partiens.get(i).toString().compareTo(partiens.get(j).toString()) > 0) {
                    temp = partiens.get(i).toString();
                    partiens.set(i, partiens.get(j));
                    partiens.set(j, temp);
                }
            }
        }

        for(int i=0 ;i<partiens.size();i++){
            System.out.println( "Patient Name :"+ partiens.get(i).toString());
        }
    }
    private static void initialise (Booth hotelRef[]) {
        Booth temp;
        for (int x=0; x<6; x++ ){
            temp =new Booth();
            temp.setBoothID(x);
            temp.setCustomerName("e");
            hotelRef[x] =   temp;
        }
        System.out.println("initialise ");
    }
    private  static void getAllBootDetail(Booth vBoot[]){
        for (int x=0; x<6; x++ ){
            System.out.println("Booth " + vBoot[x].getBoothID() +" "+ vBoot[x].getCustomerName());
        }
    }
    private static void getEmptyBoot(Booth vBoot[] ){
        Boolean isBoothEmpty=false;
        for (int x=0; x<6; x++ ){
            if(vBoot[x].getCustomerName().equals("e")){
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
