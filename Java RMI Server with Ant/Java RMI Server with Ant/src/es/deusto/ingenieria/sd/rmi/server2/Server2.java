package es.deusto.ingenieria.sd.rmi.server2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.*;
import java.util.HashMap;

public class Server2 extends UnicastRemoteObject implements IServer2 {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private HashMap <String, String> registeredUsers = null;

	protected Server2() throws RemoteException 
	{
		super();
		registeredUsers = new HashMap<String, String> ();
	}

	@Override
	public String sayHello() 
	{
		cont++;
		System.out.println(" * Client number: " + cont);
		return "Hello World!";
	}
	
	@Override
	public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser2
	{
		if (registeredUsers.containsValue(login)) {

			if (registeredUsers.get(login).contentEquals(password)) {
				return message;
			} else {
				throw new InvalidUser2("Incorrect password for user " + login);
			}

		} else {
			throw new InvalidUser2("User name " + login + "is not present among the registered users");
		}
	}

	@Override
	public void registerUser(String login, String password) throws RemoteException, InvalidUser2 
	{
		if ( registeredUsers.containsValue(login) == false ) {
			registeredUsers.put(login, password);			
		} else {
			throw new InvalidUser2("User name " + login + " is already in the database");
		}		
	}
	

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		//if (System.getSecurityManager() == null) {
		//	System.setSecurityManager(new SecurityManager());
		//}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try 
		{	
			IServer2 objServer = new Server2();
			Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
			//Naming.rebind(name, objServer);
			registry.rebind(name, objServer);
			System.out.println("* Server '" + name + "' active and waiting...");			
		} 
		catch (Exception e) 
		{
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
		

	}
	
}