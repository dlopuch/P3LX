package heronarts.p3lx.ui.mappings;

import heronarts.lx.LX;
import heronarts.p3lx.ui.UI;

/**
 * A {@link UIFactoryRequest} that provides .lx and .ui params
 */
public interface UIFactoryRequestWithLX extends UIFactoryRequest {
  LX getLX();

  UI getUI();
}
