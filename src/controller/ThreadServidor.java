package controller;

import java.util.concurrent.Semaphore;

public class ThreadServidor extends Thread {

	private int id;
	private Semaphore semaphore;

	public ThreadServidor(int id, Semaphore semaphore) {
		this.id = id;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		int resto = id % 3;
		int iteracoes = 2;
		int calculo = 0;
		int transacao = 0;

		switch (resto) {
		case 1:
			iteracoes = 3;
			break;
		case 2:
			iteracoes = 3;
			break;
		default:
			iteracoes = 2;
			break;
		}

		for (int i = 0; i < (iteracoes * 2); i++) {
			switch (resto) {
			case 1:
				calculo = (int) (Math.random() * 1001) + 500;
				transacao = 1000;
				break;
			case 2:
				calculo = (int) (Math.random() * 1001) + 500;
				transacao = 1500;
				break;
			default:
				calculo = (int) (Math.random() * 801) + 200;
				transacao = 1500;
				break;
			}

			calculo(calculo);
			try {
				semaphore.acquire();
				transacao(transacao);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();
			}
		}

	}

	public void calculo(int intervalo) {
		try {
			System.out.println(
					"THREAD #" + id + " -> Realizando cálculos. Aguarde " + ((double) intervalo / 1000) + " segundos");
			sleep(intervalo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void transacao(int intervalo) {
		System.out.println("THREAD #" + id + " -> Realizando transações em banco de dados. Aguarde "
				+ ((double) intervalo / 1000) + " segundos");
		try {
			sleep(intervalo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
