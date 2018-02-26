package org.learnTech.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings({ "serial", "rawtypes" })
public class CountTask extends RecursiveTask{

	private static final int THRESHOLD = 10000;//阀值
	
	private int start;
	private int end;
	
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		//如果任务足够小就计算任务
		boolean canCompute = (end - start) <= THRESHOLD;
		if(canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			 //如果任务大于阀值，就分裂成两个子任务计算
			int middle = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle+1, end);
			//执行子任务
			invokeAll(leftTask, rightTask);
			//等待子任务执行完，并得到其结果
			int leftResult = (int) leftTask.join();
			int rightResult = (int) rightTask.join();
			 //合并子任务
			sum = leftResult  + rightResult;
		}
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Long startTime = System.currentTimeMillis();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		//生成一个计算任务，负责计算1+2+3+4
		CountTask task =new CountTask(1, 100000000);
		Integer result = (Integer) forkJoinPool.invoke(task);
		System.out.println(result);
		Long endTime = System.currentTimeMillis();
		System.out.println("耗时：" + (endTime - startTime));
	}
}
