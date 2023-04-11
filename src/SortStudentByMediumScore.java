import java.util.Comparator;

public class SortStudentByMediumScore implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        if (student1.getMediumScore() > student2.getMediumScore()){
            return 1;
        }
        return -1;
    }
}
