package goods;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

//使用并发来处理多线程的客户端运行,并发容器用于存储多线程的共享数据
public class ClientHander implements Runnable{
	private Socket clientSocket;
	private BufferedReader br;
	private PrintWriter pw;
	//使用基于列表的堵塞队列（先进先出）
	private BlockingQueue<String>message;
	
	//有参构造函数
	public ClientHander(Socket socket) {
		try {
			this.clientSocket =socket;
			this.br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    //不覆盖内容
			this.pw=new PrintWriter(clientSocket.getOutputStream(),true);
			this.message=new LinkedBlockingQueue<>();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			//输出
			String Line;
			while((Line=br.readLine())!=null) {
				message.put(Line);
			}
			//阻塞收到中断抛出InterruptedException
		}catch(IOException |InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				clientSocket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	//得到信息,遇到中断抛出异常
	public String GetMessage()throws InterruptedException {
		//等待一个单位的时间长度，否则返回空
		return message.poll(1,TimeUnit.SECONDS);
	}
	//发送信息
	public void SendMessage(String s) {
		pw.println(s);
	}
}
