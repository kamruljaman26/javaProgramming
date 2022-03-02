/**
 * This class represent decompressed data as tuple
 * where 3 value will store offset, length, and character
 */
public class Tuple {

    private Integer offset;
    private Integer length;
    private Character chr;

    public Tuple(Integer offset, Integer length, Character chr) {
        this.offset = offset;
        this.length = length;
        this.chr = chr;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Character getChr() {
        return chr;
    }

    public void setChr(Character chr) {
        this.chr = chr;
    }

    @Override
    public String toString() {
        return "(" + offset + "," + length + "," + chr + ")";
    }
}
