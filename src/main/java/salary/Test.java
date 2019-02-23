package salary;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Test {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("sys");
		ActorRef ref = system.actorOf(Props.create(SimpleActorDemo.class),"simpleActorDemo");
		ref.tell("1", ActorRef.noSender());
		ref.tell(new Emp("张三",10000), ActorRef.noSender());
		ref.tell(new Emp("李四",20000), ActorRef.noSender());
		ref.tell("become3", ActorRef.noSender());
		ref.tell("end", ActorRef.noSender());
		ref.tell("2", ActorRef.noSender());
		ref.tell(new Emp("wang 5",10000), ActorRef.noSender());
		ref.tell(new Emp("zhao 6",20000), ActorRef.noSender());
	}

}
