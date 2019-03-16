package service;

import data.interfaces.IQuestionLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides all defined Question layouts.
 * Every Game can have its own layouts, therefor every Game needs its own QuestionLayoutProvider.
 */
public class QuestionLayoutProvider {

    private List<IQuestionLayout> layouts;

    // region Constructors

    /**
     * Creates a new QuestionLayoutProvider with given layouts
     *
     * @param layouts Layout, which will be added to the Layout List
     */
    public QuestionLayoutProvider(List<IQuestionLayout> layouts) {
        this.layouts = new ArrayList<>();
        if (layouts != null) {
            this.layouts.addAll(layouts);
        }
    }

    /**
     * Create empty QuestionLayoutProvider
     */
    public QuestionLayoutProvider() {
        this(null);
    }

    // endregion

    /**
     * add a new Layout to the Layout List.
     *
     * @param layout Layout to be added
     */
    public void addLayout(IQuestionLayout layout) {
        this.layouts.add(layout);
    }

    /**
     * Get a copy of a Layout to set it to an Question
     *
     * @param name Name of the Layout
     * @return Copy of the Layout
     */
    public IQuestionLayout getLayout(String name) {
        //TODO Create Copy of Layout and return it
        throw new UnsupportedOperationException();
    }

    /**
     * Clears all layouts and add the new ones.
     *
     * @param layouts New Game layouts
     */
    public void initLayouts(List<IQuestionLayout> layouts) {
        this.layouts.clear();
        this.layouts.addAll(layouts);
    }

}
