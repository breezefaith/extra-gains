package simulator.entity;

/**
 * 程序控制块，即进程
 */
public class PCB {
    /**
     * 进程号
     */
    private int pid;
    /**
     * 本进程的段数
     */
    private int segCount;
    /**
     * 本进程的块数
     */
    private int frameCount;

    /**
     * 每一段的在段表中的下标
     */
    private int[] segIdxes;

    public PCB() {
        pid = -1;
        segCount = -1;
        frameCount = -1;
        segIdxes = new int[5];
        for (int i = 0; i < segIdxes.length; i++) {
            segIdxes[i] = -1;
        }
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSegCount() {
        return segCount;
    }

    public void setSegCount(int segCount) {
        this.segCount = segCount;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int[] getSegIdxes() {
        return segIdxes;
    }

    @Override
    public String toString() {
        return "Process" + pid;
    }
}
