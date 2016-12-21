package org.mfusco.fromgoftolambda.talk.command;

import java.util.ArrayList;
import java.util.List;

public class CommandLambda {

	public static void log(String message) {
		System.out.println("Logging: " + message);
	}
	
	public static void save(String message) {
		System.out.println("Saving: " + message);
	}
	
	public static void send(String message) {
		System.out.println("Sending: " + message);
	}
	
    public static class Executor {
        public void execute(List<Runnable> tasks) {
            // method references call
        	tasks.forEach(Runnable::run);
            
            // tasks.forEach(r -> r.run());
        }
    }
	
    public static void main( String[] args ) {
    	// Runnable oppure una qualsiasi interfaccia
    	// con un unico metodo con signature compatibile
    	// anche l'interfaccia Runnable di prima
    	List<Runnable> tasks = new ArrayList<>();
    	tasks.add(() -> log("Hi"));
    	tasks.add(() -> save("Cheers"));
    	tasks.add(() -> send("Bye"));
    	
    	new Executor().execute(tasks);
    }
}
