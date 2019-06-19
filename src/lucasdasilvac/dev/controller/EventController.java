package lucasdasilvac.dev.controller;

import lucasdasilvac.dev.connection.ConnectionPostgres;
import lucasdasilvac.dev.dao.EventDAO;
import lucasdasilvac.dev.entity.Event;

public class EventController {
	ConnectionPostgres connPostgres = new ConnectionPostgres();
	EventDAO eventDao = new EventDAO(connPostgres);
	
	public Event searchEventById(Long id_event) {
		return eventDao.read(id_event);
	}
	
	public boolean addEvent(Long id_event, String name, String adress, String attraction) {
		if(id_event <= 0) {
			return false;
		} if (name.equals("")) {
			return false;
		} if (adress.equals("")) {
			return false;
		} if (attraction.equals("")) {
			return false;
		}
		
		Event event = new Event();
		event.setId_event(id_event);
		event.setName(name);
		event.setAdress(adress);
		event.setAttraction(attraction);
		return eventDao.create(event);
	}
	
	public boolean updateEvent(Long id_event, String name, String adress, String attraction) {
		if(id_event <= 0) {
			return false;
		} if (name.equals("")) {
			return false;
		} if (adress.equals("")) {
			return false;
		} if (attraction.equals("")) {
			return false;
		}
		
		Event event = new Event();
		event.setId_event(id_event);
		event.setName(name);
		event.setAdress(adress);
		event.setAttraction(attraction);
		return eventDao.update(event);
	}
	
	public boolean deleteEvent(Long id_event) {
		if(id_event <= 0) {
			return false;
		}
		return eventDao.delete(id_event);
	}
}
