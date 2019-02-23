package salary;

public class Emp {
	private String name;
	private double salary;
	
	protected Emp(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public Emp setName(String name) {
		this.name = name;
		return this;
	}
	public double getSalary() {
		return salary;
	}
	public Emp setSalary(double salary) {
		this.salary = salary;
		return this;
	}
	
}
