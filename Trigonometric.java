package javmos2.components.functions;

import javmos2.JavmosGUI;

public abstract class Trigonometric extends Function {

    protected double a;
    protected double k;

    public Trigonometric(JavmosGUI gui, java.lang.String function, java.lang.String name) {
        super(gui);
        String func = function.substring(function.indexOf("="));
        a = Double.parseDouble(function.substring(function.indexOf("=") + 1, function.indexOf(name)));
        k = Double.parseDouble(func.substring(func.indexOf("(") + 1, func.indexOf("x")));
    }
}
