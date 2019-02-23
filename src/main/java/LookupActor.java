import akka.actor.ActorIdentity;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Identify;
import akka.actor.Props;
import akka.actor.UntypedActor;


public class LookupActor extends UntypedActor {
	private ActorRef target = null;
	{
		target = getContext().actorOf(Props.create(TargetActor.class),"targetActor");
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof String){
			if("find".equals(msg)){
				ActorSelection as = getContext().actorSelection("targetActor");
				as.tell(new Identify("A001"), getSelf());//发送后会自动接收
			}
		}else if(msg instanceof ActorIdentity){
			ActorIdentity ai = (ActorIdentity)msg;
			if(ai.correlationId().equals("A001")){
				ActorRef ref = ai.getRef();
				if(ref != null){
					System.out.println("ActorIdentity:"+ai.correlationId()+""+ref);
					ref.tell("hello target", getSelf());
				}
			}
		}else{
			unhandled(msg);
		}
	}
	

}
