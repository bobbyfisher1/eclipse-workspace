/**
 * generated by Xtext 2.13.0
 */
package org.example.entities;

import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.example.entities.AbstractEntitiesRuntimeModule;
import org.example.entities.generator.EntitiesOutputConfigurationProvider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("all")
public class EntitiesRuntimeModule extends AbstractEntitiesRuntimeModule {
  public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return EntitiesOutputConfigurationProvider.class;
  }
}