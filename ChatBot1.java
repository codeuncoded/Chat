import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.io.*;
import java.util.Scanner;
import java.lang.Runnable;
import java.lang.Thread;
import java.lang.Integer;
class ChatBot1{
	public String[] ports;
	public static void main(String[] args){
		ChatBot1 bot1 = new ChatBot1();
		bot1.ports = args;
		bot1.go();
		System.out.println("main:program done.");
		while(true);
	}
	public void go(){
		Thread server = new Thread(new Server());
		Thread client = new Thread(new Client());
		server.start();
		System.out.println("main:server done");
		client.start();
	}
	class Client implements Runnable{
		public void run(){
			System.out.println("client:run started");
			try{
				Socket soc = new Socket();
				InetSocketAddress ip = new InetSocketAddress("192.168.1.127", Integer.parseInt(ports[0]));
				Thread.sleep(10000);
				soc.connect(ip, 0);
				System.out.println("client: request sended");
				InputStream is= soc.getInputStream();
				System.out.println("client: inputstream got");
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				while (true) {
						String message = br.readLine();
						System.out.println(message);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	class Server implements Runnable{
		public void run(){
			//System.out.println("serdonesei");
			try{
				ServerSocket serSoc = new ServerSocket(Integer.parseInt(ports[1]));
				System.out.println("server:server created");
				Socket soc = serSoc.accept();
				System.out.println("server:request recieved and accepted");
				Thread.sleep(2000);
				OutputStream is= soc.getOutputStream();
				BufferedWriter br = new BufferedWriter(new OutputStreamWriter(is)));
				Scanner sc = new Scanner(System.in);
				BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
				while(true){
					//System.out.print("enter message:");
					//Thread.sleep(20000);
					//br.write(userInput.readLine());
					br.write(sc.nextLine());
					//br.write(ports[0]+" "+i);
					br.newLine();
					br.flush();
				}
				//br.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}