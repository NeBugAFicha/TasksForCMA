package Test2;



public class Student {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String birthDay;
    private String group;
    private Integer uniqNumber;

    public Student(String firstName, String lastName, String patronymic, String birthDay, String group, Integer uniqNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDay = birthDay;
        this.group = group;
        this.uniqNumber = uniqNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getUniqNumber() {
        return uniqNumber;
    }

    public void setUniqNumber(Integer uniqNumber) {
        this.uniqNumber = uniqNumber;
    }
}
