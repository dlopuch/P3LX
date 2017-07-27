package heronarts.p3lx.ui.mappings;

import heronarts.p3lx.ui.UI2dComponent;

/**
 * Implementations will define how to create a new UI for an object given a particular UI registry's request type
 *
 * @param <T> Type of input components this registry is limited to mapping to (eg {@link heronarts.lx.modulator.LXModulator})
 * @param <R> Type of {@link UIFactoryRequest} that provides context-specific params for factories
 * @param <O> Output UI component type, if more specific than {@link UI2dComponent}
 */
public interface UIFactory <T, R extends UIFactoryRequest, O extends UI2dComponent> {

  /**
   * Reports whether this factory supports creating a UI for a given object
   *
   * Usually this is an instanceof check, ie <pre>return forObject instanceof ThingBuildingUIFor;</pre>
   *
   * @param forObject The object building a UI for
   * @return True if this factory can build a UI for this object
   */
  boolean canBuildUIFor(T forObject);

  O buildUI(R request, T forObject);
}
