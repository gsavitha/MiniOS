package OS;

import java.util.ArrayDeque;
import java.util.Queue;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class scheduling extends Thread{
	JDesktopPane desktop;
	
	JInternalFrame frame;
	//JInternalFrame [] all_frames= new JInternalFrame[10];
	int max_processes=4;
	static final Queue<JInternalFrame> q = new ArrayDeque<JInternalFrame>();
	scheduling(JDesktopPane d)
	{ 
		desktop=d;
		
	}
	public  void run(){
		while(true)
		{
			try
			{
			JInternalFrame [] all_frames=desktop.getAllFrames();

			System.out.println(all_frames.length);
			if(all_frames.length<max_processes)
			{frame=q.poll();
			if(frame!=null)
			desktop.add(frame);}
			}catch(ArrayIndexOutOfBoundsException e)
			{
				
			}
				
			
			
			
		}
	}

}


