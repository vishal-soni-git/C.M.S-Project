import javafx.scene.image.ImageView;

public class Teacher_Bean {
    
    private int id;
    private String name;
    private int salary;
    private String dob;
    private String address;
    private String subject;
	private ImageView photo;
public Teacher_Bean(){}

    public Teacher_Bean(int id, String name, int salary, String dob, String address, String subject,ImageView photo) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dob = dob;
        this.address = address;
        this.subject = subject;
		 this.photo= photo;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    } 
    
	public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
}
