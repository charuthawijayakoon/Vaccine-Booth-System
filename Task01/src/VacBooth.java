import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VacBooth {
   private static String customerName;
   private static int boothNum = 0;
   private static  int tempBoothNum =0;
   private static int vaxStock =150;
   private static String[] VaxBooth = new String[7];
   private static ArrayList<String> PatientList = new ArrayList<String>();

   private static String vaxStockFilePath =new File(".").getAbsolutePath()+"//vaxtbl.txt";


   //Making the Menu
   public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        getmenuoption();
        initialise(VaxBooth); //better to initialise in a procedure
        while (boothNum < 6)
        {
            System.out.println("Please Enter the Menu Code : ");
            String menucode = input.nextLine();

            if(menucode.equals("100") || menucode.toUpperCase().equals("VVB"))
            {
                //View all Vaccination Booths
                getAllBootDetail(VaxBooth);
            }
           else if(menucode.equals("101") || menucode.toUpperCase().equals("VEB"))
            {
               // View all Empty Booths
                getEmptyBoot(VaxBooth);
            }
            else if(menucode.equals("102") || menucode.toUpperCase().equals("APB"))
            {
                // Add Patients to booths
                System.out.println("Please Enter a Booth Number : ");
                while (!input.hasNextInt()) {                                 //https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner
                    System.out.println("Please enter a valid Booth Number :");
                    input.next(); // this is important!
                }
                tempBoothNum = input.nextInt();
                if(tempBoothNum<6) {

                    String bootAvb = VaxBooth[tempBoothNum];
                    if (bootAvb.equals("e")) {
                        System.out.println("Please Enter the Patient's Name : ");
                        customerName = input.next();
                        VaxBooth[tempBoothNum] = customerName;
                        PatientList.add(customerName);
                        // boothNum = boothNum+1;
                        vaxStock = vaxStock - 1;
                    } else {
                        System.out.println("Selected Booth is not available at the movement , please select an another Booth : ");
                    }
                }
                else{
                    System.out.println("Error in Booth number , Booths are available only from 0-5 ");
                }



            }else if(menucode.equals("103") || menucode.toUpperCase().equals("RPB"))
            {
                //remove patient booth
                System.out.println("Please Enter Booth Number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Please enter a valid Booth Number :");
                    input.next(); // this is important!
                }
                tempBoothNum = input.nextInt();

                if(tempBoothNum<=6){
                    VaxBooth[tempBoothNum]="e";
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
                saveVaxStock(vaxStock,PatientList);
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
                    System.out.println("Please enter a valid booth Number :");
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

    //
    private static void  saveVaxStock(int stock,ArrayList patients){
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(vaxStockFilePath));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            objOutput.writeObject(stock);
            objOutput.writeObject(patients);
            objOutput.close();
            fileOutput.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {        //
            ex.printStackTrace();
        }
    }

    //
    private static void reloadFromFile(){
        try{
            FileInputStream fileInput = new FileInputStream(new File(vaxStockFilePath));
            ObjectInputStream objInput = new ObjectInputStream(fileInput);
            vaxStock  = (int) objInput.readObject();
            ArrayList<String> tempPatientList  = (ArrayList<String>) objInput.readObject();
            PatientList.addAll(tempPatientList);
            objInput.close();
            fileInput.close();
        }
        catch(Exception ex){

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
            System.out.println("Vaccinations Stock should be les than or equal to 150 " );
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


    private  static void getAllBootDetail(String vBoot[]){
        for (int x=0; x<6; x++ ){
            System.out.println("Booth " + x +" "+ vBoot[x]);
        }
    }

    //
   private static void getEmptyBoot(String vBoot[] ){
       Boolean isbootEmpty=false;
           for (int x=0; x<6; x++ ){
               if (vBoot[x].equals("e")){
                   System.out.println("Booth " + x + " is empty");
                   isbootEmpty =true;
               }
           }
           if(!isbootEmpty)System.out.println("All booth full102");
    }

    //
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

    //100

    private static void initialise (String hotelRef[]) {
        for (int x=0; x<6; x++ ) hotelRef[x] = "e1";
        System.out.println("initialise ");

    }
}

//Referances;
       //https://www.javatpoint.com/java-int-to-string
       //https://www.studytonight.com/java-examples/check-if-input-is-integer-in-java
       //https://www.javatpoint.com/java-string-to-int

       //https://www.geeksforgeeks.org/java-program-to-sort-names-in-an-alphabetical-order/
       //https://beginnersbook.com/2018/10/java-program-to-sort-strings-in-an-alphabetical-order/

       //https://www.w3schools.com/java/java_arraylist.asp
       //https://www.javatpoint.com/java-arraylist
       //https://stackoverflow.com/questions/30413227/how-to-read-and-write-an-object
       //https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
       //https://howtodoinjava.com/java/collections/arraylist/merge-arraylists/
       //https://www.w3schools.com/java/java_encapsulation.asp
       //