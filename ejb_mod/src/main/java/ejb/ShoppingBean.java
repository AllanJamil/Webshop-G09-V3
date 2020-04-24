package ejb;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.List;

@Stateful(name = "ShoppingEJB")
public class ShoppingBean implements ShoppingBeanLocal{

    private String search;
    private List<Record> records;

    public ShoppingBean() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
