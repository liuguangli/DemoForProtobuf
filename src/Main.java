import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.tutorial.AddressBookProtos.Person;

public class Main {

	public static void main(String args[]){
		//创建一个Person对象
		Person john = Person.newBuilder()
				     .setId(1234)
				     .setName("John Doe")
				     .setEmail("jdoe@example.com")
				     .addPhone(Person.PhoneNumber.newBuilder()
			                   .setNumber("555-4321")
			                   .setType(Person.PhoneType.HOME))
			 	    .build();
		//将这个对象写到流
		writeToOutputStream(john);
		
		Person reJohn = parsFromInputStream();
		System.out.println(reJohn.getEmail());
		System.out.println(reJohn.getPhone(0).getNumber());
	}

	private static void writeToOutputStream(Person john) {
		// 这里写到一个文件流中，实际上多数会写到网络流中
		File f = new File("/Users/liuguangli/test.txt");
		FileInputStream input;
		FileOutputStream output;
		try {
			output = new FileOutputStream(f);
			john.writeTo(output);
			output.close();
			Person tim = null;
			input = new FileInputStream(f);
			tim = Person.parseFrom(input);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Person parsFromInputStream() {
		// 这里写到一个文件流中，实际上多数会写到网络流中
		File f = new File("/Users/liuguangli/test.txt");
		FileInputStream input;
		
		try {
			
			Person tim = null;
			input = new FileInputStream(f);
			tim = Person.parseFrom(input);
			return tim;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}
}
