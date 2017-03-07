import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/11.
 */


public class ElementaryArithmetic {
    //最大公约数
    public static int divisor(int denominator,int numerator){
        int temp;
        if(denominator < numerator){
            temp = denominator;
            denominator = numerator;
            numerator = temp;
        }
        while(numerator != 0){
            temp = denominator % numerator;
            denominator = numerator;
            numerator = temp;
        }
        return denominator;
    }
    //最小公倍数
    public static int multiple(int denominator,int numerator){
        int temp;
        temp = divisor(denominator,numerator);
        return (denominator * numerator / temp);
    }

    public static void main(String[] args) throws InterruptedException {

        int[] denominator=new int[3];    //分子分母声明
        int[] numerator=new int[3];

        int oprator;        //运算符
        char sign;                  //运算符号
        int result;         //结果
        int trueCount=0;    //正确率统计
        int count=0;        //总题数
        int num=0;          //当前题数

        int j;

        int arithmeticModality;     //运算形式

        int[] resultForDivisor=new int[2];
        int resultOfProperFraction;
        int resultOfProperFractionForDivisor;
        String inputC;
        Scanner input=new Scanner(System.in);
        System.out.println("Please input the amount of arithmetic:");

        //检测输入，如果非integer型数值，报错并结束程序
        try{
            count=input.nextInt();
        }catch (Exception e){
            System.out.println("Error");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                System.exit(0);
            }
            System.exit(0);
        }

        //出题
        while(num < count) {
            num++;

            //随机产生数值、运算符、计算形式
            arithmeticModality=(int)(Math.random()*2);
            oprator =(int)(Math.random()*4);
            numerator[0]=(int)(Math.random()*10+1);
            numerator[1]=(int)(Math.random()*10+1);
            denominator[0]=(int)(Math.random()*10)+1;
            denominator[1]=(int)(Math.random()*10)+1;
            if(arithmeticModality == 0) {
                switch (oprator) {
                    case 0:
                        sign = '+';
                        System.out.print(numerator[0] + "" + sign + "" + numerator[0] + "=");
                        result = input.nextInt();
                        if(result == numerator[0] + numerator[1]){
                            System.out.println("True");trueCount++;
                        } else{
                            System.out.println("Flase");
                        }
                        break;

                    case 1:
                        sign = '-';
                        System.out.print( numerator[0] + "" + sign + "" + numerator[1] + "=" );
                        result = input.nextInt();
                        if( result == numerator[0] - numerator[1] ){
                            trueCount++;System.out.println("True");
                        } else{
                            System.out.println("Flase");
                        }
                        break;

                    case 2:
                        sign = '*';
                        System.out.print( numerator[0] + "" + sign + "" + numerator[1] + "=" );
                        result = input.nextInt();
                        if( result == numerator[0] * numerator[1] ){
                            trueCount++;System.out.println("True");
                        } else{
                            System.out.println("Flase");
                        }
                        break;

                    case 3:
                        sign = '/';
                        System.out.print( numerator[0] + "" + sign + "" + numerator[1] + "=");
                        inputC = input.next();

                        //计算最简式
                        resultForDivisor[0] = numerator[0];
                        resultForDivisor[1] = numerator[1];
                        numerator[0] = numerator[0] / divisor(resultForDivisor[0],resultForDivisor[1]);
                        numerator[1] = numerator[0] / divisor(resultForDivisor[0],resultForDivisor[1]);

                        if(inputC.equals("" + numerator[0] / numerator[1] )){
                            trueCount++;System.out.println("True");
                        } else if (inputC.equals(numerator[0] + "/" + numerator[1])){
                            System.out.println("True");trueCount++;
                        } else {
                            System.out.println("False");
                        }
                        break;
                }
            } else {
                //当分子与分母相同时重置
                while(numerator[0] == denominator[0])
                    denominator[0]=(int)(Math.random()*10)+1;
                while(numerator[1] == denominator[1])
                    denominator[1]=(int)(Math.random()*10)+1;

                //最简化分子式
                resultOfProperFractionForDivisor = divisor(numerator[0],denominator[0]);
                if(resultOfProperFractionForDivisor != 0){
                    numerator[0] /= resultOfProperFractionForDivisor;
                    denominator[0] /= resultOfProperFractionForDivisor;
                }
                resultOfProperFractionForDivisor = divisor(numerator[1],denominator[1]);
                if(resultOfProperFractionForDivisor != 0){
                    numerator[1] /= resultOfProperFractionForDivisor;
                    denominator[1] /= resultOfProperFractionForDivisor;
                }

                //确定真分数
                int temp;
                if(numerator[0] > denominator[0]){
                    temp = numerator[0];
                    numerator[0] = denominator[0];
                    denominator[0] = temp;
                }
                if(numerator[1] > denominator[1]){
                    temp = numerator[1];
                    numerator[1] = denominator[1];
                    denominator[1] = temp;
                }

                int resultOfNumerator,resultOfDenominator;
                int resultForDivisorOfOutcome;
                switch (oprator) {
                    case 0:
                        sign = '+';

                        //求解并最简化
                        resultOfNumerator = numerator[0] * denominator[1] + numerator[1] * denominator[0];
                        resultOfDenominator = denominator[0] * denominator[1];
                        resultForDivisorOfOutcome = divisor(resultOfNumerator, resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;

                        System.out.print(numerator[0] + "/" + denominator[0] + "" + sign + "" + numerator[1] + "/" + denominator[1] + "=");
                        inputC = input.next();

                        if(inputC.equals(resultOfNumerator + "/" + resultOfDenominator)){
                            trueCount++;System.out.println("True");
                        } else if (inputC.equals("1")&&resultOfNumerator == 1){
                            trueCount++;System.out.println("True");
                        } else {
                            System.out.println("False");
                        }
                        break;

                    case 1:
                        sign = '-';

                        //求解并最简化
                        resultOfNumerator = numerator[0] * denominator[1] - numerator[1] * denominator[0];
                        resultOfDenominator = denominator[0] * denominator[1];
                        System.out.println(resultOfDenominator);
                        if(resultOfNumerator < 0){
                            resultOfNumerator *= -1;
                        }
                        resultForDivisorOfOutcome = divisor(resultOfNumerator, resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;
                        System.out.print(numerator[0] + "/" + denominator[0] + "" + sign + "" + numerator[1] + "/" + denominator[1] + "=");
                        inputC = input.next();
                        if(inputC.equals(resultOfNumerator + "/" + resultOfDenominator)){
                            trueCount++;System.out.println("True");
                        } else if (inputC.equals("0") && resultOfNumerator == 0){
                            System.out.println("True");trueCount++;
                        } else if (inputC.equals("-" + resultOfNumerator + "/" + resultOfDenominator)){
                            System.out.println("True");trueCount++;
                        } else {
                            System.out.println("False");
                        }
                        break;

                    case 2:
                        sign = '*';
                        //求解并最简化
                        resultOfNumerator = numerator[0] * numerator[1];
                        resultOfDenominator = denominator[0] * denominator[1];
                        resultForDivisorOfOutcome = divisor(resultOfNumerator,resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;
                        System.out.print(numerator[0] + "/" + denominator[0] + "" + sign + "" + numerator[1] + "/" + denominator[1] + "=");
                        inputC = input.next();
                        if(inputC.equals(resultOfNumerator + "/" + resultOfDenominator)){
                            trueCount++;System.out.println("True");
                        } else {
                            System.out.println("False");
                        }
                        break;

                    case 3:
                        sign = '/';

                        //求解并最简化
                        resultOfNumerator = numerator[0] * denominator[1];
                        resultOfDenominator = denominator[0] * numerator[1];
                        resultForDivisorOfOutcome = divisor(resultOfNumerator, resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;

                        System.out.print(numerator[0] + "/" + denominator[0] + "" + sign + "" + numerator[1] + "/" + denominator[1] + "=");
                        inputC = input.next();

                        if(inputC.equals(resultOfNumerator+"/"+resultOfDenominator)){
                            trueCount++;System.out.println("True");
                        } else if (resultOfDenominator == 1 && inputC.equals(""+resultOfNumerator)){
                            trueCount++; System.out.println("True");
                        } else {
                            System.out.println("False");
                        }
                        break;
                }
            }
        }
        System.out.println("True:"+trueCount);
        System.out.println("Accuracy:" + trueCount*20+"%");
    }
}