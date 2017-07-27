package heronarts.p3lx.ui.mappings;

import heronarts.p3lx.ui.UI2dComponent;

import java.util.LinkedList;

/**
 * Provides a mechanism to register UI factories that map some underlying component types to UI's.
 *
 * UI's are P3LX classes.  Underlying component types are LX classes.  A user might want to implement their own
 * components and/or UI's.
 *
 * This registry allows one to separate UI from core logic while making everything user-extensible by just registering
 * an appropriate factory.  eg, in P3LXStudio, we might have for the modulators pane:
 *   - {@link heronarts.lx.audio.BandGate} --> {@link heronarts.p3lx.ui.studio.modulation.UIBandGate}
 *   - {@link heronarts.lx.modulator.VariableLFO} --> {@link heronarts.p3lx.ui.studio.modulation.UIVariableLFO}
 *   - some user modulator --> some custom UI
 *
 * @param <T> Type of input components this registry is limited to mapping to (eg {@link heronarts.lx.modulator.LXModulator})
 * @param <R> Type of {@link UIFactoryRequest} that provides context-specific params for factories
 * @param <O> Output UI component type, if more specific than {@link UI2dComponent}
 */
public class ComponentToUIRegistry <T, R extends UIFactoryRequest, O extends UI2dComponent> {
  private LinkedList<UIFactory<T, R, O>> uiFactories = new LinkedList<>();
  private final R commonRequest;

  public ComponentToUIRegistry(R commonRequest) {
    this.commonRequest = commonRequest;
  }

  public ComponentToUIRegistry<T, R, O> registerFactory(UIFactory<T, R, O> factory) {
    uiFactories.addFirst(factory);
    return this;
  }

  public O getComponent(T forObject) {
    for (UIFactory<T, R, O> factory : uiFactories) {
      if (factory.canBuildUIFor(forObject)) {
        return factory.buildUI(commonRequest, forObject);
      }
    }

    throw new RuntimeException("No UI registered for object type: " + forObject.getClass().getName());
  }
}
