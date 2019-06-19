package lucasdasilvac.dev.entity;

public class User {
	private Long id;
	private String name;
	private String email;
	private String adress;
	
	public User(Long id, String name, String email, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.adress = adress;
	}
	
	public User() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
}
