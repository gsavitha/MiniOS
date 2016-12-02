package OS;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;
public class cpu_scheduling{
	
	public static double [] waitingTimes = new double[7];
	
	public static String round_robin(int [][] a,int n)
	{		
		String s ="";
		Arrays.sort(a,new Comparator<int[]>(){
		//@Override
		public int compare(int[] a,int[] b) {
			return Integer.compare(a[1],b[1]);
		}});
		
		
		int quantum=5,i,t;
		int[][] p = new int[n][4];
		String str1=new String("");
		//remaining burst time,waiting time,last executed,flag 
		//flag required to find out of process is completed or not.
		for(i=0;i<n;i++)
		{
			p[i][0]=a[i][2];
			p[i][2]=a[i][1];
			p[i][3]=0;
		}		
		int cur_time=0,p_completed=0,j=0;
		while( p_completed!=n)
		{
			for(i=0;i<n;i++)
			{
				if (a[i][1]>cur_time)
				{
					for(j=0;j<i;j++)
					{
						if(p[j][3]!=1)
							break;
					}
					if(j==i)
						cur_time=a[i][1];
					else
						i=j;
				}
				if(p[i][3]==1)
					continue;
				str1+=String.valueOf(a[i][0])+"->";
				if (p[i][0]<quantum)
					t=p[i][0];
				else
					t=quantum;
				
				p[i][1]+=cur_time-p[i][2];
				p[i][2]=cur_time+t;
				p[i][0]-=t;
				cur_time+=t;
				if (p[i][0]==0)
				{
					p[i][3]=1;
					p_completed+=1;
				}
			}
		}
		double awt=0.0;
		for(i=0;i<n;i++)
		{
			awt+=p[i][1];
			System.out.println("Waiting times of each process");
			s += a[i][0]+"   "+p[i][1]+"\n";
		}
		awt=awt/n;
		waitingTimes[0] = awt;
		return "\n`Round Robin \nOrder of execution: "+str1+"\nAWT: "+awt;
		
	}
	public static String priority_preemptive(int [][] a,int n)
	{
				String s="";
				int i;
				a[0][1]=0;
				//sorting function
				Arrays.sort(a,new Comparator<int[]>(){
					//@Override
					public int compare(int[] a,int[] b) {
						if(a[1]!=b[1])
								return Integer.compare(a[1],b[1]);
						else
							return Integer.compare(a[3], b[3]);
					}});
				//remaining burst time,waiting time,last executed,flag,priority 
				int[][] p=new int [n][5];
				for(i=0;i<n;i++)
				{
					p[i][0]=a[i][2];
					p[i][1]=0;
					p[i][2]=a[i][1];
					p[i][4]=a[i][3];
				}
				int cur_time=0,p_completed=0,t=0,prev_in=0,f=0,ind=0,temp=0;
				String str1=new String("");

				while (p_completed!=n)
				{
					ind=0;
					for(i=0;i<n;i++)
						if(a[i][1]<=cur_time && p[i][4]<=p[ind][4] && p[i][0]>0)
							ind=i;
				
					p[ind][0]-=1;
					if (p[ind][0]<1)
						f=1;
					if (p[ind][0]==0)
					{
							if (p[ind][3]==0)
								p_completed+=1;
							p[ind][3]=1;
							p[ind][4]=100;
					}
					if (ind!=prev_in)
					{
						str1+=String.valueOf(a[prev_in][0])+"->";
						p[prev_in][1]+=temp-t-p[prev_in][2];
						p[prev_in][2]=temp;
						prev_in=ind;
						t=0;
						f=0;
					}
					cur_time+=1;
					if (f!=1)
					{	temp=cur_time;
						t+=1;
					}
				}
				p[ind][1]+=temp-t-p[ind][2];
				str1+=String.valueOf(a[ind][0]);
				double awt=0.0;
				System.out.println("Priority Preemptive\nWaiting times of each process");
				for(i=0;i<n;i++)
				{
					
					s+="P"+a[i][0]+" "+p[i][1]+"\n";
					awt+=p[i][1];
				}
				awt=awt/n;
				waitingTimes[1] = awt;
				return "Priority Preemptive\nOrder of execution: "+str1+"\nAWT: "+awt;
	}
	public static String sjf_preemptive(int [][] a,int n)
	{
				String s="";
				int i;
				a[0][1]=0;
				//sorting function
				Arrays.sort(a,new Comparator<int[]>(){
					//@Override
					public int compare(int[] a,int[] b) {
						if(a[1]!=b[1])
								return Integer.compare(a[1],b[1]);
						else
							return Integer.compare(a[2], b[2]);
					}});
				//remaining burst time,waiting time,last executed,flag 
				int[][] p=new int [n][4];
				for(i=0;i<n;i++)
				{
					p[i][0]=a[i][2];
					p[i][2]=a[i][1];
					p[i][1]=0;
				}
				int cur_time=0,p_completed=0,t=0,prev_in=0,f=0,ind=0,temp=0;
				String str1=new String("");

				while (p_completed!=n)
				{
					ind=0;
					for(i=0;i<n;i++)
						if(a[i][1]<=cur_time && p[i][0]<=p[ind][0] && p[i][0]!=0 && p[i][3]!=1)
							ind=i;
					//System.out.print(ind);
					p[ind][0]-=1;
					if (p[ind][0]<0)
						f=1;
					if (p[ind][0]==0)
					{
							if (p[ind][3]==0)
								p_completed+=1;
							p[ind][3]=1;
							p[ind][0]=1000;
					}
					if (ind!=prev_in)
					{
						str1+=String.valueOf(a[prev_in][0])+"->";
						p[prev_in][1]+=temp-t-p[prev_in][2];
						p[prev_in][2]=temp;
						prev_in=ind;
						t=0;
						f=0;
					}
					cur_time+=1;
					if (f!=1)
					{	temp=cur_time;
						t+=1;
					}
				}
				p[ind][1]+=temp-t-p[ind][2];
				str1+=String.valueOf(a[ind][0]);
				double awt=0.0;
				System.out.println("SJF Preemptive\nWaiting times of each process");
				for(i=0;i<n;i++)
				{
					
					s += "P"+a[i][0]+" "+p[i][1]+"\n";
					awt+=p[i][1];
				}
				awt=awt/n;
				waitingTimes[2] = awt;
				return s+"SJF Preemptive\nOrder of execution: "+str1+"AWT: "+awt;

	}
	public static String SjfNonPreemptive(int[][] a,int n)
	{
		for(int i=0;i<n;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if(a[i][2]>a[j][2])
				{
					int temp = a[i][2];
					a[i][2]=a[j][2];
					a[j][2]=temp;

					temp = a[i][0];
					a[i][0]=a[j][0];
					a[j][0]=temp;

					temp = a[i][1];
					a[i][1]=a[j][1];
					a[j][1]=temp;
				}

				else if(a[i][2]==a[j][2])
				{
					if(a[i][1]>a[j][1])
					{
						int temp = a[i][2];
						a[i][2]=a[j][2];
						a[j][2]=temp;

						temp = a[i][0];
						a[i][0]=a[j][0];
						a[j][0]=temp;

						temp = a[i][1];
						a[i][1]=a[j][1];
						a[j][1]=temp;
					}
				}
			}
		}
		
		int total=0,waiting=0;
		double final1;
		
		total = a[0][2];

		for(int i=1;i<n;i++)
		{
			if(total>a[i][1])
			{
				waiting = waiting + (total-a[i][1]);
			}
			else
				total=a[i][1];

			total = total + a[i][2];
		}
		
		final1 = waiting/n;
		waitingTimes[3] = final1;
		return "The average waiting time for SJF-Non-Preemptive is "+final1;
	}
	
	public static String PriorityNonPreemptive(int[][] A,int n)
	{
		String s ="";
		int timep,total=0,waiting=0,remainprocesses=n,highpriority,i;
		double wait;
		int [] p=new int[n];
		System.out.println("Priority-Non-Preemptive\nWaiting times of each process");
		for(timep=0;remainprocesses!=0;)
		{
			highpriority=0;
			for(i=0;i<n;i++)
			{
				p[i]=A[i][3];
			}
		    for(i=0;i<n;i++)
		    {
		      if(A[i][1]<=timep && p[i]<p[highpriority])
		      {
		        highpriority=i;
		      }
		    }
		    timep = timep + A[highpriority][2];
		    remainprocesses--;
		    waiting = waiting + timep - A[highpriority][1] - A[highpriority][2];
		
			s += "P"+A[highpriority][0]+" "+(timep - A[highpriority][1] - A[highpriority][2])+'\n';
		    total = total + timep - A[highpriority][1];
		    p[highpriority]=1000;
		}
		wait=waiting/n;
		waitingTimes[4] = wait;
		return s+"The average waiting time for Priority-Non-Preemptive is "+wait;
	}
	
	public static String FCFS(int[][] a,int n)
	{
		String s="";
		System.out.println("FCFS\n");
		int CT=0,TAT=0,WT=0;
		int compTime[]=new int[n+1];
		int tatTime[]=new int[n+1];
		float AWT;
		
		for(int i=0;i<n;i++)
		{
			CT=CT+a[i][2]; 
			compTime[i]=CT;	
		}
	
		for(int i=0;i<n;i++)
		{
			TAT=compTime[i]-i;
			tatTime[i]=TAT;
		}
	
		for(int i=0;i<n;i++)
		{
			WT=WT+(tatTime[i]-a[i][1]-a[i][2]);
			s += "P"+a[i][0]+" "+(tatTime[i]-a[i][1])+'\n';
		}
		
		AWT=WT/n;
		waitingTimes[5] = AWT;
		return "\nThe average waiting time for First Come First Serve is "+AWT;
	}
	public static double[] compareValues(int [][]a,int n)
	{
		String s;
	    round_robin(a,n);
		priority_preemptive(a,n);
		sjf_preemptive(a,n);
		PriorityNonPreemptive(a,n);
		FCFS(a,n);
		SjfNonPreemptive(a,n);
		double min = 10000000.0; int ind=0;
		for (int i=0;i<6;++i)
		{
			if (waitingTimes[i]<min){ min = waitingTimes[i]; ind=i;}
				
		}
		waitingTimes[6] = ind;
		return waitingTimes;
		
	}
}
	
		/**public static void main(String[] args) {
		Random r=new Random();
		int n=r.nextInt(10)+1;
		System.out.println( "Number of processes: "+n+"\n");
		Random rand=new Random();
		int[][] a = new int[n][4];
		//process_id,arrival time,burst time,priority
		for (int j=0;j<n;j++)
		{
			a[j][0]=j+1;
		    a[j][1] = rand.nextInt(10);
		    a[j][2] = rand.nextInt(10)+1;
		    a[j][3] = rand.nextInt(n);
		}	
		a[0][1]=0;
		**/
		/*
		 * int [][] a={{1,0,4,1},{2,5,7,2}};
		 */
		/**Arrays.sort(a,new Comparator<int[]>(){
			//@Override
			public int compare(int[] a,int[] b) {
					return Integer.compare(a[1],b[1]);
			}});
		for (int j=0;j<n;j++)
		{
		    System.out.println(a[j][0]+" "+a[j][1]+" "+a[j][2]+" "+a[j][3]);
		}	
		round_robin(a,n);
		priority_preemptive(a,n);
		sjf_preemptive(a,n);
		PriorityNonPreemptive(a,n);
		FCFS(a,n);
		SjfNonPreemptive(a,n);}
	}**/


