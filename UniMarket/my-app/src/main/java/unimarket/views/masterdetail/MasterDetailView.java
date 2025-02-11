package unimarket.views.masterdetail;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import componenti.Prodotto;

import java.util.Objects;
import java.util.Optional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.services.ProdottoService;

@PageTitle("Master-Detail")
@Route("master-detail/:prodottoID?/:action?(edit)")
@Menu(order = 4, icon = LineAwesomeIconUrl.COLUMNS_SOLID)
@Uses(Icon.class)
public class MasterDetailView extends Div implements BeforeEnterObserver {

    private final String PRODOTTO_ID = "prodottoID";
    private final String PRODOTTO_EDIT_ROUTE_TEMPLATE = "master-detail/%s/edit";

    private final Grid<Prodotto> grid = new Grid<>(Prodotto.class, false);

    private IntegerField codice;
    private TextField nome;
    private TextArea descrizione;
    private NumberField prezzo;
    private IntegerField quantita;

    private Prodotto prodotto;
    
    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final BeanValidationBinder<Prodotto> binder;

    private final ProdottoService prodottoService;

    public MasterDetailView(ProdottoService prodottoService) {
		this.prodottoService = prodottoService;
		this.binder = new BeanValidationBinder<>(Prodotto.class);
		
        addClassNames("master-detail-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn(Prodotto::getCodice).setHeader("Codice").setAutoWidth(true);
        grid.addColumn(Prodotto::getNome).setHeader("Nome").setAutoWidth(true);
        grid.addColumn(Prodotto::getDescrizione).setHeader("Descrizione").setAutoWidth(true);
        grid.addColumn(Prodotto::getPrezzo).setHeader("Prezzo").setAutoWidth(true);
        grid.addColumn(prodotto -> prodottoService.getQuantitaById(prodotto.getCodice()))
                .setHeader("Quantità")
                .setAutoWidth(true);

        grid.setItems(DataProvider.fromCallbacks(
        	    query -> prodottoService.getProdottiPaginati(query.getOffset(), query.getLimit()).stream(),
        	    query -> prodottoService.countProdotti()
        	));

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(PRODOTTO_EDIT_ROUTE_TEMPLATE, event.getValue().getCodice()));
            } else {
                clearForm();
                UI.getCurrent().navigate(MasterDetailView.class);
            }
        });

        // Configure Form
        binder.forField(prezzo)
        .withNullRepresentation(0.0)
        .withConverter(
            new Converter<Double, Float>() {
                @Override
                public Result<Float> convertToModel(Double value, ValueContext context) {
                    return value == null ? Result.ok(0.0f) : Result.ok(value.floatValue());
                }

                @Override
                public Double convertToPresentation(Float value, ValueContext context) {
                    return value == null ? 0.0 : value.doubleValue();
                }
            })
        .bind(Prodotto::getPrezzo, Prodotto::setPrezzo);

        binder.forField(quantita)
        .bind(Prodotto::getQuantita, Prodotto::setQuantita);

        binder.forField(nome)
        .bind(Prodotto::getNome, Prodotto::setNome);
        
        binder.forField(descrizione)
        .bind(Prodotto::getDescrizione, Prodotto::setDescrizione);
        
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.prodotto == null) {
                    this.prodotto = new Prodotto();
                }

                binder.writeBean(this.prodotto);
                
                this.prodotto.setNome(nome.getValue());
                this.prodotto.setDescrizione(descrizione.getValue());
                this.prodotto.setPrezzo(prezzo.getValue().floatValue());
                this.prodotto.setQuantita(quantita.getValue() != null ? quantita.getValue().intValue() : 0);

                if (this.prodotto.getCodice() == 0) { 
                    int nuovoCodice = prodottoService.generaNuovoCodice();
                    this.prodotto.setCodice(nuovoCodice);
                }
                
                prodottoService.nuovoProdotto(this.prodotto);
                clearForm();
                refreshGrid();
                Notification.show("Prodotto aggiunto con successo");
                UI.getCurrent().navigate(MasterDetailView.class);
                
            } catch (ValidationException |ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                    "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> prodottoId = event.getRouteParameters().get(PRODOTTO_ID).map(Long::parseLong);
        if (prodottoId.isPresent()) {
            Optional<Prodotto> prodottoFromBackend = prodottoService.get(prodottoId.get());
            if (prodottoFromBackend.isPresent()) {
                populateForm(prodottoFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested prodotto was not found, ID = %s", prodottoId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(MasterDetailView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        codice = new IntegerField("Codice");
        codice.setReadOnly(true);
        nome = new TextField("Nome");
        descrizione = new TextArea("Descrizione");
        prezzo = new NumberField("Prezzo");
        quantita = new IntegerField("Quantità");

        formLayout.add(codice, nome, descrizione, prezzo, quantita);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
    	grid.setItems(prodottoService.getAllProdotti());
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Prodotto value) {
        this.prodotto = value;
        if (value != null) {
            codice.setValue(value.getCodice());
            nome.setValue(Objects.toString(value.getNome(), ""));
            descrizione.setValue(Objects.toString(value.getDescrizione(), ""));
            prezzo.setValue(value.getPrezzo() != null ? value.getPrezzo() : 0.0);
            quantita.setValue(value.getQuantita());
        } else {
            codice.clear();
            nome.clear();
            descrizione.clear();
            prezzo.clear();
            quantita.clear();
        }
    }
}
