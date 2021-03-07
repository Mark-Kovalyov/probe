package mayton.probe.ignite.jdbc;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.Objects;

public class Equity implements Serializable {

    @QuerySqlField(index = true)
    private long id;

    @QuerySqlField(index = true)
    private String ticker;

    @QuerySqlField(index = false)
    private String desc;

    public Equity(long id, String ticker, String desc) {
        this.id = id;
        this.ticker = ticker;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equity equity = (Equity) o;
        return id == equity.id &&
                Objects.equals(ticker, equity.ticker) &&
                Objects.equals(desc, equity.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticker, desc);
    }
}
