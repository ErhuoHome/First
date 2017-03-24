import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.nashorn.internal.ir.TernaryNode;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/2/11.
 */


public class ElementaryArithmetic {

    final static int RandomRange = 10;
    final static int OperatorNum = 4;
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

    //判断重复
    public static boolean ArithmeticIsRepeat(ArrayList<String> arithmeticArray, String arithmeticStr, char sign){
        if (arithmeticArray.isEmpty()){
            arithmeticArray.add(arithmeticStr);
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(arithmeticStr);
            int arr_size = 0;
            while (arr_size < arithmeticArray.size()){
                if(arithmeticArray.get(arr_size).equals(arithmeticStr)) {
                    return true;            //重复
                } else if ((sign == '+' || sign == '*') && sb.reverse().toString().equals(arithmeticArray.get(arr_size))){
                    return true;
                }
                arr_size++;
            }
        }
        return false;
    }

    public static boolean IsZero(int[] denominator, int[] numerator) {
        for(int i = 0;i<denominator.length;i++){
            if(denominator[i] == 0 || numerator[i] == 0){
                return true;
            }
        } return false;
    }

    public static void main(String[] args) throws InterruptedException {

        int[] denominator=new int[2];    //分子分母声明
        int[] numerator=new int[2];

        ArrayList<String> arithmeticArray = new ArrayList<String>();        //记录算式，防止重复
        String arithmeticStr =null;                                         //算式

        int operator;        //运算符
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
            operator = (int)(Math.random()*OperatorNum);

            while(IsZero(denominator, numerator)){
                numerator[0] = (int)(Math.random()*RandomRange)+1;
                numerator[1] = (int)(Math.random()*RandomRange)+1;
                denominator[0] = (int)(Math.random()*RandomRange)+1;
                denominator[1] = (int)(Math.random()*RandomRange)+1;
            }
            if(arithmeticModality == 0) {
                inputCount = 0;
                switch (operator) {
                    case 0:
                        sign = '+';
                        result = numerator[0] + numerator[1];
                        break;

                    case 1:
                        sign = '-';
                        result = numerator[0] - numerator[1];
                        break;

                    case 2:
                        sign = '×';
                        result = numerator[0] * numerator[1];
                        break;

                    case 3:
                        sign = '÷';
                        result = ((int)(Math.random() * 1000)+1);

                        //计算最简式
                        resultForDivisor[0] = numerator[0];
                        resultForDivisor[1] = numerator[1];
                        numerator[0] = numerator[0] / divisor(resultForDivisor[0],resultForDivisor[1]);
                        numerator[1] = numerator[0] / divisor(resultForDivisor[0],resultForDivisor[1]);
                        break;
                    default:
                        sign = '\0';
                        result = ((int)(Math.random()* 1000)+1);
                        break;
                }

                arithmeticStr = numerator[0] + " " + sign + " " + numerator[1] ;
                if (ArithmeticIsRepeat(arithmeticArray, arithmeticStr, sign))
                    continue;
                System.out.print(arithmeticStr + "=");

                while(IsError(error)){
                    inputC = input.next();
                    inputCount++;

                    //判断输入的分母是否为0
                    if (inputC.indexOf("/") != -1){
                        String[] str = inputC.split("/");
                        if (str[1].equals("0")){
                            System.out.println("Error");
                            System.exit(0);
                        }
                    }

                    if (inputC.equals(String.valueOf(result))) {
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
                    denominator[0]=(int)(Math.random()*RandomRange)+1;
                while(numerator[1] == denominator[1])
                    denominator[1]=(int)(Math.random()*RandomRange)+1;

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

                if((arithmeticModality = (int)(Math.random()*2)) == 0)
                    denominator[0] = 1 ;

                switch (operator) {
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
                        sign = '×';
                        //求解并最简化
                        resultOfNumerator = numerator[0] * numerator[1];
                        resultOfDenominator = denominator[0] * denominator[1];
                        resultForDivisorOfOutcome = divisor(resultOfNumerator,resultOfDenominator);
                        resultOfNumerator = resultOfNumerator / resultForDivisorOfOutcome;
                        resultOfDenominator = resultOfDenominator / resultForDivisorOfOutcome;

                        break;

                    case 3:
                        sign = '÷';

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
                if(arithmeticModality == 1)
                    arithmeticStr = numerator[0] + "/" + denominator[0] + " " + sign + " " + numerator[1] + "/" + denominator[1];
                else
                    arithmeticStr = numerator[0] + "" + sign + "" + numerator[1] + "/" + denominator[1];
                if (ArithmeticIsRepeat(arithmeticArray, arithmeticStr, sign))
                    continue;
                System.out.print(arithmeticStr + "=");

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