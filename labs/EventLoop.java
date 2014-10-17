package labs.two;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;;

public class EventLoop {
	
	private static Scanner sc;
	private IAuctionService imas;
	private Queue<IState> q1;
	private Queue<IState> q2;
	
	private Queue<IState> activeQueue;
	private Queue<IState> expiredQueue;
	
	public EventLoop()
	{
		if(sc == null){
			sc = new Scanner(System.in);
		}
		imas = new InMemoryAuctionService();
		q1 = new LinkedList<IState>();
		q2 = new LinkedList<IState>();
		activeQueue = q1;
		expiredQueue = q2;
	}
	
	public void begin()
	{
		activeQueue.offer(new DefaultState(sc, imas));
		activeQueue.offer(new DefaultState(sc, imas));
		
		while(true){
			while(!activeQueue.isEmpty()) {
				IState currentState = activeQueue.poll();
				if(currentState != null) {
					currentState.show();
					IState next = currentState.next();
					if(next != null) {
							expiredQueue.offer(next);
					}
				}
			}
			expiredQueue.offer(new DefaultState(sc, imas));
			Queue<IState> temp = activeQueue;
			activeQueue = expiredQueue;
			expiredQueue = temp;
		}
	}
	
}
 