package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

public class InfoCardItem {
    private int imageView;
    private int title;
    private  int description;
    private int recipe;
    private String titleString;

    public InfoCardItem(int imageView, int title, int description, int recipe, String titleString) {
        this.imageView = imageView;
        this.title = title;
        this.description = description;
        this.recipe = recipe;
        this.titleString = titleString;
    }

    public InfoCardItem(int imageView, int title, int description, int recipe) {
        this.imageView = imageView;
        this.title = title;
        this.description = description;
        this.recipe = recipe;
    }



    public int getImageView() {
        return imageView;
    }

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public int getRecipe() { return recipe; }

    public String getTitleString() { return titleString; }
}
