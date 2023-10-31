package gnu.kawa.models;

import gnu.lists.FString;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrappedException;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;

public abstract class Display {
    public static ThreadLocation myDisplay;

    public abstract void addBox(Box box, Object obj);

    public abstract void addButton(Button button, Object obj);

    public abstract void addImage(DrawImage drawImage, Object obj);

    public abstract void addLabel(Label label, Object obj);

    public abstract void addView(Object obj, Object obj2);

    public abstract Window makeWindow();

    public Display() {
    }

    static {
        ThreadLocation threadLocation;
        new ThreadLocation("my-display");
        myDisplay = threadLocation;
    }

    public static Display getInstance() {
        String obj;
        Throwable th;
        StringBuilder sb;
        Object d = myDisplay.get((Object) null);
        if (d instanceof Display) {
            return (Display) d;
        }
        if (d == null) {
            obj = "swing";
        } else {
            obj = d.toString();
        }
        String name = obj;
        Class[] noClasses = new Class[0];
        while (true) {
            int comma = name.indexOf(44);
            String rest = null;
            if (comma >= 0) {
                rest = name.substring(comma + 1);
                name = name.substring(0, comma);
            }
            if (name.equals("swing")) {
                name = "gnu.kawa.swingviews.SwingDisplay";
            } else if (name.equals("swt")) {
                name = "gnu.kawa.swtviews.SwtDisplay";
            } else if (name.equals("echo2")) {
                name = "gnu.kawa.echo2.Echo2Display";
            }
            try {
                return (Display) Class.forName(name).getDeclaredMethod("getInstance", noClasses).invoke((Object) null, new Object[0]);
            } catch (ClassNotFoundException e) {
                ClassNotFoundException classNotFoundException = e;
                if (rest == null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new RuntimeException(sb.append("no display toolkit: ").append(d).toString());
                    throw th2;
                }
                name = rest;
            } catch (Throwable th3) {
                throw WrappedException.wrapIfNeeded(th3);
            }
        }
    }

    public void addText(Text text, Object obj) {
        Throwable th;
        Text text2 = text;
        Object obj2 = obj;
        Throwable th2 = th;
        new Error("makeView called on Text");
        throw th2;
    }

    public void addSpacer(Spacer spacer, Object obj) {
        Throwable th;
        Spacer spacer2 = spacer;
        Object obj2 = obj;
        Throwable th2 = th;
        new Error("makeView called on Spacer");
        throw th2;
    }

    public static Dimension asDimension(Dimension2D dimension2D) {
        Dimension dimension;
        Dimension2D dim = dimension2D;
        if ((dim instanceof Dimension) || dim == null) {
            return (Dimension) dim;
        }
        new Dimension((int) (dim.getWidth() + 0.5d), (int) (dim.getHeight() + 0.5d));
        return dimension;
    }

    public Model coerceToModel(Object obj) {
        Model model;
        Object component = obj;
        if (!(component instanceof FString) && !(component instanceof String)) {
            return (Model) component;
        }
        new Label(component.toString());
        return model;
    }
}
