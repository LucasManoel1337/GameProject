package game.gameproject.support;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontLoaderSupport {
	
	public static Font loadCustomFont(String fontPath, float size) throws FontFormatException, IOException {
        File fontFile = new File(fontPath);
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        font = font.deriveFont(size); // Define the font size
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font); // Register the font
        return font;
    }
	
}
