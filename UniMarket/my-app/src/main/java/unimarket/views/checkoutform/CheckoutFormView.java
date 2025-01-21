package unimarket.views.checkoutform;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexWrap;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Position;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Checkout Form")
@Route("checkout-form")
@Menu(order = 2, icon = LineAwesomeIconUrl.CREDIT_CARD)
public class CheckoutFormView extends Div {

    private static final Set<String> countries = new LinkedHashSet<>();

    static {

    	countries.addAll(Arrays.asList("Afghanistan", "Albania", "Algeria", "Samoa Americane", "Andorra", "Angola",
                "Anguilla", "Antartide", "Antigua e Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
                "Austria", "Azerbaigian", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Bielorussia", "Belgio", "Belize",
                "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia ed Erzegovina", "Botswana", "Isola Bouvet",
                "Brasile", "Territorio Britannico dell'Oceano Indiano", "Isole Vergini Britanniche", "Brunei Darussalam", "Bulgaria",
                "Burkina Faso", "Burundi", "Cambogia", "Camerun", "Canada", "Capo Verde", "Isole Cayman",
                "Repubblica Centrafricana", "Ciad", "Cile", "Cina", "Isola di Natale", "Isole Cocos (Keeling)",
                "Colombia", "Comore", "Congo", "Isole Cook", "Costa Rica", "Croazia", "Cuba", "Cipro",
                "Repubblica Ceca", "Danimarca", "Gibuti", "Dominica", "Repubblica Dominicana", "Timor Est", "Ecuador",
                "Egitto", "El Salvador", "Guinea Equatoriale", "Eritrea", "Estonia", "Etiopia", "Isole Falkland",
                "Isole Faroe", "Stati Federati di Micronesia", "Figi", "Finlandia", "Francia", "Guiana Francese",
                "Polinesia Francese", "Territori Francesi del Sud", "Gabon", "Gambia", "Georgia", "Germania", "Ghana",
                "Gibilterra", "Grecia", "Groenlandia", "Grenada", "Guadalupa", "Guam", "Guatemala", "Guinea",
                "Guinea-Bissau", "Guyana", "Haiti", "Isole Heard e McDonald", "Honduras", "Hong Kong",
                "Ungheria", "Islanda", "India", "Indonesia", "Iran", "Iraq", "Irlanda", "Israele", "Italia", "Costa d'Avorio",
                "Giamaica", "Giappone", "Giordania", "Kazakistan", "Kenya", "Kiribati", "Kuwait", "Kirghizistan", "Laos",
                "Lettonia", "Libano", "Lesotho", "Liberia", "Libia", "Liechtenstein", "Lituania", "Lussemburgo", "Macao",
                "Macedonia", "Madagascar", "Malawi", "Malesia", "Maldive", "Mali", "Malta", "Isole Marshall",
                "Martinica", "Mauritania", "Mauritius", "Mayotte", "Messico", "Moldavia", "Monaco", "Mongolia",
                "Montserrat", "Marocco", "Mozambico", "Myanmar", "Namibia", "Nauru", "Nepal", "Paesi Bassi",
                "Antille Olandesi", "Nuova Caledonia", "Nuova Zelanda", "Nicaragua", "Niger", "Nigeria", "Niue",
                "Isola Norfolk", "Corea del Nord", "Isole Marianne Settentrionali", "Norvegia", "Oman", "Pakistan", "Palau",
                "Panama", "Papua Nuova Guinea", "Paraguay", "Perù", "Filippine", "Pitcairn", "Polonia", "Portogallo",
                "Porto Rico", "Qatar", "Riunione", "Romania", "Federazione Russa", "Ruanda", "Saint Kitts e Nevis",
                "Saint Lucia", "Saint Vincent e Grenadine", "Samoa", "San Marino", "Sao Tomé e Principe",
                "Arabia Saudita", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovacchia", "Slovenia",
                "Isole Salomone", "Somalia", "Sudafrica", "Georgia del Sud e Isole Sandwich Australi",
                "Corea del Sud", "Spagna", "Sri Lanka", "Sant'Elena", "Saint-Pierre e Miquelon", "Sudan", "Suriname",
                "Isole Svalbard e Jan Mayen", "Eswatini", "Svezia", "Svizzera", "Repubblica Araba Siriana",
                "Taiwan", "Tagikistan", "Tanzania", "Thailandia", "Togo", "Tokelau", "Tonga", "Trinidad e Tobago",
                "Tunisia", "Turchia", "Turkmenistan", "Isole Turks e Caicos", "Tuvalu", "Uganda", "Ucraina",
                "Emirati Arabi Uniti", "Regno Unito", "Stati Uniti", "Isole Minori degli Stati Uniti",
                "Isole Vergini Americane", "Uruguay", "Uzbekistan", "Vanuatu", "Città del Vaticano", "Venezuela",
                "Vietnam", "Isole Wallis e Futuna", "Sahara Occidentale", "Yemen", "Jugoslavia", "Zaire", "Zambia",
                "Zimbabwe"));

    }

    public CheckoutFormView() {
        addClassNames("checkout-form-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Display.GRID, Gap.XLARGE, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.add(createCheckoutForm());
        content.add(createAside());
        add(content);
    }

    private Component createCheckoutForm() {
        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Checkout");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Compila tutti i campi richiesti");
        note.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        checkoutForm.add(header, note);

        checkoutForm.add(createPersonalDetailsSection());
        checkoutForm.add(createShippingAddressSection());
        checkoutForm.add(createPaymentInformationSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }

    private Section createPersonalDetailsSection() {
        Section personalDetails = new Section();
        personalDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepOne = new Paragraph("Checkout 1/3");
        stepOne.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Informazioni personali");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        TextField name = new TextField("Nome");
        name.setRequiredIndicatorVisible(true);
        name.setPattern("[\\p{L} \\-]+");
        name.addClassNames(Margin.Bottom.SMALL);

        TextField lastName = new TextField("Cognome");
        lastName.setRequiredIndicatorVisible(true);
        lastName.setPattern("[\\p{L} \\-]+");
        lastName.addClassNames(Margin.Bottom.SMALL);
        
        EmailField email = new EmailField("Email");
        email.setRequiredIndicatorVisible(true);
        email.addClassNames(Margin.Bottom.SMALL);

        TextField phone = new TextField("Numero di telefono");
        phone.setRequiredIndicatorVisible(true);
        phone.setPattern("[\\d \\-\\+]+");
        phone.addClassNames(Margin.Bottom.SMALL);

        Checkbox rememberDetails = new Checkbox("Ricoda i miei dati");
        rememberDetails.addClassNames(Margin.Top.SMALL);

        personalDetails.add(stepOne, header, name, email, phone, rememberDetails);
        return personalDetails;
    }

    private Section createShippingAddressSection() {
        Section shippingDetails = new Section();
        shippingDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepTwo = new Paragraph("Checkout 2/3");
        stepTwo.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Indirizzo di spedizione");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        TextArea address = new TextArea("Indirizzo");
        address.setMaxLength(200);
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(Margin.Bottom.SMALL);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField postalCode = new TextField("Codice postale");
        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("[\\d \\p{L}]*");
        postalCode.addClassNames(Margin.Bottom.SMALL);

        TextField city = new TextField("Provincia");
        city.setRequiredIndicatorVisible(true);
        city.addClassNames(Flex.GROW, Margin.Bottom.SMALL);

        subSection.add(postalCode, city);

        Checkbox rememberAddress = new Checkbox("Ricorda il mio indirizzo");

        shippingDetails.add(stepTwo, header, address, subSection, rememberAddress);
        return shippingDetails;
    }

    private Component createPaymentInformationSection() {
        Section paymentInfo = new Section();
        paymentInfo.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepThree = new Paragraph("Checkout 3/3");
        stepThree.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Informazioni pagamento");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        TextField cardHolder = new TextField("Intestatario");
        cardHolder.setRequiredIndicatorVisible(true);
        cardHolder.setPattern("[\\p{L} \\-]+");
        cardHolder.addClassNames(Margin.Bottom.SMALL);

        Div subSectionOne = new Div();
        subSectionOne.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField cardNumber = new TextField("Numero carta");
        cardNumber.setRequiredIndicatorVisible(true);
        cardNumber.setPattern("[\\d ]{12,23}");
        cardNumber.addClassNames(Margin.Bottom.SMALL);

        TextField securityCode = new TextField("Security Code");
        securityCode.setRequiredIndicatorVisible(true);
        securityCode.setPattern("[0-9]{3,4}");
        securityCode.addClassNames(Flex.GROW, Margin.Bottom.SMALL);
        securityCode.setHelperText("What is this?");

        subSectionOne.add(cardNumber, securityCode);

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        Select<String> expirationMonth = new Select<>();
        expirationMonth.setLabel("Mese scadenza");
        expirationMonth.setRequiredIndicatorVisible(true);
        expirationMonth.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        Select<String> expirationYear = new Select<>();
        expirationYear.setLabel("Anno scadenza");
        expirationYear.setRequiredIndicatorVisible(true);
        expirationYear.setItems("25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35");

        subSectionTwo.add(expirationMonth, expirationYear);

        paymentInfo.add(stepThree, header, cardHolder, subSectionTwo);
        return paymentInfo;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        Button cancel = new Button("Annulla ordine");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button pay = new Button("Conferma", new Icon(VaadinIcon.LOCK));
        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        footer.add(cancel, pay);
        return footer;
    }

    private Aside createAside() {
        Aside aside = new Aside();
        aside.addClassNames(Background.CONTRAST_5, BoxSizing.BORDER, Padding.LARGE, BorderRadius.LARGE,
                Position.STICKY);
        Header headerSection = new Header();
        headerSection.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Bottom.MEDIUM);
        H3 header = new H3("Order");
        header.addClassNames(Margin.NONE);
        Button edit = new Button("Edit");
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        headerSection.add(header, edit);

        UnorderedList ul = new UnorderedList();
        ul.addClassNames(ListStyleType.NONE, Margin.NONE, Padding.NONE, Display.FLEX, FlexDirection.COLUMN, Gap.MEDIUM, Height.LARGE);

        ul.add(createListItem("da correggere", "With poca voglia di farlo io", "420.69"));
        ul.add(createListItem("Vanilla blueberry cake", "With blueberry jam", "$8.00"));
        ul.add(createListItem("Vanilla pastry", "With wholemeal flour", "$5.00"));

        aside.add(headerSection, ul);
        return aside;
    }

    private ListItem createListItem(String primary, String secondary, String price) {
        ListItem item = new ListItem();
        item.addClassNames(Display.FLEX, JustifyContent.BETWEEN);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexDirection.COLUMN);

        subSection.add(new Span(primary));
        Span secondarySpan = new Span(secondary);
        secondarySpan.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subSection.add(secondarySpan);

        Span priceSpan = new Span(price);

        item.add(subSection, priceSpan);
        return item;
    }
}
