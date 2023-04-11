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
    public Validate(){
        studentDao = new StudentDao();
        studentList = studentDao.read();
    }

    public static void add() {
        Student student = new Student();
        student.setId(getMaxId());
//        String name = inputName();
//        student.setName(name);
//        String birthday = inputBirthday();
//        student.setBirthday(birthday);
//        String address = inputAddress();
//        student.setAddress(address);
//        Double height = inputHeight();
//        student.setHeight(height);
//        Double weight = inputWeight();
//        student.setWeight(weight);
//        String studentCode = inputStudentCode();
//        student.setStudentCode(studentCode);
//        String school = inputSchool();
//        student.setSchool(school);
//        int schoolYear = inputSchoolYear();
//        student.setSchoolYear(schoolYear);
        Double mediumScore = inputMediumScore();
        student.setMediumScore(mediumScore);
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
//                studentList.get(i).setName(inputName());
//                studentList.get(i).setBirthday(inputBirthday());
//                studentList.get(i).setAddress(inputAddress());
//                studentList.get(i).setHeight(inputHeight());
//                studentList.get(i).setWeight(inputWeight());
//                studentList.get(i).setStudentCode(inputStudentCode());
//                studentList.get(i).setSchool(inputSchool());
//                studentList.get(i).setSchoolYear(inputSchoolYear());
                studentList.get(i).setMediumScore(inputMediumScore());
                break;
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            studentDao.write(studentList);
        }
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
    public void SortStudentByMediumScore(){

        Collections.sort(studentList, new SortStudentByMediumScore());
    }
    public static void show(){
        for (Student student : studentList){
            System.out.format("%5d | ",student.getId());
            System.out.format("%20s | ",student.getName());
            System.out.format("%5d | ",student.getBirthday());
            System.out.format("%20s | ",student.getAddress());
            System.out.format("%5d | ",student.getHeight());
            System.out.format("%5d | ",student.getWeight());
            System.out.format("%20s | ",student.getStudentCode());
            System.out.format("%20s | ",student.getSchool());
            System.out.format("%20s | ",student.getSchoolYear());
            System.out.format("%5d | ",student.getMediumScore());
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
            return max+1l;
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
//        Calendar instance = Calendar.getInstance();
//        int year = instance.get(Calendar.YEAR);
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
        String checkHeight = scanner.nextLine();
        if (!checkHeight.matches("\\d*[.]\\d+")) {
            System.out.println("Height phải là double");
            return inputHeight();
        } else {
            double height = Double.parseDouble(checkHeight);
            if (LOWER_HEIGHT_LIMIT < height && height < UPPER_HEIGHT_LIMIT) {
                return height;
            }
            System.out.println("chiều cao không hợp lệ: ");
            return inputHeight();
        }
    }

    private static double inputWeight() {
        System.out.print("Nhập cân nặng của bạn: ");
        String checkWeight = scanner.nextLine();
        if (!checkWeight.matches("\\d*[.]\\d+")) {
            System.out.println("weight phải là double");
            return inputWeight();
        } else {
            Double weight = Double.parseDouble(checkWeight);
            if (LOWER_WEIGHT_LIMIT < weight && weight < UPPER_WEIGHT_LIMIT) {
                return weight;
            }
            System.out.println("cân nặng phải lớn hơn 5 và nhỏ hơn 100 ");
            return inputWeight();
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
//        String checkMediumScore = scanner.nextLine();SortStudentByMediumScore
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
//        if (!checkMediumScore.matches("\\d*[.]\\d+")) {
//            System.out.println("MediumScore phải là double phải lớn hơn hoạc bằng 0 nhỏ hơn hoặc bằng 10:");
//            return inputMediumScore();
//        } else {
//            Double mediumScore = Double.parseDouble(checkMediumScore);
//            if (mediumScore >= 0.0d && mediumScore <= 10.0d) {
//                return mediumScore;
//            }
//            System.out.println("điểm trung bình phải lớn hơn hoạc bằng 0 nhỏ hơn hoặc bằng 10: ");
//            return inputMediumScore();
//        }
    }
    public List<Student> getStudentList() {
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
}
