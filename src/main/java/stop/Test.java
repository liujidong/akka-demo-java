package stop;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Kill;
import akka.actor.PoisonPill;
import akka.actor.Props;

public class Test {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("sys");
		//ActorRef ar = system.actorOf(Props.create(WorkerActor.class),"workerActor");
		ActorRef ar = system.actorOf(Props.create(WatchActor.class),"watchActor");
		//system.stop(ar);
		//ar.tell(PoisonPill.getInstance(), ActorRef.noSender());
		//ar.tell(Kill.getInstance(), ActorRef.noSender());
		ar.tell("stopChild", ActorRef.noSender());
	}
}
