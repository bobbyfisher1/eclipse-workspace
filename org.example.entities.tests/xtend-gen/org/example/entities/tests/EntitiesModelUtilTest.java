package org.example.entities.tests;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.example.entities.EntitiesModelUtil;
import org.example.entities.entities.EntitiesFactory;
import org.example.entities.entities.Entity;
import org.example.entities.entities.Model;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class EntitiesModelUtilTest {
  private final EntitiesFactory factory = EntitiesFactory.eINSTANCE;
  
  /**
   * This checks that the new entity is inserted
   * right after the desired existing entity,
   * that is, between First and Second.
   */
  @Test
  public void testAdEntityAfter() {
    Entity _createEntity = this.factory.createEntity();
    final Procedure1<Entity> _function = (Entity it) -> {
      it.setName("First");
    };
    final Entity e1 = ObjectExtensions.<Entity>operator_doubleArrow(_createEntity, _function);
    Entity _createEntity_1 = this.factory.createEntity();
    final Procedure1<Entity> _function_1 = (Entity it) -> {
      it.setName("Second");
    };
    final Entity e2 = ObjectExtensions.<Entity>operator_doubleArrow(_createEntity_1, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      EList<Entity> _entities = it.getEntities();
      _entities.add(e1);
      EList<Entity> _entities_1 = it.getEntities();
      _entities_1.add(e2);
    };
    final Model model = ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    Assert.assertNotNull(EntitiesModelUtil.addEntityAfter(e1, "Added"));
    Assert.assertEquals(3, model.getEntities().size());
    Assert.assertEquals("First", model.getEntities().get(0).getName());
    Assert.assertEquals("Added", model.getEntities().get(1).getName());
    Assert.assertEquals("Second", model.getEntities().get(2).getName());
  }
}
