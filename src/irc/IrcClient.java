package irc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class IrcClient {
	private PrintStream out;
	private BufferedReader in;
	private Socket sock;
	
	private String nick;
	private String username;
	private String host;
	private int port;
	private boolean blocking;
	private int timeout;
	
	public IrcClient(String nick, String username, String host, int port, boolean blocking, int timeout){
		this.nick = nick;
		this.username = username;
		this.host = host;
		this.port = port;
		this.blocking = blocking;
		this.timeout = timeout;
	}
	
	public void connect() throws IOException{
		sock = new Socket(host, port);
		out = new PrintStream(sock.getOutputStream());
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	}
	
	public void register(String pass) throws IOException{
	    int pings = 0;
	    send("USER " + nick + " local host " + username);
	    for(int i = 0; i < 4; i++)
		System.out.println("receiving " + in.readLine());
	    //System.out.println("stuff");
	    send("NICK " + nick);
	    send("PASS " + pass);
	    String tmp;
	    while(true){
		tmp = in.readLine();
		if(tmp == null){
		    break;
		}
		System.out.println("receiving " + tmp);
		if(tmp.startsWith("PING")){
		    pings++;
		    send("PONG " + host);
		    send("PRIVMSG MEMES :This is my " + pings + "th ping; Can you believe it?");
		}
		if(tmp.endsWith("no")) send("PRIVMSG MEMES :Yeah, me neither!");
	    }
	}
	
	public void send(String message){
	    out.println(message);
	    System.out.println("sending: " + message);
	}
	
}
