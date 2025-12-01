package hs.bssm.somonox;

public class Student {
    private String name;
    private int grade;
    // class is a reserved keyword in Java, so we use clazz instead of class
    private int clazz;
    private int number;
    private int attendance;

    public Student(String name, int grade, int clazz, int number) {
        this(name, grade, clazz, number, 0);
    }

    public Student(String name, int grade, int clazz, int number, int attendance) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");

        if (name.length() < 1 || name.length() > 20)
            throw new IllegalArgumentException("Name must be between 1 and 20 characters.");

        if (attendance < 0)
            throw new IllegalArgumentException("Attendance cannot be negative.");

        this.name = name;
        this.grade = grade;
        this.clazz = clazz;
        this.number = number;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getClazz() {
        return clazz;
    }

    public int getNumber() {
        return number;
    }

    public int getAttendance() {
        return attendance;
    }

    public void incrementAttendance() {
        this.attendance++;
    }

    public String getIdentifier() {
        return String.format("%d%02d%02d", this.grade, this.clazz, this.number);
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', grade=%d, clazz=%d, number=%d, attendance=%d}", name, grade, clazz, number, attendance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return this.getIdentifier().equals(student.getIdentifier()) && this.name.equals(student.name);
    }
}

