
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import akka.util.Timeout;


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
		
		//ask方法
		ActorRef ask_ar = system.actorOf(Props.create(AskActorDemo.class),"askDemo");
		Timeout timeout = new Timeout(Duration.create(2, "seconds"));
		//Patterns.ask异步请求，Future阻塞获取
		Future<Object> f = Patterns.ask(ask_ar, "Akka Ask", timeout);
		System.out.println("ask...");
		f.onSuccess(new OnSuccess<Object>() {

			@Override
			public void onSuccess(Object result) throws Throwable {
				System.out.println("收到消息："+result);
			}
			
		}, system.dispatcher());
		System.out.println("continue...");
	}

}
