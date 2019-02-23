package salary;

import akka.actor.UntypedActor;
import akka.japi.Procedure;

public class SimpleActorDemo extends UntypedActor {
	Procedure<Object> LEVEL1 = new Procedure<Object>(){

		@Override
		public void apply(Object message) throws Exception {
			if(message instanceof String){
				if("end".equals(message)){
					getContext().unbecome();
				}else if("become3".equals(message)){
					getContext().become(LEVEL3,false);
				}
			}else{
				Emp emp = (Emp)message;
				double result = emp.getSalary()*1.8;
				System.out.println("员工"+emp.getName()+"的奖金为："+result);
			}
		}
		
	};
	Procedure<Object> LEVEL2 = new Procedure<Object>(){

		@Override
		public void apply(Object message) throws Exception {
			if(message instanceof String){
				if(message.equals("end")){
					getContext().unbecome();
				}
			}else{
				Emp emp = (Emp)message;
				double result = emp.getSalary()*1.5;
				System.out.println("员工"+emp.getName()+"的奖金为："+result);
			}
		}
		
	};
	Procedure<Object> LEVEL3 = new Procedure<Object>(){

		@Override
		public void apply(Object message) throws Exception {
			if(message instanceof String){
				if(message.equals("end")){
					getContext().unbecome();
				}
			}else{
				Emp emp = (Emp)message;
				double result = emp.getSalary()*1.2;
				System.out.println("员工"+emp.getName()+"的奖金为："+result);
			}
		}
		
	};	
	@Override
	public void onReceive(Object msg) throws Exception {
		String level = (String)msg;
		if(level.equals("1")){
			getContext().become(LEVEL1);
		}else if(level.equals("2")){
			getContext().become(LEVEL2);
		}
	}

}
