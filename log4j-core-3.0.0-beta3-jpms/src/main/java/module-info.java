import eu.copernik.log4j.example.Log4jCorePostProcessor;
import eu.copernik.log4j.example.plugins.Log4jPlugins;
import org.apache.logging.log4j.plugins.di.spi.ConfigurableInstanceFactoryPostProcessor;
import org.apache.logging.log4j.plugins.model.PluginService;

module eu.copernik.log4j.example {
    exports eu.copernik.log4j.example;
    opens  eu.copernik.log4j.example to org.apache.logging.log4j.core;

    requires java.base;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.plugins;

    requires static org.jspecify;
    // See: https://github.com/apache/logging-log4j2/issues/3251
    requires static biz.aQute.bnd.annotation;

    provides ConfigurableInstanceFactoryPostProcessor with Log4jCorePostProcessor;
    provides PluginService with Log4jPlugins;
}