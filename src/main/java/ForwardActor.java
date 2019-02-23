import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;


public class ForwardActor extends UntypedActor {
	private ActorRef target=getContext().actorOf(Props.create(TargetActor.class),"targetActor");
	@Override
	public void onReceive(Object msg) throws Exception {
		target.forward(msg, getContext());
	}

}
