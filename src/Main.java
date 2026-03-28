import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

import static java.lang.System.out;
//1.6 Передать SocketServer в многопоточный вариант,
//чтобы он мог надежно обработать 100 запросов одновременно
//используя пулы потоков

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// 127.0.0.1:1111
		// localhost:1111
		
		ServerSocket ss = new ServerSocket(1111);//открывем локальный сокет № порта
		int req = 0; // AtomicInteger
		// create pool
		
		while (true)
		{
			Socket cs = ss.accept();//переводит готовности к приему входящих соеденений
			//этот метод блокирует текущий поток пока не придет запрос от клиента
			// create task
			out.printf("Accept connection from %s\n",
					cs.getInetAddress().toString());
			
			BufferedReader reader = new BufferedReader( 
				new InputStreamReader(cs.getInputStream(),//чтение запроса клиента 
					Charset.forName("UTF-8")));
			
			String s = reader.readLine();//читаем строчку которую прислал клиент
			
			out.printf("%s . %d\n",s,++req);//выводим на экран ++req счетчик колличество запросов
			//Thread.sleep(100);//имитация работы сервера
			
			OutputStreamWriter writer = new OutputStreamWriter(
					cs.getOutputStream(),
					Charset.forName("UTF-8"));
			
			writer.write(s.toUpperCase()+"\n");//отправляем в ответ ту же самую строчку только в верхнем регистре 
			writer.flush();
			
			
			
			
		}

	}

}
