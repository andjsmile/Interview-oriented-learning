import org.graalvm.compiler.graph.Node;

/**
 * 二叉树
 */
public class BinaryTreeTest{

    class Node{
        public Node left;
        public Node right;
        public Object value;
    }

    public void add(Object v){
        if(value==null){
            value=v;
        }else{
            if((Integer)v- (Integer)(value) <0){
                if(left==null){
                    left=new Node();
                }
                left.add(v);
            }else{
                if(right==null){
                    right=new Node();
                }
                right.add(v);
            }
        }
    }
}