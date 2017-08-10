/**
 * Copyright 2017- Mark C. Slee, Heron Arts LLC
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * @author Mark C. Slee <mark@heronarts.com>
 */

package heronarts.p3lx.ui.studio.global;

import heronarts.lx.LXEngine;
import heronarts.p3lx.ui.UI;
import heronarts.p3lx.ui.component.UIButton;
import heronarts.p3lx.ui.studio.UICollapsibleSection;

public class UIEngine extends UICollapsibleSection {

  public UIEngine(UI ui, LXEngine engine, float x, float y, float w) {
    super(ui, x, y, w, 80);
    setTitle("ENGINE");

    new UIButton(0, 0, getContentWidth(), 16)
    .setParameter(engine.isMultithreaded)
    .setLabel("Engine Thread")
    .addToContainer(this);

    new UIButton(0, 20, getContentWidth(), 16)
    .setParameter(engine.isNetworkMultithreaded)
    .setLabel("Network Thread")
    .addToContainer(this);

    new UIButton(0, 40, getContentWidth(), 16)
    .setParameter(engine.isChannelMultithreaded)
    .setLabel("Channel Threads")
    .addToContainer(this);
  }
}
