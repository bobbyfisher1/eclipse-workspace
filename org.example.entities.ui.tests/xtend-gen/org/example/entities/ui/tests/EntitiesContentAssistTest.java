package org.example.entities.ui.tests;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractContentAssistTest;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.example.entities.ui.tests.EntitiesUiInjectorProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(EntitiesUiInjectorProvider.class)
@SuppressWarnings("all")
public class EntitiesContentAssistTest extends AbstractContentAssistTest {
  @Test
  public void testEmptyProgram() {
    try {
      this.newBuilder().assertText("entity");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSuperEntity() {
    try {
      this.newBuilder().append("entity E extends ").assertText("E");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSuperEntity2() {
    try {
      this.newBuilder().append("entity A{} entity E extends ").assertText("A", "E");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testAttributeTypes() {
    try {
      this.newBuilder().append("entity E { ").assertText("E", "boolean", "int", "string", "}");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
