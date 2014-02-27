/**
 * Created with IntelliJ IDEA.
 * User: Manu
 * Date: 11/10/13
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class Pnode {
    char ch;
    int freq;
    Pnode left,right,next;
    Pnode(){
        freq=ch='\0';
        next=left=right=null;
    }
    Pnode(char ch,int freq,Pnode left,Pnode right){
        this.ch=ch;
        this.freq=freq;
        this.left=left;
        this.right=right;
        this.next=null;
    }
    Pnode(Pnode t){
        this.ch=t.ch;
        this.freq=t.freq;
        this.left=t.left;
        this.right=t.right;
        this.next=null;
    }
    public boolean isLeaf(){
        return left==null && right == null;
    }
};
