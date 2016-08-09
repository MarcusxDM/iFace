package iFace.iFace;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.User;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
    	LocalDate now = LocalDate.now();
    	Scanner integerScan = new Scanner(System.in);
        System.out.println( "######  Welcome to iFace!  ######\n\n" );
        System.out.println( "       1 - Login\n       2 - Create Account\n       3 - Quit\n" );
        
        int entry = integerScan.nextInt();
        
        switch (entry) {
        	case 1:
        		break;
        	case 2:
        		createAccount();
        		break;
        	case 3:
        		System.exit(0);
				break;		
        }
    }
    
    public static void createAccount() {
		/**
		 * 
		 * Registers an User.
		 * 
		 */
		String password, email, name, birthString;
		LocalDate birth = null, now = null;
		Scanner integerScan = new Scanner(System.in);
		Scanner stringScan = new Scanner(System.in);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#################  Cadastro  ##################\n");
		
		System.out.println("\nNome:\n");
		name = stringScan.nextLine();
		
		System.out.println("\nSenha:\n");
		password = stringScan.nextLine();

		System.out.println("\nData de Nascimento: (dd-MM-aaaa)\n ");
		birthString = stringScan.nextLine();

		System.out.println("\nEmail:\n");
		email = stringScan.nextLine();
		
		stringToDate(birthString, birth);
		
		User newUser = new User(name, password, email, birth, now);
		
		SessionFactory sessionFactory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();

		//Abrindo uma sessão junto ao Banco de Dados
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		
		//Salvar no Banco
		session.save(newUser);
		
		//Enviar a sessão para o banco 
		session.getTransaction().commit();
		
		//Fecha a sessão com o banco
		session.close();
		System.out.println("############  Cadastro Completo!  #############\n");
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
	}
    
    public static void stringToDate(String str, LocalDate date){
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
		date = LocalDate.parse(str, formatter);
    }
}
