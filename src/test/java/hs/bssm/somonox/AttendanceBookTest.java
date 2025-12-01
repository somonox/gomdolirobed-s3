package hs.bssm.somonox;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AttendanceBookTest {

    @Test
    void addAndDuplicateThrows() {
        AttendanceBook book = new AttendanceBook();
        Student s1 = new Student("이밤돌", 2, 3, 9);
        Student s2 = new Student("김이로", 1, 1, 11, 3);

        book.addStudent(s1);
        book.addStudent(s2);

        List<Student> students = book.getStudents();

        // 학생이 2명인지 assert
        assertThat(students).hasSize(2);

        // Exception message에 Already exists가 있는지 여부로 assert
        assertThatThrownBy(() -> book.addStudent(s1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재");
    }

    @Test
    void attendIncrementsAttendance() {
        AttendanceBook book = new AttendanceBook();
        Student s1 = new Student("이밤돌", 2, 3, 9, 0);
        Student s2 = new Student("김이로", 1, 1, 11, 3);

        book.addStudent(s1);
        book.addStudent(s2);

        book.attend(s1.getIdentifier());
        book.attend(s2.getIdentifier());

        // 출석 횟수가 각각 1, 4인지 assert
        assertThat(s1.getAttendance()).isEqualTo(1);
        assertThat(s2.getAttendance()).isEqualTo(4);
    }

    @Test
    void removeNonexistentThrows() {
        AttendanceBook book = new AttendanceBook();
        Student s1 = new Student("이밤돌", 2, 3, 9);
        book.addStudent(s1);

        // 존재하지 않는 학생 삭제 시 예외
        assertThatThrownBy(() -> book.removeStudent("999999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없습");
    }

    @Test
    void removeSuccessful() {
        AttendanceBook book = new AttendanceBook();
        Student s1 = new Student("이밤돌", 2, 3, 9);
        Student s2 = new Student("김이로", 1, 1, 11, 3);
        book.addStudent(s1);
        book.addStudent(s2);

        book.removeStudent(s1.getIdentifier());

        List<Student> remaining = book.getStudents();
        // 학생이 1명인지 assert
        assertThat(remaining).hasSize(1);
        // 남은 학생이 s2인지 assert
        assertThat(remaining.get(0).getIdentifier()).isEqualTo(s2.getIdentifier());
    }

    @Test
    void getStudentInfosSortedByAttendanceDesc() {
        AttendanceBook book = new AttendanceBook();
        Student s1 = new Student("A", 1, 1, 1, 2); // attendance 2
        Student s2 = new Student("B", 1, 1, 2, 5); // attendance 5
        Student s3 = new Student("C", 1, 1, 3, 3); // attendance 3

        book.addStudent(s1);
        book.addStudent(s2);
        book.addStudent(s3);

        List<StudentInfo> infos = book.getStudentInfosSortedByAttendanceDesc();
        // 학생 정보가 출석 횟수 내림차순으로 정렬되어 있는지 assert
        assertThat(infos).hasSize(3);
        // 이름이 B, C, A 순서인지 assert
        assertThat(infos).extracting(StudentInfo::getName)
                .containsExactly("B", "C", "A");

        // 출석 횟수가 각각 5, 3, 2인지 assert
        assertThat(infos.get(0).getAttendance()).isEqualTo(5);
        assertThat(infos.get(1).getAttendance()).isEqualTo(3);
        assertThat(infos.get(2).getAttendance()).isEqualTo(2);
    }
}

