package unimarket.views.myview;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route("")
public class Login extends Composite<VerticalLayout> {

    private final UtenteService utenteService;

    public Login(UtenteService utenteService) {
        this.utenteService = utenteService;
      // login 
        // Imposta il layout principale
        VerticalLayout mainLayout = getContent();
        mainLayout.setSizeFull(); // Rende il layout a tutta altezza
        mainLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Centra gli elementi orizzontalmente
        mainLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); // Centra verticalmente

        // Creiamo un contenitore centrale
        VerticalLayout loginContainer = new VerticalLayout();
        loginContainer.setPadding(true);
        loginContainer.setSpacing(true);
        loginContainer.getStyle().set("border", "1px solid #ccc");
        loginContainer.getStyle().set("border-radius", "8px");
        loginContainer.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.1)");
        loginContainer.getStyle().set("width", "400px"); // Imposta la larghezza del contenitore
        loginContainer.setAlignItems(FlexComponent.Alignment.CENTER);

            // Aggiungiamo il form di login
            LoginForm loginForm = new LoginForm();
            loginForm.getStyle().set("width", "100%"); // Larghezza del form di login
            loginForm.addLoginListener(event -> {
            String username = event.getUsername();
            String password = event.getPassword();

            // Chiamata al backend per validare le credenziali
            boolean isValid = validateCredentials(username, password);
            if (isValid) {
                UI.getCurrent().navigate(""); // Naviga  pagina principale
            } else {
                loginForm.setError(true); // Mostra un errore
            }
        });

        // Creiamo un testo e un pulsante per i nuovi utenti
        Span newUserText = new Span("Sei un nuovo utente?");
        Button registerButton = new Button("Registrati", event -> UI.getCurrent().navigate("login"));
        registerButton.getStyle().set("width", "100%"); // Larghezza del pulsante

        // Aggiungiamo il testo e il pulsante al layout
        VerticalLayout newUserLayout = new VerticalLayout(newUserText, registerButton);
        newUserLayout.setSpacing(true);
        newUserLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        newUserLayout.getStyle().set("width", "100%"); // Larghezza del layout

        // Aggiungiamo tutti gli elementi al contenitore principale
        loginContainer.add(loginForm, newUserLayout);
        mainLayout.add(loginContainer);
    }

    /**
     * deve essere sostituito con una chiamata reale al backend !!!!!!!!
     */
    private boolean validateCredentials(String username, String password) {
        return "admin@example.com".equals(username) && "password123".equals(password);
// backend
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.services.UtenteService;

@
        LoginForm loginForm = new LoginForm();

        getContent().setWidth("100%");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
        getContent().add(loginForm);

        loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            String password = event.getPassword();

            if (utenteService.login(email, password)) {
                Notification.show("Login riuscito!", 3000, Notification.Position.MIDDLE);
                Integer userId = utenteService.getUserIdByEmail(email);
                VaadinSession.getCurrent().setAttribute("userId", userId);
                UI.getCurrent().navigate(MyViewView.class);
            } else {
                Notification.show("Email o password errati", 3000, Notification.Position.MIDDLE);
                loginForm.setError(true);
            }
        });

    }
}
