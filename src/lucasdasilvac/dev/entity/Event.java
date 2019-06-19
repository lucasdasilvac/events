package lucasdasilvac.dev.entity;

public class Event {
	private Long id_event;
	private String name;
	private String adress;
	private String attraction;
	
	public Event(Long id_event, String name, String adress, String attraction) {
		super();
		this.id_event = id_event;
		this.name = name;
		this.adress = adress;
		this.attraction = attraction;
	}
	
	public Event() {
		
	}
	
	public Long getId_event() {
		return id_event;
	}

	public void setId_event(Long id_event) {
		this.id_event = id_event;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getAttraction() {
		return attraction;
	}
	public void setAttraction(String attraction) {
		this.attraction = attraction;
	}
	
	
}
