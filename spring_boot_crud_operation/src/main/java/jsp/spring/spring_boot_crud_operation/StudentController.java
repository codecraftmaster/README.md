package jsp.spring.spring_boot_crud_operation;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	@PostMapping("/student")
	public String saveStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return "student record saved";
	}
	@GetMapping("/student")
	public List<Student>getAllstudent(){
		List<Student>li=studentRepository.findAll();
		return li;
	}
	
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id) {
		Optional<Student> opt=studentRepository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else
			return null;
	}
	@PutMapping("/student")
	public String updateStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return"Student record update";
	}

}
