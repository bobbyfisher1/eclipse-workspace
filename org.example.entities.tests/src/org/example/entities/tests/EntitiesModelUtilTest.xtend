package org.example.entities.tests

import org.example.entities.EntitiesModelUtil
import org.example.entities.entities.EntitiesFactory
import org.junit.Test
import static extension org.junit.Assert.*

class EntitiesModelUtilTest {
	val factory = EntitiesFactory.eINSTANCE;

	/*
	 * This checks that the new entity is inserted 
	 * right after the desired existing entity, 
	 * that is, between First and Second.
	 * */
	@Test
	def void testAdEntityAfter() {
		val e1 = factory.createEntity => [name = "First"]
		val e2 = factory.createEntity => [name = "Second"]
		val model = factory.createModel => [
			entities += e1;
			entities += e2;
		]
		EntitiesModelUtil.addEntityAfter(e1, "Added").assertNotNull;
		3.assertEquals(model.entities.size);
		"First".assertEquals(model.entities.get(0).name);
		"Added".assertEquals(model.entities.get(1).name);
		"Second".assertEquals(model.entities.get(2).name);
	}
}
