package in.codebuffdev.bootaop.service;

import in.codebuffdev.bootaop.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student createStudent(Student student){
        System.out.println("Student saved" + student);
        return student;
    }
}
