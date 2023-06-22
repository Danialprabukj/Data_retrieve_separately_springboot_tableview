package com.kgisl.demo2.controller;

import org.springframework.web.bind.annotation.*;

import com.kgisl.demo2.entity.Student;
import com.kgisl.demo2.repository.StudentRepository;

// import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/add")
    public Student addDetails(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/details")
    public List<Student> getStudentDetails(
            @RequestParam(required = false) Integer day,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year) {

        return studentRepository.findByDayMonthYear(day, month, year);
    }

@GetMapping("/genderCount")
public Map<String, Map<String, Integer>> getGenderCountByCollege() {
    List<Object[]> genderCounts = studentRepository.getGenderCountByCollege();

    Map<String, Map<String, Integer>> result = genderCounts.stream()
            .collect(Collectors.groupingBy(
                    genderCount -> ((String) genderCount[0]).trim(),
                    Collectors.groupingBy(
                            genderCount -> (String) genderCount[1],
                            Collectors.summingInt(genderCount -> ((Number) genderCount[2]).intValue())
                    )
            ));

    return result;
}
}
