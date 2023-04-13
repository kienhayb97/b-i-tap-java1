
import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        String choose = null;
        Boolean exit = false;
        Validate validate = new Validate();
        int studentId;
        showMenu();
        while (true){

            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    Validate.add();
                    break;
                case "2":
                    studentId = validate.inputId();
                    validate.edit(studentId);
                    break;
                case "3":
                    studentId = validate.inputId();
                    validate.delete(studentId);
                case "4":
                    validate.SortStudentByMediumScore();
                    break;
                case "5" :
                    Validate.MediumScore();
                    break;
                case "6":
                    Validate.checkEnum();
                    break;
                case "0":
                    System.out.println("exited!");
                    exit = true;
                    break;
                default:
                    System.out.println("invalid! please choose action in below menu:");
                    break;

            } if (exit) {
                break;
            }
            showMenu();
        }

    }

    public static void showMenu() {
        System.out.println("-----------menu------------");
        System.out.println("1. Add student.");
        System.out.println("2. Edit student by id.");
        System.out.println("3. Delete student by id.");
        System.out.println("4. Sort Student By Medium Score.");
        System.out.println("5. Show student.");
        System.out.println("6. Show student.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }



}