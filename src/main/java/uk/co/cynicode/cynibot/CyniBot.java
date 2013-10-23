package uk.co.cynicode.cynibot;

import java.util.Iterator;
import java.util.Scanner;
import org.pircbotx.Channel;
import uk.co.cynicode.cynibot.objects.BotHandler;

public class CyniBot {
	
	public static BotHandler PBot;
	public static Scanner scan;
	public static Boolean ending = false;

	public static void ircResponse( String input ) {
		String[] limits = input.split( " " );
		
		try {
			
			if ( limits[1].equalsIgnoreCase( "connect" ) ) {
				
				if ( PBot.isConnected() == true )
					PBot.disconnect();
				
				if ( limits.length > 2 ) {
					if ( limits.length == 3 )
						PBot.connect( limits[2] );
					else if ( limits.length == 4 )
						PBot.connect( limits[2], Integer.valueOf( limits[3] ) );
					System.out.println( "Connected to " + limits[2] );
				} else {
					System.out.println( "Please provide connection details in the form of: ");
					System.out.println( ":? connect [HOST] [PORT]");
				}
			} else if ( limits[1].equalsIgnoreCase( "kill" ) ) {
				PBot.disconnect();
				PBot.shutdown();
				ending = true;
				System.out.println( "Killed the connection... shutting down." );
			} else if ( limits[1].equalsIgnoreCase( "join" ) ) {
				PBot.joinChannel( limits[2], limits[3] );
				System.out.println( "Joined the " + limits[2] + " channel.");
			} else if ( limits[1].equalsIgnoreCase( "nick" ) ) {
				PBot.changeNick( limits[2] );
				System.out.println( "Changed the name of the bot to " + limits[2] );
			} else if ( limits[1].equalsIgnoreCase( "info" ) ) {
				PBot.returnInfo();
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static String stacker( Iterator<Channel> iterms ) {
		
		String output = "";
		String connect = "";
		
		while ( iterms.hasNext() ) {
			output += connect + iterms.next().getName();
			connect = " ";
		}
		
		return output;
	}
	
	public static void main( String[] args ) {
		
		
		
		System.out.println( "Starting up the Irc bot..." );
		
		PBot = new BotHandler();
		scan = new Scanner( System.in );
		
		System.out.println( "Awaiting further instructions..." );
		PBot.setName( "CyniBot" );
		
		while ( ending != true ) {
			
			String input = scan.nextLine();
			
			if ( input.startsWith( ":?" ) )
				ircResponse( input );
			
		}
		
	}
}
