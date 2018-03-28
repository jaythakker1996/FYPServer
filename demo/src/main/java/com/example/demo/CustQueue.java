package com.example.demo;

public class CustQueue {
	double rWei[]=new double[10];
	Restaurant rFin[]=new Restaurant[10];
	int i;
	
	CustQueue() {
		i=-1;
	}
	
	public void Qadd(Restaurant r,double w) {
		if(i<9)
		{
			i++;
			rWei[i]=w;
			rFin[i]=r;
			this.sort();
			System.out.println("\n\n\n\n\n Here Selected w = "+w+"\n\n\n\n\n");
		}
		else if(rWei[9]<w)
		{
			rWei[9]=w;
			rFin[9]=r;
			this.sort();
			System.out.println("\n\n\n\n\n Here Selected w = "+w+"\n\n\n\n");
		}
	}
	public void sort()
	{
		for(int j=i;j>0;j--)
		{
			if(rWei[j]>rWei[j-1])
			{
				double temp=rWei[j];
				rWei[j]=rWei[j-1];
				rWei[j-1]=temp;
			}
		}
	}
	public Restaurant Qget(int j) {
		
		System.out.println("\n\n\n\n\n Here Final w = "+rWei[j]+"\n\n\n\n");
		
		return rFin[j];
	}
}
