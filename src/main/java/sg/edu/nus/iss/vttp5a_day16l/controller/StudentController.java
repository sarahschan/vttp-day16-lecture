package sg.edu.nus.iss.vttp5a_day16l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_day16l.constant.RedisConstant;
import sg.edu.nus.iss.vttp5a_day16l.model.Student;
import sg.edu.nus.iss.vttp5a_day16l.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    StudentService studentService;
    
    @GetMapping()
    public String studentsPage(Model model) {
        List<Student> studentList = studentService.getStudentList(RedisConstant.STUDENTKEY);
        model.addAttribute("studentList", studentList);

        return "students";
    }


    // handle requests at /students/create
    @GetMapping("/create")
    public String studentCreate(Model model) {
        Student s = new Student();
        model.addAttribute("student", s);
        return "studentCreate";
    }

    @PostMapping("/create")
    public String handleStudentCreate(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        
        System.out.println("Recieved details: " + student);

        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "studentCreate";
        }

        studentService.addStudent(RedisConstant.STUDENTKEY, student);
        System.out.println("Created new student entry: " + student);

        return "redirect:/students";
    }
    
}
