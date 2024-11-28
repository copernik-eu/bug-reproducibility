/*
 * Copyright © 2024 Piotr P. Karwasz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.copernik.log4j.example;

import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.plugins.Configurable;
import org.apache.logging.log4j.plugins.Factory;
import org.apache.logging.log4j.plugins.Plugin;
import org.apache.logging.log4j.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.status.StatusLogger;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@Plugin("Foo")
@Configurable
@NullMarked
public class FooAppender extends AbstractAppender {

    private static final Logger LOGGER = StatusLogger.getLogger();

    private FooAppender(String name) {
        super(name, null, null, false, null);
    }

    @Override
    public void append(LogEvent event) {
        System.out.append("Foo appender says: ");
        System.out.println(event.getMessage().getFormattedMessage());
    }

    @Factory
    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder implements Supplier<@Nullable Appender> {
        private Builder() {}

        @PluginBuilderAttribute
        private @Nullable String name;

        @Override
        public @Nullable Appender get() {
            if (name == null) {
                LOGGER.error("No name provided for FooAppender");
                return null;
            }
            return new FooAppender(name);
        }
    }
}
