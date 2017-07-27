package heronarts.p3lx.ui.mappings;

/**
 * Param container to a {@link UIFactory} that supplies the common context-specific parameters needed to construct a new
 * {@link heronarts.p3lx.ui.UI2dComponent} in the context of the registry's usage.
 *
 * At minimum, a request should specify the parameters needed by the UI2dComponent constructor:
 * {@link heronarts.p3lx.ui.UI2dComponent#UI2dComponent(float, float, float, float)}
 */
public interface UIFactoryRequest {
  float getX();

  float getY();

  float getWidth();
}
