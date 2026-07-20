public class Teacher extends User {

    static int limitforTeacher  = 5 ;
    public Teacher(String name, int userID, String email) {
        super(name, userID, email);
    }

    @Override
    public int getBorrowLimit() {
        return limitforTeacher ;
    }
}