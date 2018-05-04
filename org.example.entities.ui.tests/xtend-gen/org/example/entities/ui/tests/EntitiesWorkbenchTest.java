package org.example.entities.ui.tests;

import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.testing.AbstractWorkbenchTest;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("all")
public class EntitiesWorkbenchTest extends AbstractWorkbenchTest {
  private final String TEST_PROJECT = "mytestproject";
  
  @Before
  @Override
  public void setUp() {
    try {
      super.setUp();
      this.createJavaProjectWithXtextNature();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void createJavaProjectWithXtextNature() {
    try {
      IJavaProject _createJavaProject = JavaProjectSetupUtil.createJavaProject(this.TEST_PROJECT);
      final Procedure1<IJavaProject> _function = (IJavaProject it) -> {
        try {
          IResourcesSetupUtil.addNature(it.getProject(), XtextProjectHelper.NATURE_ID);
          JavaProjectSetupUtil.addSourceFolder(it, "entities-gen");
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      ObjectExtensions.<IJavaProject>operator_doubleArrow(_createJavaProject, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void checkEntityProgram(final String contents, final int expectedErrors) {
    try {
      final IFile file = IResourcesSetupUtil.createFile((this.TEST_PROJECT + "/src/test.entities"), contents);
      IResourcesSetupUtil.waitForBuild();
      Assert.assertEquals(expectedErrors, ((List<IMarker>)Conversions.doWrapArray(file.findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE))).size());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValidProgram() {
    this.checkEntityProgram("entity E {}", 0);
  }
  
  @Test
  public void testNotValidProgram() {
    this.checkEntityProgram("foo", 1);
  }
}
