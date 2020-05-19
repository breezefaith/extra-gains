package simulator.entity;

/**
 * 段
 */
public class Segment {
    /**
     * 空闲状态
     */
    public static final int SEGMENT_FREE = 0;
    /**
     * 占用状态
     */
    public static final int SEGMENT_OCCUPIED = 1;
    /**
     * 空闲状态
     */
    private int state;
    /**
     * 页数（内存块数）
     */
    private int pageCount;
    /**
     * 本段所属进程在进程列表中的下标
     */
    private int pcbIdx;
    /**
     * 段号，本段在所属进程中的下标
     */
    private int segNo;
    /**
     * 页表
     */
    private Page[] pageTable;

    public Segment() {
        state = SEGMENT_FREE;
        segNo = 0;
        pcbIdx = -1;
        pageCount = 0;
        pageTable = null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPcbIdx() {
        return pcbIdx;
    }

    public void setPcbIdx(int pcbIdx) {
        this.pcbIdx = pcbIdx;
    }

    public int getSegNo() {
        return segNo;
    }

    public void setSegNo(int segNo) {
        this.segNo = segNo;
    }

    public Page[] getPageTable() {
        return pageTable;
    }

    public void setPageTable(Page[] pageTable) {
        this.pageTable = pageTable;
    }

    @Override
    public String toString() {
        return "Segment" + segNo;
    }
}
