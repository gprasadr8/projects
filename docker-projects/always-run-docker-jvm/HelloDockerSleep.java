
public class HelloDockerSleep {
	
	public static void main(String[] args){
		System.out.println("Hello Docker!");
		try {
			while(true){
				Thread.sleep(5*1000);
				System.out.println("HelloDockerSleep running......");
			}
		}catch (InterruptedException e){
			System.out.println("InterruptedException occured.");
		}

	}
}