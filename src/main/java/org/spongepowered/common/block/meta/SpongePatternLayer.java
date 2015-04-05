/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.block.meta;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.spongepowered.api.service.persistence.data.DataQuery.of;

import com.google.common.base.Objects;
import org.spongepowered.api.block.tile.data.BannerData.PatternLayer;
import org.spongepowered.api.block.tile.data.BannerPatternShape;
import org.spongepowered.api.item.DyeColor;
import org.spongepowered.api.service.persistence.data.DataContainer;
import org.spongepowered.api.service.persistence.data.MemoryDataContainer;

public class SpongePatternLayer implements PatternLayer {

    private final BannerPatternShape id;
    private final DyeColor color;

    public SpongePatternLayer(BannerPatternShape id, DyeColor color) {
        this.id = checkNotNull(id, "id");
        this.color = checkNotNull(color, "color");
    }

    @Override
    public BannerPatternShape getId() {
        return this.id;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }

    @Override
    public DataContainer toContainer() {
        DataContainer container = new MemoryDataContainer();
        container.set(of("id"), this.id.getId());
        container.set(of("color"), this.color.getName());
        return container;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", this.id)
                .add("color", this.color)
                .toString();
    }

}
