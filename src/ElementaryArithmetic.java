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

    //错误,重新输入的判断
    public static boolean IsError(int error){
        if(error == 1)
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws InterruptedException {

        int[] denominator=new int[3];    //分子分母声明
        int[] numerator=new int[3];

        int oprator;        //运算符
        char sign;                  //运算符号
        int resultOfInput;         //用户输入结果
        int result;                 //计算结果
        int trueCount=0;    //正确率统计
        int count=0;        //总题数
        int num=0;          //当前题数

        int j;

        int arithmeticModality;     //运算形式

        int[] resultForDivisor=new int[2];      //最大公约数运算结果
        int resultOfProperFraction;              //真分数计算结果
        int resultOfProperFractionForDivisor;    //真分数最大公约数运算结果

        String inputC;                           //字符串输入
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

            int error = 1;      //记录对错
            int inputCount;     //输入次数计数

            //随机产生数值、运算符、计算形式
            arithmeticModality = (int)(Math.random()*2);
            oprator = (int)(Math.random()*4);
            numerator[0] = (int)(Math.random()*10)+1;
            numerator[1] = (int)(Math.random()*10)+1;
            denominator[0] = (int)(Math.random()*10)+1;
            denominator[1] = (int)(Math.random()*10)+1;

            if(arithmeticModality == 0) {
                inputCount = 0;
                switch (oprator) {
                    case 0:
                        sign = '+';
                        result = numerator[0] + numerator[1];
                        break;

                    case 1:
                        sign = '-';
                        result = numerator[0] - numerator[1];
                        break;

                    case 2:
                        sign = '*';
                        result = numerator[0] * numerator[1];
                        break;

                    case 3:
                        sign = '/';
                        result = ((int)(Math.random()*10)+1) * 100 + (int)(Math.random()*100)+1;

                        //计算最简式
                        resultForDivisor[0] = numerator[0];
                        resultForDivisor[1] = numerator[1];
                        numerator[0] = numerator[0] / divisor(resultForDivisor[0],resultForDivisor[1]);
                        numerator[1] = numerator[0] / divisor(resultForDivisor[0],resultForDivisor[1]);
                        break;
                    default:
                        sign = '\0';
                        result = ((int)(Math.random()*10)+1) * 100 + (int)(Math.random()*100)+1;
                        break;
                }
                System.out.print(numerator[0] + "" + sign + "" + numerator[1] + "=");
                while(IsError(error)){
                    inputC = input.next();
                    inputCount++;

                    //判断输入的分母是否为0
                    if (inputC.indexOf("/")!=-1){
                        String[] str = inputC.split("/");
                        if (str[1].equals("0")){
                            System.out.println("Error");
                            System.exit(0);
                        }
                    }

                    if (inputC.equals(String.valueOf(result))) {
                        System.out.println("1");
                        System.out.println("True");
                        if (inputCount == 1)
                            trueCount++;
                        break;
                    } else if (inputC.equals("" + numerator[0] / numerator[1] ) && sign == '/') {

                        System.out.println("True");
                        if(inputCount == 1)
                            trueCount++;
                        break;
                    } else if (inputC.equals(numerator[0] + "/" + numerator[1])){
                        System.out.println("3");
                        System.out.println("True");
                        if(inputCount == 1)
                            trueCount++;
                        break;
                    } else {
                        System.out.println("False");
                    }
                    System.out.println("Please input again:");
                }
            } else {

                inputCount = 0;     //重置输入次数

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

                int resultOfNumerator,resultOfDenominator;      //结果的分子与分母
                int resultForDivisorOfOutcome;                  //结果分子分母的最大公约数

                switch (oprator) {
                    case 0:
                        sign = '+';

                        //求解并最简化
                        resultOfNumerator = numerator[0] * denominator[1] + numerator[1] * denominator[0];
                        resultOfDenominator = denominator[0] * denominator[1];
                        resultForDivisorOfOutcome = divisor(resultOfNumerator, resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;
                        break;

                    case 1:
                        sign = '-';

                        //求解并最简化
                        resultOfNumerator = numerator[0] * denominator[1] - numerator[1] * denominator[0];
                        resultOfDenominator = denominator[0] * denominator[1];

                        if(resultOfNumerator < 0){
                            resultOfNumerator *= -1;
                        }
                        resultForDivisorOfOutcome = divisor(resultOfNumerator, resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;
                        break;

                    case 2:
                        sign = '*';
                        //求解并最简化
                        resultOfNumerator = numerator[0] * numerator[1];
                        resultOfDenominator = denominator[0] * denominator[1];
                        resultForDivisorOfOutcome = divisor(resultOfNumerator,resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;

                        break;

                    case 3:
                        sign = '/';

                        //求解并最简化
                        resultOfNumerator = numerator[0] * denominator[1];
                        resultOfDenominator = denominator[0] * numerator[1];
                        resultForDivisorOfOutcome = divisor(resultOfNumerator, resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;
                        break;
                    default:
                        sign='\0';
                        resultOfNumerator=1;
                        resultOfDenominator=2;
                        break;
                }
                System.out.print(numerator[0] + "/" + denominator[0] + "" + sign + "" + numerator[1] + "/" + denominator[1] + "=");
                while (IsError(error)){
                    inputC = input.next();
                    inputCount++;

                    //判断对错
                    if(inputC.equals(resultOfNumerator + "/" + resultOfDenominator)){
                        System.out.println("True");
                        if (inputCount == 1)
                            trueCount++;
                           break;
                    } else if (inputC.equals('1') && resultOfNumerator == resultOfDenominator) {
                       System.out.println("True");
                       if (inputCount == 1)
                           trueCount++;
                       break;
                    } else if (inputC.equals('0') && resultOfNumerator == 0){
                        System.out.println("True");
                        if (inputCount == 1)
                            trueCount++;
                        break;
                    } else if (inputC.equals("-" + resultOfNumerator + "/" + resultOfDenominator)) {
                        System.out.println("True");
                        if(inputCount == 1)
                            trueCount++;
                        break;
                    } else if (resultOfDenominator == 1 && inputC.equals(""+resultOfNumerator)) {
                        System.out.println("True");
                        if (inputCount == 1)
                            trueCount++;
                        break;
                    } else {
                        System.out.println("False");
                    }
                    System.out.println("Please input again:");
                }

            }
        }
        System.out.println("True:"+trueCount);
        System.out.println("Accuracy:" + trueCount*20+"%");
    }
}