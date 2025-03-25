package Builder;

import java.util.ArrayList;
import java.util.List;

public class EngineeringStudentBuilder extends StudentBuilder {

    public StudentBuilder setSubjects() {
        List<String> subs = new ArrayList<>();
        subs.add("Maths");
        subs.add("Physics");
        this.subjects = subs;
        return this;
    }

}