package javmos2.components.functions;

import java.util.HashSet;
import javmos2.JavmosGUI;
import javmos2.components.Point;
import javmos2.enums.FunctionType;

public class Tangent extends Trigonometric {

    public Tangent(JavmosGUI gui, java.lang.String function) {
        super(gui, function, "tan");
    }

    @Override
    public java.lang.String getFirstDerivative() {
        return "f'(x)=" + a * k + "sec^2(" + k + "x)";
    }

    @Override
    public java.lang.String getSecondDerivative() {
        return "f''(x)=" + 2 * a * Math.pow(k, 2) + "sec^2(" + k + "x)tan(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a * k / Math.pow(Math.cos(k * x), 2);
            case SECOND_DERIVATIVE:
                return 2 * a * k * Math.tan(k * x) / Math.pow(Math.cos(k * x), 2);
            case THIRD_DERIVATIVE:
                return 2 * a * Math.pow(k, 2) * (2 * Math.pow(Math.tan(k * x), 2) / Math.pow(Math.cos(k * x), 2) + 1 / Math.pow(Math.cos(k * x), 4));
            default:
                return a * Math.tan(k * x);
        }
    }

    @Override
    public java.util.HashSet<Point> getCriticalPoints() {
        return new HashSet<>();
    }

    @Override
    public java.util.HashSet<Point> getInflectionPoints() {
        return new HashSet<>();
    }
}
