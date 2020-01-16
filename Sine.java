package javmos2.components.functions;

import javmos2.JavmosGUI;
import javmos2.enums.FunctionType;

public class Sine extends Trigonometric {

    public Sine(JavmosGUI gui, java.lang.String function) {
        super(gui, function, "sin");
    }

    @Override
    public java.lang.String getFirstDerivative() {
        return "f'(x)=" + a * k + "cos(" + k + "x)";
    }

    @Override
    public java.lang.String getSecondDerivative() {
        return "f''(x)=" + -a * Math.pow(k, 2) + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a * k * Math.cos(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k, 2) * Math.sin(k * x);
            case THIRD_DERIVATIVE:
                return -a * Math.pow(k, 3) * Math.cos(k * x);
            default:
                return a * Math.sin(k * x);
        }
    }
}
