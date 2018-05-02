package org.example.entities.generator;

import java.util.Set;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.OutputConfigurationProvider;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class EntitiesOutputConfigurationProvider extends OutputConfigurationProvider {
  public final static String ENTITIES_GEN = "./entities-gen";
  
  @Override
  public Set<OutputConfiguration> getOutputConfigurations() {
    Set<OutputConfiguration> _outputConfigurations = super.getOutputConfigurations();
    final Procedure1<Set<OutputConfiguration>> _function = (Set<OutputConfiguration> it) -> {
      OutputConfiguration _head = IterableExtensions.<OutputConfiguration>head(it);
      _head.setOutputDirectory(EntitiesOutputConfigurationProvider.ENTITIES_GEN);
    };
    return ObjectExtensions.<Set<OutputConfiguration>>operator_doubleArrow(_outputConfigurations, _function);
  }
}
