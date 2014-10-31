package rshankar.hindismsinhindi.dataclasses;

import java.util.List;

public class MainCategory extends Category {
public MainCategory(int categoryId, String categoryName) {
		super(categoryId, categoryName);
	}
public MainCategory() {
}
public List<Category> mSubCategoryList;

}
