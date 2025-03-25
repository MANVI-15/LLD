package Builder;

public class Main {
    public static void main(String[] args) {
        Director director=new Director(new EngineeringStudentBuilder());
        Director director1=new Director(new MBAStudentBuilder());

        Student mba=director1.createStudent();
        Student eng=director.createStudent();

        System.out.println(mba);
        System.out.println(eng);


    }
}
