//DO NOT CHANGE ANY EXISTING CODE IN THIS FILE
//DO NOT CHANGE THE NAMES OF ANY EXISTING FUNCTIONS
public class CoinChange{

	public static int NumberofCoins(int[] denomination,int value){
		 //Write your code here to find out minimum number of coins required to provide the change for given value.
		 //This method will have a denomination array and an int which specifies the value as inputs(Please see testcase file)
		 //This method should return the number of coins
		int[] arr = new int[value + 1];
		int i;
		for(i = 0; i < value + 1; i++)
			arr[i] = Integer.MAX_VALUE;
		int retval = numCoins(denomination, value, arr);

		return retval;
	}

	public static int numCoins(int[] denomination, int change, int[] arr) {
		if(change == 0)
			return 0;
		if(arr[change] != Integer.MAX_VALUE)
		{
			return arr[change];
		}

		int min = Integer.MAX_VALUE, i, size = denomination.length;
		for(i = 0; i < size; i++)
		{
			int item = denomination[i];
			if(item <= change)
			{
				int x = 1 + numCoins(denomination, change - item, arr);
				if(x < min)
					min = x;
			}
		}

		arr[change] = min;
		return min;
	}
}
