import akka.actor.ActorRef;
import akka.actor.UntypedActor;

//ask方法
//为了能将数据返回给发送方，我们需要先引用到Sender
public class AskActorDemo extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("发送者是:"+getSender());
		getSender().tell("hello "+msg, getSelf());
	}
}
