import java.util.ArrayList;

import javax.swing.*;
import BreezySwing.*;

public class AllStudentsGUI extends GBFrame {

	JTextField name = addTextField("Student name here", 1, 1, 1, 1);
	JTextField hw = addTextField("Enter HW average", 1, 3, 1, 1);
	JTextField quiz = addTextField("Enter Quiz Grades Here", 2, 1, 1, 1);
	JTextField test = addTextField("Enter Test Grades Here", 2, 3, 1, 1);

	JButton input = addButton("Input Student", 20, 1, 1, 1);
	JButton output = addButton("Order by grade", 20, 6, 2, 1);
	JButton output2 = addButton("Order alphabetically", 20, 3, 1, 1);
	JButton stats = addButton("View stats", 20,4, 1, 1);
	

	ArrayList<StudentInfo> list = new ArrayList<StudentInfo>();

	
	
	public void buttonClicked(JButton buttonObj) { // button click "sensor"
		if (buttonObj == input && list.size() < 15) {
			try {
				double hwAvg = Double.parseDouble(hw.getText().trim());
				double quizAvg = parseAvg(quiz.getText());
				double testAvg = parseAvg(test.getText());
				int endAvg = (int) (0.5*(testAvg)+0.3*(quizAvg)+0.2*(hwAvg));

				list.add(new StudentInfo(name.getText(), endAvg));
				
				name.setText("Student name here");
				hw.setText("Enter HW average");
				quiz.setText("Enter Quiz Grades Here");
				test.setText("Enter Test Grades Here");
				
		
			} catch(Exception e) {
				messageBox("Invalid Input: Please try again --> Remember to enter grade input seperated by commas and no spaces", 800, 200);
				e.printStackTrace();
			}
			
			
		} else if(buttonObj == input && list.size() >= 15) {
			messageBox("Max of 15 students allowed");
		}
		
		if(buttonObj == output) {
			sortGrade();
			
			String output = "";
			
			for(int i = 0; i<list.size();i++) {
				output += list.get(i).toString() + "\n";
			}
			messageBox("The class ordered by grades" + "\n" + output, 500, 500);
		}
		
		if(buttonObj == output2) {
			sortAlpha();
			
			String output = "";
			
			for(int i = 0; i<list.size();i++) {
				output += list.get(i).toString() + "\n";
			}
			messageBox("The class ordered by alphabetical order" + "\n" + output, 500, 500);
		}
		
		if(buttonObj == stats) {
			
			double mean = 0;
			for(int i = 0; i<list.size();i++) {
				mean += list.get(i).getAvg();
			}
			mean = mean / list.size();
			
			System.out.println(list);
			System.out.println(mean);
			System.out.println(getMedian());
			System.out.println(getSD(mean));
			System.out.println(getMode());
			
			messageBox("Here are the stats for the class" + "\n" + "Mean: " + mean + "\n" + "Median: " + getMedian() +"\n" + getMode() + "\n" + "Standard Deviation: " + getSD(mean));
			
			
		}
		

	}

	public void sortGrade() {
	   
		    for (int i = 0; i < list.size() - 1; i++) {
		      int minIndex = i;
		      for (int j = i + 1; j < list.size(); j++) {
		        if (list.get(j).getAvg() > list.get(minIndex).getAvg())
		          minIndex = j;
		      }
		      StudentInfo temp = list.get(minIndex);
		      list.set(minIndex, list.get(i));
		      list.set(i, temp);
		    }
		  
	}
	
	public void sortAlpha() {
		   
	    for (int i = 0; i < list.size() - 1; i++) {
	      int minIndex = i;
	      for (int j = i + 1; j < list.size(); j++) {
	        if (list.get(j).getName().compareTo(list.get(minIndex).getName()) < 0  )
	          minIndex = j;
	      }
	      StudentInfo temp = list.get(minIndex);
	      list.set(minIndex, list.get(i));
	      list.set(i, temp);
	   	}
	  
	}
	
	public String getMode() {
		String output = "";
		double number = list.get(0).getAvg();;
	    int frequency = 1;
	    int maxFrequency = 1;
	    for (int i = 1; i < list.size(); i++) {
	      if (list.get(i).getAvg() == number) {
	        frequency++;
	        if (frequency > maxFrequency) {
	          maxFrequency = frequency;
	        }
	      } else {
	        number = list.get(i).getAvg();
	        frequency = 1;
	      }
	    }
	    output += "Mode: ";
	   
	    number = list.get(0).getAvg();
	    frequency = 1;
	    for (int i = 1; i < list.size(); i++) {
	      if (list.get(i).getAvg() == number) {
	        frequency++;
	        if (frequency == maxFrequency) {
	        	output += number + " ";
	        	;
	        }
	      } else {
	        number = list.get(i).getAvg();
	        frequency = 1;
	      }
	    }
	   
	   
	   if(output.equals("Mode: ")) {
		   return "There is no mode";
	   }
	   
	   return output;
	  }
	
		
	
	public double parseAvg(String list) {
		String str = "[" + list + "]";
				
		 
        // calling replaceAll() method and split() method on
        // string
        String[] string = str.replaceAll("\\[", "")
                              .replaceAll("]", "")
                              .split(",");
 
        
        double [] arr = new double[string.length];
 
    
        for (int i = 0; i < string.length; i++) {
            arr[i] = Integer.valueOf(string[i]);
        }
        
       
        
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        
        System.out.println(sum/arr.length);
        return sum/arr.length;
       
	}
	
	public double getMedian() {
		if(list.size()==1) {
			return list.get(0).getAvg();
		}
		
		else if(list.size()%2==0) {
			return (list.get(list.size()/2).getAvg() + list.get(list.size()/2-1).getAvg())/2.0;
		}
		
		else {
			return list.get(list.size()/2).getAvg();
		}
		
		
		
	}
	
	public double getSD(double mean) {
		

        double squareSum = 0;

        for (int i = 0; i < list.size(); i++)
        {

            squareSum += Math.pow(list.get(i).getAvg() - mean, 2);

        }

        return Math.sqrt((squareSum) / (list.size() - 1));

    }

	public static void main(String[] args) {

		JFrame frm = new AllStudentsGUI();
		frm.setTitle("Student Grades");
		frm.setSize(900, 200);
		frm.setVisible(true);
		
	}

}
