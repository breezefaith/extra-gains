package simulator.entity;

/**
 * 存储控制器
 */
public class Manager {
    /**
     * 内存块空闲状态
     */
    public static final int FRAME_FREE = 0;
    /**
     * 内存块占用状态
     */
    public static final int FRAME_OCCUPIED = 1;

    /**
     * 最大段数
     */
    public static final int MAX_FRAME_NUM = 10000;
    /**
     * 段表最大长度
     */
    public static final int MAX_SEGMENT_TABLE_LEN = 100;
    /**
     * 最大进程数
     */
    public static final int MAX_PCBS_LEN = 100;
    /**
     * 每个进程中最大段数
     */
    public static final int MAX_SEGMENT_NUM_IN_PCB = 5;

    /**
     * 内存块大小
     */
    private int frameSize;
    /**
     * 内存块数
     */
    private int frameCount;
    /**
     * 剩余内存块数
     */
    private int leftFrameCount;
    /**
     * 记录每一块内存占用状态，占用：1， 空闲：0
     */
    private int[] frameStates;
    /**
     * 段数
     */
    private int segCount;
    /**
     * 段表
     */
    private Segment[] segTable;
    /**
     * 系统进程列表
     */
    private PCB[] pcbs;
    /**
     * 当前系统中进程数
     */
    private int pcbCount;
    /**
     * 下一个可分配的进程id
     */
    private int pid;

    public Manager() {
        pid = 0;
        pcbCount = 0;

        //所有内存块均处于空闲状态
        frameStates = new int[MAX_FRAME_NUM];
        for (int i = 0; i < frameStates.length; i++) {
            frameStates[i] = FRAME_FREE;
        }

        //初始化段表
        segTable = new Segment[MAX_SEGMENT_TABLE_LEN];
        for (int i = 0; i < segTable.length; i++) {
            segTable[i] = new Segment();
        }

        //初始化进程表
        pcbs = new PCB[MAX_PCBS_LEN];
        for (int i = 0; i < pcbs.length; i++) {
            pcbs[i] = new PCB();
        }
    }

    /**
     * 初始化存储管理器
     *
     * @param frameNum  内存页（块）数
     * @param frameSize 每个内存块的大小
     * @param segNum    段数
     * @throws Exception
     */
    public void init(int frameNum, int frameSize, int segNum) throws Exception {
        if (frameNum > MAX_FRAME_NUM) {
            throw new Exception("页数大于系统最大值，请重新输入");
        }
        if (segNum > MAX_SEGMENT_TABLE_LEN) {
            throw new Exception("段表大于系统最大值，请重新输入");
        }
        this.frameCount = frameNum;
        this.frameSize = frameSize;
        this.segCount = segNum;
        this.leftFrameCount = frameNum;
    }

    /**
     * 创建进程
     *
     * @param segCnt 段数
     * @param sizes  每一段的大小
     * @throws Exception
     */
    public void createProcess(int segCnt, int[] sizes) throws Exception {
        if (segCnt > 5) {
            throw new Exception("进程最多分为5段，请重新输入段数!");
        }

        int totalSize = 0;
        for (int i = 0; i < segCnt; i++) {
            totalSize += sizes[i];
        }
        int totalNeededFrames = totalSize % frameSize == 0 ? totalSize / frameSize : totalSize / frameSize + 1;
        if(totalNeededFrames > leftFrameCount){
            throw new Exception("内存不足，仅剩余" + leftFrameCount + "页，请重新输入段大小");
        }

        int pcbIdx = pcbCount;
        int curPid = pid;
        //设置进程基本信息
        pcbs[pcbIdx].setPid(curPid);
        pcbs[pcbIdx].setSegCount(segCnt);
        //更新进程占用的内存块数
        pcbs[pcbIdx].setFrameCount(totalNeededFrames);

        //设置进程相关的段信息
        for (int i = 0; i < segCnt; i++) {
            //计算本段需要的内存块数量
            int frames = sizes[i] % frameSize == 0 ? sizes[i] / frameSize : sizes[i] / frameSize + 1;
            leftFrameCount = leftFrameCount - frames;

            //寻找第一个空闲段下标
            int j;
            for (j = 0; j < MAX_SEGMENT_TABLE_LEN; j++) {
                if (segTable[j].getState() == Segment.SEGMENT_FREE) {
                    break;
                }
            }

            //更新当前进程第i段在段表中的下标
            pcbs[pcbIdx].getSegIdxes()[i] = j;
            //当前段设置其在当前进程中的段号
            segTable[j].setSegNo(i);
            //当前段设置其在当前进程在进程列表中的下标
            segTable[j].setPcbIdx(pcbIdx);
            //当前段设置页数（内存块数）
            segTable[j].setPageCount(frames);
            //段表中下标为j的段设置为占用状态
            segTable[j].setState(Segment.SEGMENT_OCCUPIED);

            //为当前段设置页表
            Page[] pages = new Page[frames];
            for (int k = 0; k < pages.length; k++) {
                pages[k] = new Page();
            }
            segTable[j].setPageTable(pages);

            int count = 0;
            for (int k = 0; k < this.frameCount; k++) {
                if (this.frameStates[k] == FRAME_FREE) {
                    //更新内存块的占用状态
                    this.frameStates[k] = FRAME_OCCUPIED;
                    //设置页号
                    segTable[j].getPageTable()[count].setPageNo(count);
                    //设置内存块号
                    segTable[j].getPageTable()[count].setFrameNo(k);
                    //设置页的占用状态
                    segTable[j].getPageTable()[count].setState(Page.PAGE_OCCUPIED);
                    count++;
                }
                if (count == frames) {
                    break;
                }
            }
        }
        pid++;
        pcbCount++;
    }

    /**
     * 回收进程
     *
     * @param recycledPcb 要回收的进程
     * @throws Exception
     */
    public void recycleProcess(PCB recycledPcb) throws Exception {
        //查找当前进程在进程列表中的下标
        int idx = -1;
        for (int i = 0; i < pcbCount; i++) {
            if (pcbs[i].getPid() == recycledPcb.getPid()) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            throw new Exception("不存在这样的进程，请查看进程状态后重新输入");
        }

        //依次回收进程中的程序段
        for (int i = 0; i < pcbs[idx].getSegCount(); i++) {
            for (int j = 0; j < segTable[pcbs[idx].getSegIdxes()[i]].getPageCount(); j++) {
                frameStates[segTable[pcbs[idx].getSegIdxes()[i]].getPageTable()[j].getFrameNo()] = FRAME_FREE;
            }
            segTable[pcbs[idx].getSegIdxes()[i]].setPageCount(0);
            segTable[pcbs[idx].getSegIdxes()[i]].setPageTable(null);
            segTable[pcbs[idx].getSegIdxes()[i]].setPcbIdx(-1);
            segTable[pcbs[idx].getSegIdxes()[i]].setState(Segment.SEGMENT_FREE);
        }
        //进程列表中移除进程
        for (int i = idx + 1; i < pcbCount; i++) {
            pcbs[i - 1] = pcbs[i];
        }
        //进程数减1
        pcbCount--;
    }

    /**
     * 回收段
     *
     * @param recycledSeg 要回收的段
     * @throws Exception
     */
    public void recycleSegment(Segment recycledSeg) throws Exception {
        if (recycledSeg.getState() == Segment.SEGMENT_FREE) {
            throw new Exception("此段未分配，请重新输入");
        }

        //相应内存块置为空闲
        for (int i = 0; i < recycledSeg.getPageCount(); i++) {
            frameStates[recycledSeg.getPageTable()[i].getFrameNo()] = FRAME_FREE;
        }

        //从所在进程的段表中删除该段
        for (int i = recycledSeg.getSegNo() + 1; i < pcbs[recycledSeg.getPcbIdx()].getSegCount(); i++) {
            pcbs[recycledSeg.getPcbIdx()].getSegIdxes()[i - 1] = pcbs[recycledSeg.getPcbIdx()].getSegIdxes()[i];
        }
        //更新进程所占内存块大小
        pcbs[recycledSeg.getPcbIdx()].setFrameCount(pcbs[recycledSeg.getPcbIdx()].getFrameCount() - recycledSeg.getPageCount());
        //更新进程的段数
        pcbs[recycledSeg.getPcbIdx()].setSegCount(pcbs[recycledSeg.getPcbIdx()].getSegCount() - 1);
        //回收的段重置所有属性后在段表中备用
        recycledSeg.setPageCount(0);
        recycledSeg.setPageTable(null);
        recycledSeg.setPcbIdx(-1);
        recycledSeg.setState(Segment.SEGMENT_FREE);
    }

    public Segment[] getSegTable() {
        return segTable;
    }

    public PCB[] getPcbs() {
        return pcbs;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public int[] getFrameStates() {
        return frameStates;
    }

    public int getSegCount() {
        return segCount;
    }

    public int getPcbCount() {
        return pcbCount;
    }

    public int getLeftFrameCount() {
        return leftFrameCount;
    }
}
