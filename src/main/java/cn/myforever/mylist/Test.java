package cn.myforever.mylist;

public class Test {

	public static void main(String[] args) {
		//List<String> list = new ArrayList<String>();
		//list.add("A");
		MyArrayList<String> myList = new MyArrayList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		System.out.println(myList);
		myList.add(2,"5");
		System.out.println(myList);
	}

}
