import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

//工厂模式--Props/Creator
public class PropsDemoActor extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub

	}
	public static Props createProps(){
		//实现Creator接口并传入Props.create方法
		return Props.create(new Creator<PropsDemoActor>(){

			@Override
			public PropsDemoActor create() throws Exception {
				//创建actor
				return new PropsDemoActor();
			}
			
		});
	}
}
