package javmos2.components.functions;

import javmos2.JavmosGUI;
import javmos2.enums.FunctionType;

public final class Polynomial extends Function {

    public final double[] coefficients;
    public final int[] degrees;

    public Polynomial(JavmosGUI gui, java.lang.String function) {
        super(gui);
        String[] equation = function.replace("-", "+-").replace("x^", "x").replace("=+", "=").split("[=+]");
        this.coefficients = new double[equation.length - 1];
        this.degrees = new int[equation.length - 1];
        for (int x = 1; x < equation.length; x++) {
            String[] temp = equation[x].split("x", -1);
            //in case if 1 or -1 coefficient (x or -x)
            coefficients[x - 1] = (temp[0].equals("") || temp[0].equals("-")) ? 1 : Double.parseDouble(temp[0]);
            coefficients[x - 1] = temp[0].equals("-") ? -1 : coefficients[x - 1];
            //if no x then temp.length = 1; so degree is 0; if 2x, then temp[1] = "" so degree is 1
            if (temp.length == 1) {
                degrees[x - 1] = 0;
            } else {
                degrees[x - 1] = temp[1].equals("") ? 1 : Integer.parseInt(temp[1]);
            }
        }
    }

    public Polynomial(JavmosGUI gui, double[] coefficients, int[] degrees) {
        super(gui);
        this.coefficients = coefficients;
        this.degrees = degrees;
    }

    @Override
    public java.lang.String getFirstDerivative() {
        Polynomial d = getDerivative();
        String dx = "f'(x)=";
        for (int x = 0; x < d.coefficients.length; x++) {
            if (d.coefficients[x] == 0) {
            } else if (d.degrees[x] == 1) {
                dx += d.coefficients[x] + "x+";
            } else if (d.degrees[x] == 0) {
                dx += d.coefficients[x] + "+";
            } else {
                dx += d.coefficients[x] + "x^" + d.degrees[x] + "+";
            }
        }
        dx = dx.replace("+-", "-");
        return dx.substring(0, dx.length() - 1);
    }

    @Override
    public String getSecondDerivative() {
        Polynomial d = getDerivative();
        return d.getFirstDerivative().replace("f'(x)", "f''(x)");
    }

    public Polynomial getDerivative() {
        double[] coefficient = new double[degrees.length];
        int[] degree = new int[degrees.length];
        for (int x = 0; x < coefficients.length; x++) {
            coefficient[x] = coefficients[x] * degrees[x];
            degree[x] = degrees[x] == 0 ? 0 : degrees[x] - 1;
        }
        return new Polynomial(gui, coefficient, degree);
    }

    @Override
    public double getValueAt(double x, FunctionType type) {
        double sum = 0;
        Polynomial p = new Polynomial(gui, coefficients, degrees);
        switch (type) {
            case FIRST_DERIVATIVE:
                p = getDerivative();
                break;
            case SECOND_DERIVATIVE:
                p = getDerivative().getDerivative();
                break;
            case THIRD_DERIVATIVE:
                p = getDerivative().getDerivative().getDerivative();
                break;
            default:
                break;
        }
        for (int a = 0; a < p.coefficients.length; a++) {
            sum += p.coefficients[a] * Math.pow(x, p.degrees[a]);
        }
        return sum;
    }
}
