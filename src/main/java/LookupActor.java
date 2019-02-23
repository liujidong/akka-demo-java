import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorIdentity;
import akka.actor.ActorNotFound;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Identify;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.util.Timeout;


public class LookupActor extends UntypedActor {
	private ActorRef target = null;
	{
		target = getContext().actorOf(Props.create(TargetActor.class),"targetActor");
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof String){
			if("find".equals(msg)){
				//ActorSelection as = getContext().actorSelection("targetActor");
				ActorSystem system = ActorSystem.create("sys");
				ActorSelection as = system.actorSelection("/user/lookupActor/targetActor");
				Timeout timeout = new Timeout(Duration.create(20, "seconds"));
				Future<ActorRef> fu = as.resolveOne(timeout);
				fu.onSuccess(new OnSuccess<ActorRef>() {
					@Override
					public void onSuccess(ActorRef ref) throws Throwable {
						System.out.println("查找到Actor："+ref);
					}
					
				}, system.dispatcher());
				fu.onFailure(new OnFailure() {
					@Override
					public void onFailure(Throwable ex) throws Throwable {
						if(ex instanceof ActorNotFound){
							System.out.println("没找到Actor:"+ex.getMessage());
						}
					}
				}, system.dispatcher());
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
