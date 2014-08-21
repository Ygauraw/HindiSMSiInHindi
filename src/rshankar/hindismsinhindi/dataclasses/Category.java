package rshankar.hindismsinhindi.dataclasses;

public class Category {
public int categoryId;
public String categoryName;

public Category(int categoryId,String categoryName) {
	this.categoryId=categoryId;
	this.categoryName=categoryName;
	
}

public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public String getCategoryName() {
	return this.categoryName;
}

}
