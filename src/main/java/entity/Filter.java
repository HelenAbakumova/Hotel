package entity;

public class Filter {
    private String sort;
    private String priceFrom;
    private String priceTo;
    private String category;

    public Filter(String sort, String priceFrom, String priceTo, String category) {
        this.sort = sort;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.category = category;
    }

    public Filter() {
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "sort='" + sort + '\'' +
                ", priceFrom='" + priceFrom + '\'' +
                ", priceTo='" + priceTo + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
