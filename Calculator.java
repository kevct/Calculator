/*	Kevin Tran
	Lab 08
	Section 1
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calculator implements ActionListener{
	static JButton[] Button = new JButton[23];
	static JTextField text = new JTextField("", 30);
	static boolean newNumber = true;
	static double opnd1;
	static double opnd2;
	static char operator;
	static double result;

	public static void main(String[] args){
		//Create
		JFrame frame = new JFrame("Caluclator");
		Container host = frame.getContentPane();
		
		ActionListener AL = new Calculator() ;

		
		//Configure
		host.setLayout(new GridLayout(7,1,2,2));
		JPanel row1 = new JPanel(new GridLayout(1,5,3,3));
		JPanel row2 = new JPanel(new GridLayout(1,5,3,3));
		JPanel row3 = new JPanel(new GridLayout(1,5,3,3));
		JPanel row4 = new JPanel(new GridLayout(1,5,3,3));
		JPanel row5 = new JPanel(new GridLayout(1,3,3,3));
		
		
		//Adding Children
		JLabel label = new JLabel("Calculator", SwingConstants.CENTER);
		
		text.setHorizontalAlignment(SwingConstants.RIGHT);
		
		row1.add(Button[10] = new JButton("BkSP"));
		row1.add(Button[11] = new JButton("CE"));
		row1.add(Button[12] = new JButton("C"));
		row1.add(Button[13] = new JButton("/"));
		row1.add(Button[14] = new JButton("sqrt"));
		
		row2.add(Button[7] = new JButton("7"));
		row2.add(Button[8] = new JButton("8"));
		row2.add(Button[9] = new JButton("9"));
		row2.add(Button[15] = new JButton("*"));
		row2.add(Button[16] = new JButton("%"));
		
		row3.add(Button[4] = new JButton("4"));
		row3.add(Button[5] = new JButton("5"));
		row3.add(Button[6] = new JButton("6"));
		row3.add(Button[17] = new JButton("-"));
		row3.add(Button[18] = new JButton("1/x"));
		
		row4.add(Button[1] = new JButton("1"));
		row4.add(Button[2] = new JButton("2"));
		row4.add(Button[3] = new JButton("3"));
		row4.add(Button[19] = new JButton("+"));
		row4.add(Button[20] = new JButton("+/-"));
		
		row5.add(Button[0] = new JButton("0"));
		row5.add(Button[21] = new JButton("."));
		row5.add(Button[22] = new JButton("="));
		
		for(int i = 0; i < Button.length; i++){
			Button[i].addActionListener(AL);
		}
		if(newNumber){
			text.setText("" + 0);
		}
		
		
		//Add to Parent
		host.add(label);
		host.add(text);
		host.add(row1);
		host.add(row2);
		host.add(row3);
		host.add(row4);
		host.add(row5);
		
		//Listen to it
		frame.pack();
		frame.setSize(400, 225);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		for(int i = 0; i <= 9; i++){
			if(e.getSource() == Button[i]){
				if(newNumber){
					newNumber = false;
					text.setText(Button[i].getText());
				}else{
					text.setText(text.getText() + Button[i].getText());
				}
				return;
			}
		}
		
		if(e.getSource() == Button[15]){ // if it is multiplication
			opnd1 = Double.parseDouble(text.getText());
			newNumber = true;
			operator = '*';
			return;
		}
		
		if(e.getSource() == Button[13]){ // if it is division
			opnd1 = Double.parseDouble(text.getText());
			newNumber = true;
			operator = '/';
			return;
		}
		
		if(e.getSource() == Button[19]){ // if it is addition
			opnd1 = Double.parseDouble(text.getText());
			newNumber = true;
			operator = '+';
			return;
		}
		
		if(e.getSource() == Button[17]){ // if it is subtraction
			opnd1 = Double.parseDouble(text.getText());
			newNumber = true;
			operator = '-';
			return;
		}
		
		if(e.getSource() == Button[11]){ // if CE
			text.setText("" + 0);
			operator = ' ';
			opnd1 = 0;
			opnd2 = 0;
			result = 0;
			newNumber = true;
			return;
		}
		
		if(e.getSource() == Button[12]){ // if C
			text.setText("" + 0);
			newNumber = true;
			return;
		}
		
		if(e.getSource() == Button[10]){ //if backspace
			String str = text.getText();
			if(str.length() == 1){
				text.setText("" + 0);
			}else{
				str = str.substring(0, str.length() - 1);
				text.setText(str);
			}
			return;
		}
		
		if(e.getSource() == Button[14]){ // if it is square root
			result = Math.sqrt(Double.parseDouble(text.getText()));
			if(result % 1 == 0){
				text.setText("" + (int)result);
			}else{
				text.setText("" + result);
			}
			newNumber = true;
			return;
		}
		
		if(e.getSource() == Button[16]){ // if %
			double d = Double.parseDouble(text.getText())/100;
			text.setText("" + d);
			newNumber = true;
			return;
		}
		
		if(e.getSource() == Button[18]){ // if 1/x
			double res = ((1/Double.parseDouble(text.getText())));
			if(res % 1 == 0){
				text.setText("" + (int)res);
			}else{
				text.setText("" + res);
			}
			newNumber = true;
			return;
		}
		
		if(e.getSource() == Button[20]){ // if +/-
			double res = -1 * Double.parseDouble(text.getText());
			if(res % 1 == 0){
				text.setText("" + (int)res);
			}else{
				text.setText("" + res);
			}
			newNumber = false;
			return;
		}
		
		if(e.getSource() == Button[21]){ // if .
			String str = text.getText();
			if(!str.contains(".")){
				str += ".";
				text.setText(str);
			}
			newNumber = false;
			return;
		}
		
		if(e.getSource() == Button[22]){ //if it is equals
			opnd2 = Double.parseDouble(text.getText());
			switch(operator){
				case '*':
					result = opnd1 * opnd2;
					break;
				case '/':
					result = opnd1 / opnd2;
					break;
				case '+':
					result = opnd1 + opnd2;
					break;
				case '-':
					result = opnd1 - opnd2;
					break;
				default:
					result = opnd2;
					break;
			}
			if(result % 1 == 0){
				text.setText("" + (int)result);
			}else{
				text.setText("" + result);
			}
			newNumber = true;
			return;
		}
	}
}