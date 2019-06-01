import java.util.*;
import java.lang.Math;

public class Classifier {
	
	public double Hamming(double training[][],double input[],int row){
		
		double hamming_dist=0.0;
		for(int i=0;i<=4;i++)
		{
			hamming_dist+= training[row][i]-input[i];
		}
		
		return Math.abs(hamming_dist);
		
	}
	
	
	public int k_means(double training_data[][],double array[]){
		//Setting up necessary data structures.......
		HashMap<Integer,Double> map_probability=new HashMap<Integer,Double>();
		HashMap<Integer,Double> map_majority_vote=new HashMap<Integer,Double>();
		PriorityQueue<Element> neighbours = new PriorityQueue<Element>(10,new ElementComparator());
		
		
		//Categories should be known before hand to work;
		
		map_probability.put(1, 0.0);
		map_probability.put(2, 0.0);
		map_majority_vote.put(1, 0.0);
		map_majority_vote.put(2, 0.0);
		
		
		//assigning values in priority queue and arrranging them in ascending order...
		
		for(int i=0;i<49;i++)
		{	
			
			double distance=Hamming(training_data,array,i);
			neighbours.add(new Element((int)training_data[i][5],distance));
		}
		
		//Iterate oover neighbours to find probaility of 10 nearest neighbour classes!
		
		Iterator<Element> value=neighbours.iterator();
		
		for(int i=0;i<10;i++)
		 {
			 Element e1=new Element(); //to hold value of neighbour elements that is key and distance
			 int key; //to hold the key/category..
			 if(value.hasNext())
			 {
				 e1=(Element) value.next();
				 key=e1.getkey();
			 }
			 
			 else
			 {
				 System.out.println("Too few training data set....Add more for classification");
				 return -1;
			 }
			 double count=(double)map_probability.get(key)+1;
			 map_probability.put(key,count);
		 }
		
		
		//Itrerate over hah map to find the probability of each category count;
		
		for (Map.Entry<Integer,Double> entry : map_probability.entrySet()){
			map_probability.put(entry.getKey(),entry.getValue()/10.0);
		} 
		
		
		
		int category=-1; //default category
		
		//Iterate over neighbour(priority queue) again to find majority vote of each element and store in hash map_majority_vote
		value=neighbours.iterator();
		for(int i=0;i<10;i++)
		{
			Element e1=new Element();
			int key=0;
			
			if(value.hasNext())
			{
				e1=(Element)value.next();
				key=e1.getkey();
			}
			if(e1.getdistance()==0.0)
			{
				category=e1.getkey();
				return category;
			}
			//System.out.println(probability);
			double vote=(1/Math.pow(e1.getdistance(),2));
			map_majority_vote.put(key,map_majority_vote.get(key)+vote);
		}
		 	
		for(Map.Entry<Integer,Double> entry:map_majority_vote.entrySet())
		{
			int key=entry.getKey();
			double probability=map_probability.get(key);
			map_majority_vote.put(key,entry.getValue()*probability);
		}
		double majority_class=Double.MIN_VALUE;
		for(Map.Entry<Integer,Double> entry:map_majority_vote.entrySet()){
			if(entry.getValue()>majority_class){
				majority_class=entry.getValue();
				category=entry.getKey();
			}
		}
		return category;
	}
}
   