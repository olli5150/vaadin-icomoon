package de.headengine;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.headengine.vaadin.icomoon.IcoMoonIcon;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final CssLayout layout = new CssLayout();
        for (IcoMoonIcon icon : IcoMoonIcon.values()) {
            final Button button = new Button(icon.name(), icon);
            button.addStyleName("icomoon-button");
            button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
            HorizontalLayout horizontalLayout = new HorizontalLayout(button);
            horizontalLayout.setWidth(300,Unit.PIXELS);
            horizontalLayout.addStyleName("container");
            layout.addComponent(horizontalLayout);
        }
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
