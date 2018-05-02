package org.example.xtend.examples

class XtendHelloWorld {
	def static void main(String[] args) {

		/*var (String, int)=>String l = [s, i | s + i + 5];
		 println(l.apply("s", 100 + 5));*/
		val personList = newArrayList( //
		new Person("James", "Smith", 50), //
		new Person("John", "Smith", 40), //
		new Person("James", "Anderson", 40), //
		new Person("John", "Anderson", 30), //
		new Person("Paul", "Anderson", 30));

		val result = personList.filter[firstname.startsWith("J")].sortBy[age] // .take()
		.map[firstname + " " + surname + "(" + age + ")"] //
		.join("; ");

		println(result);
		println(Person.extra);
	}
}

@Data class Person {
	private String firstname
	private String surname
	private int age
	static public String extra = '''
this is a multi-line string 
go ooooooo
fijii
'''
}
