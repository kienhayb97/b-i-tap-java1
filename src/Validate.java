import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Validate {
    public static Scanner scanner = new Scanner(System.in);
    private static List<Student> studentList;
    private static StudentDao studentDao;

    public static final Double LOWER_HEIGHT_LIMIT = 50d;
    public static final Double UPPER_HEIGHT_LIMIT = 300d;
    public static final Double LOWER_WEIGHT_LIMIT = 5d;
    public static final Double UPPER_WEIGHT_LIMIT = 1000d;
    public static final Integer MAX_NAME = 100;
    public static final Integer MAX_LENGTH = 300;

    public Validate() {
        studentDao = new StudentDao();
        studentList = studentDao.read();
    }

    public static void add() {
        Long id = getMaxId();
        String name = inputName();
        String birthday = inputBirthday();
        String address = inputAddress();
        Double height = inputHeight();
        Double weight = inputWeight();
        String studentCode = inputStudentCode();
        String school = inputSchool();
        int schoolYear = inputSchoolYear();
        Double mediumScore = inputMediumScore();
        Student student = new Student(id, name, birthday, address, height, weight, studentCode, school, schoolYear, mediumScore);
        studentList.add(student);
        studentDao.write(studentList);
        System.out.println(student.toString());
    }
    public void edit(int id) {
        boolean isExisted = false;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                isExisted = true;
                String choose = null;
                Boolean exit = false;
                editStudent();
                while (true) {
                    choose = scanner.nextLine();
                    switch (choose) {
                        case "1":
                            studentList.get(i).setName(inputName());
                            break;
                        case "2":
                            studentList.get(i).setBirthday(inputBirthday());
                            break;
                        case "3":
                            studentList.get(i).setAddress(inputAddress());
                            break;
                        case "4":
                            studentList.get(i).setHeight(inputHeight());
                            break;
                        case "5":
                            studentList.get(i).setWeight(inputWeight());
                            break;
                        case "6":
                            studentList.get(i).setStudentCode(inputStudentCode());
                            break;
                        case "7":
                            studentList.get(i).setSchool(inputSchool());
                            break;
                        case "8":
                            studentList.get(i).setSchoolYear(inputSchoolYear());
                            break;
                        case "9":
                            studentList.get(i).setMediumScore(inputMediumScore());
                            break;
                        case "0":
                            System.out.println("exited!");
                            exit = true;
                            break;
                        default:
                            System.out.println("invalid! please choose action in below menu:");
                            break;

                    }
                    if (exit) {
                        break;
                    }
                    studentDao.write(studentList);
                    editStudent();
                }
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            studentDao.write(studentList);
        }
    }

    public static void editStudent() {
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

    public void delete(int id) {
        Student student = null;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                student = studentList.get(i);
                break;
            }
        }
        if (student != null) {
            studentList.remove(student);
            studentDao.write(studentList);
        } else {
            System.out.printf("id = %d not existed.\n", id);
        }
    }
    public static void MediumScore() {
        Map<String, Integer> map = new HashMap<>();
        int countEXCELLENT = 0;
        int countMEDIUM = 0;
        int countWEAK = 0;
        int countLEAST = 0;
        int countRATHER = 0;
        int countGOOD = 0;
        for (Student student : studentList) {
            if (AcademicAbility.LEAST.equals(student.getAcademicAbility())) {
                countLEAST++;
            } else if (AcademicAbility.WEAK.equals(student.getAcademicAbility())) {
                countWEAK++;
            } else if (AcademicAbility.MEDIUM.equals(student.getAcademicAbility())) {
                countMEDIUM++;
            } else if (AcademicAbility.RATHER.equals(student.getAcademicAbility())) {
                countRATHER++;
            } else if (AcademicAbility.GOOD.equals(student.getAcademicAbility())) {
                countGOOD++;
            } else if (AcademicAbility.EXCELLENT.equals(student.getAcademicAbility())) {
                countEXCELLENT++;
            }
        }
        map.put(AcademicAbility.LEAST.name(), 100 * countLEAST / (studentList.size()));
        map.put(AcademicAbility.WEAK.name(), 100 * countWEAK / studentList.size());
        map.put(AcademicAbility.MEDIUM.name(), 100 * countMEDIUM / studentList.size());
        map.put(AcademicAbility.RATHER.name(), 100 * countRATHER / studentList.size());
        map.put(AcademicAbility.GOOD.name(), 100 * countGOOD / studentList.size());
        map.put(AcademicAbility.EXCELLENT.name(), 100 * countEXCELLENT / studentList.size());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("học lực " + entry.getKey() + " phần trăm học lực: " + entry.getValue() + " %");
        }
        System.out.println("-----------------------------");
        System.out.println("phần trăm theo thứ tự tăng dần");
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        list.forEach((fruit) -> System.out.println(fruit.getKey() + " -> " + fruit.getValue() + " %"));
        System.out.println("-------------------------");
        System.out.println("phần trăm theo thứ tự giảm dần");
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach((fruit) -> System.out.println(fruit.getKey() + " -> " + fruit.getValue() + " %"));
    }
    public static void checkEnum(){
        System.out.print("nhập Hoc lực bạn muốn tìm : ");
        String inputAcademicAbility = scanner.nextLine();
        AcademicAbility[] academicAbilities = AcademicAbility.values();
        if (inputAcademicAbility.isEmpty()){
            System.out.println("không được để trống vui lòng nhập lại :");
        } else {
            for (AcademicAbility academicAbility : academicAbilities){
                if (academicAbility.name().equals(inputAcademicAbility)){
                    int size = studentList.size();
                    for (int i = 0; i < size; i++) {
                        if (studentList.get(i).getAcademicAbility()==academicAbility){
                            System.out.println(" ");
                            System.out.format("%5d | ", studentList.get(i).getId());
                            System.out.format("%10s | ", studentList.get(i).getName());
                            System.out.format("%20s | ", studentList.get(i).getBirthday());
                            System.out.format("%10s | ", studentList.get(i).getAddress());
                            System.out.format("%10s | ", studentList.get(i).getHeight());
                            System.out.format("%10s | ", studentList.get(i).getWeight());
                            System.out.format("%20s | ", studentList.get(i).getStudentCode());
                            System.out.format("%20s | ", studentList.get(i).getSchool());
                            System.out.format("%10s | ", studentList.get(i).getSchoolYear());
                            System.out.format("%10s | ", studentList.get(i).getMediumScore());
                            System.out.format("%10s | ", studentList.get(i).getAcademicAbility());
                            System.out.println(" ");
                        }

                    }
                }
            }

        }

    }

    private static Long getMaxId() {
        if (studentList.isEmpty()) {
            return 1l;
        } else {
            Long max = studentList.get(0).getId();
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getId() >= max) {
                    max = studentList.get(i).getId();
                }
            }
            return max + 1l;
        }
    }

    private static String inputName() {
        System.out.print("Input student name: ");
        String name = scanner.nextLine();
        if (name.length() < MAX_NAME && name.length() > 0) {
            return name;
        }
        System.out.println("tên sinh viên chưa hợp lệ");
        return inputName();
    }

    private static String inputBirthday() {
        System.out.print("Input student birthday: ");
        String birthday = scanner.nextLine();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(birthday);
        } catch (ParseException e) {
            System.out.println("Ngày sinh không hợp lệ: ");
            return inputBirthday();
        }
        return birthday;
    }

    private static String inputAddress() {
        System.out.print("Input student address: ");
        String address = scanner.nextLine();
        if (address.length() < MAX_LENGTH) {
            return address;
        }
        System.out.println("tên sinh viên chưa hợp lệ");
        return inputAddress();
    }

    private static double inputHeight() {
        System.out.println("nhập chiều cao của bạn: ");
        while (true) {
            try {
                double checkHeight = Double.parseDouble((scanner.nextLine()));
                if (checkHeight < LOWER_HEIGHT_LIMIT || checkHeight > UPPER_HEIGHT_LIMIT) {
                    throw new NumberFormatException();
                }
                return checkHeight;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input Weight again: ");
            }
        }
    }

    private static double inputWeight() {
        System.out.print("Nhập cân nặng của bạn: ");
        while (true) {
            try {
                double checkWeight = Double.parseDouble((scanner.nextLine()));
                if (checkWeight < LOWER_WEIGHT_LIMIT || checkWeight > UPPER_WEIGHT_LIMIT) {
                    throw new NumberFormatException();
                }
                return checkWeight;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input Weight again: ");
            }
        }
    }

    private static String inputStudentCode() {
        System.out.print("nhập mã sinh viên: ");
        String studentCode = scanner.nextLine();
        if (studentCode.length() != 10) {
            System.out.println("mã sinh viên phải là 10 ký tự");
        } else if (studentList.isEmpty()) {
            return studentCode;
        } else {
            for (int i = 0; i < studentList.size(); i++) {
                if (!studentCode.equalsIgnoreCase(studentList.get(i).getStudentCode())) {
                    return studentCode;
                }
            }
        }
        System.out.println("mã sinh viên phải là 10 ký tự không trùng nhau: ");
        return inputStudentCode();
    }

    private static String inputSchool() {
        System.out.print("Nhập trường học: ");
        String school = scanner.nextLine();
        if (school.length() < MAX_LENGTH && school.length() > 0) {
            return school;
        }
        System.out.println("tên trường không được rỗng và nhỏ hơn 300 kí tự");
        return inputSchool();
    }

    private static int inputSchoolYear() {
        System.out.print("nhập năm học : ");
        String checkSchoolYear = scanner.nextLine();
        if (!checkSchoolYear.matches("\\d\\d+")) {
            System.out.println("SchoolYear phải là int và lớn hơn 1990");
            return inputSchoolYear();
        } else {
            int schoolYear = Integer.parseInt(checkSchoolYear);
            Calendar instance = Calendar.getInstance();
            int year = instance.get(Calendar.YEAR);
            if (schoolYear > 1990 && schoolYear < year) {
                return schoolYear;
            }
            System.out.println("năm học phải lớn hơn 1990 và nhỏ hơn năm hiện tại: ");
            return inputSchoolYear();
        }
    }

    private static Double inputMediumScore() {
        System.out.print("nhập điểm trung bình: ");
        while (true) {
            try {
                double checkMediumScore = Double.parseDouble((scanner.nextLine()));
                if (checkMediumScore < 0.0 || checkMediumScore > 10.0) {
                    throw new NumberFormatException();
                }
                return checkMediumScore;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student medium Score again: ");
            }
        }
    }


    public void SortStudentByMediumScore() {


        Collections.sort(studentList, new SortStudentByMediumScore());
    }

    public static List<Student> getStudentList() {

        return studentList;
    }


    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int inputId() {
        System.out.print("Input student id: ");
        while (true) {
            try {
                int id = Integer.parseInt((scanner.nextLine()));
                return id;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student id again: ");
            }
        }
    }

    public static void show() {
        for (Student student : studentList) {
            System.out.println(" ");
            System.out.format("%5d | ", student.getId());
            System.out.format("%10s | ", student.getName());
            System.out.format("%20s | ", student.getBirthday());
            System.out.format("%10s | ", student.getAddress());
            System.out.format("%10s | ", student.getHeight());
            System.out.format("%10s | ", student.getWeight());
            System.out.format("%20s | ", student.getStudentCode());
            System.out.format("%20s | ", student.getSchool());
            System.out.format("%10s | ", student.getSchoolYear());
            System.out.format("%10s | ", student.getMediumScore());
            System.out.format("%10s | ", student.getAcademicAbility());
            System.out.println(" ");
        }
    }

}
