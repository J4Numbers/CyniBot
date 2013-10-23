package uk.co.cynicode.cynibot.objects;

import org.pircbotx.PircBotX;
import uk.co.cynicode.cynibot.CyniBot;
import uk.co.cynicode.cynibot.listeners.IrcListener;

/**
 *
 * @author Cynical
 */
public class BotHandler extends PircBotX {
	
	private final PircBotX bot;
	
	public BotHandler() {
		
		this.bot = new PircBotX();
		this.bot.setLogin( "CyniCode" );
		this.bot.getListenerManager().addListener(new IrcListener());
		
	}
	
	public boolean returnInfo() {
		try {
			System.out.println( "You are connected to " + this.bot.getServer() );
			System.out.println( "You are connected to channels " +CyniBot.stacker( this.bot.getChannels().iterator() ) );
			System.out.println( "Your username is " + this.bot.getNick() +", and your login is " + this.bot.getLogin() );
			System.out.println( "You are currently running " + this.bot.getVersion() + " of this bot.");
			return true;
		} catch (Exception e ) {
			e.printStackTrace();
			return false;
		}
	}
	
}
