package stop;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
//停止层级Actor时，会先停止子级，再停止父级
public class WatchActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(this.getContext().system(), this);
	ActorRef child = null;
	
	@Override
	public void preStart() throws Exception {
		//创建子级Actor
		child=getContext().actorOf(Props.create(WorkerActor.class),"workerActor");
	}

	@Override
	public void postStop() throws Exception {
		log.info("WatchActor postStop");
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		
	}

}
