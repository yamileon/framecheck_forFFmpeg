import java.util.*;
import java.io.*;
class frameCheck{
	public static void main(String[] args){

	menu();
		
	}

	public static void exCmd(String cm){
		try{
			Process p1 = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c",cm});
			try {
				p1.waitFor();
			} 
			catch(InterruptedException e){
				// this part is executed when an exception (in this example InterruptedException) occurs
			}
		}
		catch(IOException e){
			System.out.println("error");
		}
	}
	public static void run(String userin){
		File folder = new File(userin);
		File[] listOfFiles = folder.listFiles();

		String log_location = readlog();

		for (int i = 0; i < listOfFiles.length; i++) {
			if(listOfFiles[i].isFile()){
				System.out.println(i + 1 +" of "+listOfFiles.length);
				
				String x = listOfFiles[i].getName();

				System.out.println("file name is " + x);

				String full = folder+"\\"+ x;

				String log = log_location + "\\" + x;

				String quer = "ffmpeg.exe -v error -i \"" + full + "\" -f null - > \"" + log + "\".txt 2>&1";
				
				System.out.println("checking : " + x);
		
				exCmd(quer);
			}
			System.out.println("done");
		}
		menu();
	}
	public static String input(){
		System.out.println("input directory");
		Scanner in = new Scanner(System.in);
		String userin = in.nextLine();
		return userin;
	}
	public static int input2(){
		System.out.println("input number to select an option");
		Scanner in = new Scanner(System.in);
		int userin = in.nextInt();
		return userin;
	}
	public static void writelog(){
		PrintWriter outfile = null;
		try
		{
			outfile = new PrintWriter("setting.ini");
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		outfile.println(input());
		outfile.close();
		menu();
	}
	public static String readlog(){
		Scanner infile = null;
		String x = null;
		try{
			infile = new Scanner(new File("setting.ini"));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		while(infile.hasNextLine()){
			x = infile.nextLine();
		}
		infile.close();
		return x;
	}
	public static void help(){
		System.out.println("when changeing the log location please make sure that folder location exists");
		menu();
	}
	public static void menu(){
		System.out.println("");
		System.out.println("welcome to the frameCheck utility");
		System.out.println("press the following for an option");
		System.out.println("");
		System.out.println("1 to check");
		System.out.println("2 to change log location");
		System.out.println("3 for more information/help");
		System.out.println("4 to exit");
		System.out.println("");

		int x = input2();
		if(x == 1)run(input());
		else if(x == 2) writelog();
		else if(x == 3) help();
		else if(x == 4) System.exit(1);
		else{
			System.out.println("invalid input");
			menu();
		}
	}
}