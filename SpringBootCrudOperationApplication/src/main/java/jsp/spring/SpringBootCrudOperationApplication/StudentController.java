package jsp.spring.SpringBootCrudOperationApplication;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jsp")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	@PostMapping("/student")
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
	studentRepository.save(student);
	ResponseStructure<Student> structure=new ResponseStructure<Student>();
	structure.setStatusCode(HttpStatus.CREATED.value());
	structure.setMessage("Success");
	structure.setData(student);
	
	return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
 }
	
	
	@GetMapping("/student")
	public  ResponseStructure<List<Student>> getAllstudent()
	{
		List<Student> li=studentRepository.findAll();
		ResponseStructure<List<Student>> structure=new ResponseStructure<List<Student>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(li);
		return structure;
		
	}
	@GetMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<Student>> getStudentByStudent(@PathVariable int id)
	{
		Optional<Student> opt=studentRepository.findById(id);
		ResponseStructure<Student> structure =new ResponseStructure<Student>();
		if(opt.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
			
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("ID not found");
			structure.setData(null);
		}
		return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
	}
	@PutMapping("/student")
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student)
	{
		studentRepository.save(student);
		ResponseStructure<Student> structure=new ResponseStructure<Student>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);	
  }
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<Student>> deleteStudentById(@PathVariable int id)
	{
		Optional<Student>opt=studentRepository.findById(id);
		ResponseStructure<Student> structure=new ResponseStructure<Student>();
		if(opt.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Deleted");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
			
		}
		else
		{
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Id not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	
}