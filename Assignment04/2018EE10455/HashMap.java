
public class HashMap
{
	int M = 24971;
	GrowableArray[] hash_table;
	public static long hash(String s)
    {
        long hash = 5381;
        for (int i = 0;i<s.length();i++) 
        {
        	int character = (int)s.charAt(i);
            hash = ((hash << 5) + hash) + character;
        }
        return hash;
    }

	@SuppressWarnings("unchecked")
	public HashMap()
	{
		hash_table =new GrowableArray[this.M];
	}

	@SuppressWarnings("static-access")
	public void insert(String s)
	{
		int val =(int) Math.floorMod(this.hash(s),M);;
		if (hash_table[val]==null)
		{
			hash_table[val] = new GrowableArray(2);
		}
		hash_table[val].add(s);
	}

	@SuppressWarnings("static-access")
	public boolean contains(String s)
	{
		int val = (int) Math.floorMod(this.hash(s),M);
		if (this.hash_table[val] == null)
		{
			return false;
		}
		for(int i = 0;i<this.hash_table[val].size();i++)
		{
			if (((String)this.hash_table[val].getAt(i)).compareTo(s)==0)
			{
				return true;
			}
		}
		return false;
	}
}


class HashTable
{
	public GrowableArray[] hash_table;
	public int Prime ;
	public long R;
	public GrowableArray checking;

	@SuppressWarnings("unchecked")
	public HashTable(int Prime)
	{
		hash_table = new GrowableArray[Prime];
		this.Prime = Prime;
		this.R = 1779033703;
		this.checking = new GrowableArray(2);
	}
	
	public int hash(String s)
	{
		int count[] = new int[37];
		for(int j = 0;j<s.length();j++)
		{
			count[this.atoi(s.charAt(j))]+=1;
		}
		long hash = 1L;
		for(int i = 0;i<37;i++)
		{
			hash*=Math.floorMod(this.R+2*count[i]*(i+1), this.Prime);
			hash = Math.floorMod(hash, this.Prime);
		}
		
		return (int)hash;
	}

	public void print() {
		for (int i=0; i<checking.size; i++) {
			((GrowableArray)hash_table[(Integer)checking.getAt(i)].getAt(0)).print();
		}
	}
	public int atoi(char temp)
	{
		if( Character.isDigit(temp) )
		{
			return (int)temp - (int)'0';
		}
		else if(Character.isLowerCase(temp))
		{
			return (int)temp - (int)'a'+10;
		}
		else
		{
			return 36;
		}
	}
	public void insert(String s)
	{
		int index = hash(s)%this.Prime;
		checking.add(index);
		if (this.hash_table[index]==null)
		{
			this.hash_table[index] = new GrowableArray(2);
		}
		if(this.hash_table[index].size()==0)
		{
			GrowableArray ans = new GrowableArray(2);
			ans.add(s);
			this.hash_table[index].add(ans);
			return ;
		}
		boolean present = false;
		for(int i = 0;i<this.hash_table[index].size();i++ )
		{
			String we_get = (String)((GrowableArray)this.hash_table[index].getAt(i)).getAt(0);
			if (we_get.length()!= s.length())
				continue;
			boolean got_it = true;
			int [] count1 = new int[37];
			int [] count2 = new int[37];
			for(int j = 0;j<s.length();j++)
			{
				count1[this.atoi(s.charAt(j))]+=1;
				count2[this.atoi(we_get.charAt(j))]+=1;
			}
			for(int j = 0;j<count1.length;j++)
			{
				if (count1[j]!=count2[j])
				{
					got_it = false;
					break;
				}
			}
			if (got_it)
			{
				present = true;
				((GrowableArray)this.hash_table[index].getAt(i)).add(s);
			}
		}
		if (!present)
		{
			GrowableArray ans = new GrowableArray(2);
			ans.add(s);
			this.hash_table[index].add(ans);
		}
	}


	public int isPresent(String s)
	{
		int index = hash(s)%this.Prime;
		if (this.hash_table[index]==null)
		{
			return 0;
		}
		if(this.hash_table[index].size()==0)
		{
			return 0;
		}
        //checking if anagram
		for(int i = 0;i<this.hash_table[index].size();i++ )
		{
            String we_get = (String)((GrowableArray)this.hash_table[index].getAt(i)).getAt(0);
			if (we_get.length()!= s.length())
				continue;
			
			boolean got_it = true;
			int [] count1 = new int[37];
			int [] count2 = new int [37];
			for(int j = 0;j<s.length();j++)
			{
				count1[this.atoi(s.charAt(j))]+=1;
				count2[this.atoi(we_get.charAt(j))]+=1;
			}
			for(int j = 0;j<count1.length;j++)
			{
				if (count1[j]!=count2[j])
				{
					got_it = false;
					break;
				}
			}
			if (got_it)
			{
				return ((GrowableArray)this.hash_table[index].getAt(i)).size();
			}
		}
		return 0;

	}

	public GrowableArray anagrams(String s)
	{
		int index = hash(s)%this.Prime;
		if (this.hash_table[index]==null)
		{
			return null;
		}
		if(this.hash_table[index].size()==0)
		{
			return null;
		}

        //checking if first element is anagram to s
		for(int i = 0;i<this.hash_table[index].size();i++ )
		{
			String we_get = (String)((GrowableArray)this.hash_table[index].getAt(i)).getAt(0);
			if (we_get.length()!= s.length())
				continue;
			boolean got_it = true;
			int [] count1 = new int[37];
			int [] count2 = new int [37];
			for(int j = 0;j<s.length();j++)
			{
				count1[this.atoi(s.charAt(j))]+=1;
				count2[this.atoi(we_get.charAt(j))]+=1;
			}
			for(int j = 0;j<count1.length;j++)
			{
				if (count1[j]!=count2[j])
				{
					got_it = false;
					break;
				}
			}
			if (got_it)
			{
				return (GrowableArray)this.hash_table[index].getAt(i);
			}
		}
		return null;

	}
}