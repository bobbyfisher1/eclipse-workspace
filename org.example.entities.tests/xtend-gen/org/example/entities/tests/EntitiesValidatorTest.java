package org.example.entities.tests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.example.entities.entities.EntitiesPackage;
import org.example.entities.entities.Model;
import org.example.entities.tests.EntitiesInjectorProvider;
import org.example.entities.validation.EntitiesValidator;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(EntitiesInjectorProvider.class)
@SuppressWarnings("all")
public class EntitiesValidatorTest {
  @Inject
  @Extension
  private ParseHelper<Model> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void testEntityExtendsItself() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity MyEntity extends MyEntity {");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.assertCycleInHierarchy(this._parseHelper.parse(_builder), "MyEntity");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertCycleInHierarchy(final Model m, final String entityName) {
    this._validationTestHelper.assertError(m, 
      EntitiesPackage.eINSTANCE.getEntity(), 
      EntitiesValidator.HIERARCHY_CYCLE, 
      (("cycle in hierarchy of entity \'" + entityName) + "\'"));
  }
}
