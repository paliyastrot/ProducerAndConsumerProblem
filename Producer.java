package solutionOfConsumerAndProducer;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable
{
	private Queue<Integer> sharedQueue;
	private final int MAXSIZE = 10;
	public Producer(Queue<Integer> sharedQueue)
	{
		this.sharedQueue = sharedQueue;
	}
	public void run()
	{
		while(true)
		{
			synchronized(sharedQueue)
			{
				while(sharedQueue.size()==10)
				{
					System.out.println("Producer is waiting for the consumption of the item");
					try 
					{
						sharedQueue.wait();
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
					
				}
				Random random = new Random();
				int data = random.nextInt(MAXSIZE);
				sharedQueue.add(data);
				System.out.println("Produced : "+ data);
				try
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				sharedQueue.notify();
			}
		}
	}
	
	

}
