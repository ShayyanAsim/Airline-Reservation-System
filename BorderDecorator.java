import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class BorderDecorator extends Decorator {

    public BorderDecorator(OurComponent t, int w, int h){

        setCmp(t);
        setWidth(w);
        height = h;
        

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        getCmp().draw(g);
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setColor(Color.red);
        g2d.drawRect(0, 0, getWidth(), height);
        
    }
}