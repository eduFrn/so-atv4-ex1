package view;

import java.util.concurrent.Semaphore;

import controller.ThreadServidor;

public class Main {
	public static void main(String[] args) {
		
		Semaphore semaphore = new Semaphore(1);
		
		for (int i = 0; i < 21; i++) {
			Thread thread = new ThreadServidor(i+1, semaphore);
			thread.start();
		}
		
	}
}
