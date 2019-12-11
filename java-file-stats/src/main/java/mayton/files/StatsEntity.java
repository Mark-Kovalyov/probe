package mayton.files;

public class StatsEntity {

    private int count;
    private long length;
    private long rows;

    public StatsEntity() {
    }

    public StatsEntity(int count, long length) {
        this.count = count;
        this.length = length;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "count=" + count + ", length=" + length;
    }

}
