import java.awt.Font;
import java.awt.Graphics;

public class Text implements OurComponent {
    private int x;
    private int y;
    private String text;

    public Text(String words, int fVal, int sVal){

        this.text = words;
        this.x = fVal;
        this.y = sVal;

    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g.drawString(text, x, y);
    }


}
