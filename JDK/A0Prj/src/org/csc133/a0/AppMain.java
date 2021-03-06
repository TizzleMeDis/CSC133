package org.csc133.a0;


import static com.codename1.ui.CN.*;

import com.codename1.ui.*;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

class Sound {
    private Media m;
    public Sound(String fileName) {
        try {   m = MediaManager.createMedia(Display.getInstance().
                getResourceAsStream(getClass(),
                "/"+fileName), "audio/wav");

        } catch(Exception e) {System.out.println("No file found");}
    }
    public void play() { m.setVolume(50); m.setTime(0); m.play();}
}

/**
 * This file was generated by
 * <a href="https://www.codenameone.com/">Codename One</a> for the purpose
 * of building native mobile applications using Java.
 */
public class AppMain {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error",
            "There was a networking error in the connection to " +
                err.getConnectionRequest().getUrl(),
         "OK", null);
        });
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new Sound("hello.wav").play();

        Form hi = new Form("Resume", BoxLayout.y());

        int fontSize = Display.getInstance().convertToPixels(2);

        Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM,
            Font.STYLE_PLAIN, Font.SIZE_LARGE);
        Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM,
            Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Font nativeFontMainRegular = Font.createTrueTypeFont(
            "native:MainRegular","native:MainRegular"
            ).derive(fontSize, STYLE_PLAIN);
        Font nativeFontMainBold = Font.createTrueTypeFont(
            "native:MainBold","native:MainBold"
            ).derive(fontSize, STYLE_PLAIN);

        hi.add(createForFont(largePlainSystemFont, "Anthony Tisdale")).
            add(createForFont(mediumBoldSystemFont,"Experience:")).
            add(createForFont(nativeFontMainRegular,
            "I am an aspiring developer. Currently with no experience.")).
            add(createForFont(nativeFontMainRegular,
            "I have made many small projects " +
               "from websites to little games")).
            add(createForFont(nativeFontMainBold, "GitHub: TizzleMeDis")).
            add(createForFont(mediumBoldSystemFont, "Projects:")).
            add(createForFont(nativeFontMainRegular,
            "I have created python games such as pong, snake, connect 4.")).
            add(createForFont(nativeFontMainRegular,
            "Also currently making a sudoku game." +
                "You can see in my github!")).
            add(createForFont(mediumBoldSystemFont,"Hobbies:")).
            add(createForFont(nativeFontMainRegular,
            "I play the guitar and a very active " +
               "person in general. I am getting")).
            add(createForFont(nativeFontMainRegular,
            "into stocks as of recent and love to " +
               "chat about news and trends!"));

        hi.show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

}