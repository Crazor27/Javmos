package javmos2;

public abstract class JavmosComponent extends java.lang.Object {

    protected final JavmosGUI gui;

    public JavmosComponent(JavmosGUI gui) {
        this.gui = gui;
    }

    public abstract void draw(java.awt.Graphics2D graphics2D);
}
