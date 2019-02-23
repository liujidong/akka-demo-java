package stop;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WorkerActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(this.getContext().system(), this);
	@Override
	public void onReceive(Object msg) throws Exception {
		log.info("收到消息："+msg);
	}
	@Override
	public void postStop() throws Exception {
		log.info("Worker postStop");
	}
	
}
