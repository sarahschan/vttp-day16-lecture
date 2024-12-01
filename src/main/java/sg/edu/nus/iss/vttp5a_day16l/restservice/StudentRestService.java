package sg.edu.nus.iss.vttp5a_day16l.restservice;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5a_day16l.constant.Url;
import sg.edu.nus.iss.vttp5a_day16l.model.Student;

@Service
public class StudentRestService {
    
    RestTemplate restTemplate = new RestTemplate();

    public List<Student> getAllStudents(){
        
        ResponseEntity<String> data = restTemplate.getForEntity(Url.STUDENTURL, String.class);

        String payload = data.getBody();

        List<Student> students = new ArrayList<>();

        JsonReader jReader = Json.createReader(new StringReader(payload));
        JsonArray jArray = jReader.readArray();     // Array because we put it as an array of students

        for (int i = 0; i < jArray.size(); i++) {
            JsonObject jObject = jArray.getJsonObject(i);

            Student s = new Student();
                s.setId(jObject.getString("id"));
                s.setFullName(jObject.getString("fullName"));
                s.setEmail(jObject.getString("email"));
                s.setPhone(jObject.getString("phone"));
            students.add(s);
        }

        return students;
    }


    public String createStudent(Student student) {
        // convert to Json string using Json-P functions
        JsonObjectBuilder jObject = Json.createObjectBuilder();
        jObject.add("id", student.getId());
        jObject.add("fullName", student.getFullName());
        jObject.add("email", student.getEmail());
        jObject.add("phone", student.getPhone());
        String requestPayLoad = jObject.build().toString();

        RequestEntity<String> requestEntity = RequestEntity.post(Url.STUDENTURL + "/create").body(requestPayLoad);

        ResponseEntity<String> responseResult = restTemplate.exchange(requestEntity, String.class);

        return responseResult.getBody();
    }
}
