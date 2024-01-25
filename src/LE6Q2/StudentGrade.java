package LE6Q2;

public class StudentGrade implements Comparable<StudentGrade>
{
    // Fields
    private String firstName;
    private String lastName;
    private int grade;

    // Empty Constructor
    public StudentGrade(){}

    // Custom Constructor
    public StudentGrade (String f, String l, int g)
    {
        this.firstName = f;
        this.lastName = l;
        this.grade = g;
    }

    // Accessors
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getGrade() {return grade;}

    // Mutators
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setGrade(int grade) {this.grade = grade;}

    // Compares students by grade
    @Override
    public int compareTo(StudentGrade s)
    {
        return Integer.compare(this.getGrade(), s.getGrade());
    }

    // String representation of a student
    @Override
    public String toString()
    {
        return String.format("%s %s : %d", firstName, lastName, grade);
    }

}

