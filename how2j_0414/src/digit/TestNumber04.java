package digit;

import java.util.Arrays;

/*统计找出一千万以内，一共有多少质数
        质数概念: 只能被1和自己整除的数
        举例:
        5只能被 1和5整除，所以是质数
        8可以被2整除，所以不是质数*/
public class TestNumber04 {

    public void primeNum(int number){
        long startTime=System.currentTimeMillis();
        int sum=0;  //一开始总数是0个
        boolean[] prime =new boolean[number+1];  //让0是0，1是1,比如number=3，数组就是0，1，2，3
        Arrays.fill(prime,true);
        for(int i=0;i<2;i++){
            prime[i]=false;  //0和1不是质数
        }
        //根据埃氏筛法的结论，要得到自然数  N 以内的全部质数
        // 必须把小于等于" 二次根号  N "的所有质数的倍数剔除，剩下的就是质数
        int sqrtNum=(int)(Math.sqrt(number));
        for(int i=2;i<=sqrtNum;i++){  //0和1已经排除在外所以从2开始
            //比如10以内质数，根号10取整是3，3以内质数的2，3，2和3的倍数是4，6，8，9，10
            for(int j=i*i;j<=number;j+=i){  //为了避免重复j=i*i,比如i=2时,4,6,8,10,i=3时,9
                prime[j]=false;
            }
        }
        for(int i=0;i<prime.length;i++){  //计算数组只还有多少
            if(prime[i]){
                sum++;
            }
        }
        System.out.println(sum);
        long endTime=System.currentTimeMillis();
        System.out.println("耗费时间"+(endTime-startTime)+"ms");
    }

    public static void main(String[] args) {
        new TestNumber04().primeNum(10000000);
    }

}
