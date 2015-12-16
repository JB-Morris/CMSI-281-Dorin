import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

public class WordFrequencies extends java.lang.Object {

	public WordFrequencies(){
	}
	public static void main(String[] args){
		boolean sOption = true;
		boolean cOption = false;
		if(args.length > 0){
			if(args.length > 1){
				throw new IllegalArgumentException("Too many arguments");
			}
			if(args[0].equals("-c")){
				cOption = true;
			}else if(args[0].equals("-s")){
				sOption = false;
			}
		}
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			String current = scanner.nextLine();
			current = current.replaceAll("[-']", "").replaceAll("[^A-Za-z0-9]", " ");
			String[] currentArray = current.split(" ");
			for(String s : currentArray){
				if(sOption){
					s = s.toUpperCase();
				}
				if(s.length() > 0){
					if(hashMap.containsKey(s)){
						hashMap.put(s, hashMap.get(s) + 1);
					}else{
						hashMap.put(s, 1);
					}	
				}	
			}	
		}
		Object[] alphabatized = hashMap.keySet().toArray();
		java.util.Arrays.sort(alphabatized);
		if(cOption){
			for(Object s : alphabatized){
				System.out.println(s);
			}
		}else{
			for(Object s : alphabatized){
				System.out.println((s) + " " + (hashMap.get(s)));
			}	
		}
	}
}