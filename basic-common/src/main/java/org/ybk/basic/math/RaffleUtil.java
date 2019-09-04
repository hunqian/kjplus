package org.ybk.basic.math;

import java.util.Random;

public class RaffleUtil {
	
	//随之抽奖程序
	//输入中奖因子：factor
	//返回：-1=中奖因子过大,1=中奖,0=未中
	public static int drawRandom(double factor){
		if(factor>1)
			return -1;
		int multi = 0;
		//计算抽奖倍数
		//int fv = 0;
		while(true){
			factor = 10*factor;
			if(factor == (int)factor)
				break;
			else{
				multi++;
				if(multi>8){
					factor = (int)factor;
					break;
				}
			}
		}
		int range = 1;
		for(int i=0;i<=multi;i++)
			range = 10*range;
		Random m = new Random();
		int rflNum = m.nextInt(range);
		int rflNum2 = 0;
		boolean prized = false;
		//找到最低倍数
		for(long i=0;i<factor;i++){
			rflNum2 = m.nextInt(range);
			//System.out.println("[抽奖]种子="+rflNum+",随机="+rflNum2);
			if(rflNum == rflNum2){
				//中奖
				prized = true;
				break;
			}
		}
		if(prized)
			return 1;
		else
			return 0;
	}
	
	public static void main(String argc[]){
		/*int multi = 0;
		int factor = Integer.MAX_VALUE;
		while(true){
			factor = factor/10;
			if(factor<=0)
				break;
			else
				multi++;
		}
		System.out.println(Integer.MAX_VALUE+","+multi);*/
		int total2 = 0;
		int count = 10000;
		for(int j=0;j<count;j++){
			int total = 0;
			for(int i=0;i<10000;i++){
				int prize = drawRandom(0.005);
				if(prize==1)
					total++;
					//System.out.println((total++)+"[res]"+prize);
			}
			System.out.println("[中奖次数]"+total);
			total2 += total;
		}
		System.out.println("[平均中奖次数]"+total2*1.0/count);
	}
}
