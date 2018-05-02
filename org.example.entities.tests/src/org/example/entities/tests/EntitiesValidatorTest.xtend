package org.example.entities.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.example.entities.entities.EntitiesPackage
import org.example.entities.entities.Model
import org.example.entities.validation.EntitiesValidator
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(EntitiesInjectorProvider)
class EntitiesValidatorTest {
	@Inject extension ParseHelper<Model>;
	@Inject extension ValidationTestHelper;

	@Test
	def void testEntityExtendsItself() {
		'''
			entity MyEntity extends MyEntity {
							
			}
		'''.parse.assertCycleInHierarchy("MyEntity");
	}

	def private assertCycleInHierarchy(Model m, String entityName) {
		m.assertError(
			EntitiesPackage.eINSTANCE.entity,
			EntitiesValidator.HIERARCHY_CYCLE,
			"cycle in hierarchy of entity '" + entityName + "'"
		)
	}
}
