package sg.edu.nus.iss.vttp5a_day16l.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp5a_day16l.model.Student;
import sg.edu.nus.iss.vttp5a_day16l.repository.StudentRepo;

@Service
public class StudentService {
    
    @Autowired
    StudentRepo studentRepo;

    public List<Student> getStudentList(String key) {
        return studentRepo.getStudentList(key);
    }

    public void addStudent(String key, Student s) {
        studentRepo.addStudent(key, s);
    }

    public Student findByID(String key, String id) {
        return studentRepo.findByID(key, id);
    }

    public void removeStudent(String key, Student s) {
        studentRepo.removeStudent(key, s);
    }

    public void updateStudent(String key, Student s) {
        studentRepo.updateStudent(key, s);
    }
}
