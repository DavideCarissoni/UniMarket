package unimarket.views.myview;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import componenti.Prodotto;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.components.avataritem.AvatarItem;
import unimarket.services.ProdottoService;

@PageTitle("Homepage")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class MyViewView extends Composite<VerticalLayout> {
  
    public MyViewView(ProdottoService prodottoService) {        
        HorizontalLayout layoutRow = new HorizontalLayout();
        Tabs tabs = new Tabs();
        FormLayout formLayout3Col = new FormLayout();
        H1 h1 = new H1();
        RouterLink routerLink = new RouterLink();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Select select = new Select();
        MultiSelectComboBox multiSelectComboBox = new MultiSelectComboBox();
        MultiSelectListBox textItems = new MultiSelectListBox();
        MultiSelectListBox avatarItems = new MultiSelectListBox();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        Hr hr = new Hr();
        
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(layoutRow);
        getContent().add(layoutRow2);

        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("100px");
        layoutRow.add(tabs);
        layoutRow.add(formLayout3Col);
        tabs.setWidth("555px");
        
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2), new ResponsiveStep("500px", 3));
        formLayout3Col.add(h1);
        formLayout3Col.add(routerLink);

        h1.setText("UniMarket");
        h1.setWidth("max-content");
        
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow2.add(layoutColumn2);
        layoutRow2.add(layoutColumn3);

        layoutColumn2.setWidth("180px");
        layoutColumn2.getStyle().set("flex-grow", "1");  
        layoutColumn2.add(select);
        layoutColumn2.add(multiSelectComboBox);
        layoutColumn2.add(textItems);
        layoutColumn2.add(avatarItems);
        
        select.setLabel("Select");
        select.setWidth("130px");
        setSelectSampleData(select);
        
        multiSelectComboBox.setLabel("Multi-Select Combo Box");
        multiSelectComboBox.setWidth("130px");
        setMultiSelectComboBoxSampleData(multiSelectComboBox);
        
        textItems.setWidth("130px");
        setMultiSelectListBoxSampleData(textItems);
        
        avatarItems.setWidth("130px");
        setAvatarItemsSampleData(avatarItems);
        
        layoutColumn3.addClassName(Padding.SMALL);
        layoutColumn3.setWidth("3%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn3.add(hr);
        
        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        
        FlexLayout layoutColumn4 = new FlexLayout();
        layoutColumn4.setWidthFull();
        layoutColumn4.getStyle()
        	.set("flexWrap", "wrap")       
        	.set("justifyContent", "space-evenly") 
        	.set("gap", "10px");           
        
        for(Prodotto prodotto : prodotti) {

            // Create a new VerticalLayout for the box
            VerticalLayout boxLayout = new VerticalLayout();
            boxLayout.setHeight("350px"); 
            boxLayout.setWidth("100%");
            boxLayout.getStyle()            	
            	.set("flex", "1 1 200px")
            	.set("max-width", "250px") 
            	.set("min-width", "200px") 
            	.set("border-radius", "15px")
            	.set("border", "1px solid #ccc")
            	.set("background-color", "#8ba6cc")
            	.set("padding", "15px")
            	.set("box-shadow", "2px 2px 5px rgba(0,0,0,0.1");  
            
            boxLayout.setAlignItems(Alignment.CENTER);
            boxLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
            
            Button buttonPrimary = new Button(); 
            buttonPrimary.setText("Aggiungi");
            buttonPrimary.setWidthFull();
            buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            // Contenitore per i testi
            VerticalLayout contentContainer = new VerticalLayout();
            contentContainer.setWidthFull();
            contentContainer.setPadding(false);
            contentContainer.setSpacing(false);
            contentContainer.getStyle().set("flex-grow", "1");
            
            // Create an Image component
            Image image = new Image("media/icon.jpg", "Product Image");
            image.setWidth("100%"); 
            
            // Create a Span for the name
            Span name = new Span(prodotto.getNome());
            name.getStyle().set("font-weight", "bold"); 
            
            Span description = new Span(prodotto.getDescrizione());
            description.getStyle()
            	.set("font-size", "12px") 
            	.set("color", "#fff") 
            	.set("text-align", "center")
            	.set("max-height", "40px") // Limita l'altezza della descrizione
            	.set("overflow", "hidden") // Nasconde il testo in eccesso
            	.set("margin-bottom", "2px");

            // Create a Span for the price
            Span price = new Span(String.format("$%.2f", prodotto.getPrezzo()));
            price.getStyle().set("color", "green").set("font-size", "14px");

            // Aggiunge i componenti nel contenitore di testo
            contentContainer.add(name, description, price);
            
            // Imposta crescita degli elementi per mantenere il pulsante in fondo
            boxLayout.setFlexGrow(1, contentContainer);
            boxLayout.setFlexGrow(0, buttonPrimary);
            
            // Add the components to the box layout
            boxLayout.add(image, contentContainer, buttonPrimary);

            // Add the box layout to the right column
            layoutColumn4.add(boxLayout);
        }
       
        layoutRow2.add(layoutColumn4);

    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setSelectSampleData(Select select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

    private void setMultiSelectComboBoxSampleData(MultiSelectComboBox multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }

    private void setMultiSelectListBoxSampleData(MultiSelectListBox multiSelectListBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        multiSelectListBox.setItems(sampleItems);
        multiSelectListBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
        multiSelectListBox.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

    private void setAvatarItemsSampleData(MultiSelectListBox multiSelectListBox) {
        record Person(String name, String profession) {
        }
        ;
        List<Person> data = List.of(new Person("Aria Bailey", "Endocrinologist"), new Person("Aaliyah Butler", "Nephrologist"), new Person("Eleanor Price", "Ophthalmologist"), new Person("Allison Torres", "Allergist"), new Person("Madeline Lewis", "Gastroenterologist"));
        multiSelectListBox.setItems(data);
        multiSelectListBox.setRenderer(new ComponentRenderer(item -> {
            AvatarItem avatarItem = new AvatarItem();
            avatarItem.setHeading(((Person) item).name);
            avatarItem.setDescription(((Person) item).profession);
            avatarItem.setAvatar(new Avatar(((Person) item).name));
            return avatarItem;
        }));
    }
}
