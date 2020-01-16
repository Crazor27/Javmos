package javmos2.components.functions;

import java.util.HashSet;
import javmos2.JavmosGUI;
import javmos2.components.Point;
import javmos2.enums.FunctionType;

public class Logarithmic extends Function {

    public double a;
    public double base;
    public double k;

    public Logarithmic(JavmosGUI gui, java.lang.String function) {
        super(gui);
        String func = function.substring(function.indexOf("="));
        a = Double.parseDouble(function.substring(function.indexOf("=") + 1, function.indexOf("l")));
        base = function.contains("ln") ? Math.E : Double.parseDouble(func.substring(func.indexOf("g") + 1, func.indexOf("(")));
        k = Double.parseDouble(func.substring(func.indexOf("(") + 1, func.indexOf("x")));
    }

    @Override
    public java.lang.String getFirstDerivative() {
        if (base == Math.E) {
            return "f'(x)=" + a + "/x";
        }
        return "f'(x)=" + a + "/(xln" + base + ")";
    }

    @Override
    public java.lang.String getSecondDerivative() {
        if (base == Math.E) {
            return "f'(x)=" + -a + "/x^2";
        }
        return "f'(x)=" + -a + "/(x^2ln" + base + ")";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a / (x * Math.log(base));
            case SECOND_DERIVATIVE:
                return -a / (Math.pow(x, 2) * Math.log(base));
            case THIRD_DERIVATIVE:
                return a / (Math.pow(x, 3) * Math.log(base));
            default:
                return a * Math.log(k * x) / Math.log(base);
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
