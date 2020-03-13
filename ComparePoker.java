import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class ComparePoker {
	 public static int getCardSign(String cards) throws Exception {
	        String s="";//牌值
	        String s1="";//花色
	        for(int i=0;i<cards.length();i+=3) {
	            s+=cards.charAt(i)+"";
	        }
	        for(int i=1;i<cards.length();i+=3) {
	            s1+=cards.charAt(i)+"";
	        }
	        for(int i=0;i<s1.length();i++) {
	            switch(s1.charAt(i)) {
	            case 'D':
	            case 'S':
	            case 'H':
	            case 'C':
	                break;
	            default:
	                throw new  Exception("花色值越界输入异常");
	            }
	        }
	        return getCardslevel(s,s1);
	    }
	    //牌值相关的牌等级
	    public static  int getCardslevel(String s,String s1) {
	        int count_s=0;//相同牌的数量
	        int level = 0;//牌的等级
	        for(int i=0;i<s.length();i++) {
	            for(int j=i+1;j<s.length();j++) {
	                if((s.charAt(i)+"").equals((s.charAt(j)+""))){
	                    count_s++;
	                }
	            }
	        }
	        switch(count_s) {
	        case 1:
	            level=1;//对子
	            break;
	        case 2:
	            level=2;//两对
	            break;
	        case 3:
	            level=3;//三条
	            break;
	        case 4:
	            level=6;//葫芦
	            break;
	        case 6:
	            level=7;//铁支
	            break;
	        default:
	            level=getCardsColor(s,s1);
	        }
	        return level;
	    }
	    //花色相关的牌等级
	    public static int getCardsColor(String s,String s1) {
	        int count_s1=0;
	        int level=0;
	        for(int i=0;i<s1.length();i++) {
	            for(int j=i+1;j<s1.length();j++) {
	                if((s1.charAt(i)+"").equals((s1.charAt(j)+""))){
	                    count_s1++;
	                }
	            }
	        }
	        switch(count_s1) {
	        case 10:
	            level=5;//同花
	            if(transition(s)==true) {
	                level=8;//同花顺
	            }
	            break;
	        default:
	            if(transition(s)==true) {
	                level=4;//顺子
	            }
	        }
	        return level;
	    }
	    //牌面字符转换为数字
	    public static  boolean transition(String s) {
	        char[] a=s.toCharArray();
	        int[] b=new int[a.length];
	        for(int j=0;j<a.length;j++) {
	            if (Character.isDigit(a[j])){  
	                b[j]= Integer.parseInt(String.valueOf(a[j]));
	            } else if(a[j]=='T') {
	                b[j]=10;
	            }else if(a[j]=='J') {
	                b[j]=11;
	            }else if(a[j]=='Q') {
	                b[j]=12;
	            }else if(a[j]=='K') {
	                b[j]=13;
	            }else if(a[j]=='A') {
	                b[j]=1;
	            }
	        }
	        return isNStraightHand(b, b.length);
	    }
	    //判断顺子
	    public static  boolean isNStraightHand(int[] b, int length) {
	        if (b == null || b.length == 0 || b.length % length != 0) {
	            return false;
	        }
	        Arrays.sort(b);
	        Map<Integer, Integer> map = new HashMap<>();
	        for (int i : b) {
	            map.put(i, map.getOrDefault(i, 0) + 1);
	        }
	        for (int h : b) {
	            if (map.get(h) > 0) {
	                for (int j = 0; j < length; j++) {
	                    if (map.getOrDefault(h + j, 0) > 0) {
	                        map.put(h + j, map.get(h + j) - 1);
	                    } else {
	                        return false;
	                    }
	                }
	            }
	        }
	        return true;
	    }
	    //判断两副牌中牌值大的牌组
	    public static  int ValueBig(String cardsFirst,String cardsSecond) {
	        String s_first="";
	        String s1_Second="";
	        for(int i=0;i<cardsFirst.length();i+=3) {
	            s_first+=cardsFirst.charAt(i)+"";
	        }
	        for(int i=0;i<cardsSecond.length();i+=3) {
	            s1_Second+=cardsSecond.charAt(i)+"";
	        }
	        char[] a=s_first.toCharArray();
	        int[] b=new int[a.length];
	        for(int j=0;j<a.length;j++) {
	            if (Character.isDigit(a[j])){  
	                b[j]= Integer.parseInt(String.valueOf(a[j]));
	            } else if(a[j]=='T') {
	                b[j]=10;
	            }else if(a[j]=='J') {
	                b[j]=11;
	            }else if(a[j]=='Q') {
	                b[j]=12;
	            }else if(a[j]=='K') {
	                b[j]=13;
	            }else if(a[j]=='A') {
	                b[j]=14;
	            }
	        }
	        char[] a1=s1_Second.toCharArray();
	        int[] b1=new int[a1.length];
	        for(int j=0;j<a1.length;j++) {
	            if (Character.isDigit(a1[j])){  
	                b1[j]= Integer.parseInt(String.valueOf(a1[j]));
	            } else if(a1[j]=='T') {
	                b1[j]=10;
	            }else if(a1[j]=='J') {
	                b1[j]=11;
	            }else if(a1[j]=='Q') {
	                b1[j]=12;
	            }else if(a1[j]=='K') {
	                b1[j]=13;
	            }else if(a1[j]=='A') {
	                b1[j]=14;
	            }
	        }
	        Arrays.sort(b);
	        Arrays.sort(b1);
	        if (Arrays.equals(b, b1))
	        return 0;
	        else {
	            for(int i=b.length-1;i>=0;i--)
	            {
	                if(b[i]>b1[i]){
	                    return 1;
	                }
	                else if(b[i]<b1[i]){
	                    return -1;
	                }
	            }
	        }
	        return 0;
	        }
	    //判断两副牌是否来自同一副标准扑克牌
	    public static void overCards(String cardsFirst, String cardsSecond) throws Exception {
	        String s_first="";
	        String s1_first="";
	        String s_Second="";
	        String s1_Second="";	
	        for(int i=0;i<cardsFirst.length();i+=3) {
	            s_first+=cardsFirst.charAt(i)+"";
	        }
	        for(int i=1;i<cardsFirst.length();i+=3) {
	            s1_first+=cardsFirst.charAt(i)+"";
	        }
	        for(int i=0;i<cardsSecond.length();i+=3) {
	            s_Second+=cardsSecond.charAt(i)+"";
	        }
	        for(int i=1;i<cardsSecond.length();i+=3) {
	            s1_Second+=cardsSecond.charAt(i)+"";
	        }
	        for(int i=0;i<s1_first.length();i++) {
	            for(int k=0;k<s1_Second.length();k++) {
	                    if((s1_first.charAt(i)+"").equals(s1_Second.charAt(k)+"")) {
	                            if((s_first.charAt(i)+"").equals(s_Second.charAt(k)+"")) {
	                                throw new  Exception("牌数不合法");
	                            }
	                    }
	            }
	        }
	    }
	    //通过两副牌的等级比较牌的大小
	    public static int compareCards(String cardsFirst, String cardsSecond) throws Exception {     
	        overCards(cardsFirst,cardsSecond);
	        int f=getCardSign(cardsFirst);
	        int s=getCardSign(cardsSecond);
	        //System.out.println(f);
	        //System.out.println(s);
	        if(f>s) {
	            return 1;
	        }else if(f<s) {
	            return -1;       
	        }
	        else 
	        return ValueBig(cardsFirst,cardsSecond);
	        }
	    public static void print(int flag)
	    {
			if(flag==1) {
				System.out.print("Black wins");
		    }
		    else if(flag==-1){
		        System.out.print("White wins");
		    }
		    else{
		        System.out.print("Tie");
		    }
	    }
		public static void main(String[] args) throws Exception { 
	    	Scanner sc=new Scanner(System.in);
		    System.out.println("输入: ");
			System.out.print("Black: ");
			String str_First=sc.nextLine();
			System.out.print("White: ");
		    String str_Second=sc.nextLine();
		    sc.close();
	    	int flag=compareCards(str_First,str_Second);
		    System.out.println("输出: ");
			print(flag);
	}
}
