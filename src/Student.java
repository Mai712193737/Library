public class Student extends User {
static int Limit = 3 ;

    public Student(String name, int userID, String email) {
        super(name, userID, email);
    }

    @Override
    public int getBorrowLimit() {
        return Limit ;
    }
}