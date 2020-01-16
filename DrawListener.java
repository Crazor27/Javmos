package javmos2.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javmos2.JavmosGUI;
import javmos2.components.JavmosPanel;
import javmos2.components.functions.Cosine;
import javmos2.components.functions.Logarithmic;
import javmos2.components.functions.Polynomial;
import javmos2.components.functions.Sine;
import javmos2.components.functions.Tangent;

public class DrawListener extends java.lang.Object implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public DrawListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (gui.getEquationField().contains("sin")) {
            panel.setFunction(new Sine(gui, gui.getEquationField()));
        } else if (gui.getEquationField().contains("cos")) {
            panel.setFunction(new Cosine(gui, gui.getEquationField()));
        } else if (gui.getEquationField().contains("tan")) {
            panel.setFunction(new Tangent(gui, gui.getEquationField()));
        } else if (gui.getEquationField().contains("l")) {
            panel.setFunction(new Logarithmic(gui, gui.getEquationField()));
        } else {
            panel.setFunction(new Polynomial(gui, gui.getEquationField()));
        }
        gui.setFirstDerivativeLabel(panel.getFunction().getFirstDerivative());
        gui.setSecondDerivativeLabel(panel.getFunction().getSecondDerivative());
        panel.repaint();
    }
}
