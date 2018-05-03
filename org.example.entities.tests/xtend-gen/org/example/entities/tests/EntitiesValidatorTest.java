package org.example.entities.tests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
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
  
  @Test
  public void testCycleInEntityHierarchy() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity A extends B {}");
      _builder.newLine();
      _builder.append("entity B extends C {}");
      _builder.newLine();
      _builder.append("entity C extends A{}");
      _builder.newLine();
      Model _parse = this._parseHelper.parse(_builder);
      final Procedure1<Model> _function = (Model it) -> {
        this.assertCycleInHierarchy(it, "A");
        this.assertCycleInHierarchy(it, "C");
        this.assertCycleInHierarchy(it, "B");
      };
      ObjectExtensions.<Model>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCycleInHierarchyErrorPosition() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity MyEntity extends MyEntity {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String testInput = _builder.toString();
      this._validationTestHelper.assertError(this._parseHelper.parse(testInput), 
        EntitiesPackage.eINSTANCE.getEntity(), 
        EntitiesValidator.HIERARCHY_CYCLE, 
        testInput.lastIndexOf("MyEntity"), 
        "MyEntity".length());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValidHierarchy() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity FirstEntity  {}");
      _builder.newLine();
      _builder.append("entity SecondEntity extends FirstEntity{}");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
