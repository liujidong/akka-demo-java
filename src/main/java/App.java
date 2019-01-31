import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class App {

	public static void main(String[] args) {
		//一般创建:通过ActorSystem/ActorContext
		//参数为ActorSystem的名字，可以不传
		ActorSystem system = ActorSystem.create("sys");
		//参数分别是构造器和actor的名字，名字可以不传
		ActorRef actorRef = system.actorOf(Props.create(ActorDemo.class),"actorDemo");
		//创建子actor
		//ActorRef childActor = getContext().actorOf(Props.create(ChildActor.class),"childActor");
		
		//工厂模式创建
		ActorRef ref = system.actorOf(PropsDemoActor.createProps(), "propsActor");
		
		//tell方法
		actorRef.tell("hello Akka", ActorRef.noSender());
	}

}
