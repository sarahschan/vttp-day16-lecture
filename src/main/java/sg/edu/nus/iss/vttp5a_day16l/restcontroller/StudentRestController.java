package sg.edu.nus.iss.vttp5a_day16l.restcontroller;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5a_day16l.constant.RedisConstant;
import sg.edu.nus.iss.vttp5a_day16l.model.Student;
import sg.edu.nus.iss.vttp5a_day16l.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/students", produces = "application/json")
public class StudentRestController {
    
    @Autowired
    StudentService studentService;

    @GetMapping()
    public ResponseEntity<String> allStudents() {

        List<Student> students = studentService.getStudentList(RedisConstant.STUDENTKEY);

        JsonArrayBuilder jsonArray = Json.createArrayBuilder();

        for (Student s : students) {
            JsonObject jsonObject = Json.createObjectBuilder()
                                        .add("id", s.getId())
                                        .add("fullName", s.getFullName())
                                        .add("email", s.getEmail())
                                        .add("phone", s.getPhone())
                                        .build();
            jsonArray.add(jsonObject);
        }

        String builtData = jsonArray.build().toString();
        System.out.println("StudentRestController: " + builtData);

        return ResponseEntity.ok().body(builtData);
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody String entity) {
        
        // using Json-P to convert Json string to student object
        JsonReader jReader = Json.createReader(new StringReader(entity));
        JsonObject jObject = jReader.readObject();

        Student s = new Student();
            s.setId(jObject.getString("id"));
            s.setFullName(jObject.getString("fullName"));
            s.setEmail(jObject.getString("email"));
            s.setPhone(jObject.getString("phone"));
            
        studentService.addStudent(RedisConstant.STUDENTKEY, s);

        return ResponseEntity.ok().body("Student added");
    }
    
}
