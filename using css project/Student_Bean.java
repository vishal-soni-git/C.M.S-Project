import javafx.scene.image.ImageView;

public class Student_Bean {
    
    private String id;
	private long rollNo;
    private String name;
	public String fatherName;
	private String course;
	private String session;
    private long adhar;
    private long phoneNo;
    private String dob;
    private String address;
	private String email;
	private ImageView photo;
    
public Student_Bean(){}

    public Student_Bean(String id,long rollNo, String name,String fatherName,String course,String session,long adhar, long phoneNo, String dob, String address,String email,ImageView photo) 
		{
        this.id = id;
		this.rollNo=rollNo;
        this.name = name;
		this.fatherName=fatherName;
        this.course = course;
        this.session=session;
		this.adhar=adhar;
		this.phoneNo=phoneNo;
        this.dob = dob;
        this.address = address;
        this.email = email;
		this.photo=photo;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


	 public long getRollNo() {
        return rollNo;
    }
    public void getRollNo(long rollNo) {
        this.rollNo=rollNo;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


	  public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fName) {
        this.fatherName=fatherName;
    }


	 public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }


	 public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }


     public ImageView getPhoto() {
        return photo;
    }
	public void setPhoto(ImageView photo) {
        this.photo=photo;
    }


	public long getAdhar() {
        return adhar;
    }
    public void setAdhar(long adhar) {
        this.adhar=adhar;
    }


   public long getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(long phoneNo) {
        this.phoneNo=phoneNo;
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


    public String getEmail() {
        return email;
    }

    public void setEmali(String email) {
        this.email = email;
    } 
}