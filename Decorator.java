import java.awt.Graphics;

public class Decorator implements OurComponent {

    private OurComponent cmp;
    private int width;
    public int height;


    @Override
    public void draw(Graphics g) {
        
    }


    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public OurComponent getCmp() {
        return cmp;
    }


    public void setCmp(OurComponent cmp) {
        this.cmp = cmp;
    }

}