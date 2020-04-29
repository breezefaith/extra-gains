package simulator.view;

/**
 * 进程树节点自定义对象
 * @param <T>
 */
public class TreeNodeObject<T> {
    private T obj;
    private TreeNodeTypeEnum type;

    public TreeNodeObject(T label, TreeNodeTypeEnum type) {
        this.obj = label;
        this.type = type;
    }

    public T getObj() {
        return obj;
    }

    public TreeNodeTypeEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return obj.toString();
    }
}
