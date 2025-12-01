package hs.bssm.somonox;

// This class is for transferring student information without exposing the entire Student class.
public class StudentInfo {
    private final String name;
    private final String identifier;
    private final int attendance;

    public StudentInfo(String name, String identifier, int attendance) {
        this.name = name;
        this.identifier = identifier;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getAttendance() {
        return attendance;
    }

    @Override
    public String toString() {
        return String.format("%s, 학번: %s, 출석횟수: %d", name, identifier, attendance);
    }
}

