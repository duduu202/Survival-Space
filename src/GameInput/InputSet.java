package GameInput;

import java.util.LinkedList;

public class InputSet {
	
//	private LinkedList<Input> inputs = new LinkedList<Input>();
	private LinkedList<Integer> inputsInt = new LinkedList<Integer>();
	//static ArrayList<Input> ingre = new ArrayList<>();
	
//	public void add(Input in) {
//		if(!inputs.contains(in)) {
//			inputs.add(in);
//			//System.out.println("Adicionado: " + in);
//		}
//	}
	public void add(Integer in) {
		if(!inputsInt.contains(in)) {
			inputsInt.add(in);
			//System.out.println("Adicionado: " + in);
		}
	}
	/////////////////////////////////////////////
//	public void remove(Input in) {
//		if(inputs.contains(in)) {
//			inputs.remove(in);
//			//System.out.println("Removido: " + in);
//		}
//	}
	public void remove(Integer in) {
		if(inputsInt.contains(in)) {
			inputsInt.remove(in);
			//System.out.println("Adicionado: " + in);
		}
	}
	/////////////////////////////////////////////
//	public boolean isPressed(Input in) {
//		return inputs.contains(in);
//	}
	public boolean isPressed(Integer in) {
		return inputsInt.contains(in);
	}
	/////////////////////////////////////////////
}	
