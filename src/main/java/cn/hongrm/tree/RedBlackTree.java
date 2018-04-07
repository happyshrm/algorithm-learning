package cn.hongrm.tree;

/**
 * 以下实现参考《算法》第四版
 * 红黑树的四个特性：
 * 1,红链接均为左链接;
 * 2,没有任何一个结点同时和两条红链接相连;
 * 3,任意空链接到根结点的路径上的黑链接数量相同;
 * 4,根结点是黑链接;
 * created by hongrm on 2018/4/7 14:43
 */
public class RedBlackTree<T extends Comparable> {

    private static final Boolean RED = true;
    private static final Boolean BLACK = false;
    private Node root;

    private class Node {
        T key;
        Object value;
        Node right, left;
        int size;
        boolean color;

        public Node(T key, Object value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

    public void put(T key, Object value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, T key, Object value) {
        //新建的结点都是红色链接
        if (h == null)
            return new Node(key, value, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;
        //左边连续两个链接都是红色链接
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        //红色的右链接转为红色的左链接
        if (!isRed(h.left) && isRed(h.right)) h = rotateLeft(h);
        //左右子链接都是红色链接
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = 1 + size(h.left) + size(h.right);
        return h;
    }

    private void flipColors(Node h){
        h.right.color = BLACK;
        h.left.color = BLACK;
        h.color = RED;
    }
    /**
     * 左旋,将红色的右链接转为红色的左链接
     * 将由红链接连接的两个键的较大者作为根节点
     * 假设 x = h.right
     * 明显 x.key 比 h.key 大,即把 x 作为根节点,
     * 并把 x.left 赋给 h.right, x.left = h
     * x.color = h.color;
     * h.color = RED;
     *
     * @param h
     * @return x
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    //右旋,将红色的左链接转为红色的右链接,
    //这种情况一般出现在连续的两条左链接中
    //实现就是把左旋中的left和right相互替换即可
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    //空链接的子节点数量是0
    private int size(Node h) {
        if (h == null) return 0;
        return h.size;
    }

    //空链接默认为黑链接
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

}
