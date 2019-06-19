package lucasdasilvac.dev.view;

import java.sql.SQLException;
import java.util.Scanner;

import lucasdasilvac.dev.controller.EventController;
import lucasdasilvac.dev.entity.Event;

public class InterfaceMain {
	Scanner scanner = new Scanner(System.in);
	EventController eventC = new EventController();
	
	public void start() throws SQLException {
		boolean loop = true;
		while(loop) {
			System.out.println("menu dos eventos");
			System.out.println("informe a opção desejada");
			System.out.println("1. cadastrar evento");
			System.out.println("2. atualizar evento");
			System.out.println("3. remover evento");
			System.out.println("4. procurar evento por id");
			System.out.println("5. sair");
			
			String option = scanner.nextLine();
			if(option.matches("^[0-5]{1,}")) {
				int n = Integer.parseInt(option);
				if(n < 1 || n > 5) {
					System.out.println("opção inválida, digite somente um número entre 1 e 5");
				} else {
					switch (n) {
					case 1:
						registerEvent();
						break;
					case 2:
						updateEvent();
						break;	
					case 3:
						deleteEvent();
						break;
					case 4:
						searchEventById();
						break;
					case 5:
						System.out.println("sistema encerrado");
						loop = false;
						break;
					}
					
				}
			} else {
				System.out.println("digite somente números inteiros");
			}
		}
	}

	private void searchEventById() {
		System.out.println("procurar evento pelo id");
		System.out.println("digite o id do evento que deseja busca");
		String id_event = scanner.nextLine();
		Long _id_event = Long.parseLong(id_event);
		
		if(id_event.equals("")) {
			System.out.println("id em branco");
			return;
		} else if(!id_event.matches("^[0-9]{1,}")) {
			System.out.println("código inválido");
			return;
		}
		
		Event event = eventC.searchEventById(_id_event);
		if(event == null) {
			System.out.println("evento não encontrado");
			return;
		} else {
			System.out.println("id evento: " + event.getId_event() + " nome " + event.getName() +
							   " endereço " + event.getAdress() + " atração " + event.getAttraction());
			return;
		}
		
	}

	private void deleteEvent() {
		System.out.println("deletar evento");
		System.out.println("digite o id do evento que deseja deletar");
		
		String id_event = scanner.nextLine();
		Long _id_event = Long.parseLong(id_event);
		
		if(_id_event <= 0) {
			System.out.println("digite um id válido");
			return;
		}
		boolean data = eventC.deleteEvent(_id_event);
		if(data) {
			System.out.println("evento deletado");;
		} else {
			System.out.println("ocorreu algum erro");
		}
	}

	private void updateEvent() {
		System.out.println("atualizar evento");
		System.out.println("digite o id do evento");
		String id_event = scanner.nextLine();
		
		if(id_event.equals("")) {
			System.out.println("id do evento em branco");
			return;
		} else if(!id_event.matches("^[0-9]{1,}") && (id_event.length() <= 10)) {
			System.out.println("id do evento inválido");
			return;
		}
		
		System.out.println("digite o nome do evento a ser atualizado");
		String name = scanner.nextLine();
		if(name.equals("")) {
			System.out.println("nome em branco");
			return;
		} else if(!name.matches("[a-zA-Z ÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*")) {
			System.out.println("nome só pode conter letras de a-z e A-Z com ou sem acento");
			return;
		}
		
		System.out.println("digite o local do evento a ser atualizado");
		String adress = scanner.nextLine();
		if(adress.equals("")) {
			System.out.println("endereço em branco");
			return;
		}
		
		System.out.println("digite a atração do evento a ser atualizado");
		String attraction = scanner.nextLine();
		if(attraction.equals("")) {
			System.out.println("atração em branco");
			return;
		}
		
		Long _id_event = Long.parseLong(id_event);
		boolean data = eventC.updateEvent(_id_event, name, adress, attraction);
		if(data) {
			System.out.println("evento atualizado com sucesso");
		} else {
			System.out.println("ocorreu um erro");
		}
		
	}

	private void registerEvent() {
		System.out.println("registrar evento");
		System.out.println("digite o id do evento");
		String id_event = scanner.nextLine();
		
		if(id_event.equals("")) {
			System.out.println("id do evento em branco");
			return;
		} else if(!id_event.matches("^[0-9]{1,}") && (id_event.length() <= 10)) {
			System.out.println("id do evento inválido");
			return;
		}
		
		System.out.println("digite o nome do evento");
		String name = scanner.nextLine();
		if(name.equals("")) {
			System.out.println("nome em branco");
			return;
		} else if(!name.matches("[a-zA-Z ÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*")) {
			System.out.println("nome só pode conter letras de a-z e A-Z com ou sem acento");
			return;
		}
		
		System.out.println("digite o local do evento");
		String adress = scanner.nextLine();
		if(adress.equals("")) {
			System.out.println("endereço em branco");
			return;
		}
		
		System.out.println("digite a atração do evento");
		String attraction = scanner.nextLine();
		if(attraction.equals("")) {
			System.out.println("atração em branco");
			return;
		}
		
		Long _id_event = Long.parseLong(id_event);
		boolean data = eventC.addEvent(_id_event, name, adress, attraction);
		if(data) {
			System.out.println("evento cadastrado com sucesso");
		} else {
			System.out.println("ocorreu um erro");
		}
		
	}
}
