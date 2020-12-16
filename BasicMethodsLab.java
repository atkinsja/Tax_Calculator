/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab03b;

import javax.swing.*;
import java.text.*;

public class BasicMethodsLab {

   public static void main( String args[] ) {

    String grossPayStr;     // Value entered by user; string type
    String taxpayerTypeStr;
    String taxpayerTypeName;
    char taxpayerType = ' ';
    double grossPay;        // The double form of the user-entered gross pay
    double stateTax = 0;         // The tax for the pay period
    double netPay = 0;
    double fedTax = 0;
    int resp = 0;           // The user's response as to whether to continue
    DecimalFormat prec1 = new DecimalFormat("#.0%");
    DecimalFormat prec2 = new DecimalFormat("$#.00");
    

    // Initialize the output string to the empty string
    String outputStr = "";

    while (true) {
      // Read in gross pay from user as a string
      grossPayStr = JOptionPane.showInputDialog("Enter gross pay for the pay period");

      // Convert from type String to type double
      grossPay = Double.parseDouble(grossPayStr);

      taxpayerTypeStr = JOptionPane.showInputDialog("Enter the taxpayer type (enter w, b, or m)\n"
        + "w: Weekly\n" + "b: Biweekly\n" + "m: Monthly\n");
      
      switch(taxpayerTypeStr)
      {
          case "w":
              taxpayerType = 'w';
              break;
          case "b":
              taxpayerType = 'b';
              break;
          case "m":
              taxpayerType = 'm';
              break;
          default:
              taxpayerType = 'e';
      }
      
      taxpayerTypeName = getTaxpayerTypeName(taxpayerType);
      // Echo print the entered values
      outputStr = outputStr + taxpayerTypeName + " pay period: ";
      outputStr = outputStr + "Gross pay: " + prec2.format(grossPay);
      
      // Calculate tax for the pay period
      stateTax = calculateTax(grossPay, taxpayerType);
      fedTax = computeFederalTax(grossPay, taxpayerType);
      netPay = calculateNetPay(grossPay, stateTax + fedTax);
      // Print the tax
      outputStr = outputStr + ", " + "Federal Tax: " + prec2.format(fedTax);
      outputStr = outputStr + ", " + "NYSTax: " + prec2.format(stateTax);
      
      outputStr = outputStr + ", " + "Net pay: " + prec2.format(netPay) + "\n";

      // Determine whether the user wants to continue the loop
      resp = JOptionPane.showConfirmDialog(null, outputStr + "\nContinue?", "Confirm",
                                           JOptionPane.YES_NO_OPTION,
                                           JOptionPane.QUESTION_MESSAGE);

      if (resp == JOptionPane.NO_OPTION)
        break;
    }

    // Display final output to the user
    JOptionPane.showMessageDialog(
       null, outputStr, "Final Results", JOptionPane.INFORMATION_MESSAGE);
    
  }
   
   private static double calculateTax(double grossPay, char taxpayerType)
   {
       double calculatedTax = 0;// = grossPay * taxRate;
       switch(taxpayerType)
       {
           case 'w':
               calculatedTax = computeWeeklyTax(grossPay);
               break;
           case 'b':
               calculatedTax = computeBiweeklyTax(grossPay);
               break;
           case 'm':
               calculatedTax = computeMonthlyTax(grossPay);
               break;
           default:
               calculatedTax = 0;
       }
       return calculatedTax;
   }
   
   private static double computeWeeklyTax(double grossPay)
   {
       double tax = 0;
       if(grossPay < 154)
           tax = grossPay * 0.0400;
       else if (grossPay < 212)
           tax = 6.15+((grossPay - 154) * 0.0450);
       else if (grossPay < 250)
           tax = 8.75 + ((grossPay - 212) * 0.0525);
       else if (grossPay < 385)
           tax = 10.77 + ((grossPay - 250) * 0.0590);
       else if (grossPay < 1731)
           tax = 18.71 + ((grossPay - 385) * 0.0685);
       else if (grossPay < 1923)
           tax = 110.92 + ((grossPay - 1731) * 0.0764);
       else if (grossPay < 2885)
           tax = 125.62 + ((grossPay - 1923) * 0.0814);
       else 
           tax = 203.92 + ((grossPay - 2885) * 0.0935);
       return tax;
   }
   
   private static double computeBiweeklyTax(double grossPay)
   {
       double tax = 0;
       if(grossPay < 308)
           tax = grossPay * 0.0400;
       else if (grossPay < 423)
           tax = 12.31+((grossPay - 308) * 0.0450);
       else if (grossPay < 500)
           tax = 17.50 + ((grossPay - 423) * 0.0525);
       else if (grossPay < 769)
           tax = 21.54 + ((grossPay - 500) * 0.0590);
       else if (grossPay < 3462)
           tax = 37.42 + ((grossPay - 769) * 0.0685);
       else if (grossPay < 3846)
           tax = 221.85 + ((grossPay - 3462) * 0.0764);
       else if (grossPay < 5769)
           tax = 251.23 + ((grossPay - 3846) * 0.0814);
       else 
           tax = 407.85 + ((grossPay - 5769) * 0.0935);
       return tax;
   }
   
   private static double computeMonthlyTax(double grossPay)
   {
       double tax = 0;
       if(grossPay < 667)
           tax = grossPay * 0.0400;
       else if (grossPay < 917)
           tax = 26.27 + ((grossPay - 667) * 0.0450);
       else if (grossPay < 1083)
           tax = 37.92 + ((grossPay - 917) * 0.0525);
       else if (grossPay < 1667)
           tax = 46.67 + ((grossPay - 1083) * 0.0590);
       else if (grossPay < 7500)
           tax = 81.08 + ((grossPay - 1667) * 0.0685);
       else if (grossPay < 8333)
           tax = 480.67 + ((grossPay - 7500) * 0.0764);
       else if (grossPay < 12500)
           tax = 544.33 + ((grossPay - 8333) * 0.0814);
       else 
           tax = 883.67 + ((grossPay - 12500) * 0.0935);
       return tax;
   }
   
   private static double computeFederalTax(double grossPay, char taxpayerType)
   {
       double tax = 0;
       double withholdingAmount = 0;
       double adjustedPay = 0;
       switch(taxpayerType)
       {
           case 'w':
               withholdingAmount = 65.38;
               break;
           case 'b':
               withholdingAmount = 130.77;
               break;
           case 'm':
               withholdingAmount = 283.33;
               break;
       }
       adjustedPay = grossPay - withholdingAmount;
       
       if(adjustedPay < 51)
           tax = ((adjustedPay - 0) * 0.0) + 0;
       else if(adjustedPay < 195)
           tax = ((adjustedPay - 51) * .10);
       else if(adjustedPay < 645)
           tax = ((adjustedPay - 195) * .15) + 14.40;
       else if (adjustedPay < 1482)
           tax = ((adjustedPay - 645) * .25) + 81.90;
       else if (adjustedPay < 3131)
           tax = (((adjustedPay) - 1482) * .28) + 291.15;
       else if (adjustedPay < 6763)
           tax = ((adjustedPay - 3131) * .33) + 752.87;
       else
           tax = ((adjustedPay - 6763) * .35) + 1951.43;
       
       return tax;
   }
   
   private static double calculateNetPay(double grossPay, double tax)
   {
       double netPay = grossPay - tax;
       return netPay;
   }
   
   private static String getTaxpayerTypeName(char taxpayerType)
   {
       String taxpayerTypeName = "";
       switch(taxpayerType)
       {
           case 'w':
               taxpayerTypeName = "WEEKLY";
               break;
           case 'b':
               taxpayerTypeName = "BIWEEKLY";
               break;
           case 'm':
               taxpayerTypeName = "MONTHLY";
               break;
           default:
               taxpayerTypeName = "ERROR";
       }
       return taxpayerTypeName;
   }
}
