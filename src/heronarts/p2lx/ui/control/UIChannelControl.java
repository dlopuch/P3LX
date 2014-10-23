/**
 * Copyright 2013- Mark C. Slee, Heron Arts LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package heronarts.p2lx.ui.control;

import heronarts.lx.LX;
import heronarts.lx.LXChannel;
import heronarts.lx.parameter.LXListenableNormalizedParameter;
import heronarts.lx.parameter.LXParameter;
import heronarts.lx.pattern.LXPattern;
import heronarts.p2lx.ui.UI;
import heronarts.p2lx.ui.UIWindow;
import heronarts.p2lx.ui.component.UIItemList;
import heronarts.p2lx.ui.component.UIKnob;

import java.util.ArrayList;
import java.util.List;

public class UIChannelControl extends UIWindow {

  private final LXChannel channel;

  private final static int NUM_KNOBS = 12;
  private final static int KNOBS_PER_ROW = 4;

  public final static int WIDTH = 140;
  public final static int HEIGHT = 318;

  public UIChannelControl(UI ui, LX lx, float x, float y) {
    this(ui, lx, "PATTERN", x, y);
  }

  public UIChannelControl(UI ui, LX lx, String label, float x, float y) {
    this(ui, lx.engine.getChannel(0), label, x, y);
  }

  public UIChannelControl(UI ui, LXChannel channel, float x, float y) {
    this(ui, channel, "PATTERN", x, y);
  }

  public UIChannelControl(UI ui, LXChannel channel, String label, float x, float y) {
    this(ui, channel, label, x, y, WIDTH, HEIGHT);
  }

  public UIChannelControl(UI ui, LXChannel channel, String label, float x, float y,
      float w, float h) {
    super(ui, label, x, y, w, h);
    this.channel = channel;
    int yp = TITLE_LABEL_HEIGHT;

    List<UIItemList.Item> items = new ArrayList<UIItemList.Item>();
    for (LXPattern p : channel.getPatterns()) {
      items.add(new PatternScrollItem(p));
    }
    final UIItemList patternList =
      new UIItemList(1, yp, w - 2, 140)
      .setItems(items);
    patternList.setBackgroundColor(ui.theme.getWindowBackgroundColor()).addToContainer(this);
    yp += patternList.getHeight() + 10;

    final UIKnob[] knobs = new UIKnob[NUM_KNOBS];
    for (int ki = 0; ki < knobs.length; ++ki) {
      knobs[ki] = new UIKnob(5 + 34 * (ki % KNOBS_PER_ROW), yp
          + (ki / KNOBS_PER_ROW) * 48);
      knobs[ki].addToContainer(this);
    }

    LXChannel.Listener lxListener = new LXChannel.AbstractListener() {
      @Override
      public void patternWillChange(LXChannel channel, LXPattern pattern, LXPattern nextPattern) {
        patternList.redraw();
      }

      @Override
      public void patternDidChange(LXChannel channel, LXPattern pattern) {
        patternList.redraw();
        int pi = 0;
        for (LXParameter parameter : pattern.getParameters()) {
          if (pi >= knobs.length) {
            break;
          }
          if (parameter instanceof LXListenableNormalizedParameter) {
            knobs[pi++].setParameter((LXListenableNormalizedParameter) parameter);
          }
        }
        while (pi < knobs.length) {
          knobs[pi++].setParameter(null);
        }
      }
    };

    channel.addListener(lxListener);
    lxListener.patternDidChange(channel, channel.getActivePattern());
  }

  private class PatternScrollItem extends UIItemList.AbstractItem {

    private LXPattern pattern;

    private String label;

    PatternScrollItem(LXPattern pattern) {
      this.pattern = pattern;
      this.label = UI.uiClassName(pattern, "Pattern");
    }

    public String getLabel() {
      return this.label;
    }

    public boolean isSelected() {
      return channel.getActivePattern() == this.pattern;
    }

    @Override
    public boolean isPending() {
      return channel.getNextPattern() == this.pattern;
    }

    @Override
    public void onMousePressed() {
      channel.goPattern(this.pattern);
    }
  }
}
