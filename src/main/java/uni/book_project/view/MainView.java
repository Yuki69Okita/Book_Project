package uni.book_project.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;
import uni.book_project.Book;
import uni.book_project.BookEditor;
import uni.book_project.BookRepository;

@Route
public class MainView extends VerticalLayout {

    private final BookRepository repo;

    private final BookEditor editor;

    final Grid<Book> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(BookRepository repo, BookEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Book.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("Add Book", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "bookName", "bookGenre");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by Genre");


        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listBook(e.getValue()));

        // Connect selected Book to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editBook(e.getValue());
        });

        // Instantiate and edit new Book the new button is clicked
        addNewBtn.addClickListener(e -> editor.editBook(new Book("", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listBook(filter.getValue());
        });

        // Initialize listing
        listBook(null);
    }

    void listBook(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findBybookGenreStartsWithIgnoreCase(filterText));
        }
    }
}