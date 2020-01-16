package javmos2.components.functions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import javmos2.JavmosComponent;
import javmos2.JavmosGUI;
import javmos2.components.Point;
import javmos2.enums.FunctionType;
import javmos2.enums.RootType;

public abstract class Function extends JavmosComponent {

    protected Function(JavmosGUI gui) {
        super(gui);
    }

    public abstract java.lang.String getFirstDerivative();

    public abstract java.lang.String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);

    public java.util.HashSet<Point> getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }

    public java.util.HashSet<Point> getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }

    public java.util.HashSet<Point> getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        graphics2D.setColor(Color.MAGENTA);
        // Set curve thickness
        graphics2D.setStroke(new BasicStroke(2));
        // Draw points at every 0.1 units on the x-axis
        double x1 = -400;
        double y1 = -400;
        double domainScale = gui.getDomainStep() / gui.getZoom();
        double rangeScale = gui.getRangeStep() / gui.getZoom();
        for (double x = -400; x < 400; x += 0.01) {
            if (x != 0) {
                double x2 = x;
                double y2 = -getValueAt(x * domainScale, FunctionType.ORIGINAL) / rangeScale;
                if ((x * domainScale) >= gui.getMinDomain() && (x * domainScale) <= gui.getMaxDomain()) {
                    if ((-y2 * rangeScale) >= gui.getMinRange() && (-y2 * rangeScale) <= gui.getMaxRange()) {
                        graphics2D.draw(new Line2D.Double(x1 + 400, y1 + 400, x2 + 400, y2 + 400));
                    }
                }
                x1 = x2;
                y1 = y2;
            }
        }
    }
}
