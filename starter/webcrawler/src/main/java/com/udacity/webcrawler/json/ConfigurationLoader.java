package com.udacity.webcrawler.json;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load() throws java.io.IOException {
    try (Reader reader = Files.newBufferedReader(this.path)){
      System.out.println(reader);
      return this.read(reader);
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */

  @JsonDeserialize(builder = CrawlerConfiguration.Builder.class)
  public static CrawlerConfiguration read(Reader reader) throws java.io.IOException {
    try {
      // This is here to get rid of the unused variable warning.
      Objects.requireNonNull(reader);
      ObjectMapper objectMapper = new ObjectMapper();
      //JSON String to a Java object
      CrawlerConfiguration deserializedClass = objectMapper.readValue(reader, CrawlerConfiguration.class);
      return deserializedClass;
    }
    catch(IOException e) {
      e.printStackTrace();
    }

  }
}
