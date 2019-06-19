package lucasdasilvac.dev.entity;

public class Participation {
	private Long code;
	private Event event;
	private User user;
	
	public Participation() {
		super();
	}
	public Participation(Long code, Event event, User user) {
		super();
		this.code = code;
		this.event = event;
		this.user = user;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
