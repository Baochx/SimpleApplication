package chongxue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

class ForkAndJoinTask extends RecursiveTask<Integer> {
	
	private static final long serialVersionUID = 1L;
	public static final int MIN = 2;
	private int start;
	private int end;
	ForkAndJoinTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public Integer compute() {
		int sum = 0;
		if ((end - start) < MIN ) {
			for (int i = start; i <= end; i++) 
				sum += i;
		} else {
			int middle = (start + end) / 2;
			ForkAndJoinTask leftTask = new ForkAndJoinTask(start, middle);
			ForkAndJoinTask rightTask = new ForkAndJoinTask(middle + 1, end);
			
			leftTask.fork();
			rightTask.fork();
			
			int left = leftTask.join();
			int right = rightTask.join();
			sum = left + right;
		}
		return sum;
	}
	
}

public class ForkAndJoin {
	public static void main(String[] args) {
		ForkJoinPool p = new ForkJoinPool();
		ForkAndJoinTask fjt = new ForkAndJoinTask(1,4);
		Future<Integer> f = p.submit(fjt);
		try {
			System.out.println(f.get());
		} catch (InterruptedException e) {
			
		} catch (ExecutionException e) {
			
		}
	}
}