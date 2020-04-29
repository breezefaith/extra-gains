package simulator.entity;

/**
 * 页
 */
public class Page {
    /**
     * 空闲状态
     */
    public static final int PAGE_FREE = 0;
    /**
     * 占用状态
     */
    public static final int PAGE_OCCUPIED = 1;

    /**
     * 是否被占用
     */
    private int state;
    /**
     * 块号
     */
    private int frameNo;

    /**
     * 页号
     */
    private int pageNo;

    public Page() {
        state = PAGE_FREE;
        frameNo = -1;
        pageNo = -1;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(int frameNo) {
        this.frameNo = frameNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "Page" + pageNo;
    }
}
