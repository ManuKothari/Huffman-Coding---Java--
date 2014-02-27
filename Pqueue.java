import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Manu
 * Date: 11/10/13
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class Pqueue {
    Pnode pq,root;
    int count,n=0;
    static final int X = 256;
    String table[],input_string,encoded_string,filename;
     //int ip_size,op_size;
     //double ratio = 0.0;
    Pqueue(){
       // count=ip_size=op_size=0;
        input_string=encoded_string="";
        pq=null;
    }
    public void insert(Pnode node){
        count++;
        if(pq==null){
            pq=node;
        }
        else if(pq.freq>node.freq){
            node.next=pq;
            pq=node;
        }
        else{
            Pnode temp = pq;
            Pnode prev = null;
            while(temp.next!=null && temp.freq<=node.freq){
                prev=temp;
                temp=temp.next;
            }
            if(temp.freq>node.freq){
                prev.next=node;
                node.next=temp;
            }
            else{
             temp.next=node;
            }

        }
    }
    public Pnode delete(){
        count--;
        if(pq==null){
            System.out.println("Unexpected error! ");
            return null;
        }
        else{
            Pnode temp= new Pnode(pq);
            pq=pq.next;
            return temp;
        }
    }
    public void display(){
           for(int i=0;i<X;i++)
               if(table[i]!=null)
                    System.out.println((char)i+" "+ table[i]);
    }

    public void encode() /*throws IOException, FileNotFoundException*/{
        //Scanner in = new Scanner(System.in);
        //System.out.println("Enter 0 for String input or 1 for Filename input");
        //int k=in.nextInt();
        
        //System.out.println("Enter the input string: ");
        //input_string = in.nextLine();
        //input_string = in.nextLine();
        
        /*else{
            //C:\Users\Manu\Desktop\Workspace\IntelliJ\HuffmanCoding\src\test.txt
            System.out.print("Enter the filepath: ");
            filename = in.next();
            BufferedReader br= new BufferedReader(new FileReader(filename));
            String line;
            while((line=br.readLine())!=null)
                input_string+=line;
        }*/
        //System.out.println("Input string: "+ input_string);
         //System.out.println("Size occupied by input string(in bytes): "+ ( ip_size=input_string.length()*2));
        // ip_size=input_string.length()*2;
        int freq[] = new int[X];
        pq=null;
        for(int i=0;i<input_string.length();i++)
            freq[input_string.charAt(i)]++;
        for(int i=0;i<X;i++)
            if(freq[i]>0)
                insert(new Pnode((char) i,freq[i],null,null));
        /*System.out.print("Before merging\n");
        display();*/
        while(count>1){
            Pnode x1=delete();
            Pnode x2=delete();
            insert(new Pnode('\0',x1.freq + x2.freq,x1,x2));
        }
        root=delete();
        table = new String[X];
        traverse(root,"");
        encoded_string = "";
        
        //display();
        //System.out.println("*************ENCODED STRING**************");
        for(int i=0;i<input_string.length();i++)
            encoded_string+=table[input_string.charAt(i)];
        //System.out.println("Encoded String: "+encoded_string);
        //encode_ascii(encoded_string);
        /*decode_ascii();
        System.out.println("*************DECODED STRING**************");
        decode();*/
    }

    /**
     *
     * @param filename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void encode_file(String filename)throws FileNotFoundException, IOException
    {
     
            //C:\Users\Manu\Desktop\Workspace\IntelliJ\HuffmanCoding\src\test.txt
            //System.out.print("Enter the filepath: ");
            
           
                BufferedReader br= new BufferedReader(new FileReader(filename));
                String line;
                
                while((line=br.readLine())!=null)
                   input_string+=line;
                //ip_size=input_string.length()*2;
                int freq[] = new int[X];
                pq=null;
                for(int i=0;i<input_string.length();i++)
                     freq[input_string.charAt(i)]++;
                for(int i=0;i<X;i++)
                   if(freq[i]>0)
                     insert(new Pnode((char) i,freq[i],null,null));
           
                while(count>1){
                     Pnode x1=delete();
                     Pnode x2=delete();
                     insert(new Pnode('\0',x1.freq + x2.freq,x1,x2));
                 }
                root=delete();
                table = new String[X];
                traverse(root,"");
                encoded_string = "";
       
                for(int i=0;i<input_string.length();i++)
                 encoded_string+=table[input_string.charAt(i)];
                
               br.close();
    }
    
    public void traverse(Pnode root,String code){
        if(root!=null){
            if(root.isLeaf())
                table[root.ch]=code;
            traverse(root.left,code+"0");
            traverse(root.right,code+"1");
        }
    }
    public void encode_ascii(String encoded_string) throws IOException
    {
        File op = new File("output.txt");
        n=0;
        BufferedWriter bw = new BufferedWriter(new FileWriter(op));
        String temp=String.valueOf(encoded_string.charAt(0));
        for(int i=1;i<encoded_string.length();i++)
            if(i%8==0){
                //System.out.println(temp);
                bw.write((char)(Integer.parseInt(temp, 2)));
                temp=String.valueOf(encoded_string.charAt(i));
            }
            else
                temp+=String.valueOf(encoded_string.charAt(i));
            //System.out.println(temp);
            if(temp.length()==8)
                bw.write((char)(Integer.parseInt(temp, 2)));
            else{
                /*bw.write('X');
                bw.write(temp);
            */
            n=8-temp.length();
            while(temp.length()!=8)
                temp="0"+temp;
             bw.write((char)(Integer.parseInt(temp, 2)));
            }
        bw.close();
    }
    public void decode_ascii() throws IOException
    {
        String temp="";
        String decoded_string="";
        File file=new File("output.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line,s="";
        while((line=br.readLine())!=null)
            s+=line;
       // op_size=s.length()*2;
        //System.out.println("S:" + s);
        //System.out.println("Size occupied by output string(in bytes): "+ (op_size=s.length()*2));
        //System.out.println("Compression is approximately: " + ((ip_size-op_size)/(double)ip_size)*100 + "%");
        //ratio = (((ip_size-op_size)/(double)ip_size)*100);
        int i=0;
        for(;i<s.length()-1;i++){
             temp=Integer.toBinaryString((int)s.charAt(i));
            if(temp.length()<8){
                while(temp.length()!=8)
                    temp="0"+temp;
            }
            //System.out.println(temp);
            decoded_string+=temp;
        }
         temp=Integer.toBinaryString((int)s.charAt(i));
         if(temp.length()<8){
                while(temp.length()!=8)
                    temp="0"+temp;
            }
         temp=temp.substring(n);
         decoded_string+=temp;
        /*if(i!=s.length() && s.charAt(i)=='X'){
            for(i=i+1;i<s.length();i++)
                decoded_string+=String.valueOf(s.charAt(i));
        }*/
        System.out.println("Decoded String: " + decoded_string);
        encoded_string=decoded_string;
        decoded_string = "";
        
    }

    public String decode(){
        String ret = "";
        Pnode temp =root;
        for(int i=0;i<encoded_string.length();i++){
            if(encoded_string.charAt(i)=='0')
                temp=temp.left;
            else
                temp=temp.right;
            if(temp.isLeaf()){
                ret = ret+temp.ch;
                temp=root;
            }
        }
        
        return ret;
       
    }
};

