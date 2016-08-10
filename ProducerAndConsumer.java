package chongxue.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Message {
	private String str;
	
	Message(String str) {
		this.str = str;
	}
	
	public String getMessage() {
		return str;
	}
}


class Producer extends Thread {
	BlockingQueue<Message> queue;
	
	Producer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}
	
	public void run() {
		for (int i = 0; i < 100; i++) {
				try {
					queue.put(new Message("" + i));
					System.out.println("Produce " + i);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	
}

class Consumer extends Thread {
	BlockingQueue<Message> queue;
	
	Consumer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}
	
	public void run() {
		while(true) {
			try {
				Message m = queue.take();
				if (m.getMessage()!= "exit") {
					System.out.println("consume " + m.getMessage());
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class ProducerAndConsumer {
	
    public static void main(String[] args) {
    	BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
    	new Producer(queue).start();
    	new Consumer(queue).start();
    }
    
 }