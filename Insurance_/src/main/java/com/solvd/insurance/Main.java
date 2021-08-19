package com.solvd.insurance;


import com.solvd.insurance.DAO.classes.Employee;
import com.solvd.insurance.DAO.mysql.jdbc.EmployeesDAO;
import com.solvd.insurance.InsuranceCompanies.AbstractCompany;
import com.solvd.insurance.InsuranceCompanies.AbstractInsuranceType;
import com.solvd.insurance.InsuranceCompanies.Farmers;
import com.solvd.insurance.InsuranceCompanies.Geico;
import com.solvd.insurance.InsuranceCompanies.TrippleA;
import com.solvd.insurance.TasksForThreads.TaskGetCompanyName;
import com.solvd.insurance.TasksForThreads.TaskUnderwriting;
import com.solvd.insurance.business.BusinessInsurance;
import com.solvd.insurance.customer.Person;
import com.solvd.insurance.enums.InsuranceCompany;
import com.solvd.insurance.enums.InsurancePolicyOptions;
import com.solvd.insurance.exceptions.InvalidUserInputException;
import com.solvd.insurance.exceptions.NoSuchInsuranceCompanyException;
import com.solvd.insurance.functionalInterfaceLambda.IFuncGetUserInputAsDouble;
import com.solvd.insurance.functionalInterfaceLambda.IFuncGetUserInputAsInteger;
import com.solvd.insurance.functionalInterfaceLambda.IFuncGetUserInputAsString;
import com.solvd.insurance.insuranceTypes.home.RentersForHome;
import com.solvd.insurance.insuranceTypes.life.Life;
import com.solvd.insurance.insuranceTypes.pets.Pets;
import com.solvd.insurance.insuranceTypes.vehicles.home.Home;
import com.solvd.insurance.insuranceTypes.vehicles.vehicles.Boat;
import com.solvd.insurance.insuranceTypes.vehicles.vehicles.Car;
import com.solvd.insurance.insuranceTypes.vehicles.vehicles.Motorcycle;
import com.solvd.insurance.interfaces.BoatRisk;
import com.solvd.insurance.interfaces.IRiskyClient;
import com.solvd.insurance.interfaces.IUnderwrite;
import com.solvd.insurance.interfaces.MotorcycleRisk;
import com.solvd.insurance.interfaces.UnderwriteBusiness;
import com.solvd.insurance.interfaces.UnderwriteCar;
import com.solvd.insurance.interfaces.UnderwriteHome;
import com.solvd.insurance.interfaces.UnderwriteRentersInsurance;
import com.solvd.insurance.policyOperations.Policy;
import com.solvd.insurance.policyOperations.eventBond;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
  public static final Logger LOGGER = LogManager.getLogger(Main.class);
  public static final String  PROMPT_MESSAGE_BEGIN = "You need to send details about your ";
  public static final String  PROMPT_MESSAGE_END = "policy requirements.";
  public static final String  CAR = " Car ";
  public static final String  BUSINESS= " BUSINESS ";
  public static final List<String>  CAR_INSURANCE_QUESTIONS = Arrays.asList("Car year?",
      "Price/value?", "Accidents history?", "Miles driven?", "Car make?", " ");
  public static final List<String>  BUSINESS_INSURANCE_QUESTIONS = Arrays.asList("Name",
      "Business Costs?", "Business Years?", " ");


  public static void main(String[] args)
      throws ArithmeticException, NoSuchInsuranceCompanyException, InvalidUserInputException, SQLException {

    // ********* BEGINNING OF DAO DEMONSTRATION ********** //
    //creating and Employee object
    Employee employee = new Employee();
    employee.setAge(27);
    employee.setId(131);
    employee.setName("John");
    employee.setLastName("Smith");
    //creating Employee DAO
    EmployeesDAO db = new EmployeesDAO();
    //CREATE a new row in the DB
    db.createItem(employee);
    //GET to display the change
    LOGGER.info("Created row -> "+db.getItemById(131).toString());
    //UPDATE current employee age and last then send update to MySQL db
    employee.setAge(131);
    employee.setLastName("Washington");
    db.updateItem(employee);
    //GET to display the change
    LOGGER.info("Updated row -> " + db.getItemById(131).toString());
    //DELETE employee from MySQL db
    db.deleteItem(131);
    //GET to display the change
    LOGGER.info(db.getItemById(131).toString());

    // ********* END OF DAO DEMONSTRATION ********** //


    //Declaration of thread pool to send tasks to. This thread pool size of 3
    ExecutorService pool = Executors.newFixedThreadPool(3);
    //Lambda functional interfaces to collect user input for different types such as
    //String, boolean, double. You can add more types if needed
    IFuncGetUserInputAsInteger intValue  = (String s) -> Integer.parseInt(s);
    IFuncGetUserInputAsDouble doubleValue  = (String s) -> Double.parseDouble(s);
    IFuncGetUserInputAsString StringValue  = (String s) -> s;
    Scanner scanner = new Scanner(System.in);
    boolean quit = false;
    int choice =0;


    LOGGER.info("Would you like a quote from 'Farmers' or 'Geico' or `TripleA`?");
    String companyUserInput = scanner.nextLine();
    AbstractCompany company = getSelectedInsuranceCompany(companyUserInput);
    List<String> personInfo = Arrays.asList(userPromptString("Enter your Name, Age, Address (optional). Comma separated", scanner).trim().split(","));
    Person person = createPerson(personInfo);
    printPrompt();
    while(!quit) {
      LOGGER.info("Enter your choice: ");
      choice = scanner.nextInt();
      InsurancePolicyOptions userSelectedOption = InsurancePolicyOptions.getName(choice);
      scanner.nextLine();
      switch (userSelectedOption) {
        case PRINT_ALL:
          printPrompt();
          break;
        case CAR:
          questionsPrompt(CAR_INSURANCE_QUESTIONS, CAR.toString());
          int carYear;
          double carCost;
          boolean isAccidents;
          double milesDriven;
          String make;

          try {
            LOGGER.info(PROMPT_MESSAGE_BEGIN + CAR + PROMPT_MESSAGE_END);
            LOGGER.info("Enter car year");
            carYear = intValue.getInput(scanner.nextLine());
            LOGGER.info("Enter car price/value");
            carCost = doubleValue.getInput(scanner.nextLine());
            LOGGER.info("Have you had accidents yes/no");
            isAccidents = false;
            if (StringValue.getInput(scanner.nextLine()).equals("yes")){
              isAccidents=true;
            }
            LOGGER.info("Miles driven on odometer ");
            milesDriven = doubleValue.getInput(scanner.nextLine());
            LOGGER.info("Car make");
            make = StringValue.getInput(scanner.nextLine());
          } catch (Exception e){
            throw new InvalidUserInputException("User input can't be processed");
          }

          LOGGER.info(company.getPersonalProfileInfo(person));

          AbstractInsuranceType quoteTypeCar = new Car(carYear, carCost, isAccidents, milesDriven, make, company.generatePolicyNumber());

          //Creating a Task/Thread for printing selected insurance company in a thread
          Runnable runnableTaskGetCompanyName = new TaskGetCompanyName(company);
          Runnable runnableTaskDoUnderwriting = new TaskUnderwriting(company, quoteTypeCar, new UnderwriteCar());

          pool.execute(runnableTaskGetCompanyName);
          pool.execute(runnableTaskDoUnderwriting);

          LOGGER.info(company.getQuoteDetails(quoteTypeCar));

          quit=true;
          break;
        case BUSINESS:
          LOGGER.info(PROMPT_MESSAGE_BEGIN + BUSINESS + PROMPT_MESSAGE_END);
          LOGGER.info("You will be asked the following questions about your Business");
          //Streams
          BUSINESS_INSURANCE_QUESTIONS.stream().forEach(e ->
              {
                LOGGER.info(e);
              }
          );

          int businessYear;
          double businessCost;
          String name;
          try {

            LOGGER.info("What is your name");
            name = StringValue.getInput(scanner.nextLine());
            LOGGER.info("Enter your business cost");
            businessCost = doubleValue.getInput(scanner.nextLine());
            LOGGER.info("Enter your business year");
            businessYear = intValue.getInput(scanner.nextLine());
          } catch (Exception e){
            throw new InvalidUserInputException("User input can't be processed");
          }

          LOGGER.info(company.getPersonalProfileInfo(person));

          AbstractInsuranceType quoteTypeBusiness = new BusinessInsurance(name, businessCost, businessYear);

          //Creating a Thread for printing selected insurance company in a thread
          Runnable runnableTaskGetBusinessCompanyName = new TaskGetCompanyName(company);
          Runnable runnableTaskDoUnderwritingBusiness = new TaskUnderwriting(company, quoteTypeBusiness, new UnderwriteBusiness());
          // Thread pool
          pool.execute(runnableTaskGetBusinessCompanyName);
          pool.execute(runnableTaskDoUnderwritingBusiness);

          LOGGER.info(company.getQuoteDetails(quoteTypeBusiness));
          quit=true;
          break;
        case BOAT:
          LOGGER.info("You need to send details about your Boat policy requirements.");
          break;
        case LIFE:
          LOGGER.info("You need to send details about your Life policy requirements.");
          break;
        case PETS:
          LOGGER.info("You need to send details about your Pets policy requirements.");

          break;
        case HOME:
          LOGGER.info("You need to send details about your Home policy requirements.");
          break;
        case RENTERS:
          LOGGER.info("You need to send details about your Renters policy requirements.");
          break;
        case BOND:
          LOGGER.info("For Bond policy proceed to eventBond policy creation");
        case NONE:
          quit = true;
          break;
      }
      pool.shutdown();
    }

    try {

      LOGGER.info("================ SEPARATOR ==================================");

      Person vasyl = new Person("vasyl Romaniuk", 25, "12 Happy Street");
      Car toyota = new Car(1998, 68.90, false, 18, "Sienna", 123456);
      Motorcycle motor = new Motorcycle(20, 4.98, true, 12, "bmw");
      Boat boat1 = new Boat(1980, 3, false, 14, "Rivera", 34);
      Life life1 = new Life(25, false, false, 5);
      Life life2 = new Life(25, false, false, -5);
      RentersForHome renters = new RentersForHome(50, 150, true, 24, 999);
      Home home1 = new Home(70, 40, false, 567);
      Pets pet1 = new Pets("Bars", 20);
      Policy policy1 = new Policy(123456, vasyl, toyota, home1, boat1, motor, life1, "June 2021", "December 2021", "Vasyl", renters, pet1);
      Policy policy2 = new Policy(123456, vasyl, toyota, home1, boat1, motor, life1, "June 2021", "December 2021", "Vasyl", renters, pet1);
      Policy policy3 = new Policy(12345, vasyl, toyota, home1, boat1, motor, life1, "June 2021", "December 2021", "Vasyl", renters, pet1);


      LOGGER.info("policy1 does equal policy2: " + policy1.equals(policy2));

      LOGGER.info("toyota cost: $" + toyota.getCost());

      LOGGER.info("motorcycle cost: $" + motor.getCost());

      LOGGER.info("boat cost: $" + boat1.getCost());

      LOGGER.info("life insurance cost: $" + life1.getCost());

      LOGGER.info("renters insurance cost: $" + renters.getHomeCost());

      LOGGER.info("home insurance cost: $" + home1.getHomeCost());

      LOGGER.info("this is the grand cost of policy1: $" + policy1.grandTotalCost());

      BusinessInsurance business = new BusinessInsurance("Cleaning", 12, 5);
      LOGGER.info("this is a business cost: " + business.totalCost());

      double total = policy1.grandTotalCost() + business.totalCost();
      LOGGER.info("this is a total of policy1 and business: " + total);

      //equal Override that checks policy number
      LOGGER.info("does policy1 equal policy2? : " + policy1.equals(policy2));
      LOGGER.info("does policy1 equal policy3? : " + policy1.equals(policy3));

      //hashCode Override
      Set<Policy> set = new HashSet<>();
      set.add(policy1);
      set.add(policy2);
      LOGGER.info("Set has how many elements: " + set.size());

      //toString Override
      LOGGER.info(policy1);

      //1st Interface check
      IUnderwrite forCarUnderwriting;
      forCarUnderwriting = new UnderwriteCar();

//            Car underwriting described above and can be removed
//            forCarUnderwriting.description();
//            int num = toyota.getPolicyNum();
//            forCarUnderwriting.policy(num);
//            double cost = toyota.getCost();
//            forCarUnderwriting.startPolicyCheck(num, cost);

      //2nd Interface check
      IUnderwrite forHomeUnderwriting;
      forHomeUnderwriting = new UnderwriteHome();
      forHomeUnderwriting.description();
      int num2 = home1.getHomePolicyNum();
      forHomeUnderwriting.policy(num2);
      double cost2 = home1.getHomeCost();
      forHomeUnderwriting.startPolicyCheck(num2, cost2);

      //3rd Interface check
      IUnderwrite forRenters;
      forRenters = new UnderwriteRentersInsurance();
      forRenters.description();
      int num3 = renters.getHomePolicyNum();
      forRenters.policy(num3);
      double cost3 = renters.getHomeCost();
      forRenters.startPolicyCheck(num3, cost3);

      //4th Interface check
      IRiskyClient forMotorcycle;
      forMotorcycle = new MotorcycleRisk();
      forMotorcycle.description();
      if (motor.isAccidents()) {
        forMotorcycle.isRisky();
        LOGGER.info("we have accident, stop writing!");
      } else {
        LOGGER.info("No risk!");
      }

      //5th Interface check
      IRiskyClient forBoat;
      forBoat = new BoatRisk();
      forBoat.description();
      if (boat1.getBoatSize() > 1) {
        LOGGER.info("We can proceed, the size of the boat is checked!");
      } else {
        LOGGER.info("This is not a boat!");
      }


      //1st try catch with results going to a pre-created file
      try (PrintStream myFileConsole = new PrintStream(new File("/Users/vasylromaniuk/Documents/java_console.txt"))) {
        if (policy1.equals(policy2)) {
          System.setOut(myFileConsole);
          myFileConsole.print("this is the info on policy 1 and policy 2. Policy number for the 1st policy is: " + policy1.getPolicyNumber() + "...." + " Policy number for the 2nd policy is: " + policy2.getPolicyNumber());
        }
      } catch (FileNotFoundException exception) {
        LOGGER.warn(exception);
      }


      //2nd try catch
      try {
        if (!policy1.equals(policy3)) {
          LOGGER.warn("this is a warning that both policies are NOT the same: " + policy1.getName());
        }
      } catch (Exception e) {
        LOGGER.fatal("policies aren't equal");
      }

      //3rd try catch
      try {

        if (policy1.equals(policy2)) {
          LOGGER.info("this is info that both policies are the same: " + policy1.getName());
        }
      } catch (Exception e) {
        LOGGER.fatal("policies are equal");

      }

      //throws
      try {
        if(life2.getCoverageAmount() < 0) {
          double totalZero = life2.getCost() / 0;
          LOGGER.warn("Total of this division is: " + totalZero);
          throw new ArithmeticException("ZERO:");
        }


      } catch (ArithmeticException e) {
        LOGGER.error("this is the divide by 0 " + e.getMessage());
      }


      //throws
      try {
        if (motor.getCost() < 5) {
          LOGGER.warn("the motorcycle cost is: " + motor.getCost() / 0);
          throw new ArithmeticException("The coverage is less than $5!");
        }
      }catch (ArithmeticException e){
        LOGGER.fatal("cost is too low: " + e.getMessage());
      }

      //abstract class
      eventBond wedding = new eventBond(12000);
      LOGGER.info("the bond insurance cost is: " + wedding.calculate());

    }catch(Exception e){
      LOGGER.fatal("to many exceptions!");

    }


  }

  public static void printPrompt(){
    LOGGER.info("\t 0 - To print available options.");
    LOGGER.info("\t 1 - To buy Car insurance.");
    LOGGER.info("\t 2 - To buy Business insurance.");
    LOGGER.info("\t 3 - To buy Boat insurance.");
    LOGGER.info("\t 4 - To buy Life insurance.");
    LOGGER.info("\t 5 - To buy Pets insurance.");
    LOGGER.info("\t 6 - To buy Home insurance.");
    LOGGER.info("\t 7 - To buy Renters insurance.");
    LOGGER.info("\t 8 - To buy Bond insurance.");
    LOGGER.info("\t 9 - To quit.");

  }



  private static void CompanyClients() {
    List<String> myList = new ArrayList<>();
    myList.add("Uber");
    myList.add("Lyft");
    myList.add("Amazon");
    LOGGER.info("Top 3 Company Clients (List) ");
    print(myList);

    Set<String> mySet = new HashSet<>();
    mySet.add("Uber");
    mySet.add("Lyft");
    mySet.add("Amazon");
    LOGGER.info("Top 3 Company Clients (Set) ");
    print(mySet);

    Queue<String> myQueue = new LinkedList<>();
    myQueue.add("Uber");
    myQueue.add("Lyft");
    myQueue.add("Amazon");
    LOGGER.info("Top 3 Company Clients (Queue) ");
    print(myQueue);

    Map<Integer, String> myMap = new HashMap<>();
    myMap.put(52376000, "Uber");
    myMap.put(51377111, "Lyft");
    myMap.put(40790030, "Amazon");
    LOGGER.info("Top 3 Company Clients (Map) ");
    printDni(myMap.keySet());
    print(myMap.values());

    LinkedList<String> myLinkedList = new LinkedList<>();
    myLinkedList.add("Uber");
    myLinkedList.add("Lyft");
    myLinkedList.add("Amazon");
    LOGGER.info("Top 3 Company Clients (Linked List) ");
    LOGGER.info("Company clients: " + myLinkedList);
    Collections.reverse(myLinkedList);
    LOGGER.info("Company clients in reverse order: " + myLinkedList);

    List<Long> myListPhone = new ArrayList<>();
    myListPhone.add(4155174441L);
    myListPhone.add(4145456781L);
    myListPhone.add(4155468281L);
    LOGGER.info("Top 3 Company clients Phones ");
    LOGGER.info("Company clients: " + myListPhone);
  }

  public static void print(Collection<String> collection) {
    collection.forEach(element -> {
      LOGGER.info("Company client Name: " + element);
    });
  }
  public static void printDni(Collection<Integer> collection) {
    collection.forEach(element -> {
      LOGGER.info("Company client DNI: " + element);
    });
    //  LinkedListTest();
  }

  //   private static void LinkedListTest() {
  //       MyLinkedList<Person> linkedList=new MyLinkedList<>();
//
  //      linkedList.add(new Person("Ivan Ivanov", 25, "22 10th Street"));
  //      linkedList.add(new Person("Petr Petrov", 40, "10 Grand Street"));
  //      linkedList.add(new Person("Nick Nicolaev", 36, "24 Watt Road"));
  //      linkedList.addOne(new Person("Alex Romaniuk", 32,"1045 Madison Blvd"));

  //       logger.info("Persons : "+linkedList);

  //   }
  private static String userPromptString(String promptText, Scanner scanner){
    LOGGER.info(promptText);
    return scanner.nextLine();
  }

  private static Person createPerson(List<String> personInfo) throws InvalidUserInputException {
    Person person=null;
    if (personInfo.size()==2){
      person = new Person(personInfo.get(0), Integer.parseInt(personInfo.get(1)));

    }else if (personInfo.size()==3) {
      person = new Person(personInfo.get(0), Integer.parseInt(personInfo.get(1).trim()), personInfo.get(2));
    } else {
      throw new InvalidUserInputException ("Person info is missing, can't proceed");

    }
    return person;
  }

//    private static void processingCar(){
//      LOGGER.info("You will be asked the following questions about your Car");
//      //Streams example
//      CAR_INSURANCE_QUESTIONS.stream().forEach(e ->
//          {
//            LOGGER.info(e);
//          }
//      );
//
//      int carYear;
//      double carCost;
//      boolean isAccidents;
//      double milesDriven;
//      String make;
//      try {
//
//        LOGGER.info(PROMPT_MESSAGE_BEGIN + CAR + PROMPT_MESSAGE_END);
//        LOGGER.info("Enter car year");
//        carYear = intValue.getInput(scanner.nextLine());
//        LOGGER.info("Enter car price/value");
//        carCost = doubleValue.getInput(scanner.nextLine());
//        LOGGER.info("Have you had accidents yes/no");
//        isAccidents = false;
//        if (StringValue.getInput(scanner.nextLine()).equals("yes")){
//          isAccidents=true;
//        }
//        LOGGER.info("Miles driven on odometer ");
//        milesDriven = doubleValue.getInput(scanner.nextLine());
//        LOGGER.info("Car make");
//        make = StringValue.getInput(scanner.nextLine());
//      } catch (Exception e){
//        throw new InvalidUserInputException("User input can't be processed");
//      }
//
//      LOGGER.info(company.getPersonalProfileInfo(person));
//
//      AbstractInsuranceType quoteTypeCar = new Car(carYear, carCost, isAccidents, milesDriven, make, company.generatePolicyNumber());
//      //Creating a Task/Thread for printing selected insurance company in a thread
//      Runnable runnableTaskGetCompanyName = new TaskGetCompanyName(company);
//      Runnable runnableTaskDoUnderwriting = new TaskUnderwriting(company, quoteTypeCar, new UnderwriteCar());
//
//      pool.execute(runnableTaskGetCompanyName);
//      pool.execute(runnableTaskDoUnderwriting);
//
//      LOGGER.info(company.getQuoteDetails(quoteTypeCar));
//    }

  private static void questionsPrompt(List<String> questions, String policyType){

    LOGGER.info("You will be asked the following questions about your "+policyType);
    //Streams example
    questions.stream().forEach(e ->
        {
          LOGGER.info(e);
        }
    );
  }

  private static AbstractCompany getSelectedInsuranceCompany (String companyUserInput)
      throws NoSuchInsuranceCompanyException {
    AbstractCompany company;

    // Enums
    if (InsuranceCompany.GEICO.name().equalsIgnoreCase(companyUserInput)){
      return new Geico();
    }else  if (InsuranceCompany.FARMERS.name().equalsIgnoreCase(companyUserInput)) {
      return new Farmers();
    }
    else if (InsuranceCompany.TRIPLEA.name().equalsIgnoreCase(companyUserInput)){
      return new TrippleA();
    } else {
      throw new NoSuchInsuranceCompanyException("There is no insurance company with this name");
    }

  }
}
