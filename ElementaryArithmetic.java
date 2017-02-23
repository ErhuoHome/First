import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/11.
 */


public class ElementaryArithmetic {
    //最大公约数
    public static int divisor(int a,int b){
        int temp;
        if(a<b){
            temp=a;a=b;b=temp;
        }
        while(b!=0){
            temp=a%b;
            a=b;
            b=temp;
        }
        return a;
    }
    //最小公倍数
    public static int multiple(int a,int b){
        int temp;
        temp=divisor(a,b);
        return (a*b/temp);
    }
    public static void main(String[] args) {
        int a,b,c,d,j,num=0,i;
        int trueCount=0,falseCount=0;
        int jg;
        int s;
        char sign;
        int a1,b1,jg1;
        int a_c,b_d;
        String inputC;
        Scanner input=new Scanner(System.in);

        while(num<5) {
            num++;
            s=(int)(Math.random()*2);
            i =(int)(Math.random()*4);
            a=(int)(Math.random()*10+1);
            b=(int)(Math.random()*10+1);
            c=(int)(Math.random()*10)+1;
            d=(int)(Math.random()*10)+1;
            if(s==0) {
                switch (i) {
                    case 0:
                        sign = '+';
                        System.out.print(a+""+sign+""+b+"=");
                        jg=input.nextInt();
                        if(jg==a+b){
                            System.out.println("True");trueCount++;
                        }

                        else{
                            System.out.println("Flase");falseCount++;
                        }

                        break;
                    case 1:
                        sign = '-';
                        System.out.print(a+""+sign+""+b+"=");
                        jg=input.nextInt();
                        if(jg==a-b){
                            trueCount++;System.out.println("True");
                        }

                        else{
                            System.out.println("Flase");falseCount++;
                        }

                        break;
                    case 2:
                        sign = '*';
                        System.out.print(a+""+sign+""+b+"=");
                        jg=input.nextInt();
                        if(jg==a*b){
                            trueCount++;System.out.println("True");
                        }

                        else{
                            System.out.println("Flase");falseCount++;
                        }

                        break;
                    case 3:
                        sign = '/';
                        System.out.print(a+""+sign+""+b+"=");
                        inputC=input.next();
                        a1=a;
                        b1=b;
                        a=a/divisor(a1,b1);
                        b=b/divisor(a1,b1);
                        if(inputC.equals(""+a/b)){
                            trueCount++;System.out.println("True");
                        }

                        else if(inputC.equals(a+"/"+b)){
                            System.out.println("True");trueCount++;
                        }

                        else{
                            falseCount++; System.out.println("False");
                        }

                        break;
                }
            }else{


                while(a==c)
                    c=(int)(Math.random()*10)+1;

                while(b==d)
                    d=(int)(Math.random()*10)+1;
                //最简化分子式
                a_c=divisor(a,c);
                if(a_c!=0){
                    a/=a_c;
                    c/=a_c;
                }
                b_d=divisor(b,d);
                if(b_d!=0){
                    b/=b_d;
                    d/=b_d;
                }

                //确定真分数
                int temp;
                if(a>c){
                    temp=a;a=c;c=temp;
                }
                if(b>d){
                    temp=b;b=d;d=temp;
                }

                int fz,fm;
                int k;
                switch (i) {
                    case 0:
                        sign='+';

                        //求解并最简化
                        fz=a*d+b*c;
                        fm=c*d;
                        k=divisor(fz,fm);
                        fz=fz/k;
                        fm=fm/k;
                        System.out.print(a + "/" + c + "" + sign + "" + b + "/" + d + "=");
                        inputC = input.next();
                        if(inputC.equals(fz+"/"+fm)){
                            trueCount++;System.out.println("True");
                        }

                        else if(inputC.equals("1")&&fz==1){
                            trueCount++;System.out.println("True");
                        }

                        else{
                            System.out.println("False");falseCount++;
                        }

                        break;
                    case 1:
                        sign='-';

                        //求解并最简化
                        fz=a*d-b*c;
                        fm=c*d;
                        System.out.println(fm);
                        if(fz<0){
                            fz*=-1;
                        }
                        k=divisor(fz,fm);
                        fz=fz/k;
                        fm=fm/k;
                        System.out.print(a + "/" + c + "" + sign + "" + b + "/" + d + "=");
                        inputC = input.next();
                        if(inputC.equals(fz+"/"+fm)){
                            trueCount++;System.out.println("True");
                        }

                        else if(inputC.equals("0")&&fz==0){
                            System.out.println("True");trueCount++;
                        }

                        else if(inputC.equals("-"+fz+"/"+fm)){
                            System.out.println("True");trueCount++;
                        }

                        else{
                            falseCount++;System.out.println("False");
                        }

                        break;
                    case 2:
                        sign='*';
                        //求解并最简化
                        fz=a*b;
                        fm=c*d;
                        k=divisor(fz,fm);
                        fz=fz/k;
                        fm=fm/k;
                        System.out.print(a + "/" + c + "" + sign + "" + b + "/" + d + "=");
                        inputC = input.next();
                        if(inputC.equals(fz+"/"+fm)){
                            trueCount++;System.out.println("True");
                        }

                        else{
                            falseCount++;System.out.println("False");
                        }

                        break;
                    case 3:
                        sign='/';
                        //求解并最简化
                        fz=a*d;
                        fm=c*b;
                        k=divisor(fz,fm);

                        fz=fz/k;
                        fm=fm/k;
                        System.out.print(a + "/" + c + "" + sign + "" + b + "/" + d + "=");
                        inputC = input.next();

                        if(inputC.equals(fz+"/"+fm)){
                            trueCount++;System.out.println("True");
                        }

                        else if(fm==1&&inputC.equals(""+fz)){
                            trueCount++; System.out.println("True");
                        }

                        else{
                            falseCount++; System.out.println("False");
                        }

                        break;
                }
            }

        }
        System.out.println("True:"+trueCount);
        System.out.println("Accuracy:" + trueCount*20+"%");
    }
}