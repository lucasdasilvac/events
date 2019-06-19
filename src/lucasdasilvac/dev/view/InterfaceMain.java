package lucasdasilvac.dev.view;

import java.sql.SQLException;
import java.util.Scanner;

import lucasdasilvac.dev.controller.EventController;
import lucasdasilvac.dev.controller.UserController;
import lucasdasilvac.dev.entity.Event;
import lucasdasilvac.dev.entity.User;

public class InterfaceMain {
	Scanner scanner = new Scanner(System.in);
	EventController eventC = new EventController();
	UserController userC = new UserController();
	
	public void start() throws SQLException {
		boolean loop = true;
		while(loop) {
			System.out.println("menu");
			System.out.println("informe a opção desejada");
			System.out.println("1. cadastrar evento");
			System.out.println("2. atualizar evento");
			System.out.println("3. remover evento");
			System.out.println("4. procurar evento por id");
			System.out.println("5. cadastrar usuário");
			System.out.println("6. atualizar usuário");
			System.out.println("7. deletar usuário");
			System.out.println("8. procurar usuário por id");
			System.out.println("9. sair");
			
			String option = scanner.nextLine();
			if(option.matches("^[0-9]{1,}")) {
				int n = Integer.parseInt(option);
				if(n < 1 || n > 9) {
					System.out.println("opção inválida, digite somente um número entre 1 e 9");
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
						registerUser();
						break;
					case 6:
						updateUser();
						break;
					case 7:
						deleteUser();
						break;
					case 8:
						serchUserById();
						break;
					case 9:
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

	private void serchUserById() {
		System.out.println("procurar usuário por id");
		System.out.println("digite o id do usuário que deseja procurar");
		
		String id_user = scanner.nextLine();
		if(id_user.equals("")) {
			System.out.println("código do usuário em branco");
		} else if(!id_user.matches("^[0-9]{1,}")) {
			System.out.println("código inválido");
			return;
		}
		
		Long _id_user = Long.parseLong(id_user);
		User user = userC.searchUserById(_id_user);
		
		if(user == null) {
			System.out.println("usuário não encontrado");
		} else {
			System.out.println("id: " + user.getId() + " nome: " + user.getName() + " email: " +
					            user.getEmail() + " endereço " + user.getAdress());
		}
		
	}

	private void deleteUser() {
		System.out.println("deletar usuário");
		System.out.println("digite o id do usuário que deseja deletar");
		
		String id = scanner.nextLine();
		Long _id = Long.parseLong(id);
		
		if(_id <= 0) {
			System.out.println("digite um id válido");
			return;
		}
		
		boolean data = userC.deleteUser(_id);
		
		if(data) {
			System.out.println("usuário deletado");
		} else {
			System.out.println("ocorreu um erro");
		}
		
	}

	private void updateUser() {
		System.out.println("atualizar usuário");
		System.out.println("digite o id do usuário");
		String id_user = scanner.nextLine();
		
		if(id_user.equals("")) {
			System.out.println("id do usuário em branco");
			return;
		} else if(!id_user.matches("^[0-9]{1,}") && (id_user.length() <= 10)) {
			System.out.println("id do usuário inválido");
			return;
		}
		
		System.out.println("digite o nome do usuário a ser atualizado");
		String name = scanner.nextLine();
		if(name.equals("")) {
			System.out.println("nome em branco");
			return;
		} else if(!name.matches("[a-zA-Z ÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*")) {
			System.out.println("nome só pode conter letras de a-z e A-Z com ou sem acento");
			return;
		}
		
		System.out.println("digite o email do usuário a ser atualizado");
		String email = scanner.nextLine();
		if(email.equals("")) {
			System.out.println("email em branco");
			return;
		} else if(!email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+")) {
			System.out.println("o email digitado é inválido");
			return;
		}
		
		System.out.println("digite o endereço do usuário a ser atualizado");
		String adress = scanner.nextLine();
		if(adress.equals("")) {
			System.out.println("endereço em branco");
			return;
		}
		
		Long _id_user = Long.parseLong(id_user);
		boolean data = userC.updateUser(_id_user, name, email, adress);
		if(data) {
			System.out.println("usuário atualizado com sucesso");
		} else {
			System.out.println("ocorreu um erro");
		}

	}

	private void registerUser() {
		
		
		System.out.println("cadastrar usuário");
		System.out.println("digite seu nome");
		
		String name = scanner.nextLine();
		if(name.equals("")) {
			System.out.println("nome em branco");
			return;
		} else if(!name.matches("[a-zA-Z ÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*")) {
			System.out.println("nome pode conter apenas a-z e A-Z com ou sem acento");
			return;
		}
		
		System.out.println("digite seu email");
		String email = scanner.nextLine();
		
		if(email.equals("")) {
			System.out.println("email em branco");
			return;
		} else if(!email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+")) {
			System.out.println("o email digitado é inválido");
			return;
		}
		
		System.out.println("digite seu endereço");
		String adress = scanner.nextLine();
		
		if(email.equals("")) {
			System.out.println("endereço em branco");
			return;
		}
		
		boolean data = userC.registerUser(name, email, adress);
		if(data) {
			System.out.println("usuário cadastrado com sucesso");
		} else {
			System.out.println("ocorreu um problema");
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
