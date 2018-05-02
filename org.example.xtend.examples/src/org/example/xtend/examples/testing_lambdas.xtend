package org.example.xtend.examples

import java.util.Collections

class testing_lambdas {
	def static void main(String[] args) {

		val list = newArrayList("second", 'first', "third", "vier", "zzzz");
		println(list)
//		Collections.sort(list)[a, b|a.compareToIgnoreCase(b)]
		Collections.sort(list)[$0.compareToIgnoreCase($1)]
		println(list)
//		println()
//		println(strings.findFirst[startsWith('f')])
	}
}
