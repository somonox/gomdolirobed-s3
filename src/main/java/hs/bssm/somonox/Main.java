package hs.bssm.somonox;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO-1 출석부 생성
        // 학생목록이 비어 있는 출석부를 하나 생성합니다.
        AttendanceBook attendanceBook = new AttendanceBook();

        // TODO-2 학생 생성
        // 출석횟수가 0회인 2학년 3반 9번 이밤돌을 생성합니다.
        // 출석횟수가 3회인 1학년 1반 11번 김이로를 생성합니다.
        // 출석횟수가 2회인 1학년 2반 9번 최시나를 생성합니다.
        Student student1 = new Student("이밤돌", 2, 3, 9, 0);
        Student student2 = new Student("김이로", 1, 1, 11, 3);
        Student student3 = new Student("최시나", 1, 2, 9, 2);

        // 출석횟수가 -1회인 2학년 1반 1번 박마루를 생성합니다.
        // 출석횟수는 음수일 수 없다는 메시지가 출력되고 생성되지 않습니다.
        try {
            Student invalidStudent = new Student("박마루", 2, 1, 1, -1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // TODO-3 출석부에 학생 삽입
        // 생성한 출석부에 이밤돌, 김이로를 삽입합니다.
        attendanceBook.addStudent(student1);
        attendanceBook.addStudent(student2);

        // TODO-4 출석부에 동일한 학생 삽입
        // 출석부에 이밤돌을 다시 삽입합니다.
        try {
            attendanceBook.addStudent(student1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // 출석부에 이밤돌은 존재하므로 삽입할 수 없다는 메시지가 출력됩니다.

        // TODO-5 출석횟수 증가
        // 출석부에 존재하는 이밤돌, 김이로의 출석횟수가 각각 1씩 증가합니다.
        attendanceBook.getStudent(student1.getIdentifier()).incrementAttendance();
        attendanceBook.getStudent(student2.getIdentifier()).incrementAttendance();
        // or
        attendanceBook.attendAll();

        // TODO-6 출석부 조회
        // 출석부에 등록된 모든 학생의 정보를 조회합니다.
        // 각 학생들의 이름, 학반번호, 출석횟수를 출력해야합니다.
        // 학생 목록은 출석횟수를 기준으로 내림차순 정렬하여 출력해야 합니다.
        List<Student> students = attendanceBook.getStudents();
        students.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getAttendance(), s1.getAttendance()))
                .forEach(s -> System.out.printf("이름: %s, 학반: %d-%d, 출석횟수: %d%n",
                        s.getName(), s.getGrade(), s.getClazz(), s.getAttendance()));

        // TODO-7 특정 학생 정보 조회
        // 이밤돌의 출석 횟수를 조회합니다.
        // 김이로의 출석 횟수를 조회합니다.
        Student ibamdol = attendanceBook.getStudent(student1.getIdentifier());
        Student kimiro = attendanceBook.getStudent(student2.getIdentifier());
        System.out.printf("이밤돌의 출석 횟수: %d%n", ibamdol.getAttendance());
        System.out.printf("김이로의 출석 횟수: %d%n", kimiro.getAttendance());

        // TODO-8 출석부에 존재하지 않는 학생 삭제
        // 출석부에서 최시나를 삭제합니다.
        // 출석부에 최시나는 없으므로 존재하지 않는 학생은 삭제가 불가능하다는 메시지가 출력됩니다.
        try {
            attendanceBook.removeStudent(student3.getIdentifier());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // TODO-9 출석부에서 학생 삭제
        // 출석부에서 이밤돌을 삭제합니다.
        // 학생이름 + '님이 출석부에서 삭제되었습니다' 라는 메시지가 출력되어야합니다.
        attendanceBook.removeStudent(student1.getIdentifier());

        // TODO-10 삭제 후 출석부 조회
        // 학생 삭제가 정상적으로 되었는지 다시 한 번 출석부의 학생 명단을 조회해 출력합니다.
        students = attendanceBook.getStudents();
        students.stream()
                .forEach(s -> System.out.printf("이름: %s, 학반: %d-%d, 출석횟수: %d%n",
                        s.getName(), s.getGrade(), s.getClazz(), s.getAttendance()));
    }
}
