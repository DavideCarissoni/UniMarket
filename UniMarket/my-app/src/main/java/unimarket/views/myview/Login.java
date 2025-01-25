package unimarket.views.myview;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("My View2")
@Route("my-view")
@Menu(order = 5, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class Login extends Composite<VerticalLayout> {

    public Login() {
        LoginForm loginForm = new LoginForm();
        Button buttonSecondary = new Button();
        TextField textField = new TextField();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(loginForm);
        
        textField.setLabel("Nome");

		buttonSecondary.setText("Annulla");
        buttonSecondary.setWidth("fill");
    }
}
