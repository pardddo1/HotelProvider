package es.deusto.ingenieria.sd.rmi.server2;

public class InvalidUser2 extends Exception 
{

	private String message;
	
	
	public InvalidUser2() {
		super();
		this.message = "";		
	}
	
	public InvalidUser2(String exceptionMessage) {
		super();
		this.message = exceptionMessage;
	}
	
	
	public String getErrorMessage() {
		return this.message;
	}

}
