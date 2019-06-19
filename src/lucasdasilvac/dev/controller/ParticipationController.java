package lucasdasilvac.dev.controller;

import lucasdasilvac.dev.connection.ConnectionPostgres;
import lucasdasilvac.dev.dao.EventDAO;
import lucasdasilvac.dev.dao.ParticipationDAO;
import lucasdasilvac.dev.dao.UserDAO;
import lucasdasilvac.dev.entity.Event;
import lucasdasilvac.dev.entity.Participation;
import lucasdasilvac.dev.entity.User;

public class ParticipationController {
	ConnectionPostgres connPostgres = new ConnectionPostgres();
	ParticipationDAO participationDao = new ParticipationDAO(connPostgres);
	UserDAO userDao = new UserDAO(connPostgres);
	EventDAO eventDao = new EventDAO(connPostgres);
	
	public String registerParticipation(Long user_id, Long id_event) {
		String participationCode = "";
		User user = userDao.read(user_id);
		Event event = eventDao.read(id_event);
		
		if(event == null || user == null) {
			participationCode = "ocorreu um erro";
			return participationCode;
		}
		
		Participation participation = new Participation();
		participation.setEvent(event);
		participation.setUser(user);
		
		boolean result = participationDao.create(participation);
		
		if(result) {
			Long participationCodeLong = participationDao.getLastParticipationCode();
			if(participationCodeLong == 0) {
				participationCode = "não há eventos cadastrados";
				return participationCode;
			}
			participationCode = participationDao.getLastParticipationCode().toString();
		} else {
			participationCode = "ocorreu um erro";
			return participationCode;
		}
		return participationCode;
	}
}
