/*
 * Copyright (c) 2016 Cisco and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fd.honeycomb.translate.spi.read;

import com.google.common.annotations.Beta;
import io.fd.honeycomb.translate.read.ReadContext;
import io.fd.honeycomb.translate.read.ReadFailedException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.binding.Identifier;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

/**
 * CompositeListReader SPI to customize its behavior.
 *
 * @param <O> Specific DataObject derived type (Identifiable), that is handled by this customizer
 * @param <K> Specific Identifier for handled type (C)
 * @param <B> Specific Builder for handled type (C)
 */
@Beta
public interface ListReaderCustomizer<O extends DataObject & Identifiable<K>, K extends Identifier<O>, B extends Builder<O>>
    extends ReaderCustomizer<O, B> {

    /**
     * Return list with IDs of all list nodes to be read.
     *
     * @param id Wildcarded ID pointing to list node managed by enclosing reader
     * @param context Read context
     * @throws ReadFailedException if the list of IDs could not be read
     */
    @Nonnull
    List<K> getAllIds(@Nonnull final InstanceIdentifier<O> id, @Nonnull final ReadContext context) throws
            ReadFailedException;

    /**
     * Merge read data into provided parent builder.
     */
    void merge(@Nonnull final Builder<? extends DataObject> builder, @Nonnull final List<O> readData);

    @Override
    default void merge(@Nonnull final Builder<? extends DataObject> parentBuilder, @Nonnull final O readValue) {
        merge(parentBuilder, Collections.singletonList(readValue));
    }
}
