package iFace.iFace;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Message;
import models.User;

/**
 * created by Vinicius
 *
 */
public class App
{
	public static User mainUser;
	public static SessionFactory sessionFactory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
	
    public static void main( String[] args )
    {
    	LocalDate now = LocalDate.now();
    	Scanner integerScan = new Scanner(System.in);
    	SessionFactory sessionFactory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
    	while(mainUser == null){
	        System.out.println( "######  Bem-Vindo ao iFace!  ######\n\n" );
	        System.out.println( "       1 - Login\n       2 - Criar Conta\n       3 - Sair\n" );
	        
	        int entry = integerScan.nextInt();
	        
	        switch (entry) {
	        	case 1:
	        		loginUser();
	        		while(mainUser != null){
	        			userMainScreen();
	        		}
	        		break;
	        	case 2:
	        		createAccount();
	        		break;
	        	case 3:
	        		System.exit(0);
					break;		
	        }
    	}
    }
    
    private static void userMainScreen() {
    	System.out.println("#########  Bem vindo, "+mainUser.getName()+"! O que deseja fazer?   ########\n");
		System.out.println("1 - Editar Perfil");
		System.out.println("2 - Adicionar Amigo");
		System.out.println("3 - Caixa de Mensagens");
		System.out.println("4 - Criar Comunidade");
		System.out.println("5 - Gerenciar Comunidade");
		System.out.println("6 - Pesquisar Usuario");
		System.out.println("7 - Remover Conta");
		System.out.println("8 - Logout");
		
		Scanner integerScan = new Scanner(System.in);
		int entry = integerScan .nextInt();
		
		switch (entry) {
		case 1:
			editUser();
			break;
		case 2:
			addFriend();
			break;
		case 3:
			messageBox();
			break;
		case 4:
			createComunity();
			break;
		case 5:
			manageComunity();
			break;
		case 6:
			userSearch();
			break;
	    case 7:
			deleteAccount();
	    case 8:
	    	mainUser = null;
		}
	
		
	}

	private static void messageBox() {
		int entry;
		Scanner integerScan = new Scanner(System.in);
		System.out.println("O que deseja fazer? 1 - Checar Caixa de Entrada, 2 - Mandar mensagem");
		entry = integerScan.nextInt();
		switch(entry){
		case 1:
			//System.out.print(mainUser.getMsgsList().toString());
			
			//for(Message m : mainUser.getMsgsList){
			//	System.out.println("De: ")+m.getSender.getName()+" '";
			//	System.out.println("' "+m.getContentText()+" '");
			//}
			break;
		
		case 2:
			sendMessage();
			break;
		}
		
	}

	private static void sendMessage() {
		String receiverName, messageText;
		User receiverUser;
		Message msg = null;
		Scanner stringScan = new Scanner(System.in);
		List<User> userList;
		System.out.println("Digite o nome do Destinatario: \n");
		receiverName = stringScan.nextLine();
		receiverUser = getUserByName(receiverName);
		if(receiverUser != null){
			System.out.println("Conteudo da Mensagem: \n");
			messageText = stringScan.nextLine();
			msg = new Message(messageText, mainUser);
			receiverUser.getMsgsList().add(msg);
			Session session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				session.save(receiverUser);
				session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
				System.out.println("Mensagem enviada com sucesso!");
			}
			
		}
		
		
		
	}

	private static void deleteAccount() {
		// TODO Auto-generated method stub
		
	}

	public static User getUserByName(String name) {
		List<User> userList = null;
		int found = 0;
		User userFound = null;
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from User");
			userList = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
		for(User u : userList){
			if(u.getName().equals(name)){
				found = 1;
				userFound = u;
				System.out.println("Usuario Encontrado.\n");
			}
		}
	 
		if(found == 0){
			System.out.println("\nUsuario não encontrado\n");
		}
		return userFound;
	}

	private static void manageComunity() {
		// TODO Auto-generated method stub
		
	}

	private static void createComunity() {
		// TODO Auto-generated method stub
		
	}

	private static void userSearch() {
		String name;
		int found = 0, entry;
		List<User> userList = null;
		Scanner stringScan = new Scanner(System.in);
		Scanner intScan = new Scanner(System.in);
		System.out.println("############## Pesquisa por Nome de Usuario ###############");
		
		System.out.println("\nNome:");
		name = stringScan.nextLine();
		
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from User");
			userList = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
		for(User u : userList){
			if(u.getName().equals(name)){
				found = 1;
				System.out.println("########################\nInfo:\n\nNome: "+u.getName());
				System.out.println("\nEmail: "+u.getEmail());
				System.out.println("\nIdade: "+u.getAge());
				if(mainUser != u)
					System.out.println("\n\nAdicionar como amigo? 1 - Sim, 2 - Nao");
					entry = intScan.nextInt();
					switch(entry){
					case 1:
						addFriend();
						break;
					case 2:
						break;
				}
			}
		}
	 
		if(found == 0)
			System.out.println("\nUsuario não encontrado\n");
	}
		
	

	private static void addFriend() {
		// TODO Auto-generated method stub
		
	}

	private static void editUser() {
		mainUserInfo();
		Scanner integerScan = new Scanner(System.in);
		Scanner stringScan = new Scanner(System.in);
		
		System.out.println("\nO que você deseja editar?\n");
		System.out.println("1 - Senha\n");
		System.out.println("2 - email\n");
		System.out.println("3 - Idade\n");
		System.out.println("4 - Adicionar Informacao\n");
		System.out.println("5 - Nenhum\n");
		int entry = integerScan.nextInt();
		
		switch(entry){
		case 1:
			System.out.println("\nQual sera sua nova senha?");
			String newPassword = stringScan.nextLine();
			mainUser.setPassword(newPassword);
	
			Session session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				session.update(mainUser);
				session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
				System.out.println("\nSenha modificada!\n\n");
			}
			break;
		case 2:
			System.out.println("\nQual sera seu novo email?");
			String newEmail = stringScan.nextLine();
			mainUser.setEmail(newEmail);
	
			Session session2 = sessionFactory.openSession();
			try {
				session2.beginTransaction();
				session2.update(mainUser);
				session2.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session2.getTransaction().rollback();
			} finally {
				session2.close();
				System.out.println("\nEmail modificado!\n\n");
			}
			break;
		case 3:
			System.out.println("\nQual sua idade?");
			int newAge = integerScan.nextInt();
			mainUser.setAge(newAge);
	
			Session session3 = sessionFactory.openSession();
			try {
				session3.beginTransaction();
				session3.update(mainUser);
				session3.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session3.getTransaction().rollback();
			} finally {
				session3.close();
				System.out.println("\nEmail modificado!\n\n");
			}
			break;
		case 4:
			addUserInfo();
			break;
		case 5:
			break;
		}
		
	}

	private static void mainUserInfo() {
		System.out.println("###################  Seu Perfil ##################");
		System.out.println("Nome: "+mainUser.getName()+"\n");
		System.out.println("login: "+mainUser.getLogin()+"\n");
		System.out.println("Email: "+mainUser.getEmail()+"\n");
		System.out.println("Idade: "+mainUser.getAge()+"\n");
		System.out.println("Senha: "+mainUser.getPassword()+"\n");
		//System.out.println(atributes);
		//System.out.println("Quantidade de Amigos: "+mainUser.getFriendsNum()+"\n\n");
		//System.out.println(Amigos);
		//System.out.println("Comunidades que participo: \n");
		//System.out.println("Comunidades que sou dono: \n");
	}

	private static void addUserInfo() {
		// TODO Auto-generated method stub
		
	}

	private static void loginUser() {
		String login, password;
		List<User> userList = null;
		boolean userFound = false;
		Scanner integerScan = new Scanner(System.in);
		Scanner stringScan = new Scanner(System.in);
		
		while(mainUser == null){
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("#################  Login  ##################\n");
			
			System.out.println("login:\n");
			login = stringScan.nextLine();
	
			System.out.println("\nSenha:\n");
			password = stringScan.nextLine();
			
			Session session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				Query query = session.createQuery("from User");
				userList = query.list();
				session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
			
			for(User u : userList){
				if ((u.getLogin().equals(login)) && (u.getPassword().equals(password))){
					userFound = true;
					System.out.println("##############  Login Efetuado!  ##############\n");
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
					mainUser = u;
				}
			}
			System.out.println("\n\n");
			if(!userFound){
				System.out.println("Usuario nao encontrado, tentar novamente?\n1 - Sim\n2 - nao\n");
				int entry = integerScan.nextInt();
				if(entry == 2){
					break;
				}
			}
		}
		
	}

	public static void createAccount() {
		/**
		 * 
		 * Registers an User.
		 * 
		 */
    	SessionFactory sessionFactory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
		String password, email, name, birthString, login;
		LocalDate birth = null, now = null;
		int age;
		Scanner integerScan = new Scanner(System.in);
		Scanner stringScan = new Scanner(System.in);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#################  Cadastro  ##################\n");
		
		System.out.println("\nLogin:\n");
		login = stringScan.nextLine();
		
		System.out.println("\nNome:\n");
		name = stringScan.nextLine();
		
		System.out.println("\nSenha:\n");
		password = stringScan.nextLine();
		
		System.out.println("\nIdade\n");
		age = integerScan.nextInt();
		//System.out.println("\nData de Nascimento: (dd-MM-aaaa)\n ");
		//birthString = stringScan.nextLine();
		
		System.out.println("\nEmail:\n");
		email = stringScan.nextLine();
		
		//stringToDate(birthString, birth);
		User newUser = new User(name, password, email, login, age);
		
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(newUser);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
		System.out.println("############  Cadastro Completo!  #############\n");
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
	}
    
    public static void stringToDate(String str, LocalDate date){
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
		date = LocalDate.parse(str, formatter);
    }
}
