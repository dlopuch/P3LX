package heronarts.p3lx.ui.mappings;

import heronarts.lx.LX;
import heronarts.p3lx.ui.UI;


public abstract class AbstractUIFactoryRequestWithLX implements UIFactoryRequestWithLX {
  private final LX lx;
  private final UI ui;

  public AbstractUIFactoryRequestWithLX(LX lx, UI ui) {
    this.lx = lx;
    this.ui = ui;
  }

  @Override
  public LX getLX() {
    return lx;
  }

  @Override
  public UI getUI() {
    return ui;
  }
}
