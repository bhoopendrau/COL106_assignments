import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Anagram
{
	
	public static String ThreeString(String s1, String s2, String s3)
	{
		String ans = "";
		if (s1.length()!=0)
			ans+=s1;
		if (ans.length()!=0 &&s2.length()!=0)
			ans+=" "+s2;
		else if (s2.length()!=0)
			ans+=s2;
		if (ans.length()!=0 && s3.length()!=0)
			ans+=" "+s3;
		else
			ans+=s3;
		return ans;
	}
	
	
	public static void generate_strings(GrowableArray result,String s1, String s2, String s3, String s, int index, HashTable vocab , HashMap hash_table)
	{
		
		if (index == s.length())
		{
			//rejecting small strings because there is no string size less than 3 in vocabulary
			if(s1.length()<=2)
				return;
			if(s2.length()<=2 && s1.length()!=s.length())
				return;
			if(s3.length()<=2 && s1.length()+s2.length()!=s.length())
				return;
			
			//making a string by joining three substrings
			String ans = "";
			if (s1.length()!=0)
				ans+=s1;
			if (ans.length()!=0 &&s2.length()!=0)
				ans+=" "+s2;
			else if (s2.length()!=0)
				ans+=s2;
			if (ans.length()!=0 && s3.length()!=0)
				ans+=" "+s3;
			else
				ans+=s3;

			//if string made is already in vocabulary, reject
			if (hash_table.contains(ans))
				return;
			
			//delcaring three lists for anagrams
			GrowableArray List1 = new GrowableArray(2);
			GrowableArray List2 = new GrowableArray(2);
			GrowableArray List3 = new GrowableArray(2);
			
			//getting anagram lists
			if(s1.length()!=0)
			{
				if(s1.length()<=2)
					return;
				List1 = vocab.anagrams(s1);
				if (List1==null)
					return;
			}
			if(s2.length()!=0)
			{
				if(s2.length()<=2)
					return;
				List2 = vocab.anagrams(s2);
				if (List2==null)
					return;
			}
			
			if(s3.length()!=0)
			{
				if(s3.length()<=2)
					return;
				List3 = vocab.anagrams(s3);
				if (List3==null)
					return;
			}
			
			if (List1==null||List1.size() ==0)
			{
				List1 = new GrowableArray(2);
				List1.add("");
			}
			if (List2==null||List2.size()==0)
			{
				List2 = new GrowableArray(2);
				List2.add("");
			}
			if (List3 == null||List3.size()==0)
			{
				List3 = new GrowableArray(2);
				List3.add("");
			}
			for(int i = 0;i<List1.size();i++)
			{
				for(int j = 0;j<List2.size();j++)
				{
					for(int k  = 0;k<List3.size();k++)
					{
						String ins = ThreeString((String)List1.getAt(i),(String)List2.getAt(j),(String)List3.getAt(k));
						if (i==0 &&j==0 &&k==0 && hash_table.contains(ins))
						{
							return;
						}
						if (ins.compareTo(s)==0)
							return;
						if (ins.length()!=0)
						{
							result.add(ins);
							hash_table.insert(ins);
						}
					}
				}
			}
			return;
		}
		generate_strings(result, s1+s.charAt(index), s2, s3, s, index+1, vocab, hash_table);
		generate_strings(result, s1, s2+s.charAt(index), s3, s, index+1, vocab, hash_table);
		generate_strings(result, s1, s2, s3+s.charAt(index), s, index+1, vocab, hash_table);
	}
	
	public static void Generator(String s, HashTable vocab)
	{
		HashMap hash_table = new HashMap();
		String s1= "";
		String s2 = "";
		String s3 = "";
		GrowableArray result = new GrowableArray(2);
		generate_strings(result,s1,s2,s3,s,0,vocab,hash_table);

		//quicksorting the result
		quickSort(result, 0, result.size-1);
		for(int i = 0;i<result.size();i++)
		{
			System.out.println(result.getAt(i));
		}
	}

	public static void quickSort(GrowableArray arr, int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);
	
			quickSort(arr, begin, partitionIndex-1);
			quickSort(arr, partitionIndex+1, end);
		}
	}
	private static int partition(GrowableArray arr, int begin, int end) {
		String pivot = (String)arr.getAt(end);
		int i = (begin-1);
	
		for (int j = begin; j < end; j++) {
			if (((String)arr.getAt(j)).compareTo(pivot) <= 0) {
				i++;
	
				String swapTemp = (String)arr.getAt(i);
				arr.setAt(i, arr.getAt(j));
				arr.setAt(j, swapTemp);
			}
		}
		String swapTemp = (String)arr.getAt(i+1);
		arr.setAt(i+1, arr.getAt(end));
		arr.setAt(end, swapTemp);
	
		return i+1;
	}

	
//	public HashTable vocab;
	public static void main(String []args)
	{
		HashTable hashtable = new HashTable(24971);
		FileInputStream file;
		Scanner sc;
		long startTime = System.currentTimeMillis();
		try {
			file = new FileInputStream(args[0]);
			sc = new Scanner(file);
			int K = sc.nextInt();
			// int maxChain = 0;
			for(int i=0;i<K;i++)
			{
				String word = sc.next();
				hashtable.insert(word);
			}
			// for(int i = 0;i<hashtable.hash_table.length;i++)
			// {
			// 	if (hashtable.hash_table[i]!=null)
			// 	{
			// 		maxChain = Math.max(maxChain, hashtable.hash_table[i].size());
			// 	}
			// }
			sc.close();
			
			file = new FileInputStream(args[1]);
			sc = new Scanner(file);
			K = sc.nextInt();
			for(int i =0;i<K;i++)
			{
				String word = sc.next();
				Generator(word,hashtable);
				System.out.println(-1);
			}
			sc.close();
			// long endTime = System.currentTimeMillis();
			// System.out.println(endTime-startTime);
			
		} catch (FileNotFoundException e) {
            System.out.println("error");
			e.printStackTrace();
		}
	}
}