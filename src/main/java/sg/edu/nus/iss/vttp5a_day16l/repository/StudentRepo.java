package sg.edu.nus.iss.vttp5a_day16l.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5a_day16l.constant.RedisConstant;
import sg.edu.nus.iss.vttp5a_day16l.model.Student;

@Repository
public class StudentRepo {
    
    @Autowired
    @Qualifier(RedisConstant.REDISTEMPLATE)
    RedisTemplate<String, String> template;

    // get student list function
    public List<Student> getStudentList(String key) {
        
        List<String> studentListString = template.opsForList().range(key, 0, -1);

        List<Student> studentList = new ArrayList<>();

        for (String student : studentListString) {
            String[] data = student.split(",");
            Student s = new Student(Integer.parseInt(data[0]), data[1], data[2], data[3]);
            studentList.add(s);
        }

        return studentList;
    }


    // add student function
    public void addStudent(String key, Student s) {
        String student = s.toString();
        template.opsForList().rightPush(key, student);
    }


    // find student by ID function
    public Student findByID(String key, Integer id) {
        List<Student> studentList = getStudentList(key);
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }

        return null;
    }


    // remove student function
    public void removeStudent(String key, Student s) {
        String student = s.toString();
        template.opsForList().remove(key, 1, student);
    }


    // update student function
    public void updateStudent(String key, Student s) {
        Student studentToUpdate = findByID(key, s.getId());
        if (studentToUpdate != null) {
            removeStudent(key, studentToUpdate);
        }
        addStudent(key, s);
    }
}
