import java.util.*;

public class Main {
    public TreeNode getTree(int[] arrays){
        TreeNode treeNode = new TreeNode(arrays[0]);
        for(int i=1;i<arrays.length;i++){
            insertIntoTree(arrays[i],treeNode);
        }
        return treeNode;
    }
    public void insertIntoTree(int val,TreeNode treeNode) {
         if(val>treeNode.val&&treeNode.right==null){
             TreeNode insert = new TreeNode(val);
             treeNode.right = insert;

         }else if(val<treeNode.val&&treeNode.left==null){
             TreeNode insert = new TreeNode(val);
             treeNode.left = insert;
         }
         else if(val<treeNode.val){
             insertIntoTree(val,treeNode.left);

         }
         else if(val>treeNode.val){
             insertIntoTree(val,treeNode.right);

         }

    }
    public void heapSort(int [] arrays){
        for(int i=arrays.length/2;i>0;i--){
            swit(arrays,i,arrays.length-1);
        }
        for(int i=arrays.length-1;i>0;i--){
            swap(0,i,arrays);
            swit(arrays,0,i-1);
        }

    }
    public void swap(int i,int j,int[] arrays){
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
    public void swit(int[] arrays,int parent,int end){
        int child = 2*parent+1;
        int vol = arrays[parent];
        while(child<=end){
            if(child<end&&arrays[child]<arrays[child+1]){
                child = child+1;
            }
            if(vol>arrays[child]){
                arrays[parent] = arrays[child];
                parent=child;
                child = parent*2+1;
            }else
                break;
        }
        arrays[parent] = vol;
    }
    public ListNode FindKthToTail (ListNode pHead, int k) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        int count = -1;
        while(fast!=null){
            if(count!=k){
                fast = fast.next;
                count++;
            }
            else{
               fast = fast.next;
               slow = slow.next;
            }
        }
        if(count==k) {
            slow.next = slow.next.next;
            return pHead;
        }else{
            return null;
        }
        // write code here
    }
    public int c (String d) {
        HashSet<Character> set = new HashSet<>();
        char[] ch = d.toCharArray();
        ArrayList<Character> list = new ArrayList<Character>();
        for(int i=0;i<ch.length;i++){
            set.add(ch[i]);
            list.add(ch[i]);
        }
        if(set.size()==ch.length){
            return this.countAll(ch.length);
        }else if(set.size()==1)
            return 1;
        else if(d.length()==0||d==null)
            return 0;
        else{
            set.clear();
                HashSet<ArrayList> hashSet = new HashSet<>();
            for(int i=0;i<ch.length;i++){
                char c = list.get(i);
                ArrayList<Character> temp  = new ArrayList<>();
                temp.addAll(list);
                temp.remove(i);
                for(int j=0;j<list.size();j++){

                    temp.add(j,c);

                    
                    temp.remove(j);
                }
            }
            return hashSet.size();
        }

    }
    public int countAll(int n){
        int result = 1;
        for(int i=2;i<=n;i++){
            result = result*i;
        }
        return result;
    }
    public int  isShuixian(int n){
        int tempn = n;
        int result = 0;
        while(n>0){
            int temp = n%10;
            n = n/10;
            result = temp*temp*temp+result;
        }
        if(result == tempn)
            return 1;
        return 0;
    }
    public int getMoney(int N,int M,int K,int[][]array){
        int [][]dp = new int[N][K+1];

        for(int i=0;i<K+1;i++){
            dp[0][i] = array[0][1];

        }
        for(int i=0;i<N;i++){
            dp[i][0] = array[0][1];
        }
//        int [][]temp = new int[2][N];
//        for(int i=0;i<N;i++){
//            for(int j=0;j<2;j++){
//                temp[j][i] = array[i][j];
//            }
//        }
        for(int i=1;i<N;i++){
            for(int j=1;j<K+1;j++){
                if(array[i][0]-array[i-1][0]>M){
                    return array[i][K];
                }
                if(K<j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][K-j]+array[i][1]);
                }
            }
        }
        return dp[K+1][N];
    }

    public String diff(String[] array,String[] str){
        List<String> list = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            boolean exits = false;
            String[] temp1= array[i].split("-");
            for(int j=0;j<str.length;j++){
                String[] temp2= str[j].split("-");
                if(temp1[0].equals(temp2[0])&&temp2[1].equals(temp1[1])) {
                   // list.add("true-" + temp1[0]);
                    exits = true;
                }
                else if(temp1[0].equals(temp2[0])&&!temp2[1].equals(temp1[1])){
                    list.add("modify-"+temp1[0]);
                    exits = true;
                }

            }
            if(!exits){
                list.add("delete-"+temp1[0]);
            }

        }
        for(int i=0;i<str.length;i++){
            boolean exits = false;
            String[] temp1= str[i].split("-");
            for(int j=0;j<array.length;j++){
                String[] temp2= array[j].split("-");
                if(temp1[0].equals(temp2[0])&&temp2[1].equals(temp1[1])) {

                    exits = true;
                }
                else if(temp1[0].equals(temp2[0])&&!temp2[1].equals(temp1[1])){
                    exits = true;
                }

            }
            if(!exits){
                list.add("add-"+temp1[0]);
            }

        }
        String[] result = new String[list.size()];
        Map<String,String> map = new HashMap<>();
        for(int i=0;i<list.size();i++){
            String temp = list.get(i);
            String[] keyValue = temp.split("-");
            map.put(keyValue[1],temp);
            result[i] = keyValue[1];
        }
        Arrays.sort(result);
//        System.out.println("=====================");
//        for(int i=0;i<result.length;i++){
//            System.out.println(result[i]);
//        }
//        System.out.println("=====================");
        String output = "";
        for(int i=0;i<result.length;i++){
            if(i!=0) {
                output = output+","+map.get(result[i]);
            }else {
                output = output+map.get(result[i]);
            }
        }
        if(result.length==0){
            output = null;
        }
        return output;
    }
    public long countTime(int n,long[][] array){
        long start = array[0][0]+array[0][1];
        long count = array[0][1];
        for(int i=1;i<array.length;i++){
            if(i!=1) {
                start = start + array[i - 1][1];
            }
            if(array[i][0]<=start){
                count = count+(start-array[i][0])+array[i][1];
            }else {
                count = count+array[i][1];
            }
        }
        return count;
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

//        int N = scanner.nextInt();
//        int M = scanner.nextInt();
//        int K = scanner.nextInt();
//        int[][]array = new int[N][2];
//        for(int i=0;i<N;i++){
//            for(int j=0;j<2;j++){
//                array[i][j] = scanner.nextInt();
//            }
//        }
        Main main = new Main();
//        ListNode pHead = new ListNode(1);
//        pHead.next = new ListNode(2);
//        pHead.next.next = new ListNode(3);
//        pHead.next.next.next = new ListNode(4);
//        pHead.next.next.next.next = new ListNode(5);
//        pHead.next.next.next.next.next = new ListNode(6);
//        pHead = main.FindKthToTail(pHead,3);
//        System.out.println(pHead);
        int[] arrays = {49,19,15,30,50};
        TreeNode treeNode = main.getTree(arrays);
        TreeNode root = new TreeNode(49);
        root.left = new TreeNode(19);
        root.right = new TreeNode(50);
        root.left.left = new TreeNode(15);
        root.left.right = new TreeNode(30);
        main.insertIntoTree(41,root);
        System.out.println(root);
        System.out.println();
//        System.out.println(main.getMoney(N,M,K,array));
    }
}
