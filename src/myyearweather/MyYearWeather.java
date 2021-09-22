/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myyearweather;

import java.util.Random;


/**
 *
 * @author pupil
 */
public class MyYearWeather {
    
    public static void main(String[] args) {
        //минимальная и максимальная температура 
        int min=-30, max=30;
        //Создаем зубчатый массив из 12 строк
        int[][] dayTempInYear = new int[12][];
        dayTempInYear[0] = new int[31]; //январь
        dayTempInYear[1] = new int[28]; //февраль
        dayTempInYear[2] = new int[31]; //март
        dayTempInYear[3] = new int[30]; //апрель
        dayTempInYear[4] = new int[31]; //май
        dayTempInYear[5] = new int[30]; //июнь
        dayTempInYear[6] = new int[31]; //июль
        dayTempInYear[7] = new int[31]; //август
        dayTempInYear[8] = new int[30]; //сентябрь
        dayTempInYear[9] = new int[31]; //октябрь
        dayTempInYear[10] = new int[30]; //ноябрь
        dayTempInYear[11] = new int[31]; //декабрь
        
        Random random = new Random();
        for(int i=0;i<dayTempInYear.length;i++) {
            for(int j = 0;j<dayTempInYear[i].length;j++) {
                if(i==11 || i == 0 || i == 1) {
                    min = -30;
                    max = 0;
                }else if(i==2 || i == 3 || i == 4) {
                    min = -5;
                    max = 15;
                }else if(i==5 || i == 6 || i == 7) {
                    min = 5;
                    max = 30;
                }else if(i==8 || i == 9 || i == 4) {
                    min = -10;
                    max = 10;
                }
                dayTempInYear[i][j]=random.nextInt(max-min+1)+min;
            }
        }
        for(int i=0;i<dayTempInYear.length;i++) {
            for(int j=0; j<dayTempInYear[i].length;j++) {
                System.out.printf("%4d", dayTempInYear[i][j]);
            }
            System.out.println(); 
        }
        double[] averageTemperaturInMonth=new double[12];
        for(int i=0; i<dayTempInYear.length;i++) {
            int daysInMonth =-1;
            for(int j=0; j<dayTempInYear[i].length;j++) {
                averageTemperaturInMonth[i]+=(double)dayTempInYear[i][j];
                daysInMonth=j+1;  
            }
            averageTemperaturInMonth[i]=averageTemperaturInMonth[i]/daysInMonth;
        }
        String[] months1 = {"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};
        System.out.println("Средняя температура по месяцам: ");
        for(int i=0;i<averageTemperaturInMonth.length;i++) {
            System.out.printf("%s: %-4.2f%n",months1[i],averageTemperaturInMonth[i]);
        }
    }  
}
