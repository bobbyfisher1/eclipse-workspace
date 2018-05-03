package org.example.entities.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.xbase.lib.util.ReflectExtensions
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.xtext.diagnostics.Severity

@RunWith(XtextRunner)
@InjectWith(EntitiesInjectorProvider)
class EntitiesGeneratorTest {
	@Inject extension CompilationTestHelper
	@Inject extension ReflectExtensions

	@Test
	def void testGeneratedCode() {
		'''
			entity MyEntity {
			  string myAttribute;
			}
		'''.assertCompilesTo(
  		'''
			package entities;
			
			public class MyEntity {
				private String myAttribute;
				
				public String getMyAttribute() {
					return myAttribute;
				}
				
				public void setMyAttribute(String _arg) {
					this.myAttribute = _arg;
				}
				
			}
		''')
	}

	@Test
	def void testGeneratedJavaCodeIsValid() {
		'''
			entity MyEntity {
			  string myAttribute;
			}
		'''.compile[getCompiledClass]
	// check that it is valid Java code
	}

	@Test
	def void testGeneratedJavaCode() {
		'''
			entity E {
			  string myAttribute;
			}
		'''.compile [
			getCompiledClass.newInstance => [
				Assert.assertNull(it.invoke("getMyAttribute"));
				it.invoke("setMyAttribute", "value");
				Assert.assertEquals("value", it.invoke("getMyAttribute"));
			]
		]
	}

//	@Test
//	def void testGeneratedJavaCodeWithTwoClasses() {
//		'''
//			entity FirstEntity {
//			  SecondEntity myAttribute;
//			}
//			
//			entity SecondEntity {
//			  string s;
//			}
//		'''.compile [
//			val FirstEntity = allGeneratedResources.get("") // CompiledClass("entities.FirstEntity").newInstance;
//			val SecondEntity = getCompiledClass("entities.SecondEntity").newInstance;
//			SecondEntity.invoke("setS", "testvalue");
//			FirstEntity.invoke("setMyAttribute", SecondEntity);
//			SecondEntity.assertSame(FirstEntity.invoke("getMyAttribute"));
//			"testvalue".assertEquals(FirstEntity.invoke("getMyAttribute").invoke("getS"));
//		]
//	}
//
//
//	def void testInputWithValidationError() {
//		'''
//			entity MyEntity {
//			        // missing ;
//			        string myAttribute
//			}
//		'''.compile [
//			val allErrors = getErrorsAndWarnings.filter[severity == Severity.ERROR]
//			if (!allErrors.empty) {
//				throw new IllegalStateException(
//					"One or more resources contained errors : " + allErrors.map[toString].join(", ")
//				);
//			}
//		]
//	}
}
