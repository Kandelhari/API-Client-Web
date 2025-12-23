package MiguelRios.Kandelhari.ApiOfProducts.Core.Entities.ConstantValues;

public class DataBaseValues {

    // COMMON COLUMNS:
    public static final String ID = "id";
    public static final boolean IS_NOT_NULLABLE = false;
    public static final boolean IS_NULLABLE = true;
    public static final boolean IS_UNIQUE = true;


    // this file has the next structure for each table on DB:

    // TABLE:
    // public static final String TABLE_NAME = "name_of_table_on_db";
    // COLUMN:
    // public static final String COLUMN_NAME = "name_of_column_on_db";
    // public static final String COLUMN_BLANKED_MESSAGE = "Column name is required";
    // public static final String COLUMN_OVER_MAX_SIZE_MESSAGE = "Column name must not exceed 20 characters";
    // public static final int COLUMN_MAX_SIZE = 20;

    public static final String TABLE_PRODUCT_CATEGORY = "product_category";

    // CATEGORY
    public static final String TABLE_CATEGORY = "category";
    // name:
    public static final String CATEGORY_NAME = "name";
    public static final String CATEGORY_NAME_BLANKED_MESSAGE = "Category name is required";
    public static final String CATEGORY_NAME_OVER_MAX_SIZE_MESSAGE = "Category name must not exceed 20 characters";
    public static final int CATEGORY_NAME_MAX_SIZE = 20;


    // PRODUCT:
    public static final String TABLE_PRODUCT = "product";
    // name:
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_NAME_BLANKED_MESSAGE = "Product name is required";
    public static final int PRODUCT_NAME_MAX_SIZE = 20;
    public static final String PRODUCT_NAME_OVER_MAX_SIZE_MESSAGE = "Product name must not exceed 20 characters";
    // amount:
    public static final String PRODUCT_AMOUNT = "amount";
    public static final String PRODUCT_AMOUNT_NULL_MESSAGE = "Product amount is required";
    public static final int PRODUCT_AMOUNT_MAX_VALUE = 2000000;
    public static final int PRODUCT_AMOUNT_MIN_VALUE = -2000000;
    public static final String PRODUCT_AMOUNT_OVER_MAX_VALUE_MESSAGE = "Product amount must not exceed 2000000";
    public static final String PRODUCT_AMOUNT_UNDER_MIN_VALUE_MESSAGE = "Product amount must not be under -2000000";

    // PRODUCT_CATEGORY
    public static final String PRODUCTCATEGORY_CATEGORY_ID = "category_id";
    public static final String PRODUCTCATEGORY_PRODUCT_ID = "product_id";

    private DataBaseValues() {}
}
