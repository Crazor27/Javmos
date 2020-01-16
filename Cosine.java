package javmos2.components.functions;

import javmos2.JavmosGUI;
import javmos2.enums.FunctionType;

public final class Cosine extends Trigonometric {

    public Cosine(JavmosGUI gui, java.lang.String function) {
        super(gui, function, "cos");
    }

    @Override
    public java.lang.String getFirstDerivative() {
        return "f'(x)=" + (-a * k) + "sin(" + k + "x)";
    }

    @Override
    public java.lang.String getSecondDerivative() {
        return "f''(x)=" + (-a * Math.pow(k, 2)) + "cos(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return -a * k * Math.sin(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k, 2) * Math.cos(k * x);
            case THIRD_DERIVATIVE:
                return a * Math.pow(k, 3) * Math.sin(k * x);
            default:
                return a * Math.cos(k * x);
        }
    }
}
