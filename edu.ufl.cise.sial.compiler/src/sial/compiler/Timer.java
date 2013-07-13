package sial.compiler;

public class Timer {
	long startTime;
	long endTime;
	
	public Timer reset(){
		startTime = System.currentTimeMillis();
		endTime = 0;
		return this;
	}
	
	public Timer end(){
		endTime = System.currentTimeMillis();
		return this;
	}
	
	public long getElapsed(){
		if (endTime == 0) endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public  void printElapsed(String msg){
		System.out.println(getElapsed() + " msecs " + msg);
	}

	public void printElapsed(boolean verbose, String msg) {
		if (verbose){
			printElapsed(msg);
		}
		
	}
	
	public Timer(){
		startTime = System.currentTimeMillis();
		endTime = 0;
	}
}
