package eu.copernik.bug;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnnotationWarning {

  private static final Logger LOGGER = LogManager.getLogger();

  public static void main(String[] args) {
    LOGGER.log(Level.INFO, "Hello warning!");
  }
}
