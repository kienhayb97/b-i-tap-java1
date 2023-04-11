import java.io.Serializable;

public class Student extends People implements Serializable {
    private String studentCode;
    private String school;
    private int schoolYear;
    private Double mediumScore;

    private AcademicAbility academicAbility;

    public void setMediumScore(Double mediumScore) {
        this.mediumScore = mediumScore;
    }

    public AcademicAbility getAcademicAbility() {
        Double compare = this.getMediumScore();
        if (compare<3){
            return AcademicAbility.LEAST;
        } else if (compare<5) {
            return AcademicAbility.WEAK;
        } else if (compare<6.5) {
            return AcademicAbility.MEDIUM;
        } else if (compare<7.5) {
            return AcademicAbility.RATHER;
        } else if (compare<9) {
            return AcademicAbility.GOOD;
        }else {
            return AcademicAbility.EXCELLENT;
        }
    }

    public void setAcademicAbility(AcademicAbility academicAbility) {
        this.academicAbility = academicAbility;

    }

    public Student(String studentCode, String school, int schoolYear, Double mediumScore) {
        this.studentCode = studentCode;
        this.school = school;
        this.schoolYear = schoolYear;
        this.mediumScore = mediumScore;
    }

    public Student(Long id, String name, String birthday, String address, Double height, Double weight, String studentCode, String school, int schoolYear, Double mediumScore) {
        super(id, name, birthday, address, height, weight);
        this.studentCode = studentCode;
        this.school = school;
        this.schoolYear = schoolYear;
        this.mediumScore = mediumScore;
    }

    public Student() {

    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public double getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(double mediumScore) {
        this.mediumScore = mediumScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", birthday='"+super.getBirthday() + '\''+
                ", address='" + super.getAddress() + '\'' +
                ", height=" + super.getHeight() +
                ", weight=" + super.getWeight() +
                ", studentCode='" + studentCode + '\'' +
                ", school='" + school + '\'' +
                ", schoolYear=" + schoolYear +
                ", mediumScore=" + mediumScore +
                ""+getAcademicAbility()+
                '}';
    }

    public void informationStudent(){
        System.out.printf("%-5d %-20s %-15s %-15s \n",getId(),getName(),getBirthday(),getAddress(),getHeight(),getWeight(),studentCode,school,schoolYear,mediumScore );
    }

}
