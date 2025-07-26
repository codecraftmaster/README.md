package jsp.spring.Employee_crud_operation;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String desi;
	private double ctc;
	@Override
	public String toString() {
		return"Employee[id="+id+",name="+name+",desi="+desi+",ctc="+ctc+"]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesi() {
		return desi;
	}
	public void setDesi(String desi) {
		this.desi = desi;
	}
	public double getCtc() {
		return ctc;
	}
	public void setCtc(double ctc) {
		this.ctc = ctc;
	}
	

}
