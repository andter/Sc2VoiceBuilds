package com.example.sc2voicebuilds;

public class Build {
	
		int n = 0;
		String buildArray[], temp = "";
		int minuteArray[];
		int secondArray[];
		
		
		public Build(int buildSize)
		{
		int timerSize = buildSize;
		String title = "";
		buildArray = new String[buildSize];
		minuteArray = new int[buildSize];
		secondArray = new int[buildSize];
		}
		
		public void addItem(String name, int minutes, int seconds)
		{
			buildArray[n] = name;
			minuteArray[n] = minutes;
			secondArray[n] = seconds;
			n++;
		}

        public void removeItem(int position){
            if(position < n)
            {
                while (position<n){
                    buildArray[position] = buildArray[position+1];
                    minuteArray[position] = minuteArray[position+1];
                    secondArray[position] = secondArray[position+1];
                    position++;
                }
                n--;
            }


            else if(position == n){
                n--;
            }
        }

        public void removeLastItem(){
            n--;
        }
		
		public String getName()
		{
			return buildArray[n-1];
		}
		
		public int getMinutes()
		{
			return minuteArray[n-1];
		}
		
		public String toString()
		{
			temp = "";
			for (int x = 0; x < n; x++)
			{
				temp += buildArray[x]+"\n";
				temp += minuteArray[x] + "\n";
				temp += secondArray[x] + "\n";
			}
			return temp;

		}
		
		public int getSeconds()
		{
			return secondArray[n-1];
		}
		
		public int returnSize()
		{
			return n-1;
		}
		
		public void checkTime(int m, int s)
		{
		}
		
		
}
