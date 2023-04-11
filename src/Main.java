
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
//                    editStudent();
                    studentId = validate.inputId();
                    validate.edit(studentId);
                    break;
                case "3":
                    studentId = validate.inputId();
                    validate.delete(studentId);
//                case "0":
//                    System.out.println("exited!");
//                    exit = true;
//                    break;
                default:
                    System.out.println("invalid! please choose action in below menu:");
                    break;

            } if (exit) {
                break;
            }
            // show menu
            showMenu();
            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("-----------menu------------");
        System.out.println("1. Add student.");
        System.out.println("2. Edit student by id.");
        System.out.println("3. Delete student by id.");
        System.out.println("5. Sort student by id.");
        System.out.println("6. Show student.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
    public static void editStudent(){
        System.out.println("----List Student----");
        System.out.println("1. edit name");
        System.out.println("2. edit birthday");
        System.out.println("3. edit address");
        System.out.println("4. edit height");
        System.out.println("5. edit weight");
        System.out.println("6. edit studentCode");
        System.out.println("7. edit school");
        System.out.println("8. edit schoolYear");
        System.out.println("9. edit mediumScore");
        System.out.println("0. exit.");
        System.out.println("---------------------");
        System.out.println("Please choose: ");

    }

}