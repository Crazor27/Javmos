package javmos2.enums;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos2.JavmosGUI;
import javmos2.components.Point;
import javmos2.components.functions.Function;

public enum RootType {
    X_INTERCEPT(Color.GRAY, "X_INTERCEPT", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.RED, "CRITICAL_POINT", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.ORANGE, "INFLECTION_POINT", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    public final int ATTEMPTS = 15;
    public final java.lang.String rootName;
    public final FunctionType functionOne;
    public final FunctionType functionTwo;
    public final java.awt.Color rootColor;

    RootType(Color color, String root, FunctionType f1, FunctionType f2) {
        rootColor = color;
        rootName = root;
        functionOne = f1;
        functionTwo = f2;
    }

    public String getRootName() {
        return rootName;
    }

    public Color getRootColor() {
        return rootColor;
    }

    public java.util.HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        HashSet<Point> roots = new HashSet<>();
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        for (int i = 0; i < 10000; i++) {
            //guess iterates in increments from min to max for every 0.01% of the domain specified
            double guess = minDomain + i * (maxDomain - minDomain) / 10000;
            if ((newtonsMethod(function, guess, ATTEMPTS)) != null) {
                double root = (newtonsMethod(function, guess, ATTEMPTS));
                double value = function.getValueAt(root, FunctionType.ORIGINAL);
                root = Double.parseDouble(df.format(root)) == -0.0 ? 0 : Double.parseDouble(df.format(root));
                value = Double.parseDouble(df.format(value)) == -0.0 ? 0 : Double.parseDouble(df.format(value));
                roots.add(new Point(gui, this, root, value));
            }
        }
        return roots;
    }

    public java.lang.Double newtonsMethod(Function function, double guess, int attempts) {
        if (attempts > 0) {
            double newGuess = guess - function.getValueAt(guess, functionOne) / function.getValueAt(guess, functionTwo);
            if (Math.abs(newGuess - guess) <= 0.00000001) {
                return newGuess;
            } else {
                attempts--;
                return newtonsMethod(function, newGuess, attempts);
            }
        } else {
            return null;
        }
    }
}
