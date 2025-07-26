package jsp.spring.Employee_crud_operation;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@PostMapping("/employee")
	public String saveEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return"employee record saved";
	}
	@GetMapping("/employee")
	public List<Employee>getAllEmployee(){
		List<Employee>li=employeeRepository.findAll();
		return li;
	}
	@GetMapping("/employee/{id}")
	public Employee getEmployeeByEmployee(@PathVariable int id) {
		Optional<Employee>opt=employeeRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
		{
			return null;
		}
	}
	@PutMapping("/employee")
	public String updateEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return"Employee Record Updated";
	}
	@DeleteMapping("/employee/{id}")
	public String deleteEmployeeById(@PathVariable int id) {
		Optional<Employee>opt=employeeRepository.findById(id);
		if(opt.isPresent())
		{
			employeeRepository.delete(opt.get());
			return"Employee Record deleted";
		}
	
	else {
		return "Not found";
	}
}
}
