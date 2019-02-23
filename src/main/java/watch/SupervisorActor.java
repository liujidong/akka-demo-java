package watch;

import java.io.IOException;
import java.sql.SQLException;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;

public class SupervisorActor extends UntypedActor {
	//定义监督策略
	private SupervisorStrategy strategy = new OneForOneStrategy(3, 
			Duration.create("1 minute"),new Function<Throwable,Directive>() {
				
				@Override
				public Directive apply(Throwable t) throws Exception {
					if(t instanceof IOException){
						System.out.println("==========IOException================");
						return SupervisorStrategy.resume();
					}else if(t instanceof IndexOutOfBoundsException){
						System.out.println("===========IndexOutOfBounds==================");
						return SupervisorStrategy.restart();
					}else if(t instanceof SQLException){
						System.out.println("==========SQLException===================");
						return SupervisorStrategy.stop();
					}else{
						System.out.println("============escalate===============");
						return SupervisorStrategy.escalate();//升级失败
					}
				}

		
	});
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Terminated){
			Terminated ter = (Terminated)msg;
			System.out.println(ter.getActor()+"已经停止");
		}else{
			System.out.println("stateCount="+msg);
		}
	}

	@Override
	public void preStart() throws Exception {
		//创建子Actor
		ActorRef workerActor = getContext().actorOf(Props.create(WorkerActor.class),"workerActor");
		//监控生命周期
		getContext().watch(workerActor);
	}
	public void test(){
		ActorRef workerActor = getContext().actorOf(Props.create(WorkerActor.class),"workerActor");
		workerActor.tell(new IOException(), getSelf());
		workerActor.tell(new IndexOutOfBoundsException("SQL异常"), getSelf());
		workerActor.tell(new IndexOutOfBoundsException(), getSelf());
		workerActor.tell("getValue", getSelf());
	}

}
