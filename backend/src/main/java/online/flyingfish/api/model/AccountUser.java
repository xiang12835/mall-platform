package online.flyingfish.api.model;

//a.account_id, e.emp_number, e.name, e.sex, e.position, e.level, e.role, a.balance, a.create_time, a.change_time
public class AccountUser extends Account {

    private String empNumber;

    private String name;

    private int sex;

    private String position;

    private int level;

    private int role;

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
