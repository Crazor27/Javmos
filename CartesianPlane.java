package javmos2.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.text.DecimalFormat;
import javmos2.JavmosComponent;
import javmos2.JavmosGUI;

public class CartesianPlane extends JavmosComponent {

    public CartesianPlane(JavmosGUI gui) {
        super(gui);
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        Font numbers = new Font("Times New Roman", BOLD, 14);
        for (int x = 1; x < 400 / gui.getZoom() + 1; x++) {
            int x2 = (int) (x * gui.getZoom());
            graphics2D.setStroke(new BasicStroke(1));
            graphics2D.setColor(Color.GRAY);
            graphics2D.setFont(numbers);
            graphics2D.drawLine(x2 + 400, 0, x2 + 400, 800);
            graphics2D.drawLine(0, x2 + 400, 800, x2 + 400);
            graphics2D.drawLine(-x2 + 400, 0, -x2 + 400, 800);
            graphics2D.drawLine(0, -x2 + 400, 800, -x2 + 400);
            graphics2D.setColor(Color.BLACK);
            DecimalFormat df = new DecimalFormat("#.##########");
            graphics2D.drawString(df.format(gui.getDomainStep() * x), x2 + 405, 395);
            graphics2D.drawString(df.format(-gui.getRangeStep() * x), 405, x2 + 400);
            graphics2D.drawString(df.format(-gui.getDomainStep() * x), -x2 + 405, 395);
            graphics2D.drawString(df.format(gui.getRangeStep() * x), 405, -x2 + 400);
        }

        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(400, 0, 400, 800);
        graphics2D.drawLine(0, 400, 800, 400);
    }
}
