package test;

import java.io.IOException;

import irc.IrcClient;

public class Test {

	public static void main(String[] args) throws IOException{
		IrcClient cli = new IrcClient("maybe", "pcc31", "localhost", 6667, true, 120);
		cli.connect();
		cli.register("letmein");
	}

}
