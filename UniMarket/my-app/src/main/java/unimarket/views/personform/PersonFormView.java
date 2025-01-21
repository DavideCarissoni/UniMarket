package unimarket.views.personform;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Login")
@Route("person-form")
@Menu(order = 1, icon = LineAwesomeIconUrl.USER)
public class PersonFormView extends Composite<VerticalLayout> {

    public PersonFormView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        EmailField emailField = new EmailField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutColumn2.add(h3);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.add(formLayout2Col);
        layoutColumn2.add(layoutRow);
        
        h3.setText("Informazioni personali");
        h3.setWidth("100%");
        
        formLayout2Col.setWidth("100%");
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(textField3);
        formLayout2Col.add(emailField);
        
        textField.setLabel("Nome");
        textField2.setLabel("Cognome");
        textField3.setLabel("Numero di telefono");
        emailField.setLabel("Email");
        
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
        
        buttonPrimary.setText("Salva");
        buttonPrimary.setWidth("fill");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        buttonSecondary.setText("Annulla");
        buttonSecondary.setWidth("fill");
        
        getContent().add(layoutColumn2);
       
       
    }
}
