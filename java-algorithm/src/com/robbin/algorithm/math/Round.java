package com.robbin.algorithm.math;

public class Round {
	
	//������d��ʾҪ�������������i��ʾҪ������С�����Ϊ����  
	   /// <summary>  
	   /// ��������  
	   /// </summary>  
	   /// <param name="d">�������</param>  
	   /// <param name="i">Ҫ������С�����Ϊ��</param>  
	   /// <returns>����������</returns>  
	 /*  public static double Round(double d, int i)  
	   {  
	       if (d >= 0)  
	       {  
	           d += 5 * Math.Pow(10, -(i + 1));  
	       }  
	       else  
	       {  
	           d += -5 * Math.Pow(10, -(i + 1));  
	       }  
	       string str = d.ToString();  
	       string[] strs = str.Split('.');  
	       int idot = str.IndexOf('.');  
	       string prestr = strs[0];  
	       string poststr = (strs.Length < 2) ? "0" : strs[1];  
	       if (poststr.Length > i)  
	       {  
	           poststr = str.Substring(idot + 1, i);  
	       }  
	       poststr = (poststr.Length == 0) ? "0" : poststr;  
	       string strd = prestr + "." + poststr;  
	       d = Double.Parse(strd);  
	       return d; 
	   }*/

}
