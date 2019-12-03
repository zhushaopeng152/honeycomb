/*
 * Copyright (c) 2017 Cisco and/or its affiliates.
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

package io.fd.honeycomb.infra.bgp;

import com.google.inject.Inject;
import io.fd.honeycomb.binding.init.ProviderTrait;
import io.netty.channel.EventLoopGroup;
import org.opendaylight.protocol.bgp.parser.spi.BGPExtensionConsumerContext;
import org.opendaylight.protocol.bgp.rib.impl.BGPDispatcherImpl;
import org.opendaylight.protocol.bgp.rib.impl.spi.BGPDispatcher;
import org.opendaylight.protocol.bgp.rib.impl.spi.BGPPeerRegistry;

final class BGPDispatcherImplProvider extends ProviderTrait<BGPDispatcher> {
    @Inject
    private BGPExtensionConsumerContext consumerContext;
    @Inject
    private EventLoopGroup threadGroup;
    @Inject
    private BGPPeerRegistry peerRegistry;

    @Override
    protected BGPDispatcher create() {
        return new BGPDispatcherImpl(consumerContext.getMessageRegistry(), threadGroup, threadGroup, peerRegistry);
    }
}
