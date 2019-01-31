import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

//创建Actor实例
public class ActorDemo extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(this.getContext().system(),this);
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof String){
			log.info(msg.toString());
		}else{
			unhandled(msg);
		}
	}

}
