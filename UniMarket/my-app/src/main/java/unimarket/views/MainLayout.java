package unimarket.views;


import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Whitespace;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;
import org.vaadin.lineawesome.LineAwesomeIcon;
import unimarket.views.checkoutform.CheckoutFormView;
import unimarket.views.gridwithfilters.GridwithFiltersView;
import unimarket.views.masterdetail.MasterDetailView;
import unimarket.views.myview.Login;
import unimarket.views.myview.MyViewView;
import unimarket.views.personform.PersonFormView;

import java.util.ArrayList;
import java.util.List;

/**
 * The main view is a top-level placeholder for other views.
 */
@Layout
@AnonymousAllowed
public class MainLayout extends AppLayout {


    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;


        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

    }

    private Component header;
    public MainLayout() {
        header = createHeaderContent();
        addToNavbar(header);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        Boolean updateLayout = (Boolean) VaadinSession.getCurrent().getAttribute("updateLayout");
        if (Boolean.TRUE.equals(updateLayout)) {
            VaadinSession.getCurrent().setAttribute("updateLayout", false); // Reset flag
            remove(header); // Rimuove solo la navbar esistente
            header = createHeaderContent(); // Ricrea la navbar aggiornata
            addToNavbar(header); // Aggiungi la nuova navbar aggiornata
        }
    }


    private void updateNavbar() {
        remove(header); // Rimuove la navbar vecchia
        header = createHeaderContent(); // Ricrea la navbar aggiornata
        addToNavbar(header); // Aggiunge la nuova navbar
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("UniMarket");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }

        header.add(layout, nav);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        // Controllo se la sessione ha un valore valido per "admin"
        Object isAdminObj = VaadinSession.getCurrent().getAttribute("admin");
        boolean admin = isAdminObj instanceof Boolean && (Boolean) isAdminObj;

        System.out.println("DEBUG - Valore admin: " + admin); // Debug console

        List<MenuItemInfo> menuItems = new ArrayList<>();
        menuItems.add(new MenuItemInfo("Home", LineAwesomeIcon.PENCIL_RULER_SOLID.create(), MyViewView.class));
        menuItems.add(new MenuItemInfo("Checkout", LineAwesomeIcon.CREDIT_CARD.create(), CheckoutFormView.class));

        if (admin) {
            menuItems.add(new MenuItemInfo("Utenti", LineAwesomeIcon.FILTER_SOLID.create(), GridwithFiltersView.class));
            menuItems.add(new MenuItemInfo("Aggiungi prodotti", LineAwesomeIcon.COLUMNS_SOLID.create(), MasterDetailView.class));
        }

        return menuItems.toArray(new MenuItemInfo[0]);
    }
}


