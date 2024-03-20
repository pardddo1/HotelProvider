package es.deusto.ingenieria.sd.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.*;
import java.util.HashMap;
//import ApiClient;
//import Server; //netstat ano | findstr :port

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private HashMap <String, String> registeredUsers = null;
	private ApiClient ApiClient;

	protected Server() throws RemoteException 
	{
		super();
		registeredUsers = new HashMap<String, String> ();
		//this.conexionAPI = new ConexionAPI();
	}

	@Override
	public String sayHello() 
	{
		cont++;
		System.out.println(" * Client number: " + cont);
		return "Hello World!";
	}
	
	@Override
	public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser
	{
		if (registeredUsers.containsValue(login)) {

			if (registeredUsers.get(login).contentEquals(password)) {
				return message;
			} else {
				throw new InvalidUser("Incorrect password for user " + login);
			}

		} else {
			throw new InvalidUser("User name " + login + "is not present among the registered users");
		}
	}

	@Override
	public void registerUser(String login, String password) throws RemoteException, InvalidUser 
	{
		if ( registeredUsers.containsValue(login) == false ) {
			registeredUsers.put(login, password);			
		} else {
			throw new InvalidUser("User name " + login + " is already in the database");
		}		
	}

	@Override
	public String obtenerApartamentos(){
		try{
			String url = "https://ds2324.arambarri.eus";
			String token = "0518ee96193abf0dca7b3a46591653eb2b162f3fb2dd6fa681b65b97e3e00243187a1b6839aac73946715fb62719b12a1eb14afc36018935b935c2dbf293448fc98a5cde5a219fc208a3db97489b2c2c479825f212d87658ff3b369e4951b0b3f101ac8d52330262e60846ae80b45b6799c69371e4f47a548053137ada4ec6e5";
			String apiResponse = ApiClient.getApiResponse(url, token);
			return apiResponse;
		}catch(Exception e){
			e.printStackTrace();
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
			IServer objServer = new Server();
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