package com.example.pebbler10.pebble;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import android.content.Context;

public final class Configuration
{
 
  public static UUID myUUID = UUID.fromString("344ca0b6-9aca-4cf6-a2d1-4a72699f8751");
  
  public static boolean installed;
  
  public static final String Server1 = "s1";
  public static final String Server2 = "s2";
  public static final String Server3 = "s3";
  public static final String Checkbox = "c1";
  
  public static  String s1 = "";
  public static  String s2 = "";
  public static  String s3 = "";
  
  public static final int DATA_KEY = 0;
  public static final int STATE_INIT = 0x00;
  
  public static final int SHUTDOWN_KEY = 1;
  public static final int RESTART_KEY = 2;
  public static final int LOCK_KEY = 3;
  public static final int LOG_OFF_KEY = 4;
  public static final int SLEEP_KEY = 5;
  public static final int ABORT_KEY = 6;
  
  public static final int SERVER1_KEY = 7;
  public static final int SERVER2_KEY = 8;
  public static final int SERVER3_KEY = 9;
  
  public static final int TURN_ON = 0X11;
  public static final int TURN_OFF = 0X12;
  
  public static final int VLC_PLAY = 0X21;
  public static final int VLC_NEXT = 0X22;
  public static final int VLC_PREVIOUS = 0X23;
  public static final int VLC_M10 = 0X24;
  public static final int VLC_L10 = 0X25;
  
  public static final int VOLUME_UP = 0X41;
  public static final int VOLUME_DOWN = 0X42;
  public static final int VOLUME_MUTE = 0X43;
  
  public static final int SELECT_KEY = 0x0;

  public static final int XBMC_PLAY = 0x26;
  public static final int XBMC_NEXT = 0x27;
  public static final int XBMC_PREVIOUS = 0x28;
  public static final int XBMC_M10 = 0x29;
  public static final int XBMC_L10 = 0x30;
    
  public static final int PLEX_PLAY = 0x31;
  public static final int PLEX_PREVIOUS = 0x32;
  public static final int PLEX_NEXT = 0x33;
  public static final int PLEX_FORWARD = 0x34;
  public static final int PLEX_REWIND = 0x35;
		    
  public static final int SPOTIFY_PLAY = 0x36;
  public static final int SPOTIFY_NEXT = 0x37;
  public static final int SPOTIFY_PREVIOUS = 0x38;
		    
  public static final int ITUNES_PLAY = 0x44;
  public static final int ITUNES_NEXT = 0x45;
  public static final int ITUNES_PREVIOUS = 0x46;
  public static final int ITUNES_M10 = 0x47;
  public static final int ITUNES_L10 = 0x48;

  public static final int WINDOW_OPEN = 0x51;
  public static final int WINDOW_RUN = 0x52;
  public static final int WINDOW_MINIMIZE = 0x53;

  public static final int PPOINT_NEXT = 0x61;
  public static final int PPOINT_PREVIOUS = 0x62;
  public static final int PPOINT_BLACK = 0x63;
  public static final int PPOINT_WHITE = 0x64;
  public static final int PPOINT_START = 0x65;
  public static final int PPOINT_END =  0x66;

  public static final int CHROME_HOME = 0x71;
  public static final int CHROME_REFRESH = 0x72;
  public static final int CHROME_BACK_P = 0x73;
  public static final int CHROME_NEXT_P = 0x74;
  public static final int CHROME_BACK_T = 0x75;
  public static final int CHROME_NEXT_T = 0x76;
  public static final int CHROME_UP = 0x77;
  public static final int CHROME_DOWN = 0x78;

  public static final int YOUTUBE_PLAY = 0x81;
  public static final int YOUTUBE_NEXT = 0x82;
  public static final int YOUTUBE_PREVIOUS = 0x83;
  public static final int YOUTUBE_M10 = 0x84;
  public static final int YOUTUBE_L10 = 0x85;

  public static final int NETFLIX_PLAY = 0x91;
  public static final int NETFLIX_NEXT = 0x92;
  public static final int NETFLIX_PREVIOUS = 0x93;

  public static final int PANDORA_PLAY = 0X94;
  public static final int PANDORA_NEXT = 0x95;
  public static final int PANDORA_UP = 0x96;
  public static final int PANDORA_DOWN = 0x97;
  
  public static final int FOOBAR_PLAY = 0x98;
  public static final int FOOBAR_NEXT = 0x99;
  public static final int FOOBAR_PREVIOUS = 0x100;
  public static final int FOOBAR_STOP = 0x101;
  
  public static final int MONKEY_PLAY = 0x102;
  public static final int MONKEY_NEXT = 0x103;
  public static final int MONKEY_FORWARD = 0x104;
  public static final int MONKEY_STOP = 0x105;
  
  public static final int WMPLAYER_PLAY = 0x106;
  public static final int WMPLAYER_NEXT = 0x107;
  public static final int WMPLAYER_FORWARD = 0x108;
  public static final int WMPLAYER_STOP = 0x109;
  
  public static final int GMEDIA_PLAY = 0x110;
  public static final int GMEDIA_NEXT = 0x111;
  public static final int GMEDIA_FORWARD = 0x112;
  public static final int GMEDIA_STOP = 0x113;
  
  public static final int WINAP_PLAY = 0x114;
  public static final int WINAP_NEXT = 0x115;
  public static final int WINAP_FORWARD = 0x116;
  public static final int WINAP_STOP = 0x117;
  
  public static final int GSLIDE_NEXT = 0x118;
  public static final int GSLIDE_PREVIOUS = 0x119;
  public static final int GSLIDE_START = 0x120;
  public static final int GSLIDE_END = 0x121;
  
  
  public static final int DATA_INSTALLED = 0x10; 
		  
  public static final int BUFFER_LENGTH = 3200;
  
  public static String readFileFromAssets(String fileName, Context c) {
	    try {
	        InputStream is = c.getAssets().open(fileName);
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        String text = new String(buffer);

	        return text;

	    } catch (IOException e) {
	    	//String text = "";
	    	//return text;
	        throw new RuntimeException(e);
	    }

	}
}
