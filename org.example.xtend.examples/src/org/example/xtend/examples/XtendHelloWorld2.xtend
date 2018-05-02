package org.example.xtend.examples

class XtendHelloWorld2 {

	def generateBody(String name, String body) '''
		/* body of «name» */
		«body»
	'''

	def generateMethod(String name, String body) '''
		public void «name»(){
		«generateBody(name, body)»
		}
	'''

	def static void main(String[] args) {
		val generator = new XtendHelloWorld2;
		println(generator.generateMethod(
			"m",
			'''
				System.out.println("Hello World2");
				return;
			'''
		))
	}
}
