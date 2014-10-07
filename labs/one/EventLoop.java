import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class EventLoop {
	
	private static Scanner sc;
	private InMemoryAuctionService imas;
	private ArrayBlockingQueue<IState> q1;
	private ArrayBlockingQueue<IState> q2;
	
	private ArrayBlockingQueue<IState> activeQueue;
	private ArrayBlockingQueue<IState> expiredQueue;
	
	public EventLoop()
	{
		if(sc == null){
			sc = new Scanner(System.in);
		}
		imas = new InMemoryAuctionService();
		q1 = new ArrayBlockingQueue<IState>(64);
		q2 = new ArrayBlockingQueue<IState>(64);
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
						if(next instanceof SearchResultsState) {
							activeQueue.offer(next);
						}
						else {
							expiredQueue.offer(next);
						}
					}
				}
			}
			expiredQueue.offer(new DefaultState(sc, imas));
			ArrayBlockingQueue<IState> temp = activeQueue;
			activeQueue = expiredQueue;
			expiredQueue = temp;
		}
	}
	
}
 